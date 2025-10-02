package org.maplibre.android.maps;

import android.graphics.PointF;
import java.util.ArrayList;
import java.util.Iterator;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.android.geometry.ProjectedMeters;
import org.maplibre.android.geometry.VisibleRegion;

/* loaded from: classes2.dex */
public class Projection {
    private final MapView mapView;
    private final NativeMap nativeMapView;

    static double degreesToRadians(double d) {
        return ((d % 360.0d) * 3.141592653589793d) / 180.0d;
    }

    static double radiansToDegrees(double d) {
        return ((d % 6.283185307179586d) * 180.0d) / 3.141592653589793d;
    }

    @Deprecated
    public void invalidateContentPadding() {
    }

    Projection(NativeMap nativeMap, MapView mapView) {
        this.nativeMapView = nativeMap;
        this.mapView = mapView;
    }

    void setContentPadding(int[] iArr) {
        double[] dArr = new double[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            dArr[i] = iArr[i];
        }
        this.nativeMapView.setContentPadding(dArr);
    }

    int[] getContentPadding() {
        double[] dArr = this.nativeMapView.getCameraPosition().padding;
        return new int[]{(int) dArr[0], (int) dArr[1], (int) dArr[2], (int) dArr[3]};
    }

    public ProjectedMeters getProjectedMetersForLatLng(LatLng latLng) {
        return this.nativeMapView.projectedMetersForLatLng(latLng);
    }

    public LatLng getLatLngForProjectedMeters(ProjectedMeters projectedMeters) {
        return this.nativeMapView.latLngForProjectedMeters(projectedMeters);
    }

    public double getMetersPerPixelAtLatitude(double d) {
        return this.nativeMapView.getMetersPerPixelAtLatitude(d);
    }

    public LatLng fromScreenLocation(PointF pointF) {
        return this.nativeMapView.latLngForPixel(pointF);
    }

    public void fromScreenLocations(double[] dArr, double[] dArr2) {
        this.nativeMapView.latLngsForPixels(dArr, dArr2);
    }

    public VisibleRegion getVisibleRegion() {
        return getVisibleRegion(true);
    }

    public VisibleRegion getVisibleRegion(boolean z) {
        float f;
        float f2;
        float height;
        float width;
        Iterator it;
        if (z) {
            width = this.mapView.getWidth();
            height = this.mapView.getHeight();
            f = 0.0f;
            f2 = 0.0f;
        } else {
            int[] contentPadding = getContentPadding();
            f = contentPadding[0];
            float width2 = this.mapView.getWidth() - contentPadding[2];
            f2 = contentPadding[1];
            height = this.mapView.getHeight() - contentPadding[3];
            width = width2;
        }
        LatLng latLngFromScreenLocation = fromScreenLocation(new PointF(((width - f) / 2.0f) + f, ((height - f2) / 2.0f) + f2));
        LatLng latLngFromScreenLocation2 = fromScreenLocation(new PointF(f, f2));
        LatLng latLngFromScreenLocation3 = fromScreenLocation(new PointF(width, f2));
        LatLng latLngFromScreenLocation4 = fromScreenLocation(new PointF(width, height));
        LatLng latLngFromScreenLocation5 = fromScreenLocation(new PointF(f, height));
        ArrayList arrayList = new ArrayList();
        arrayList.add(latLngFromScreenLocation3);
        arrayList.add(latLngFromScreenLocation4);
        arrayList.add(latLngFromScreenLocation5);
        arrayList.add(latLngFromScreenLocation2);
        Iterator it2 = arrayList.iterator();
        double d = 0.0d;
        double longitude = 0.0d;
        double longitude2 = 0.0d;
        double latitude = -90.0d;
        double latitude2 = 90.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        while (it2.hasNext()) {
            LatLng latLng = (LatLng) it2.next();
            if (bearing(latLngFromScreenLocation, latLng) >= d) {
                it = it2;
                double longitudeSpan = getLongitudeSpan(latLng.getLongitude(), latLngFromScreenLocation.getLongitude());
                if (longitudeSpan > d3) {
                    longitude = latLng.getLongitude();
                    d3 = longitudeSpan;
                }
            } else {
                it = it2;
                double longitudeSpan2 = getLongitudeSpan(latLngFromScreenLocation.getLongitude(), latLng.getLongitude());
                if (longitudeSpan2 > d2) {
                    longitude2 = latLng.getLongitude();
                    d2 = longitudeSpan2;
                }
            }
            if (latitude < latLng.getLatitude()) {
                latitude = latLng.getLatitude();
            }
            if (latitude2 > latLng.getLatitude()) {
                latitude2 = latLng.getLatitude();
            }
            it2 = it;
            d = 0.0d;
        }
        if (longitude < longitude2) {
            return new VisibleRegion(latLngFromScreenLocation2, latLngFromScreenLocation3, latLngFromScreenLocation5, latLngFromScreenLocation4, LatLngBounds.from(latitude, longitude + 360.0d, latitude2, longitude2));
        }
        return new VisibleRegion(latLngFromScreenLocation2, latLngFromScreenLocation3, latLngFromScreenLocation5, latLngFromScreenLocation4, LatLngBounds.from(latitude, longitude, latitude2, longitude2));
    }

    public void getVisibleCoordinateBounds(double[] dArr) {
        this.nativeMapView.getVisibleCoordinateBounds(dArr);
    }

    static double bearing(LatLng latLng, LatLng latLng2) {
        double dDegreesToRadians = degreesToRadians(latLng.getLongitude());
        double dDegreesToRadians2 = degreesToRadians(latLng2.getLongitude());
        double dDegreesToRadians3 = degreesToRadians(latLng.getLatitude());
        double dDegreesToRadians4 = degreesToRadians(latLng2.getLatitude());
        double d = dDegreesToRadians2 - dDegreesToRadians;
        return radiansToDegrees(Math.atan2(Math.sin(d) * Math.cos(dDegreesToRadians4), (Math.cos(dDegreesToRadians3) * Math.sin(dDegreesToRadians4)) - ((Math.sin(dDegreesToRadians3) * Math.cos(dDegreesToRadians4)) * Math.cos(d))));
    }

    static double getLongitudeSpan(double d, double d2) {
        double dAbs = Math.abs(d - d2);
        return d > d2 ? dAbs : 360.0d - dAbs;
    }

    public PointF toScreenLocation(LatLng latLng) {
        return this.nativeMapView.pixelForLatLng(latLng);
    }

    public void toScreenLocations(double[] dArr, double[] dArr2) {
        this.nativeMapView.pixelsForLatLngs(dArr, dArr2);
    }

    float getHeight() {
        return this.mapView.getHeight();
    }

    float getWidth() {
        return this.mapView.getWidth();
    }

    public double calculateZoom(float f) {
        return this.nativeMapView.getZoom() + (Math.log(f) / Math.log(2.0d));
    }
}

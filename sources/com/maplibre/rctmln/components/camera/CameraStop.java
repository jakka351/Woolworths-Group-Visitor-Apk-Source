package com.maplibre.rctmln.components.camera;

import android.content.Context;
import android.util.DisplayMetrics;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.geojson.FeatureCollection;

/* loaded from: classes3.dex */
public class CameraStop {
    private Double mBearing;
    private LatLngBounds mBounds;
    private MapLibreMap.CancelableCallback mCallback;
    private LatLng mLatLng;
    private Double mTilt;
    private Double mZoom;
    private int mPaddingLeft = 0;
    private int mPaddingRight = 0;
    private int mPaddingBottom = 0;
    private int mPaddingTop = 0;
    private int mMode = 2;
    private int mDuration = 2000;

    public void setBearing(double d) {
        this.mBearing = Double.valueOf(d);
    }

    public void setTilt(double d) {
        this.mTilt = Double.valueOf(d);
    }

    public void setZoom(double d) {
        this.mZoom = Double.valueOf(d);
    }

    public void setLatLng(LatLng latLng) {
        this.mLatLng = latLng;
    }

    public void setDuration(int i) {
        this.mDuration = i;
    }

    public void setCallback(MapLibreMap.CancelableCallback cancelableCallback) {
        this.mCallback = cancelableCallback;
    }

    public void setBounds(LatLngBounds latLngBounds) {
        this.mBounds = latLngBounds;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingRight = i2;
        this.mPaddingTop = i3;
        this.mPaddingBottom = i4;
    }

    public void setMode(int i) {
        this.mMode = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.maplibre.rctmln.components.camera.CameraUpdateItem toCameraUpdate(com.maplibre.rctmln.components.mapview.RCTMLNMapView r20) {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.maplibre.rctmln.components.camera.CameraStop.toCameraUpdate(com.maplibre.rctmln.components.mapview.RCTMLNMapView):com.maplibre.rctmln.components.camera.CameraUpdateItem");
    }

    public static CameraStop fromReadableMap(Context context, ReadableMap readableMap, MapLibreMap.CancelableCallback cancelableCallback) {
        CameraStop cameraStop = new CameraStop();
        if (readableMap.hasKey("pitch")) {
            cameraStop.setTilt(readableMap.getDouble("pitch"));
        }
        if (readableMap.hasKey("heading")) {
            cameraStop.setBearing(readableMap.getDouble("heading"));
        }
        int paddingByKey = getPaddingByKey(readableMap, ViewProps.PADDING_TOP);
        int paddingByKey2 = getPaddingByKey(readableMap, ViewProps.PADDING_RIGHT);
        int paddingByKey3 = getPaddingByKey(readableMap, ViewProps.PADDING_BOTTOM);
        int paddingByKey4 = getPaddingByKey(readableMap, ViewProps.PADDING_LEFT);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        cameraStop.setPadding(Float.valueOf(paddingByKey4 * displayMetrics.scaledDensity).intValue(), Float.valueOf(paddingByKey2 * displayMetrics.scaledDensity).intValue(), Float.valueOf(paddingByKey * displayMetrics.scaledDensity).intValue(), Float.valueOf(paddingByKey3 * displayMetrics.scaledDensity).intValue());
        if (readableMap.hasKey("centerCoordinate")) {
            cameraStop.setLatLng(GeoJSONUtils.toLatLng(GeoJSONUtils.toPointGeometry(readableMap.getString("centerCoordinate"))));
        }
        if (readableMap.hasKey("zoom")) {
            cameraStop.setZoom(readableMap.getDouble("zoom"));
        }
        if (readableMap.hasKey("duration")) {
            cameraStop.setDuration(readableMap.getInt("duration"));
        }
        if (readableMap.hasKey("bounds")) {
            cameraStop.setBounds(GeoJSONUtils.toLatLngBounds(FeatureCollection.fromJson(readableMap.getString("bounds"))));
        }
        if (readableMap.hasKey("mode")) {
            int i = readableMap.getInt("mode");
            if (i == 1) {
                cameraStop.setMode(1);
            } else if (i == 3) {
                cameraStop.setMode(3);
            } else if (i == 4) {
                cameraStop.setMode(4);
            } else {
                cameraStop.setMode(2);
            }
        }
        cameraStop.setCallback(cancelableCallback);
        return cameraStop;
    }

    private static int[] clippedPadding(int[] iArr, RCTMLNMapView rCTMLNMapView) {
        int height = rCTMLNMapView.getHeight();
        int width = rCTMLNMapView.getWidth();
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        int i5 = i2 + i4;
        if (i5 >= height) {
            double d = i5;
            double d2 = (d - height) + 1.0d;
            double d3 = i2;
            i2 = (int) (d3 - ((d3 * d2) / d));
            double d4 = i4;
            i4 = (int) (d4 - ((d2 * d4) / d));
        }
        int i6 = i + i3;
        if (i6 >= width) {
            double d5 = i6;
            double d6 = (d5 - width) + 1.0d;
            double d7 = i;
            i = (int) (d7 - ((d7 * d6) / d5));
            double d8 = i3;
            i3 = (int) (d8 - ((d6 * d8) / d5));
        }
        return new int[]{i, i2, i3, i4};
    }

    private static int getPaddingByKey(ReadableMap readableMap, String str) {
        if (readableMap.hasKey(str)) {
            return readableMap.getInt(str);
        }
        return 0;
    }
}

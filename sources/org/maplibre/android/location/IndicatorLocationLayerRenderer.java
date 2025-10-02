package org.maplibre.android.location;

import android.graphics.Bitmap;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.Layer;
import org.maplibre.android.style.layers.PropertyValue;
import org.maplibre.android.utils.BitmapUtils;
import org.maplibre.android.utils.ColorUtils;

/* loaded from: classes2.dex */
class IndicatorLocationLayerRenderer implements LocationLayerRenderer {
    private LatLng lastLatLng;
    private Layer layer;
    private final LayerSourceProvider layerSourceProvider;
    private Style style;
    private double lastBearing = 0.0d;
    private float lastAccuracy = 0.0f;

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void adjustPulsingCircleLayerVisibility(boolean z) {
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void cameraBearingUpdated(double d) {
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void cameraTiltUpdated(double d) {
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void stylePulsingCircle(LocationComponentOptions locationComponentOptions) {
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void updateIconIds(String str, String str2, String str3, String str4, String str5) {
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void updatePulsingUi(float f, Float f2) {
    }

    IndicatorLocationLayerRenderer(LayerSourceProvider layerSourceProvider) {
        this.layerSourceProvider = layerSourceProvider;
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void initializeComponents(Style style) {
        this.style = style;
        this.layer = this.layerSourceProvider.generateLocationComponentLayer();
        LatLng latLng = this.lastLatLng;
        if (latLng != null) {
            setLatLng(latLng);
        }
        setLayerBearing(this.lastBearing);
        setAccuracyRadius(Float.valueOf(this.lastAccuracy));
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void addLayers(LocationComponentPositionManager locationComponentPositionManager) {
        locationComponentPositionManager.addLayerToMap(this.layer);
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void removeLayers() {
        this.style.removeLayer(this.layer);
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void hide() {
        setLayerVisibility(false);
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void show(int i, boolean z) {
        setImages(i, z);
        setLayerVisibility(true);
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void styleAccuracy(float f, int i) {
        float[] fArrColorToRgbaArray = ColorUtils.colorToRgbaArray(i);
        fArrColorToRgbaArray[3] = f;
        Expression expressionRgba = Expression.rgba(Float.valueOf(fArrColorToRgbaArray[0]), Float.valueOf(fArrColorToRgbaArray[1]), Float.valueOf(fArrColorToRgbaArray[2]), Float.valueOf(fArrColorToRgbaArray[3]));
        this.layer.setProperties(LocationPropertyFactory.accuracyRadiusColor(expressionRgba), LocationPropertyFactory.accuracyRadiusBorderColor(expressionRgba));
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void setLatLng(LatLng latLng) {
        setLayerLocation(latLng);
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void setGpsBearing(Float f) {
        setLayerBearing(f.floatValue());
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void setCompassBearing(Float f) {
        setLayerBearing(f.floatValue());
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void setAccuracyRadius(Float f) {
        this.layer.setProperties(LocationPropertyFactory.accuracyRadius(f));
        this.lastAccuracy = f.floatValue();
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void styleScaling(Expression expression) {
        this.layer.setProperties(LocationPropertyFactory.shadowImageSize(expression), LocationPropertyFactory.bearingImageSize(expression), LocationPropertyFactory.topImageSize(expression));
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void setLocationStale(boolean z, int i) {
        setImages(i, z);
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void addBitmaps(int i, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6) {
        if (bitmap != null) {
            this.style.addImage("mapbox-location-shadow-icon", bitmap);
        } else {
            this.style.removeImage("mapbox-location-shadow-icon");
        }
        this.style.addImage("mapbox-location-icon", bitmap5);
        this.style.addImage("mapbox-location-stale-icon", bitmap6);
        if (i == 4) {
            this.style.addImage("mapbox-location-bearing-icon", BitmapUtils.mergeBitmap(bitmap4, bitmap2, (bitmap4.getWidth() - bitmap2.getWidth()) / 2.0f, (bitmap4.getHeight() - bitmap2.getHeight()) / 2.0f));
            this.style.addImage("mapbox-location-bearing-stale-icon", BitmapUtils.mergeBitmap(bitmap4, bitmap3, (bitmap4.getWidth() - bitmap3.getWidth()) / 2.0f, (bitmap4.getHeight() - bitmap3.getHeight()) / 2.0f));
            return;
        }
        this.style.addImage("mapbox-location-stroke-icon", bitmap2);
        this.style.addImage("mapbox-location-background-stale-icon", bitmap3);
        this.style.addImage("mapbox-location-bearing-icon", bitmap4);
    }

    private void setLayerVisibility(boolean z) {
        Layer layer = this.layer;
        PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
        propertyValueArr[0] = LocationPropertyFactory.visibility(z ? "visible" : "none");
        layer.setProperties(propertyValueArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setLayerLocation(LatLng latLng) {
        this.layer.setProperties(LocationPropertyFactory.location(new Double[]{Double.valueOf(latLng.getLatitude()), Double.valueOf(latLng.getLongitude()), Double.valueOf(0.0d)}));
        this.lastLatLng = latLng;
    }

    private void setLayerBearing(double d) {
        this.layer.setProperties(LocationPropertyFactory.bearing(Double.valueOf(d)));
        this.lastBearing = d;
    }

    private void setImages(int i, boolean z) {
        String str;
        String str2;
        String str3 = "mapbox-location-shadow-icon";
        if (i != 4) {
            str = "";
            if (i == 8) {
                str2 = z ? "mapbox-location-stale-icon" : "mapbox-location-icon";
                str3 = z ? "mapbox-location-background-stale-icon" : "mapbox-location-stroke-icon";
                setAccuracyRadius(Float.valueOf(0.0f));
            } else if (i != 18) {
                str3 = "";
                str2 = str3;
            } else {
                str = z ? "mapbox-location-stale-icon" : "mapbox-location-icon";
                str2 = z ? "mapbox-location-background-stale-icon" : "mapbox-location-stroke-icon";
            }
        } else {
            str = z ? "mapbox-location-stale-icon" : "mapbox-location-icon";
            str2 = z ? "mapbox-location-bearing-stale-icon" : "mapbox-location-bearing-icon";
        }
        this.layer.setProperties(LocationPropertyFactory.topImage(str), LocationPropertyFactory.bearingImage(str2), LocationPropertyFactory.shadowImage(str3));
    }
}

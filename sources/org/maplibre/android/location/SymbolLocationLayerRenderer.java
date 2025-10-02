package org.maplibre.android.location;

import android.graphics.Bitmap;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Iterator;
import java.util.Set;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.Layer;
import org.maplibre.android.style.layers.PropertyFactory;
import org.maplibre.android.style.layers.PropertyValue;
import org.maplibre.android.style.layers.SymbolLayer;
import org.maplibre.android.style.sources.GeoJsonSource;
import org.maplibre.android.utils.ColorUtils;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.Point;

/* loaded from: classes2.dex */
final class SymbolLocationLayerRenderer implements LocationLayerRenderer {
    private final Set<String> layerSet;
    private final LayerSourceProvider layerSourceProvider;
    private Feature locationFeature;
    private GeoJsonSource locationSource;
    private Style style;

    SymbolLocationLayerRenderer(LayerSourceProvider layerSourceProvider, LayerFeatureProvider layerFeatureProvider, boolean z) {
        this.layerSourceProvider = layerSourceProvider;
        this.layerSet = layerSourceProvider.getEmptyLayerSet();
        this.locationFeature = layerFeatureProvider.generateLocationFeature(this.locationFeature, z);
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void initializeComponents(Style style) {
        this.style = style;
        addLocationSource();
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void addLayers(LocationComponentPositionManager locationComponentPositionManager) {
        Layer layerGenerateLayer = this.layerSourceProvider.generateLayer(LocationComponentConstants.BEARING_LAYER);
        locationComponentPositionManager.addLayerToMap(layerGenerateLayer);
        this.layerSet.add(layerGenerateLayer.getId());
        addSymbolLayer(LocationComponentConstants.FOREGROUND_LAYER, LocationComponentConstants.BEARING_LAYER);
        addSymbolLayer(LocationComponentConstants.BACKGROUND_LAYER, LocationComponentConstants.FOREGROUND_LAYER);
        addSymbolLayer(LocationComponentConstants.SHADOW_LAYER, LocationComponentConstants.BACKGROUND_LAYER);
        addAccuracyLayer();
        addPulsingCircleLayerToMap();
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void removeLayers() {
        Iterator<String> it = this.layerSet.iterator();
        while (it.hasNext()) {
            this.style.removeLayer(it.next());
        }
        this.layerSet.clear();
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void hide() {
        Iterator<String> it = this.layerSet.iterator();
        while (it.hasNext()) {
            setLayerVisibility(it.next(), false);
        }
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void cameraTiltUpdated(double d) {
        updateForegroundOffset(d);
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void cameraBearingUpdated(double d) {
        updateForegroundBearing((float) d);
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void show(int i, boolean z) {
        if (i == 4) {
            setLayerVisibility(LocationComponentConstants.SHADOW_LAYER, true);
            setLayerVisibility(LocationComponentConstants.FOREGROUND_LAYER, true);
            setLayerVisibility(LocationComponentConstants.BACKGROUND_LAYER, true);
            setLayerVisibility(LocationComponentConstants.ACCURACY_LAYER, !z);
            setLayerVisibility(LocationComponentConstants.BEARING_LAYER, true);
            return;
        }
        if (i == 8) {
            setLayerVisibility(LocationComponentConstants.SHADOW_LAYER, false);
            setLayerVisibility(LocationComponentConstants.FOREGROUND_LAYER, true);
            setLayerVisibility(LocationComponentConstants.BACKGROUND_LAYER, true);
            setLayerVisibility(LocationComponentConstants.ACCURACY_LAYER, false);
            setLayerVisibility(LocationComponentConstants.BEARING_LAYER, false);
            return;
        }
        if (i != 18) {
            return;
        }
        setLayerVisibility(LocationComponentConstants.SHADOW_LAYER, true);
        setLayerVisibility(LocationComponentConstants.FOREGROUND_LAYER, true);
        setLayerVisibility(LocationComponentConstants.BACKGROUND_LAYER, true);
        setLayerVisibility(LocationComponentConstants.ACCURACY_LAYER, !z);
        setLayerVisibility(LocationComponentConstants.BEARING_LAYER, false);
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void styleAccuracy(float f, int i) {
        this.locationFeature.addNumberProperty("mapbox-property-accuracy-alpha", Float.valueOf(f));
        this.locationFeature.addStringProperty("mapbox-property-accuracy-color", ColorUtils.colorToRgbaString(i));
        refreshSource();
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void setLatLng(LatLng latLng) {
        setLocationPoint(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()));
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void setGpsBearing(Float f) {
        setBearingProperty("mapbox-property-gps-bearing", f.floatValue());
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void setCompassBearing(Float f) {
        setBearingProperty("mapbox-property-compass-bearing", f.floatValue());
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void setAccuracyRadius(Float f) {
        updateAccuracyRadius(f.floatValue());
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void styleScaling(Expression expression) {
        Iterator<String> it = this.layerSet.iterator();
        while (it.hasNext()) {
            Layer layer = this.style.getLayer(it.next());
            if (layer instanceof SymbolLayer) {
                layer.setProperties(PropertyFactory.iconSize(expression));
            }
        }
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void setLocationStale(boolean z, int i) {
        this.locationFeature.addBooleanProperty("mapbox-property-location-stale", Boolean.valueOf(z));
        refreshSource();
        if (i != 8) {
            setLayerVisibility(LocationComponentConstants.ACCURACY_LAYER, !z);
        }
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void updateIconIds(String str, String str2, String str3, String str4, String str5) {
        this.locationFeature.addStringProperty("mapbox-property-foreground-icon", str);
        this.locationFeature.addStringProperty("mapbox-property-background-icon", str3);
        this.locationFeature.addStringProperty("mapbox-property-foreground-stale-icon", str2);
        this.locationFeature.addStringProperty("mapbox-property-background-stale-icon", str4);
        this.locationFeature.addStringProperty("mapbox-property-shadow-icon", str5);
        refreshSource();
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void addBitmaps(int i, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6) {
        if (bitmap != null) {
            this.style.addImage("mapbox-location-shadow-icon", bitmap);
        } else {
            this.style.removeImage("mapbox-location-shadow-icon");
        }
        this.style.addImage("mapbox-location-stroke-icon", bitmap2);
        this.style.addImage("mapbox-location-background-stale-icon", bitmap3);
        this.style.addImage("mapbox-location-bearing-icon", bitmap4);
        this.style.addImage("mapbox-location-icon", bitmap5);
        this.style.addImage("mapbox-location-stale-icon", bitmap6);
    }

    private void updateForegroundOffset(double d) {
        JsonArray jsonArray = new JsonArray();
        Float fValueOf = Float.valueOf(0.0f);
        jsonArray.add(fValueOf);
        jsonArray.add(Float.valueOf((float) ((-0.05d) * d)));
        this.locationFeature.addProperty("mapbox-property-foreground-icon-offset", jsonArray);
        JsonArray jsonArray2 = new JsonArray();
        jsonArray2.add(fValueOf);
        jsonArray2.add(Float.valueOf((float) (d * 0.05d)));
        this.locationFeature.addProperty("mapbox-property-shadow-icon-offset", jsonArray2);
        refreshSource();
    }

    private void updateForegroundBearing(float f) {
        setBearingProperty("mapbox-property-gps-bearing", f);
    }

    private void setLayerVisibility(String str, boolean z) {
        Layer layer = this.style.getLayer(str);
        if (layer != null) {
            if (layer.getVisibility().value.equals(z ? "visible" : "none")) {
                return;
            }
            PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
            propertyValueArr[0] = PropertyFactory.visibility(z ? "visible" : "none");
            layer.setProperties(propertyValueArr);
        }
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void adjustPulsingCircleLayerVisibility(boolean z) {
        setLayerVisibility(LocationComponentConstants.PULSING_CIRCLE_LAYER, z);
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void stylePulsingCircle(LocationComponentOptions locationComponentOptions) {
        if (this.style.getLayer(LocationComponentConstants.PULSING_CIRCLE_LAYER) != null) {
            setLayerVisibility(LocationComponentConstants.PULSING_CIRCLE_LAYER, true);
            this.style.getLayer(LocationComponentConstants.PULSING_CIRCLE_LAYER).setProperties(PropertyFactory.circleRadius(Expression.get("mapbox-property-pulsing-circle-radius")), PropertyFactory.circleColor(locationComponentOptions.pulseColor().intValue()), PropertyFactory.circleStrokeColor(locationComponentOptions.pulseColor().intValue()), PropertyFactory.circleOpacity(Expression.get("mapbox-property-pulsing-circle-opacity")));
        }
    }

    @Override // org.maplibre.android.location.LocationLayerRenderer
    public void updatePulsingUi(float f, Float f2) {
        this.locationFeature.addNumberProperty("mapbox-property-pulsing-circle-radius", Float.valueOf(f));
        if (f2 != null) {
            this.locationFeature.addNumberProperty("mapbox-property-pulsing-circle-opacity", f2);
        }
        refreshSource();
    }

    private void addSymbolLayer(String str, String str2) {
        addLayerToMap(this.layerSourceProvider.generateLayer(str), str2);
    }

    private void addAccuracyLayer() {
        addLayerToMap(this.layerSourceProvider.generateAccuracyLayer(), LocationComponentConstants.BACKGROUND_LAYER);
    }

    private void addPulsingCircleLayerToMap() {
        addLayerToMap(this.layerSourceProvider.generatePulsingCircleLayer(), LocationComponentConstants.ACCURACY_LAYER);
    }

    private void addLayerToMap(Layer layer, String str) {
        this.style.addLayerBelow(layer, str);
        this.layerSet.add(layer.getId());
    }

    private void addLocationSource() {
        GeoJsonSource geoJsonSourceGenerateSource = this.layerSourceProvider.generateSource(this.locationFeature);
        this.locationSource = geoJsonSourceGenerateSource;
        this.style.addSource(geoJsonSourceGenerateSource);
    }

    private void refreshSource() {
        if (((GeoJsonSource) this.style.getSourceAs(LocationComponentConstants.LOCATION_SOURCE)) != null) {
            this.locationSource.setGeoJson(this.locationFeature);
        }
    }

    private void setLocationPoint(Point point) {
        JsonObject jsonObjectProperties = this.locationFeature.properties();
        if (jsonObjectProperties != null) {
            this.locationFeature = Feature.fromGeometry(point, jsonObjectProperties);
            refreshSource();
        }
    }

    private void setBearingProperty(String str, float f) {
        this.locationFeature.addNumberProperty(str, Float.valueOf(f));
        refreshSource();
    }

    private void updateAccuracyRadius(float f) {
        this.locationFeature.addNumberProperty("mapbox-property-accuracy-radius", Float.valueOf(f));
        refreshSource();
    }
}

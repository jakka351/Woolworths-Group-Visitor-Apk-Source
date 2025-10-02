package org.maplibre.android.location;

import android.graphics.Bitmap;
import java.util.HashSet;
import java.util.Set;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.location.MapLibreAnimator;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;

/* loaded from: classes2.dex */
final class LocationLayerController {
    private static final String TAG = "Mbgl-LocationLayerController";
    private final LayerBitmapProvider bitmapProvider;
    private final OnRenderModeChangedListener internalRenderModeChangedListener;
    private boolean isStale;
    private LocationLayerRenderer locationLayerRenderer;
    private final MapLibreMap maplibreMap;
    private LocationComponentOptions options;
    private LocationComponentPositionManager positionManager;
    private int renderMode;
    private final boolean useSpecializedLocationLayer;
    private boolean isHidden = true;
    private final MapLibreAnimator.AnimationsValueChangeListener<LatLng> latLngValueListener = new MapLibreAnimator.AnimationsValueChangeListener<LatLng>() { // from class: org.maplibre.android.location.LocationLayerController.1
        @Override // org.maplibre.android.location.MapLibreAnimator.AnimationsValueChangeListener
        public void onNewAnimationValue(LatLng latLng) {
            LocationLayerController.this.locationLayerRenderer.setLatLng(latLng);
        }
    };
    private final MapLibreAnimator.AnimationsValueChangeListener<Float> gpsBearingValueListener = new MapLibreAnimator.AnimationsValueChangeListener<Float>() { // from class: org.maplibre.android.location.LocationLayerController.2
        @Override // org.maplibre.android.location.MapLibreAnimator.AnimationsValueChangeListener
        public void onNewAnimationValue(Float f) {
            LocationLayerController.this.locationLayerRenderer.setGpsBearing(f);
        }
    };
    private final MapLibreAnimator.AnimationsValueChangeListener<Float> compassBearingValueListener = new MapLibreAnimator.AnimationsValueChangeListener<Float>() { // from class: org.maplibre.android.location.LocationLayerController.3
        @Override // org.maplibre.android.location.MapLibreAnimator.AnimationsValueChangeListener
        public void onNewAnimationValue(Float f) {
            LocationLayerController.this.locationLayerRenderer.setCompassBearing(f);
        }
    };
    private final MapLibreAnimator.AnimationsValueChangeListener<Float> accuracyValueListener = new MapLibreAnimator.AnimationsValueChangeListener<Float>() { // from class: org.maplibre.android.location.LocationLayerController.4
        @Override // org.maplibre.android.location.MapLibreAnimator.AnimationsValueChangeListener
        public void onNewAnimationValue(Float f) {
            LocationLayerController.this.locationLayerRenderer.setAccuracyRadius(f);
        }
    };
    private final MapLibreAnimator.AnimationsValueChangeListener<Float> pulsingCircleRadiusListener = new MapLibreAnimator.AnimationsValueChangeListener<Float>() { // from class: org.maplibre.android.location.LocationLayerController.5
        @Override // org.maplibre.android.location.MapLibreAnimator.AnimationsValueChangeListener
        public void onNewAnimationValue(Float f) {
            LocationLayerController.this.locationLayerRenderer.updatePulsingUi(f.floatValue(), LocationLayerController.this.options.pulseFadeEnabled().booleanValue() ? Float.valueOf(1.0f - ((f.floatValue() / 100.0f) * 3.0f)) : null);
        }
    };

    LocationLayerController(MapLibreMap mapLibreMap, Style style, LayerSourceProvider layerSourceProvider, LayerFeatureProvider layerFeatureProvider, LayerBitmapProvider layerBitmapProvider, LocationComponentOptions locationComponentOptions, OnRenderModeChangedListener onRenderModeChangedListener, boolean z) {
        this.maplibreMap = mapLibreMap;
        this.bitmapProvider = layerBitmapProvider;
        this.internalRenderModeChangedListener = onRenderModeChangedListener;
        this.useSpecializedLocationLayer = z;
        boolean zEnableStaleState = locationComponentOptions.enableStaleState();
        this.isStale = zEnableStaleState;
        if (z) {
            this.locationLayerRenderer = layerSourceProvider.getIndicatorLocationLayerRenderer();
        } else {
            this.locationLayerRenderer = layerSourceProvider.getSymbolLocationLayerRenderer(layerFeatureProvider, zEnableStaleState);
        }
        initializeComponents(style, locationComponentOptions);
    }

    void initializeComponents(Style style, LocationComponentOptions locationComponentOptions) {
        this.positionManager = new LocationComponentPositionManager(style, locationComponentOptions.layerAbove(), locationComponentOptions.layerBelow());
        this.locationLayerRenderer.initializeComponents(style);
        this.locationLayerRenderer.addLayers(this.positionManager);
        applyStyle(locationComponentOptions);
        if (this.isHidden) {
            hide();
        } else {
            show();
        }
    }

    void applyStyle(LocationComponentOptions locationComponentOptions) {
        if (this.positionManager.update(locationComponentOptions.layerAbove(), locationComponentOptions.layerBelow())) {
            this.locationLayerRenderer.removeLayers();
            this.locationLayerRenderer.addLayers(this.positionManager);
            if (this.isHidden) {
                hide();
            }
        }
        this.options = locationComponentOptions;
        styleBitmaps(locationComponentOptions);
        this.locationLayerRenderer.styleAccuracy(locationComponentOptions.accuracyAlpha(), locationComponentOptions.accuracyColor());
        styleScaling(locationComponentOptions);
        this.locationLayerRenderer.stylePulsingCircle(locationComponentOptions);
        determineIconsSource(locationComponentOptions);
        if (this.isHidden) {
            return;
        }
        show();
    }

    void setGpsBearing(float f) {
        this.locationLayerRenderer.setGpsBearing(Float.valueOf(f));
    }

    void setRenderMode(int i) {
        if (this.renderMode == i) {
            return;
        }
        this.renderMode = i;
        styleBitmaps(this.options);
        determineIconsSource(this.options);
        if (!this.isHidden) {
            show();
        }
        this.internalRenderModeChangedListener.onRenderModeChanged(i);
    }

    int getRenderMode() {
        return this.renderMode;
    }

    void show() {
        this.isHidden = false;
        this.locationLayerRenderer.show(this.renderMode, this.isStale);
    }

    void hide() {
        this.isHidden = true;
        this.locationLayerRenderer.hide();
    }

    boolean isHidden() {
        return this.isHidden;
    }

    boolean isConsumingCompass() {
        return this.renderMode == 4;
    }

    private void styleBitmaps(LocationComponentOptions locationComponentOptions) {
        Bitmap bitmap;
        Bitmap bitmapGenerateBitmap;
        Bitmap bitmapGenerateShadowBitmap = locationComponentOptions.elevation() > 0.0f ? this.bitmapProvider.generateShadowBitmap(locationComponentOptions) : null;
        Bitmap bitmapGenerateBitmap2 = this.bitmapProvider.generateBitmap(locationComponentOptions.backgroundDrawable(), locationComponentOptions.backgroundTintColor());
        Bitmap bitmapGenerateBitmap3 = this.bitmapProvider.generateBitmap(locationComponentOptions.backgroundDrawableStale(), locationComponentOptions.backgroundStaleTintColor());
        Bitmap bitmapGenerateBitmap4 = this.bitmapProvider.generateBitmap(locationComponentOptions.bearingDrawable(), locationComponentOptions.bearingTintColor());
        Bitmap bitmapGenerateBitmap5 = this.bitmapProvider.generateBitmap(locationComponentOptions.foregroundDrawable(), locationComponentOptions.foregroundTintColor());
        Bitmap bitmapGenerateBitmap6 = this.bitmapProvider.generateBitmap(locationComponentOptions.foregroundDrawableStale(), locationComponentOptions.foregroundStaleTintColor());
        if (this.renderMode == 8) {
            Bitmap bitmapGenerateBitmap7 = this.bitmapProvider.generateBitmap(locationComponentOptions.gpsDrawable(), locationComponentOptions.foregroundTintColor());
            bitmapGenerateBitmap = this.bitmapProvider.generateBitmap(locationComponentOptions.gpsDrawable(), locationComponentOptions.foregroundStaleTintColor());
            bitmap = bitmapGenerateBitmap7;
        } else {
            bitmap = bitmapGenerateBitmap5;
            bitmapGenerateBitmap = bitmapGenerateBitmap6;
        }
        this.locationLayerRenderer.addBitmaps(this.renderMode, bitmapGenerateShadowBitmap, bitmapGenerateBitmap2, bitmapGenerateBitmap3, bitmapGenerateBitmap4, bitmap, bitmapGenerateBitmap);
    }

    private void styleScaling(LocationComponentOptions locationComponentOptions) {
        this.locationLayerRenderer.styleScaling(Expression.interpolate(Expression.linear(), Expression.zoom(), Expression.stop(Double.valueOf(this.maplibreMap.getMinZoomLevel()), Float.valueOf(locationComponentOptions.minZoomIconScale())), Expression.stop(Double.valueOf(this.maplibreMap.getMaxZoomLevel()), Float.valueOf(locationComponentOptions.maxZoomIconScale()))));
    }

    private void determineIconsSource(LocationComponentOptions locationComponentOptions) {
        this.locationLayerRenderer.updateIconIds(buildIconString(this.renderMode == 8 ? locationComponentOptions.gpsName() : locationComponentOptions.foregroundName(), "mapbox-location-icon"), buildIconString(locationComponentOptions.foregroundStaleName(), "mapbox-location-stale-icon"), buildIconString(locationComponentOptions.backgroundName(), "mapbox-location-stroke-icon"), buildIconString(locationComponentOptions.backgroundStaleName(), "mapbox-location-background-stale-icon"), buildIconString(locationComponentOptions.bearingName(), "mapbox-location-bearing-icon"));
    }

    private String buildIconString(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (!this.useSpecializedLocationLayer) {
            return str;
        }
        Logger.e(TAG, str + " replacement ID provided for an unsupported specialized location layer");
        return str2;
    }

    void setLocationsStale(boolean z) {
        this.isStale = z;
        this.locationLayerRenderer.setLocationStale(z, this.renderMode);
    }

    boolean onMapClick(LatLng latLng) {
        return !this.maplibreMap.queryRenderedFeatures(this.maplibreMap.getProjection().toScreenLocation(latLng), LocationComponentConstants.BACKGROUND_LAYER, LocationComponentConstants.FOREGROUND_LAYER, LocationComponentConstants.BEARING_LAYER).isEmpty();
    }

    Set<AnimatorListenerHolder> getAnimationListeners() {
        HashSet hashSet = new HashSet();
        hashSet.add(new AnimatorListenerHolder(0, this.latLngValueListener));
        int i = this.renderMode;
        if (i == 8) {
            hashSet.add(new AnimatorListenerHolder(2, this.gpsBearingValueListener));
        } else if (i == 4) {
            hashSet.add(new AnimatorListenerHolder(3, this.compassBearingValueListener));
        }
        int i2 = this.renderMode;
        if (i2 == 4 || i2 == 18) {
            hashSet.add(new AnimatorListenerHolder(6, this.accuracyValueListener));
        }
        if (this.options.pulseEnabled().booleanValue()) {
            hashSet.add(new AnimatorListenerHolder(9, this.pulsingCircleRadiusListener));
        }
        return hashSet;
    }

    void cameraBearingUpdated(double d) {
        if (this.renderMode != 8) {
            this.locationLayerRenderer.cameraBearingUpdated(d);
        }
    }

    void cameraTiltUpdated(double d) {
        this.locationLayerRenderer.cameraTiltUpdated(d);
    }

    void adjustPulsingCircleLayerVisibility(boolean z) {
        this.locationLayerRenderer.adjustPulsingCircleLayerVisibility(z);
    }
}

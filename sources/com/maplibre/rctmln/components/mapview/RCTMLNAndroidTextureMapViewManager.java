package com.maplibre.rctmln.components.mapview;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import org.maplibre.android.maps.MapLibreMapOptions;

/* loaded from: classes3.dex */
public class RCTMLNAndroidTextureMapViewManager extends RCTMLNMapViewManager {
    public static final String LOG_TAG = "RCTMLNAndroidTextureMapViewManager";
    public static final String REACT_CLASS = "RCTMLNAndroidTextureMapView";

    @Override // com.maplibre.rctmln.components.mapview.RCTMLNMapViewManager, com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RCTMLNAndroidTextureMapView";
    }

    public RCTMLNAndroidTextureMapViewManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.maplibre.rctmln.components.mapview.RCTMLNMapViewManager, com.facebook.react.uimanager.ViewManager
    public RCTMLNAndroidTextureMapView createViewInstance(ThemedReactContext themedReactContext) {
        MapLibreMapOptions mapLibreMapOptions = new MapLibreMapOptions();
        mapLibreMapOptions.textureMode(true);
        return new RCTMLNAndroidTextureMapView(themedReactContext, this, mapLibreMapOptions);
    }
}

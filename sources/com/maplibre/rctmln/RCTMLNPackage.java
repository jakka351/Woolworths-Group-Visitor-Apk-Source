package com.maplibre.rctmln;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.maplibre.rctmln.components.annotation.RCTMLNCalloutManager;
import com.maplibre.rctmln.components.annotation.RCTMLNMarkerViewManager;
import com.maplibre.rctmln.components.annotation.RCTMLNPointAnnotationManager;
import com.maplibre.rctmln.components.camera.RCTMLNCameraManager;
import com.maplibre.rctmln.components.images.RCTMLNImagesManager;
import com.maplibre.rctmln.components.location.RCTMLNNativeUserLocationManager;
import com.maplibre.rctmln.components.mapview.RCTMLNAndroidTextureMapViewManager;
import com.maplibre.rctmln.components.mapview.RCTMLNMapViewManager;
import com.maplibre.rctmln.components.styles.layers.RCTMLNBackgroundLayerManager;
import com.maplibre.rctmln.components.styles.layers.RCTMLNCircleLayerManager;
import com.maplibre.rctmln.components.styles.layers.RCTMLNFillExtrusionLayerManager;
import com.maplibre.rctmln.components.styles.layers.RCTMLNFillLayerManager;
import com.maplibre.rctmln.components.styles.layers.RCTMLNHeatmapLayerManager;
import com.maplibre.rctmln.components.styles.layers.RCTMLNLineLayerManager;
import com.maplibre.rctmln.components.styles.layers.RCTMLNRasterLayerManager;
import com.maplibre.rctmln.components.styles.layers.RCTMLNSymbolLayerManager;
import com.maplibre.rctmln.components.styles.light.RCTMLNLightManager;
import com.maplibre.rctmln.components.styles.sources.RCTMLNImageSourceManager;
import com.maplibre.rctmln.components.styles.sources.RCTMLNRasterSourceManager;
import com.maplibre.rctmln.components.styles.sources.RCTMLNShapeSourceManager;
import com.maplibre.rctmln.components.styles.sources.RCTMLNVectorSourceManager;
import com.maplibre.rctmln.modules.RCTMLNLocationModule;
import com.maplibre.rctmln.modules.RCTMLNLogging;
import com.maplibre.rctmln.modules.RCTMLNModule;
import com.maplibre.rctmln.modules.RCTMLNOfflineModule;
import com.maplibre.rctmln.modules.RCTMLNSnapshotModule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class RCTMLNPackage implements ReactPackage {
    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RCTMLNModule(reactApplicationContext));
        arrayList.add(new RCTMLNOfflineModule(reactApplicationContext));
        arrayList.add(new RCTMLNSnapshotModule(reactApplicationContext));
        arrayList.add(new RCTMLNLocationModule(reactApplicationContext));
        arrayList.add(new RCTMLNLogging(reactApplicationContext));
        return arrayList;
    }

    @Deprecated
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RCTMLNCameraManager(reactApplicationContext));
        arrayList.add(new RCTMLNMapViewManager(reactApplicationContext));
        arrayList.add(new RCTMLNMarkerViewManager(reactApplicationContext));
        arrayList.add(new RCTMLNAndroidTextureMapViewManager(reactApplicationContext));
        arrayList.add(new RCTMLNLightManager());
        arrayList.add(new RCTMLNPointAnnotationManager(reactApplicationContext));
        arrayList.add(new RCTMLNCalloutManager());
        arrayList.add(new RCTMLNNativeUserLocationManager());
        arrayList.add(new RCTMLNVectorSourceManager(reactApplicationContext));
        arrayList.add(new RCTMLNShapeSourceManager(reactApplicationContext));
        arrayList.add(new RCTMLNRasterSourceManager(reactApplicationContext));
        arrayList.add(new RCTMLNImageSourceManager());
        arrayList.add(new RCTMLNImagesManager(reactApplicationContext));
        arrayList.add(new RCTMLNFillLayerManager());
        arrayList.add(new RCTMLNFillExtrusionLayerManager());
        arrayList.add(new RCTMLNHeatmapLayerManager());
        arrayList.add(new RCTMLNLineLayerManager());
        arrayList.add(new RCTMLNCircleLayerManager());
        arrayList.add(new RCTMLNSymbolLayerManager());
        arrayList.add(new RCTMLNRasterLayerManager());
        arrayList.add(new RCTMLNBackgroundLayerManager());
        return arrayList;
    }
}

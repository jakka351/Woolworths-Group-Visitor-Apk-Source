package com.maplibre.rctmln.components.styles.layers;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes3.dex */
public class RCTMLNRasterLayerManager extends ViewGroupManager<RCTMLNRasterLayer> {
    public static final String REACT_CLASS = "RCTMLNRasterLayer";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNRasterLayer createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNRasterLayer(themedReactContext);
    }

    @ReactProp(name = "id")
    public void setId(RCTMLNRasterLayer rCTMLNRasterLayer, String str) {
        rCTMLNRasterLayer.setID(str);
    }

    @ReactProp(name = "sourceID")
    public void setSourceID(RCTMLNRasterLayer rCTMLNRasterLayer, String str) {
        rCTMLNRasterLayer.setSourceID(str);
    }

    @ReactProp(name = "aboveLayerID")
    public void setAboveLayerID(RCTMLNRasterLayer rCTMLNRasterLayer, String str) {
        rCTMLNRasterLayer.setAboveLayerID(str);
    }

    @ReactProp(name = "belowLayerID")
    public void setBelowLayerID(RCTMLNRasterLayer rCTMLNRasterLayer, String str) {
        rCTMLNRasterLayer.setBelowLayerID(str);
    }

    @ReactProp(name = "layerIndex")
    public void setLayerIndex(RCTMLNRasterLayer rCTMLNRasterLayer, int i) {
        rCTMLNRasterLayer.setLayerIndex(i);
    }

    @ReactProp(name = "minZoomLevel")
    public void setMinZoomLevel(RCTMLNRasterLayer rCTMLNRasterLayer, double d) {
        rCTMLNRasterLayer.setMinZoomLevel(d);
    }

    @ReactProp(name = "maxZoomLevel")
    public void setMaxZoomLevel(RCTMLNRasterLayer rCTMLNRasterLayer, double d) {
        rCTMLNRasterLayer.setMaxZoomLevel(d);
    }

    @ReactProp(name = "reactStyle")
    public void setReactStyle(RCTMLNRasterLayer rCTMLNRasterLayer, ReadableMap readableMap) {
        rCTMLNRasterLayer.setReactStyle(readableMap);
    }
}

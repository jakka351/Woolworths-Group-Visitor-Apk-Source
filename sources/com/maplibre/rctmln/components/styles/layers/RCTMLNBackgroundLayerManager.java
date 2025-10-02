package com.maplibre.rctmln.components.styles.layers;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes3.dex */
public class RCTMLNBackgroundLayerManager extends ViewGroupManager<RCTMLNBackgroundLayer> {
    public static final String REACT_CLASS = "RCTMLNBackgroundLayer";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNBackgroundLayer createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNBackgroundLayer(themedReactContext);
    }

    @ReactProp(name = "id")
    public void setId(RCTMLNBackgroundLayer rCTMLNBackgroundLayer, String str) {
        rCTMLNBackgroundLayer.setID(str);
    }

    @ReactProp(name = "sourceID")
    public void setSourceID(RCTMLNBackgroundLayer rCTMLNBackgroundLayer, String str) {
        rCTMLNBackgroundLayer.setSourceID(str);
    }

    @ReactProp(name = "aboveLayerID")
    public void setAboveLayerID(RCTMLNBackgroundLayer rCTMLNBackgroundLayer, String str) {
        rCTMLNBackgroundLayer.setAboveLayerID(str);
    }

    @ReactProp(name = "belowLayerID")
    public void setBelowLayerID(RCTMLNBackgroundLayer rCTMLNBackgroundLayer, String str) {
        rCTMLNBackgroundLayer.setBelowLayerID(str);
    }

    @ReactProp(name = "layerIndex")
    public void setLayerIndex(RCTMLNBackgroundLayer rCTMLNBackgroundLayer, int i) {
        rCTMLNBackgroundLayer.setLayerIndex(i);
    }

    @ReactProp(name = "minZoomLevel")
    public void setMinZoomLevel(RCTMLNBackgroundLayer rCTMLNBackgroundLayer, double d) {
        rCTMLNBackgroundLayer.setMinZoomLevel(d);
    }

    @ReactProp(name = "maxZoomLevel")
    public void setMaxZoomLevel(RCTMLNBackgroundLayer rCTMLNBackgroundLayer, double d) {
        rCTMLNBackgroundLayer.setMaxZoomLevel(d);
    }

    @ReactProp(name = "reactStyle")
    public void setReactStyle(RCTMLNBackgroundLayer rCTMLNBackgroundLayer, ReadableMap readableMap) {
        rCTMLNBackgroundLayer.setReactStyle(readableMap);
    }
}

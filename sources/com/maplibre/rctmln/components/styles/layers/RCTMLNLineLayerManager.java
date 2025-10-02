package com.maplibre.rctmln.components.styles.layers;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes3.dex */
public class RCTMLNLineLayerManager extends ViewGroupManager<RCTMLNLineLayer> {
    public static final String REACT_CLASS = "RCTMLNLineLayer";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNLineLayer createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNLineLayer(themedReactContext);
    }

    @ReactProp(name = "id")
    public void setId(RCTMLNLineLayer rCTMLNLineLayer, String str) {
        rCTMLNLineLayer.setID(str);
    }

    @ReactProp(name = "sourceID")
    public void setSourceID(RCTMLNLineLayer rCTMLNLineLayer, String str) {
        rCTMLNLineLayer.setSourceID(str);
    }

    @ReactProp(name = "aboveLayerID")
    public void setAboveLayerID(RCTMLNLineLayer rCTMLNLineLayer, String str) {
        rCTMLNLineLayer.setAboveLayerID(str);
    }

    @ReactProp(name = "belowLayerID")
    public void setBelowLayerID(RCTMLNLineLayer rCTMLNLineLayer, String str) {
        rCTMLNLineLayer.setBelowLayerID(str);
    }

    @ReactProp(name = "layerIndex")
    public void setLayerIndex(RCTMLNLineLayer rCTMLNLineLayer, int i) {
        rCTMLNLineLayer.setLayerIndex(i);
    }

    @ReactProp(name = "minZoomLevel")
    public void setMinZoomLevel(RCTMLNLineLayer rCTMLNLineLayer, double d) {
        rCTMLNLineLayer.setMinZoomLevel(d);
    }

    @ReactProp(name = "maxZoomLevel")
    public void setMaxZoomLevel(RCTMLNLineLayer rCTMLNLineLayer, double d) {
        rCTMLNLineLayer.setMaxZoomLevel(d);
    }

    @ReactProp(name = "reactStyle")
    public void setReactStyle(RCTMLNLineLayer rCTMLNLineLayer, ReadableMap readableMap) {
        rCTMLNLineLayer.setReactStyle(readableMap);
    }

    @ReactProp(name = "sourceLayerID")
    public void setSourceLayerId(RCTMLNLineLayer rCTMLNLineLayer, String str) {
        rCTMLNLineLayer.setSourceLayerID(str);
    }

    @ReactProp(name = "filter")
    public void setFilter(RCTMLNLineLayer rCTMLNLineLayer, ReadableArray readableArray) {
        rCTMLNLineLayer.setFilter(readableArray);
    }
}

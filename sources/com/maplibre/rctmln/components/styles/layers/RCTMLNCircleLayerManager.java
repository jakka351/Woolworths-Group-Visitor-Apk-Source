package com.maplibre.rctmln.components.styles.layers;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes3.dex */
public class RCTMLNCircleLayerManager extends ViewGroupManager<RCTMLNCircleLayer> {
    public static final String REACT_CLASS = "RCTMLNCircleLayer";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNCircleLayer createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNCircleLayer(themedReactContext);
    }

    @ReactProp(name = "id")
    public void setId(RCTMLNCircleLayer rCTMLNCircleLayer, String str) {
        rCTMLNCircleLayer.setID(str);
    }

    @ReactProp(name = "sourceID")
    public void setSourceID(RCTMLNCircleLayer rCTMLNCircleLayer, String str) {
        rCTMLNCircleLayer.setSourceID(str);
    }

    @ReactProp(name = "aboveLayerID")
    public void setAboveLayerID(RCTMLNCircleLayer rCTMLNCircleLayer, String str) {
        rCTMLNCircleLayer.setAboveLayerID(str);
    }

    @ReactProp(name = "belowLayerID")
    public void setBelowLayerID(RCTMLNCircleLayer rCTMLNCircleLayer, String str) {
        rCTMLNCircleLayer.setBelowLayerID(str);
    }

    @ReactProp(name = "layerIndex")
    public void setLayerIndex(RCTMLNCircleLayer rCTMLNCircleLayer, int i) {
        rCTMLNCircleLayer.setLayerIndex(i);
    }

    @ReactProp(name = "minZoomLevel")
    public void setMinZoomLevel(RCTMLNCircleLayer rCTMLNCircleLayer, double d) {
        rCTMLNCircleLayer.setMinZoomLevel(d);
    }

    @ReactProp(name = "maxZoomLevel")
    public void setMaxZoomLevel(RCTMLNCircleLayer rCTMLNCircleLayer, double d) {
        rCTMLNCircleLayer.setMaxZoomLevel(d);
    }

    @ReactProp(name = "reactStyle")
    public void setReactStyle(RCTMLNCircleLayer rCTMLNCircleLayer, ReadableMap readableMap) {
        rCTMLNCircleLayer.setReactStyle(readableMap);
    }

    @ReactProp(name = "sourceLayerID")
    public void setSourceLayerId(RCTMLNCircleLayer rCTMLNCircleLayer, String str) {
        rCTMLNCircleLayer.setSourceLayerID(str);
    }

    @ReactProp(name = "filter")
    public void setFilter(RCTMLNCircleLayer rCTMLNCircleLayer, ReadableArray readableArray) {
        rCTMLNCircleLayer.setFilter(readableArray);
    }
}

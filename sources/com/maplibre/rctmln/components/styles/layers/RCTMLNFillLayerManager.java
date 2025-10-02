package com.maplibre.rctmln.components.styles.layers;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes3.dex */
public class RCTMLNFillLayerManager extends ViewGroupManager<RCTMLNFillLayer> {
    public static final String REACT_CLASS = "RCTMLNFillLayer";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNFillLayer createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNFillLayer(themedReactContext);
    }

    @ReactProp(name = "id")
    public void setId(RCTMLNFillLayer rCTMLNFillLayer, String str) {
        rCTMLNFillLayer.setID(str);
    }

    @ReactProp(name = "sourceID")
    public void setSourceID(RCTMLNFillLayer rCTMLNFillLayer, String str) {
        rCTMLNFillLayer.setSourceID(str);
    }

    @ReactProp(name = "sourceLayerID")
    public void setSourceLayerId(RCTMLNFillLayer rCTMLNFillLayer, String str) {
        rCTMLNFillLayer.setSourceLayerID(str);
    }

    @ReactProp(name = "aboveLayerID")
    public void setAboveLayerID(RCTMLNFillLayer rCTMLNFillLayer, String str) {
        rCTMLNFillLayer.setAboveLayerID(str);
    }

    @ReactProp(name = "belowLayerID")
    public void setBelowLayerID(RCTMLNFillLayer rCTMLNFillLayer, String str) {
        rCTMLNFillLayer.setBelowLayerID(str);
    }

    @ReactProp(name = "layerIndex")
    public void setLayerIndex(RCTMLNFillLayer rCTMLNFillLayer, int i) {
        rCTMLNFillLayer.setLayerIndex(i);
    }

    @ReactProp(name = "minZoomLevel")
    public void setMinZoomLevel(RCTMLNFillLayer rCTMLNFillLayer, double d) {
        rCTMLNFillLayer.setMinZoomLevel(d);
    }

    @ReactProp(name = "maxZoomLevel")
    public void setMaxZoomLevel(RCTMLNFillLayer rCTMLNFillLayer, double d) {
        rCTMLNFillLayer.setMaxZoomLevel(d);
    }

    @ReactProp(name = "reactStyle")
    public void setReactStyle(RCTMLNFillLayer rCTMLNFillLayer, ReadableMap readableMap) {
        rCTMLNFillLayer.setReactStyle(readableMap);
    }

    @ReactProp(name = "filter")
    public void setFilter(RCTMLNFillLayer rCTMLNFillLayer, ReadableArray readableArray) {
        rCTMLNFillLayer.setFilter(readableArray);
    }
}

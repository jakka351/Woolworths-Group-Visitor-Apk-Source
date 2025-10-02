package com.maplibre.rctmln.components.styles.layers;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes3.dex */
public class RCTMLNFillExtrusionLayerManager extends ViewGroupManager<RCTMLNFillExtrusionLayer> {
    public static final String REACT_CLASS = "RCTMLNFillExtrusionLayer";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNFillExtrusionLayer createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNFillExtrusionLayer(themedReactContext);
    }

    @ReactProp(name = "id")
    public void setId(RCTMLNFillExtrusionLayer rCTMLNFillExtrusionLayer, String str) {
        rCTMLNFillExtrusionLayer.setID(str);
    }

    @ReactProp(name = "sourceID")
    public void setSourceID(RCTMLNFillExtrusionLayer rCTMLNFillExtrusionLayer, String str) {
        rCTMLNFillExtrusionLayer.setSourceID(str);
    }

    @ReactProp(name = "aboveLayerID")
    public void setAboveLayerID(RCTMLNFillExtrusionLayer rCTMLNFillExtrusionLayer, String str) {
        rCTMLNFillExtrusionLayer.setAboveLayerID(str);
    }

    @ReactProp(name = "belowLayerID")
    public void setBelowLayerID(RCTMLNFillExtrusionLayer rCTMLNFillExtrusionLayer, String str) {
        rCTMLNFillExtrusionLayer.setBelowLayerID(str);
    }

    @ReactProp(name = "layerIndex")
    public void setLayerIndex(RCTMLNFillExtrusionLayer rCTMLNFillExtrusionLayer, int i) {
        rCTMLNFillExtrusionLayer.setLayerIndex(i);
    }

    @ReactProp(name = "minZoomLevel")
    public void setMinZoomLevel(RCTMLNFillExtrusionLayer rCTMLNFillExtrusionLayer, double d) {
        rCTMLNFillExtrusionLayer.setMinZoomLevel(d);
    }

    @ReactProp(name = "maxZoomLevel")
    public void setMaxZoomLevel(RCTMLNFillExtrusionLayer rCTMLNFillExtrusionLayer, double d) {
        rCTMLNFillExtrusionLayer.setMaxZoomLevel(d);
    }

    @ReactProp(name = "reactStyle")
    public void setReactStyle(RCTMLNFillExtrusionLayer rCTMLNFillExtrusionLayer, ReadableMap readableMap) {
        rCTMLNFillExtrusionLayer.setReactStyle(readableMap);
    }

    @ReactProp(name = "sourceLayerID")
    public void setSourceLayerId(RCTMLNFillExtrusionLayer rCTMLNFillExtrusionLayer, String str) {
        rCTMLNFillExtrusionLayer.setSourceLayerID(str);
    }

    @ReactProp(name = "filter")
    public void setFilter(RCTMLNFillExtrusionLayer rCTMLNFillExtrusionLayer, ReadableArray readableArray) {
        rCTMLNFillExtrusionLayer.setFilter(readableArray);
    }
}

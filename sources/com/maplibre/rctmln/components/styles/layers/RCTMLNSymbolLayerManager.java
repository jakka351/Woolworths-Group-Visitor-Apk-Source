package com.maplibre.rctmln.components.styles.layers;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes3.dex */
public class RCTMLNSymbolLayerManager extends ViewGroupManager<RCTMLNSymbolLayer> {
    public static final String REACT_CLASS = "RCTMLNSymbolLayer";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNSymbolLayer createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNSymbolLayer(themedReactContext);
    }

    @ReactProp(name = "id")
    public void setId(RCTMLNSymbolLayer rCTMLNSymbolLayer, String str) {
        rCTMLNSymbolLayer.setID(str);
    }

    @ReactProp(name = "sourceID")
    public void setSourceID(RCTMLNSymbolLayer rCTMLNSymbolLayer, String str) {
        rCTMLNSymbolLayer.setSourceID(str);
    }

    @ReactProp(name = "aboveLayerID")
    public void setAboveLayerID(RCTMLNSymbolLayer rCTMLNSymbolLayer, String str) {
        rCTMLNSymbolLayer.setAboveLayerID(str);
    }

    @ReactProp(name = "belowLayerID")
    public void setBelowLayerID(RCTMLNSymbolLayer rCTMLNSymbolLayer, String str) {
        rCTMLNSymbolLayer.setBelowLayerID(str);
    }

    @ReactProp(name = "layerIndex")
    public void setLayerIndex(RCTMLNSymbolLayer rCTMLNSymbolLayer, int i) {
        rCTMLNSymbolLayer.setLayerIndex(i);
    }

    @ReactProp(name = "minZoomLevel")
    public void setMinZoomLevel(RCTMLNSymbolLayer rCTMLNSymbolLayer, double d) {
        rCTMLNSymbolLayer.setMinZoomLevel(d);
    }

    @ReactProp(name = "maxZoomLevel")
    public void setMaxZoomLevel(RCTMLNSymbolLayer rCTMLNSymbolLayer, double d) {
        rCTMLNSymbolLayer.setMaxZoomLevel(d);
    }

    @ReactProp(name = "reactStyle")
    public void setReactStyle(RCTMLNSymbolLayer rCTMLNSymbolLayer, ReadableMap readableMap) {
        rCTMLNSymbolLayer.setReactStyle(readableMap);
    }

    @ReactProp(name = "sourceLayerID")
    public void setSourceLayerId(RCTMLNSymbolLayer rCTMLNSymbolLayer, String str) {
        rCTMLNSymbolLayer.setSourceLayerID(str);
    }

    @ReactProp(name = "filter")
    public void setFilter(RCTMLNSymbolLayer rCTMLNSymbolLayer, ReadableArray readableArray) {
        rCTMLNSymbolLayer.setFilter(readableArray);
    }
}

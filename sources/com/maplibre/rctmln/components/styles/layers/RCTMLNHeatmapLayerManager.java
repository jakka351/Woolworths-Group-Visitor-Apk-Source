package com.maplibre.rctmln.components.styles.layers;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes3.dex */
public class RCTMLNHeatmapLayerManager extends ViewGroupManager<RCTMLNHeatmapLayer> {
    public static final String REACT_CLASS = "RCTMLNHeatmapLayer";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNHeatmapLayer createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNHeatmapLayer(themedReactContext);
    }

    @ReactProp(name = "id")
    public void setId(RCTMLNHeatmapLayer rCTMLNHeatmapLayer, String str) {
        rCTMLNHeatmapLayer.setID(str);
    }

    @ReactProp(name = "sourceID")
    public void setSourceID(RCTMLNHeatmapLayer rCTMLNHeatmapLayer, String str) {
        rCTMLNHeatmapLayer.setSourceID(str);
    }

    @ReactProp(name = "aboveLayerID")
    public void setAboveLayerID(RCTMLNHeatmapLayer rCTMLNHeatmapLayer, String str) {
        rCTMLNHeatmapLayer.setAboveLayerID(str);
    }

    @ReactProp(name = "belowLayerID")
    public void setBelowLayerID(RCTMLNHeatmapLayer rCTMLNHeatmapLayer, String str) {
        rCTMLNHeatmapLayer.setBelowLayerID(str);
    }

    @ReactProp(name = "layerIndex")
    public void setLayerIndex(RCTMLNHeatmapLayer rCTMLNHeatmapLayer, int i) {
        rCTMLNHeatmapLayer.setLayerIndex(i);
    }

    @ReactProp(name = "minZoomLevel")
    public void setMinZoomLevel(RCTMLNHeatmapLayer rCTMLNHeatmapLayer, double d) {
        rCTMLNHeatmapLayer.setMinZoomLevel(d);
    }

    @ReactProp(name = "maxZoomLevel")
    public void setMaxZoomLevel(RCTMLNHeatmapLayer rCTMLNHeatmapLayer, double d) {
        rCTMLNHeatmapLayer.setMaxZoomLevel(d);
    }

    @ReactProp(name = "reactStyle")
    public void setReactStyle(RCTMLNHeatmapLayer rCTMLNHeatmapLayer, ReadableMap readableMap) {
        rCTMLNHeatmapLayer.setReactStyle(readableMap);
    }

    @ReactProp(name = "sourceLayerID")
    public void setSourceLayerId(RCTMLNHeatmapLayer rCTMLNHeatmapLayer, String str) {
        rCTMLNHeatmapLayer.setSourceLayerID(str);
    }

    @ReactProp(name = "filter")
    public void setFilter(RCTMLNHeatmapLayer rCTMLNHeatmapLayer, ReadableArray readableArray) {
        rCTMLNHeatmapLayer.setFilter(readableArray);
    }
}

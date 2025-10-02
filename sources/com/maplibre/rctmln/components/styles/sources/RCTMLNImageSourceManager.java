package com.maplibre.rctmln.components.styles.sources;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import org.maplibre.android.geometry.LatLngQuad;

/* loaded from: classes3.dex */
public class RCTMLNImageSourceManager extends ViewGroupManager<RCTMLNImageSource> {
    public static final String REACT_CLASS = "RCTMLNImageSource";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RCTMLNImageSource";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNImageSource createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNImageSource(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public View getChildAt(RCTMLNImageSource rCTMLNImageSource, int i) {
        return rCTMLNImageSource.getLayerAt(i);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public int getChildCount(RCTMLNImageSource rCTMLNImageSource) {
        return rCTMLNImageSource.getLayerCount();
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(RCTMLNImageSource rCTMLNImageSource, View view, int i) {
        rCTMLNImageSource.addLayer(view, i);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(RCTMLNImageSource rCTMLNImageSource, int i) {
        rCTMLNImageSource.removeLayer(i);
    }

    @ReactProp(name = "id")
    public void setId(RCTMLNImageSource rCTMLNImageSource, String str) {
        rCTMLNImageSource.setID(str);
    }

    @ReactProp(name = "url")
    public void setUrl(RCTMLNImageSource rCTMLNImageSource, String str) {
        rCTMLNImageSource.setURL(str);
    }

    @ReactProp(name = "coordinates")
    public void setCoordinates(RCTMLNImageSource rCTMLNImageSource, ReadableArray readableArray) {
        LatLngQuad latLngQuad = GeoJSONUtils.toLatLngQuad(readableArray);
        if (latLngQuad == null) {
            return;
        }
        rCTMLNImageSource.setCoordinates(latLngQuad);
    }
}

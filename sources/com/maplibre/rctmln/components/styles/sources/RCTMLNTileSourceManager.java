package com.maplibre.rctmln.components.styles.sources;

import android.view.View;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.maplibre.rctmln.components.AbstractEventEmitter;
import com.maplibre.rctmln.components.styles.sources.RCTMLNTileSource;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public abstract class RCTMLNTileSourceManager<T extends RCTMLNTileSource> extends AbstractEventEmitter<T> {
    RCTMLNTileSourceManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public View getChildAt(T t, int i) {
        return t.getLayerAt(i);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public int getChildCount(T t) {
        return t.getLayerCount();
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(T t, View view, int i) {
        t.addLayer(view, i);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(T t, int i) {
        t.removeLayer(i);
    }

    @ReactProp(name = "id")
    public void setID(T t, String str) {
        t.setID(str);
    }

    @ReactProp(name = "url")
    public void setURL(T t, String str) {
        t.setURL(str);
    }

    @ReactProp(name = "tileUrlTemplates")
    public void setTileUrlTemplates(T t, ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            if (readableArray.getType(0) == ReadableType.String) {
                arrayList.add(readableArray.getString(i));
            }
        }
        t.setTileUrlTemplates(arrayList);
    }

    @ReactProp(name = "attribution")
    public void setAttribution(T t, String str) {
        t.setAttribution(str);
    }

    @ReactProp(name = "minZoomLevel")
    public void setMinZoomLevel(T t, int i) {
        t.setMinZoomLevel(Integer.valueOf(i));
    }

    @ReactProp(name = "maxZoomLevel")
    public void setMaxZoomLevel(T t, int i) {
        t.setMaxZoomLevel(Integer.valueOf(i));
    }

    @ReactProp(name = "tms")
    public void setTMS(T t, boolean z) {
        t.setTMS(z);
    }
}

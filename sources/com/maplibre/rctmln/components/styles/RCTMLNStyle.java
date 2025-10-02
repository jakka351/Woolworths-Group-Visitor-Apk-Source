package com.maplibre.rctmln.components.styles;

import android.content.Context;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.maplibre.rctmln.utils.DownloadMapImageTask;
import com.maplibre.rctmln.utils.ImageEntry;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import org.maplibre.android.maps.MapLibreMap;

/* loaded from: classes3.dex */
public class RCTMLNStyle {
    private Context mContext;
    private MapLibreMap mMap;
    private ReadableMap mReactStyle;

    public RCTMLNStyle(Context context, ReadableMap readableMap, MapLibreMap mapLibreMap) {
        this.mContext = context;
        this.mReactStyle = readableMap;
        this.mMap = mapLibreMap;
    }

    public List<String> getAllStyleKeys() {
        ReadableMap readableMap = this.mReactStyle;
        if (readableMap == null) {
            return new ArrayList();
        }
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        ArrayList arrayList = new ArrayList();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            if (!strNextKey.equals("__MAPBOX_STYLESHEET__")) {
                arrayList.add(strNextKey);
            }
        }
        return arrayList;
    }

    public RCTMLNStyleValue getStyleValueForKey(String str) {
        ReadableMap map = this.mReactStyle.getMap(str);
        if (map == null) {
            return null;
        }
        return new RCTMLNStyleValue(map);
    }

    public void addImage(RCTMLNStyleValue rCTMLNStyleValue) {
        addImage(rCTMLNStyleValue, null);
    }

    public ImageEntry imageEntry(RCTMLNStyleValue rCTMLNStyleValue) {
        return new ImageEntry(rCTMLNStyleValue.getImageURI(), Double.valueOf(rCTMLNStyleValue.getImageScale()));
    }

    public void addImage(RCTMLNStyleValue rCTMLNStyleValue, DownloadMapImageTask.OnAllImagesLoaded onAllImagesLoaded) {
        if (rCTMLNStyleValue.shouldAddImage()) {
            new DownloadMapImageTask(this.mContext, this.mMap, onAllImagesLoaded).execute(new AbstractMap.SimpleEntry(rCTMLNStyleValue.getImageURI(), imageEntry(rCTMLNStyleValue)));
        } else if (onAllImagesLoaded != null) {
            onAllImagesLoaded.onAllImagesLoaded();
        }
    }
}

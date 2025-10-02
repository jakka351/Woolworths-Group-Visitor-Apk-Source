package com.maplibre.rctmln.components.images;

import android.graphics.drawable.BitmapDrawable;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.maplibre.rctmln.components.AbstractEventEmitter;
import com.maplibre.rctmln.events.constants.EventKeys;
import com.maplibre.rctmln.utils.ImageEntry;
import com.maplibre.rctmln.utils.ResourceUtils;
import io.sentry.protocol.DebugMeta;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes3.dex */
public class RCTMLNImagesManager extends AbstractEventEmitter<RCTMLNImages> {
    public static final String REACT_CLASS = "RCTMLNImages";
    private ReactApplicationContext mContext;

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public RCTMLNImagesManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mContext = reactApplicationContext;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNImages createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNImages(themedReactContext, this);
    }

    @ReactProp(name = "id")
    public void setId(RCTMLNImages rCTMLNImages, String str) {
        rCTMLNImages.setID(str);
    }

    @ReactProp(name = DebugMeta.JsonKeys.IMAGES)
    public void setImages(RCTMLNImages rCTMLNImages, ReadableMap readableMap) {
        ImageEntry imageEntry;
        ArrayList arrayList = new ArrayList();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            if (readableMap.getType(strNextKey) == ReadableType.Map) {
                ReadableMap map = readableMap.getMap(strNextKey);
                imageEntry = new ImageEntry(map.getString(ReactNativeBlobUtilConst.DATA_ENCODE_URI), Double.valueOf(map.hasKey("scale") && map.getType("scale") == ReadableType.Number ? map.getDouble("scale") : 0.0d));
            } else {
                imageEntry = new ImageEntry(readableMap.getString(strNextKey));
            }
            arrayList.add(new AbstractMap.SimpleEntry(strNextKey, imageEntry));
        }
        rCTMLNImages.setImages(arrayList);
    }

    @ReactProp(name = "hasOnImageMissing")
    public void setHasOnImageMissing(RCTMLNImages rCTMLNImages, Boolean bool) {
        rCTMLNImages.setHasOnImageMissing(bool.booleanValue());
    }

    @ReactProp(name = "nativeImages")
    public void setNativeImages(RCTMLNImages rCTMLNImages, ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            String string = readableArray.getString(i);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) ResourceUtils.getDrawableByName(this.mContext, string);
            if (bitmapDrawable != null) {
                arrayList.add(new AbstractMap.SimpleEntry(string, bitmapDrawable));
            }
        }
        rCTMLNImages.setNativeImages(arrayList);
    }

    @Override // com.maplibre.rctmln.components.AbstractEventEmitter
    public Map<String, String> customEvents() {
        return MapBuilder.builder().put(EventKeys.IMAGES_MISSING, "onImageMissing").build();
    }
}

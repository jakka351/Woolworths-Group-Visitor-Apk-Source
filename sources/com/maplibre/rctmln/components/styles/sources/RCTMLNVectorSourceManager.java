package com.maplibre.rctmln.components.styles.sources;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.maplibre.rctmln.events.constants.EventKeys;
import com.maplibre.rctmln.utils.ConvertUtils;
import com.maplibre.rctmln.utils.ExpressionParser;
import java.util.Map;
import javax.annotation.Nonnull;

/* loaded from: classes3.dex */
public class RCTMLNVectorSourceManager extends RCTMLNTileSourceManager<RCTMLNVectorSource> {
    public static final int METHOD_FEATURES = 102;
    public static final String REACT_CLASS = "RCTMLNVectorSource";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return REACT_CLASS;
    }

    public RCTMLNVectorSourceManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @Nonnull
    public RCTMLNVectorSource createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        return new RCTMLNVectorSource(themedReactContext, this);
    }

    @ReactProp(name = "hasPressListener")
    public void setHasPressListener(RCTMLNVectorSource rCTMLNVectorSource, boolean z) {
        rCTMLNVectorSource.setHasPressListener(z);
    }

    @ReactProp(name = "hitbox")
    public void setHitbox(RCTMLNVectorSource rCTMLNVectorSource, ReadableMap readableMap) {
        rCTMLNVectorSource.setHitbox(readableMap);
    }

    @Override // com.maplibre.rctmln.components.AbstractEventEmitter
    public Map<String, String> customEvents() {
        return MapBuilder.builder().put(EventKeys.VECTOR_SOURCE_LAYER_CLICK, "onMapboxVectorSourcePress").put(EventKeys.MAP_ANDROID_CALLBACK, "onAndroidCallback").build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.builder().put("features", 102).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(RCTMLNVectorSource rCTMLNVectorSource, int i, ReadableArray readableArray) {
        if (i != 102) {
            return;
        }
        rCTMLNVectorSource.querySourceFeatures(readableArray.getString(0), ConvertUtils.toStringList(readableArray.getArray(1)), ExpressionParser.from(readableArray.getArray(2)));
    }
}

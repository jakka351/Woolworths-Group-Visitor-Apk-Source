package com.maplibre.rctmln.components.annotation;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.maplibre.rctmln.components.AbstractEventEmitter;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import java.util.Map;

/* loaded from: classes3.dex */
public class RCTMLNMarkerViewManager extends AbstractEventEmitter<RCTMLNMarkerView> {
    public static final String REACT_CLASS = "RCTMLNMarkerView";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public RCTMLNMarkerViewManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactProp(name = "coordinate")
    public void setCoordinate(RCTMLNMarkerView rCTMLNMarkerView, String str) {
        rCTMLNMarkerView.setCoordinate(GeoJSONUtils.toPointGeometry(str));
    }

    @ReactProp(name = "anchor")
    public void setAnchor(RCTMLNMarkerView rCTMLNMarkerView, ReadableMap readableMap) {
        rCTMLNMarkerView.setAnchor((float) readableMap.getDouble("x"), (float) readableMap.getDouble("y"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNMarkerView createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNMarkerView(themedReactContext, this);
    }

    @Override // com.maplibre.rctmln.components.AbstractEventEmitter
    public Map<String, String> customEvents() {
        return MapBuilder.builder().build();
    }
}

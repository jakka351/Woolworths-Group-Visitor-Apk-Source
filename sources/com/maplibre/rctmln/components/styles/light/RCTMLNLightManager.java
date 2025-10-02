package com.maplibre.rctmln.components.styles.light;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

/* loaded from: classes3.dex */
public class RCTMLNLightManager extends ViewGroupManager<RCTMLNLight> {
    public static final String REACT_CLASS = "RCTMLNLight";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNLight createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNLight(themedReactContext);
    }

    @ReactProp(name = "reactStyle")
    public void setReactStyle(RCTMLNLight rCTMLNLight, ReadableMap readableMap) {
        rCTMLNLight.setReactStyle(readableMap);
    }
}

package com.maplibre.rctmln.components.location;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nonnull;

/* loaded from: classes3.dex */
public class RCTMLNNativeUserLocationManager extends ViewGroupManager<RCTMLNNativeUserLocation> {
    public static final String REACT_CLASS = "RCTMLNNativeUserLocation";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "androidRenderMode")
    public void setAndroidRenderMode(RCTMLNNativeUserLocation rCTMLNNativeUserLocation, String str) {
        if ("compass".equalsIgnoreCase(str)) {
            rCTMLNNativeUserLocation.setRenderMode(4);
        } else if ("gps".equalsIgnoreCase(str)) {
            rCTMLNNativeUserLocation.setRenderMode(8);
        } else {
            rCTMLNNativeUserLocation.setRenderMode(18);
        }
    }

    @ReactProp(name = "androidPreferredFramesPerSecond")
    public void setPreferredFramesPerSecond(RCTMLNNativeUserLocation rCTMLNNativeUserLocation, int i) {
        rCTMLNNativeUserLocation.setPreferredFramesPerSecond(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @Nonnull
    public RCTMLNNativeUserLocation createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        return new RCTMLNNativeUserLocation(themedReactContext);
    }
}

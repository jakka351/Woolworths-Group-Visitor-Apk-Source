package com.maplibre.rctmln.components.annotation;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

/* loaded from: classes3.dex */
public class RCTMLNCalloutManager extends ViewGroupManager<RCTMLNCallout> {
    public static final String REACT_CLASS = "RCTMLNCallout";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNCallout createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNCallout(themedReactContext);
    }
}

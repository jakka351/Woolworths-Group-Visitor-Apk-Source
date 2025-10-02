package com.maplibre.rctmln.components.styles.sources;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import javax.annotation.Nonnull;

/* loaded from: classes3.dex */
public class RCTMLNRasterSourceManager extends RCTMLNTileSourceManager<RCTMLNRasterSource> {
    public static final String REACT_CLASS = "RCTMLNRasterSource";

    @Override // com.maplibre.rctmln.components.AbstractEventEmitter
    public Map<String, String> customEvents() {
        return null;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return REACT_CLASS;
    }

    public RCTMLNRasterSourceManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @Nonnull
    public RCTMLNRasterSource createViewInstance(@Nonnull ThemedReactContext themedReactContext) {
        return new RCTMLNRasterSource(themedReactContext);
    }

    @ReactProp(name = "tileSize")
    public void setTileSize(RCTMLNRasterSource rCTMLNRasterSource, int i) {
        rCTMLNRasterSource.setTileSize(i);
    }
}

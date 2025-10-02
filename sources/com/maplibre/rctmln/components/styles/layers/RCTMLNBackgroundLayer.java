package com.maplibre.rctmln.components.styles.layers;

import android.content.Context;
import com.maplibre.rctmln.components.styles.RCTMLNStyle;
import com.maplibre.rctmln.components.styles.RCTMLNStyleFactory;
import org.maplibre.android.style.layers.BackgroundLayer;

/* loaded from: classes3.dex */
public class RCTMLNBackgroundLayer extends RCTLayer<BackgroundLayer> {
    public RCTMLNBackgroundLayer(Context context) {
        super(context);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public BackgroundLayer makeLayer() {
        return new BackgroundLayer(this.mID);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public void addStyles() {
        RCTMLNStyleFactory.setBackgroundLayerStyle((BackgroundLayer) this.mLayer, new RCTMLNStyle(getContext(), this.mReactStyle, this.mMap));
    }
}

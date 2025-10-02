package com.maplibre.rctmln.components.styles.layers;

import android.content.Context;
import com.maplibre.rctmln.components.styles.RCTMLNStyle;
import com.maplibre.rctmln.components.styles.RCTMLNStyleFactory;
import org.maplibre.android.style.layers.RasterLayer;

/* loaded from: classes3.dex */
public class RCTMLNRasterLayer extends RCTLayer<RasterLayer> {
    public RCTMLNRasterLayer(Context context) {
        super(context);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public RasterLayer makeLayer() {
        return new RasterLayer(this.mID, this.mSourceID);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public void addStyles() {
        RCTMLNStyleFactory.setRasterLayerStyle((RasterLayer) this.mLayer, new RCTMLNStyle(getContext(), this.mReactStyle, this.mMap));
    }
}

package com.maplibre.rctmln.components.styles.layers;

import android.content.Context;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.components.styles.RCTMLNStyle;
import com.maplibre.rctmln.components.styles.RCTMLNStyleFactory;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.FillLayer;

/* loaded from: classes3.dex */
public class RCTMLNFillLayer extends RCTLayer<FillLayer> {
    private String mSourceLayerID;

    public RCTMLNFillLayer(Context context) {
        super(context);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    protected void updateFilter(Expression expression) {
        ((FillLayer) this.mLayer).setFilter(expression);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer, com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(RCTMLNMapView rCTMLNMapView) {
        super.addToMap(rCTMLNMapView);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public FillLayer makeLayer() {
        FillLayer fillLayer = new FillLayer(this.mID, this.mSourceID);
        String str = this.mSourceLayerID;
        if (str != null) {
            fillLayer.setSourceLayer(str);
        }
        return fillLayer;
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public void addStyles() {
        RCTMLNStyleFactory.setFillLayerStyle((FillLayer) this.mLayer, new RCTMLNStyle(getContext(), this.mReactStyle, this.mMap));
    }

    public void setSourceLayerID(String str) {
        this.mSourceLayerID = str;
        if (this.mLayer != 0) {
            ((FillLayer) this.mLayer).setSourceLayer(this.mSourceLayerID);
        }
    }
}

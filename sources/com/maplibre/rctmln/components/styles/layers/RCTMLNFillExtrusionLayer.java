package com.maplibre.rctmln.components.styles.layers;

import android.content.Context;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.components.styles.RCTMLNStyle;
import com.maplibre.rctmln.components.styles.RCTMLNStyleFactory;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.FillExtrusionLayer;

/* loaded from: classes3.dex */
public class RCTMLNFillExtrusionLayer extends RCTLayer<FillExtrusionLayer> {
    private String mSourceLayerID;

    public RCTMLNFillExtrusionLayer(Context context) {
        super(context);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    protected void updateFilter(Expression expression) {
        ((FillExtrusionLayer) this.mLayer).setFilter(expression);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer, com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(RCTMLNMapView rCTMLNMapView) {
        super.addToMap(rCTMLNMapView);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public FillExtrusionLayer makeLayer() {
        FillExtrusionLayer fillExtrusionLayer = new FillExtrusionLayer(this.mID, this.mSourceID);
        String str = this.mSourceLayerID;
        if (str != null) {
            fillExtrusionLayer.setSourceLayer(str);
        }
        return fillExtrusionLayer;
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public void addStyles() {
        RCTMLNStyleFactory.setFillExtrusionLayerStyle((FillExtrusionLayer) this.mLayer, new RCTMLNStyle(getContext(), this.mReactStyle, this.mMap));
    }

    public void setSourceLayerID(String str) {
        this.mSourceLayerID = str;
        if (this.mLayer != 0) {
            ((FillExtrusionLayer) this.mLayer).setSourceLayer(this.mSourceLayerID);
        }
    }
}

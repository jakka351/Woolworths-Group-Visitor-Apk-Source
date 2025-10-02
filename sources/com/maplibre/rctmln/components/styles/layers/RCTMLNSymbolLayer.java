package com.maplibre.rctmln.components.styles.layers;

import android.content.Context;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.components.styles.RCTMLNStyle;
import com.maplibre.rctmln.components.styles.RCTMLNStyleFactory;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.SymbolLayer;

/* loaded from: classes3.dex */
public class RCTMLNSymbolLayer extends RCTLayer<SymbolLayer> {
    private String mSourceLayerID;

    public RCTMLNSymbolLayer(Context context) {
        super(context);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    protected void updateFilter(Expression expression) {
        ((SymbolLayer) this.mLayer).setFilter(expression);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer, com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(RCTMLNMapView rCTMLNMapView) {
        super.addToMap(rCTMLNMapView);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public SymbolLayer makeLayer() {
        SymbolLayer symbolLayer = new SymbolLayer(this.mID, this.mSourceID);
        String str = this.mSourceLayerID;
        if (str != null) {
            symbolLayer.setSourceLayer(str);
        }
        return symbolLayer;
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public void addStyles() {
        RCTMLNStyleFactory.setSymbolLayerStyle((SymbolLayer) this.mLayer, new RCTMLNStyle(getContext(), this.mReactStyle, this.mMap));
    }

    public void setSourceLayerID(String str) {
        this.mSourceLayerID = str;
        if (this.mLayer != 0) {
            ((SymbolLayer) this.mLayer).setSourceLayer(str);
        }
    }
}

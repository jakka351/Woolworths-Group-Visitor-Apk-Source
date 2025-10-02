package com.maplibre.rctmln.components.styles.layers;

import android.content.Context;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.components.styles.RCTMLNStyle;
import com.maplibre.rctmln.components.styles.RCTMLNStyleFactory;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.LineLayer;

/* loaded from: classes3.dex */
public class RCTMLNLineLayer extends RCTLayer<LineLayer> {
    private String mSourceLayerID;

    public RCTMLNLineLayer(Context context) {
        super(context);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    protected void updateFilter(Expression expression) {
        ((LineLayer) this.mLayer).setFilter(expression);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer, com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(RCTMLNMapView rCTMLNMapView) {
        super.addToMap(rCTMLNMapView);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public LineLayer makeLayer() {
        LineLayer lineLayer = new LineLayer(this.mID, this.mSourceID);
        String str = this.mSourceLayerID;
        if (str != null) {
            lineLayer.setSourceLayer(str);
        }
        return lineLayer;
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public void addStyles() {
        RCTMLNStyleFactory.setLineLayerStyle((LineLayer) this.mLayer, new RCTMLNStyle(getContext(), this.mReactStyle, this.mMap));
    }

    public void setSourceLayerID(String str) {
        this.mSourceLayerID = str;
        if (this.mLayer != 0) {
            ((LineLayer) this.mLayer).setSourceLayer(this.mSourceLayerID);
        }
    }
}

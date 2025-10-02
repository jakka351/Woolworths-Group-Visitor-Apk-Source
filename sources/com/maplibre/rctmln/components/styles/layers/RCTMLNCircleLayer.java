package com.maplibre.rctmln.components.styles.layers;

import android.content.Context;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.components.styles.RCTMLNStyle;
import com.maplibre.rctmln.components.styles.RCTMLNStyleFactory;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.CircleLayer;

/* loaded from: classes3.dex */
public class RCTMLNCircleLayer extends RCTLayer<CircleLayer> {
    private String mSourceLayerID;

    public RCTMLNCircleLayer(Context context) {
        super(context);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    protected void updateFilter(Expression expression) {
        ((CircleLayer) this.mLayer).setFilter(expression);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer, com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(RCTMLNMapView rCTMLNMapView) {
        super.addToMap(rCTMLNMapView);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public CircleLayer makeLayer() {
        CircleLayer circleLayer = new CircleLayer(this.mID, this.mSourceID);
        String str = this.mSourceLayerID;
        if (str != null) {
            circleLayer.setSourceLayer(str);
        }
        return circleLayer;
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public void addStyles() {
        RCTMLNStyleFactory.setCircleLayerStyle((CircleLayer) this.mLayer, new RCTMLNStyle(getContext(), this.mReactStyle, this.mMap));
    }

    public void setSourceLayerID(String str) {
        this.mSourceLayerID = str;
        if (this.mLayer != 0) {
            ((CircleLayer) this.mLayer).setSourceLayer(str);
        }
    }
}

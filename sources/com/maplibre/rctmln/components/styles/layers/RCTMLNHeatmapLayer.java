package com.maplibre.rctmln.components.styles.layers;

import android.content.Context;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.components.styles.RCTMLNStyle;
import com.maplibre.rctmln.components.styles.RCTMLNStyleFactory;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.HeatmapLayer;

/* loaded from: classes3.dex */
public class RCTMLNHeatmapLayer extends RCTLayer<HeatmapLayer> {
    private String mSourceLayerID;

    public RCTMLNHeatmapLayer(Context context) {
        super(context);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    protected void updateFilter(Expression expression) {
        ((HeatmapLayer) this.mLayer).setFilter(expression);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer, com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(RCTMLNMapView rCTMLNMapView) {
        super.addToMap(rCTMLNMapView);
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public HeatmapLayer makeLayer() {
        HeatmapLayer heatmapLayer = new HeatmapLayer(this.mID, this.mSourceID);
        String str = this.mSourceLayerID;
        if (str != null) {
            heatmapLayer.setSourceLayer(str);
        }
        return heatmapLayer;
    }

    @Override // com.maplibre.rctmln.components.styles.layers.RCTLayer
    public void addStyles() {
        RCTMLNStyleFactory.setHeatmapLayerStyle((HeatmapLayer) this.mLayer, new RCTMLNStyle(getContext(), this.mReactStyle, this.mMap));
    }

    public void setSourceLayerID(String str) {
        this.mSourceLayerID = str;
        if (this.mLayer != 0) {
            ((HeatmapLayer) this.mLayer).setSourceLayer(str);
        }
    }
}

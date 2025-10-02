package com.maplibre.rctmln.components.styles.sources;

import android.content.Context;
import com.facebook.react.bridge.WritableNativeMap;
import com.maplibre.rctmln.components.styles.sources.RCTSource;
import com.maplibre.rctmln.events.AndroidCallbackEvent;
import com.maplibre.rctmln.events.FeatureClickEvent;
import java.util.List;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.sources.VectorSource;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;

/* loaded from: classes3.dex */
public class RCTMLNVectorSource extends RCTMLNTileSource<VectorSource> {
    private RCTMLNVectorSourceManager mManager;

    public RCTMLNVectorSource(Context context, RCTMLNVectorSourceManager rCTMLNVectorSourceManager) {
        super(context);
        this.mManager = rCTMLNVectorSourceManager;
    }

    @Override // com.maplibre.rctmln.components.styles.sources.RCTSource
    public void onPress(RCTSource.OnPressEvent onPressEvent) {
        this.mManager.handleEvent(FeatureClickEvent.makeVectorSourceEvent(this, onPressEvent));
    }

    @Override // com.maplibre.rctmln.components.styles.sources.RCTSource
    public VectorSource makeSource() {
        if (isDefaultSource(this.mID)) {
            return (VectorSource) this.mMap.getStyle().getSource(RCTSource.DEFAULT_ID);
        }
        if (getURL() != null) {
            return new VectorSource(this.mID, getURL());
        }
        return new VectorSource(this.mID, buildTileset());
    }

    public void querySourceFeatures(String str, List<String> list, Expression expression) {
        if (this.mSource == 0) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("error", "source is not yet loaded");
            this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
            return;
        }
        List<Feature> listQuerySourceFeatures = ((VectorSource) this.mSource).querySourceFeatures((String[]) list.toArray(new String[list.size()]), expression);
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putString("data", FeatureCollection.fromFeatures(listQuerySourceFeatures).toJson());
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap2));
    }
}

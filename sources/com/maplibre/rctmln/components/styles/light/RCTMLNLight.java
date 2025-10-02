package com.maplibre.rctmln.components.styles.light;

import android.content.Context;
import com.facebook.react.bridge.ReadableMap;
import com.maplibre.rctmln.components.AbstractMapFeature;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.components.styles.RCTMLNStyle;
import com.maplibre.rctmln.components.styles.RCTMLNStyleFactory;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.light.Light;

/* loaded from: classes3.dex */
public class RCTMLNLight extends AbstractMapFeature {
    private MapLibreMap mMap;
    private ReadableMap mReactStyle;

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void removeFromMap(RCTMLNMapView rCTMLNMapView) {
    }

    public RCTMLNLight(Context context) {
        super(context);
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(RCTMLNMapView rCTMLNMapView) {
        this.mMap = rCTMLNMapView.getMapboxMap();
        setLight();
    }

    public void setReactStyle(ReadableMap readableMap) {
        this.mReactStyle = readableMap;
        setLight();
    }

    private void setLight(Light light) {
        RCTMLNStyleFactory.setLightLayerStyle(light, new RCTMLNStyle(getContext(), this.mReactStyle, this.mMap));
    }

    private void setLight() {
        Style style = getStyle();
        if (style != null) {
            setLight(style.getLight());
        }
    }

    private Style getStyle() {
        MapLibreMap mapLibreMap = this.mMap;
        if (mapLibreMap == null) {
            return null;
        }
        return mapLibreMap.getStyle();
    }
}

package com.maplibre.rctmln.components;

import android.content.Context;
import com.facebook.react.views.view.ReactViewGroup;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;

/* loaded from: classes3.dex */
public abstract class AbstractMapFeature extends ReactViewGroup {
    public abstract void addToMap(RCTMLNMapView rCTMLNMapView);

    public abstract void removeFromMap(RCTMLNMapView rCTMLNMapView);

    public AbstractMapFeature(Context context) {
        super(context);
    }
}

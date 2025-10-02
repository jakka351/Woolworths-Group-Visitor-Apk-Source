package com.maplibre.rctmln.components.annotation;

import android.view.View;
import org.maplibre.android.geometry.LatLng;

/* loaded from: classes3.dex */
public class MarkerView extends org.maplibre.android.plugins.markerview.MarkerView {
    View view;

    public MarkerView(LatLng latLng, View view) {
        super(latLng, view);
        this.view = view;
    }

    public View getView() {
        return this.view;
    }
}

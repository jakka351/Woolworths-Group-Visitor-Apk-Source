package org.maplibre.android.plugins.markerview;

import android.graphics.PointF;
import android.view.View;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.maps.Projection;

/* loaded from: classes2.dex */
public class MarkerView {
    private LatLng latLng;
    private OnPositionUpdateListener onPositionUpdateListener;
    private Projection projection;
    private final View view;

    public interface OnPositionUpdateListener {
        PointF onUpdate(PointF pointF);
    }

    public MarkerView(LatLng latLng, View view) {
        this.latLng = latLng;
        this.view = view;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
        update();
    }

    public void setOnPositionUpdateListener(OnPositionUpdateListener onPositionUpdateListener) {
        this.onPositionUpdateListener = onPositionUpdateListener;
    }

    void setProjection(Projection projection) {
        this.projection = projection;
    }

    View getView() {
        return this.view;
    }

    void update() {
        PointF screenLocation = this.projection.toScreenLocation(this.latLng);
        OnPositionUpdateListener onPositionUpdateListener = this.onPositionUpdateListener;
        if (onPositionUpdateListener != null) {
            screenLocation = onPositionUpdateListener.onUpdate(screenLocation);
        }
        this.view.setX(screenLocation.x);
        this.view.setY(screenLocation.y);
    }
}

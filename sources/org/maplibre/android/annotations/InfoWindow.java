package org.maplibre.android.annotations;

import android.graphics.PointF;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import org.maplibre.android.R;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;

@Deprecated
/* loaded from: classes2.dex */
public class InfoWindow {
    private WeakReference<Marker> boundMarker;
    private final ViewTreeObserver.OnGlobalLayoutListener contentUpdateListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: org.maplibre.android.annotations.InfoWindow.3
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            View view = InfoWindow.this.view.get();
            if (view != null) {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                InfoWindow.this.viewHeightOffset = (-view.getMeasuredHeight()) + InfoWindow.this.markerHeightOffset;
                InfoWindow.this.update();
            }
        }
    };
    private PointF coordinates;
    private boolean isVisible;
    private int layoutRes;
    private WeakReference<MapLibreMap> maplibreMap;
    private float markerHeightOffset;
    private float markerWidthOffset;
    protected WeakReference<View> view;
    private float viewHeightOffset;
    private float viewWidthOffset;

    InfoWindow(MapView mapView, int i, MapLibreMap mapLibreMap) {
        this.layoutRes = i;
        initialize(LayoutInflater.from(mapView.getContext()).inflate(i, (ViewGroup) mapView, false), mapLibreMap);
    }

    InfoWindow(View view, MapLibreMap mapLibreMap) {
        initialize(view, mapLibreMap);
    }

    private void initialize(View view, MapLibreMap mapLibreMap) {
        this.maplibreMap = new WeakReference<>(mapLibreMap);
        this.isVisible = false;
        this.view = new WeakReference<>(view);
        view.setOnClickListener(new View.OnClickListener() { // from class: org.maplibre.android.annotations.InfoWindow.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                MapLibreMap mapLibreMap2 = (MapLibreMap) InfoWindow.this.maplibreMap.get();
                if (mapLibreMap2 != null) {
                    MapLibreMap.OnInfoWindowClickListener onInfoWindowClickListener = mapLibreMap2.getOnInfoWindowClickListener();
                    if (onInfoWindowClickListener != null ? onInfoWindowClickListener.onInfoWindowClick(InfoWindow.this.getBoundMarker()) : false) {
                        return;
                    }
                    InfoWindow.this.closeInfoWindow();
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: org.maplibre.android.annotations.InfoWindow.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view2) {
                MapLibreMap.OnInfoWindowLongClickListener onInfoWindowLongClickListener;
                MapLibreMap mapLibreMap2 = (MapLibreMap) InfoWindow.this.maplibreMap.get();
                if (mapLibreMap2 == null || (onInfoWindowLongClickListener = mapLibreMap2.getOnInfoWindowLongClickListener()) == null) {
                    return true;
                }
                onInfoWindowLongClickListener.onInfoWindowLongClick(InfoWindow.this.getBoundMarker());
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeInfoWindow() {
        MapLibreMap mapLibreMap = this.maplibreMap.get();
        Marker marker = this.boundMarker.get();
        if (marker != null && mapLibreMap != null) {
            mapLibreMap.deselectMarker(marker);
        }
        close();
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    org.maplibre.android.annotations.InfoWindow open(org.maplibre.android.maps.MapView r18, org.maplibre.android.annotations.Marker r19, org.maplibre.android.geometry.LatLng r20, int r21, int r22) throws android.content.res.Resources.NotFoundException {
        /*
            Method dump skipped, instructions count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.maplibre.android.annotations.InfoWindow.open(org.maplibre.android.maps.MapView, org.maplibre.android.annotations.Marker, org.maplibre.android.geometry.LatLng, int, int):org.maplibre.android.annotations.InfoWindow");
    }

    InfoWindow close() {
        MapLibreMap mapLibreMap = this.maplibreMap.get();
        if (this.isVisible && mapLibreMap != null) {
            this.isVisible = false;
            View view = this.view.get();
            if (view != null && view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            Marker boundMarker = getBoundMarker();
            MapLibreMap.OnInfoWindowCloseListener onInfoWindowCloseListener = mapLibreMap.getOnInfoWindowCloseListener();
            if (onInfoWindowCloseListener != null) {
                onInfoWindowCloseListener.onInfoWindowClose(boundMarker);
            }
            setBoundMarker(null);
        }
        return this;
    }

    void adaptDefaultMarker(Marker marker, MapLibreMap mapLibreMap, MapView mapView) {
        View viewInflate = this.view.get();
        if (viewInflate == null) {
            viewInflate = LayoutInflater.from(mapView.getContext()).inflate(this.layoutRes, (ViewGroup) mapView, false);
            initialize(viewInflate, mapLibreMap);
        }
        this.maplibreMap = new WeakReference<>(mapLibreMap);
        String title = marker.getTitle();
        TextView textView = (TextView) viewInflate.findViewById(R.id.infowindow_title);
        if (!TextUtils.isEmpty(title)) {
            textView.setText(title);
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        String snippet = marker.getSnippet();
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.infowindow_description);
        if (!TextUtils.isEmpty(snippet)) {
            textView2.setText(snippet);
            textView2.setVisibility(0);
        } else {
            textView2.setVisibility(8);
        }
    }

    InfoWindow setBoundMarker(Marker marker) {
        this.boundMarker = new WeakReference<>(marker);
        return this;
    }

    Marker getBoundMarker() {
        WeakReference<Marker> weakReference = this.boundMarker;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public void update() {
        MapLibreMap mapLibreMap = this.maplibreMap.get();
        Marker marker = this.boundMarker.get();
        View view = this.view.get();
        if (mapLibreMap == null || marker == null || view == null) {
            return;
        }
        PointF screenLocation = mapLibreMap.getProjection().toScreenLocation(marker.getPosition());
        this.coordinates = screenLocation;
        if (view instanceof BubbleLayout) {
            view.setX((screenLocation.x + this.viewWidthOffset) - this.markerWidthOffset);
        } else {
            view.setX((screenLocation.x - (view.getMeasuredWidth() / 2)) - this.markerWidthOffset);
        }
        view.setY(this.coordinates.y + this.viewHeightOffset);
    }

    void onContentUpdate() {
        View view = this.view.get();
        if (view != null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(this.contentUpdateListener);
            }
        }
    }

    public View getView() {
        WeakReference<View> weakReference = this.view;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    boolean isVisible() {
        return this.isVisible;
    }
}

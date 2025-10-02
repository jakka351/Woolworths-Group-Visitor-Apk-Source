package org.maplibre.android.maps;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.maplibre.android.maps.MapFragment;
import org.maplibre.android.utils.MapFragmentUtils;

/* loaded from: classes2.dex */
public class SupportMapFragment extends Fragment implements OnMapReadyCallback {
    private MapView map;
    private final List<OnMapReadyCallback> mapReadyCallbackList = new ArrayList();
    private MapFragment.OnMapViewReadyCallback mapViewReadyCallback;
    private MapLibreMap maplibreMap;

    public static SupportMapFragment newInstance() {
        return new SupportMapFragment();
    }

    public static SupportMapFragment newInstance(MapLibreMapOptions mapLibreMapOptions) {
        SupportMapFragment supportMapFragment = new SupportMapFragment();
        supportMapFragment.setArguments(MapFragmentUtils.createFragmentArgs(mapLibreMapOptions));
        return supportMapFragment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MapFragment.OnMapViewReadyCallback) {
            this.mapViewReadyCallback = (MapFragment.OnMapViewReadyCallback) context;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(context, attributeSet, bundle);
        setArguments(MapFragmentUtils.createFragmentArgs(MapLibreMapOptions.createFromAttributes(context, attributeSet)));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        Context context = layoutInflater.getContext();
        MapView mapView = new MapView(context, MapFragmentUtils.resolveArgs(context, getArguments()));
        this.map = mapView;
        return mapView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.map.onCreate(bundle);
        this.map.getMapAsync(this);
        MapFragment.OnMapViewReadyCallback onMapViewReadyCallback = this.mapViewReadyCallback;
        if (onMapViewReadyCallback != null) {
            onMapViewReadyCallback.onMapViewReady(this.map);
        }
    }

    @Override // org.maplibre.android.maps.OnMapReadyCallback
    public void onMapReady(MapLibreMap mapLibreMap) {
        this.maplibreMap = mapLibreMap;
        Iterator<OnMapReadyCallback> it = this.mapReadyCallbackList.iterator();
        while (it.hasNext()) {
            it.next().onMapReady(mapLibreMap);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.map.onStart();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.map.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.map.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        MapView mapView = this.map;
        if (mapView != null) {
            mapView.onSaveInstanceState(bundle);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.map.onStop();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        MapView mapView = this.map;
        if (mapView != null) {
            mapView.onLowMemory();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.map.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mapReadyCallbackList.clear();
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        MapLibreMap mapLibreMap = this.maplibreMap;
        if (mapLibreMap == null) {
            this.mapReadyCallbackList.add(onMapReadyCallback);
        } else {
            onMapReadyCallback.onMapReady(mapLibreMap);
        }
    }
}

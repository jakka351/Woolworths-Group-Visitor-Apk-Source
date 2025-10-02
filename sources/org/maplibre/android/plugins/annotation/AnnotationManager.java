package org.maplibre.android.plugins.annotation;

import android.graphics.PointF;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;
import org.maplibre.android.maps.Style;
import org.maplibre.android.plugins.annotation.Annotation;
import org.maplibre.android.plugins.annotation.OnAnnotationClickListener;
import org.maplibre.android.plugins.annotation.OnAnnotationDragListener;
import org.maplibre.android.plugins.annotation.OnAnnotationLongClickListener;
import org.maplibre.android.plugins.annotation.Options;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.Layer;
import org.maplibre.android.style.layers.PropertyValue;
import org.maplibre.android.style.sources.GeoJsonOptions;
import org.maplibre.android.style.sources.GeoJsonSource;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;

/* loaded from: classes2.dex */
public abstract class AnnotationManager<L extends Layer, T extends Annotation, S extends Options<T>, D extends OnAnnotationDragListener<T>, U extends OnAnnotationClickListener<T>, V extends OnAnnotationLongClickListener<T>> {
    private static final String TAG = "AnnotationManager";
    private String aboveLayerId;
    private String belowLayerId;
    protected CoreElementProvider<L> coreElementProvider;
    private long currentId;
    private DraggableAnnotationController draggableAnnotationController;
    protected GeoJsonSource geoJsonSource;
    protected L layer;
    Expression layerFilter;
    private final AnnotationManager<L, T, S, D, U, V>.MapClickResolver mapClickResolver;
    private final MapView mapView;
    protected final MapLibreMap maplibreMap;
    private Style style;
    protected final LongSparseArray<T> annotations = new LongSparseArray<>();
    final Map<String, Boolean> dataDrivenPropertyUsageMap = new HashMap();
    final Map<String, PropertyValue> constantPropertyUsageMap = new HashMap();
    private final List<D> dragListeners = new ArrayList();
    private final List<U> clickListeners = new ArrayList();
    private final List<V> longClickListeners = new ArrayList();
    private AtomicBoolean isSourceUpToDate = new AtomicBoolean(true);

    abstract String getAnnotationIdKey();

    abstract void initializeDataDrivenPropertyMap();

    protected abstract void setDataDrivenPropertyIsUsed(String str);

    abstract void setFilter(Expression expression);

    protected AnnotationManager(MapView mapView, final MapLibreMap mapLibreMap, Style style, CoreElementProvider<L> coreElementProvider, DraggableAnnotationController draggableAnnotationController, String str, String str2, final GeoJsonOptions geoJsonOptions) {
        this.mapView = mapView;
        this.maplibreMap = mapLibreMap;
        this.style = style;
        this.belowLayerId = str;
        this.aboveLayerId = str2;
        this.coreElementProvider = coreElementProvider;
        this.draggableAnnotationController = draggableAnnotationController;
        if (!style.isFullyLoaded()) {
            throw new RuntimeException("The style has to be non-null and fully loaded.");
        }
        AnnotationManager<L, T, S, D, U, V>.MapClickResolver mapClickResolver = new MapClickResolver();
        this.mapClickResolver = mapClickResolver;
        mapLibreMap.addOnMapClickListener(mapClickResolver);
        mapLibreMap.addOnMapLongClickListener(mapClickResolver);
        initializeSourcesAndLayers(geoJsonOptions);
        draggableAnnotationController.addAnnotationManager(this);
        mapView.addOnDidFinishLoadingStyleListener(new MapView.OnDidFinishLoadingStyleListener() { // from class: org.maplibre.android.plugins.annotation.AnnotationManager.1
            @Override // org.maplibre.android.maps.MapView.OnDidFinishLoadingStyleListener
            public void onDidFinishLoadingStyle() {
                mapLibreMap.getStyle(new Style.OnStyleLoaded() { // from class: org.maplibre.android.plugins.annotation.AnnotationManager.1.1
                    @Override // org.maplibre.android.maps.Style.OnStyleLoaded
                    public void onStyleLoaded(Style style2) {
                        AnnotationManager.this.style = style2;
                        AnnotationManager.this.initializeSourcesAndLayers(geoJsonOptions);
                    }
                });
            }
        });
    }

    public String getLayerId() {
        return this.layer.getId();
    }

    public String getBelowLayerId() {
        return this.belowLayerId;
    }

    public String getAboveLayerId() {
        return this.aboveLayerId;
    }

    public LongSparseArray<T> getAnnotations() {
        return this.annotations;
    }

    public T create(S s) {
        T t = (T) s.build(this.currentId, this);
        this.annotations.put(t.getId(), t);
        this.currentId++;
        updateSource();
        return t;
    }

    public List<T> create(List<S> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<S> it = list.iterator();
        while (it.hasNext()) {
            Annotation annotationBuild = it.next().build(this.currentId, this);
            arrayList.add(annotationBuild);
            this.annotations.put(annotationBuild.getId(), annotationBuild);
            this.currentId++;
        }
        updateSource();
        return arrayList;
    }

    public void delete(T t) {
        this.annotations.remove(t.getId());
        this.draggableAnnotationController.onAnnotationDeleted(t);
        updateSource();
    }

    public void delete(List<T> list) {
        for (T t : list) {
            this.annotations.remove(t.getId());
            this.draggableAnnotationController.onAnnotationDeleted(t);
        }
        updateSource();
    }

    public void deleteAll() {
        this.annotations.clear();
        updateSource();
    }

    public void update(T t) {
        if (this.annotations.containsValue(t)) {
            this.annotations.put(t.getId(), t);
            updateSource();
        } else {
            Logger.e(TAG, "Can't update annotation: " + t.toString() + ", the annotation isn't active annotation.");
        }
    }

    public void update(List<T> list) {
        for (T t : list) {
            this.annotations.put(t.getId(), t);
        }
        updateSource();
    }

    public void updateSource() {
        if (this.isSourceUpToDate.compareAndSet(true, false)) {
            this.mapView.post(new Runnable() { // from class: org.maplibre.android.plugins.annotation.AnnotationManager$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m2985xe19594bc();
                }
            });
        }
    }

    /* renamed from: lambda$updateSource$0$org-maplibre-android-plugins-annotation-AnnotationManager, reason: not valid java name */
    /* synthetic */ void m2985xe19594bc() {
        this.isSourceUpToDate.set(true);
        if (this.style.isFullyLoaded()) {
            updateSourceNow();
        }
    }

    void updateSourceNow() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.annotations.size(); i++) {
            T tValueAt = this.annotations.valueAt(i);
            arrayList.add(Feature.fromGeometry(tValueAt.getGeometry(), tValueAt.getFeature()));
            tValueAt.setUsedDataDrivenProperties();
        }
        this.geoJsonSource.setGeoJson(FeatureCollection.fromFeatures(arrayList));
    }

    void enableDataDrivenProperty(String str) {
        if (this.dataDrivenPropertyUsageMap.get(str).equals(false)) {
            this.dataDrivenPropertyUsageMap.put(str, true);
            setDataDrivenPropertyIsUsed(str);
        }
    }

    public void addDragListener(D d) {
        this.dragListeners.add(d);
    }

    public void removeDragListener(D d) {
        this.dragListeners.remove(d);
    }

    public void addClickListener(U u) {
        this.clickListeners.add(u);
    }

    public void removeClickListener(U u) {
        this.clickListeners.remove(u);
    }

    public void addLongClickListener(V v) {
        this.longClickListeners.add(v);
    }

    public void removeLongClickListener(V v) {
        this.longClickListeners.remove(v);
    }

    List<U> getClickListeners() {
        return this.clickListeners;
    }

    List<V> getLongClickListeners() {
        return this.longClickListeners;
    }

    List<D> getDragListeners() {
        return this.dragListeners;
    }

    public void onDestroy() {
        this.maplibreMap.removeOnMapClickListener(this.mapClickResolver);
        this.maplibreMap.removeOnMapLongClickListener(this.mapClickResolver);
        this.draggableAnnotationController.removeAnnotationManager(this);
        this.dragListeners.clear();
        this.clickListeners.clear();
        this.longClickListeners.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeSourcesAndLayers(GeoJsonOptions geoJsonOptions) {
        this.geoJsonSource = this.coreElementProvider.getSource(geoJsonOptions);
        this.layer = (L) this.coreElementProvider.getLayer();
        this.style.addSource(this.geoJsonSource);
        String str = this.belowLayerId;
        if (str != null && this.aboveLayerId != null) {
            throw new IllegalArgumentException("At most one of belowLayerId and aboveLayerId can be set, not both!");
        }
        if (str != null) {
            this.style.addLayerBelow(this.layer, str);
        } else {
            String str2 = this.aboveLayerId;
            if (str2 != null) {
                this.style.addLayerAbove(this.layer, str2);
            } else {
                this.style.addLayer(this.layer);
            }
        }
        initializeDataDrivenPropertyMap();
        this.layer.setProperties((PropertyValue[]) this.constantPropertyUsageMap.values().toArray(new PropertyValue[0]));
        Expression expression = this.layerFilter;
        if (expression != null) {
            setFilter(expression);
        }
        updateSource();
    }

    private class MapClickResolver implements MapLibreMap.OnMapClickListener, MapLibreMap.OnMapLongClickListener {
        private MapClickResolver() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.maplibre.android.maps.MapLibreMap.OnMapClickListener
        public boolean onMapClick(LatLng latLng) {
            Annotation annotationQueryMapForFeatures;
            if (!AnnotationManager.this.clickListeners.isEmpty() && (annotationQueryMapForFeatures = AnnotationManager.this.queryMapForFeatures(latLng)) != null) {
                Iterator it = AnnotationManager.this.clickListeners.iterator();
                while (it.hasNext()) {
                    if (((OnAnnotationClickListener) it.next()).onAnnotationClick(annotationQueryMapForFeatures)) {
                        return true;
                    }
                }
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.maplibre.android.maps.MapLibreMap.OnMapLongClickListener
        public boolean onMapLongClick(LatLng latLng) {
            Annotation annotationQueryMapForFeatures;
            if (!AnnotationManager.this.longClickListeners.isEmpty() && (annotationQueryMapForFeatures = AnnotationManager.this.queryMapForFeatures(latLng)) != null) {
                Iterator it = AnnotationManager.this.longClickListeners.iterator();
                while (it.hasNext()) {
                    if (((OnAnnotationLongClickListener) it.next()).onAnnotationLongClick(annotationQueryMapForFeatures)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public T queryMapForFeatures(LatLng latLng) {
        return (T) queryMapForFeatures(this.maplibreMap.getProjection().toScreenLocation(latLng));
    }

    T queryMapForFeatures(PointF pointF) {
        List<Feature> listQueryRenderedFeatures = this.maplibreMap.queryRenderedFeatures(pointF, this.coreElementProvider.getLayerId());
        if (listQueryRenderedFeatures.isEmpty()) {
            return null;
        }
        return this.annotations.get(listQueryRenderedFeatures.get(0).getProperty(getAnnotationIdKey()).getAsLong());
    }
}

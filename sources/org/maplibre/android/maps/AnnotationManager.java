package org.maplibre.android.maps;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.maplibre.android.MapLibre;
import org.maplibre.android.R;
import org.maplibre.android.annotations.Annotation;
import org.maplibre.android.annotations.BaseMarkerOptions;
import org.maplibre.android.annotations.Marker;
import org.maplibre.android.annotations.Polygon;
import org.maplibre.android.annotations.PolygonOptions;
import org.maplibre.android.annotations.Polyline;
import org.maplibre.android.annotations.PolylineOptions;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.MapLibreMap;

/* loaded from: classes2.dex */
class AnnotationManager {
    private static final long NO_ANNOTATION_ID = -1;
    private static final String TAG = "Mbgl-AnnotationManager";
    private Annotations annotations;
    private final LongSparseArray<Annotation> annotationsArray;
    private final IconManager iconManager;
    private final MapView mapView;
    private MapLibreMap maplibreMap;
    private Markers markers;
    private MapLibreMap.OnMarkerClickListener onMarkerClickListener;
    private MapLibreMap.OnPolygonClickListener onPolygonClickListener;
    private MapLibreMap.OnPolylineClickListener onPolylineClickListener;
    private Polygons polygons;
    private Polylines polylines;
    private ShapeAnnotations shapeAnnotations;
    private final InfoWindowManager infoWindowManager = new InfoWindowManager();
    private final List<Marker> selectedMarkers = new ArrayList();

    AnnotationManager(MapView mapView, LongSparseArray<Annotation> longSparseArray, IconManager iconManager, Annotations annotations, Markers markers, Polygons polygons, Polylines polylines, ShapeAnnotations shapeAnnotations) {
        this.mapView = mapView;
        this.annotationsArray = longSparseArray;
        this.iconManager = iconManager;
        this.annotations = annotations;
        this.markers = markers;
        this.polygons = polygons;
        this.polylines = polylines;
        this.shapeAnnotations = shapeAnnotations;
    }

    AnnotationManager bind(MapLibreMap mapLibreMap) {
        this.maplibreMap = mapLibreMap;
        return this;
    }

    void update() {
        this.infoWindowManager.update();
    }

    Annotation getAnnotation(long j) {
        return this.annotations.obtainBy(j);
    }

    List<Annotation> getAnnotations() {
        return this.annotations.obtainAll();
    }

    void removeAnnotation(long j) {
        this.annotations.removeBy(j);
    }

    void removeAnnotation(Annotation annotation) {
        if (annotation instanceof Marker) {
            Marker marker = (Marker) annotation;
            marker.hideInfoWindow();
            if (this.selectedMarkers.contains(marker)) {
                this.selectedMarkers.remove(marker);
            }
            this.iconManager.iconCleanup(marker.getIcon());
        }
        this.annotations.removeBy(annotation);
    }

    void removeAnnotations(List<? extends Annotation> list) {
        for (Annotation annotation : list) {
            if (annotation instanceof Marker) {
                Marker marker = (Marker) annotation;
                marker.hideInfoWindow();
                if (this.selectedMarkers.contains(marker)) {
                    this.selectedMarkers.remove(marker);
                }
                this.iconManager.iconCleanup(marker.getIcon());
            }
        }
        this.annotations.removeBy(list);
    }

    void removeAnnotations() {
        int size = this.annotationsArray.size();
        long[] jArr = new long[size];
        this.selectedMarkers.clear();
        for (int i = 0; i < size; i++) {
            long jKeyAt = this.annotationsArray.keyAt(i);
            jArr[i] = jKeyAt;
            Annotation annotation = this.annotationsArray.get(jKeyAt);
            if (annotation instanceof Marker) {
                Marker marker = (Marker) annotation;
                marker.hideInfoWindow();
                this.iconManager.iconCleanup(marker.getIcon());
            }
        }
        this.annotations.removeAll();
    }

    Marker addMarker(BaseMarkerOptions baseMarkerOptions, MapLibreMap mapLibreMap) {
        return this.markers.addBy(baseMarkerOptions, mapLibreMap);
    }

    List<Marker> addMarkers(List<? extends BaseMarkerOptions> list, MapLibreMap mapLibreMap) {
        return this.markers.addBy(list, mapLibreMap);
    }

    void updateMarker(Marker marker, MapLibreMap mapLibreMap) {
        if (!isAddedToMap(marker)) {
            logNonAdded(marker);
        } else {
            this.markers.update(marker, mapLibreMap);
        }
    }

    List<Marker> getMarkers() {
        return this.markers.obtainAll();
    }

    List<Marker> getMarkersInRect(RectF rectF) {
        return this.markers.obtainAllIn(rectF);
    }

    void reloadMarkers() {
        this.markers.reload();
    }

    Polygon addPolygon(PolygonOptions polygonOptions, MapLibreMap mapLibreMap) {
        return this.polygons.addBy(polygonOptions, mapLibreMap);
    }

    List<Polygon> addPolygons(List<PolygonOptions> list, MapLibreMap mapLibreMap) {
        return this.polygons.addBy(list, mapLibreMap);
    }

    void updatePolygon(Polygon polygon) {
        if (!isAddedToMap(polygon)) {
            logNonAdded(polygon);
        } else {
            this.polygons.update(polygon);
        }
    }

    List<Polygon> getPolygons() {
        return this.polygons.obtainAll();
    }

    Polyline addPolyline(PolylineOptions polylineOptions, MapLibreMap mapLibreMap) {
        return this.polylines.addBy(polylineOptions, mapLibreMap);
    }

    List<Polyline> addPolylines(List<PolylineOptions> list, MapLibreMap mapLibreMap) {
        return this.polylines.addBy(list, mapLibreMap);
    }

    void updatePolyline(Polyline polyline) {
        if (!isAddedToMap(polyline)) {
            logNonAdded(polyline);
        } else {
            this.polylines.update(polyline);
        }
    }

    List<Polyline> getPolylines() {
        return this.polylines.obtainAll();
    }

    void setOnMarkerClickListener(MapLibreMap.OnMarkerClickListener onMarkerClickListener) {
        this.onMarkerClickListener = onMarkerClickListener;
    }

    void setOnPolygonClickListener(MapLibreMap.OnPolygonClickListener onPolygonClickListener) {
        this.onPolygonClickListener = onPolygonClickListener;
    }

    void setOnPolylineClickListener(MapLibreMap.OnPolylineClickListener onPolylineClickListener) {
        this.onPolylineClickListener = onPolylineClickListener;
    }

    void selectMarker(Marker marker) {
        if (this.selectedMarkers.contains(marker)) {
            return;
        }
        if (!this.infoWindowManager.isAllowConcurrentMultipleOpenInfoWindows()) {
            deselectMarkers();
        }
        if (this.infoWindowManager.isInfoWindowValidForMarker(marker) || this.infoWindowManager.getInfoWindowAdapter() != null) {
            this.infoWindowManager.add(marker.showInfoWindow(this.maplibreMap, this.mapView));
        }
        this.selectedMarkers.add(marker);
    }

    void deselectMarkers() {
        if (this.selectedMarkers.isEmpty()) {
            return;
        }
        for (Marker marker : this.selectedMarkers) {
            if (marker != null && marker.isInfoWindowShown()) {
                marker.hideInfoWindow();
            }
        }
        this.selectedMarkers.clear();
    }

    void deselectMarker(Marker marker) {
        if (this.selectedMarkers.contains(marker)) {
            if (marker.isInfoWindowShown()) {
                marker.hideInfoWindow();
            }
            this.selectedMarkers.remove(marker);
        }
    }

    List<Marker> getSelectedMarkers() {
        return this.selectedMarkers;
    }

    InfoWindowManager getInfoWindowManager() {
        return this.infoWindowManager;
    }

    void adjustTopOffsetPixels(MapLibreMap mapLibreMap) {
        int size = this.annotationsArray.size();
        for (int i = 0; i < size; i++) {
            Annotation annotation = this.annotationsArray.get(i);
            if (annotation instanceof Marker) {
                Marker marker = (Marker) annotation;
                marker.setTopOffsetPixels(this.iconManager.getTopOffsetPixelsForIcon(marker.getIcon()));
            }
        }
        for (Marker marker2 : this.selectedMarkers) {
            if (marker2.isInfoWindowShown()) {
                marker2.hideInfoWindow();
                marker2.showInfoWindow(mapLibreMap, this.mapView);
            }
        }
    }

    private boolean isAddedToMap(Annotation annotation) {
        return (annotation == null || annotation.getId() == -1 || this.annotationsArray.indexOfKey(annotation.getId()) <= -1) ? false : true;
    }

    private void logNonAdded(Annotation annotation) {
        Logger.w(TAG, String.format("Attempting to update non-added %s with value %s", annotation.getClass().getCanonicalName(), annotation));
    }

    boolean onTap(PointF pointF) {
        long jExecute = new MarkerHitResolver(this.maplibreMap).execute(getMarkerHitFromTouchArea(pointF));
        if (jExecute != -1 && isClickHandledForMarker(jExecute)) {
            return true;
        }
        Annotation annotationExecute = new ShapeAnnotationHitResolver(this.shapeAnnotations).execute(getShapeAnnotationHitFromTap(pointF));
        return annotationExecute != null && handleClickForShapeAnnotation(annotationExecute);
    }

    private ShapeAnnotationHit getShapeAnnotationHitFromTap(PointF pointF) throws Resources.NotFoundException {
        float dimension = MapLibre.getApplicationContext().getResources().getDimension(R.dimen.maplibre_eight_dp);
        return new ShapeAnnotationHit(new RectF(pointF.x - dimension, pointF.y - dimension, pointF.x + dimension, pointF.y + dimension));
    }

    private boolean handleClickForShapeAnnotation(Annotation annotation) {
        MapLibreMap.OnPolylineClickListener onPolylineClickListener;
        MapLibreMap.OnPolygonClickListener onPolygonClickListener;
        if ((annotation instanceof Polygon) && (onPolygonClickListener = this.onPolygonClickListener) != null) {
            onPolygonClickListener.onPolygonClick((Polygon) annotation);
            return true;
        }
        if (!(annotation instanceof Polyline) || (onPolylineClickListener = this.onPolylineClickListener) == null) {
            return false;
        }
        onPolylineClickListener.onPolylineClick((Polyline) annotation);
        return true;
    }

    private MarkerHit getMarkerHitFromTouchArea(PointF pointF) {
        float highestIconHeight = (int) (this.iconManager.getHighestIconHeight() * 1.5d);
        float highestIconWidth = (int) (this.iconManager.getHighestIconWidth() * 1.5d);
        RectF rectF = new RectF(pointF.x - highestIconHeight, pointF.y - highestIconWidth, pointF.x + highestIconHeight, pointF.y + highestIconWidth);
        return new MarkerHit(rectF, getMarkersInRect(rectF));
    }

    private boolean isClickHandledForMarker(long j) {
        Marker marker = (Marker) getAnnotation(j);
        if (onClickMarker(marker)) {
            return true;
        }
        toggleMarkerSelectionState(marker);
        return true;
    }

    private boolean onClickMarker(Marker marker) {
        MapLibreMap.OnMarkerClickListener onMarkerClickListener = this.onMarkerClickListener;
        return onMarkerClickListener != null && onMarkerClickListener.onMarkerClick(marker);
    }

    private void toggleMarkerSelectionState(Marker marker) {
        if (!this.selectedMarkers.contains(marker)) {
            selectMarker(marker);
        } else {
            deselectMarker(marker);
        }
    }

    private static class ShapeAnnotationHitResolver {
        private ShapeAnnotations shapeAnnotations;

        ShapeAnnotationHitResolver(ShapeAnnotations shapeAnnotations) {
            this.shapeAnnotations = shapeAnnotations;
        }

        public Annotation execute(ShapeAnnotationHit shapeAnnotationHit) {
            List<Annotation> listObtainAllIn = this.shapeAnnotations.obtainAllIn(shapeAnnotationHit.tapPoint);
            if (listObtainAllIn.size() > 0) {
                return listObtainAllIn.get(0);
            }
            return null;
        }
    }

    private static class MarkerHitResolver {
        private Bitmap bitmap;
        private int bitmapHeight;
        private int bitmapWidth;
        private PointF markerLocation;
        private final Projection projection;
        private View view;
        private Rect hitRectView = new Rect();
        private RectF hitRectMarker = new RectF();
        private RectF highestSurfaceIntersection = new RectF();
        private long closestMarkerId = -1;
        private final int minimalTouchSize = (int) (MapLibre.getApplicationContext().getResources().getDisplayMetrics().density * 32.0f);

        MarkerHitResolver(MapLibreMap mapLibreMap) {
            this.projection = mapLibreMap.getProjection();
        }

        public long execute(MarkerHit markerHit) {
            resolveForMarkers(markerHit);
            return this.closestMarkerId;
        }

        private void resolveForMarkers(MarkerHit markerHit) {
            Iterator it = markerHit.markers.iterator();
            while (it.hasNext()) {
                resolveForMarker(markerHit, (Marker) it.next());
            }
        }

        private void resolveForMarker(MarkerHit markerHit, Marker marker) {
            this.markerLocation = this.projection.toScreenLocation(marker.getPosition());
            Bitmap bitmap = marker.getIcon().getBitmap();
            this.bitmap = bitmap;
            int height = bitmap.getHeight();
            this.bitmapHeight = height;
            int i = this.minimalTouchSize;
            if (height < i) {
                this.bitmapHeight = i;
            }
            int width = this.bitmap.getWidth();
            this.bitmapWidth = width;
            int i2 = this.minimalTouchSize;
            if (width < i2) {
                this.bitmapWidth = i2;
            }
            this.hitRectMarker.set(0.0f, 0.0f, this.bitmapWidth, this.bitmapHeight);
            this.hitRectMarker.offsetTo(this.markerLocation.x - (this.bitmapWidth / 2), this.markerLocation.y - (this.bitmapHeight / 2));
            hitTestMarker(markerHit, marker, this.hitRectMarker);
        }

        private void hitTestMarker(MarkerHit markerHit, Marker marker, RectF rectF) {
            if (rectF.contains(markerHit.getTapPointX(), markerHit.getTapPointY())) {
                rectF.intersect(markerHit.tapRect);
                if (isRectangleHighestSurfaceIntersection(rectF)) {
                    this.highestSurfaceIntersection = new RectF(rectF);
                    this.closestMarkerId = marker.getId();
                }
            }
        }

        private boolean isRectangleHighestSurfaceIntersection(RectF rectF) {
            return rectF.width() * rectF.height() > this.highestSurfaceIntersection.width() * this.highestSurfaceIntersection.height();
        }
    }

    private static class ShapeAnnotationHit {
        private final RectF tapPoint;

        ShapeAnnotationHit(RectF rectF) {
            this.tapPoint = rectF;
        }
    }

    private static class MarkerHit {
        private final List<Marker> markers;
        private final RectF tapRect;

        MarkerHit(RectF rectF, List<Marker> list) {
            this.tapRect = rectF;
            this.markers = list;
        }

        float getTapPointX() {
            return this.tapRect.centerX();
        }

        float getTapPointY() {
            return this.tapRect.centerY();
        }
    }
}

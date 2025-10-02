package org.maplibre.android.plugins.annotation;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.maplibre.android.gestures.AndroidGesturesManager;
import org.maplibre.android.gestures.MoveDistancesObject;
import org.maplibre.android.gestures.MoveGestureDetector;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;
import org.maplibre.geojson.Geometry;

/* loaded from: classes2.dex */
final class DraggableAnnotationController {
    private static DraggableAnnotationController INSTANCE;
    private List<AnnotationManager> annotationManagers;
    private HashMap<String, AnnotationManager> annotationManagersById;
    private Annotation draggedAnnotation;
    private AnnotationManager draggedAnnotationManager;
    private MapView mapView;
    private MapLibreMap maplibreMap;
    private final int touchAreaMaxX;
    private final int touchAreaMaxY;
    private final int touchAreaShiftX;
    private final int touchAreaShiftY;

    public static DraggableAnnotationController getInstance(MapView mapView, MapLibreMap mapLibreMap) {
        DraggableAnnotationController draggableAnnotationController = INSTANCE;
        if (draggableAnnotationController == null || draggableAnnotationController.mapView != mapView || draggableAnnotationController.maplibreMap != mapLibreMap) {
            INSTANCE = new DraggableAnnotationController(mapView, mapLibreMap);
        }
        return INSTANCE;
    }

    private static void clearInstance() {
        DraggableAnnotationController draggableAnnotationController = INSTANCE;
        if (draggableAnnotationController != null) {
            draggableAnnotationController.mapView = null;
            draggableAnnotationController.maplibreMap = null;
            INSTANCE = null;
        }
    }

    DraggableAnnotationController(MapView mapView, MapLibreMap mapLibreMap) {
        this(mapView, mapLibreMap, new AndroidGesturesManager(mapView.getContext(), false), mapView.getScrollX(), mapView.getScrollY(), mapView.getMeasuredWidth(), mapView.getMeasuredHeight());
    }

    public DraggableAnnotationController(MapView mapView, MapLibreMap mapLibreMap, final AndroidGesturesManager androidGesturesManager, int i, int i2, int i3, int i4) {
        this.annotationManagers = new LinkedList();
        this.annotationManagersById = new HashMap<>();
        this.mapView = mapView;
        this.maplibreMap = mapLibreMap;
        this.touchAreaShiftX = i;
        this.touchAreaShiftY = i2;
        this.touchAreaMaxX = i3;
        this.touchAreaMaxY = i4;
        androidGesturesManager.setMoveGestureListener(new AnnotationMoveGestureListener());
        mapView.setOnTouchListener(new View.OnTouchListener() { // from class: org.maplibre.android.plugins.annotation.DraggableAnnotationController.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Annotation annotation = DraggableAnnotationController.this.draggedAnnotation;
                androidGesturesManager.onTouchEvent(motionEvent);
                return (DraggableAnnotationController.this.draggedAnnotation == null && annotation == null) ? false : true;
            }
        });
    }

    void addAnnotationManager(AnnotationManager annotationManager) {
        if (annotationManager.getBelowLayerId() != null) {
            this.annotationManagers.add(this.annotationManagers.indexOf(this.annotationManagersById.get(annotationManager.getBelowLayerId())) + 1, annotationManager);
        } else if (annotationManager.getAboveLayerId() != null) {
            this.annotationManagers.add(this.annotationManagers.indexOf(this.annotationManagersById.get(annotationManager.getAboveLayerId())), annotationManager);
        } else {
            this.annotationManagers.add(0, annotationManager);
        }
        this.annotationManagersById.put(annotationManager.getLayerId(), annotationManager);
    }

    void removeAnnotationManager(AnnotationManager annotationManager) {
        this.annotationManagers.remove(annotationManager);
        this.annotationManagersById.remove(annotationManager.getLayerId());
        if (this.annotationManagers.isEmpty()) {
            clearInstance();
        }
    }

    void onAnnotationDeleted(Annotation annotation) {
        Annotation annotation2 = this.draggedAnnotation;
        if (annotation == annotation2) {
            stopDragging(annotation2, this.draggedAnnotationManager);
        }
    }

    boolean onMoveBegin(MoveGestureDetector moveGestureDetector) {
        Annotation annotationQueryMapForFeatures;
        for (AnnotationManager annotationManager : this.annotationManagers) {
            if (moveGestureDetector.getPointersCount() == 1 && (annotationQueryMapForFeatures = annotationManager.queryMapForFeatures(moveGestureDetector.getFocalPoint())) != null && startDragging(annotationQueryMapForFeatures, annotationManager)) {
                return true;
            }
        }
        return false;
    }

    boolean onMove(MoveGestureDetector moveGestureDetector) {
        if (this.draggedAnnotation != null && (moveGestureDetector.getPointersCount() > 1 || !this.draggedAnnotation.isDraggable())) {
            stopDragging(this.draggedAnnotation, this.draggedAnnotationManager);
            return true;
        }
        if (this.draggedAnnotation != null) {
            MoveDistancesObject moveObject = moveGestureDetector.getMoveObject(0);
            PointF pointF = new PointF(moveObject.getCurrentX() - this.touchAreaShiftX, moveObject.getCurrentY() - this.touchAreaShiftY);
            if (pointF.x < 0.0f || pointF.y < 0.0f || pointF.x > this.touchAreaMaxX || pointF.y > this.touchAreaMaxY) {
                stopDragging(this.draggedAnnotation, this.draggedAnnotationManager);
                return true;
            }
            Geometry offsetGeometry = this.draggedAnnotation.getOffsetGeometry(this.maplibreMap.getProjection(), moveObject, this.touchAreaShiftX, this.touchAreaShiftY);
            if (offsetGeometry != null) {
                this.draggedAnnotation.setGeometry(offsetGeometry);
                this.draggedAnnotationManager.updateSource();
                Iterator it = this.draggedAnnotationManager.getDragListeners().iterator();
                while (it.hasNext()) {
                    ((OnAnnotationDragListener) it.next()).onAnnotationDrag(this.draggedAnnotation);
                }
                return true;
            }
        }
        return false;
    }

    void onMoveEnd() {
        stopDragging(this.draggedAnnotation, this.draggedAnnotationManager);
    }

    boolean startDragging(Annotation annotation, AnnotationManager annotationManager) {
        if (!annotation.isDraggable()) {
            return false;
        }
        Iterator it = annotationManager.getDragListeners().iterator();
        while (it.hasNext()) {
            ((OnAnnotationDragListener) it.next()).onAnnotationDragStarted(annotation);
        }
        this.draggedAnnotation = annotation;
        this.draggedAnnotationManager = annotationManager;
        return true;
    }

    void stopDragging(Annotation annotation, AnnotationManager annotationManager) {
        if (annotation != null && annotationManager != null) {
            Iterator it = annotationManager.getDragListeners().iterator();
            while (it.hasNext()) {
                ((OnAnnotationDragListener) it.next()).onAnnotationDragFinished(annotation);
            }
        }
        this.draggedAnnotation = null;
        this.draggedAnnotationManager = null;
    }

    private class AnnotationMoveGestureListener implements MoveGestureDetector.OnMoveGestureListener {
        private AnnotationMoveGestureListener() {
        }

        @Override // org.maplibre.android.gestures.MoveGestureDetector.OnMoveGestureListener
        public boolean onMoveBegin(MoveGestureDetector moveGestureDetector) {
            return DraggableAnnotationController.this.onMoveBegin(moveGestureDetector);
        }

        @Override // org.maplibre.android.gestures.MoveGestureDetector.OnMoveGestureListener
        public boolean onMove(MoveGestureDetector moveGestureDetector, float f, float f2) {
            return DraggableAnnotationController.this.onMove(moveGestureDetector);
        }

        @Override // org.maplibre.android.gestures.MoveGestureDetector.OnMoveGestureListener
        public void onMoveEnd(MoveGestureDetector moveGestureDetector, float f, float f2) {
            DraggableAnnotationController.this.onMoveEnd();
        }
    }
}

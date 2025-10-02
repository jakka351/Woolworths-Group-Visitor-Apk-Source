package org.maplibre.android.maps;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.maplibre.android.gestures.AndroidGesturesManager;
import org.maplibre.android.gestures.MoveGestureDetector;
import org.maplibre.android.gestures.MultiFingerTapGestureDetector;
import org.maplibre.android.gestures.R;
import org.maplibre.android.gestures.RotateGestureDetector;
import org.maplibre.android.gestures.ShoveGestureDetector;
import org.maplibre.android.gestures.StandardGestureDetector;
import org.maplibre.android.gestures.StandardScaleGestureDetector;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.utils.MathUtils;

/* loaded from: classes2.dex */
final class MapGestureDetector {
    private static final String TAG = "MapGestureDetector";
    private final AnnotationManager annotationManager;
    private final CameraChangeDispatcher cameraChangeDispatcher;
    private PointF constantFocalPoint;
    private boolean doubleTapRegistered;
    private AndroidGesturesManager gesturesManager;
    private final Projection projection;
    private Animator rotateAnimator;
    private Animator scaleAnimator;
    private final Transform transform;
    private final UiSettings uiSettings;
    private final CopyOnWriteArrayList<MapLibreMap.OnMapClickListener> onMapClickListenerList = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<MapLibreMap.OnMapLongClickListener> onMapLongClickListenerList = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<MapLibreMap.OnFlingListener> onFlingListenerList = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<MapLibreMap.OnMoveListener> onMoveListenerList = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<MapLibreMap.OnRotateListener> onRotateListenerList = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<MapLibreMap.OnScaleListener> onScaleListenerList = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<MapLibreMap.OnShoveListener> onShoveListenerList = new CopyOnWriteArrayList<>();
    private PointF doubleTapFocalPoint = new PointF();
    private final List<Animator> scheduledAnimators = new ArrayList();
    private Handler animationsTimeoutHandler = new Handler(Looper.getMainLooper());
    private final Runnable cancelAnimatorsRunnable = new Runnable() { // from class: org.maplibre.android.maps.MapGestureDetector.1
        @Override // java.lang.Runnable
        public void run() {
            MapGestureDetector.this.cancelAnimators();
        }
    };

    MapGestureDetector(Context context, Transform transform, Projection projection, UiSettings uiSettings, AnnotationManager annotationManager, CameraChangeDispatcher cameraChangeDispatcher) {
        this.annotationManager = annotationManager;
        this.transform = transform;
        this.projection = projection;
        this.uiSettings = uiSettings;
        this.cameraChangeDispatcher = cameraChangeDispatcher;
        if (context != null) {
            initializeGesturesManager(new AndroidGesturesManager(context), true);
            initializeGestureListeners(context, true);
        }
    }

    private void initializeGestureListeners(Context context, boolean z) {
        if (z) {
            StandardGestureListener standardGestureListener = new StandardGestureListener(context.getResources().getDimension(R.dimen.mapbox_defaultScaleSpanSinceStartThreshold));
            MoveGestureListener moveGestureListener = new MoveGestureListener();
            ScaleGestureListener scaleGestureListener = new ScaleGestureListener(context.getResources().getDimension(org.maplibre.android.R.dimen.maplibre_density_constant), context.getResources().getDimension(org.maplibre.android.R.dimen.maplibre_minimum_scale_speed), context.getResources().getDimension(org.maplibre.android.R.dimen.maplibre_minimum_angled_scale_speed), context.getResources().getDimension(org.maplibre.android.R.dimen.maplibre_minimum_scale_velocity));
            RotateGestureListener rotateGestureListener = new RotateGestureListener(context.getResources().getDimension(org.maplibre.android.R.dimen.maplibre_minimum_scale_span_when_rotating), context.getResources().getDimension(org.maplibre.android.R.dimen.maplibre_density_constant), context.getResources().getDimension(org.maplibre.android.R.dimen.maplibre_angular_velocity_multiplier), context.getResources().getDimension(org.maplibre.android.R.dimen.maplibre_minimum_angular_velocity), context.getResources().getDimension(R.dimen.mapbox_defaultScaleSpanSinceStartThreshold));
            ShoveGestureListener shoveGestureListener = new ShoveGestureListener();
            TapGestureListener tapGestureListener = new TapGestureListener();
            this.gesturesManager.setStandardGestureListener(standardGestureListener);
            this.gesturesManager.setMoveGestureListener(moveGestureListener);
            this.gesturesManager.setStandardScaleGestureListener(scaleGestureListener);
            this.gesturesManager.setRotateGestureListener(rotateGestureListener);
            this.gesturesManager.setShoveGestureListener(shoveGestureListener);
            this.gesturesManager.setMultiFingerTapGestureListener(tapGestureListener);
        }
    }

    private void initializeGesturesManager(AndroidGesturesManager androidGesturesManager, boolean z) {
        if (z) {
            HashSet hashSet = new HashSet();
            hashSet.add(3);
            hashSet.add(1);
            HashSet hashSet2 = new HashSet();
            hashSet2.add(3);
            hashSet2.add(2);
            HashSet hashSet3 = new HashSet();
            hashSet3.add(1);
            hashSet3.add(6);
            androidGesturesManager.setMutuallyExclusiveGestures(hashSet, hashSet2, hashSet3);
        }
        this.gesturesManager = androidGesturesManager;
        androidGesturesManager.getRotateGestureDetector().setAngleThreshold(3.0f);
    }

    void setFocalPoint(PointF pointF) {
        if (pointF == null && this.uiSettings.getFocalPoint() != null) {
            pointF = this.uiSettings.getFocalPoint();
        }
        this.constantFocalPoint = pointF;
    }

    boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        if (motionEvent.getButtonState() != 0 && motionEvent.getButtonState() != 1) {
            return false;
        }
        if (motionEvent.getActionMasked() == 0) {
            cancelAnimators();
            this.transform.setGestureInProgress(true);
        }
        boolean zOnTouchEvent = this.gesturesManager.onTouchEvent(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1) {
            doubleTapFinished();
            this.transform.setGestureInProgress(false);
            if (!this.scheduledAnimators.isEmpty()) {
                this.animationsTimeoutHandler.removeCallbacksAndMessages(null);
                Iterator<Animator> it = this.scheduledAnimators.iterator();
                while (it.hasNext()) {
                    it.next().start();
                }
                this.scheduledAnimators.clear();
            }
        } else if (actionMasked == 3) {
            this.scheduledAnimators.clear();
            this.transform.setGestureInProgress(false);
            doubleTapFinished();
        } else if (actionMasked == 5) {
            doubleTapFinished();
        }
        return zOnTouchEvent;
    }

    void cancelAnimators() {
        this.animationsTimeoutHandler.removeCallbacksAndMessages(null);
        this.scheduledAnimators.clear();
        cancelAnimator(this.scaleAnimator);
        cancelAnimator(this.rotateAnimator);
        dispatchCameraIdle();
    }

    private void cancelAnimator(Animator animator) {
        if (animator == null || !animator.isStarted()) {
            return;
        }
        animator.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleAnimator(Animator animator) {
        this.scheduledAnimators.add(animator);
        this.animationsTimeoutHandler.removeCallbacksAndMessages(null);
        this.animationsTimeoutHandler.postDelayed(this.cancelAnimatorsRunnable, 150L);
    }

    boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 2 || motionEvent.getActionMasked() != 8 || !this.uiSettings.isZoomGesturesEnabled()) {
            return false;
        }
        this.transform.cancelTransitions();
        this.transform.zoomBy(motionEvent.getAxisValue(9), new PointF(motionEvent.getX(), motionEvent.getY()));
        return true;
    }

    private final class StandardGestureListener extends StandardGestureDetector.SimpleStandardOnGestureListener {
        private final float doubleTapMovementThreshold;

        @Override // org.maplibre.android.gestures.StandardGestureDetector.SimpleStandardOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        StandardGestureListener(float f) {
            this.doubleTapMovementThreshold = f;
        }

        @Override // org.maplibre.android.gestures.StandardGestureDetector.SimpleStandardOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            MapGestureDetector.this.transform.cancelTransitions();
            return true;
        }

        @Override // org.maplibre.android.gestures.StandardGestureDetector.SimpleStandardOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
            if (MapGestureDetector.this.annotationManager.onTap(pointF)) {
                return true;
            }
            if (MapGestureDetector.this.uiSettings.isDeselectMarkersOnTap()) {
                MapGestureDetector.this.annotationManager.deselectMarkers();
            }
            MapGestureDetector.this.notifyOnMapClickListeners(pointF);
            return true;
        }

        @Override // org.maplibre.android.gestures.StandardGestureDetector.SimpleStandardOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            if (motionEvent.getActionMasked() == 0) {
                MapGestureDetector.this.doubleTapFocalPoint = new PointF(motionEvent.getX(), motionEvent.getY());
                MapGestureDetector.this.doubleTapStarted();
            }
            if (motionEvent.getActionMasked() == 1) {
                float fAbs = Math.abs(motionEvent.getX() - MapGestureDetector.this.doubleTapFocalPoint.x);
                float fAbs2 = Math.abs(motionEvent.getY() - MapGestureDetector.this.doubleTapFocalPoint.y);
                float f = this.doubleTapMovementThreshold;
                if (fAbs > f || fAbs2 > f || !MapGestureDetector.this.uiSettings.isZoomGesturesEnabled() || !MapGestureDetector.this.uiSettings.isDoubleTapGesturesEnabled()) {
                    return false;
                }
                if (MapGestureDetector.this.constantFocalPoint != null) {
                    MapGestureDetector mapGestureDetector = MapGestureDetector.this;
                    mapGestureDetector.doubleTapFocalPoint = mapGestureDetector.constantFocalPoint;
                }
                MapGestureDetector mapGestureDetector2 = MapGestureDetector.this;
                mapGestureDetector2.zoomInAnimated(mapGestureDetector2.doubleTapFocalPoint, false);
                return true;
            }
            return super.onDoubleTapEvent(motionEvent);
        }

        @Override // org.maplibre.android.gestures.StandardGestureDetector.SimpleStandardOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            MapGestureDetector.this.notifyOnMapLongClickListeners(new PointF(motionEvent.getX(), motionEvent.getY()));
        }

        @Override // org.maplibre.android.gestures.StandardGestureDetector.SimpleStandardOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            double d;
            if (!MapGestureDetector.this.uiSettings.isScrollGesturesEnabled() || !MapGestureDetector.this.uiSettings.isFlingVelocityAnimationEnabled()) {
                return false;
            }
            float pixelRatio = MapGestureDetector.this.uiSettings.getPixelRatio();
            double dHypot = Math.hypot(f / pixelRatio, f2 / pixelRatio);
            if (dHypot < MapGestureDetector.this.uiSettings.getFlingThreshold()) {
                return false;
            }
            double tilt = MapGestureDetector.this.transform.getTilt();
            long flingAnimationBaseTime = (long) (((dHypot / 7.0d) / ((tilt != 0.0d ? tilt / 10.0d : 0.0d) + 1.5d)) + MapGestureDetector.this.uiSettings.getFlingAnimationBaseTime());
            float f3 = flingAnimationBaseTime;
            double d2 = ((f * f3) * 0.28d) / 1000.0d;
            double d3 = ((f3 * f2) * 0.28d) / 1000.0d;
            if (MapGestureDetector.this.uiSettings.isHorizontalScrollGesturesEnabled()) {
                d = d2;
            } else {
                if (Math.abs(Math.toDegrees(Math.atan(d2 / d3))) > 75.0d) {
                    return false;
                }
                d = 0.0d;
            }
            MapGestureDetector.this.transform.cancelTransitions();
            MapGestureDetector.this.notifyOnFlingListeners();
            MapGestureDetector.this.cameraChangeDispatcher.onCameraMoveStarted(1);
            MapGestureDetector.this.transform.moveBy(d, d3, flingAnimationBaseTime);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doubleTapStarted() {
        this.gesturesManager.getMoveGestureDetector().setEnabled(false);
        this.doubleTapRegistered = true;
    }

    private void doubleTapFinished() {
        if (this.doubleTapRegistered) {
            this.gesturesManager.getMoveGestureDetector().setEnabled(true);
            this.doubleTapRegistered = false;
        }
    }

    private final class MoveGestureListener extends MoveGestureDetector.SimpleOnMoveGestureListener {
        private MoveGestureListener() {
        }

        @Override // org.maplibre.android.gestures.MoveGestureDetector.SimpleOnMoveGestureListener, org.maplibre.android.gestures.MoveGestureDetector.OnMoveGestureListener
        public boolean onMoveBegin(MoveGestureDetector moveGestureDetector) {
            if (!MapGestureDetector.this.uiSettings.isScrollGesturesEnabled()) {
                return false;
            }
            MapGestureDetector.this.cancelTransitionsIfRequired();
            MapGestureDetector.this.notifyOnMoveBeginListeners(moveGestureDetector);
            return true;
        }

        @Override // org.maplibre.android.gestures.MoveGestureDetector.SimpleOnMoveGestureListener, org.maplibre.android.gestures.MoveGestureDetector.OnMoveGestureListener
        public boolean onMove(MoveGestureDetector moveGestureDetector, float f, float f2) {
            if (Float.isNaN(f) || Float.isNaN(f2)) {
                Logger.e(MapGestureDetector.TAG, String.format("Could not call onMove with parameters %s,%s", Float.valueOf(f), Float.valueOf(f2)));
            } else if (f != 0.0f || f2 != 0.0f) {
                MapGestureDetector.this.cameraChangeDispatcher.onCameraMoveStarted(1);
                if (!MapGestureDetector.this.uiSettings.isHorizontalScrollGesturesEnabled()) {
                    f = 0.0f;
                }
                MapGestureDetector.this.transform.moveBy(-f, -f2, 0L);
                MapGestureDetector.this.notifyOnMoveListeners(moveGestureDetector);
            }
            return true;
        }

        @Override // org.maplibre.android.gestures.MoveGestureDetector.SimpleOnMoveGestureListener, org.maplibre.android.gestures.MoveGestureDetector.OnMoveGestureListener
        public void onMoveEnd(MoveGestureDetector moveGestureDetector, float f, float f2) {
            MapGestureDetector.this.dispatchCameraIdle();
            MapGestureDetector.this.notifyOnMoveEndListeners(moveGestureDetector);
        }
    }

    private final class ScaleGestureListener extends StandardScaleGestureDetector.SimpleStandardOnScaleGestureListener {
        private final float minimumAngledGestureSpeed;
        private final float minimumGestureSpeed;
        private final float minimumVelocity;
        private boolean quickZoom;
        private final double scaleVelocityRatioThreshold;
        private double screenHeight;
        private float spanSinceLast;
        private double startZoom;

        ScaleGestureListener(double d, float f, float f2, float f3) {
            this.minimumGestureSpeed = f;
            this.minimumAngledGestureSpeed = f2;
            this.minimumVelocity = f3;
            this.scaleVelocityRatioThreshold = d * 0.004d;
        }

        @Override // org.maplibre.android.gestures.StandardScaleGestureDetector.SimpleStandardOnScaleGestureListener, org.maplibre.android.gestures.StandardScaleGestureDetector.StandardOnScaleGestureListener
        public boolean onScaleBegin(StandardScaleGestureDetector standardScaleGestureDetector) {
            this.quickZoom = standardScaleGestureDetector.getPointersCount() == 1;
            if (!MapGestureDetector.this.uiSettings.isZoomGesturesEnabled()) {
                return false;
            }
            if (this.quickZoom) {
                if (!MapGestureDetector.this.uiSettings.isQuickZoomGesturesEnabled()) {
                    return false;
                }
                MapGestureDetector.this.gesturesManager.getMoveGestureDetector().setEnabled(false);
            } else {
                if (standardScaleGestureDetector.getPreviousSpan() <= 0.0f) {
                    return false;
                }
                float currentSpan = standardScaleGestureDetector.getCurrentSpan();
                float previousSpan = standardScaleGestureDetector.getPreviousSpan();
                double eventTime = standardScaleGestureDetector.getCurrentEvent().getEventTime();
                double eventTime2 = standardScaleGestureDetector.getPreviousEvent().getEventTime();
                if (eventTime == eventTime2) {
                    return false;
                }
                double dAbs = Math.abs(currentSpan - previousSpan) / (eventTime - eventTime2);
                if (dAbs < this.minimumGestureSpeed) {
                    return false;
                }
                if (!MapGestureDetector.this.gesturesManager.getRotateGestureDetector().isInProgress()) {
                    if (Math.abs(MapGestureDetector.this.gesturesManager.getRotateGestureDetector().getDeltaSinceLast()) > 0.4d && dAbs < this.minimumAngledGestureSpeed) {
                        return false;
                    }
                    if (MapGestureDetector.this.uiSettings.isDisableRotateWhenScaling()) {
                        MapGestureDetector.this.gesturesManager.getRotateGestureDetector().setEnabled(false);
                    }
                }
            }
            this.screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
            this.startZoom = MapGestureDetector.this.transform.getRawZoom();
            MapGestureDetector.this.cancelTransitionsIfRequired();
            MapGestureDetector.this.notifyOnScaleBeginListeners(standardScaleGestureDetector);
            this.spanSinceLast = Math.abs(standardScaleGestureDetector.getCurrentSpan() - standardScaleGestureDetector.getPreviousSpan());
            return true;
        }

        @Override // org.maplibre.android.gestures.StandardScaleGestureDetector.SimpleStandardOnScaleGestureListener, org.maplibre.android.gestures.StandardScaleGestureDetector.StandardOnScaleGestureListener
        public boolean onScale(StandardScaleGestureDetector standardScaleGestureDetector) {
            MapGestureDetector.this.cameraChangeDispatcher.onCameraMoveStarted(1);
            PointF scaleFocalPoint = getScaleFocalPoint(standardScaleGestureDetector);
            if (this.quickZoom) {
                double dAbs = Math.abs(standardScaleGestureDetector.getCurrentEvent().getY() - MapGestureDetector.this.doubleTapFocalPoint.y);
                boolean z = standardScaleGestureDetector.getCurrentEvent().getY() < MapGestureDetector.this.doubleTapFocalPoint.y;
                double dNormalize = MathUtils.normalize(dAbs, 0.0d, this.screenHeight, 0.0d, 4.0d);
                double d = this.startZoom;
                MapGestureDetector.this.transform.setZoom((z ? d - dNormalize : d + dNormalize) * MapGestureDetector.this.uiSettings.getZoomRate(), scaleFocalPoint);
            } else {
                MapGestureDetector.this.transform.zoomBy((Math.log(standardScaleGestureDetector.getScaleFactor()) / Math.log(1.5707963267948966d)) * 0.6499999761581421d * MapGestureDetector.this.uiSettings.getZoomRate(), scaleFocalPoint);
            }
            MapGestureDetector.this.notifyOnScaleListeners(standardScaleGestureDetector);
            this.spanSinceLast = Math.abs(standardScaleGestureDetector.getCurrentSpan() - standardScaleGestureDetector.getPreviousSpan());
            return true;
        }

        @Override // org.maplibre.android.gestures.StandardScaleGestureDetector.SimpleStandardOnScaleGestureListener, org.maplibre.android.gestures.StandardScaleGestureDetector.StandardOnScaleGestureListener
        public void onScaleEnd(StandardScaleGestureDetector standardScaleGestureDetector, float f, float f2) {
            if (this.quickZoom) {
                MapGestureDetector.this.gesturesManager.getMoveGestureDetector().setEnabled(true);
            } else {
                MapGestureDetector.this.gesturesManager.getRotateGestureDetector().setEnabled(true);
            }
            MapGestureDetector.this.notifyOnScaleEndListeners(standardScaleGestureDetector);
            float fAbs = Math.abs(f) + Math.abs(f2);
            if (!MapGestureDetector.this.uiSettings.isScaleVelocityAnimationEnabled() || fAbs < this.minimumVelocity || this.spanSinceLast / fAbs < this.scaleVelocityRatioThreshold) {
                MapGestureDetector.this.dispatchCameraIdle();
                return;
            }
            double dCalculateScale = calculateScale(fAbs, standardScaleGestureDetector.isScalingOut());
            double rawZoom = MapGestureDetector.this.transform.getRawZoom();
            PointF scaleFocalPoint = getScaleFocalPoint(standardScaleGestureDetector);
            long jLog = (long) ((Math.log(Math.abs(dCalculateScale) + (1.0d / Math.pow(2.718281828459045d, 2.0d))) + 2.0d) * 150.0d);
            MapGestureDetector mapGestureDetector = MapGestureDetector.this;
            mapGestureDetector.scaleAnimator = mapGestureDetector.createScaleAnimator(rawZoom, dCalculateScale, scaleFocalPoint, jLog);
            MapGestureDetector mapGestureDetector2 = MapGestureDetector.this;
            mapGestureDetector2.scheduleAnimator(mapGestureDetector2.scaleAnimator);
        }

        private PointF getScaleFocalPoint(StandardScaleGestureDetector standardScaleGestureDetector) {
            if (MapGestureDetector.this.constantFocalPoint != null) {
                return MapGestureDetector.this.constantFocalPoint;
            }
            if (this.quickZoom) {
                return new PointF(MapGestureDetector.this.uiSettings.getWidth() / 2.0f, MapGestureDetector.this.uiSettings.getHeight() / 2.0f);
            }
            return standardScaleGestureDetector.getFocalPoint();
        }

        private double calculateScale(double d, boolean z) {
            double dClamp = MathUtils.clamp(d * 2.5d * 1.0E-4d, 0.0d, 2.5d);
            return z ? -dClamp : dClamp;
        }
    }

    private final class RotateGestureListener extends RotateGestureDetector.SimpleOnRotateGestureListener {
        private final float angularVelocityMultiplier;
        private final float defaultSpanSinceStartThreshold;
        private final float minimumAngularVelocity;
        private final float minimumScaleSpanWhenRotating;
        private final double rotateVelocityRatioThreshold;

        RotateGestureListener(float f, double d, float f2, float f3, float f4) {
            this.minimumScaleSpanWhenRotating = f;
            this.angularVelocityMultiplier = f2;
            this.minimumAngularVelocity = f3;
            this.rotateVelocityRatioThreshold = d * 2.2000000000000003E-4d;
            this.defaultSpanSinceStartThreshold = f4;
        }

        @Override // org.maplibre.android.gestures.RotateGestureDetector.SimpleOnRotateGestureListener, org.maplibre.android.gestures.RotateGestureDetector.OnRotateGestureListener
        public boolean onRotateBegin(RotateGestureDetector rotateGestureDetector) {
            if (!MapGestureDetector.this.uiSettings.isRotateGesturesEnabled()) {
                return false;
            }
            float fAbs = Math.abs(rotateGestureDetector.getDeltaSinceLast());
            double eventTime = rotateGestureDetector.getCurrentEvent().getEventTime();
            double eventTime2 = rotateGestureDetector.getPreviousEvent().getEventTime();
            if (eventTime == eventTime2) {
                return false;
            }
            double d = fAbs / (eventTime - eventTime2);
            float fAbs2 = Math.abs(rotateGestureDetector.getDeltaSinceStart());
            if (d < 0.04d || ((d > 0.07d && fAbs2 < 5.0f) || ((d > 0.15d && fAbs2 < 7.0f) || (d > 0.5d && fAbs2 < 15.0f)))) {
                return false;
            }
            if (MapGestureDetector.this.uiSettings.isIncreaseScaleThresholdWhenRotating()) {
                MapGestureDetector.this.gesturesManager.getStandardScaleGestureDetector().setSpanSinceStartThreshold(this.minimumScaleSpanWhenRotating);
                MapGestureDetector.this.gesturesManager.getStandardScaleGestureDetector().interrupt();
            }
            MapGestureDetector.this.cancelTransitionsIfRequired();
            MapGestureDetector.this.notifyOnRotateBeginListeners(rotateGestureDetector);
            return true;
        }

        @Override // org.maplibre.android.gestures.RotateGestureDetector.SimpleOnRotateGestureListener, org.maplibre.android.gestures.RotateGestureDetector.OnRotateGestureListener
        public boolean onRotate(RotateGestureDetector rotateGestureDetector, float f, float f2) {
            MapGestureDetector.this.cameraChangeDispatcher.onCameraMoveStarted(1);
            double rawBearing = MapGestureDetector.this.transform.getRawBearing() + f;
            PointF rotateFocalPoint = getRotateFocalPoint(rotateGestureDetector);
            MapGestureDetector.this.transform.setBearing(rawBearing, rotateFocalPoint.x, rotateFocalPoint.y);
            MapGestureDetector.this.notifyOnRotateListeners(rotateGestureDetector);
            return true;
        }

        @Override // org.maplibre.android.gestures.RotateGestureDetector.SimpleOnRotateGestureListener, org.maplibre.android.gestures.RotateGestureDetector.OnRotateGestureListener
        public void onRotateEnd(RotateGestureDetector rotateGestureDetector, float f, float f2, float f3) {
            if (MapGestureDetector.this.uiSettings.isIncreaseScaleThresholdWhenRotating()) {
                MapGestureDetector.this.gesturesManager.getStandardScaleGestureDetector().setSpanSinceStartThreshold(this.defaultSpanSinceStartThreshold);
            }
            MapGestureDetector.this.notifyOnRotateEndListeners(rotateGestureDetector);
            float fClamp = MathUtils.clamp(f3 * this.angularVelocityMultiplier, -30.0f, 30.0f);
            double dAbs = Math.abs(rotateGestureDetector.getDeltaSinceLast()) / (Math.abs(f) + Math.abs(f2));
            if (!MapGestureDetector.this.uiSettings.isRotateVelocityAnimationEnabled() || Math.abs(fClamp) < this.minimumAngularVelocity || (MapGestureDetector.this.gesturesManager.getStandardScaleGestureDetector().isInProgress() && dAbs < this.rotateVelocityRatioThreshold)) {
                MapGestureDetector.this.dispatchCameraIdle();
                return;
            }
            MapGestureDetector.this.rotateAnimator = createRotateAnimator(fClamp, (long) ((Math.log(Math.abs(fClamp) + (1.0d / Math.pow(2.718281828459045d, 2.0d))) + 2.0d) * 150.0d), getRotateFocalPoint(rotateGestureDetector));
            MapGestureDetector mapGestureDetector = MapGestureDetector.this;
            mapGestureDetector.scheduleAnimator(mapGestureDetector.rotateAnimator);
        }

        private PointF getRotateFocalPoint(RotateGestureDetector rotateGestureDetector) {
            if (MapGestureDetector.this.constantFocalPoint != null) {
                return MapGestureDetector.this.constantFocalPoint;
            }
            return rotateGestureDetector.getFocalPoint();
        }

        private Animator createRotateAnimator(float f, long j, final PointF pointF) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f, 0.0f);
            valueAnimatorOfFloat.setDuration(j);
            valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
            valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: org.maplibre.android.maps.MapGestureDetector.RotateGestureListener.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    MapGestureDetector.this.transform.setBearing(MapGestureDetector.this.transform.getRawBearing() + ((Float) valueAnimator.getAnimatedValue()).floatValue(), pointF.x, pointF.y, 0L);
                }
            });
            valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: org.maplibre.android.maps.MapGestureDetector.RotateGestureListener.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    MapGestureDetector.this.transform.cancelTransitions();
                    MapGestureDetector.this.cameraChangeDispatcher.onCameraMoveStarted(1);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    MapGestureDetector.this.transform.cancelTransitions();
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    MapGestureDetector.this.dispatchCameraIdle();
                }
            });
            return valueAnimatorOfFloat;
        }
    }

    private final class ShoveGestureListener extends ShoveGestureDetector.SimpleOnShoveGestureListener {
        private ShoveGestureListener() {
        }

        @Override // org.maplibre.android.gestures.ShoveGestureDetector.SimpleOnShoveGestureListener, org.maplibre.android.gestures.ShoveGestureDetector.OnShoveGestureListener
        public boolean onShoveBegin(ShoveGestureDetector shoveGestureDetector) {
            if (!MapGestureDetector.this.uiSettings.isTiltGesturesEnabled()) {
                return false;
            }
            MapGestureDetector.this.cancelTransitionsIfRequired();
            MapGestureDetector.this.gesturesManager.getMoveGestureDetector().setEnabled(false);
            MapGestureDetector.this.notifyOnShoveBeginListeners(shoveGestureDetector);
            return true;
        }

        @Override // org.maplibre.android.gestures.ShoveGestureDetector.SimpleOnShoveGestureListener, org.maplibre.android.gestures.ShoveGestureDetector.OnShoveGestureListener
        public boolean onShove(ShoveGestureDetector shoveGestureDetector, float f, float f2) {
            MapGestureDetector.this.cameraChangeDispatcher.onCameraMoveStarted(1);
            MapGestureDetector.this.transform.setTilt(Double.valueOf(MathUtils.clamp(MapGestureDetector.this.transform.getTilt() - (f * 0.1f), 0.0d, 60.0d)));
            MapGestureDetector.this.notifyOnShoveListeners(shoveGestureDetector);
            return true;
        }

        @Override // org.maplibre.android.gestures.ShoveGestureDetector.SimpleOnShoveGestureListener, org.maplibre.android.gestures.ShoveGestureDetector.OnShoveGestureListener
        public void onShoveEnd(ShoveGestureDetector shoveGestureDetector, float f, float f2) {
            MapGestureDetector.this.dispatchCameraIdle();
            MapGestureDetector.this.gesturesManager.getMoveGestureDetector().setEnabled(true);
            MapGestureDetector.this.notifyOnShoveEndListeners(shoveGestureDetector);
        }
    }

    private final class TapGestureListener implements MultiFingerTapGestureDetector.OnMultiFingerTapGestureListener {
        private TapGestureListener() {
        }

        @Override // org.maplibre.android.gestures.MultiFingerTapGestureDetector.OnMultiFingerTapGestureListener
        public boolean onMultiFingerTap(MultiFingerTapGestureDetector multiFingerTapGestureDetector, int i) {
            PointF focalPoint;
            if (!MapGestureDetector.this.uiSettings.isZoomGesturesEnabled() || i != 2) {
                return false;
            }
            MapGestureDetector.this.transform.cancelTransitions();
            MapGestureDetector.this.cameraChangeDispatcher.onCameraMoveStarted(1);
            if (MapGestureDetector.this.constantFocalPoint != null) {
                focalPoint = MapGestureDetector.this.constantFocalPoint;
            } else {
                focalPoint = multiFingerTapGestureDetector.getFocalPoint();
            }
            MapGestureDetector.this.zoomOutAnimated(focalPoint, false);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animator createScaleAnimator(double d, double d2, final PointF pointF, long j) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat((float) d, (float) (d + d2));
        valueAnimatorOfFloat.setDuration(j);
        valueAnimatorOfFloat.setInterpolator(new DecelerateInterpolator());
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: org.maplibre.android.maps.MapGestureDetector.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                MapGestureDetector.this.transform.setZoom(((Float) valueAnimator.getAnimatedValue()).floatValue(), pointF);
            }
        });
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: org.maplibre.android.maps.MapGestureDetector.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                MapGestureDetector.this.transform.cancelTransitions();
                MapGestureDetector.this.cameraChangeDispatcher.onCameraMoveStarted(1);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                MapGestureDetector.this.transform.cancelTransitions();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                MapGestureDetector.this.dispatchCameraIdle();
            }
        });
        return valueAnimatorOfFloat;
    }

    void zoomInAnimated(PointF pointF, boolean z) {
        zoomAnimated(true, pointF, z);
    }

    void zoomOutAnimated(PointF pointF, boolean z) {
        zoomAnimated(false, pointF, z);
    }

    private void zoomAnimated(boolean z, PointF pointF, boolean z2) {
        cancelAnimator(this.scaleAnimator);
        Animator animatorCreateScaleAnimator = createScaleAnimator(this.transform.getRawZoom(), z ? 1.0d : -1.0d, pointF, 300L);
        this.scaleAnimator = animatorCreateScaleAnimator;
        if (z2) {
            animatorCreateScaleAnimator.start();
        } else {
            scheduleAnimator(animatorCreateScaleAnimator);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchCameraIdle() {
        if (noGesturesInProgress()) {
            this.transform.invalidateCameraPosition();
            this.cameraChangeDispatcher.onCameraIdle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelTransitionsIfRequired() {
        if (noGesturesInProgress()) {
            this.transform.cancelTransitions();
        }
    }

    private boolean noGesturesInProgress() {
        return ((this.uiSettings.isScrollGesturesEnabled() && this.gesturesManager.getMoveGestureDetector().isInProgress()) || (this.uiSettings.isZoomGesturesEnabled() && this.gesturesManager.getStandardScaleGestureDetector().isInProgress()) || ((this.uiSettings.isRotateGesturesEnabled() && this.gesturesManager.getRotateGestureDetector().isInProgress()) || (this.uiSettings.isTiltGesturesEnabled() && this.gesturesManager.getShoveGestureDetector().isInProgress()))) ? false : true;
    }

    void notifyOnMapClickListeners(PointF pointF) {
        Iterator<MapLibreMap.OnMapClickListener> it = this.onMapClickListenerList.iterator();
        while (it.hasNext() && !it.next().onMapClick(this.projection.fromScreenLocation(pointF))) {
        }
    }

    void notifyOnMapLongClickListeners(PointF pointF) {
        Iterator<MapLibreMap.OnMapLongClickListener> it = this.onMapLongClickListenerList.iterator();
        while (it.hasNext() && !it.next().onMapLongClick(this.projection.fromScreenLocation(pointF))) {
        }
    }

    void notifyOnFlingListeners() {
        Iterator<MapLibreMap.OnFlingListener> it = this.onFlingListenerList.iterator();
        while (it.hasNext()) {
            it.next().onFling();
        }
    }

    void notifyOnMoveBeginListeners(MoveGestureDetector moveGestureDetector) {
        Iterator<MapLibreMap.OnMoveListener> it = this.onMoveListenerList.iterator();
        while (it.hasNext()) {
            it.next().onMoveBegin(moveGestureDetector);
        }
    }

    void notifyOnMoveListeners(MoveGestureDetector moveGestureDetector) {
        Iterator<MapLibreMap.OnMoveListener> it = this.onMoveListenerList.iterator();
        while (it.hasNext()) {
            it.next().onMove(moveGestureDetector);
        }
    }

    void notifyOnMoveEndListeners(MoveGestureDetector moveGestureDetector) {
        Iterator<MapLibreMap.OnMoveListener> it = this.onMoveListenerList.iterator();
        while (it.hasNext()) {
            it.next().onMoveEnd(moveGestureDetector);
        }
    }

    void notifyOnRotateBeginListeners(RotateGestureDetector rotateGestureDetector) {
        Iterator<MapLibreMap.OnRotateListener> it = this.onRotateListenerList.iterator();
        while (it.hasNext()) {
            it.next().onRotateBegin(rotateGestureDetector);
        }
    }

    void notifyOnRotateListeners(RotateGestureDetector rotateGestureDetector) {
        Iterator<MapLibreMap.OnRotateListener> it = this.onRotateListenerList.iterator();
        while (it.hasNext()) {
            it.next().onRotate(rotateGestureDetector);
        }
    }

    void notifyOnRotateEndListeners(RotateGestureDetector rotateGestureDetector) {
        Iterator<MapLibreMap.OnRotateListener> it = this.onRotateListenerList.iterator();
        while (it.hasNext()) {
            it.next().onRotateEnd(rotateGestureDetector);
        }
    }

    void notifyOnScaleBeginListeners(StandardScaleGestureDetector standardScaleGestureDetector) {
        Iterator<MapLibreMap.OnScaleListener> it = this.onScaleListenerList.iterator();
        while (it.hasNext()) {
            it.next().onScaleBegin(standardScaleGestureDetector);
        }
    }

    void notifyOnScaleListeners(StandardScaleGestureDetector standardScaleGestureDetector) {
        Iterator<MapLibreMap.OnScaleListener> it = this.onScaleListenerList.iterator();
        while (it.hasNext()) {
            it.next().onScale(standardScaleGestureDetector);
        }
    }

    void notifyOnScaleEndListeners(StandardScaleGestureDetector standardScaleGestureDetector) {
        Iterator<MapLibreMap.OnScaleListener> it = this.onScaleListenerList.iterator();
        while (it.hasNext()) {
            it.next().onScaleEnd(standardScaleGestureDetector);
        }
    }

    void notifyOnShoveBeginListeners(ShoveGestureDetector shoveGestureDetector) {
        Iterator<MapLibreMap.OnShoveListener> it = this.onShoveListenerList.iterator();
        while (it.hasNext()) {
            it.next().onShoveBegin(shoveGestureDetector);
        }
    }

    void notifyOnShoveListeners(ShoveGestureDetector shoveGestureDetector) {
        Iterator<MapLibreMap.OnShoveListener> it = this.onShoveListenerList.iterator();
        while (it.hasNext()) {
            it.next().onShove(shoveGestureDetector);
        }
    }

    void notifyOnShoveEndListeners(ShoveGestureDetector shoveGestureDetector) {
        Iterator<MapLibreMap.OnShoveListener> it = this.onShoveListenerList.iterator();
        while (it.hasNext()) {
            it.next().onShoveEnd(shoveGestureDetector);
        }
    }

    void addOnMapClickListener(MapLibreMap.OnMapClickListener onMapClickListener) {
        this.onMapClickListenerList.add(onMapClickListener);
    }

    void removeOnMapClickListener(MapLibreMap.OnMapClickListener onMapClickListener) {
        this.onMapClickListenerList.remove(onMapClickListener);
    }

    void addOnMapLongClickListener(MapLibreMap.OnMapLongClickListener onMapLongClickListener) {
        this.onMapLongClickListenerList.add(onMapLongClickListener);
    }

    void removeOnMapLongClickListener(MapLibreMap.OnMapLongClickListener onMapLongClickListener) {
        this.onMapLongClickListenerList.remove(onMapLongClickListener);
    }

    void addOnFlingListener(MapLibreMap.OnFlingListener onFlingListener) {
        this.onFlingListenerList.add(onFlingListener);
    }

    void removeOnFlingListener(MapLibreMap.OnFlingListener onFlingListener) {
        this.onFlingListenerList.remove(onFlingListener);
    }

    void addOnMoveListener(MapLibreMap.OnMoveListener onMoveListener) {
        this.onMoveListenerList.add(onMoveListener);
    }

    void removeOnMoveListener(MapLibreMap.OnMoveListener onMoveListener) {
        this.onMoveListenerList.remove(onMoveListener);
    }

    void addOnRotateListener(MapLibreMap.OnRotateListener onRotateListener) {
        this.onRotateListenerList.add(onRotateListener);
    }

    void removeOnRotateListener(MapLibreMap.OnRotateListener onRotateListener) {
        this.onRotateListenerList.remove(onRotateListener);
    }

    void addOnScaleListener(MapLibreMap.OnScaleListener onScaleListener) {
        this.onScaleListenerList.add(onScaleListener);
    }

    void removeOnScaleListener(MapLibreMap.OnScaleListener onScaleListener) {
        this.onScaleListenerList.remove(onScaleListener);
    }

    void addShoveListener(MapLibreMap.OnShoveListener onShoveListener) {
        this.onShoveListenerList.add(onShoveListener);
    }

    void removeShoveListener(MapLibreMap.OnShoveListener onShoveListener) {
        this.onShoveListenerList.remove(onShoveListener);
    }

    AndroidGesturesManager getGesturesManager() {
        return this.gesturesManager;
    }

    void setGesturesManager(Context context, AndroidGesturesManager androidGesturesManager, boolean z, boolean z2) {
        initializeGesturesManager(androidGesturesManager, z2);
        initializeGestureListeners(context, z);
    }
}

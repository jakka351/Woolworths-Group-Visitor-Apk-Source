package org.maplibre.android.gestures;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.RectF;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class MoveGestureDetector extends ProgressiveGesture<OnMoveGestureListener> {
    private static final int MOVE_REQUIRED_POINTERS_COUNT = 1;
    private static final Set<Integer> handledTypes;
    float lastDistanceX;
    float lastDistanceY;
    private final Map<Integer, MoveDistancesObject> moveDistancesObjectMap;
    private float moveThreshold;
    private RectF moveThresholdRect;
    private PointF previousFocalPoint;
    private boolean resetFocal;

    public interface OnMoveGestureListener {
        boolean onMove(MoveGestureDetector moveGestureDetector, float f, float f2);

        boolean onMoveBegin(MoveGestureDetector moveGestureDetector);

        void onMoveEnd(MoveGestureDetector moveGestureDetector, float f, float f2);
    }

    public static class SimpleOnMoveGestureListener implements OnMoveGestureListener {
        @Override // org.maplibre.android.gestures.MoveGestureDetector.OnMoveGestureListener
        public boolean onMove(MoveGestureDetector moveGestureDetector, float f, float f2) {
            return false;
        }

        @Override // org.maplibre.android.gestures.MoveGestureDetector.OnMoveGestureListener
        public boolean onMoveBegin(MoveGestureDetector moveGestureDetector) {
            return true;
        }

        @Override // org.maplibre.android.gestures.MoveGestureDetector.OnMoveGestureListener
        public void onMoveEnd(MoveGestureDetector moveGestureDetector, float f, float f2) {
        }
    }

    @Override // org.maplibre.android.gestures.MultiFingerGesture
    protected int getRequiredPointersCount() {
        return 1;
    }

    static {
        HashSet hashSet = new HashSet();
        handledTypes = hashSet;
        hashSet.add(13);
    }

    public MoveGestureDetector(Context context, AndroidGesturesManager androidGesturesManager) {
        super(context, androidGesturesManager);
        this.moveDistancesObjectMap = new HashMap();
    }

    @Override // org.maplibre.android.gestures.ProgressiveGesture
    protected Set<Integer> provideHandledTypes() {
        return handledTypes;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    @Override // org.maplibre.android.gestures.ProgressiveGesture, org.maplibre.android.gestures.MultiFingerGesture, org.maplibre.android.gestures.BaseGesture
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean analyzeEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getActionMasked()
            r1 = 1
            if (r0 == 0) goto L33
            if (r0 == r1) goto L2d
            r2 = 3
            if (r0 == r2) goto L27
            r2 = 5
            if (r0 == r2) goto L33
            r2 = 6
            if (r0 == r2) goto L13
            goto L5b
        L13:
            r5.resetFocal = r1
            java.util.Map<java.lang.Integer, org.maplibre.android.gestures.MoveDistancesObject> r0 = r5.moveDistancesObjectMap
            int r1 = r6.getActionIndex()
            int r1 = r6.getPointerId(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.remove(r1)
            goto L5b
        L27:
            java.util.Map<java.lang.Integer, org.maplibre.android.gestures.MoveDistancesObject> r0 = r5.moveDistancesObjectMap
            r0.clear()
            goto L5b
        L2d:
            java.util.Map<java.lang.Integer, org.maplibre.android.gestures.MoveDistancesObject> r0 = r5.moveDistancesObjectMap
            r0.clear()
            goto L5b
        L33:
            r5.resetFocal = r1
            int r0 = r6.getActionIndex()
            float r0 = r6.getX(r0)
            int r1 = r6.getActionIndex()
            float r1 = r6.getY(r1)
            java.util.Map<java.lang.Integer, org.maplibre.android.gestures.MoveDistancesObject> r2 = r5.moveDistancesObjectMap
            int r3 = r6.getActionIndex()
            int r3 = r6.getPointerId(r3)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            org.maplibre.android.gestures.MoveDistancesObject r4 = new org.maplibre.android.gestures.MoveDistancesObject
            r4.<init>(r0, r1)
            r2.put(r3, r4)
        L5b:
            boolean r6 = super.analyzeEvent(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.maplibre.android.gestures.MoveGestureDetector.analyzeEvent(android.view.MotionEvent):boolean");
    }

    @Override // org.maplibre.android.gestures.MultiFingerGesture
    protected boolean analyzeMovement() {
        super.analyzeMovement();
        updateMoveDistancesObjects();
        if (isInProgress()) {
            PointF focalPoint = getFocalPoint();
            this.lastDistanceX = this.previousFocalPoint.x - focalPoint.x;
            this.lastDistanceY = this.previousFocalPoint.y - focalPoint.y;
            this.previousFocalPoint = focalPoint;
            if (this.resetFocal) {
                this.resetFocal = false;
                return ((OnMoveGestureListener) this.listener).onMove(this, 0.0f, 0.0f);
            }
            return ((OnMoveGestureListener) this.listener).onMove(this, this.lastDistanceX, this.lastDistanceY);
        }
        if (!canExecute(13) || !((OnMoveGestureListener) this.listener).onMoveBegin(this)) {
            return false;
        }
        gestureStarted();
        this.previousFocalPoint = getFocalPoint();
        this.resetFocal = false;
        return true;
    }

    private void updateMoveDistancesObjects() {
        Iterator<Integer> it = this.pointerIdList.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            this.moveDistancesObjectMap.get(Integer.valueOf(iIntValue)).addNewPosition(getCurrentEvent().getX(getCurrentEvent().findPointerIndex(iIntValue)), getCurrentEvent().getY(getCurrentEvent().findPointerIndex(iIntValue)));
        }
    }

    boolean checkAnyMoveAboveThreshold() {
        Iterator<MoveDistancesObject> it = this.moveDistancesObjectMap.values().iterator();
        if (!it.hasNext()) {
            return false;
        }
        MoveDistancesObject next = it.next();
        boolean z = Math.abs(next.getDistanceXSinceStart()) >= this.moveThreshold || Math.abs(next.getDistanceYSinceStart()) >= this.moveThreshold;
        RectF rectF = this.moveThresholdRect;
        return !(rectF != null && rectF.contains(getFocalPoint().x, getFocalPoint().y)) && z;
    }

    @Override // org.maplibre.android.gestures.MultiFingerGesture, org.maplibre.android.gestures.BaseGesture
    protected boolean canExecute(int i) {
        return super.canExecute(i) && checkAnyMoveAboveThreshold();
    }

    @Override // org.maplibre.android.gestures.MultiFingerGesture
    protected void reset() {
        super.reset();
    }

    @Override // org.maplibre.android.gestures.ProgressiveGesture
    protected void gestureStopped() {
        super.gestureStopped();
        ((OnMoveGestureListener) this.listener).onMoveEnd(this, this.velocityX, this.velocityY);
    }

    public float getMoveThreshold() {
        return this.moveThreshold;
    }

    public void setMoveThreshold(float f) {
        this.moveThreshold = f;
    }

    public RectF getMoveThresholdRect() {
        return this.moveThresholdRect;
    }

    public void setMoveThresholdRect(RectF rectF) {
        this.moveThresholdRect = rectF;
    }

    public void setMoveThresholdResource(int i) {
        setMoveThreshold(this.context.getResources().getDimension(i));
    }

    public float getLastDistanceX() {
        return this.lastDistanceX;
    }

    public float getLastDistanceY() {
        return this.lastDistanceY;
    }

    public MoveDistancesObject getMoveObject(int i) {
        if (!isInProgress() || i < 0 || i >= getPointersCount()) {
            return null;
        }
        return this.moveDistancesObjectMap.get(this.pointerIdList.get(i));
    }
}

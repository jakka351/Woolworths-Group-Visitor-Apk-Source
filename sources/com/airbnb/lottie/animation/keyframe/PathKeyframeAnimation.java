package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

/* loaded from: classes.dex */
public class PathKeyframeAnimation extends KeyframeAnimation<PointF> {
    private final PathMeasure pathMeasure;
    private PathKeyframe pathMeasureKeyframe;
    private final PointF point;
    private final float[] pos;
    private final float[] tangent;

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public /* bridge */ /* synthetic */ Object getValue(Keyframe keyframe, float f) {
        return getValue((Keyframe<PointF>) keyframe, f);
    }

    public PathKeyframeAnimation(List<? extends Keyframe<PointF>> list) {
        super(list);
        this.point = new PointF();
        this.pos = new float[2];
        this.tangent = new float[2];
        this.pathMeasure = new PathMeasure();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public PointF getValue(Keyframe<PointF> keyframe, float f) {
        PointF pointF;
        PathKeyframe pathKeyframe = (PathKeyframe) keyframe;
        Path path = pathKeyframe.getPath();
        if (path == null) {
            return keyframe.startValue;
        }
        if (this.valueCallback != null && (pointF = (PointF) this.valueCallback.getValueInternal(pathKeyframe.startFrame, pathKeyframe.endFrame.floatValue(), (PointF) pathKeyframe.startValue, (PointF) pathKeyframe.endValue, getLinearCurrentKeyframeProgress(), f, getProgress())) != null) {
            return pointF;
        }
        if (this.pathMeasureKeyframe != pathKeyframe) {
            this.pathMeasure.setPath(path, false);
            this.pathMeasureKeyframe = pathKeyframe;
        }
        float length = this.pathMeasure.getLength();
        float f2 = f * length;
        this.pathMeasure.getPosTan(f2, this.pos, this.tangent);
        PointF pointF2 = this.point;
        float[] fArr = this.pos;
        pointF2.set(fArr[0], fArr[1]);
        if (f2 < 0.0f) {
            PointF pointF3 = this.point;
            float[] fArr2 = this.tangent;
            pointF3.offset(fArr2[0] * f2, fArr2[1] * f2);
        } else if (f2 > length) {
            PointF pointF4 = this.point;
            float[] fArr3 = this.tangent;
            float f3 = f2 - length;
            pointF4.offset(fArr3[0] * f3, fArr3[1] * f3);
        }
        return this.point;
    }
}

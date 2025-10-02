package org.maplibre.android.location;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.animation.Interpolator;
import java.util.List;

/* loaded from: classes2.dex */
class MapLibreAnimatorSetProvider {
    private static MapLibreAnimatorSetProvider instance;

    private MapLibreAnimatorSetProvider() {
    }

    static MapLibreAnimatorSetProvider getInstance() {
        if (instance == null) {
            instance = new MapLibreAnimatorSetProvider();
        }
        return instance;
    }

    void startAnimation(List<Animator> list, Interpolator interpolator, long j) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(list);
        animatorSet.setInterpolator(interpolator);
        animatorSet.setDuration(j);
        animatorSet.start();
    }
}

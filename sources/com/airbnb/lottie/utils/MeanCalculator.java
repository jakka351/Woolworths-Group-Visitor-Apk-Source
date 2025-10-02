package com.airbnb.lottie.utils;

/* loaded from: classes.dex */
public class MeanCalculator {

    /* renamed from: n, reason: collision with root package name */
    private int f5n;
    private float sum;

    public void add(float f) {
        float f2 = this.sum + f;
        this.sum = f2;
        int i = this.f5n + 1;
        this.f5n = i;
        if (i == Integer.MAX_VALUE) {
            this.sum = f2 / 2.0f;
            this.f5n = i / 2;
        }
    }

    public float getMean() {
        int i = this.f5n;
        if (i == 0) {
            return 0.0f;
        }
        return this.sum / i;
    }
}

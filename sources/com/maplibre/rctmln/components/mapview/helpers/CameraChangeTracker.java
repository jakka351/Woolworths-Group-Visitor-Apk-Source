package com.maplibre.rctmln.components.mapview.helpers;

/* loaded from: classes3.dex */
public class CameraChangeTracker {
    public static final int DEVELOPER_ANIMATION = 2;
    public static final int EMPTY = -1;
    public static final int SDK_ANIMATION = 3;
    public static final int USER_GESTURE = 1;
    private boolean isAnimating;
    private int reason = -1;

    public void setReason(int i) {
        this.reason = i;
    }

    public void setIsAnimating(boolean z) {
        this.isAnimating = z;
    }

    public boolean isUserInteraction() {
        int i = this.reason;
        return i == 1 || i == 2;
    }

    public boolean isAnimated() {
        int i = this.reason;
        return i == 2 || i == 3;
    }

    public boolean isAnimating() {
        return this.isAnimating;
    }

    public boolean isEmpty() {
        return this.reason == -1;
    }
}

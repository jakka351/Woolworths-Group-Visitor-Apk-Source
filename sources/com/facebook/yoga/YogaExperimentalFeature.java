package com.facebook.yoga;

/* loaded from: classes3.dex */
public enum YogaExperimentalFeature {
    WEB_FLEX_BASIS(0),
    ABSOLUTE_PERCENTAGE_AGAINST_PADDING_EDGE(1);

    private final int mIntValue;

    YogaExperimentalFeature(int i) {
        this.mIntValue = i;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaExperimentalFeature fromInt(int i) {
        if (i == 0) {
            return WEB_FLEX_BASIS;
        }
        if (i == 1) {
            return ABSOLUTE_PERCENTAGE_AGAINST_PADDING_EDGE;
        }
        throw new IllegalArgumentException("Unknown enum value: " + i);
    }
}

package org.maplibre.android.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DoubleCompanionObject;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Extensions.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0012\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\b\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0004¨\u0006\t"}, d2 = {"isNaN", "", "Lkotlin/Double$Companion;", "d", "", "Lkotlin/Float$Companion;", "f", "", "isInfinite", "MapLibreAndroid_drawableRelease"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExtensionsKt {
    public static final boolean isNaN(DoubleCompanionObject doubleCompanionObject, double d) {
        Intrinsics.checkNotNullParameter(doubleCompanionObject, "<this>");
        return Double.isNaN(d);
    }

    public static final boolean isNaN(FloatCompanionObject floatCompanionObject, float f) {
        Intrinsics.checkNotNullParameter(floatCompanionObject, "<this>");
        return Float.isNaN(f);
    }

    public static final boolean isInfinite(DoubleCompanionObject doubleCompanionObject, double d) {
        Intrinsics.checkNotNullParameter(doubleCompanionObject, "<this>");
        return Double.isInfinite(d);
    }

    public static final boolean isInfinite(FloatCompanionObject floatCompanionObject, double d) {
        Intrinsics.checkNotNullParameter(floatCompanionObject, "<this>");
        return Double.isInfinite(d);
    }
}

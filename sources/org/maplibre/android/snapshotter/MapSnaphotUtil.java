package org.maplibre.android.snapshotter;

import android.graphics.BitmapFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapSnaphotUtil.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005¨\u0006\n"}, d2 = {"Lorg/maplibre/android/snapshotter/MapSnaphotUtil;", "", "<init>", "()V", "calculateInSampleSize", "", "options", "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MapSnaphotUtil {
    public static final MapSnaphotUtil INSTANCE = new MapSnaphotUtil();

    private MapSnaphotUtil() {
    }

    public final int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        Intrinsics.checkNotNullParameter(options, "options");
        int i = options.outHeight;
        int i2 = options.outWidth;
        int i3 = 1;
        if (i > reqHeight || i2 > reqWidth) {
            int i4 = i / 2;
            int i5 = i2 / 2;
            while (i4 / i3 >= reqHeight && i5 / i3 >= reqWidth) {
                i3 *= 2;
            }
        }
        return i3;
    }
}

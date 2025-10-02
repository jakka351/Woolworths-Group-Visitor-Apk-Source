package com.site360new;

import kotlin.Metadata;

/* compiled from: LocationHelper.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0002\u001a6\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\t0\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0001¨\u0006\r"}, d2 = {"distance", "", "lat1", "lon1", "lat2", "lon2", "isLocationInPolygon", "", "polygon", "", "coordinates", "Lkotlin/Pair;", "horizontalAccuracy", "app_wooliesRelease"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LocationHelperKt {
    private static final double distance(double d, double d2, double d3, double d4) {
        double d5 = d - d3;
        double d6 = 2;
        return Math.sqrt(Math.pow(d5, d6) + Math.pow(d2 - d4, d6));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean isLocationInPolygon(java.util.List<? extends java.util.List<java.lang.Double>> r24, kotlin.Pair<java.lang.Double, java.lang.Double> r25, double r26) {
        /*
            Method dump skipped, instructions count: 373
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.site360new.LocationHelperKt.isLocationInPolygon(java.util.List, kotlin.Pair, double):boolean");
    }
}

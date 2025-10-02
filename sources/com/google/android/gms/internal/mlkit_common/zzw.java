package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes3.dex */
final class zzw {
    static void zza(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("null key in entry: null=".concat(String.valueOf(String.valueOf(obj2))));
        }
        if (obj2 != null) {
            return;
        }
        throw new NullPointerException("null value in entry: " + obj.toString() + "=null");
    }
}

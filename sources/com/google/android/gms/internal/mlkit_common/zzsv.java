package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes3.dex */
public final class zzsv {
    private static zzsv zza;

    private zzsv() {
    }

    public static synchronized zzsv zza() {
        if (zza == null) {
            zza = new zzsv();
        }
        return zza;
    }

    public static void zzb() {
        zzsu.zza();
    }
}

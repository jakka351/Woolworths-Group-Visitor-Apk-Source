package com.google.android.gms.internal.mlkit_common;

/* compiled from: com.google.mlkit:common@@18.11.0 */
/* loaded from: classes3.dex */
public final class zzss {
    private static zzsr zza;

    public static synchronized zzsh zza(zzsb zzsbVar) {
        if (zza == null) {
            zza = new zzsr(null);
        }
        return (zzsh) zza.get(zzsbVar);
    }

    public static synchronized zzsh zzb(String str) {
        return zza(zzsb.zzd("common").zzd());
    }
}

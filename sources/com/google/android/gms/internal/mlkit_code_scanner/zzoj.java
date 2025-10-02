package com.google.android.gms.internal.mlkit_code_scanner;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zzoj {
    private static zzoi zza;

    public static synchronized zzny zza(zznt zzntVar) {
        if (zza == null) {
            zza = new zzoi(null);
        }
        return (zzny) zza.get(zzntVar);
    }

    public static synchronized zzny zzb(String str) {
        return zza(zznt.zzd("play-services-code-scanner").zzd());
    }
}

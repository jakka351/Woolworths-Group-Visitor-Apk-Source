package com.google.android.gms.internal.mlkit_code_scanner;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zzok {
    private static zzok zza;

    private zzok() {
    }

    public static synchronized zzok zza() {
        if (zza == null) {
            zza = new zzok();
        }
        return zza;
    }
}

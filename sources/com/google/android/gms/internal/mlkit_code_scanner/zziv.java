package com.google.android.gms.internal.mlkit_code_scanner;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zziv {
    private Integer zza;
    private Long zzb;
    private zzka zzc;
    private Boolean zzd;

    public final zziv zza(Boolean bool) {
        this.zzd = bool;
        return this;
    }

    public final zziv zzb(Long l) {
        this.zzb = Long.valueOf(l.longValue() & Long.MAX_VALUE);
        return this;
    }

    public final zziv zzc(zzka zzkaVar) {
        this.zzc = zzkaVar;
        return this;
    }

    public final zziv zzd(Integer num) {
        this.zza = Integer.valueOf(num.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzix zze() {
        return new zzix(this, null);
    }
}

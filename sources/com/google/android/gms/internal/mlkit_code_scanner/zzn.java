package com.google.android.gms.internal.mlkit_code_scanner;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
final class zzn extends zzh {
    private final zzp zza;

    zzn(zzp zzpVar, int i) {
        super(zzpVar.size(), i);
        this.zza = zzpVar;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzh
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}

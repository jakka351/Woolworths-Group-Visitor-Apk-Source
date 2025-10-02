package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzvo implements zzwi {
    private static final zzvu zza = new zzvm();
    private final zzvu zzb;

    public zzvo() {
        zzvu zzvuVar = zza;
        int i = zzwe.zza;
        zzvn zzvnVar = new zzvn(zzup.zza(), zzvuVar);
        byte[] bArr = zzvc.zzb;
        this.zzb = zzvnVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzwi
    public final zzwh zza(Class cls) {
        int i = zzwj.zza;
        if (!zzuw.class.isAssignableFrom(cls)) {
            int i2 = zzwe.zza;
        }
        zzvt zzvtVarZzb = this.zzb.zzb(cls);
        if (zzvtVarZzb.zzb()) {
            int i3 = zzwe.zza;
            return zzwa.zzc(zzwj.zzm(), zzuk.zza(), zzvtVarZzb.zza());
        }
        int i4 = zzwe.zza;
        return zzvz.zzl(cls, zzvtVarZzb, zzwd.zza(), zzvk.zza(), zzwj.zzm(), zzvtVarZzb.zzc() + (-1) != 1 ? zzuk.zza() : null, zzvs.zza());
    }
}

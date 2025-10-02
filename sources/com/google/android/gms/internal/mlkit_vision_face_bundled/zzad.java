package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzad extends zzuw implements zzvx {
    private static final zzad zzb;
    private int zzd;
    private zzag zze;
    private zzag zzf;
    private zzag zzg;
    private zzag zzh;

    static {
        zzad zzadVar = new zzad();
        zzb = zzadVar;
        zzuw.zzF(zzad.class, zzadVar);
    }

    private zzad() {
    }

    public static zzac zza() {
        return (zzac) zzb.zzv();
    }

    static /* synthetic */ void zzc(zzad zzadVar, zzag zzagVar) {
        zzagVar.getClass();
        zzadVar.zze = zzagVar;
        zzadVar.zzd |= 1;
    }

    static /* synthetic */ void zzd(zzad zzadVar, zzag zzagVar) {
        zzagVar.getClass();
        zzadVar.zzf = zzagVar;
        zzadVar.zzd |= 2;
    }

    static /* synthetic */ void zze(zzad zzadVar, zzag zzagVar) {
        zzagVar.getClass();
        zzadVar.zzg = zzagVar;
        zzadVar.zzd |= 4;
    }

    static /* synthetic */ void zzg(zzad zzadVar, zzag zzagVar) {
        zzagVar.getClass();
        zzadVar.zzh = zzagVar;
        zzadVar.zzd |= 8;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw
    protected final Object zzf(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0004\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဉ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzad();
        }
        zzj zzjVar = null;
        if (i2 == 4) {
            return new zzac(zzjVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}

package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzz extends zzuw implements zzvx {
    private static final zzz zzb;
    private int zzd;
    private zzag zze;
    private zzag zzf;
    private zzag zzg;
    private zzag zzh;

    static {
        zzz zzzVar = new zzz();
        zzb = zzzVar;
        zzuw.zzF(zzz.class, zzzVar);
    }

    private zzz() {
    }

    public static zzy zza() {
        return (zzy) zzb.zzv();
    }

    static /* synthetic */ void zzc(zzz zzzVar, zzag zzagVar) {
        zzagVar.getClass();
        zzzVar.zze = zzagVar;
        zzzVar.zzd |= 1;
    }

    static /* synthetic */ void zzd(zzz zzzVar, zzag zzagVar) {
        zzagVar.getClass();
        zzzVar.zzf = zzagVar;
        zzzVar.zzd |= 2;
    }

    static /* synthetic */ void zze(zzz zzzVar, zzag zzagVar) {
        zzagVar.getClass();
        zzzVar.zzg = zzagVar;
        zzzVar.zzd |= 4;
    }

    static /* synthetic */ void zzg(zzz zzzVar, zzag zzagVar) {
        zzagVar.getClass();
        zzzVar.zzh = zzagVar;
        zzzVar.zzd |= 8;
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
            return new zzz();
        }
        zzj zzjVar = null;
        if (i2 == 4) {
            return new zzy(zzjVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}

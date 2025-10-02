package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzl extends zzuw implements zzvx {
    private static final zzl zzb;
    private int zzd;
    private zzag zze;
    private zzag zzf;

    static {
        zzl zzlVar = new zzl();
        zzb = zzlVar;
        zzuw.zzF(zzl.class, zzlVar);
    }

    private zzl() {
    }

    public static zzk zza() {
        return (zzk) zzb.zzv();
    }

    static /* synthetic */ void zzc(zzl zzlVar, zzag zzagVar) {
        zzagVar.getClass();
        zzlVar.zze = zzagVar;
        zzlVar.zzd |= 1;
    }

    static /* synthetic */ void zzd(zzl zzlVar, zzag zzagVar) {
        zzagVar.getClass();
        zzlVar.zzf = zzagVar;
        zzlVar.zzd |= 2;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw
    protected final Object zzf(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0004\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        if (i2 == 3) {
            return new zzl();
        }
        zzj zzjVar = null;
        if (i2 == 4) {
            return new zzk(zzjVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}

package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzq extends zzuw implements zzvx {
    private static final zzq zzb;
    private int zzd;
    private float zze;
    private float zzf;
    private float zzg;

    static {
        zzq zzqVar = new zzq();
        zzb = zzqVar;
        zzuw.zzF(zzq.class, zzqVar);
    }

    private zzq() {
    }

    public final float zza() {
        return this.zze;
    }

    public final float zzb() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw
    protected final Object zzf(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzq();
        }
        zzj zzjVar = null;
        if (i2 == 4) {
            return new zzp(zzjVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}

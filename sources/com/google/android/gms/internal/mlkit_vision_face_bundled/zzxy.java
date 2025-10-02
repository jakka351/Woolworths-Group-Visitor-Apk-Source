package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzxy extends zzuw implements zzvx {
    private static final zzxy zzb;
    private int zzd;
    private float zze;
    private float zzf;
    private float zzg;
    private int zzh = 15000;
    private int zzi;
    private float zzj;

    static {
        zzxy zzxyVar = new zzxy();
        zzb = zzxyVar;
        zzuw.zzF(zzxy.class, zzxyVar);
    }

    private zzxy() {
    }

    public final float zzb() {
        return this.zze;
    }

    public final float zzd() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw
    protected final Object zzf(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ခ\u0000\u0002ခ\u0001\u0003ခ\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006ခ\u0005", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", zzxv.zza, "zzi", zzxx.zza, "zzj"});
        }
        if (i2 == 3) {
            return new zzxy();
        }
        zzxj zzxjVar = null;
        if (i2 == 4) {
            return new zzxu(zzxjVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }

    public final int zzg() {
        int iZza = zzxw.zza(this.zzh);
        if (iZza == 0) {
            return 15001;
        }
        return iZza;
    }
}

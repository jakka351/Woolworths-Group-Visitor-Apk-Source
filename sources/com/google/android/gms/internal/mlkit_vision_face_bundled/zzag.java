package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzag extends zzuw implements zzvx {
    private static final zzag zzb;
    private int zzd;
    private zztu zze = zztu.zzb;
    private String zzf = "";
    private String zzg = "";

    static {
        zzag zzagVar = new zzag();
        zzb = zzagVar;
        zzuw.zzF(zzag.class, zzagVar);
    }

    private zzag() {
    }

    public static zzaf zza() {
        return (zzaf) zzb.zzv();
    }

    static /* synthetic */ void zzc(zzag zzagVar, String str) {
        zzagVar.zzd |= 2;
        zzagVar.zzf = str;
    }

    static /* synthetic */ void zzd(zzag zzagVar, String str) {
        zzagVar.zzd |= 4;
        zzagVar.zzg = str;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw
    protected final Object zzf(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0004\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ည\u0000\u0002ဈ\u0001\u0003ဈ\u0002", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzag();
        }
        zzj zzjVar = null;
        if (i2 == 4) {
            return new zzaf(zzjVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}

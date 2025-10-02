package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzx extends zzuw implements zzvx {
    private static final zzx zzb;
    private int zzd;
    private boolean zzi;
    private boolean zzj;
    private boolean zzm;
    private zzz zzn;
    private zzl zzo;
    private zzad zzp;
    private float zze = 0.1f;
    private int zzf = 1;
    private int zzg = 1;
    private int zzh = 1;
    private float zzk = 45.0f;
    private float zzl = 0.5f;

    static {
        zzx zzxVar = new zzx();
        zzb = zzxVar;
        zzuw.zzF(zzx.class, zzxVar);
    }

    private zzx() {
    }

    public static zzw zza() {
        return (zzw) zzb.zzv();
    }

    static /* synthetic */ void zzc(zzx zzxVar, float f) {
        zzxVar.zzd |= 1;
        zzxVar.zze = f;
    }

    static /* synthetic */ void zzd(zzx zzxVar, boolean z) {
        zzxVar.zzd |= 32;
        zzxVar.zzj = z;
    }

    static /* synthetic */ void zze(zzx zzxVar, boolean z) {
        zzxVar.zzd |= 256;
        zzxVar.zzm = true;
    }

    static /* synthetic */ void zzg(zzx zzxVar, zzz zzzVar) {
        zzzVar.getClass();
        zzxVar.zzn = zzzVar;
        zzxVar.zzd |= 512;
    }

    static /* synthetic */ void zzh(zzx zzxVar, zzl zzlVar) {
        zzlVar.getClass();
        zzxVar.zzo = zzlVar;
        zzxVar.zzd |= 1024;
    }

    static /* synthetic */ void zzi(zzx zzxVar, zzad zzadVar) {
        zzadVar.getClass();
        zzxVar.zzp = zzadVar;
        zzxVar.zzd |= 2048;
    }

    static /* synthetic */ void zzj(zzx zzxVar, boolean z) {
        zzxVar.zzd |= 16;
        zzxVar.zzi = z;
    }

    static /* synthetic */ void zzm(zzx zzxVar, int i) {
        zzxVar.zzf = i - 1;
        zzxVar.zzd |= 2;
    }

    static /* synthetic */ void zzn(zzx zzxVar, int i) {
        zzxVar.zzg = i - 1;
        zzxVar.zzd |= 4;
    }

    static /* synthetic */ void zzo(zzx zzxVar, int i) {
        zzxVar.zzh = i - 1;
        zzxVar.zzd |= 8;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw
    protected final Object zzf(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzC(zzb, "\u0004\f\u0000\u0001\u0001\f\f\u0000\u0000\u0000\u0001ခ\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005ဇ\u0004\u0006ဇ\u0005\u0007ခ\u0006\bခ\u0007\tဇ\b\nဉ\t\u000bဉ\n\fဉ\u000b", new Object[]{"zzd", "zze", "zzf", zzaa.zza, "zzg", zzm.zza, "zzh", zzae.zza, "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp"});
        }
        if (i2 == 3) {
            return new zzx();
        }
        zzj zzjVar = null;
        if (i2 == 4) {
            return new zzw(zzjVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }

    public final int zzk() {
        int iZza = zzn.zza(this.zzg);
        if (iZza == 0) {
            return 2;
        }
        return iZza;
    }

    public final int zzl() {
        int iZza = zzab.zza(this.zzf);
        if (iZza == 0) {
            return 2;
        }
        return iZza;
    }
}

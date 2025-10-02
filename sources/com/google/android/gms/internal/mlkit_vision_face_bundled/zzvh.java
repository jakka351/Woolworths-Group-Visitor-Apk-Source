package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public class zzvh {
    protected volatile zzvw zza;
    private volatile zztu zzb;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzvh)) {
            return false;
        }
        zzvh zzvhVar = (zzvh) obj;
        zzvw zzvwVar = this.zza;
        zzvw zzvwVar2 = zzvhVar.zza;
        if (zzvwVar == null && zzvwVar2 == null) {
            return zzb().equals(zzvhVar.zzb());
        }
        if (zzvwVar != null && zzvwVar2 != null) {
            return zzvwVar.equals(zzvwVar2);
        }
        if (zzvwVar != null) {
            zzvhVar.zzc(zzvwVar.zzq());
            return zzvwVar.equals(zzvhVar.zza);
        }
        zzc(zzvwVar2.zzq());
        return this.zza.equals(zzvwVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzb != null) {
            return ((zztt) this.zzb).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzu();
        }
        return 0;
    }

    public final zztu zzb() {
        if (this.zzb != null) {
            return this.zzb;
        }
        synchronized (this) {
            if (this.zzb != null) {
                return this.zzb;
            }
            if (this.zza == null) {
                this.zzb = zztu.zzb;
            } else {
                this.zzb = this.zza.zzM();
            }
            return this.zzb;
        }
    }

    protected final void zzc(zzvw zzvwVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza != null) {
                return;
            }
            try {
                this.zza = zzvwVar;
                this.zzb = zztu.zzb;
            } catch (zzve unused) {
                this.zza = zzvwVar;
                this.zzb = zztu.zzb;
            }
        }
    }
}

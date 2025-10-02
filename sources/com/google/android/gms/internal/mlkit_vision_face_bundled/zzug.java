package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzug {
    private final Object zza;
    private final int zzb;

    zzug(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzug)) {
            return false;
        }
        zzug zzugVar = (zzug) obj;
        return this.zza == zzugVar.zza && this.zzb == zzugVar.zzb;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * 65535) + this.zzb;
    }
}

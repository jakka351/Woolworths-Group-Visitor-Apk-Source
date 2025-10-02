package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzrv extends zzrz {
    private final String zza;
    private final boolean zzb;
    private final int zzc;

    /* synthetic */ zzrv(String str, boolean z, int i, zzru zzruVar) {
        this.zza = str;
        this.zzb = z;
        this.zzc = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzrz) {
            zzrz zzrzVar = (zzrz) obj;
            if (this.zza.equals(zzrzVar.zzb()) && this.zzb == zzrzVar.zzc() && this.zzc == zzrzVar.zza()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.zza.hashCode() ^ 1000003) * 1000003) ^ (true != this.zzb ? 1237 : 1231)) * 1000003) ^ this.zzc;
    }

    public final String toString() {
        return "MLKitLoggingOptions{libraryName=" + this.zza + ", enableFirelog=" + this.zzb + ", firelogEventType=" + this.zzc + "}";
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzrz
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzrz
    public final String zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzrz
    public final boolean zzc() {
        return this.zzb;
    }
}

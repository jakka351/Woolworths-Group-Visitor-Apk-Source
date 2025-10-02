package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzbh implements zzbm {
    private final int zza;
    private final zzbl zzb;

    zzbh(int i, zzbl zzblVar) {
        this.zza = i;
        this.zzb = zzblVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzbm.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbm)) {
            return false;
        }
        zzbm zzbmVar = (zzbm) obj;
        return this.zza == zzbmVar.zza() && this.zzb.equals(zzbmVar.zzb());
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.zza ^ 14552422) + (this.zzb.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.zza + "intEncoding=" + this.zzb + ')';
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzbm
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzbm
    public final zzbl zzb() {
        return this.zzb;
    }
}

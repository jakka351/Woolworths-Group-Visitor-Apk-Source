package com.google.android.gms.internal.mlkit_code_scanner;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
final class zzac implements zzah {
    private final int zza;
    private final zzag zzb;

    zzac(int i, zzag zzagVar) {
        this.zza = i;
        this.zzb = zzagVar;
    }

    @Override // java.lang.annotation.Annotation
    public final Class annotationType() {
        return zzah.class;
    }

    @Override // java.lang.annotation.Annotation
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzah)) {
            return false;
        }
        zzah zzahVar = (zzah) obj;
        return this.zza == zzahVar.zza() && this.zzb.equals(zzahVar.zzb());
    }

    @Override // java.lang.annotation.Annotation
    public final int hashCode() {
        return (this.zza ^ 14552422) + (this.zzb.hashCode() ^ 2041407134);
    }

    @Override // java.lang.annotation.Annotation
    public final String toString() {
        return "@com.google.firebase.encoders.proto.Protobuf(tag=" + this.zza + "intEncoding=" + this.zzb + ')';
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzah
    public final int zza() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzah
    public final zzag zzb() {
        return this.zzb;
    }
}

package com.google.android.gms.internal.mlkit_code_scanner;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
final class zznp extends zznt {
    private final String zza;
    private final boolean zzb;
    private final int zzc;

    /* synthetic */ zznp(String str, boolean z, int i, zzno zznoVar) {
        this.zza = str;
        this.zzb = z;
        this.zzc = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zznt) {
            zznt zzntVar = (zznt) obj;
            if (this.zza.equals(zzntVar.zzb()) && this.zzb == zzntVar.zzc() && this.zzc == zzntVar.zza()) {
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

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznt
    public final int zza() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznt
    public final String zzb() {
        return this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznt
    public final boolean zzc() {
        return this.zzb;
    }
}

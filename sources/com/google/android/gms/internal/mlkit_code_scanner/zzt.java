package com.google.android.gms.internal.mlkit_code_scanner;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
final class zzt extends zzp {
    static final zzp zza = new zzt(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    zzt(Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzf.zza(i, this.zzc, "index");
        Object obj = this.zzb[i];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzp, com.google.android.gms.internal.mlkit_code_scanner.zzl
    final int zza(Object[] objArr, int i) {
        System.arraycopy(this.zzb, 0, objArr, 0, this.zzc);
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzl
    final int zzb() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzl
    final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzl
    final Object[] zze() {
        return this.zzb;
    }
}

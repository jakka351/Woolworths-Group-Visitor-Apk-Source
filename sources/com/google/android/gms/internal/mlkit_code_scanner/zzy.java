package com.google.android.gms.internal.mlkit_code_scanner;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
final class zzy extends zzr {
    static final zzr zza = new zzy(null, new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    private zzy(@CheckForNull Object obj, Object[] objArr, int i) {
        this.zzb = objArr;
        this.zzc = i;
    }

    static zzy zzg(int i, Object[] objArr, zzq zzqVar) {
        Object obj = objArr[0];
        obj.getClass();
        Object obj2 = objArr[1];
        obj2.getClass();
        zzi.zza(obj, obj2);
        return new zzy(null, objArr, 1);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0007  */
    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzr, java.util.Map
    @javax.annotation.CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object get(@javax.annotation.CheckForNull java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object[] r0 = r4.zzb
            int r1 = r4.zzc
            r2 = 0
            if (r5 != 0) goto L9
        L7:
            r5 = r2
            goto L1d
        L9:
            r3 = 1
            if (r1 != r3) goto L7
            r1 = 0
            r1 = r0[r1]
            r1.getClass()
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L7
            r5 = r0[r3]
            r5.getClass()
        L1d:
            if (r5 != 0) goto L20
            return r2
        L20:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_code_scanner.zzy.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzr
    final zzl zza() {
        return new zzx(this.zzb, 1, this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzr
    final zzs zzd() {
        return new zzv(this, this.zzb, 0, this.zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zzr
    final zzs zze() {
        return new zzw(this, new zzx(this.zzb, 0, this.zzc));
    }
}

package com.google.android.gms.internal.mlkit_code_scanner;

import java.util.AbstractMap;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
final class zzu extends zzp {
    final /* synthetic */ zzv zza;

    zzu(zzv zzvVar) {
        this.zza = zzvVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzf.zza(i, this.zza.zzc, "index");
        zzv zzvVar = this.zza;
        int i2 = i + i;
        Object obj = zzvVar.zzb[i2];
        obj.getClass();
        Object obj2 = zzvVar.zzb[i2 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }
}

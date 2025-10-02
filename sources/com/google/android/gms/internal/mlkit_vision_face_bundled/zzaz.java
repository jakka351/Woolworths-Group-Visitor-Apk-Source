package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.AbstractMap;
import java.util.Objects;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzaz extends zzau {
    final /* synthetic */ zzba zza;

    zzaz(zzba zzbaVar) {
        this.zza = zzbaVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzak.zza(i, this.zza.zzc, "index");
        int i2 = i + i;
        return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(this.zza.zzb[i2]), Objects.requireNonNull(this.zza.zzb[i2 + 1]));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.zzc;
    }
}

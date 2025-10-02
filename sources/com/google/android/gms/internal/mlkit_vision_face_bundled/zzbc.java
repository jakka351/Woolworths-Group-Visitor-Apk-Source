package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Objects;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzbc extends zzau {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc = 1;

    zzbc(Object[] objArr, int i, int i2) {
        this.zza = objArr;
        this.zzb = i;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzak.zza(i, this.zzc, "index");
        return Objects.requireNonNull(this.zza[i + i + this.zzb]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }
}

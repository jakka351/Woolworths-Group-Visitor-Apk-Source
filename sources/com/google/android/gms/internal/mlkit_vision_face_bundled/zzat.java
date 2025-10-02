package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.List;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzat extends zzau {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzau zzc;

    zzat(zzau zzauVar, int i, int i2) {
        this.zzc = zzauVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzak.zza(i, this.zzb, "index");
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzau, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzaq
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzaq
    final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzaq
    @CheckForNull
    final Object[] zze() {
        return this.zzc.zze();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzau
    /* renamed from: zzf */
    public final zzau subList(int i, int i2) {
        zzak.zzc(i, i2, this.zzb);
        int i3 = this.zza;
        return this.zzc.subList(i + i3, i2 + i3);
    }
}

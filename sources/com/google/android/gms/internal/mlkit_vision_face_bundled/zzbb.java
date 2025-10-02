package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzbb extends zzax {
    private final transient zzaw zza;
    private final transient zzau zzb;

    zzbb(zzaw zzawVar, zzau zzauVar) {
        this.zza = zzawVar;
        this.zzb = zzauVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzaq, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzax, com.google.android.gms.internal.mlkit_vision_face_bundled.zzaq, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return this.zzb.listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzaq
    final int zza(Object[] objArr, int i) {
        return this.zzb.zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzax, com.google.android.gms.internal.mlkit_vision_face_bundled.zzaq
    /* renamed from: zzd */
    public final zzbf iterator() {
        return this.zzb.listIterator(0);
    }
}

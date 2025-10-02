package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzba extends zzax {
    private final transient zzaw zza;
    private final transient Object[] zzb;
    private final transient int zzc = 1;

    zzba(zzaw zzawVar, Object[] objArr, int i, int i2) {
        this.zza = zzawVar;
        this.zzb = objArr;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzaq, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zza.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzax, com.google.android.gms.internal.mlkit_vision_face_bundled.zzaq, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return zzf().listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzaq
    final int zza(Object[] objArr, int i) {
        return zzf().zza(objArr, 0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzax, com.google.android.gms.internal.mlkit_vision_face_bundled.zzaq
    /* renamed from: zzd */
    public final zzbf iterator() {
        return zzf().listIterator(0);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzax
    final zzau zzg() {
        return new zzaz(this);
    }
}

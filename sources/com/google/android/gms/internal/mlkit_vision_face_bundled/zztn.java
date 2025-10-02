package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.NoSuchElementException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zztn extends zzto {
    final /* synthetic */ zztu zza;
    private int zzb = 0;
    private final int zzc;

    zztn(zztu zztuVar) {
        this.zza = zztuVar;
        this.zzc = zztuVar.zzd();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zzb < this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zztq
    public final byte zza() {
        int i = this.zzb;
        if (i >= this.zzc) {
            throw new NoSuchElementException();
        }
        this.zzb = i + 1;
        return this.zza.zzb(i);
    }
}

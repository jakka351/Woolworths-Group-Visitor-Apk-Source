package com.google.android.gms.internal.mlkit_vision_face_bundled;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzwn implements Iterator {
    final /* synthetic */ zzwr zza;
    private int zzb = -1;
    private boolean zzc;
    private Iterator zzd;

    /* synthetic */ zzwn(zzwr zzwrVar, zzwm zzwmVar) {
        this.zza = zzwrVar;
    }

    private final Iterator zza() {
        if (this.zzd == null) {
            this.zzd = this.zza.zzc.entrySet().iterator();
        }
        return this.zzd;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i = this.zzb + 1;
        zzwr zzwrVar = this.zza;
        if (i >= zzwrVar.zzb) {
            return !zzwrVar.zzc.isEmpty() && zza().hasNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        this.zzc = true;
        int i = this.zzb + 1;
        this.zzb = i;
        zzwr zzwrVar = this.zza;
        return i < zzwrVar.zzb ? (zzwl) zzwrVar.zza[i] : (Map.Entry) zza().next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.zzc) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzc = false;
        this.zza.zzo();
        int i = this.zzb;
        zzwr zzwrVar = this.zza;
        if (i >= zzwrVar.zzb) {
            zza().remove();
        } else {
            this.zzb = i - 1;
            zzwrVar.zzm(i);
        }
    }
}

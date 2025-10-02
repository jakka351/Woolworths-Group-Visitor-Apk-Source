package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzsa implements zzrx {
    final List zza;

    public zzsa(Context context, zzrz zzrzVar) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzrzVar.zzc()) {
            arrayList.add(new zzsm(context, zzrzVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzrx
    public final void zza(zzrw zzrwVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zzrx) it.next()).zza(zzrwVar);
        }
    }
}

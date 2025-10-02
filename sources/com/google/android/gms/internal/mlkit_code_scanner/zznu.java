package com.google.android.gms.internal.mlkit_code_scanner;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zznu implements zznr {
    final List zza;

    public zznu(Context context, zznt zzntVar) {
        ArrayList arrayList = new ArrayList();
        this.zza = arrayList;
        if (zzntVar.zzc()) {
            arrayList.add(new zzog(context, zzntVar));
        }
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznr
    public final void zza(zznq zznqVar) {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            ((zznr) it.next()).zza(zznqVar);
        }
    }
}

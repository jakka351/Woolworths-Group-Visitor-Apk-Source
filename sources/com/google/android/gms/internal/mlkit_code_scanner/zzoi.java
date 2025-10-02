package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.mlkit.common.sdkinternal.LazyInstanceMap;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
final class zzoi extends LazyInstanceMap {
    private zzoi() {
    }

    @Override // com.google.mlkit.common.sdkinternal.LazyInstanceMap
    protected final /* bridge */ /* synthetic */ Object create(Object obj) {
        zznt zzntVar = (zznt) obj;
        MlKitContext mlKitContext = MlKitContext.getInstance();
        return new zzny(mlKitContext.getApplicationContext(), (SharedPrefManager) mlKitContext.get(SharedPrefManager.class), new zznu(MlKitContext.getInstance().getApplicationContext(), zzntVar), zzntVar.zzb());
    }

    /* synthetic */ zzoi(zzoh zzohVar) {
    }
}

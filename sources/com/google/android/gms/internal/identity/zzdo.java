package com.google.android.gms.internal.identity;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.location.zzz;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes3.dex */
final class zzdo extends zzq {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ zzz zzb;

    zzdo(TaskCompletionSource taskCompletionSource, zzz zzzVar) {
        this.zza = taskCompletionSource;
        this.zzb = zzzVar;
    }

    @Override // com.google.android.gms.internal.identity.zzr
    public final void zzd(zzl zzlVar) {
        TaskUtil.setResultOrApiException(zzlVar.getStatus(), this.zza);
    }

    @Override // com.google.android.gms.internal.identity.zzr
    public final void zze() throws RemoteException {
        this.zzb.zze();
    }
}

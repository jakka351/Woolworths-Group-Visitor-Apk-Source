package com.google.android.gms.internal.identity;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes3.dex */
final /* synthetic */ class zzcm implements Executor {
    static final /* synthetic */ zzcm zza = new zzcm();

    private /* synthetic */ zzcm() {
    }

    @Override // java.util.concurrent.Executor
    public final /* synthetic */ void execute(Runnable runnable) {
        runnable.run();
    }
}

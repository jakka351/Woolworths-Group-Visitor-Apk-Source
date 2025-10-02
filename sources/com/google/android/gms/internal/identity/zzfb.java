package com.google.android.gms.internal.identity;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes3.dex */
enum zzfb implements Executor {
    INSTANCE;

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.run();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "MoreExecutors.directExecutor()";
    }
}

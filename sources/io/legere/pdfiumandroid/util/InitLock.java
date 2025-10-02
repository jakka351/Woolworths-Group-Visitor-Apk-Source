package io.legere.pdfiumandroid.util;

import java.util.concurrent.Semaphore;
import kotlin.Metadata;

/* compiled from: InitLock.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lio/legere/pdfiumandroid/util/InitLock;", "", "()V", "isInitialized", "", "semaphore", "Ljava/util/concurrent/Semaphore;", "markReady", "", "waitForReady", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class InitLock {
    private boolean isInitialized;
    private final Semaphore semaphore = new Semaphore(0);

    public final void markReady() {
        this.isInitialized = true;
        this.semaphore.release();
    }

    public final synchronized void waitForReady() {
        if (!this.isInitialized) {
            this.semaphore.acquire();
        }
    }
}

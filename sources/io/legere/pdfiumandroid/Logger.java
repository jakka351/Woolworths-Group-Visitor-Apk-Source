package io.legere.pdfiumandroid;

import io.sentry.SentryEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Logger.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J$\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0001R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lio/legere/pdfiumandroid/Logger;", "Lio/legere/pdfiumandroid/LoggerInterface;", "()V", SentryEvent.JsonKeys.LOGGER, "d", "", "tag", "", "message", "e", "t", "", "setLogger", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Logger implements LoggerInterface {
    public static final Logger INSTANCE = new Logger();
    private static LoggerInterface logger;

    private Logger() {
    }

    @Override // io.legere.pdfiumandroid.LoggerInterface
    public void d(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        LoggerInterface loggerInterface = logger;
        if (loggerInterface != null) {
            loggerInterface.d(tag, message);
        }
    }

    @Override // io.legere.pdfiumandroid.LoggerInterface
    public void e(String tag, Throwable t, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        LoggerInterface loggerInterface = logger;
        if (loggerInterface != null) {
            loggerInterface.e(tag, t, message);
        }
    }

    public final void setLogger(LoggerInterface logger2) {
        Intrinsics.checkNotNullParameter(logger2, "logger");
        logger = logger2;
    }
}

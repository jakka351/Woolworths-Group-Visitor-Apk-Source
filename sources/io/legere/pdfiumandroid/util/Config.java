package io.legere.pdfiumandroid.util;

import io.legere.pdfiumandroid.DefaultLogger;
import io.legere.pdfiumandroid.LoggerInterface;
import io.sentry.SentryEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Config.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lio/legere/pdfiumandroid/util/Config;", "", SentryEvent.JsonKeys.LOGGER, "Lio/legere/pdfiumandroid/LoggerInterface;", "alreadyClosedBehavior", "Lio/legere/pdfiumandroid/util/AlreadyClosedBehavior;", "(Lio/legere/pdfiumandroid/LoggerInterface;Lio/legere/pdfiumandroid/util/AlreadyClosedBehavior;)V", "getAlreadyClosedBehavior", "()Lio/legere/pdfiumandroid/util/AlreadyClosedBehavior;", "getLogger", "()Lio/legere/pdfiumandroid/LoggerInterface;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class Config {
    private final AlreadyClosedBehavior alreadyClosedBehavior;
    private final LoggerInterface logger;

    public Config() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ Config copy$default(Config config, LoggerInterface loggerInterface, AlreadyClosedBehavior alreadyClosedBehavior, int i, Object obj) {
        if ((i & 1) != 0) {
            loggerInterface = config.logger;
        }
        if ((i & 2) != 0) {
            alreadyClosedBehavior = config.alreadyClosedBehavior;
        }
        return config.copy(loggerInterface, alreadyClosedBehavior);
    }

    /* renamed from: component1, reason: from getter */
    public final LoggerInterface getLogger() {
        return this.logger;
    }

    /* renamed from: component2, reason: from getter */
    public final AlreadyClosedBehavior getAlreadyClosedBehavior() {
        return this.alreadyClosedBehavior;
    }

    public final Config copy(LoggerInterface logger, AlreadyClosedBehavior alreadyClosedBehavior) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(alreadyClosedBehavior, "alreadyClosedBehavior");
        return new Config(logger, alreadyClosedBehavior);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Config)) {
            return false;
        }
        Config config = (Config) other;
        return Intrinsics.areEqual(this.logger, config.logger) && this.alreadyClosedBehavior == config.alreadyClosedBehavior;
    }

    public int hashCode() {
        return (this.logger.hashCode() * 31) + this.alreadyClosedBehavior.hashCode();
    }

    public String toString() {
        return "Config(logger=" + this.logger + ", alreadyClosedBehavior=" + this.alreadyClosedBehavior + ')';
    }

    public Config(LoggerInterface logger, AlreadyClosedBehavior alreadyClosedBehavior) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(alreadyClosedBehavior, "alreadyClosedBehavior");
        this.logger = logger;
        this.alreadyClosedBehavior = alreadyClosedBehavior;
    }

    public /* synthetic */ Config(DefaultLogger defaultLogger, AlreadyClosedBehavior alreadyClosedBehavior, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new DefaultLogger() : defaultLogger, (i & 2) != 0 ? AlreadyClosedBehavior.EXCEPTION : alreadyClosedBehavior);
    }

    public final LoggerInterface getLogger() {
        return this.logger;
    }

    public final AlreadyClosedBehavior getAlreadyClosedBehavior() {
        return this.alreadyClosedBehavior;
    }
}

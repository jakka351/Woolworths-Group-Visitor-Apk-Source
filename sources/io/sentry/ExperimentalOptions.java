package io.sentry;

/* loaded from: classes3.dex */
public final class ExperimentalOptions {
    private SentryReplayOptions sessionReplay = new SentryReplayOptions();

    public SentryReplayOptions getSessionReplay() {
        return this.sessionReplay;
    }

    public void setSessionReplay(SentryReplayOptions sentryReplayOptions) {
        this.sessionReplay = sentryReplayOptions;
    }
}

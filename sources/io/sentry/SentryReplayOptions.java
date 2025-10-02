package io.sentry;

import io.sentry.metrics.MetricsHelper;
import io.sentry.util.SampleRateUtils;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* loaded from: classes3.dex */
public final class SentryReplayOptions {
    private Double errorSampleRate;
    private Double sessionSampleRate;
    private boolean redactAllText = true;
    private boolean redactAllImages = true;
    private Set<String> redactClasses = new CopyOnWriteArraySet();
    private SentryReplayQuality quality = SentryReplayQuality.MEDIUM;
    private int frameRate = 1;
    private long errorReplayDuration = 30000;
    private long sessionSegmentDuration = 5000;
    private long sessionDuration = org.apache.commons.lang3.time.DateUtils.MILLIS_PER_HOUR;

    public enum SentryReplayQuality {
        LOW(0.8f, 50000),
        MEDIUM(1.0f, 75000),
        HIGH(1.0f, MetricsHelper.MAX_TOTAL_WEIGHT);

        public final int bitRate;
        public final float sizeScale;

        SentryReplayQuality(float f, int i) {
            this.sizeScale = f;
            this.bitRate = i;
        }
    }

    public SentryReplayOptions() {
    }

    public SentryReplayOptions(Double d, Double d2) {
        this.sessionSampleRate = d;
        this.errorSampleRate = d2;
    }

    public Double getErrorSampleRate() {
        return this.errorSampleRate;
    }

    public boolean isSessionReplayEnabled() {
        return getSessionSampleRate() != null && getSessionSampleRate().doubleValue() > 0.0d;
    }

    public void setErrorSampleRate(Double d) {
        if (!SampleRateUtils.isValidSampleRate(d)) {
            throw new IllegalArgumentException("The value " + d + " is not valid. Use null to disable or values >= 0.0 and <= 1.0.");
        }
        this.errorSampleRate = d;
    }

    public Double getSessionSampleRate() {
        return this.sessionSampleRate;
    }

    public boolean isSessionReplayForErrorsEnabled() {
        return getErrorSampleRate() != null && getErrorSampleRate().doubleValue() > 0.0d;
    }

    public void setSessionSampleRate(Double d) {
        if (!SampleRateUtils.isValidSampleRate(d)) {
            throw new IllegalArgumentException("The value " + d + " is not valid. Use null to disable or values >= 0.0 and <= 1.0.");
        }
        this.sessionSampleRate = d;
    }

    public boolean getRedactAllText() {
        return this.redactAllText;
    }

    public void setRedactAllText(boolean z) {
        this.redactAllText = z;
    }

    public boolean getRedactAllImages() {
        return this.redactAllImages;
    }

    public void setRedactAllImages(boolean z) {
        this.redactAllImages = z;
    }

    public Set<String> getRedactClasses() {
        return this.redactClasses;
    }

    public void addClassToRedact(String str) {
        this.redactClasses.add(str);
    }

    public SentryReplayQuality getQuality() {
        return this.quality;
    }

    public void setQuality(SentryReplayQuality sentryReplayQuality) {
        this.quality = sentryReplayQuality;
    }

    public int getFrameRate() {
        return this.frameRate;
    }

    public long getErrorReplayDuration() {
        return this.errorReplayDuration;
    }

    public long getSessionSegmentDuration() {
        return this.sessionSegmentDuration;
    }

    public long getSessionDuration() {
        return this.sessionDuration;
    }
}

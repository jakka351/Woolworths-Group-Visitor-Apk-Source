package io.sentry;

import io.sentry.util.Objects;
import java.security.SecureRandom;

/* loaded from: classes3.dex */
final class TracesSampler {
    private static final Double DEFAULT_TRACES_SAMPLE_RATE = Double.valueOf(1.0d);
    private final SentryOptions options;
    private final SecureRandom random;

    public TracesSampler(SentryOptions sentryOptions) {
        this((SentryOptions) Objects.requireNonNull(sentryOptions, "options are required"), new SecureRandom());
    }

    TracesSampler(SentryOptions sentryOptions, SecureRandom secureRandom) {
        this.options = sentryOptions;
        this.random = secureRandom;
    }

    TracesSamplingDecision sample(SamplingContext samplingContext) {
        Double dSample;
        Double dSample2;
        TracesSamplingDecision samplingDecision = samplingContext.getTransactionContext().getSamplingDecision();
        if (samplingDecision != null) {
            return samplingDecision;
        }
        if (this.options.getProfilesSampler() != null) {
            try {
                dSample = this.options.getProfilesSampler().sample(samplingContext);
            } catch (Throwable th) {
                this.options.getLogger().log(SentryLevel.ERROR, "Error in the 'ProfilesSamplerCallback' callback.", th);
            }
        } else {
            dSample = null;
        }
        if (dSample == null) {
            dSample = this.options.getProfilesSampleRate();
        }
        Boolean boolValueOf = Boolean.valueOf(dSample != null && sample(dSample));
        if (this.options.getTracesSampler() != null) {
            try {
                dSample2 = this.options.getTracesSampler().sample(samplingContext);
            } catch (Throwable th2) {
                this.options.getLogger().log(SentryLevel.ERROR, "Error in the 'TracesSamplerCallback' callback.", th2);
                dSample2 = null;
            }
            if (dSample2 != null) {
                return new TracesSamplingDecision(Boolean.valueOf(sample(dSample2)), dSample2, boolValueOf, dSample);
            }
        }
        TracesSamplingDecision parentSamplingDecision = samplingContext.getTransactionContext().getParentSamplingDecision();
        if (parentSamplingDecision != null) {
            return parentSamplingDecision;
        }
        Double tracesSampleRate = this.options.getTracesSampleRate();
        Double d = Boolean.TRUE.equals(this.options.getEnableTracing()) ? DEFAULT_TRACES_SAMPLE_RATE : null;
        if (tracesSampleRate == null) {
            tracesSampleRate = d;
        }
        Double dValueOf = tracesSampleRate == null ? null : Double.valueOf(tracesSampleRate.doubleValue() / Double.valueOf(Math.pow(2.0d, this.options.getBackpressureMonitor().getDownsampleFactor())).doubleValue());
        if (dValueOf != null) {
            return new TracesSamplingDecision(Boolean.valueOf(sample(dValueOf)), dValueOf, boolValueOf, dSample);
        }
        return new TracesSamplingDecision(false, null, false, null);
    }

    private boolean sample(Double d) {
        return d.doubleValue() >= this.random.nextDouble();
    }
}

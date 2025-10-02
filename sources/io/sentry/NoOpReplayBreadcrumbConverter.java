package io.sentry;

import io.sentry.rrweb.RRWebEvent;

/* loaded from: classes3.dex */
public final class NoOpReplayBreadcrumbConverter implements ReplayBreadcrumbConverter {
    private static final NoOpReplayBreadcrumbConverter instance = new NoOpReplayBreadcrumbConverter();

    @Override // io.sentry.ReplayBreadcrumbConverter
    public RRWebEvent convert(Breadcrumb breadcrumb) {
        return null;
    }

    public static NoOpReplayBreadcrumbConverter getInstance() {
        return instance;
    }

    private NoOpReplayBreadcrumbConverter() {
    }
}

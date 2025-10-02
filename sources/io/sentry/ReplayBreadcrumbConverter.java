package io.sentry;

import io.sentry.rrweb.RRWebEvent;

/* loaded from: classes3.dex */
public interface ReplayBreadcrumbConverter {
    RRWebEvent convert(Breadcrumb breadcrumb);
}

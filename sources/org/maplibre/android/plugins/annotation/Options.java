package org.maplibre.android.plugins.annotation;

import org.maplibre.android.plugins.annotation.Annotation;

/* loaded from: classes2.dex */
public abstract class Options<T extends Annotation> {
    abstract T build(long j, AnnotationManager<?, T, ?, ?, ?, ?> annotationManager);
}

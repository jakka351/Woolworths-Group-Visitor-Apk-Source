package org.maplibre.android.maps;

import java.util.List;
import org.maplibre.android.annotations.Annotation;

/* loaded from: classes2.dex */
interface Annotations {
    List<Annotation> obtainAll();

    Annotation obtainBy(long j);

    void removeAll();

    void removeBy(long j);

    void removeBy(List<? extends Annotation> list);

    void removeBy(Annotation annotation);
}

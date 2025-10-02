package org.maplibre.android.maps;

import android.graphics.RectF;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.List;
import org.maplibre.android.annotations.Annotation;

/* loaded from: classes2.dex */
class ShapeAnnotationContainer implements ShapeAnnotations {
    private final LongSparseArray<Annotation> annotations;
    private final NativeMap nativeMapView;

    ShapeAnnotationContainer(NativeMap nativeMap, LongSparseArray<Annotation> longSparseArray) {
        this.nativeMapView = nativeMap;
        this.annotations = longSparseArray;
    }

    @Override // org.maplibre.android.maps.ShapeAnnotations
    public List<Annotation> obtainAllIn(RectF rectF) {
        return getAnnotationsFromIds(this.nativeMapView.queryShapeAnnotations(this.nativeMapView.getDensityDependantRectangle(rectF)));
    }

    private List<Annotation> getAnnotationsFromIds(long[] jArr) {
        ArrayList arrayList = new ArrayList();
        for (long j : jArr) {
            Annotation annotation = this.annotations.get(j);
            if (annotation != null) {
                arrayList.add(annotation);
            }
        }
        return arrayList;
    }
}

package org.maplibre.android.maps;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.List;
import org.maplibre.android.annotations.Annotation;

/* loaded from: classes2.dex */
class AnnotationContainer implements Annotations {
    private final LongSparseArray<Annotation> annotations;
    private final NativeMap nativeMap;

    AnnotationContainer(NativeMap nativeMap, LongSparseArray<Annotation> longSparseArray) {
        this.nativeMap = nativeMap;
        this.annotations = longSparseArray;
    }

    @Override // org.maplibre.android.maps.Annotations
    public Annotation obtainBy(long j) {
        return this.annotations.get(j);
    }

    @Override // org.maplibre.android.maps.Annotations
    public List<Annotation> obtainAll() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.annotations.size(); i++) {
            LongSparseArray<Annotation> longSparseArray = this.annotations;
            arrayList.add(longSparseArray.get(longSparseArray.keyAt(i)));
        }
        return arrayList;
    }

    @Override // org.maplibre.android.maps.Annotations
    public void removeBy(long j) {
        NativeMap nativeMap = this.nativeMap;
        if (nativeMap != null) {
            nativeMap.removeAnnotation(j);
        }
        this.annotations.remove(j);
    }

    @Override // org.maplibre.android.maps.Annotations
    public void removeBy(Annotation annotation) {
        removeBy(annotation.getId());
    }

    @Override // org.maplibre.android.maps.Annotations
    public void removeBy(List<? extends Annotation> list) {
        int size = list.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = list.get(i).getId();
        }
        removeNativeAnnotations(jArr);
        for (int i2 = 0; i2 < size; i2++) {
            this.annotations.remove(jArr[i2]);
        }
    }

    @Override // org.maplibre.android.maps.Annotations
    public void removeAll() {
        int size = this.annotations.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = this.annotations.keyAt(i);
        }
        removeNativeAnnotations(jArr);
        this.annotations.clear();
    }

    private void removeNativeAnnotations(long[] jArr) {
        NativeMap nativeMap = this.nativeMap;
        if (nativeMap != null) {
            nativeMap.removeAnnotations(jArr);
        }
    }
}

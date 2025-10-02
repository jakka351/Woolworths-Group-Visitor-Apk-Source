package org.maplibre.android.maps;

import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.maplibre.android.annotations.Annotation;
import org.maplibre.android.annotations.Polygon;
import org.maplibre.android.annotations.PolygonOptions;

/* loaded from: classes2.dex */
class PolygonContainer implements Polygons {
    private final LongSparseArray<Annotation> annotations;
    private final NativeMap nativeMap;

    PolygonContainer(NativeMap nativeMap, LongSparseArray<Annotation> longSparseArray) {
        this.nativeMap = nativeMap;
        this.annotations = longSparseArray;
    }

    @Override // org.maplibre.android.maps.Polygons
    public Polygon addBy(PolygonOptions polygonOptions, MapLibreMap mapLibreMap) {
        Polygon polygon = polygonOptions.getPolygon();
        if (!polygon.getPoints().isEmpty()) {
            NativeMap nativeMap = this.nativeMap;
            long jAddPolygon = nativeMap != null ? nativeMap.addPolygon(polygon) : 0L;
            polygon.setId(jAddPolygon);
            polygon.setMapLibreMap(mapLibreMap);
            this.annotations.put(jAddPolygon, polygon);
        }
        return polygon;
    }

    @Override // org.maplibre.android.maps.Polygons
    public List<Polygon> addBy(List<PolygonOptions> list, MapLibreMap mapLibreMap) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        if (this.nativeMap != null && size > 0) {
            Iterator<PolygonOptions> it = list.iterator();
            while (it.hasNext()) {
                Polygon polygon = it.next().getPolygon();
                if (!polygon.getPoints().isEmpty()) {
                    arrayList.add(polygon);
                }
            }
            long[] jArrAddPolygons = this.nativeMap.addPolygons(arrayList);
            for (int i = 0; i < jArrAddPolygons.length; i++) {
                Polygon polygon2 = (Polygon) arrayList.get(i);
                polygon2.setMapLibreMap(mapLibreMap);
                polygon2.setId(jArrAddPolygons[i]);
                this.annotations.put(jArrAddPolygons[i], polygon2);
            }
        }
        return arrayList;
    }

    @Override // org.maplibre.android.maps.Polygons
    public void update(Polygon polygon) {
        this.nativeMap.updatePolygon(polygon);
        LongSparseArray<Annotation> longSparseArray = this.annotations;
        longSparseArray.setValueAt(longSparseArray.indexOfKey(polygon.getId()), polygon);
    }

    @Override // org.maplibre.android.maps.Polygons
    public List<Polygon> obtainAll() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.annotations.size(); i++) {
            LongSparseArray<Annotation> longSparseArray = this.annotations;
            Annotation annotation = longSparseArray.get(longSparseArray.keyAt(i));
            if (annotation instanceof Polygon) {
                arrayList.add((Polygon) annotation);
            }
        }
        return arrayList;
    }
}

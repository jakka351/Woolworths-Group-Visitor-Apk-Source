package org.maplibre.android.maps;

import java.util.List;
import org.maplibre.android.annotations.Polygon;
import org.maplibre.android.annotations.PolygonOptions;

/* loaded from: classes2.dex */
interface Polygons {
    List<Polygon> addBy(List<PolygonOptions> list, MapLibreMap mapLibreMap);

    Polygon addBy(PolygonOptions polygonOptions, MapLibreMap mapLibreMap);

    List<Polygon> obtainAll();

    void update(Polygon polygon);
}

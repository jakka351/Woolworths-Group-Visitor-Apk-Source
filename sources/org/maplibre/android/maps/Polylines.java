package org.maplibre.android.maps;

import java.util.List;
import org.maplibre.android.annotations.Polyline;
import org.maplibre.android.annotations.PolylineOptions;

/* loaded from: classes2.dex */
interface Polylines {
    List<Polyline> addBy(List<PolylineOptions> list, MapLibreMap mapLibreMap);

    Polyline addBy(PolylineOptions polylineOptions, MapLibreMap mapLibreMap);

    List<Polyline> obtainAll();

    void update(Polyline polyline);
}

package org.maplibre.android.maps;

import android.graphics.RectF;
import java.util.List;
import org.maplibre.android.annotations.BaseMarkerOptions;
import org.maplibre.android.annotations.Marker;

/* loaded from: classes2.dex */
interface Markers {
    List<Marker> addBy(List<? extends BaseMarkerOptions> list, MapLibreMap mapLibreMap);

    Marker addBy(BaseMarkerOptions baseMarkerOptions, MapLibreMap mapLibreMap);

    List<Marker> obtainAll();

    List<Marker> obtainAllIn(RectF rectF);

    void reload();

    void update(Marker marker, MapLibreMap mapLibreMap);
}

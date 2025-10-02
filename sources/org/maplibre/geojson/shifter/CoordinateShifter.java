package org.maplibre.geojson.shifter;

import java.util.List;
import org.maplibre.geojson.Point;

/* loaded from: classes2.dex */
public interface CoordinateShifter {
    List<Double> shiftLonLat(double d, double d2);

    List<Double> shiftLonLatAlt(double d, double d2, double d3);

    List<Double> unshiftPoint(List<Double> list);

    List<Double> unshiftPoint(Point point);
}

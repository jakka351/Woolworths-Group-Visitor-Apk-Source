package org.maplibre.turf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;
import org.maplibre.geojson.Geometry;
import org.maplibre.geojson.GeometryCollection;
import org.maplibre.geojson.LineString;
import org.maplibre.geojson.MultiLineString;
import org.maplibre.geojson.MultiPoint;
import org.maplibre.geojson.MultiPolygon;
import org.maplibre.geojson.Point;
import org.maplibre.geojson.Polygon;

/* loaded from: classes2.dex */
public final class TurfMeta {
    private TurfMeta() {
    }

    public static List<Point> coordAll(Point point) {
        return coordAll(new ArrayList(), point);
    }

    private static List<Point> coordAll(List<Point> list, Point point) {
        list.add(point);
        return list;
    }

    public static List<Point> coordAll(MultiPoint multiPoint) {
        return coordAll(new ArrayList(), multiPoint);
    }

    private static List<Point> coordAll(List<Point> list, MultiPoint multiPoint) {
        list.addAll(multiPoint.coordinates());
        return list;
    }

    public static List<Point> coordAll(LineString lineString) {
        return coordAll(new ArrayList(), lineString);
    }

    private static List<Point> coordAll(List<Point> list, LineString lineString) {
        list.addAll(lineString.coordinates());
        return list;
    }

    public static List<Point> coordAll(Polygon polygon, boolean z) {
        return coordAll(new ArrayList(), polygon, z);
    }

    private static List<Point> coordAll(List<Point> list, Polygon polygon, boolean z) {
        for (int i = 0; i < polygon.coordinates().size(); i++) {
            for (int i2 = 0; i2 < polygon.coordinates().get(i).size() - (z ? 1 : 0); i2++) {
                list.add(polygon.coordinates().get(i).get(i2));
            }
        }
        return list;
    }

    public static List<Point> coordAll(MultiLineString multiLineString) {
        return coordAll(new ArrayList(), multiLineString);
    }

    private static List<Point> coordAll(List<Point> list, MultiLineString multiLineString) {
        for (int i = 0; i < multiLineString.coordinates().size(); i++) {
            list.addAll(multiLineString.coordinates().get(i));
        }
        return list;
    }

    public static List<Point> coordAll(MultiPolygon multiPolygon, boolean z) {
        return coordAll(new ArrayList(), multiPolygon, z);
    }

    private static List<Point> coordAll(List<Point> list, MultiPolygon multiPolygon, boolean z) {
        for (int i = 0; i < multiPolygon.coordinates().size(); i++) {
            for (int i2 = 0; i2 < multiPolygon.coordinates().get(i).size(); i2++) {
                for (int i3 = 0; i3 < multiPolygon.coordinates().get(i).get(i2).size() - (z ? 1 : 0); i3++) {
                    list.add(multiPolygon.coordinates().get(i).get(i2).get(i3));
                }
            }
        }
        return list;
    }

    public static List<Point> coordAll(Feature feature, boolean z) {
        return addCoordAll(new ArrayList(), feature, z);
    }

    public static List<Point> coordAll(FeatureCollection featureCollection, boolean z) {
        ArrayList arrayList = new ArrayList();
        Iterator<Feature> it = featureCollection.features().iterator();
        while (it.hasNext()) {
            addCoordAll(arrayList, it.next(), z);
        }
        return arrayList;
    }

    private static List<Point> addCoordAll(List<Point> list, Feature feature, boolean z) {
        return coordAllFromSingleGeometry(list, feature.geometry(), z);
    }

    private static List<Point> coordAllFromSingleGeometry(List<Point> list, Geometry geometry, boolean z) {
        if (geometry instanceof Point) {
            list.add((Point) geometry);
        } else if (geometry instanceof MultiPoint) {
            list.addAll(((MultiPoint) geometry).coordinates());
        } else if (geometry instanceof LineString) {
            list.addAll(((LineString) geometry).coordinates());
        } else if (geometry instanceof MultiLineString) {
            coordAll(list, (MultiLineString) geometry);
        } else if (geometry instanceof Polygon) {
            coordAll(list, (Polygon) geometry, z);
        } else if (geometry instanceof MultiPolygon) {
            coordAll(list, (MultiPolygon) geometry, z);
        } else if (geometry instanceof GeometryCollection) {
            Iterator<Geometry> it = ((GeometryCollection) geometry).geometries().iterator();
            while (it.hasNext()) {
                coordAllFromSingleGeometry(list, it.next(), z);
            }
        }
        return list;
    }

    public static Point getCoord(Feature feature) {
        if (feature.geometry() instanceof Point) {
            return (Point) feature.geometry();
        }
        throw new TurfException("A Feature with a Point geometry is required.");
    }
}

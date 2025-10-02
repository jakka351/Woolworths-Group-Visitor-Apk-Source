package org.maplibre.turf;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.maplibre.geojson.BoundingBox;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;
import org.maplibre.geojson.GeoJson;
import org.maplibre.geojson.Geometry;
import org.maplibre.geojson.GeometryCollection;
import org.maplibre.geojson.LineString;
import org.maplibre.geojson.MultiLineString;
import org.maplibre.geojson.MultiPoint;
import org.maplibre.geojson.MultiPolygon;
import org.maplibre.geojson.Point;
import org.maplibre.geojson.Polygon;

/* loaded from: classes2.dex */
public final class TurfMeasurement {
    public static double EARTH_RADIUS = 6378137.0d;

    private static double rad(double d) {
        return (d * 3.141592653589793d) / 180.0d;
    }

    private TurfMeasurement() {
        throw new AssertionError("No Instances.");
    }

    public static double bearing(Point point, Point point2) {
        double dDegreesToRadians = TurfConversion.degreesToRadians(point.longitude());
        double dDegreesToRadians2 = TurfConversion.degreesToRadians(point2.longitude());
        double dDegreesToRadians3 = TurfConversion.degreesToRadians(point.latitude());
        double dDegreesToRadians4 = TurfConversion.degreesToRadians(point2.latitude());
        double d = dDegreesToRadians2 - dDegreesToRadians;
        return TurfConversion.radiansToDegrees(Math.atan2(Math.sin(d) * Math.cos(dDegreesToRadians4), (Math.cos(dDegreesToRadians3) * Math.sin(dDegreesToRadians4)) - ((Math.sin(dDegreesToRadians3) * Math.cos(dDegreesToRadians4)) * Math.cos(d))));
    }

    public static Point destination(Point point, double d, double d2, String str) {
        double dDegreesToRadians = TurfConversion.degreesToRadians(point.longitude());
        double dDegreesToRadians2 = TurfConversion.degreesToRadians(point.latitude());
        double dDegreesToRadians3 = TurfConversion.degreesToRadians(d2);
        double dLengthToRadians = TurfConversion.lengthToRadians(d, str);
        double dAsin = Math.asin((Math.sin(dDegreesToRadians2) * Math.cos(dLengthToRadians)) + (Math.cos(dDegreesToRadians2) * Math.sin(dLengthToRadians) * Math.cos(dDegreesToRadians3)));
        return Point.fromLngLat(TurfConversion.radiansToDegrees(dDegreesToRadians + Math.atan2(Math.sin(dDegreesToRadians3) * Math.sin(dLengthToRadians) * Math.cos(dDegreesToRadians2), Math.cos(dLengthToRadians) - (Math.sin(dDegreesToRadians2) * Math.sin(dAsin)))), TurfConversion.radiansToDegrees(dAsin));
    }

    public static double distance(Point point, Point point2) {
        return distance(point, point2, "kilometers");
    }

    public static double distance(Point point, Point point2, String str) {
        double dPow = Math.pow(Math.sin(TurfConversion.degreesToRadians(point2.latitude() - point.latitude()) / 2.0d), 2.0d) + (Math.pow(Math.sin(TurfConversion.degreesToRadians(point2.longitude() - point.longitude()) / 2.0d), 2.0d) * Math.cos(TurfConversion.degreesToRadians(point.latitude())) * Math.cos(TurfConversion.degreesToRadians(point2.latitude())));
        return TurfConversion.radiansToLength(Math.atan2(Math.sqrt(dPow), Math.sqrt(1.0d - dPow)) * 2.0d, str);
    }

    public static double length(LineString lineString, String str) {
        return length(lineString.coordinates(), str);
    }

    public static double length(MultiLineString multiLineString, String str) {
        Iterator<List<Point>> it = multiLineString.coordinates().iterator();
        double length = 0.0d;
        while (it.hasNext()) {
            length += length(it.next(), str);
        }
        return length;
    }

    public static double length(Polygon polygon, String str) {
        Iterator<List<Point>> it = polygon.coordinates().iterator();
        double length = 0.0d;
        while (it.hasNext()) {
            length += length(it.next(), str);
        }
        return length;
    }

    public static double length(MultiPolygon multiPolygon, String str) {
        Iterator<List<List<Point>>> it = multiPolygon.coordinates().iterator();
        double length = 0.0d;
        while (it.hasNext()) {
            Iterator<List<Point>> it2 = it.next().iterator();
            while (it2.hasNext()) {
                length += length(it2.next(), str);
            }
        }
        return length;
    }

    public static double length(List<Point> list, String str) {
        Point point = list.get(0);
        double dDistance = 0.0d;
        int i = 1;
        while (i < list.size()) {
            Point point2 = list.get(i);
            dDistance += distance(point, point2, str);
            i++;
            point = point2;
        }
        return dDistance;
    }

    public static Point midpoint(Point point, Point point2) {
        return destination(point, distance(point, point2, TurfConstants.UNIT_MILES) / 2.0d, bearing(point, point2), TurfConstants.UNIT_MILES);
    }

    public static Point along(LineString lineString, double d, String str) {
        return along(lineString.coordinates(), d, str);
    }

    public static Point along(List<Point> list, double d, String str) {
        int i = 0;
        double dDistance = 0.0d;
        while (i < list.size() && (d < dDistance || i != list.size() - 1)) {
            if (dDistance >= d) {
                double d2 = d - dDistance;
                if (d2 == 0.0d) {
                    return list.get(i);
                }
                return destination(list.get(i), d2, bearing(list.get(i), list.get(i - 1)) - 180.0d, str);
            }
            Point point = list.get(i);
            i++;
            dDistance += distance(point, list.get(i), str);
        }
        return list.get(list.size() - 1);
    }

    public static double[] bbox(Point point) {
        return bboxCalculator(TurfMeta.coordAll(point));
    }

    public static double[] bbox(LineString lineString) {
        return bboxCalculator(TurfMeta.coordAll(lineString));
    }

    public static double[] bbox(MultiPoint multiPoint) {
        return bboxCalculator(TurfMeta.coordAll(multiPoint));
    }

    public static double[] bbox(Polygon polygon) {
        return bboxCalculator(TurfMeta.coordAll(polygon, false));
    }

    public static double[] bbox(MultiLineString multiLineString) {
        return bboxCalculator(TurfMeta.coordAll(multiLineString));
    }

    public static double[] bbox(MultiPolygon multiPolygon) {
        return bboxCalculator(TurfMeta.coordAll(multiPolygon, false));
    }

    public static double[] bbox(GeoJson geoJson) {
        BoundingBox boundingBoxBbox = geoJson.bbox();
        if (boundingBoxBbox != null) {
            return new double[]{boundingBoxBbox.west(), boundingBoxBbox.south(), boundingBoxBbox.east(), boundingBoxBbox.north()};
        }
        if (geoJson instanceof Geometry) {
            return bbox((Geometry) geoJson);
        }
        if (geoJson instanceof FeatureCollection) {
            return bbox((FeatureCollection) geoJson);
        }
        if (geoJson instanceof Feature) {
            return bbox((Feature) geoJson);
        }
        throw new UnsupportedOperationException("bbox type not supported for GeoJson instance");
    }

    public static double[] bbox(FeatureCollection featureCollection) {
        return bboxCalculator(TurfMeta.coordAll(featureCollection, false));
    }

    public static double[] bbox(Feature feature) {
        return bboxCalculator(TurfMeta.coordAll(feature, false));
    }

    public static double[] bbox(Geometry geometry) {
        if (geometry instanceof Point) {
            return bbox((Point) geometry);
        }
        if (geometry instanceof MultiPoint) {
            return bbox((MultiPoint) geometry);
        }
        if (geometry instanceof LineString) {
            return bbox((LineString) geometry);
        }
        if (geometry instanceof MultiLineString) {
            return bbox((MultiLineString) geometry);
        }
        if (geometry instanceof Polygon) {
            return bbox((Polygon) geometry);
        }
        if (geometry instanceof MultiPolygon) {
            return bbox((MultiPolygon) geometry);
        }
        if (geometry instanceof GeometryCollection) {
            ArrayList arrayList = new ArrayList();
            Iterator<Geometry> it = ((GeometryCollection) geometry).geometries().iterator();
            while (it.hasNext()) {
                double[] dArrBbox = bbox(it.next());
                arrayList.add(Point.fromLngLat(dArrBbox[0], dArrBbox[1]));
                arrayList.add(Point.fromLngLat(dArrBbox[2], dArrBbox[1]));
                arrayList.add(Point.fromLngLat(dArrBbox[2], dArrBbox[3]));
                arrayList.add(Point.fromLngLat(dArrBbox[0], dArrBbox[3]));
            }
            return bbox(MultiPoint.fromLngLats(arrayList));
        }
        throw new RuntimeException("Unknown geometry class: " + geometry.getClass());
    }

    private static double[] bboxCalculator(List<Point> list) {
        double[] dArr = {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY};
        for (Point point : list) {
            if (dArr[0] > point.longitude()) {
                dArr[0] = point.longitude();
            }
            if (dArr[1] > point.latitude()) {
                dArr[1] = point.latitude();
            }
            if (dArr[2] < point.longitude()) {
                dArr[2] = point.longitude();
            }
            if (dArr[3] < point.latitude()) {
                dArr[3] = point.latitude();
            }
        }
        return dArr;
    }

    public static Feature bboxPolygon(BoundingBox boundingBox) {
        return bboxPolygon(boundingBox, (JsonObject) null, (String) null);
    }

    public static Feature bboxPolygon(BoundingBox boundingBox, JsonObject jsonObject, String str) {
        return Feature.fromGeometry(Polygon.fromLngLats((List<List<Point>>) Collections.singletonList(Arrays.asList(Point.fromLngLat(boundingBox.west(), boundingBox.south()), Point.fromLngLat(boundingBox.east(), boundingBox.south()), Point.fromLngLat(boundingBox.east(), boundingBox.north()), Point.fromLngLat(boundingBox.west(), boundingBox.north()), Point.fromLngLat(boundingBox.west(), boundingBox.south())))), jsonObject, str);
    }

    public static Feature bboxPolygon(double[] dArr) {
        return bboxPolygon(dArr, (JsonObject) null, (String) null);
    }

    public static Feature bboxPolygon(double[] dArr, JsonObject jsonObject, String str) {
        return Feature.fromGeometry(Polygon.fromLngLats((List<List<Point>>) Collections.singletonList(Arrays.asList(Point.fromLngLat(dArr[0], dArr[1]), Point.fromLngLat(dArr[2], dArr[1]), Point.fromLngLat(dArr[2], dArr[3]), Point.fromLngLat(dArr[0], dArr[3]), Point.fromLngLat(dArr[0], dArr[1])))), jsonObject, str);
    }

    public static Polygon envelope(GeoJson geoJson) {
        return (Polygon) bboxPolygon(bbox(geoJson)).geometry();
    }

    public static BoundingBox square(BoundingBox boundingBox) {
        if (distance(boundingBox.southwest(), Point.fromLngLat(boundingBox.east(), boundingBox.south())) >= distance(Point.fromLngLat(boundingBox.west(), boundingBox.south()), Point.fromLngLat(boundingBox.west(), boundingBox.north()))) {
            double dSouth = (boundingBox.south() + boundingBox.north()) / 2.0d;
            return BoundingBox.fromLngLats(boundingBox.west(), dSouth - ((boundingBox.east() - boundingBox.west()) / 2.0d), boundingBox.east(), ((boundingBox.east() - boundingBox.west()) / 2.0d) + dSouth);
        }
        double dWest = (boundingBox.west() + boundingBox.east()) / 2.0d;
        return BoundingBox.fromLngLats(dWest - ((boundingBox.north() - boundingBox.south()) / 2.0d), boundingBox.south(), dWest + ((boundingBox.north() - boundingBox.south()) / 2.0d), boundingBox.north());
    }

    public static double area(Feature feature) {
        if (feature.geometry() != null) {
            return area(feature.geometry());
        }
        return 0.0d;
    }

    public static double area(FeatureCollection featureCollection) {
        List<Feature> listFeatures = featureCollection.features();
        double dArea = 0.0d;
        if (listFeatures != null) {
            Iterator<Feature> it = listFeatures.iterator();
            while (it.hasNext()) {
                dArea += area(it.next());
            }
        }
        return dArea;
    }

    public static double area(Geometry geometry) {
        return calculateArea(geometry);
    }

    private static double calculateArea(Geometry geometry) {
        if (geometry instanceof Polygon) {
            return polygonArea(((Polygon) geometry).coordinates());
        }
        double dPolygonArea = 0.0d;
        if (geometry instanceof MultiPolygon) {
            List<List<List<Point>>> listCoordinates = ((MultiPolygon) geometry).coordinates();
            for (int i = 0; i < listCoordinates.size(); i++) {
                dPolygonArea += polygonArea(listCoordinates.get(i));
            }
        }
        return dPolygonArea;
    }

    private static double polygonArea(List<List<Point>> list) {
        double dAbs = 0.0d;
        if (list.size() > 0) {
            dAbs = Math.abs(ringArea(list.get(0))) + 0.0d;
            for (int i = 1; i < list.size(); i++) {
                dAbs -= Math.abs(ringArea(list.get(i)));
            }
        }
        return dAbs;
    }

    private static double ringArea(List<Point> list) {
        int i;
        int i2;
        int size = list.size();
        double dRad = 0.0d;
        if (size <= 2) {
            return 0.0d;
        }
        for (int i3 = 0; i3 < size; i3++) {
            int i4 = size - 2;
            if (i3 == i4) {
                i = size - 1;
                i2 = 0;
            } else {
                i4 = size - 1;
                if (i3 == i4) {
                    i2 = 1;
                    i = 0;
                } else {
                    i = i3 + 1;
                    i2 = i3 + 2;
                    i4 = i3;
                }
            }
            dRad += (rad(list.get(i2).longitude()) - rad(list.get(i4).longitude())) * Math.sin(rad(list.get(i).latitude()));
        }
        double d = EARTH_RADIUS;
        return ((dRad * d) * d) / 2.0d;
    }

    public static Feature center(Feature feature, JsonObject jsonObject, String str) {
        return center(FeatureCollection.fromFeature(feature), jsonObject, str);
    }

    public static Feature center(Feature feature) {
        return center(FeatureCollection.fromFeature(feature), (JsonObject) null, (String) null);
    }

    public static Feature center(FeatureCollection featureCollection, JsonObject jsonObject, String str) {
        double[] dArrBbox = bbox(featureCollection);
        return Feature.fromGeometry(Point.fromLngLat((dArrBbox[0] + dArrBbox[2]) / 2.0d, (dArrBbox[1] + dArrBbox[3]) / 2.0d), jsonObject, str);
    }

    public static Feature center(FeatureCollection featureCollection) {
        return center(featureCollection, (JsonObject) null, (String) null);
    }
}

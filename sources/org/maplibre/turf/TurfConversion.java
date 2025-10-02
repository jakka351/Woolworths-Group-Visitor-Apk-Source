package org.maplibre.turf;

import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;
import org.maplibre.geojson.Geometry;
import org.maplibre.geojson.LineString;
import org.maplibre.geojson.MultiLineString;
import org.maplibre.geojson.MultiPoint;
import org.maplibre.geojson.MultiPolygon;
import org.maplibre.geojson.Point;
import org.maplibre.geojson.Polygon;

/* loaded from: classes2.dex */
public final class TurfConversion {
    private static final Map<String, Double> FACTORS;

    public static double degreesToRadians(double d) {
        return ((d % 360.0d) * 3.141592653589793d) / 180.0d;
    }

    public static double radiansToDegrees(double d) {
        return ((d % 6.283185307179586d) * 180.0d) / 3.141592653589793d;
    }

    static {
        HashMap map = new HashMap();
        FACTORS = map;
        map.put(TurfConstants.UNIT_MILES, Double.valueOf(3960.0d));
        map.put(TurfConstants.UNIT_NAUTICAL_MILES, Double.valueOf(3441.145d));
        map.put(TurfConstants.UNIT_DEGREES, Double.valueOf(57.2957795d));
        map.put(TurfConstants.UNIT_RADIANS, Double.valueOf(1.0d));
        map.put(TurfConstants.UNIT_INCHES, Double.valueOf(2.509056E8d));
        map.put(TurfConstants.UNIT_YARDS, Double.valueOf(6969600.0d));
        Double dValueOf = Double.valueOf(6373000.0d);
        map.put(TurfConstants.UNIT_METERS, dValueOf);
        Double dValueOf2 = Double.valueOf(6.373E8d);
        map.put(TurfConstants.UNIT_CENTIMETERS, dValueOf2);
        Double dValueOf3 = Double.valueOf(6373.0d);
        map.put("kilometers", dValueOf3);
        map.put(TurfConstants.UNIT_FEET, Double.valueOf(2.090879265E7d));
        map.put(TurfConstants.UNIT_CENTIMETRES, dValueOf2);
        map.put(TurfConstants.UNIT_METRES, dValueOf);
        map.put(TurfConstants.UNIT_KILOMETRES, dValueOf3);
    }

    private TurfConversion() {
    }

    public static double lengthToDegrees(double d, String str) {
        return radiansToDegrees(lengthToRadians(d, str));
    }

    public static double radiansToLength(double d) {
        return radiansToLength(d, "kilometers");
    }

    public static double radiansToLength(double d, String str) {
        return d * FACTORS.get(str).doubleValue();
    }

    public static double lengthToRadians(double d) {
        return lengthToRadians(d, "kilometers");
    }

    public static double lengthToRadians(double d, String str) {
        return d / FACTORS.get(str).doubleValue();
    }

    public static double convertLength(double d, String str) {
        return convertLength(d, str, "kilometers");
    }

    public static double convertLength(double d, String str, String str2) {
        if (str2 == null) {
            str2 = "kilometers";
        }
        return radiansToLength(lengthToRadians(d, str), str2);
    }

    public static FeatureCollection explode(FeatureCollection featureCollection) {
        ArrayList arrayList = new ArrayList();
        Iterator<Point> it = TurfMeta.coordAll(featureCollection, true).iterator();
        while (it.hasNext()) {
            arrayList.add(Feature.fromGeometry(it.next()));
        }
        return FeatureCollection.fromFeatures(arrayList);
    }

    public static FeatureCollection explode(Feature feature) {
        ArrayList arrayList = new ArrayList();
        Iterator<Point> it = TurfMeta.coordAll(feature, true).iterator();
        while (it.hasNext()) {
            arrayList.add(Feature.fromGeometry(it.next()));
        }
        return FeatureCollection.fromFeatures(arrayList);
    }

    public static Feature polygonToLine(Feature feature) {
        return polygonToLine(feature, (JsonObject) null);
    }

    public static Feature polygonToLine(Feature feature, JsonObject jsonObject) {
        Geometry geometry = feature.geometry();
        if (geometry instanceof Polygon) {
            Polygon polygon = (Polygon) geometry;
            if (jsonObject == null) {
                jsonObject = feature.type().equals("Feature") ? feature.properties() : new JsonObject();
            }
            return polygonToLine(polygon, jsonObject);
        }
        throw new TurfException("Feature's geometry must be Polygon");
    }

    public static Feature polygonToLine(Polygon polygon) {
        return polygonToLine(polygon, (JsonObject) null);
    }

    public static FeatureCollection polygonToLine(MultiPolygon multiPolygon) {
        return polygonToLine(multiPolygon, (JsonObject) null);
    }

    public static Feature polygonToLine(Polygon polygon, JsonObject jsonObject) {
        return coordsToLine(polygon.coordinates(), jsonObject);
    }

    public static FeatureCollection polygonToLine(MultiPolygon multiPolygon, JsonObject jsonObject) {
        List<List<List<Point>>> listCoordinates = multiPolygon.coordinates();
        ArrayList arrayList = new ArrayList();
        Iterator<List<List<Point>>> it = listCoordinates.iterator();
        while (it.hasNext()) {
            arrayList.add(coordsToLine(it.next(), jsonObject));
        }
        return FeatureCollection.fromFeatures(arrayList);
    }

    public static FeatureCollection multiPolygonToLine(Feature feature) {
        return multiPolygonToLine(feature, null);
    }

    public static FeatureCollection multiPolygonToLine(Feature feature, JsonObject jsonObject) {
        Geometry geometry = feature.geometry();
        if (geometry instanceof MultiPolygon) {
            MultiPolygon multiPolygon = (MultiPolygon) geometry;
            if (jsonObject == null) {
                jsonObject = feature.type().equals("Feature") ? feature.properties() : new JsonObject();
            }
            return polygonToLine(multiPolygon, jsonObject);
        }
        throw new TurfException("Feature's geometry must be MultiPolygon");
    }

    private static Feature coordsToLine(List<List<Point>> list, JsonObject jsonObject) {
        if (list.size() > 1) {
            return Feature.fromGeometry(MultiLineString.fromLngLats(list), jsonObject);
        }
        if (list.size() == 1) {
            return Feature.fromGeometry(LineString.fromLngLats(list.get(0)), jsonObject);
        }
        return null;
    }

    public static FeatureCollection combine(FeatureCollection featureCollection) {
        if (featureCollection.features() == null) {
            throw new TurfException("Your FeatureCollection is null.");
        }
        if (featureCollection.features().size() == 0) {
            throw new TurfException("Your FeatureCollection doesn't have any Feature objects in it.");
        }
        ArrayList arrayList = new ArrayList(0);
        ArrayList arrayList2 = new ArrayList(0);
        ArrayList arrayList3 = new ArrayList(0);
        Iterator<Feature> it = featureCollection.features().iterator();
        while (it.hasNext()) {
            Geometry geometry = it.next().geometry();
            boolean z = geometry instanceof Point;
            if (!z && !(geometry instanceof MultiPoint)) {
                boolean z2 = geometry instanceof LineString;
                if (!z2 && !(geometry instanceof MultiLineString)) {
                    boolean z3 = geometry instanceof Polygon;
                    if (z3 || (geometry instanceof MultiPolygon)) {
                        if (z3) {
                            arrayList3.add((Polygon) geometry);
                        } else {
                            arrayList3.addAll(((MultiPolygon) geometry).polygons());
                        }
                    }
                } else if (z2) {
                    arrayList2.add((LineString) geometry);
                } else {
                    arrayList2.addAll(((MultiLineString) geometry).lineStrings());
                }
            } else if (z) {
                arrayList.add((Point) geometry);
            } else {
                arrayList.addAll(((MultiPoint) geometry).coordinates());
            }
        }
        ArrayList arrayList4 = new ArrayList(0);
        if (!arrayList.isEmpty()) {
            arrayList4.add(Feature.fromGeometry(MultiPoint.fromLngLats(arrayList)));
        }
        if (!arrayList2.isEmpty()) {
            arrayList4.add(Feature.fromGeometry(MultiLineString.fromLineStrings(arrayList2)));
        }
        if (!arrayList3.isEmpty()) {
            arrayList4.add(Feature.fromGeometry(MultiPolygon.fromPolygons(arrayList3)));
        }
        return arrayList4.isEmpty() ? featureCollection : FeatureCollection.fromFeatures(arrayList4);
    }
}

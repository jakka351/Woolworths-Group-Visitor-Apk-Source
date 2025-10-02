package org.maplibre.geojson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.maplibre.geojson.exception.GeoJsonException;
import org.maplibre.geojson.gson.GeoJsonAdapterFactory;

/* loaded from: classes2.dex */
public final class Polygon implements CoordinateContainer<List<List<Point>>> {
    private static final String TYPE = "Polygon";
    private final BoundingBox bbox;
    private final List<List<Point>> coordinates;
    private final String type;

    public static Polygon fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        return (Polygon) gsonBuilder.create().fromJson(str, Polygon.class);
    }

    public static Polygon fromLngLats(List<List<Point>> list) {
        return new Polygon(TYPE, null, list);
    }

    public static Polygon fromLngLats(List<List<Point>> list, BoundingBox boundingBox) {
        return new Polygon(TYPE, boundingBox, list);
    }

    static Polygon fromLngLats(double[][][] dArr) {
        ArrayList arrayList = new ArrayList(dArr.length);
        for (double[][] dArr2 : dArr) {
            ArrayList arrayList2 = new ArrayList(dArr2.length);
            for (double[] dArr3 : dArr2) {
                arrayList2.add(Point.fromLngLat(dArr3));
            }
            arrayList.add(arrayList2);
        }
        return new Polygon(TYPE, null, arrayList);
    }

    public static Polygon fromOuterInner(LineString lineString, LineString... lineStringArr) {
        ensureIsLinearRing(lineString);
        ArrayList arrayList = new ArrayList();
        arrayList.add(lineString.coordinates());
        if (lineStringArr == null) {
            return new Polygon(TYPE, null, arrayList);
        }
        for (LineString lineString2 : lineStringArr) {
            ensureIsLinearRing(lineString2);
            arrayList.add(lineString2.coordinates());
        }
        return new Polygon(TYPE, null, arrayList);
    }

    public static Polygon fromOuterInner(LineString lineString, BoundingBox boundingBox, LineString... lineStringArr) {
        ensureIsLinearRing(lineString);
        ArrayList arrayList = new ArrayList();
        arrayList.add(lineString.coordinates());
        if (lineStringArr == null) {
            return new Polygon(TYPE, boundingBox, arrayList);
        }
        for (LineString lineString2 : lineStringArr) {
            ensureIsLinearRing(lineString2);
            arrayList.add(lineString2.coordinates());
        }
        return new Polygon(TYPE, boundingBox, arrayList);
    }

    public static Polygon fromOuterInner(LineString lineString, List<LineString> list) {
        ensureIsLinearRing(lineString);
        ArrayList arrayList = new ArrayList();
        arrayList.add(lineString.coordinates());
        if (list == null || list.isEmpty()) {
            return new Polygon(TYPE, null, arrayList);
        }
        for (LineString lineString2 : list) {
            ensureIsLinearRing(lineString2);
            arrayList.add(lineString2.coordinates());
        }
        return new Polygon(TYPE, null, arrayList);
    }

    public static Polygon fromOuterInner(LineString lineString, BoundingBox boundingBox, List<LineString> list) {
        ensureIsLinearRing(lineString);
        ArrayList arrayList = new ArrayList();
        arrayList.add(lineString.coordinates());
        if (list == null) {
            return new Polygon(TYPE, boundingBox, arrayList);
        }
        for (LineString lineString2 : list) {
            ensureIsLinearRing(lineString2);
            arrayList.add(lineString2.coordinates());
        }
        return new Polygon(TYPE, boundingBox, arrayList);
    }

    Polygon(String str, BoundingBox boundingBox, List<List<Point>> list) {
        if (str == null) {
            throw new NullPointerException("Null type");
        }
        this.type = str;
        this.bbox = boundingBox;
        if (list == null) {
            throw new NullPointerException("Null coordinates");
        }
        this.coordinates = list;
    }

    public LineString outer() {
        return LineString.fromLngLats(coordinates().get(0));
    }

    public List<LineString> inner() {
        List<List<Point>> listCoordinates = coordinates();
        if (listCoordinates.size() <= 1) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(listCoordinates.size() - 1);
        Iterator<List<Point>> it = listCoordinates.subList(1, listCoordinates.size()).iterator();
        while (it.hasNext()) {
            arrayList.add(LineString.fromLngLats(it.next()));
        }
        return arrayList;
    }

    @Override // org.maplibre.geojson.GeoJson
    public String type() {
        return this.type;
    }

    @Override // org.maplibre.geojson.GeoJson
    public BoundingBox bbox() {
        return this.bbox;
    }

    @Override // org.maplibre.geojson.CoordinateContainer
    public List<List<Point>> coordinates() {
        return this.coordinates;
    }

    @Override // org.maplibre.geojson.GeoJson
    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        return gsonBuilder.create().toJson(this);
    }

    public static TypeAdapter<Polygon> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    private static void ensureIsLinearRing(LineString lineString) {
        if (lineString.coordinates().size() < 4) {
            throw new GeoJsonException("LinearRings need to be made up of 4 or more coordinates.");
        }
        if (!lineString.coordinates().get(0).equals(lineString.coordinates().get(lineString.coordinates().size() - 1))) {
            throw new GeoJsonException("LinearRings require first and last coordinate to be identical.");
        }
    }

    public String toString() {
        return "Polygon{type=" + this.type + ", bbox=" + this.bbox + ", coordinates=" + this.coordinates + "}";
    }

    public boolean equals(Object obj) {
        BoundingBox boundingBox;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Polygon)) {
            return false;
        }
        Polygon polygon = (Polygon) obj;
        return this.type.equals(polygon.type()) && ((boundingBox = this.bbox) != null ? boundingBox.equals(polygon.bbox()) : polygon.bbox() == null) && this.coordinates.equals(polygon.coordinates());
    }

    public int hashCode() {
        int iHashCode = (this.type.hashCode() ^ 1000003) * 1000003;
        BoundingBox boundingBox = this.bbox;
        return ((iHashCode ^ (boundingBox == null ? 0 : boundingBox.hashCode())) * 1000003) ^ this.coordinates.hashCode();
    }

    static final class GsonTypeAdapter extends BaseGeometryTypeAdapter<Polygon, List<List<Point>>> {
        GsonTypeAdapter(Gson gson) {
            super(gson, new ListOfListOfPointCoordinatesTypeAdapter());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Polygon polygon) throws IOException {
            writeCoordinateContainer(jsonWriter, polygon);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Polygon read2(JsonReader jsonReader) throws IOException {
            return (Polygon) readCoordinateContainer(jsonReader);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.maplibre.geojson.BaseGeometryTypeAdapter
        public CoordinateContainer<List<List<Point>>> createCoordinateContainer(String str, BoundingBox boundingBox, List<List<Point>> list) {
            if (str == null) {
                str = Polygon.TYPE;
            }
            return new Polygon(str, boundingBox, list);
        }
    }
}

package org.maplibre.geojson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.maplibre.geojson.gson.GeoJsonAdapterFactory;
import org.maplibre.geojson.utils.PolylineUtils;

/* loaded from: classes2.dex */
public final class LineString implements CoordinateContainer<List<Point>> {
    private static final String TYPE = "LineString";
    private final BoundingBox bbox;
    private final List<Point> coordinates;
    private final String type;

    public static LineString fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        return (LineString) gsonBuilder.create().fromJson(str, LineString.class);
    }

    public static LineString fromLngLats(MultiPoint multiPoint) {
        return new LineString(TYPE, null, multiPoint.coordinates());
    }

    public static LineString fromLngLats(List<Point> list) {
        return new LineString(TYPE, null, list);
    }

    public static LineString fromLngLats(List<Point> list, BoundingBox boundingBox) {
        return new LineString(TYPE, boundingBox, list);
    }

    public static LineString fromLngLats(MultiPoint multiPoint, BoundingBox boundingBox) {
        return new LineString(TYPE, boundingBox, multiPoint.coordinates());
    }

    LineString(String str, BoundingBox boundingBox, List<Point> list) {
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

    static LineString fromLngLats(double[][] dArr) {
        ArrayList arrayList = new ArrayList(dArr.length);
        for (double[] dArr2 : dArr) {
            arrayList.add(Point.fromLngLat(dArr2));
        }
        return fromLngLats(arrayList);
    }

    public static LineString fromPolyline(String str, int i) {
        return fromLngLats(PolylineUtils.decode(str, i), (BoundingBox) null);
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
    public List<Point> coordinates() {
        return this.coordinates;
    }

    @Override // org.maplibre.geojson.GeoJson
    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        return gsonBuilder.create().toJson(this);
    }

    public String toPolyline(int i) {
        return PolylineUtils.encode(coordinates(), i);
    }

    public static TypeAdapter<LineString> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public String toString() {
        return "LineString{type=" + this.type + ", bbox=" + this.bbox + ", coordinates=" + this.coordinates + "}";
    }

    public boolean equals(Object obj) {
        BoundingBox boundingBox;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LineString)) {
            return false;
        }
        LineString lineString = (LineString) obj;
        return this.type.equals(lineString.type()) && ((boundingBox = this.bbox) != null ? boundingBox.equals(lineString.bbox()) : lineString.bbox() == null) && this.coordinates.equals(lineString.coordinates());
    }

    public int hashCode() {
        int iHashCode = (this.type.hashCode() ^ 1000003) * 1000003;
        BoundingBox boundingBox = this.bbox;
        return ((iHashCode ^ (boundingBox == null ? 0 : boundingBox.hashCode())) * 1000003) ^ this.coordinates.hashCode();
    }

    static final class GsonTypeAdapter extends BaseGeometryTypeAdapter<LineString, List<Point>> {
        GsonTypeAdapter(Gson gson) {
            super(gson, new ListOfPointCoordinatesTypeAdapter());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, LineString lineString) throws IOException {
            writeCoordinateContainer(jsonWriter, lineString);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public LineString read2(JsonReader jsonReader) throws IOException {
            return (LineString) readCoordinateContainer(jsonReader);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.maplibre.geojson.BaseGeometryTypeAdapter
        public CoordinateContainer<List<Point>> createCoordinateContainer(String str, BoundingBox boundingBox, List<Point> list) {
            if (str == null) {
                str = LineString.TYPE;
            }
            return new LineString(str, boundingBox, list);
        }
    }
}

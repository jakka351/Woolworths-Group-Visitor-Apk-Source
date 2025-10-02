package org.maplibre.geojson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.maplibre.geojson.gson.GeoJsonAdapterFactory;

/* loaded from: classes2.dex */
public final class MultiLineString implements CoordinateContainer<List<List<Point>>> {
    private static final String TYPE = "MultiLineString";
    private final BoundingBox bbox;
    private final List<List<Point>> coordinates;
    private final String type;

    public static MultiLineString fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        return (MultiLineString) gsonBuilder.create().fromJson(str, MultiLineString.class);
    }

    public static MultiLineString fromLineStrings(List<LineString> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<LineString> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().coordinates());
        }
        return new MultiLineString(TYPE, null, arrayList);
    }

    public static MultiLineString fromLineStrings(List<LineString> list, BoundingBox boundingBox) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<LineString> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().coordinates());
        }
        return new MultiLineString(TYPE, boundingBox, arrayList);
    }

    public static MultiLineString fromLineString(LineString lineString) {
        return new MultiLineString(TYPE, null, Arrays.asList(lineString.coordinates()));
    }

    public static MultiLineString fromLineString(LineString lineString, BoundingBox boundingBox) {
        return new MultiLineString(TYPE, boundingBox, Arrays.asList(lineString.coordinates()));
    }

    public static MultiLineString fromLngLats(List<List<Point>> list) {
        return new MultiLineString(TYPE, null, list);
    }

    public static MultiLineString fromLngLats(List<List<Point>> list, BoundingBox boundingBox) {
        return new MultiLineString(TYPE, boundingBox, list);
    }

    static MultiLineString fromLngLats(double[][][] dArr) {
        ArrayList arrayList = new ArrayList(dArr.length);
        for (int i = 0; i < dArr.length; i++) {
            ArrayList arrayList2 = new ArrayList(dArr[i].length);
            int i2 = 0;
            while (true) {
                double[][] dArr2 = dArr[i];
                if (i2 < dArr2.length) {
                    arrayList2.add(Point.fromLngLat(dArr2[i2]));
                    i2++;
                }
            }
            arrayList.add(arrayList2);
        }
        return new MultiLineString(TYPE, null, arrayList);
    }

    MultiLineString(String str, BoundingBox boundingBox, List<List<Point>> list) {
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

    public List<LineString> lineStrings() {
        List<List<Point>> listCoordinates = coordinates();
        ArrayList arrayList = new ArrayList(listCoordinates.size());
        Iterator<List<Point>> it = listCoordinates.iterator();
        while (it.hasNext()) {
            arrayList.add(LineString.fromLngLats(it.next()));
        }
        return arrayList;
    }

    @Override // org.maplibre.geojson.GeoJson
    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        return gsonBuilder.create().toJson(this);
    }

    public static TypeAdapter<MultiLineString> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public String toString() {
        return "MultiLineString{type=" + this.type + ", bbox=" + this.bbox + ", coordinates=" + this.coordinates + "}";
    }

    public boolean equals(Object obj) {
        BoundingBox boundingBox;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MultiLineString)) {
            return false;
        }
        MultiLineString multiLineString = (MultiLineString) obj;
        return this.type.equals(multiLineString.type()) && ((boundingBox = this.bbox) != null ? boundingBox.equals(multiLineString.bbox()) : multiLineString.bbox() == null) && this.coordinates.equals(multiLineString.coordinates());
    }

    public int hashCode() {
        int iHashCode = (this.type.hashCode() ^ 1000003) * 1000003;
        BoundingBox boundingBox = this.bbox;
        return ((iHashCode ^ (boundingBox == null ? 0 : boundingBox.hashCode())) * 1000003) ^ this.coordinates.hashCode();
    }

    static final class GsonTypeAdapter extends BaseGeometryTypeAdapter<MultiLineString, List<List<Point>>> {
        GsonTypeAdapter(Gson gson) {
            super(gson, new ListOfListOfPointCoordinatesTypeAdapter());
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, MultiLineString multiLineString) throws IOException {
            writeCoordinateContainer(jsonWriter, multiLineString);
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public MultiLineString read2(JsonReader jsonReader) throws IOException {
            return (MultiLineString) readCoordinateContainer(jsonReader);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.maplibre.geojson.BaseGeometryTypeAdapter
        public CoordinateContainer<List<List<Point>>> createCoordinateContainer(String str, BoundingBox boundingBox, List<List<Point>> list) {
            if (str == null) {
                str = MultiLineString.TYPE;
            }
            return new MultiLineString(str, boundingBox, list);
        }
    }
}

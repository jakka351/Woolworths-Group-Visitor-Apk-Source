package org.maplibre.geojson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.maplibre.geojson.gson.GeoJsonAdapterFactory;

/* loaded from: classes2.dex */
public final class GeometryCollection implements Geometry {
    private static final String TYPE = "GeometryCollection";
    private final BoundingBox bbox;
    private final List<Geometry> geometries;
    private final String type;

    public static GeometryCollection fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        gsonBuilder.registerTypeAdapterFactory(GeometryAdapterFactory.create());
        return (GeometryCollection) gsonBuilder.create().fromJson(str, GeometryCollection.class);
    }

    public static GeometryCollection fromGeometries(List<Geometry> list) {
        return new GeometryCollection(TYPE, null, list);
    }

    public static GeometryCollection fromGeometries(List<Geometry> list, BoundingBox boundingBox) {
        return new GeometryCollection(TYPE, boundingBox, list);
    }

    public static GeometryCollection fromGeometry(Geometry geometry) {
        return new GeometryCollection(TYPE, null, Arrays.asList(geometry));
    }

    public static GeometryCollection fromGeometry(Geometry geometry, BoundingBox boundingBox) {
        return new GeometryCollection(TYPE, boundingBox, Arrays.asList(geometry));
    }

    GeometryCollection(String str, BoundingBox boundingBox, List<Geometry> list) {
        if (str == null) {
            throw new NullPointerException("Null type");
        }
        this.type = str;
        this.bbox = boundingBox;
        if (list == null) {
            throw new NullPointerException("Null geometries");
        }
        this.geometries = list;
    }

    @Override // org.maplibre.geojson.GeoJson
    public String type() {
        return this.type;
    }

    @Override // org.maplibre.geojson.GeoJson
    public BoundingBox bbox() {
        return this.bbox;
    }

    public List<Geometry> geometries() {
        return this.geometries;
    }

    @Override // org.maplibre.geojson.GeoJson
    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        gsonBuilder.registerTypeAdapterFactory(GeometryAdapterFactory.create());
        return gsonBuilder.create().toJson(this);
    }

    public static TypeAdapter<GeometryCollection> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public String toString() {
        return "GeometryCollection{type=" + this.type + ", bbox=" + this.bbox + ", geometries=" + this.geometries + "}";
    }

    public boolean equals(Object obj) {
        BoundingBox boundingBox;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GeometryCollection)) {
            return false;
        }
        GeometryCollection geometryCollection = (GeometryCollection) obj;
        return this.type.equals(geometryCollection.type()) && ((boundingBox = this.bbox) != null ? boundingBox.equals(geometryCollection.bbox()) : geometryCollection.bbox() == null) && this.geometries.equals(geometryCollection.geometries());
    }

    public int hashCode() {
        int iHashCode = (this.type.hashCode() ^ 1000003) * 1000003;
        BoundingBox boundingBox = this.bbox;
        return ((iHashCode ^ (boundingBox == null ? 0 : boundingBox.hashCode())) * 1000003) ^ this.geometries.hashCode();
    }

    static final class GsonTypeAdapter extends TypeAdapter<GeometryCollection> {
        private volatile TypeAdapter<BoundingBox> boundingBoxTypeAdapter;
        private final Gson gson;
        private volatile TypeAdapter<List<Geometry>> listGeometryAdapter;
        private volatile TypeAdapter<String> stringTypeAdapter;

        GsonTypeAdapter(Gson gson) {
            this.gson = gson;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, GeometryCollection geometryCollection) throws IOException {
            if (geometryCollection == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("type");
            if (geometryCollection.type() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> adapter = this.stringTypeAdapter;
                if (adapter == null) {
                    adapter = this.gson.getAdapter(String.class);
                    this.stringTypeAdapter = adapter;
                }
                adapter.write(jsonWriter, geometryCollection.type());
            }
            jsonWriter.name("bbox");
            if (geometryCollection.bbox() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<BoundingBox> adapter2 = this.boundingBoxTypeAdapter;
                if (adapter2 == null) {
                    adapter2 = this.gson.getAdapter(BoundingBox.class);
                    this.boundingBoxTypeAdapter = adapter2;
                }
                adapter2.write(jsonWriter, geometryCollection.bbox());
            }
            jsonWriter.name("geometries");
            if (geometryCollection.geometries() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Geometry>> adapter3 = this.listGeometryAdapter;
                if (adapter3 == null) {
                    adapter3 = this.gson.getAdapter(TypeToken.getParameterized(List.class, Geometry.class));
                    this.listGeometryAdapter = adapter3;
                }
                adapter3.write(jsonWriter, geometryCollection.geometries());
            }
            jsonWriter.endObject();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public GeometryCollection read2(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            BoundingBox boundingBox = null;
            List<Geometry> list = null;
            while (jsonReader.hasNext()) {
                String strNextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    strNextName.hashCode();
                    switch (strNextName) {
                        case "bbox":
                            TypeAdapter<BoundingBox> adapter = this.boundingBoxTypeAdapter;
                            if (adapter == null) {
                                adapter = this.gson.getAdapter(BoundingBox.class);
                                this.boundingBoxTypeAdapter = adapter;
                            }
                            boundingBox = adapter.read2(jsonReader);
                            break;
                        case "type":
                            TypeAdapter<String> adapter2 = this.stringTypeAdapter;
                            if (adapter2 == null) {
                                adapter2 = this.gson.getAdapter(String.class);
                                this.stringTypeAdapter = adapter2;
                            }
                            str = adapter2.read2(jsonReader);
                            break;
                        case "geometries":
                            TypeAdapter<List<Geometry>> adapter3 = this.listGeometryAdapter;
                            if (adapter3 == null) {
                                adapter3 = this.gson.getAdapter(TypeToken.getParameterized(List.class, Geometry.class));
                                this.listGeometryAdapter = adapter3;
                            }
                            list = adapter3.read2(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
            }
            jsonReader.endObject();
            if (str == null) {
                str = GeometryCollection.TYPE;
            }
            return new GeometryCollection(str, boundingBox, list);
        }
    }
}

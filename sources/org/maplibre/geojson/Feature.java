package org.maplibre.geojson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import org.maplibre.geojson.gson.BoundingBoxTypeAdapter;
import org.maplibre.geojson.gson.GeoJsonAdapterFactory;

/* loaded from: classes2.dex */
public final class Feature implements GeoJson {
    private static final String TYPE = "Feature";

    @JsonAdapter(BoundingBoxTypeAdapter.class)
    private final BoundingBox bbox;
    private final Geometry geometry;
    private final String id;
    private final JsonObject properties;
    private final String type;

    public static Feature fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        gsonBuilder.registerTypeAdapterFactory(GeometryAdapterFactory.create());
        Feature feature = (Feature) gsonBuilder.create().fromJson(str, Feature.class);
        return feature.properties != null ? feature : new Feature(TYPE, feature.bbox(), feature.id(), feature.geometry(), new JsonObject());
    }

    public static Feature fromGeometry(Geometry geometry) {
        return new Feature(TYPE, null, null, geometry, new JsonObject());
    }

    public static Feature fromGeometry(Geometry geometry, BoundingBox boundingBox) {
        return new Feature(TYPE, boundingBox, null, geometry, new JsonObject());
    }

    public static Feature fromGeometry(Geometry geometry, JsonObject jsonObject) {
        if (jsonObject == null) {
            jsonObject = new JsonObject();
        }
        return new Feature(TYPE, null, null, geometry, jsonObject);
    }

    public static Feature fromGeometry(Geometry geometry, JsonObject jsonObject, BoundingBox boundingBox) {
        if (jsonObject == null) {
            jsonObject = new JsonObject();
        }
        return new Feature(TYPE, boundingBox, null, geometry, jsonObject);
    }

    public static Feature fromGeometry(Geometry geometry, JsonObject jsonObject, String str) {
        if (jsonObject == null) {
            jsonObject = new JsonObject();
        }
        return new Feature(TYPE, null, str, geometry, jsonObject);
    }

    public static Feature fromGeometry(Geometry geometry, JsonObject jsonObject, String str, BoundingBox boundingBox) {
        if (jsonObject == null) {
            jsonObject = new JsonObject();
        }
        return new Feature(TYPE, boundingBox, str, geometry, jsonObject);
    }

    Feature(String str, BoundingBox boundingBox, String str2, Geometry geometry, JsonObject jsonObject) {
        if (str == null) {
            throw new NullPointerException("Null type");
        }
        this.type = str;
        this.bbox = boundingBox;
        this.id = str2;
        this.geometry = geometry;
        this.properties = jsonObject;
    }

    @Override // org.maplibre.geojson.GeoJson
    public String type() {
        return this.type;
    }

    @Override // org.maplibre.geojson.GeoJson
    public BoundingBox bbox() {
        return this.bbox;
    }

    public String id() {
        return this.id;
    }

    public Geometry geometry() {
        return this.geometry;
    }

    public JsonObject properties() {
        return this.properties;
    }

    @Override // org.maplibre.geojson.GeoJson
    public String toJson() {
        return new GsonBuilder().registerTypeAdapterFactory(GeoJsonAdapterFactory.create()).registerTypeAdapterFactory(GeometryAdapterFactory.create()).create().toJson(properties().size() == 0 ? new Feature(TYPE, bbox(), id(), geometry(), null) : this);
    }

    public static TypeAdapter<Feature> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public void addStringProperty(String str, String str2) {
        properties().addProperty(str, str2);
    }

    public void addNumberProperty(String str, Number number) {
        properties().addProperty(str, number);
    }

    public void addBooleanProperty(String str, Boolean bool) {
        properties().addProperty(str, bool);
    }

    public void addCharacterProperty(String str, Character ch) {
        properties().addProperty(str, ch);
    }

    public void addProperty(String str, JsonElement jsonElement) {
        properties().add(str, jsonElement);
    }

    public String getStringProperty(String str) {
        JsonElement jsonElement = properties().get(str);
        if (jsonElement == null) {
            return null;
        }
        return jsonElement.getAsString();
    }

    public Number getNumberProperty(String str) {
        JsonElement jsonElement = properties().get(str);
        if (jsonElement == null) {
            return null;
        }
        return jsonElement.getAsNumber();
    }

    public Boolean getBooleanProperty(String str) {
        JsonElement jsonElement = properties().get(str);
        if (jsonElement == null) {
            return null;
        }
        return Boolean.valueOf(jsonElement.getAsBoolean());
    }

    @Deprecated
    public Character getCharacterProperty(String str) {
        JsonElement jsonElement = properties().get(str);
        if (jsonElement == null) {
            return null;
        }
        return Character.valueOf(jsonElement.getAsCharacter());
    }

    public JsonElement getProperty(String str) {
        return properties().get(str);
    }

    public JsonElement removeProperty(String str) {
        return properties().remove(str);
    }

    public boolean hasProperty(String str) {
        return properties().has(str);
    }

    public boolean hasNonNullValueForProperty(String str) {
        return hasProperty(str) && !getProperty(str).isJsonNull();
    }

    public String toString() {
        return "Feature{type=" + this.type + ", bbox=" + this.bbox + ", id=" + this.id + ", geometry=" + this.geometry + ", properties=" + this.properties + "}";
    }

    public boolean equals(Object obj) {
        BoundingBox boundingBox;
        String str;
        Geometry geometry;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Feature)) {
            return false;
        }
        Feature feature = (Feature) obj;
        if (this.type.equals(feature.type()) && ((boundingBox = this.bbox) != null ? boundingBox.equals(feature.bbox()) : feature.bbox() == null) && ((str = this.id) != null ? str.equals(feature.id()) : feature.id() == null) && ((geometry = this.geometry) != null ? geometry.equals(feature.geometry()) : feature.geometry() == null)) {
            JsonObject jsonObject = this.properties;
            if (jsonObject == null) {
                if (feature.properties == null) {
                    return true;
                }
            } else if (jsonObject.equals(feature.properties())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = (this.type.hashCode() ^ 1000003) * 1000003;
        BoundingBox boundingBox = this.bbox;
        int iHashCode2 = (iHashCode ^ (boundingBox == null ? 0 : boundingBox.hashCode())) * 1000003;
        String str = this.id;
        int iHashCode3 = (iHashCode2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        Geometry geometry = this.geometry;
        int iHashCode4 = (iHashCode3 ^ (geometry == null ? 0 : geometry.hashCode())) * 1000003;
        JsonObject jsonObject = this.properties;
        return iHashCode4 ^ (jsonObject != null ? jsonObject.hashCode() : 0);
    }

    static final class GsonTypeAdapter extends TypeAdapter<Feature> {
        private volatile TypeAdapter<BoundingBox> boundingBoxTypeAdapter;
        private volatile TypeAdapter<Geometry> geometryTypeAdapter;
        private final Gson gson;
        private volatile TypeAdapter<JsonObject> jsonObjectTypeAdapter;
        private volatile TypeAdapter<String> stringTypeAdapter;

        GsonTypeAdapter(Gson gson) {
            this.gson = gson;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Feature feature) throws IOException {
            if (feature == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("type");
            TypeAdapter<String> adapter = this.stringTypeAdapter;
            if (adapter == null) {
                adapter = this.gson.getAdapter(String.class);
                this.stringTypeAdapter = adapter;
            }
            adapter.write(jsonWriter, feature.type());
            jsonWriter.name("bbox");
            if (feature.bbox() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<BoundingBox> adapter2 = this.boundingBoxTypeAdapter;
                if (adapter2 == null) {
                    adapter2 = this.gson.getAdapter(BoundingBox.class);
                    this.boundingBoxTypeAdapter = adapter2;
                }
                adapter2.write(jsonWriter, feature.bbox());
            }
            jsonWriter.name("id");
            if (feature.id() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> adapter3 = this.stringTypeAdapter;
                if (adapter3 == null) {
                    adapter3 = this.gson.getAdapter(String.class);
                    this.stringTypeAdapter = adapter3;
                }
                adapter3.write(jsonWriter, feature.id());
            }
            jsonWriter.name("geometry");
            if (feature.geometry() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Geometry> adapter4 = this.geometryTypeAdapter;
                if (adapter4 == null) {
                    adapter4 = this.gson.getAdapter(Geometry.class);
                    this.geometryTypeAdapter = adapter4;
                }
                adapter4.write(jsonWriter, feature.geometry());
            }
            jsonWriter.name("properties");
            if (feature.properties == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<JsonObject> adapter5 = this.jsonObjectTypeAdapter;
                if (adapter5 == null) {
                    adapter5 = this.gson.getAdapter(JsonObject.class);
                    this.jsonObjectTypeAdapter = adapter5;
                }
                adapter5.write(jsonWriter, feature.properties());
            }
            jsonWriter.endObject();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public Feature read2(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            BoundingBox boundingBox = null;
            String str2 = null;
            Geometry geometry = null;
            JsonObject jsonObject = null;
            while (jsonReader.hasNext()) {
                String strNextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    strNextName.hashCode();
                    switch (strNextName) {
                        case "properties":
                            TypeAdapter<JsonObject> adapter = this.jsonObjectTypeAdapter;
                            if (adapter == null) {
                                adapter = this.gson.getAdapter(JsonObject.class);
                                this.jsonObjectTypeAdapter = adapter;
                            }
                            jsonObject = adapter.read2(jsonReader);
                            break;
                        case "id":
                            TypeAdapter<String> adapter2 = this.stringTypeAdapter;
                            if (adapter2 == null) {
                                adapter2 = this.gson.getAdapter(String.class);
                                this.stringTypeAdapter = adapter2;
                            }
                            str2 = adapter2.read2(jsonReader);
                            break;
                        case "bbox":
                            TypeAdapter<BoundingBox> adapter3 = this.boundingBoxTypeAdapter;
                            if (adapter3 == null) {
                                adapter3 = this.gson.getAdapter(BoundingBox.class);
                                this.boundingBoxTypeAdapter = adapter3;
                            }
                            boundingBox = adapter3.read2(jsonReader);
                            break;
                        case "type":
                            TypeAdapter<String> adapter4 = this.stringTypeAdapter;
                            if (adapter4 == null) {
                                adapter4 = this.gson.getAdapter(String.class);
                                this.stringTypeAdapter = adapter4;
                            }
                            str = adapter4.read2(jsonReader);
                            break;
                        case "geometry":
                            TypeAdapter<Geometry> adapter5 = this.geometryTypeAdapter;
                            if (adapter5 == null) {
                                adapter5 = this.gson.getAdapter(Geometry.class);
                                this.geometryTypeAdapter = adapter5;
                            }
                            geometry = adapter5.read2(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
            }
            jsonReader.endObject();
            return new Feature(str, boundingBox, str2, geometry, jsonObject);
        }
    }
}

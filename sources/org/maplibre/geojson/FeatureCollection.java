package org.maplibre.geojson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.maplibre.geojson.gson.BoundingBoxTypeAdapter;
import org.maplibre.geojson.gson.GeoJsonAdapterFactory;

/* loaded from: classes2.dex */
public final class FeatureCollection implements GeoJson {
    private static final String TYPE = "FeatureCollection";

    @JsonAdapter(BoundingBoxTypeAdapter.class)
    private final BoundingBox bbox;
    private final List<Feature> features;
    private final String type;

    public static FeatureCollection fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        gsonBuilder.registerTypeAdapterFactory(GeometryAdapterFactory.create());
        return (FeatureCollection) gsonBuilder.create().fromJson(str, FeatureCollection.class);
    }

    public static FeatureCollection fromFeatures(Feature[] featureArr) {
        return new FeatureCollection(TYPE, null, Arrays.asList(featureArr));
    }

    public static FeatureCollection fromFeatures(List<Feature> list) {
        return new FeatureCollection(TYPE, null, list);
    }

    public static FeatureCollection fromFeatures(Feature[] featureArr, BoundingBox boundingBox) {
        return new FeatureCollection(TYPE, boundingBox, Arrays.asList(featureArr));
    }

    public static FeatureCollection fromFeatures(List<Feature> list, BoundingBox boundingBox) {
        return new FeatureCollection(TYPE, boundingBox, list);
    }

    public static FeatureCollection fromFeature(Feature feature) {
        return new FeatureCollection(TYPE, null, Arrays.asList(feature));
    }

    public static FeatureCollection fromFeature(Feature feature, BoundingBox boundingBox) {
        return new FeatureCollection(TYPE, boundingBox, Arrays.asList(feature));
    }

    FeatureCollection(String str, BoundingBox boundingBox, List<Feature> list) {
        if (str == null) {
            throw new NullPointerException("Null type");
        }
        this.type = str;
        this.bbox = boundingBox;
        this.features = list;
    }

    @Override // org.maplibre.geojson.GeoJson
    public String type() {
        return this.type;
    }

    @Override // org.maplibre.geojson.GeoJson
    public BoundingBox bbox() {
        return this.bbox;
    }

    public List<Feature> features() {
        return this.features;
    }

    @Override // org.maplibre.geojson.GeoJson
    public String toJson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        gsonBuilder.registerTypeAdapterFactory(GeometryAdapterFactory.create());
        return gsonBuilder.create().toJson(this);
    }

    public static TypeAdapter<FeatureCollection> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public String toString() {
        return "FeatureCollection{type=" + this.type + ", bbox=" + this.bbox + ", features=" + this.features + "}";
    }

    public boolean equals(Object obj) {
        BoundingBox boundingBox;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FeatureCollection)) {
            return false;
        }
        FeatureCollection featureCollection = (FeatureCollection) obj;
        if (this.type.equals(featureCollection.type()) && ((boundingBox = this.bbox) != null ? boundingBox.equals(featureCollection.bbox()) : featureCollection.bbox() == null)) {
            List<Feature> list = this.features;
            if (list == null) {
                if (featureCollection.features() == null) {
                    return true;
                }
            } else if (list.equals(featureCollection.features())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = (this.type.hashCode() ^ 1000003) * 1000003;
        BoundingBox boundingBox = this.bbox;
        int iHashCode2 = (iHashCode ^ (boundingBox == null ? 0 : boundingBox.hashCode())) * 1000003;
        List<Feature> list = this.features;
        return iHashCode2 ^ (list != null ? list.hashCode() : 0);
    }

    static final class GsonTypeAdapter extends TypeAdapter<FeatureCollection> {
        private volatile TypeAdapter<BoundingBox> boundingBoxAdapter;
        private final Gson gson;
        private volatile TypeAdapter<List<Feature>> listFeatureAdapter;
        private volatile TypeAdapter<String> stringAdapter;

        GsonTypeAdapter(Gson gson) {
            this.gson = gson;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, FeatureCollection featureCollection) throws IOException {
            if (featureCollection == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("type");
            if (featureCollection.type() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> adapter = this.stringAdapter;
                if (adapter == null) {
                    adapter = this.gson.getAdapter(String.class);
                    this.stringAdapter = adapter;
                }
                adapter.write(jsonWriter, featureCollection.type());
            }
            jsonWriter.name("bbox");
            if (featureCollection.bbox() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<BoundingBox> adapter2 = this.boundingBoxAdapter;
                if (adapter2 == null) {
                    adapter2 = this.gson.getAdapter(BoundingBox.class);
                    this.boundingBoxAdapter = adapter2;
                }
                adapter2.write(jsonWriter, featureCollection.bbox());
            }
            jsonWriter.name("features");
            if (featureCollection.features() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<Feature>> adapter3 = this.listFeatureAdapter;
                if (adapter3 == null) {
                    adapter3 = this.gson.getAdapter(TypeToken.getParameterized(List.class, Feature.class));
                    this.listFeatureAdapter = adapter3;
                }
                adapter3.write(jsonWriter, featureCollection.features());
            }
            jsonWriter.endObject();
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read, reason: avoid collision after fix types in other method */
        public FeatureCollection read2(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            BoundingBox boundingBox = null;
            List<Feature> list = null;
            while (jsonReader.hasNext()) {
                String strNextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    strNextName.hashCode();
                    switch (strNextName) {
                        case "features":
                            TypeAdapter<List<Feature>> adapter = this.listFeatureAdapter;
                            if (adapter == null) {
                                adapter = this.gson.getAdapter(TypeToken.getParameterized(List.class, Feature.class));
                                this.listFeatureAdapter = adapter;
                            }
                            list = adapter.read2(jsonReader);
                            break;
                        case "bbox":
                            TypeAdapter<BoundingBox> adapter2 = this.boundingBoxAdapter;
                            if (adapter2 == null) {
                                adapter2 = this.gson.getAdapter(BoundingBox.class);
                                this.boundingBoxAdapter = adapter2;
                            }
                            boundingBox = adapter2.read2(jsonReader);
                            break;
                        case "type":
                            TypeAdapter<String> adapter3 = this.stringAdapter;
                            if (adapter3 == null) {
                                adapter3 = this.gson.getAdapter(String.class);
                                this.stringAdapter = adapter3;
                            }
                            str = adapter3.read2(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
            }
            jsonReader.endObject();
            return new FeatureCollection(str, boundingBox, list);
        }
    }
}

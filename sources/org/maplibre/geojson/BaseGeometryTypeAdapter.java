package org.maplibre.geojson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import org.maplibre.geojson.exception.GeoJsonException;
import org.maplibre.geojson.gson.BoundingBoxTypeAdapter;

/* loaded from: classes2.dex */
abstract class BaseGeometryTypeAdapter<G, T> extends TypeAdapter<G> {
    private volatile TypeAdapter<BoundingBox> boundingBoxAdapter = new BoundingBoxTypeAdapter();
    private volatile TypeAdapter<T> coordinatesAdapter;
    private final Gson gson;
    private volatile TypeAdapter<String> stringAdapter;

    abstract CoordinateContainer<T> createCoordinateContainer(String str, BoundingBox boundingBox, T t);

    BaseGeometryTypeAdapter(Gson gson, TypeAdapter<T> typeAdapter) {
        this.gson = gson;
        this.coordinatesAdapter = typeAdapter;
    }

    public void writeCoordinateContainer(JsonWriter jsonWriter, CoordinateContainer<T> coordinateContainer) throws IOException {
        if (coordinateContainer == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginObject();
        jsonWriter.name("type");
        if (coordinateContainer.type() == null) {
            jsonWriter.nullValue();
        } else {
            TypeAdapter<String> adapter = this.stringAdapter;
            if (adapter == null) {
                adapter = this.gson.getAdapter(String.class);
                this.stringAdapter = adapter;
            }
            adapter.write(jsonWriter, coordinateContainer.type());
        }
        jsonWriter.name("bbox");
        if (coordinateContainer.bbox() == null) {
            jsonWriter.nullValue();
        } else {
            TypeAdapter<BoundingBox> adapter2 = this.boundingBoxAdapter;
            if (adapter2 == null) {
                adapter2 = this.gson.getAdapter(BoundingBox.class);
                this.boundingBoxAdapter = adapter2;
            }
            adapter2.write(jsonWriter, coordinateContainer.bbox());
        }
        jsonWriter.name("coordinates");
        if (coordinateContainer.coordinates() == null) {
            jsonWriter.nullValue();
        } else {
            TypeAdapter<T> typeAdapter = this.coordinatesAdapter;
            if (typeAdapter == null) {
                throw new GeoJsonException("Coordinates type adapter is null");
            }
            typeAdapter.write(jsonWriter, coordinateContainer.coordinates());
        }
        jsonWriter.endObject();
    }

    public CoordinateContainer<T> readCoordinateContainer(JsonReader jsonReader) throws IOException {
        String str = null;
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        jsonReader.beginObject();
        BoundingBox boundingBox = null;
        T t = null;
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
            } else {
                strNextName.hashCode();
                switch (strNextName) {
                    case "bbox":
                        TypeAdapter<BoundingBox> adapter = this.boundingBoxAdapter;
                        if (adapter == null) {
                            adapter = this.gson.getAdapter(BoundingBox.class);
                            this.boundingBoxAdapter = adapter;
                        }
                        boundingBox = adapter.read2(jsonReader);
                        break;
                    case "type":
                        TypeAdapter<String> adapter2 = this.stringAdapter;
                        if (adapter2 == null) {
                            adapter2 = this.gson.getAdapter(String.class);
                            this.stringAdapter = adapter2;
                        }
                        str = adapter2.read2(jsonReader);
                        break;
                    case "coordinates":
                        TypeAdapter<T> typeAdapter = this.coordinatesAdapter;
                        if (typeAdapter == null) {
                            throw new GeoJsonException("Coordinates type adapter is null");
                        }
                        t = typeAdapter.read2(jsonReader);
                        break;
                    default:
                        jsonReader.skipValue();
                        break;
                }
            }
        }
        jsonReader.endObject();
        return createCoordinateContainer(str, boundingBox, t);
    }
}

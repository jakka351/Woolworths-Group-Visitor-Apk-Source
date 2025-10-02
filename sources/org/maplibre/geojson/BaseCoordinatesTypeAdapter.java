package org.maplibre.geojson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.maplibre.geojson.exception.GeoJsonException;
import org.maplibre.geojson.shifter.CoordinateShifterManager;
import org.maplibre.geojson.utils.GeoJsonUtils;

/* loaded from: classes2.dex */
abstract class BaseCoordinatesTypeAdapter<T> extends TypeAdapter<T> {
    BaseCoordinatesTypeAdapter() {
    }

    protected void writePoint(JsonWriter jsonWriter, Point point) throws IOException {
        if (point == null) {
            return;
        }
        writePointList(jsonWriter, point.coordinates());
    }

    protected Point readPoint(JsonReader jsonReader) throws IOException {
        List<Double> pointList = readPointList(jsonReader);
        if (pointList != null && pointList.size() > 1) {
            return new Point("Point", null, pointList);
        }
        throw new GeoJsonException(" Point coordinates should be non-null double array");
    }

    protected void writePointList(JsonWriter jsonWriter, List<Double> list) throws IOException {
        if (list == null) {
            return;
        }
        jsonWriter.beginArray();
        List<Double> listUnshiftPoint = CoordinateShifterManager.getCoordinateShifter().unshiftPoint(list);
        jsonWriter.value(GeoJsonUtils.trim(listUnshiftPoint.get(0).doubleValue()));
        jsonWriter.value(GeoJsonUtils.trim(listUnshiftPoint.get(1).doubleValue()));
        if (list.size() > 2) {
            jsonWriter.value(listUnshiftPoint.get(2));
        }
        jsonWriter.endArray();
    }

    protected List<Double> readPointList(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            throw null;
        }
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(Double.valueOf(jsonReader.nextDouble()));
        }
        jsonReader.endArray();
        if (arrayList.size() > 2) {
            return CoordinateShifterManager.getCoordinateShifter().shiftLonLatAlt(((Double) arrayList.get(0)).doubleValue(), ((Double) arrayList.get(1)).doubleValue(), ((Double) arrayList.get(2)).doubleValue());
        }
        return CoordinateShifterManager.getCoordinateShifter().shiftLonLat(((Double) arrayList.get(0)).doubleValue(), ((Double) arrayList.get(1)).doubleValue());
    }
}

package org.maplibre.geojson.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.maplibre.geojson.BoundingBox;
import org.maplibre.geojson.Point;
import org.maplibre.geojson.exception.GeoJsonException;
import org.maplibre.geojson.shifter.CoordinateShifterManager;
import org.maplibre.geojson.utils.GeoJsonUtils;

/* loaded from: classes2.dex */
public class BoundingBoxTypeAdapter extends TypeAdapter<BoundingBox> {
    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, BoundingBox boundingBox) throws IOException {
        if (boundingBox == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        Point pointSouthwest = boundingBox.southwest();
        List<Double> listUnshiftPoint = CoordinateShifterManager.getCoordinateShifter().unshiftPoint(pointSouthwest);
        jsonWriter.value(GeoJsonUtils.trim(listUnshiftPoint.get(0).doubleValue()));
        jsonWriter.value(GeoJsonUtils.trim(listUnshiftPoint.get(1).doubleValue()));
        if (pointSouthwest.hasAltitude()) {
            jsonWriter.value(listUnshiftPoint.get(2));
        }
        Point pointNortheast = boundingBox.northeast();
        List<Double> listUnshiftPoint2 = CoordinateShifterManager.getCoordinateShifter().unshiftPoint(pointNortheast);
        jsonWriter.value(GeoJsonUtils.trim(listUnshiftPoint2.get(0).doubleValue()));
        jsonWriter.value(GeoJsonUtils.trim(listUnshiftPoint2.get(1).doubleValue()));
        if (pointNortheast.hasAltitude()) {
            jsonWriter.value(listUnshiftPoint2.get(2));
        }
        jsonWriter.endArray();
    }

    @Override // com.google.gson.TypeAdapter
    /* renamed from: read, reason: avoid collision after fix types in other method */
    public BoundingBox read2(JsonReader jsonReader) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(Double.valueOf(jsonReader.nextDouble()));
        }
        jsonReader.endArray();
        if (arrayList.size() == 6) {
            return BoundingBox.fromLngLats(((Double) arrayList.get(0)).doubleValue(), ((Double) arrayList.get(1)).doubleValue(), ((Double) arrayList.get(2)).doubleValue(), ((Double) arrayList.get(3)).doubleValue(), ((Double) arrayList.get(4)).doubleValue(), ((Double) arrayList.get(5)).doubleValue());
        }
        if (arrayList.size() == 4) {
            return BoundingBox.fromLngLats(((Double) arrayList.get(0)).doubleValue(), ((Double) arrayList.get(1)).doubleValue(), ((Double) arrayList.get(2)).doubleValue(), ((Double) arrayList.get(3)).doubleValue());
        }
        throw new GeoJsonException("The value of the bbox member MUST be an array of length 2*n where n is the number of dimensions represented in the contained geometries,with all axes of the most southwesterly point followed  by all axes of the more northeasterly point. The axes order of a bbox follows the axes order of geometries.");
    }
}

package org.maplibre.android.plugins.annotation;

import android.graphics.PointF;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.gestures.MoveDistancesObject;
import org.maplibre.android.maps.Projection;
import org.maplibre.android.utils.ColorUtils;
import org.maplibre.geojson.Geometry;
import org.maplibre.geojson.LineString;
import org.maplibre.geojson.Point;

/* loaded from: classes2.dex */
public class Line extends Annotation<LineString> {
    private final AnnotationManager<?, Line, ?, ?, ?, ?> annotationManager;

    @Override // org.maplibre.android.plugins.annotation.Annotation
    String getName() {
        return "Line";
    }

    Line(long j, AnnotationManager<?, Line, ?, ?, ?, ?> annotationManager, JsonObject jsonObject, LineString lineString) {
        super(j, jsonObject, lineString);
        this.annotationManager = annotationManager;
    }

    @Override // org.maplibre.android.plugins.annotation.Annotation
    void setUsedDataDrivenProperties() {
        if (!(this.jsonObject.get("line-join") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("line-join");
        }
        if (!(this.jsonObject.get("line-opacity") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("line-opacity");
        }
        if (!(this.jsonObject.get("line-color") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("line-color");
        }
        if (!(this.jsonObject.get("line-width") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("line-width");
        }
        if (!(this.jsonObject.get("line-gap-width") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("line-gap-width");
        }
        if (!(this.jsonObject.get("line-offset") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("line-offset");
        }
        if (!(this.jsonObject.get("line-blur") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("line-blur");
        }
        if (this.jsonObject.get("line-pattern") instanceof JsonNull) {
            return;
        }
        this.annotationManager.enableDataDrivenProperty("line-pattern");
    }

    public void setLatLngs(List<LatLng> list) {
        ArrayList arrayList = new ArrayList();
        for (LatLng latLng : list) {
            arrayList.add(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()));
        }
        this.geometry = LineString.fromLngLats(arrayList);
    }

    public List<LatLng> getLatLngs() {
        LineString lineString = (LineString) this.geometry;
        ArrayList arrayList = new ArrayList();
        for (Point point : lineString.coordinates()) {
            arrayList.add(new LatLng(point.latitude(), point.longitude()));
        }
        return arrayList;
    }

    public String getLineJoin() {
        return this.jsonObject.get("line-join").getAsString();
    }

    public void setLineJoin(String str) {
        this.jsonObject.addProperty("line-join", str);
    }

    public Float getLineOpacity() {
        return Float.valueOf(this.jsonObject.get("line-opacity").getAsFloat());
    }

    public void setLineOpacity(Float f) {
        this.jsonObject.addProperty("line-opacity", f);
    }

    public int getLineColorAsInt() {
        return ColorUtils.rgbaToColor(this.jsonObject.get("line-color").getAsString());
    }

    public String getLineColor() {
        return this.jsonObject.get("line-color").getAsString();
    }

    public void setLineColor(int i) {
        this.jsonObject.addProperty("line-color", ColorUtils.colorToRgbaString(i));
    }

    public void setLineColor(String str) {
        this.jsonObject.addProperty("line-color", str);
    }

    public Float getLineWidth() {
        return Float.valueOf(this.jsonObject.get("line-width").getAsFloat());
    }

    public void setLineWidth(Float f) {
        this.jsonObject.addProperty("line-width", f);
    }

    public Float getLineGapWidth() {
        return Float.valueOf(this.jsonObject.get("line-gap-width").getAsFloat());
    }

    public void setLineGapWidth(Float f) {
        this.jsonObject.addProperty("line-gap-width", f);
    }

    public Float getLineOffset() {
        return Float.valueOf(this.jsonObject.get("line-offset").getAsFloat());
    }

    public void setLineOffset(Float f) {
        this.jsonObject.addProperty("line-offset", f);
    }

    public Float getLineBlur() {
        return Float.valueOf(this.jsonObject.get("line-blur").getAsFloat());
    }

    public void setLineBlur(Float f) {
        this.jsonObject.addProperty("line-blur", f);
    }

    public String getLinePattern() {
        return this.jsonObject.get("line-pattern").getAsString();
    }

    public void setLinePattern(String str) {
        this.jsonObject.addProperty("line-pattern", str);
    }

    @Override // org.maplibre.android.plugins.annotation.Annotation
    Geometry getOffsetGeometry(Projection projection, MoveDistancesObject moveDistancesObject, float f, float f2) {
        List<Point> listCoordinates = ((LineString) this.geometry).coordinates();
        ArrayList arrayList = new ArrayList(listCoordinates.size());
        for (Point point : listCoordinates) {
            PointF screenLocation = projection.toScreenLocation(new LatLng(point.latitude(), point.longitude()));
            screenLocation.x -= moveDistancesObject.getDistanceXSinceLast();
            screenLocation.y -= moveDistancesObject.getDistanceYSinceLast();
            LatLng latLngFromScreenLocation = projection.fromScreenLocation(screenLocation);
            if (latLngFromScreenLocation.getLatitude() > 85.05112877980659d || latLngFromScreenLocation.getLatitude() < -85.05112877980659d) {
                return null;
            }
            arrayList.add(Point.fromLngLat(latLngFromScreenLocation.getLongitude(), latLngFromScreenLocation.getLatitude()));
        }
        return LineString.fromLngLats(arrayList);
    }
}

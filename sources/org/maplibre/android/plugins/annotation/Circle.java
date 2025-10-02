package org.maplibre.android.plugins.annotation;

import android.graphics.PointF;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.gestures.MoveDistancesObject;
import org.maplibre.android.maps.Projection;
import org.maplibre.android.utils.ColorUtils;
import org.maplibre.geojson.Geometry;
import org.maplibre.geojson.Point;

/* loaded from: classes2.dex */
public class Circle extends Annotation<Point> {
    private final AnnotationManager<?, Circle, ?, ?, ?, ?> annotationManager;

    @Override // org.maplibre.android.plugins.annotation.Annotation
    String getName() {
        return "Circle";
    }

    Circle(long j, AnnotationManager<?, Circle, ?, ?, ?, ?> annotationManager, JsonObject jsonObject, Point point) {
        super(j, jsonObject, point);
        this.annotationManager = annotationManager;
    }

    @Override // org.maplibre.android.plugins.annotation.Annotation
    void setUsedDataDrivenProperties() {
        if (!(this.jsonObject.get("circle-radius") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("circle-radius");
        }
        if (!(this.jsonObject.get("circle-color") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("circle-color");
        }
        if (!(this.jsonObject.get("circle-blur") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("circle-blur");
        }
        if (!(this.jsonObject.get("circle-opacity") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("circle-opacity");
        }
        if (!(this.jsonObject.get("circle-stroke-width") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("circle-stroke-width");
        }
        if (!(this.jsonObject.get("circle-stroke-color") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("circle-stroke-color");
        }
        if (this.jsonObject.get("circle-stroke-opacity") instanceof JsonNull) {
            return;
        }
        this.annotationManager.enableDataDrivenProperty("circle-stroke-opacity");
    }

    public void setLatLng(LatLng latLng) {
        this.geometry = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
    }

    public LatLng getLatLng() {
        return new LatLng(((Point) this.geometry).latitude(), ((Point) this.geometry).longitude());
    }

    public Float getCircleRadius() {
        return Float.valueOf(this.jsonObject.get("circle-radius").getAsFloat());
    }

    public void setCircleRadius(Float f) {
        this.jsonObject.addProperty("circle-radius", f);
    }

    public int getCircleColorAsInt() {
        return ColorUtils.rgbaToColor(this.jsonObject.get("circle-color").getAsString());
    }

    public String getCircleColor() {
        return this.jsonObject.get("circle-color").getAsString();
    }

    public void setCircleColor(int i) {
        this.jsonObject.addProperty("circle-color", ColorUtils.colorToRgbaString(i));
    }

    public void setCircleColor(String str) {
        this.jsonObject.addProperty("circle-color", str);
    }

    public Float getCircleBlur() {
        return Float.valueOf(this.jsonObject.get("circle-blur").getAsFloat());
    }

    public void setCircleBlur(Float f) {
        this.jsonObject.addProperty("circle-blur", f);
    }

    public Float getCircleOpacity() {
        return Float.valueOf(this.jsonObject.get("circle-opacity").getAsFloat());
    }

    public void setCircleOpacity(Float f) {
        this.jsonObject.addProperty("circle-opacity", f);
    }

    public Float getCircleStrokeWidth() {
        return Float.valueOf(this.jsonObject.get("circle-stroke-width").getAsFloat());
    }

    public void setCircleStrokeWidth(Float f) {
        this.jsonObject.addProperty("circle-stroke-width", f);
    }

    public int getCircleStrokeColorAsInt() {
        return ColorUtils.rgbaToColor(this.jsonObject.get("circle-stroke-color").getAsString());
    }

    public String getCircleStrokeColor() {
        return this.jsonObject.get("circle-stroke-color").getAsString();
    }

    public void setCircleStrokeColor(int i) {
        this.jsonObject.addProperty("circle-stroke-color", ColorUtils.colorToRgbaString(i));
    }

    public void setCircleStrokeColor(String str) {
        this.jsonObject.addProperty("circle-stroke-color", str);
    }

    public Float getCircleStrokeOpacity() {
        return Float.valueOf(this.jsonObject.get("circle-stroke-opacity").getAsFloat());
    }

    public void setCircleStrokeOpacity(Float f) {
        this.jsonObject.addProperty("circle-stroke-opacity", f);
    }

    @Override // org.maplibre.android.plugins.annotation.Annotation
    Geometry getOffsetGeometry(Projection projection, MoveDistancesObject moveDistancesObject, float f, float f2) {
        LatLng latLngFromScreenLocation = projection.fromScreenLocation(new PointF(moveDistancesObject.getCurrentX() - f, moveDistancesObject.getCurrentY() - f2));
        if (latLngFromScreenLocation.getLatitude() > 85.05112877980659d || latLngFromScreenLocation.getLatitude() < -85.05112877980659d) {
            return null;
        }
        return Point.fromLngLat(latLngFromScreenLocation.getLongitude(), latLngFromScreenLocation.getLatitude());
    }
}

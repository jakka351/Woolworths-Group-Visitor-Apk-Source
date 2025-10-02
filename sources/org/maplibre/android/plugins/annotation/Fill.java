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
import org.maplibre.geojson.Point;
import org.maplibre.geojson.Polygon;

/* loaded from: classes2.dex */
public class Fill extends Annotation<Polygon> {
    private final AnnotationManager<?, Fill, ?, ?, ?, ?> annotationManager;

    @Override // org.maplibre.android.plugins.annotation.Annotation
    String getName() {
        return "Fill";
    }

    Fill(long j, AnnotationManager<?, Fill, ?, ?, ?, ?> annotationManager, JsonObject jsonObject, Polygon polygon) {
        super(j, jsonObject, polygon);
        this.annotationManager = annotationManager;
    }

    @Override // org.maplibre.android.plugins.annotation.Annotation
    void setUsedDataDrivenProperties() {
        if (!(this.jsonObject.get("fill-opacity") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("fill-opacity");
        }
        if (!(this.jsonObject.get("fill-color") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("fill-color");
        }
        if (!(this.jsonObject.get("fill-outline-color") instanceof JsonNull)) {
            this.annotationManager.enableDataDrivenProperty("fill-outline-color");
        }
        if (this.jsonObject.get("fill-pattern") instanceof JsonNull) {
            return;
        }
        this.annotationManager.enableDataDrivenProperty("fill-pattern");
    }

    public void setLatLngs(List<List<LatLng>> list) {
        ArrayList arrayList = new ArrayList();
        for (List<LatLng> list2 : list) {
            ArrayList arrayList2 = new ArrayList();
            for (LatLng latLng : list2) {
                arrayList2.add(Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude()));
            }
            arrayList.add(arrayList2);
        }
        this.geometry = Polygon.fromLngLats(arrayList);
    }

    public List<List<LatLng>> getLatLngs() {
        Polygon polygon = (Polygon) this.geometry;
        ArrayList arrayList = new ArrayList();
        List<List<Point>> listCoordinates = polygon.coordinates();
        if (listCoordinates != null) {
            for (List<Point> list : listCoordinates) {
                ArrayList arrayList2 = new ArrayList();
                for (Point point : list) {
                    arrayList2.add(new LatLng(point.latitude(), point.longitude()));
                }
                arrayList.add(arrayList2);
            }
        }
        return arrayList;
    }

    public Float getFillOpacity() {
        return Float.valueOf(this.jsonObject.get("fill-opacity").getAsFloat());
    }

    public void setFillOpacity(Float f) {
        this.jsonObject.addProperty("fill-opacity", f);
    }

    public int getFillColorAsInt() {
        return ColorUtils.rgbaToColor(this.jsonObject.get("fill-color").getAsString());
    }

    public String getFillColor() {
        return this.jsonObject.get("fill-color").getAsString();
    }

    public void setFillColor(int i) {
        this.jsonObject.addProperty("fill-color", ColorUtils.colorToRgbaString(i));
    }

    public void setFillColor(String str) {
        this.jsonObject.addProperty("fill-color", str);
    }

    public int getFillOutlineColorAsInt() {
        return ColorUtils.rgbaToColor(this.jsonObject.get("fill-outline-color").getAsString());
    }

    public String getFillOutlineColor() {
        return this.jsonObject.get("fill-outline-color").getAsString();
    }

    public void setFillOutlineColor(int i) {
        this.jsonObject.addProperty("fill-outline-color", ColorUtils.colorToRgbaString(i));
    }

    public void setFillOutlineColor(String str) {
        this.jsonObject.addProperty("fill-outline-color", str);
    }

    public String getFillPattern() {
        return this.jsonObject.get("fill-pattern").getAsString();
    }

    public void setFillPattern(String str) {
        this.jsonObject.addProperty("fill-pattern", str);
    }

    @Override // org.maplibre.android.plugins.annotation.Annotation
    Geometry getOffsetGeometry(Projection projection, MoveDistancesObject moveDistancesObject, float f, float f2) {
        List<List<Point>> listCoordinates = ((Polygon) this.geometry).coordinates();
        if (listCoordinates == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(listCoordinates.size());
        for (List<Point> list : listCoordinates) {
            ArrayList arrayList2 = new ArrayList();
            for (Point point : list) {
                PointF screenLocation = projection.toScreenLocation(new LatLng(point.latitude(), point.longitude()));
                screenLocation.x -= moveDistancesObject.getDistanceXSinceLast();
                screenLocation.y -= moveDistancesObject.getDistanceYSinceLast();
                LatLng latLngFromScreenLocation = projection.fromScreenLocation(screenLocation);
                if (latLngFromScreenLocation.getLatitude() > 85.05112877980659d || latLngFromScreenLocation.getLatitude() < -85.05112877980659d) {
                    return null;
                }
                arrayList2.add(Point.fromLngLat(latLngFromScreenLocation.getLongitude(), latLngFromScreenLocation.getLatitude()));
            }
            arrayList.add(arrayList2);
        }
        return Polygon.fromLngLats(arrayList);
    }
}

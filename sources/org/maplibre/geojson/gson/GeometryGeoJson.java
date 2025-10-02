package org.maplibre.geojson.gson;

import com.google.gson.GsonBuilder;
import org.maplibre.geojson.Geometry;
import org.maplibre.geojson.GeometryAdapterFactory;

/* loaded from: classes2.dex */
public class GeometryGeoJson {
    public static Geometry fromJson(String str) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(GeoJsonAdapterFactory.create());
        gsonBuilder.registerTypeAdapterFactory(GeometryAdapterFactory.create());
        return (Geometry) gsonBuilder.create().fromJson(str, Geometry.class);
    }
}

package org.maplibre.geojson.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import org.maplibre.geojson.BoundingBox;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;
import org.maplibre.geojson.GeometryCollection;
import org.maplibre.geojson.LineString;
import org.maplibre.geojson.MultiLineString;
import org.maplibre.geojson.MultiPoint;
import org.maplibre.geojson.MultiPolygon;
import org.maplibre.geojson.Point;
import org.maplibre.geojson.Polygon;

/* loaded from: classes2.dex */
public abstract class GeoJsonAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        return new GeoJsonAdapterFactoryIml();
    }

    public static final class GeoJsonAdapterFactoryIml extends GeoJsonAdapterFactory {
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class<? super T> rawType = typeToken.getRawType();
            if (BoundingBox.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) BoundingBox.typeAdapter(gson);
            }
            if (Feature.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) Feature.typeAdapter(gson);
            }
            if (FeatureCollection.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) FeatureCollection.typeAdapter(gson);
            }
            if (GeometryCollection.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) GeometryCollection.typeAdapter(gson);
            }
            if (LineString.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) LineString.typeAdapter(gson);
            }
            if (MultiLineString.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) MultiLineString.typeAdapter(gson);
            }
            if (MultiPoint.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) MultiPoint.typeAdapter(gson);
            }
            if (MultiPolygon.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) MultiPolygon.typeAdapter(gson);
            }
            if (Polygon.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) Polygon.typeAdapter(gson);
            }
            if (Point.class.isAssignableFrom(rawType)) {
                return (TypeAdapter<T>) Point.typeAdapter(gson);
            }
            return null;
        }
    }
}

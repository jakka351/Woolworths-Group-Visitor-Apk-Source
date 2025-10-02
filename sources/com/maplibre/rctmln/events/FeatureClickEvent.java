package com.maplibre.rctmln.events;

import android.graphics.PointF;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.maplibre.rctmln.components.styles.sources.RCTSource;
import com.maplibre.rctmln.events.constants.EventKeys;
import com.maplibre.rctmln.events.constants.EventTypes;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import java.util.Iterator;
import java.util.List;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.style.layers.Property;
import org.maplibre.geojson.Feature;

/* loaded from: classes3.dex */
public class FeatureClickEvent extends AbstractEvent {
    private String mEventKey;
    private List<Feature> mFeatures;
    private LatLng mLatLng;
    private PointF mPoint;

    public FeatureClickEvent(View view, String str, String str2, List<Feature> list, LatLng latLng, PointF pointF) {
        super(view, str2);
        this.mFeatures = list;
        this.mEventKey = str;
        this.mLatLng = latLng;
        this.mPoint = pointF;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public String getKey() {
        return this.mEventKey;
    }

    @Override // com.maplibre.rctmln.events.AbstractEvent, com.maplibre.rctmln.events.IEvent
    public WritableMap getPayload() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        WritableArray writableArrayCreateArray = Arguments.createArray();
        Iterator<Feature> it = this.mFeatures.iterator();
        while (it.hasNext()) {
            writableArrayCreateArray.pushMap(GeoJSONUtils.fromFeature(it.next()));
        }
        writableMapCreateMap.putArray("features", writableArrayCreateArray);
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap2.putDouble("latitude", this.mLatLng.getLatitude());
        writableMapCreateMap2.putDouble("longitude", this.mLatLng.getLongitude());
        writableMapCreateMap.putMap("coordinates", writableMapCreateMap2);
        WritableMap writableMapCreateMap3 = Arguments.createMap();
        writableMapCreateMap3.putDouble("x", this.mPoint.x);
        writableMapCreateMap3.putDouble("y", this.mPoint.y);
        writableMapCreateMap.putMap(Property.SYMBOL_PLACEMENT_POINT, writableMapCreateMap3);
        return writableMapCreateMap;
    }

    public static FeatureClickEvent makeShapeSourceEvent(View view, RCTSource.OnPressEvent onPressEvent) {
        return new FeatureClickEvent(view, EventKeys.SHAPE_SOURCE_LAYER_CLICK, EventTypes.SHAPE_SOURCE_LAYER_CLICK, onPressEvent.features, onPressEvent.latLng, onPressEvent.screenPoint);
    }

    public static FeatureClickEvent makeVectorSourceEvent(View view, RCTSource.OnPressEvent onPressEvent) {
        return new FeatureClickEvent(view, EventKeys.VECTOR_SOURCE_LAYER_CLICK, EventTypes.VECTOR_SOURCE_LAYER_CLICK, onPressEvent.features, onPressEvent.latLng, onPressEvent.screenPoint);
    }

    public static FeatureClickEvent makeRasterSourceEvent(View view, RCTSource.OnPressEvent onPressEvent) {
        return new FeatureClickEvent(view, EventKeys.RASTER_SOURCE_LAYER_CLICK, EventTypes.RASTER_SOURCE_LAYER_CLICK, onPressEvent.features, onPressEvent.latLng, onPressEvent.screenPoint);
    }
}

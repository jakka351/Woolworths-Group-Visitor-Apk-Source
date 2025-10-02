package com.maplibre.rctmln.events;

import android.graphics.PointF;
import android.view.View;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.maplibre.rctmln.events.constants.EventKeys;
import com.maplibre.rctmln.events.constants.EventTypes;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import org.maplibre.android.geometry.LatLng;

/* loaded from: classes3.dex */
public class MapClickEvent extends AbstractEvent {
    private PointF mScreenPoint;
    private LatLng mTouchedLatLng;

    public MapClickEvent(View view, LatLng latLng, PointF pointF) {
        this(view, latLng, pointF, EventTypes.MAP_CLICK);
    }

    public MapClickEvent(View view, LatLng latLng, PointF pointF, String str) {
        super(view, str);
        this.mTouchedLatLng = latLng;
        this.mScreenPoint = pointF;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public String getKey() {
        if (getType().equals(EventTypes.MAP_LONG_CLICK)) {
            return EventKeys.MAP_LONG_CLICK;
        }
        return EventKeys.MAP_CLICK;
    }

    @Override // com.maplibre.rctmln.events.AbstractEvent, com.maplibre.rctmln.events.IEvent
    public WritableMap getPayload() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putDouble("screenPointX", this.mScreenPoint.x);
        writableNativeMap.putDouble("screenPointY", this.mScreenPoint.y);
        return GeoJSONUtils.toPointFeature(this.mTouchedLatLng, writableNativeMap);
    }
}

package com.maplibre.rctmln.events;

import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.maplibre.rctmln.events.constants.EventKeys;
import com.maplibre.rctmln.events.constants.EventTypes;
import com.maplibre.rctmln.location.UserTrackingMode;

/* loaded from: classes3.dex */
public class MapUserTrackingModeEvent extends AbstractEvent {
    private int mUserTrackingMode;

    public MapUserTrackingModeEvent(View view, int i) {
        super(view, EventTypes.MAP_USER_TRACKING_MODE_CHANGE);
        this.mUserTrackingMode = i;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public String getKey() {
        return EventKeys.MAP_USER_TRACKING_MODE_CHANGE;
    }

    @Override // com.maplibre.rctmln.events.AbstractEvent, com.maplibre.rctmln.events.IEvent
    public WritableMap getPayload() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putBoolean("followUserLocation", this.mUserTrackingMode != 0);
        writableMapCreateMap.putString("followUserMode", UserTrackingMode.toString(this.mUserTrackingMode));
        return writableMapCreateMap;
    }
}

package com.maplibre.rctmln.events;

import android.location.Location;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.events.constants.EventKeys;
import com.maplibre.rctmln.events.constants.EventTypes;
import java.util.UUID;

/* loaded from: classes3.dex */
public class LocationEvent implements IEvent {
    private Location location;
    private RCTMLNMapView mapView;
    private UUID uuid;

    @Override // com.maplibre.rctmln.events.IEvent
    public boolean canCoalesce() {
        return true;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public String getType() {
        return EventTypes.USER_LOCATION_UPDATED;
    }

    public LocationEvent(Location location, RCTMLNMapView rCTMLNMapView) {
        this.mapView = rCTMLNMapView;
        this.location = location;
        this.uuid = UUID.randomUUID();
    }

    public LocationEvent(Location location) {
        this(location, null);
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public int getID() {
        RCTMLNMapView rCTMLNMapView = this.mapView;
        if (rCTMLNMapView != null) {
            return rCTMLNMapView.getId();
        }
        return -1;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public String getKey() {
        return EventKeys.USER_LOCATION_UPDATE;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public boolean equals(IEvent iEvent) {
        return getUUID().equals(((LocationEvent) iEvent).getUUID());
    }

    public boolean equals(LocationEvent locationEvent) {
        return this.uuid.equals(locationEvent.getUUID());
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public WritableMap getPayload() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putDouble("longitude", this.location.getLongitude());
        writableNativeMap2.putDouble("latitude", this.location.getLatitude());
        writableNativeMap2.putDouble("altitude", this.location.getAltitude());
        writableNativeMap2.putDouble("accuracy", this.location.getAccuracy());
        writableNativeMap2.putDouble("heading", this.location.getBearing());
        writableNativeMap2.putDouble("course", this.location.getBearing());
        writableNativeMap2.putDouble("speed", this.location.getSpeed());
        writableNativeMap.putMap("coords", writableNativeMap2);
        writableNativeMap.putDouble("timestamp", this.location.getTime());
        return writableNativeMap;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public WritableMap toJSON() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("type", getType());
        writableMapCreateMap.putMap("payload", getPayload());
        return writableMapCreateMap;
    }
}

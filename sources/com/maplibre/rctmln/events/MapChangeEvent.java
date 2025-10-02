package com.maplibre.rctmln.events;

import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.maplibre.rctmln.events.constants.EventKeys;

/* loaded from: classes3.dex */
public class MapChangeEvent extends AbstractEvent {
    private WritableMap mPayload;

    @Override // com.maplibre.rctmln.events.AbstractEvent, com.maplibre.rctmln.events.IEvent
    public boolean canCoalesce() {
        return false;
    }

    public MapChangeEvent(View view, String str) {
        this(view, str, Arguments.createMap());
    }

    public MapChangeEvent(View view, String str, WritableMap writableMap) {
        super(view, str);
        this.mPayload = writableMap;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public String getKey() {
        return EventKeys.MAP_ONCHANGE;
    }

    @Override // com.maplibre.rctmln.events.AbstractEvent, com.maplibre.rctmln.events.IEvent
    public WritableMap getPayload() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.merge(this.mPayload);
        return writableMapCreateMap;
    }
}

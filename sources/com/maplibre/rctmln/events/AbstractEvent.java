package com.maplibre.rctmln.events;

import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

/* loaded from: classes3.dex */
public abstract class AbstractEvent implements IEvent {
    private String mEventType;
    private int mTagID;
    private long mTimestamp;

    @Override // com.maplibre.rctmln.events.IEvent
    public boolean canCoalesce() {
        return true;
    }

    public AbstractEvent(String str) {
        this(null, str);
    }

    public AbstractEvent(View view, String str) {
        this.mEventType = str;
        if (view != null) {
            this.mTagID = view.getId();
        }
        this.mTimestamp = System.currentTimeMillis();
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public int getID() {
        return this.mTagID;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public String getType() {
        return this.mEventType;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public boolean equals(IEvent iEvent) {
        return getKey().equals(iEvent.getKey()) && this.mEventType.equals(iEvent.getType());
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public WritableMap getPayload() {
        return Arguments.createMap();
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public long getTimestamp() {
        return this.mTimestamp;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public WritableMap toJSON() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("type", getType());
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap2.merge(getPayload());
        writableMapCreateMap.putMap("payload", writableMapCreateMap2);
        return writableMapCreateMap;
    }
}

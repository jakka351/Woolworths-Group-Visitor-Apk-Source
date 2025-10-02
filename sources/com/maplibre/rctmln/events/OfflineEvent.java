package com.maplibre.rctmln.events;

import com.facebook.react.bridge.WritableMap;

/* loaded from: classes3.dex */
public class OfflineEvent extends AbstractEvent {
    private String mEventKey;
    private WritableMap mPayload;

    public OfflineEvent(String str, String str2, WritableMap writableMap) {
        super(str2);
        this.mEventKey = str;
        this.mPayload = writableMap;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public String getKey() {
        return this.mEventKey;
    }

    @Override // com.maplibre.rctmln.events.AbstractEvent, com.maplibre.rctmln.events.IEvent
    public WritableMap getPayload() {
        return this.mPayload;
    }
}

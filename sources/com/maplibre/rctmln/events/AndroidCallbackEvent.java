package com.maplibre.rctmln.events;

import android.view.View;
import com.facebook.react.bridge.WritableMap;
import com.maplibre.rctmln.events.constants.EventKeys;

/* loaded from: classes3.dex */
public class AndroidCallbackEvent extends AbstractEvent {
    private final WritableMap mPayload;

    @Override // com.maplibre.rctmln.events.AbstractEvent, com.maplibre.rctmln.events.IEvent
    public boolean canCoalesce() {
        return false;
    }

    public AndroidCallbackEvent(View view, String str, WritableMap writableMap) {
        super(view, str);
        this.mPayload = writableMap;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public String getKey() {
        return EventKeys.MAP_ANDROID_CALLBACK;
    }

    @Override // com.maplibre.rctmln.events.AbstractEvent, com.maplibre.rctmln.events.IEvent
    public WritableMap getPayload() {
        return this.mPayload;
    }
}

package com.maplibre.rctmln.components;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public class AbstractEvent extends Event<AbstractEvent> {
    private final boolean mCanCoalesce;
    private WritableMap mEvent;
    private String mEventName;

    public AbstractEvent(int i, String str, boolean z, @Nullable WritableMap writableMap) {
        super(i);
        this.mEventName = str;
        this.mCanCoalesce = z;
        this.mEvent = writableMap;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return this.mEventName;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), this.mEvent);
    }

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return this.mCanCoalesce;
    }
}

package com.maplibre.rctmln.utils;

import com.maplibre.rctmln.components.AbstractEventEmitter;
import com.maplibre.rctmln.events.IEvent;
import org.maplibre.android.maps.MapLibreMap;

/* loaded from: classes3.dex */
public class SimpleEventCallback implements MapLibreMap.CancelableCallback {
    private IEvent mEvent;
    private AbstractEventEmitter mEventEmitter;

    public SimpleEventCallback(AbstractEventEmitter abstractEventEmitter, IEvent iEvent) {
        this.mEventEmitter = abstractEventEmitter;
        this.mEvent = iEvent;
    }

    @Override // org.maplibre.android.maps.MapLibreMap.CancelableCallback
    public void onCancel() {
        this.mEventEmitter.handleEvent(this.mEvent);
    }

    @Override // org.maplibre.android.maps.MapLibreMap.CancelableCallback
    public void onFinish() {
        this.mEventEmitter.handleEvent(this.mEvent);
    }
}

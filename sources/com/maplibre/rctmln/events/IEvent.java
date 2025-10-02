package com.maplibre.rctmln.events;

import com.facebook.react.bridge.WritableMap;

/* loaded from: classes3.dex */
public interface IEvent {
    boolean canCoalesce();

    boolean equals(IEvent iEvent);

    int getID();

    String getKey();

    WritableMap getPayload();

    long getTimestamp();

    String getType();

    WritableMap toJSON();
}

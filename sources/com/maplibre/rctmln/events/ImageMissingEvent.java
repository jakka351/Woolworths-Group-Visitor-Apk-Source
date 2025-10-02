package com.maplibre.rctmln.events;

import android.view.View;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.maplibre.rctmln.events.constants.EventKeys;
import com.maplibre.rctmln.events.constants.EventTypes;

/* loaded from: classes3.dex */
public class ImageMissingEvent extends AbstractEvent {
    private String mEventKey;
    private String mImageKey;

    @Override // com.maplibre.rctmln.events.AbstractEvent, com.maplibre.rctmln.events.IEvent
    public boolean canCoalesce() {
        return false;
    }

    public ImageMissingEvent(View view, String str, String str2, String str3) {
        super(view, str2);
        this.mImageKey = str3;
        this.mEventKey = str;
    }

    @Override // com.maplibre.rctmln.events.IEvent
    public String getKey() {
        return this.mEventKey;
    }

    @Override // com.maplibre.rctmln.events.AbstractEvent, com.maplibre.rctmln.events.IEvent
    public WritableMap getPayload() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("imageKey", this.mImageKey);
        return writableNativeMap;
    }

    public static ImageMissingEvent makeImageMissingEvent(View view, String str) {
        return new ImageMissingEvent(view, EventKeys.IMAGES_MISSING, EventTypes.IMAGES_MISSING, str);
    }
}

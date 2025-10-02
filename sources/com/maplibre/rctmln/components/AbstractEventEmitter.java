package com.maplibre.rctmln.components;

import android.view.ViewGroup;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.maplibre.rctmln.events.IEvent;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public abstract class AbstractEventEmitter<T extends ViewGroup> extends ViewGroupManager<T> {
    private static final double BRIDGE_TIMEOUT_MS = 10.0d;
    private EventDispatcher mEventDispatcher;
    private ReactApplicationContext mRCTAppContext;
    private Map<String, Long> mRateLimitedEvents = new HashMap();

    @Nullable
    public abstract Map<String, String> customEvents();

    public AbstractEventEmitter(ReactApplicationContext reactApplicationContext) {
        this.mRCTAppContext = reactApplicationContext;
    }

    public void handleEvent(IEvent iEvent) {
        String eventCacheKey = getEventCacheKey(iEvent);
        if (shouldDropEvent(eventCacheKey, iEvent)) {
            return;
        }
        this.mRateLimitedEvents.put(eventCacheKey, Long.valueOf(System.currentTimeMillis()));
        this.mEventDispatcher.dispatchEvent(new AbstractEvent(iEvent.getID(), iEvent.getKey(), iEvent.canCoalesce(), iEvent.toJSON()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, @Nonnull T t) {
        this.mEventDispatcher = ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher();
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, String> mapCustomEvents = customEvents();
        if (mapCustomEvents == null) {
            return null;
        }
        HashMap map = new HashMap();
        for (Map.Entry<String, String> entry : mapCustomEvents.entrySet()) {
            map.put(entry.getKey(), MapBuilder.of("registrationName", entry.getValue()));
        }
        return map;
    }

    private boolean shouldDropEvent(String str, IEvent iEvent) {
        Long l = this.mRateLimitedEvents.get(str);
        return l != null && ((double) (iEvent.getTimestamp() - l.longValue())) <= BRIDGE_TIMEOUT_MS;
    }

    private String getEventCacheKey(IEvent iEvent) {
        return String.format("%s-%s", iEvent.getKey(), iEvent.getType());
    }
}

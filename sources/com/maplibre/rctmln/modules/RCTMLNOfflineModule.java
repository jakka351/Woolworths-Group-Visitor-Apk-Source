package com.maplibre.rctmln.modules;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.maplibre.rctmln.events.IEvent;
import com.maplibre.rctmln.events.OfflineEvent;
import com.maplibre.rctmln.events.constants.EventTypes;
import com.maplibre.rctmln.utils.ConvertUtils;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import io.sentry.protocol.SentryThread;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.android.offline.OfflineManager;
import org.maplibre.android.offline.OfflineRegion;
import org.maplibre.android.offline.OfflineRegionDefinition;
import org.maplibre.android.offline.OfflineRegionError;
import org.maplibre.android.offline.OfflineRegionStatus;
import org.maplibre.android.offline.OfflineTilePyramidRegionDefinition;
import org.maplibre.android.storage.FileSource;
import org.maplibre.geojson.FeatureCollection;

@ReactModule(name = RCTMLNOfflineModule.REACT_CLASS)
/* loaded from: classes3.dex */
public class RCTMLNOfflineModule extends ReactContextBaseJavaModule {
    public static final int ACTIVE_REGION_DOWNLOAD_STATE = 1;
    public static final int COMPLETE_REGION_DOWNLOAD_STATE = 2;
    public static final String DEFAULT_STYLE_URL = "https://demotiles.maplibre.org/style.json";
    public static final int INACTIVE_REGION_DOWNLOAD_STATE = 0;
    public static final String OFFLINE_ERROR = "MapboxOfflineRegionError";
    public static final String OFFLINE_PROGRESS = "MapboxOfflineRegionProgress";
    public static final String REACT_CLASS = "RCTMLNOfflineModule";
    private final Context mContext;
    private Double mProgressEventThrottle;
    private final ReactContext mReactContext;
    public static final Double DEFAULT_MIN_ZOOM_LEVEL = Double.valueOf(10.0d);
    public static final Double DEFAULT_MAX_ZOOM_LEVEL = Double.valueOf(20.0d);

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public RCTMLNOfflineModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mProgressEventThrottle = Double.valueOf(300.0d);
        this.mReactContext = reactApplicationContext;
        this.mContext = reactApplicationContext.getApplicationContext();
    }

    @ReactMethod
    public void createPack(ReadableMap readableMap, final Promise promise) {
        final String string = ConvertUtils.getString("name", readableMap, "");
        OfflineManager.INSTANCE.getInstance(this.mContext).createOfflineRegion(makeDefinition(getBoundsFromOptions(readableMap), readableMap), getMetadataBytes(ConvertUtils.getString("metadata", readableMap, "")), new OfflineManager.CreateOfflineRegionCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.1
            @Override // org.maplibre.android.offline.OfflineManager.CreateOfflineRegionCallback
            public void onCreate(OfflineRegion offlineRegion) {
                promise.resolve(RCTMLNOfflineModule.this.fromOfflineRegion(offlineRegion));
                RCTMLNOfflineModule.this.setOfflineRegionObserver(string, offlineRegion);
            }

            @Override // org.maplibre.android.offline.OfflineManager.CreateOfflineRegionCallback
            public void onError(String str) {
                RCTMLNOfflineModule rCTMLNOfflineModule = RCTMLNOfflineModule.this;
                rCTMLNOfflineModule.sendEvent(rCTMLNOfflineModule.makeErrorEvent(string, EventTypes.OFFLINE_ERROR, str));
            }
        });
    }

    @ReactMethod
    public void getPacks(final Promise promise) {
        activateFileSource();
        OfflineManager.INSTANCE.getInstance(this.mContext).listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.2
            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onList(OfflineRegion[] offlineRegionArr) {
                WritableArray writableArrayCreateArray = Arguments.createArray();
                for (OfflineRegion offlineRegion : offlineRegionArr) {
                    writableArrayCreateArray.pushMap(RCTMLNOfflineModule.this.fromOfflineRegion(offlineRegion));
                }
                promise.resolve(writableArrayCreateArray);
            }

            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onError(String str) {
                promise.reject("getRegions", str);
            }
        });
    }

    @ReactMethod
    public void invalidateAmbientCache(final Promise promise) {
        activateFileSource();
        OfflineManager.INSTANCE.getInstance(this.mContext).invalidateAmbientCache(new OfflineManager.FileSourceCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.3
            @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
            public void onSuccess() {
                promise.resolve(null);
            }

            @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
            public void onError(String str) {
                promise.reject("invalidateAmbientCache", str);
            }
        });
    }

    @ReactMethod
    public void clearAmbientCache(final Promise promise) {
        activateFileSource();
        OfflineManager.INSTANCE.getInstance(this.mContext).clearAmbientCache(new OfflineManager.FileSourceCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.4
            @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
            public void onSuccess() {
                promise.resolve(null);
            }

            @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
            public void onError(String str) {
                promise.reject("clearAmbientCache", str);
            }
        });
    }

    @ReactMethod
    public void setMaximumAmbientCacheSize(int i, final Promise promise) {
        activateFileSource();
        OfflineManager.INSTANCE.getInstance(this.mContext).setMaximumAmbientCacheSize(i, new OfflineManager.FileSourceCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.5
            @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
            public void onSuccess() {
                promise.resolve(null);
            }

            @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
            public void onError(String str) {
                promise.reject("setMaximumAmbientCacheSize", str);
            }
        });
    }

    @ReactMethod
    public void resetDatabase(final Promise promise) {
        activateFileSource();
        OfflineManager.INSTANCE.getInstance(this.mContext).resetDatabase(new OfflineManager.FileSourceCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.6
            @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
            public void onSuccess() {
                promise.resolve(null);
            }

            @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
            public void onError(String str) {
                promise.reject("resetDatabase", str);
            }
        });
    }

    @ReactMethod
    public void getPackStatus(final String str, final Promise promise) {
        activateFileSource();
        OfflineManager.INSTANCE.getInstance(this.mContext).listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.7
            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onList(OfflineRegion[] offlineRegionArr) {
                OfflineRegion regionByName = RCTMLNOfflineModule.this.getRegionByName(str, offlineRegionArr);
                if (regionByName == null) {
                    promise.resolve(null);
                    Log.w(RCTMLNOfflineModule.REACT_CLASS, "getPackStatus - Unknown offline region");
                } else {
                    regionByName.getStatus(new OfflineRegion.OfflineRegionStatusCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.7.1
                        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionStatusCallback
                        public void onStatus(OfflineRegionStatus offlineRegionStatus) {
                            promise.resolve(RCTMLNOfflineModule.this.makeRegionStatus(str, offlineRegionStatus));
                        }

                        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionStatusCallback
                        public void onError(String str2) {
                            promise.reject("getPackStatus", str2);
                        }
                    });
                }
            }

            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onError(String str2) {
                promise.reject("getPackStatus", str2);
            }
        });
    }

    @ReactMethod
    public void setPackObserver(final String str, final Promise promise) {
        activateFileSource();
        OfflineManager.INSTANCE.getInstance(this.mContext).listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.8
            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onList(OfflineRegion[] offlineRegionArr) {
                OfflineRegion regionByName = RCTMLNOfflineModule.this.getRegionByName(str, offlineRegionArr);
                boolean z = regionByName != null;
                if (z) {
                    RCTMLNOfflineModule.this.setOfflineRegionObserver(str, regionByName);
                }
                promise.resolve(Boolean.valueOf(z));
            }

            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onError(String str2) {
                promise.reject("setPackObserver", str2);
            }
        });
    }

    @ReactMethod
    public void invalidatePack(final String str, final Promise promise) {
        activateFileSource();
        OfflineManager.INSTANCE.getInstance(this.mContext).listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.9
            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onList(OfflineRegion[] offlineRegionArr) {
                OfflineRegion regionByName = RCTMLNOfflineModule.this.getRegionByName(str, offlineRegionArr);
                if (regionByName == null) {
                    promise.resolve(null);
                    Log.w(RCTMLNOfflineModule.REACT_CLASS, "invalidateRegion - Unknown offline region");
                } else {
                    regionByName.invalidate(new OfflineRegion.OfflineRegionInvalidateCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.9.1
                        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionInvalidateCallback
                        public void onInvalidate() {
                            promise.resolve(null);
                        }

                        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionInvalidateCallback
                        public void onError(String str2) {
                            promise.reject("invalidateRegion", str2);
                        }
                    });
                }
            }

            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onError(String str2) {
                promise.reject("invalidateRegion", str2);
            }
        });
    }

    @ReactMethod
    public void deletePack(final String str, final Promise promise) {
        activateFileSource();
        OfflineManager.INSTANCE.getInstance(this.mContext).listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.10
            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onList(OfflineRegion[] offlineRegionArr) {
                OfflineRegion regionByName = RCTMLNOfflineModule.this.getRegionByName(str, offlineRegionArr);
                if (regionByName == null) {
                    promise.resolve(null);
                    Log.w(RCTMLNOfflineModule.REACT_CLASS, "deleteRegion - Unknown offline region");
                } else {
                    regionByName.setDownloadState(0);
                    regionByName.delete(new OfflineRegion.OfflineRegionDeleteCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.10.1
                        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionDeleteCallback
                        public void onDelete() {
                            promise.resolve(null);
                        }

                        @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionDeleteCallback
                        public void onError(String str2) {
                            promise.reject("deleteRegion", str2);
                        }
                    });
                }
            }

            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onError(String str2) {
                promise.reject("deleteRegion", str2);
            }
        });
    }

    @ReactMethod
    public void pausePackDownload(final String str, final Promise promise) {
        activateFileSource();
        OfflineManager.INSTANCE.getInstance(this.mContext).listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.11
            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onList(OfflineRegion[] offlineRegionArr) {
                final OfflineRegion regionByName = RCTMLNOfflineModule.this.getRegionByName(str, offlineRegionArr);
                if (regionByName == null) {
                    promise.reject("pauseRegionDownload", "Unknown offline region");
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.11.1
                        @Override // java.lang.Runnable
                        public void run() {
                            regionByName.setDownloadState(0);
                            promise.resolve(null);
                        }
                    });
                }
            }

            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onError(String str2) {
                promise.reject("pauseRegionDownload", str2);
            }
        });
    }

    @ReactMethod
    public void resumePackDownload(final String str, final Promise promise) {
        activateFileSource();
        OfflineManager.INSTANCE.getInstance(this.mContext).listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.12
            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onList(OfflineRegion[] offlineRegionArr) {
                OfflineRegion regionByName = RCTMLNOfflineModule.this.getRegionByName(str, offlineRegionArr);
                if (regionByName == null) {
                    promise.reject("resumeRegionDownload", "Unknown offline region");
                } else {
                    regionByName.setDownloadState(1);
                    promise.resolve(null);
                }
            }

            @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
            public void onError(String str2) {
                promise.reject("resumeRegionDownload", str2);
            }
        });
    }

    @ReactMethod
    public void mergeOfflineRegions(String str, final Promise promise) {
        activateFileSource();
        OfflineManager.INSTANCE.getInstance(this.mContext).mergeOfflineRegions(str, new OfflineManager.MergeOfflineRegionsCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.13
            @Override // org.maplibre.android.offline.OfflineManager.MergeOfflineRegionsCallback
            public void onMerge(OfflineRegion[] offlineRegionArr) {
                promise.resolve(null);
            }

            @Override // org.maplibre.android.offline.OfflineManager.MergeOfflineRegionsCallback
            public void onError(String str2) {
                promise.reject("mergeOfflineRegions", str2);
            }
        });
    }

    @ReactMethod
    public void setTileCountLimit(int i) {
        OfflineManager.INSTANCE.getInstance(this.mContext).setOfflineMapboxTileCountLimit(i);
    }

    @ReactMethod
    public void setProgressEventThrottle(double d) {
        this.mProgressEventThrottle = Double.valueOf(d);
    }

    private OfflineRegionDefinition makeDefinition(LatLngBounds latLngBounds, ReadableMap readableMap) {
        return new OfflineTilePyramidRegionDefinition(ConvertUtils.getString("styleURL", readableMap, DEFAULT_STYLE_URL), latLngBounds, ConvertUtils.getDouble("minZoom", readableMap, DEFAULT_MIN_ZOOM_LEVEL.doubleValue()), ConvertUtils.getDouble("maxZoom", readableMap, DEFAULT_MAX_ZOOM_LEVEL.doubleValue()), this.mContext.getResources().getDisplayMetrics().density);
    }

    private byte[] getMetadataBytes(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            Log.w(REACT_CLASS, e.getLocalizedMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOfflineRegionObserver(final String str, OfflineRegion offlineRegion) {
        offlineRegion.setObserver(new OfflineRegion.OfflineRegionObserver() { // from class: com.maplibre.rctmln.modules.RCTMLNOfflineModule.14
            OfflineRegionStatus prevStatus = null;
            long timestamp = System.currentTimeMillis();

            @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionObserver
            public void onStatusChanged(OfflineRegionStatus offlineRegionStatus) {
                if (shouldSendUpdate(System.currentTimeMillis(), offlineRegionStatus)) {
                    RCTMLNOfflineModule rCTMLNOfflineModule = RCTMLNOfflineModule.this;
                    rCTMLNOfflineModule.sendEvent(rCTMLNOfflineModule.makeStatusEvent(str, offlineRegionStatus));
                    this.timestamp = System.currentTimeMillis();
                }
                this.prevStatus = offlineRegionStatus;
            }

            @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionObserver
            public void onError(OfflineRegionError offlineRegionError) {
                RCTMLNOfflineModule rCTMLNOfflineModule = RCTMLNOfflineModule.this;
                rCTMLNOfflineModule.sendEvent(rCTMLNOfflineModule.makeErrorEvent(str, EventTypes.OFFLINE_ERROR, offlineRegionError.getMessage()));
            }

            @Override // org.maplibre.android.offline.OfflineRegion.OfflineRegionObserver
            public void mapboxTileCountLimitExceeded(long j) {
                String str2 = String.format(Locale.getDefault(), "Mapbox tile limit exceeded %d", Long.valueOf(j));
                RCTMLNOfflineModule rCTMLNOfflineModule = RCTMLNOfflineModule.this;
                rCTMLNOfflineModule.sendEvent(rCTMLNOfflineModule.makeErrorEvent(str, EventTypes.OFFLINE_TILE_LIMIT, str2));
            }

            private boolean shouldSendUpdate(long j, OfflineRegionStatus offlineRegionStatus) {
                OfflineRegionStatus offlineRegionStatus2 = this.prevStatus;
                if (offlineRegionStatus2 == null) {
                    return false;
                }
                return offlineRegionStatus2.getDownloadState() != offlineRegionStatus.getDownloadState() || ((double) (j - this.timestamp)) > RCTMLNOfflineModule.this.mProgressEventThrottle.doubleValue();
            }
        });
        offlineRegion.setDownloadState(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(IEvent iEvent) {
        getEventEmitter().emit(iEvent.getKey(), iEvent.toJSON());
    }

    private RCTNativeAppEventEmitter getEventEmitter() {
        return (RCTNativeAppEventEmitter) this.mReactContext.getJSModule(RCTNativeAppEventEmitter.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OfflineEvent makeErrorEvent(String str, String str2, String str3) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("message", str3);
        writableNativeMap.putString("name", str);
        return new OfflineEvent(OFFLINE_ERROR, str2, writableNativeMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OfflineEvent makeStatusEvent(String str, OfflineRegionStatus offlineRegionStatus) {
        return new OfflineEvent(OFFLINE_PROGRESS, EventTypes.OFFLINE_STATUS, makeRegionStatus(str, offlineRegionStatus));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WritableMap makeRegionStatus(String str, OfflineRegionStatus offlineRegionStatus) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        int downloadState = offlineRegionStatus.getDownloadState();
        double completedResourceCount = 100.0d;
        if (offlineRegionStatus.isComplete()) {
            downloadState = 2;
        } else {
            completedResourceCount = offlineRegionStatus.getRequiredResourceCount() >= 0 ? (offlineRegionStatus.getCompletedResourceCount() * 100.0d) / offlineRegionStatus.getRequiredResourceCount() : 0.0d;
        }
        writableMapCreateMap.putString("name", str);
        writableMapCreateMap.putInt(SentryThread.JsonKeys.STATE, downloadState);
        writableMapCreateMap.putDouble("percentage", completedResourceCount);
        writableMapCreateMap.putInt("completedResourceCount", (int) offlineRegionStatus.getCompletedResourceCount());
        writableMapCreateMap.putInt("completedResourceSize", (int) offlineRegionStatus.getCompletedResourceSize());
        writableMapCreateMap.putInt("completedTileSize", (int) offlineRegionStatus.getCompletedTileSize());
        writableMapCreateMap.putInt("completedTileCount", (int) offlineRegionStatus.getCompletedTileCount());
        writableMapCreateMap.putInt("requiredResourceCount", (int) offlineRegionStatus.getRequiredResourceCount());
        return writableMapCreateMap;
    }

    private LatLngBounds getBoundsFromOptions(ReadableMap readableMap) {
        return GeoJSONUtils.toLatLngBounds(FeatureCollection.fromJson(ConvertUtils.getString("bounds", readableMap, "{}")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WritableMap fromOfflineRegion(OfflineRegion offlineRegion) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putArray("bounds", GeoJSONUtils.fromLatLngBounds(offlineRegion.getDefinition().getBounds()));
        writableMapCreateMap.putString("metadata", new String(offlineRegion.getMetadata()));
        return writableMapCreateMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public OfflineRegion getRegionByName(String str, OfflineRegion[] offlineRegionArr) {
        byte[] metadata;
        if (str != null && !str.isEmpty()) {
            for (OfflineRegion offlineRegion : offlineRegionArr) {
                try {
                    metadata = offlineRegion.getMetadata();
                } catch (JSONException e) {
                    Log.w(REACT_CLASS, e.getLocalizedMessage());
                }
                boolean zEquals = metadata != null ? str.equals(new JSONObject(new String(metadata)).getString("name")) : false;
                if (zEquals) {
                    return offlineRegion;
                }
            }
        }
        return null;
    }

    private void activateFileSource() {
        FileSource.getInstance(this.mContext).activate();
    }
}

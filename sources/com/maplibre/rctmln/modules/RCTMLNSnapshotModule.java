package com.maplibre.rctmln.modules;

import android.graphics.Bitmap;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.maplibre.rctmln.utils.BitmapUtils;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.snapshotter.MapSnapshot;
import org.maplibre.android.snapshotter.MapSnapshotter;
import org.maplibre.android.storage.FileSource;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;
import org.maplibre.geojson.Point;

@ReactModule(name = RCTMLNSnapshotModule.REACT_CLASS)
/* loaded from: classes3.dex */
public class RCTMLNSnapshotModule extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "RCTMLNSnapshotModule";
    private ReactApplicationContext mContext;
    private Map<String, MapSnapshotter> mSnapshotterMap;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public RCTMLNSnapshotModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mContext = reactApplicationContext;
        this.mSnapshotterMap = new HashMap();
    }

    @ReactMethod
    public void takeSnap(final ReadableMap readableMap, final Promise promise) {
        FileSource.getInstance(this.mContext).activate();
        this.mContext.runOnUiQueueThread(new Runnable() { // from class: com.maplibre.rctmln.modules.RCTMLNSnapshotModule.1
            @Override // java.lang.Runnable
            public void run() {
                final String string = UUID.randomUUID().toString();
                MapSnapshotter mapSnapshotter = new MapSnapshotter(RCTMLNSnapshotModule.this.mContext, RCTMLNSnapshotModule.this.getOptions(readableMap));
                RCTMLNSnapshotModule.this.mSnapshotterMap.put(string, mapSnapshotter);
                mapSnapshotter.start(new MapSnapshotter.SnapshotReadyCallback() { // from class: com.maplibre.rctmln.modules.RCTMLNSnapshotModule.1.1
                    @Override // org.maplibre.android.snapshotter.MapSnapshotter.SnapshotReadyCallback
                    public void onSnapshotReady(MapSnapshot mapSnapshot) throws IOException {
                        String strCreateBase64;
                        Bitmap bitmap = mapSnapshot.getBitmap();
                        if (readableMap.getBoolean("writeToDisk")) {
                            strCreateBase64 = BitmapUtils.createTempFile(RCTMLNSnapshotModule.this.mContext, bitmap);
                        } else {
                            strCreateBase64 = BitmapUtils.createBase64(bitmap);
                        }
                        if (strCreateBase64 == null) {
                            promise.reject(RCTMLNSnapshotModule.REACT_CLASS, "Could not generate snapshot, please check Android logs for more info.");
                        } else {
                            promise.resolve(strCreateBase64);
                            RCTMLNSnapshotModule.this.mSnapshotterMap.remove(string);
                        }
                    }
                }, new MapSnapshotter.ErrorHandler() { // from class: com.maplibre.rctmln.modules.RCTMLNSnapshotModule.1.2
                    @Override // org.maplibre.android.snapshotter.MapSnapshotter.ErrorHandler
                    public void onError(String str) {
                        Log.w(RCTMLNSnapshotModule.REACT_CLASS, str);
                        RCTMLNSnapshotModule.this.mSnapshotterMap.remove(string);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapSnapshotter.Options getOptions(ReadableMap readableMap) {
        MapSnapshotter.Options options = new MapSnapshotter.Options((int) readableMap.getDouble("width"), (int) readableMap.getDouble("height"));
        options.withLogo(readableMap.getBoolean("withLogo"));
        options.withStyle(readableMap.getString("styleURL"));
        options.withPixelRatio(Float.valueOf(this.mContext.getResources().getDisplayMetrics().scaledDensity).intValue());
        if (readableMap.hasKey("bounds")) {
            options.withRegion(GeoJSONUtils.toLatLngBounds(FeatureCollection.fromJson(readableMap.getString("bounds"))));
        } else {
            options.withCameraPosition(new CameraPosition.Builder().target(GeoJSONUtils.toLatLng((Point) Feature.fromJson(readableMap.getString("centerCoordinate")).geometry())).tilt(readableMap.getDouble("pitch")).bearing(readableMap.getDouble("heading")).zoom(readableMap.getDouble("zoomLevel")).build());
        }
        return options;
    }

    private void closeSnapshotOutputStream(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            return;
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            Log.w(REACT_CLASS, e.getLocalizedMessage());
        }
    }
}

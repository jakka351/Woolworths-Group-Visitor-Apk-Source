package com.maplibre.rctmln.components.camera;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.maplibre.rctmln.components.AbstractEventEmitter;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import java.util.HashMap;
import java.util.Map;
import org.maplibre.geojson.FeatureCollection;

/* loaded from: classes3.dex */
public class RCTMLNCameraManager extends AbstractEventEmitter<RCTMLNCamera> {
    public static final String REACT_CLASS = "RCTMLNCamera";
    private ReactApplicationContext mContext;

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public RCTMLNCameraManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mContext = reactApplicationContext;
    }

    @Override // com.maplibre.rctmln.components.AbstractEventEmitter
    public Map<String, String> customEvents() {
        return new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNCamera createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNCamera(themedReactContext, this);
    }

    @ReactProp(name = "stop")
    public void setStop(RCTMLNCamera rCTMLNCamera, ReadableMap readableMap) {
        if (readableMap != null) {
            rCTMLNCamera.setStop(CameraStop.fromReadableMap(this.mContext, readableMap, null));
        }
    }

    @ReactProp(name = "defaultStop")
    public void setDefaultStop(RCTMLNCamera rCTMLNCamera, ReadableMap readableMap) {
        if (readableMap != null) {
            rCTMLNCamera.setDefaultStop(CameraStop.fromReadableMap(this.mContext, readableMap, null));
        }
    }

    @ReactProp(name = "maxBounds")
    public void setMaxBounds(RCTMLNCamera rCTMLNCamera, String str) {
        if (str != null) {
            rCTMLNCamera.setMaxBounds(GeoJSONUtils.toLatLngBounds(FeatureCollection.fromJson(str)));
        }
    }

    @ReactProp(name = "userTrackingMode")
    public void setUserTrackingMode(RCTMLNCamera rCTMLNCamera, int i) {
        rCTMLNCamera.setUserTrackingMode(i);
        throw new AssertionError("Unused code");
    }

    @ReactProp(name = "followZoomLevel")
    public void setZoomLevel(RCTMLNCamera rCTMLNCamera, double d) {
        rCTMLNCamera.setZoomLevel(d);
    }

    @ReactProp(name = "followUserLocation")
    public void setFollowUserLocation(RCTMLNCamera rCTMLNCamera, boolean z) {
        rCTMLNCamera.setFollowUserLocation(z);
    }

    @ReactProp(name = "followUserMode")
    public void setFollowUserMode(RCTMLNCamera rCTMLNCamera, String str) {
        rCTMLNCamera.setFollowUserMode(str);
    }

    @ReactProp(name = "minZoomLevel")
    public void setMinZoomLevel(RCTMLNCamera rCTMLNCamera, double d) {
        rCTMLNCamera.setMinZoomLevel(d);
    }

    @ReactProp(name = "maxZoomLevel")
    public void setMaxZoomLevel(RCTMLNCamera rCTMLNCamera, double d) {
        rCTMLNCamera.setMaxZoomLevel(d);
    }

    @ReactProp(name = "followPitch")
    public void setFollowPitch(RCTMLNCamera rCTMLNCamera, double d) {
        rCTMLNCamera.setFollowPitch(d);
    }
}

package com.maplibre.rctmln.modules;

import android.os.Handler;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.maplibre.rctmln.components.styles.sources.RCTSource;
import com.maplibre.rctmln.events.constants.EventTypes;
import com.maplibre.rctmln.http.CustomHeadersInterceptor;
import com.oblador.keychain.KeychainModule;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.maplibre.android.MapLibre;
import org.maplibre.android.WellKnownTileServer;
import org.maplibre.android.module.http.HttpRequestUtil;
import org.maplibre.android.style.layers.Property;

@ReactModule(name = RCTMLNModule.REACT_CLASS)
/* loaded from: classes3.dex */
public class RCTMLNModule extends ReactContextBaseJavaModule {
    public static final String REACT_CLASS = "RCTMLNModule";
    private static boolean customHeaderInterceptorAdded = false;
    private ReactApplicationContext mReactContext;
    private Handler mUiThreadHandler;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public RCTMLNModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mReactContext = reactApplicationContext;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        HashMap map = new HashMap();
        map.put("Default", RCTMLNOfflineModule.DEFAULT_STYLE_URL);
        HashMap map2 = new HashMap();
        map2.put("MapClick", EventTypes.MAP_CLICK);
        map2.put("MapLongClick", EventTypes.MAP_LONG_CLICK);
        map2.put("RegionWillChange", EventTypes.REGION_WILL_CHANGE);
        map2.put("RegionIsChanging", EventTypes.REGION_IS_CHANGING);
        map2.put("RegionDidChange", EventTypes.REGION_DID_CHANGE);
        map2.put("UserLocationUpdated", EventTypes.USER_LOCATION_UPDATED);
        map2.put("WillStartLoadingMap", EventTypes.WILL_START_LOADING_MAP);
        map2.put("DidFinishLoadingMap", EventTypes.DID_FINISH_LOADING_MAP);
        map2.put("DidFailLoadingMap", EventTypes.DID_FAIL_LOADING_MAP);
        map2.put("WillStartRenderingFrame", EventTypes.WILL_START_RENDERING_FRAME);
        map2.put("DidFinishRenderingFrame", EventTypes.DID_FINISH_RENDERING_FRAME);
        map2.put("DidFinishRenderingFrameFully", EventTypes.DID_FINISH_RENDERING_FRAME_FULLY);
        map2.put("WillStartRenderingMap", EventTypes.WILL_START_RENDERING_MAP);
        map2.put("DidFinishRenderingMap", EventTypes.DID_FINISH_RENDERING_MAP);
        map2.put("DidFinishRenderingMapFully", EventTypes.DID_FINISH_RENDERING_MAP_FULLY);
        map2.put("DidFinishLoadingStyle", EventTypes.DID_FINISH_LOADING_STYLE);
        HashMap map3 = new HashMap();
        map3.put(KeychainModule.AccessControl.NONE, 0);
        map3.put("Follow", 1);
        map3.put("FollowWithCourse", 2);
        map3.put("FollowWithHeading", 3);
        HashMap map4 = new HashMap();
        map4.put("Center", 0);
        map4.put("Top", 1);
        map4.put("Bottom", 2);
        HashMap map5 = new HashMap();
        map5.put("Flight", 1);
        map5.put("Ease", 2);
        map5.put("Linear", 3);
        map5.put(KeychainModule.AccessControl.NONE, 4);
        HashMap map6 = new HashMap();
        map6.put("DefaultSourceID", RCTSource.DEFAULT_ID);
        HashMap map7 = new HashMap();
        map7.put("Exponential", 100);
        map7.put("Categorical", 102);
        map7.put("Interval", 101);
        map7.put("Identity", 103);
        HashMap map8 = new HashMap();
        map8.put("Bevel", Property.LINE_JOIN_BEVEL);
        map8.put("Round", "round");
        map8.put("Miter", Property.LINE_JOIN_MITER);
        HashMap map9 = new HashMap();
        map9.put("Butt", Property.LINE_CAP_BUTT);
        map9.put("Round", "round");
        map9.put("Square", Property.LINE_CAP_SQUARE);
        HashMap map10 = new HashMap();
        map10.put("Map", "map");
        map10.put("Viewport", "viewport");
        HashMap map11 = new HashMap();
        map11.put("Map", "map");
        map11.put("Viewport", "viewport");
        HashMap map12 = new HashMap();
        map12.put("Map", "map");
        map12.put("Viewport", "viewport");
        HashMap map13 = new HashMap();
        map13.put("Map", "map");
        map13.put("Viewport", "viewport");
        HashMap map14 = new HashMap();
        map14.put("Map", "map");
        map14.put("Viewport", "viewport");
        HashMap map15 = new HashMap();
        map15.put("Map", "map");
        map15.put("Viewport", "viewport");
        HashMap map16 = new HashMap();
        map16.put("Auto", "auto");
        map16.put("Map", "map");
        map16.put("Viewport", "viewport");
        HashMap map17 = new HashMap();
        map17.put(KeychainModule.AccessControl.NONE, "none");
        map17.put("Width", "width");
        map17.put("Height", "height");
        map17.put("Both", Property.ICON_TEXT_FIT_BOTH);
        HashMap map18 = new HashMap();
        map18.put("Center", "center");
        map18.put("Left", "left");
        map18.put("Right", "right");
        map18.put("Top", "top");
        map18.put("Bottom", "bottom");
        map18.put("TopLeft", "top-left");
        map18.put("TopRight", "top-right");
        map18.put("BottomLeft", "bottom-left");
        map18.put("BottomRight", "bottom-right");
        HashMap map19 = new HashMap();
        map19.put("Auto", "auto");
        map19.put("Map", "map");
        map19.put("Viewport", "viewport");
        HashMap map20 = new HashMap();
        map20.put("Map", "map");
        map20.put("Viewport", "viewport");
        HashMap map21 = new HashMap();
        map21.put("Line", Property.SYMBOL_PLACEMENT_LINE);
        map21.put("Point", Property.SYMBOL_PLACEMENT_POINT);
        HashMap map22 = new HashMap();
        map22.put("Center", "center");
        map22.put("Left", "left");
        map22.put("Right", "right");
        map22.put("Top", "top");
        map22.put("Bottom", "bottom");
        map22.put("TopLeft", "top-left");
        map22.put("TopRight", "top-right");
        map22.put("BottomLeft", "bottom-left");
        map22.put("BottomRight", "bottom-right");
        HashMap map23 = new HashMap();
        map23.put("Center", "center");
        map23.put("Left", "left");
        map23.put("Right", "right");
        HashMap map24 = new HashMap();
        map24.put("Auto", "auto");
        map24.put("Map", "map");
        map24.put("Viewport", "viewport");
        HashMap map25 = new HashMap();
        map25.put("Auto", "auto");
        map25.put("Map", "map");
        map25.put("Viewport", "viewport");
        HashMap map26 = new HashMap();
        map26.put(KeychainModule.AccessControl.NONE, "none");
        map26.put("Lowercase", Property.TEXT_TRANSFORM_LOWERCASE);
        map26.put("Uppercase", Property.TEXT_TRANSFORM_UPPERCASE);
        HashMap map27 = new HashMap();
        map27.put("Map", "map");
        map27.put("Viewport", "viewport");
        HashMap map28 = new HashMap();
        map28.put("Map", "map");
        map28.put("Viewport", "viewport");
        HashMap map29 = new HashMap();
        map29.put("Inactive", 0);
        map29.put("Active", 1);
        map29.put("Complete", 2);
        HashMap map30 = new HashMap();
        map30.put("Error", RCTMLNOfflineModule.OFFLINE_ERROR);
        map30.put("Progress", RCTMLNOfflineModule.OFFLINE_PROGRESS);
        HashMap map31 = new HashMap();
        map31.put("Update", RCTMLNLocationModule.LOCATION_UPDATE);
        return MapBuilder.builder().put("StyleURL", map).put("EventTypes", map2).put("UserTrackingModes", map3).put("UserLocationVerticalAlignment", map4).put("CameraModes", map5).put("StyleSource", map6).put("InterpolationMode", map7).put("LineJoin", map8).put("LineCap", map9).put("LineTranslateAnchor", map10).put("CirclePitchScale", map11).put("CircleTranslateAnchor", map12).put("CirclePitchAlignment", map13).put("FillExtrusionTranslateAnchor", map14).put("FillTranslateAnchor", map15).put("IconRotationAlignment", map16).put("IconTextFit", map17).put("IconTranslateAnchor", map20).put("SymbolPlacement", map21).put("IconAnchor", map18).put("TextAnchor", map22).put("TextJustify", map23).put("IconPitchAlignment", map19).put("TextPitchAlignment", map24).put("TextRotationAlignment", map25).put("TextTransform", map26).put("TextTranslateAnchor", map27).put("LightAnchor", map28).put("OfflinePackDownloadState", map29).put("OfflineCallbackName", map30).put("LocationCallbackName", map31).build();
    }

    @ReactMethod
    public void setAccessToken(final String str) {
        this.mReactContext.runOnUiQueueThread(new Runnable() { // from class: com.maplibre.rctmln.modules.RCTMLNModule.1
            @Override // java.lang.Runnable
            public void run() {
                if (str == null) {
                    MapLibre.getInstance(RCTMLNModule.this.getReactApplicationContext());
                } else {
                    MapLibre.getInstance(RCTMLNModule.this.getReactApplicationContext(), str, WellKnownTileServer.Mapbox);
                }
            }
        });
    }

    @ReactMethod
    public void removeCustomHeader(final String str) {
        this.mReactContext.runOnUiQueueThread(new Runnable() { // from class: com.maplibre.rctmln.modules.RCTMLNModule.2
            @Override // java.lang.Runnable
            public void run() {
                CustomHeadersInterceptor.INSTANCE.removeHeader(str);
            }
        });
    }

    @ReactMethod
    public void addCustomHeader(final String str, final String str2) {
        this.mReactContext.runOnUiQueueThread(new Runnable() { // from class: com.maplibre.rctmln.modules.RCTMLNModule.3
            @Override // java.lang.Runnable
            public void run() {
                if (!RCTMLNModule.customHeaderInterceptorAdded) {
                    Log.i("header", "Add interceptor");
                    HttpRequestUtil.setOkHttpClient(new OkHttpClient.Builder().addInterceptor(CustomHeadersInterceptor.INSTANCE).dispatcher(RCTMLNModule.this.getDispatcher()).build());
                    RCTMLNModule.customHeaderInterceptorAdded = true;
                }
                CustomHeadersInterceptor.INSTANCE.addHeader(str, str2);
            }
        });
    }

    @ReactMethod
    public void getAccessToken(Promise promise) {
        String apiKey = MapLibre.getApiKey();
        if (apiKey == null) {
            promise.reject("missing_access_token", "No access token has been set");
        } else {
            promise.resolve(apiKey);
        }
    }

    @ReactMethod
    public void setConnected(final boolean z) {
        this.mReactContext.runOnUiQueueThread(new Runnable() { // from class: com.maplibre.rctmln.modules.RCTMLNModule.4
            @Override // java.lang.Runnable
            public void run() {
                MapLibre.setConnected(Boolean.valueOf(z));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Dispatcher getDispatcher() {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequestsPerHost(20);
        return dispatcher;
    }
}

package com.maplibre.rctmln.components.mapview;

import android.util.Log;
import android.view.View;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.maplibre.rctmln.components.AbstractEventEmitter;
import com.maplibre.rctmln.events.constants.EventKeys;
import com.maplibre.rctmln.utils.ConvertUtils;
import com.maplibre.rctmln.utils.ExpressionParser;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes3.dex */
public class RCTMLNMapViewManager extends AbstractEventEmitter<RCTMLNMapView> {
    public static final String LOG_TAG = "RCTMLNMapViewManager";
    public static final int METHOD_GET_CENTER = 9;
    public static final int METHOD_GET_COORDINATE_FROM_VIEW = 6;
    public static final int METHOD_GET_POINT_IN_VIEW = 5;
    public static final int METHOD_GET_ZOOM = 8;
    public static final int METHOD_QUERY_FEATURES_POINT = 2;
    public static final int METHOD_QUERY_FEATURES_RECT = 3;
    public static final int METHOD_SET_HANDLED_MAP_EVENTS = 10;
    public static final int METHOD_SET_SOURCE_VISIBILITY = 12;
    public static final int METHOD_SHOW_ATTRIBUTION = 11;
    public static final int METHOD_TAKE_SNAP = 7;
    public static final int METHOD_VISIBLE_BOUNDS = 4;
    public static final String REACT_CLASS = "RCTMLNMapView";
    private Map<Integer, RCTMLNMapView> mViews;

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RCTMLNMapView";
    }

    public RCTMLNMapViewManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mViews = new HashMap();
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new MapShadowNode(this);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return MapShadowNode.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(RCTMLNMapView rCTMLNMapView) {
        super.onAfterUpdateTransaction((RCTMLNMapViewManager) rCTMLNMapView);
        if (rCTMLNMapView.getMapboxMap() == null) {
            this.mViews.put(Integer.valueOf(rCTMLNMapView.getId()), rCTMLNMapView);
            rCTMLNMapView.init();
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(RCTMLNMapView rCTMLNMapView, View view, int i) {
        rCTMLNMapView.addFeature(view, i);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public int getChildCount(RCTMLNMapView rCTMLNMapView) {
        return rCTMLNMapView.getFeatureCount();
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public View getChildAt(RCTMLNMapView rCTMLNMapView, int i) {
        return rCTMLNMapView.getFeatureAt(i);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(RCTMLNMapView rCTMLNMapView, int i) {
        rCTMLNMapView.removeFeature(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNMapView createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNMapView(themedReactContext, this, null);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(RCTMLNMapView rCTMLNMapView) {
        int id = rCTMLNMapView.getId();
        if (this.mViews.containsKey(Integer.valueOf(id))) {
            this.mViews.remove(Integer.valueOf(id));
        }
        super.onDropViewInstance((RCTMLNMapViewManager) rCTMLNMapView);
    }

    public RCTMLNMapView getByReactTag(int i) {
        return this.mViews.get(Integer.valueOf(i));
    }

    @ReactProp(name = "styleURL")
    public void setStyleURL(RCTMLNMapView rCTMLNMapView, String str) {
        rCTMLNMapView.setReactStyleURL(str);
    }

    @ReactProp(name = "preferredFramesPerSecond")
    public void setPreferredFramesPerSecond(RCTMLNMapView rCTMLNMapView, int i) {
        rCTMLNMapView.setReactPreferredFramesPerSecond(Integer.valueOf(i));
    }

    @ReactProp(name = "localizeLabels")
    public void setLocalizeLabels(RCTMLNMapView rCTMLNMapView, boolean z) {
        rCTMLNMapView.setLocalizeLabels(z);
    }

    @ReactProp(name = "zoomEnabled")
    public void setZoomEnabled(RCTMLNMapView rCTMLNMapView, boolean z) {
        rCTMLNMapView.setReactZoomEnabled(z);
    }

    @ReactProp(name = "scrollEnabled")
    public void setScrollEnabled(RCTMLNMapView rCTMLNMapView, boolean z) {
        rCTMLNMapView.setReactScrollEnabled(z);
    }

    @ReactProp(name = "pitchEnabled")
    public void setPitchEnabled(RCTMLNMapView rCTMLNMapView, boolean z) {
        rCTMLNMapView.setReactPitchEnabled(z);
    }

    @ReactProp(name = "rotateEnabled")
    public void setRotateEnabled(RCTMLNMapView rCTMLNMapView, boolean z) {
        rCTMLNMapView.setReactRotateEnabled(z);
    }

    @ReactProp(name = "attributionEnabled")
    public void setAttributionEnabled(RCTMLNMapView rCTMLNMapView, boolean z) {
        rCTMLNMapView.setReactAttributionEnabled(z);
    }

    @ReactProp(name = "attributionPosition")
    public void setAttributionPosition(RCTMLNMapView rCTMLNMapView, @Nullable ReadableMap readableMap) {
        rCTMLNMapView.setReactAttributionPosition(readableMap);
    }

    @ReactProp(name = "logoEnabled")
    public void setLogoEnabled(RCTMLNMapView rCTMLNMapView, boolean z) {
        rCTMLNMapView.setReactLogoEnabled(z);
    }

    @ReactProp(name = "logoPosition")
    public void setLogoPosition(RCTMLNMapView rCTMLNMapView, ReadableMap readableMap) {
        rCTMLNMapView.setReactLogoPosition(readableMap);
    }

    @ReactProp(name = "compassEnabled")
    public void setCompassEnabled(RCTMLNMapView rCTMLNMapView, boolean z) {
        rCTMLNMapView.setReactCompassEnabled(z);
    }

    @ReactProp(name = "compassViewMargins")
    public void setCompassViewMargins(RCTMLNMapView rCTMLNMapView, ReadableMap readableMap) {
        rCTMLNMapView.setReactCompassViewMargins(readableMap);
    }

    @ReactProp(name = "compassViewPosition")
    public void setCompassViewPosition(RCTMLNMapView rCTMLNMapView, int i) {
        rCTMLNMapView.setReactCompassViewPosition(i);
    }

    @ReactProp(name = "contentInset")
    public void setContentInset(RCTMLNMapView rCTMLNMapView, ReadableArray readableArray) {
        rCTMLNMapView.setReactContentInset(readableArray);
    }

    @ReactProp(customType = "Color", name = "tintColor")
    public void setTintColor(RCTMLNMapView rCTMLNMapView, @Nullable Integer num) {
        rCTMLNMapView.setTintColor(num);
    }

    @Override // com.maplibre.rctmln.components.AbstractEventEmitter
    public Map<String, String> customEvents() {
        return MapBuilder.builder().put(EventKeys.MAP_CLICK, "onPress").put(EventKeys.MAP_LONG_CLICK, "onLongPress").put(EventKeys.MAP_ONCHANGE, "onMapChange").put(EventKeys.MAP_ON_LOCATION_CHANGE, "onLocationChange").put(EventKeys.MAP_USER_TRACKING_MODE_CHANGE, "onUserTrackingModeChange").put(EventKeys.MAP_ANDROID_CALLBACK, "onAndroidCallback").build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.builder().put("queryRenderedFeaturesAtPoint", 2).put("queryRenderedFeaturesInRect", 3).put("getVisibleBounds", 4).put("getPointInView", 5).put("getCoordinateFromView", 6).put("takeSnap", 7).put("getZoom", 8).put("getCenter", 9).put("setHandledMapChangedEvents", 10).put("showAttribution", 11).put("setSourceVisibility", 12).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(RCTMLNMapView rCTMLNMapView, int i, @Nullable ReadableArray readableArray) {
        if (rCTMLNMapView.getMapboxMap() == null) {
            rCTMLNMapView.enqueuePreRenderMapMethod(Integer.valueOf(i), readableArray);
        }
        switch (i) {
            case 2:
                rCTMLNMapView.queryRenderedFeaturesAtPoint(readableArray.getString(0), ConvertUtils.toPointF(readableArray.getArray(1)), ExpressionParser.from(readableArray.getArray(2)), ConvertUtils.toStringList(readableArray.getArray(3)));
                break;
            case 3:
                rCTMLNMapView.queryRenderedFeaturesInRect(readableArray.getString(0), ConvertUtils.toRectF(readableArray.getArray(1)), ExpressionParser.from(readableArray.getArray(2)), ConvertUtils.toStringList(readableArray.getArray(3)));
                break;
            case 4:
                rCTMLNMapView.getVisibleBounds(readableArray.getString(0));
                break;
            case 5:
                rCTMLNMapView.getPointInView(readableArray.getString(0), GeoJSONUtils.toLatLng(readableArray.getArray(1)));
                break;
            case 6:
                rCTMLNMapView.getCoordinateFromView(readableArray.getString(0), ConvertUtils.toPointF(readableArray.getArray(1)));
                break;
            case 7:
                rCTMLNMapView.takeSnap(readableArray.getString(0), readableArray.getBoolean(1));
                break;
            case 8:
                rCTMLNMapView.getZoom(readableArray.getString(0));
                break;
            case 9:
                rCTMLNMapView.getCenter(readableArray.getString(0));
                break;
            case 10:
                if (readableArray != null) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    for (int i2 = 1; i2 < readableArray.size(); i2++) {
                        arrayList.add(readableArray.getString(i2));
                    }
                    rCTMLNMapView.setHandledMapChangedEvents(arrayList);
                    break;
                }
                break;
            case 11:
                rCTMLNMapView.showAttribution();
                break;
            case 12:
                rCTMLNMapView.setSourceVisibility(readableArray.getBoolean(1), readableArray.getString(2), readableArray.getString(3));
                break;
        }
    }

    private static final class MapShadowNode extends LayoutShadowNode {
        private RCTMLNMapViewManager mViewManager;

        public MapShadowNode(RCTMLNMapViewManager rCTMLNMapViewManager) {
            this.mViewManager = rCTMLNMapViewManager;
        }

        @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
        public void dispose() {
            super.dispose();
            diposeNativeMapView();
        }

        private void diposeNativeMapView() {
            final RCTMLNMapView byReactTag = this.mViewManager.getByReactTag(getReactTag());
            if (byReactTag != null) {
                UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapViewManager.MapShadowNode.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            byReactTag.dispose();
                        } catch (Exception e) {
                            Log.e(RCTMLNMapViewManager.LOG_TAG, " disposeNativeMapView() exception destroying map view", e);
                        }
                    }
                });
            }
        }
    }
}

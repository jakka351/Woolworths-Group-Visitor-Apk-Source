package com.maplibre.rctmln.components.styles.sources;

import android.util.Log;
import android.view.View;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.maplibre.rctmln.components.AbstractEventEmitter;
import com.maplibre.rctmln.events.constants.EventKeys;
import com.maplibre.rctmln.utils.ClusterPropertyEntry;
import com.maplibre.rctmln.utils.ExpressionParser;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import org.maplibre.android.style.expressions.Expression;

/* loaded from: classes3.dex */
public class RCTMLNShapeSourceManager extends AbstractEventEmitter<RCTMLNShapeSource> {
    public static final String LOG_TAG = "RCTMLNShapeSourceManager";
    public static final int METHOD_FEATURES = 103;
    public static final int METHOD_GET_CLUSTER_CHILDREN = 106;
    public static final int METHOD_GET_CLUSTER_CHILDREN_BY_ID = 109;
    public static final int METHOD_GET_CLUSTER_EXPANSION_ZOOM = 104;
    public static final int METHOD_GET_CLUSTER_EXPANSION_ZOOM_BY_ID = 107;
    public static final int METHOD_GET_CLUSTER_LEAVES = 105;
    public static final int METHOD_GET_CLUSTER_LEAVES_BY_ID = 108;
    public static final String REACT_CLASS = "RCTMLNShapeSource";
    private ReactApplicationContext mContext;

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public RCTMLNShapeSourceManager(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mContext = reactApplicationContext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public RCTMLNShapeSource createViewInstance(ThemedReactContext themedReactContext) {
        return new RCTMLNShapeSource(themedReactContext, this);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public View getChildAt(RCTMLNShapeSource rCTMLNShapeSource, int i) {
        return rCTMLNShapeSource.getLayerAt(i);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public int getChildCount(RCTMLNShapeSource rCTMLNShapeSource) {
        return rCTMLNShapeSource.getLayerCount();
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(RCTMLNShapeSource rCTMLNShapeSource, View view, int i) {
        rCTMLNShapeSource.addLayer(view, getChildCount(rCTMLNShapeSource));
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(RCTMLNShapeSource rCTMLNShapeSource, int i) {
        rCTMLNShapeSource.removeLayer(i);
    }

    @ReactProp(name = "id")
    public void setId(RCTMLNShapeSource rCTMLNShapeSource, String str) {
        rCTMLNShapeSource.setID(str);
    }

    @ReactProp(name = "url")
    public void setURL(RCTMLNShapeSource rCTMLNShapeSource, String str) {
        try {
            rCTMLNShapeSource.setURL(new URL(str));
        } catch (MalformedURLException e) {
            Log.w(LOG_TAG, e.getLocalizedMessage());
        }
    }

    @ReactProp(name = "shape")
    public void setGeometry(RCTMLNShapeSource rCTMLNShapeSource, String str) {
        rCTMLNShapeSource.setShape(str);
    }

    @ReactProp(name = "cluster")
    public void setCluster(RCTMLNShapeSource rCTMLNShapeSource, int i) {
        rCTMLNShapeSource.setCluster(i == 1);
    }

    @ReactProp(name = "clusterRadius")
    public void setClusterRadius(RCTMLNShapeSource rCTMLNShapeSource, int i) {
        rCTMLNShapeSource.setClusterRadius(i);
    }

    @ReactProp(name = "clusterMaxZoomLevel")
    public void setClusterMaxZoomLevel(RCTMLNShapeSource rCTMLNShapeSource, int i) {
        rCTMLNShapeSource.setClusterMaxZoom(i);
    }

    @ReactProp(name = "clusterProperties")
    public void setClusterProperties(RCTMLNShapeSource rCTMLNShapeSource, ReadableMap readableMap) {
        Expression expressionLiteral;
        ArrayList arrayList = new ArrayList();
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            ReadableArray array = readableMap.getArray(strNextKey);
            if (array.getType(0) == ReadableType.Array) {
                expressionLiteral = ExpressionParser.from(array.getArray(0));
            } else {
                expressionLiteral = Expression.literal(array.getString(0));
            }
            arrayList.add(new AbstractMap.SimpleEntry(strNextKey, new ClusterPropertyEntry(expressionLiteral, ExpressionParser.from(array.getArray(1)))));
        }
        rCTMLNShapeSource.setClusterProperties(arrayList);
    }

    @ReactProp(name = "maxZoomLevel")
    public void setMaxZoomLevel(RCTMLNShapeSource rCTMLNShapeSource, int i) {
        rCTMLNShapeSource.setMaxZoom(i);
    }

    @ReactProp(name = "buffer")
    public void setBuffer(RCTMLNShapeSource rCTMLNShapeSource, int i) {
        rCTMLNShapeSource.setBuffer(i);
    }

    @ReactProp(name = "tolerance")
    public void setTolerance(RCTMLNShapeSource rCTMLNShapeSource, double d) {
        rCTMLNShapeSource.setTolerance(d);
    }

    @ReactProp(name = "lineMetrics")
    public void setLineMetrics(RCTMLNShapeSource rCTMLNShapeSource, boolean z) {
        rCTMLNShapeSource.setLineMetrics(z);
    }

    @ReactProp(name = "hasPressListener")
    public void setHasPressListener(RCTMLNShapeSource rCTMLNShapeSource, boolean z) {
        rCTMLNShapeSource.setHasPressListener(z);
    }

    @ReactProp(name = "hitbox")
    public void setHitbox(RCTMLNShapeSource rCTMLNShapeSource, ReadableMap readableMap) {
        rCTMLNShapeSource.setHitbox(readableMap);
    }

    @Override // com.maplibre.rctmln.components.AbstractEventEmitter
    public Map<String, String> customEvents() {
        return MapBuilder.builder().put(EventKeys.SHAPE_SOURCE_LAYER_CLICK, "onMapboxShapeSourcePress").put(EventKeys.MAP_ANDROID_CALLBACK, "onAndroidCallback").build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.builder().put("features", 103).put("getClusterExpansionZoom", 104).put("getClusterLeaves", 105).put("getClusterChildren", Integer.valueOf(METHOD_GET_CLUSTER_CHILDREN)).put("getClusterExpansionZoomById", Integer.valueOf(METHOD_GET_CLUSTER_EXPANSION_ZOOM_BY_ID)).put("getClusterLeavesById", 108).put("getClusterChildrenById", 109).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(RCTMLNShapeSource rCTMLNShapeSource, int i, ReadableArray readableArray) {
        switch (i) {
            case 103:
                rCTMLNShapeSource.querySourceFeatures(readableArray.getString(0), ExpressionParser.from(readableArray.getArray(1)));
                break;
            case 104:
                rCTMLNShapeSource.getClusterExpansionZoom(readableArray.getString(0), readableArray.getString(1));
                break;
            case 105:
                rCTMLNShapeSource.getClusterLeaves(readableArray.getString(0), readableArray.getString(1), readableArray.getInt(2), readableArray.getInt(3));
                break;
            case METHOD_GET_CLUSTER_CHILDREN /* 106 */:
                rCTMLNShapeSource.getClusterChildren(readableArray.getString(0), readableArray.getString(1));
                break;
            case METHOD_GET_CLUSTER_EXPANSION_ZOOM_BY_ID /* 107 */:
                rCTMLNShapeSource.getClusterExpansionZoomById(readableArray.getString(0), readableArray.getInt(1));
                break;
            case 108:
                rCTMLNShapeSource.getClusterLeavesById(readableArray.getString(0), readableArray.getInt(1), readableArray.getInt(2), readableArray.getInt(3));
                break;
            case 109:
                rCTMLNShapeSource.getClusterChildrenById(readableArray.getString(0), readableArray.getInt(1));
                break;
        }
    }
}

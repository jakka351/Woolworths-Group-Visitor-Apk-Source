package com.maplibre.rctmln.components.styles.sources;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.facebook.react.bridge.WritableNativeMap;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.components.styles.sources.RCTSource;
import com.maplibre.rctmln.events.AndroidCallbackEvent;
import com.maplibre.rctmln.events.FeatureClickEvent;
import com.maplibre.rctmln.utils.ClusterPropertyEntry;
import com.maplibre.rctmln.utils.ImageEntry;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.sources.GeoJsonOptions;
import org.maplibre.android.style.sources.GeoJsonSource;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;

/* loaded from: classes3.dex */
public class RCTMLNShapeSource extends RCTSource<GeoJsonSource> {
    private static Bitmap mImagePlaceholder;
    private Integer mBuffer;
    private Boolean mCluster;
    private Integer mClusterMaxZoom;
    private List<Map.Entry<String, ClusterPropertyEntry>> mClusterProperties;
    private Integer mClusterRadius;
    private List<Map.Entry<String, ImageEntry>> mImages;
    private Boolean mLineMetrics;
    private RCTMLNShapeSourceManager mManager;
    private Integer mMaxZoom;
    private List<Map.Entry<String, BitmapDrawable>> mNativeImages;
    private String mShape;
    private Double mTolerance;
    private URL mURL;

    public RCTMLNShapeSource(Context context, RCTMLNShapeSourceManager rCTMLNShapeSourceManager) {
        super(context);
        this.mManager = rCTMLNShapeSourceManager;
    }

    @Override // com.maplibre.rctmln.components.styles.sources.RCTSource, com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(final RCTMLNMapView rCTMLNMapView) {
        rCTMLNMapView.getMapboxMap().getStyle(new Style.OnStyleLoaded() { // from class: com.maplibre.rctmln.components.styles.sources.RCTMLNShapeSource.1
            @Override // org.maplibre.android.maps.Style.OnStyleLoaded
            public void onStyleLoaded(Style style) {
                rCTMLNMapView.getMapboxMap();
                RCTMLNShapeSource.super.addToMap(rCTMLNMapView);
            }
        });
    }

    @Override // com.maplibre.rctmln.components.styles.sources.RCTSource
    public GeoJsonSource makeSource() {
        GeoJsonOptions options = getOptions();
        if (this.mShape != null) {
            return new GeoJsonSource(this.mID, this.mShape, options);
        }
        return new GeoJsonSource(this.mID, this.mURL, options);
    }

    public void setURL(URL url) {
        this.mURL = url;
        if (this.mSource == 0 || this.mMapView == null || this.mMapView.isDestroyed()) {
            return;
        }
        ((GeoJsonSource) this.mSource).setUrl(this.mURL);
    }

    public void setShape(String str) {
        this.mShape = str;
        if (this.mSource == 0 || this.mMapView == null || this.mMapView.isDestroyed()) {
            return;
        }
        ((GeoJsonSource) this.mSource).setGeoJson(this.mShape);
    }

    public void setCluster(boolean z) {
        this.mCluster = Boolean.valueOf(z);
    }

    public void setClusterRadius(int i) {
        this.mClusterRadius = Integer.valueOf(i);
    }

    public void setClusterMaxZoom(int i) {
        this.mClusterMaxZoom = Integer.valueOf(i);
    }

    public void setClusterProperties(List<Map.Entry<String, ClusterPropertyEntry>> list) {
        this.mClusterProperties = list;
    }

    public void setMaxZoom(int i) {
        this.mMaxZoom = Integer.valueOf(i);
    }

    public void setBuffer(int i) {
        this.mBuffer = Integer.valueOf(i);
    }

    public void setTolerance(double d) {
        this.mTolerance = Double.valueOf(d);
    }

    public void setLineMetrics(boolean z) {
        this.mLineMetrics = Boolean.valueOf(z);
    }

    @Override // com.maplibre.rctmln.components.styles.sources.RCTSource
    public void onPress(RCTSource.OnPressEvent onPressEvent) {
        this.mManager.handleEvent(FeatureClickEvent.makeShapeSourceEvent(this, onPressEvent));
    }

    private GeoJsonOptions getOptions() {
        GeoJsonOptions geoJsonOptions = new GeoJsonOptions();
        Boolean bool = this.mCluster;
        if (bool != null) {
            geoJsonOptions.withCluster(bool.booleanValue());
        }
        Integer num = this.mClusterRadius;
        if (num != null) {
            geoJsonOptions.withClusterRadius(num.intValue());
        }
        Integer num2 = this.mClusterMaxZoom;
        if (num2 != null) {
            geoJsonOptions.withClusterMaxZoom(num2.intValue());
        }
        List<Map.Entry<String, ClusterPropertyEntry>> list = this.mClusterProperties;
        if (list != null) {
            for (Map.Entry<String, ClusterPropertyEntry> entry : list) {
                ClusterPropertyEntry value = entry.getValue();
                geoJsonOptions.withClusterProperty(entry.getKey(), value.operator, value.mapping);
            }
        }
        Integer num3 = this.mMaxZoom;
        if (num3 != null) {
            geoJsonOptions.withMaxZoom(num3.intValue());
        }
        Integer num4 = this.mBuffer;
        if (num4 != null) {
            geoJsonOptions.withBuffer(num4.intValue());
        }
        Double d = this.mTolerance;
        if (d != null) {
            geoJsonOptions.withTolerance(d.floatValue());
        }
        Boolean bool2 = this.mLineMetrics;
        if (bool2 != null) {
            geoJsonOptions.withLineMetrics(bool2.booleanValue());
        }
        return geoJsonOptions;
    }

    public void querySourceFeatures(String str, Expression expression) {
        if (this.mSource == 0) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("error", "source is not yet loaded");
            this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
            return;
        }
        List<Feature> listQuerySourceFeatures = ((GeoJsonSource) this.mSource).querySourceFeatures(expression);
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putString("data", FeatureCollection.fromFeatures(listQuerySourceFeatures).toJson());
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap2));
    }

    public void getClusterExpansionZoom(String str, String str2) {
        if (this.mSource == 0) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("error", "source is not yet loaded");
            this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
            return;
        }
        int clusterExpansionZoom = ((GeoJsonSource) this.mSource).getClusterExpansionZoom(Feature.fromJson(str2));
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putInt("data", clusterExpansionZoom);
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap2));
    }

    public void getClusterLeaves(String str, String str2, int i, int i2) {
        if (this.mSource == 0) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("error", "source is not yet loaded");
            this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
            return;
        }
        FeatureCollection clusterLeaves = ((GeoJsonSource) this.mSource).getClusterLeaves(Feature.fromJson(str2), i, i2);
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putString("data", clusterLeaves.toJson());
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap2));
    }

    public void getClusterChildren(String str, String str2) {
        if (this.mSource == 0) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("error", "source is not yet loaded");
            this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
            return;
        }
        FeatureCollection clusterChildren = ((GeoJsonSource) this.mSource).getClusterChildren(Feature.fromJson(str2));
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putString("data", clusterChildren.toJson());
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap2));
    }

    public void getClusterExpansionZoomById(String str, int i) {
        if (this.mSource == 0) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("error", "source is not yet loaded");
            this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
            return;
        }
        List<Feature> listQuerySourceFeatures = ((GeoJsonSource) this.mSource).querySourceFeatures(Expression.eq(Expression.id(), Integer.valueOf(i)));
        int clusterExpansionZoom = listQuerySourceFeatures.size() > 0 ? ((GeoJsonSource) this.mSource).getClusterExpansionZoom(listQuerySourceFeatures.get(0)) : -1;
        if (clusterExpansionZoom == -1) {
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            writableNativeMap2.putString("error", "Could not get zoom for cluster id " + i);
            this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap2));
        } else {
            WritableNativeMap writableNativeMap3 = new WritableNativeMap();
            writableNativeMap3.putInt("data", clusterExpansionZoom);
            this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap3));
        }
    }

    public void getClusterLeavesById(String str, int i, int i2, int i3) {
        if (this.mSource == 0) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("error", "source is not yet loaded");
            this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
            return;
        }
        FeatureCollection clusterLeaves = ((GeoJsonSource) this.mSource).getClusterLeaves(((GeoJsonSource) this.mSource).querySourceFeatures(Expression.eq(Expression.get("cluster_id"), Integer.valueOf(i))).get(0), i2, i3);
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putString("data", clusterLeaves.toJson());
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap2));
    }

    public void getClusterChildrenById(String str, int i) {
        if (this.mSource == 0) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString("error", "source is not yet loaded");
            this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
            return;
        }
        FeatureCollection clusterChildren = ((GeoJsonSource) this.mSource).getClusterChildren(((GeoJsonSource) this.mSource).querySourceFeatures(Expression.eq(Expression.get("cluster_id"), Integer.valueOf(i))).get(0));
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putString("data", clusterChildren.toJson());
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap2));
    }
}

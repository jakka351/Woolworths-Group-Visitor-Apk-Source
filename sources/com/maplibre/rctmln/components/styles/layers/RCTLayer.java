package com.maplibre.rctmln.components.styles.layers;

import android.content.Context;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.maplibre.rctmln.components.AbstractMapFeature;
import com.maplibre.rctmln.components.mapview.RCTMLNMapView;
import com.maplibre.rctmln.utils.ExpressionParser;
import org.maplibre.android.location.LocationComponentConstants;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.Style;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.Layer;
import org.maplibre.android.style.layers.PropertyFactory;

/* loaded from: classes3.dex */
public abstract class RCTLayer<T extends Layer> extends AbstractMapFeature {
    public static final String LOG_TAG = "RCTLayer";
    protected String mAboveLayerID;
    protected String mBelowLayerID;
    protected Context mContext;
    protected Expression mFilter;
    protected boolean mHadFilter;
    protected String mID;
    protected T mLayer;
    protected Integer mLayerIndex;
    protected MapLibreMap mMap;
    protected RCTMLNMapView mMapView;
    protected Double mMaxZoomLevel;
    protected Double mMinZoomLevel;
    protected ReadableMap mReactStyle;
    protected String mSourceID;
    protected boolean mVisible;

    public abstract void addStyles();

    public abstract T makeLayer();

    protected void updateFilter(Expression expression) {
    }

    public RCTLayer(Context context) {
        super(context);
        this.mContext = context;
        this.mHadFilter = false;
    }

    public String getID() {
        return this.mID;
    }

    public void setID(String str) {
        this.mID = str;
    }

    public void setSourceID(String str) {
        this.mSourceID = str;
    }

    public void setAboveLayerID(String str) {
        String str2 = this.mAboveLayerID;
        if (str2 == null || !str2.equals(str)) {
            this.mAboveLayerID = str;
            if (this.mLayer != null) {
                removeFromMap(this.mMapView);
                addAbove(this.mAboveLayerID);
            }
        }
    }

    public void setBelowLayerID(String str) {
        String str2 = this.mBelowLayerID;
        if (str2 == null || !str2.equals(str)) {
            this.mBelowLayerID = str;
            if (this.mLayer != null) {
                removeFromMap(this.mMapView);
                addBelow(this.mBelowLayerID);
            }
        }
    }

    public void setLayerIndex(int i) {
        Integer num = this.mLayerIndex;
        if (num == null || num.intValue() != i) {
            this.mLayerIndex = Integer.valueOf(i);
            if (this.mLayer != null) {
                removeFromMap(this.mMapView);
                addAtIndex(this.mLayerIndex.intValue());
            }
        }
    }

    public void setVisible(boolean z) {
        this.mVisible = z;
        T t = this.mLayer;
        if (t != null) {
            t.setProperties(PropertyFactory.visibility(z ? "visible" : "none"));
        }
    }

    public void setMinZoomLevel(double d) {
        this.mMinZoomLevel = Double.valueOf(d);
        T t = this.mLayer;
        if (t != null) {
            t.setMinZoom((float) d);
        }
    }

    public void setMaxZoomLevel(double d) {
        this.mMaxZoomLevel = Double.valueOf(d);
        T t = this.mLayer;
        if (t != null) {
            t.setMaxZoom((float) d);
        }
    }

    public void setReactStyle(ReadableMap readableMap) {
        this.mReactStyle = readableMap;
        if (this.mLayer != null) {
            addStyles();
        }
    }

    public void setFilter(ReadableArray readableArray) {
        Expression expressionFrom = ExpressionParser.from(readableArray);
        this.mFilter = expressionFrom;
        if (this.mLayer != null) {
            if (expressionFrom != null) {
                this.mHadFilter = true;
                updateFilter(expressionFrom);
            } else if (this.mHadFilter) {
                updateFilter(Expression.literal(true));
            }
        }
    }

    public void add() {
        if (hasInitialized() && getStyle() != null) {
            if (getStyle().getLayer(LocationComponentConstants.BACKGROUND_LAYER) != null) {
                getStyle().addLayerBelow(this.mLayer, LocationComponentConstants.BACKGROUND_LAYER);
                this.mMapView.layerAdded(this.mLayer);
            } else {
                getStyle().addLayer(this.mLayer);
                this.mMapView.layerAdded(this.mLayer);
            }
        }
    }

    public void addAbove(final String str) {
        this.mMapView.waitForLayer(str, new RCTMLNMapView.FoundLayerCallback() { // from class: com.maplibre.rctmln.components.styles.layers.RCTLayer.1
            @Override // com.maplibre.rctmln.components.mapview.RCTMLNMapView.FoundLayerCallback
            public void found(Layer layer) {
                if (RCTLayer.this.hasInitialized() && RCTLayer.this.getStyle() != null) {
                    RCTLayer.this.getStyle().addLayerAbove(RCTLayer.this.mLayer, str);
                    RCTLayer.this.mMapView.layerAdded(RCTLayer.this.mLayer);
                }
            }
        });
    }

    public void addBelow(final String str) {
        this.mMapView.waitForLayer(str, new RCTMLNMapView.FoundLayerCallback() { // from class: com.maplibre.rctmln.components.styles.layers.RCTLayer.2
            @Override // com.maplibre.rctmln.components.mapview.RCTMLNMapView.FoundLayerCallback
            public void found(Layer layer) {
                if (RCTLayer.this.hasInitialized() && RCTLayer.this.getStyle() != null) {
                    RCTLayer.this.getStyle().addLayerBelow(RCTLayer.this.mLayer, str);
                    RCTLayer.this.mMapView.layerAdded(RCTLayer.this.mLayer);
                }
            }
        });
    }

    public void addAtIndex(int i) {
        if (hasInitialized() && getStyle() != null) {
            int size = getStyle().getLayers().size();
            if (i >= size) {
                FLog.e(LOG_TAG, "Layer index is greater than number of layers on map. Layer inserted at end of layer stack.");
                i = size - 1;
            }
            getStyle().addLayerAt(this.mLayer, i);
            this.mMapView.layerAdded(this.mLayer);
        }
    }

    protected void insertLayer() {
        if (getStyle() != null && getStyle().getLayer(this.mID) == null) {
            String str = this.mAboveLayerID;
            if (str != null) {
                addAbove(str);
            } else {
                String str2 = this.mBelowLayerID;
                if (str2 != null) {
                    addBelow(str2);
                } else {
                    Integer num = this.mLayerIndex;
                    if (num != null) {
                        addAtIndex(num.intValue());
                    } else {
                        add();
                    }
                }
            }
            setZoomBounds();
        }
    }

    protected void setZoomBounds() {
        Double d = this.mMaxZoomLevel;
        if (d != null) {
            this.mLayer.setMaxZoom(d.floatValue());
        }
        Double d2 = this.mMinZoomLevel;
        if (d2 != null) {
            this.mLayer.setMinZoom(d2.floatValue());
        }
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void addToMap(RCTMLNMapView rCTMLNMapView) {
        this.mMap = rCTMLNMapView.getMapboxMap();
        this.mMapView = rCTMLNMapView;
        if (getStyle() == null) {
            return;
        }
        T t = (T) getStyle().getLayerAs(this.mID);
        if (t != null) {
            this.mLayer = t;
        } else {
            this.mLayer = (T) makeLayer();
            insertLayer();
        }
        addStyles();
        Expression expression = this.mFilter;
        if (expression != null) {
            this.mHadFilter = true;
            updateFilter(expression);
        }
    }

    @Override // com.maplibre.rctmln.components.AbstractMapFeature
    public void removeFromMap(RCTMLNMapView rCTMLNMapView) {
        if (getStyle() != null) {
            getStyle().removeLayer(this.mLayer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Style getStyle() {
        MapLibreMap mapLibreMap = this.mMap;
        if (mapLibreMap == null) {
            return null;
        }
        return mapLibreMap.getStyle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasInitialized() {
        return (this.mMap == null || this.mLayer == null) ? false : true;
    }
}

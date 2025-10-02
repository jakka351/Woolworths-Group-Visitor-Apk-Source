package com.maplibre.rctmln.components.mapview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.RectF;
import android.location.Location;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.GravityCompat;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.maplibre.rctmln.R;
import com.maplibre.rctmln.components.AbstractMapFeature;
import com.maplibre.rctmln.components.annotation.MarkerViewManager;
import com.maplibre.rctmln.components.annotation.RCTMLNMarkerView;
import com.maplibre.rctmln.components.annotation.RCTMLNPointAnnotation;
import com.maplibre.rctmln.components.camera.RCTMLNCamera;
import com.maplibre.rctmln.components.images.RCTMLNImages;
import com.maplibre.rctmln.components.location.LocationComponentManager;
import com.maplibre.rctmln.components.location.RCTMLNNativeUserLocation;
import com.maplibre.rctmln.components.mapview.helpers.CameraChangeTracker;
import com.maplibre.rctmln.components.styles.layers.RCTLayer;
import com.maplibre.rctmln.components.styles.light.RCTMLNLight;
import com.maplibre.rctmln.components.styles.sources.RCTMLNShapeSource;
import com.maplibre.rctmln.components.styles.sources.RCTSource;
import com.maplibre.rctmln.events.AndroidCallbackEvent;
import com.maplibre.rctmln.events.MapChangeEvent;
import com.maplibre.rctmln.events.MapClickEvent;
import com.maplibre.rctmln.events.constants.EventTypes;
import com.maplibre.rctmln.modules.RCTMLNOfflineModule;
import com.maplibre.rctmln.utils.BitmapUtils;
import com.maplibre.rctmln.utils.GeoJSONUtils;
import com.maplibre.rctmln.utils.GeoViewport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.camera.CameraUpdate;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.geometry.VisibleRegion;
import org.maplibre.android.gestures.MoveGestureDetector;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.AttributionDialogManager;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapLibreMapOptions;
import org.maplibre.android.maps.MapView;
import org.maplibre.android.maps.OnMapReadyCallback;
import org.maplibre.android.maps.Style;
import org.maplibre.android.maps.UiSettings;
import org.maplibre.android.plugins.annotation.OnSymbolClickListener;
import org.maplibre.android.plugins.annotation.OnSymbolDragListener;
import org.maplibre.android.plugins.annotation.Symbol;
import org.maplibre.android.plugins.annotation.SymbolManager;
import org.maplibre.android.plugins.localization.LocalizationPlugin;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.Layer;
import org.maplibre.android.style.layers.PropertyFactory;
import org.maplibre.android.style.layers.PropertyValue;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.FeatureCollection;

/* loaded from: classes3.dex */
public class RCTMLNMapView extends MapView implements OnMapReadyCallback, MapLibreMap.OnMapClickListener, MapLibreMap.OnMapLongClickListener, MapView.OnCameraIsChangingListener, MapView.OnCameraDidChangeListener, MapView.OnDidFailLoadingMapListener, MapView.OnDidFinishLoadingMapListener, MapView.OnWillStartRenderingFrameListener, MapView.OnWillStartRenderingMapListener, MapView.OnDidFinishRenderingFrameListener, MapView.OnDidFinishRenderingMapListener, MapView.OnDidFinishLoadingStyleListener, MapView.OnStyleImageMissingListener {
    public static final String LOG_TAG = "RCTMLNMapView";
    private Map<String, List<FoundLayerCallback>> layerWaiters;
    private long mActiveMarkerID;
    private boolean mAnnotationClicked;
    private Boolean mAttributionEnabled;
    private Integer mAttributionGravity;
    private int[] mAttributionMargin;
    private RCTMLNCamera mCamera;
    private CameraChangeTracker mCameraChangeTracker;
    private Boolean mCompassEnabled;
    private ReadableMap mCompassViewMargins;
    private int mCompassViewPosition;
    private Context mContext;
    private boolean mDestroyed;
    private List<AbstractMapFeature> mFeatures;
    private HashSet<String> mHandledMapChangedEvents;
    private Handler mHandler;
    private List<RCTMLNImages> mImages;
    private ReadableArray mInsets;
    private LifecycleEventListener mLifeCycleListener;
    private LocalizationPlugin mLocalizationPlugin;
    private boolean mLocalizeLabels;
    private LocationComponentManager mLocationComponentManager;
    private Boolean mLogoEnabled;
    private Integer mLogoGravity;
    private int[] mLogoMargins;
    private RCTMLNMapViewManager mManager;
    private MapLibreMap mMap;
    private ViewGroup mOffscreenAnnotationViewContainer;
    private boolean mPaused;
    private Boolean mPitchEnabled;
    private Map<String, RCTMLNPointAnnotation> mPointAnnotations;
    private List<Pair<Integer, ReadableArray>> mPreRenderMethods;
    private Integer mPreferredFramesPerSecond;
    private List<AbstractMapFeature> mQueuedFeatures;
    private Boolean mRotateEnabled;
    private Boolean mScrollEnabled;
    private Map<String, RCTSource> mSources;
    private String mStyleURL;

    @Nullable
    private Integer mTintColor;
    private Boolean mZoomEnabled;
    private MarkerViewManager markerViewManager;
    private SymbolManager symbolManager;

    public interface FoundLayerCallback {
        void found(Layer layer);
    }

    public RCTMLNMapView(Context context, RCTMLNMapViewManager rCTMLNMapViewManager, MapLibreMapOptions mapLibreMapOptions) {
        super(context, mapLibreMapOptions);
        this.mCameraChangeTracker = new CameraChangeTracker();
        this.mPreRenderMethods = new ArrayList();
        this.mCompassViewPosition = -1;
        this.mActiveMarkerID = -1L;
        this.mHandledMapChangedEvents = null;
        this.markerViewManager = null;
        this.mOffscreenAnnotationViewContainer = null;
        this.mAnnotationClicked = false;
        this.mLocationComponentManager = null;
        this.mTintColor = null;
        this.layerWaiters = new HashMap();
        this.mContext = context;
        onCreate(null);
        onStart();
        onResume();
        getMapAsync(this);
        this.mManager = rCTMLNMapViewManager;
        this.mSources = new HashMap();
        this.mImages = new ArrayList();
        this.mPointAnnotations = new HashMap();
        this.mQueuedFeatures = new ArrayList();
        this.mFeatures = new ArrayList();
        this.mHandler = new Handler();
        this.mStyleURL = RCTMLNOfflineModule.DEFAULT_STYLE_URL;
        setLifecycleListeners();
        addOnCameraIsChangingListener(this);
        addOnCameraDidChangeListener(this);
        addOnDidFailLoadingMapListener(this);
        addOnDidFinishLoadingMapListener(this);
        addOnStyleImageMissingListener(this);
        addOnWillStartRenderingFrameListener(this);
        addOnDidFinishRenderingFrameListener(this);
        addOnWillStartRenderingMapListener(this);
        addOnDidFinishRenderingMapListener(this);
        addOnDidFinishLoadingStyleListener(this);
    }

    @Override // org.maplibre.android.maps.MapView
    public void onResume() {
        super.onResume();
        this.mPaused = false;
    }

    @Override // org.maplibre.android.maps.MapView
    public void onPause() {
        super.onPause();
        this.mPaused = true;
    }

    @Override // org.maplibre.android.maps.MapView
    public void onDestroy() {
        super.onDestroy();
        this.mDestroyed = true;
    }

    public void enqueuePreRenderMapMethod(Integer num, @Nullable ReadableArray readableArray) {
        this.mPreRenderMethods.add(new Pair<>(num, readableArray));
    }

    public void addFeature(View view, int i) {
        AbstractMapFeature abstractMapFeature;
        if (view instanceof RCTSource) {
            RCTSource rCTSource = (RCTSource) view;
            this.mSources.put(rCTSource.getID(), rCTSource);
            abstractMapFeature = (AbstractMapFeature) view;
        } else if (view instanceof RCTMLNImages) {
            this.mImages.add((RCTMLNImages) view);
            abstractMapFeature = (AbstractMapFeature) view;
        } else if ((view instanceof RCTMLNLight) || (view instanceof RCTMLNNativeUserLocation)) {
            abstractMapFeature = (AbstractMapFeature) view;
        } else if (view instanceof RCTMLNPointAnnotation) {
            RCTMLNPointAnnotation rCTMLNPointAnnotation = (RCTMLNPointAnnotation) view;
            this.mPointAnnotations.put(rCTMLNPointAnnotation.getID(), rCTMLNPointAnnotation);
            abstractMapFeature = (AbstractMapFeature) view;
        } else if (view instanceof RCTMLNMarkerView) {
            abstractMapFeature = (AbstractMapFeature) view;
        } else if (view instanceof RCTMLNCamera) {
            this.mCamera = (RCTMLNCamera) view;
            abstractMapFeature = (AbstractMapFeature) view;
        } else if (view instanceof RCTLayer) {
            abstractMapFeature = (RCTLayer) view;
        } else {
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                    addFeature(viewGroup.getChildAt(i2), i);
                }
            }
            abstractMapFeature = null;
        }
        if (abstractMapFeature != null) {
            List<AbstractMapFeature> list = this.mQueuedFeatures;
            if (list == null) {
                abstractMapFeature.addToMap(this);
                this.mFeatures.add(i, abstractMapFeature);
            } else {
                list.add(i, abstractMapFeature);
            }
        }
    }

    public void removeFeature(int i) {
        AbstractMapFeature abstractMapFeature = features().get(i);
        if (abstractMapFeature == null) {
            return;
        }
        if (abstractMapFeature instanceof RCTSource) {
            this.mSources.remove(((RCTSource) abstractMapFeature).getID());
        } else if (abstractMapFeature instanceof RCTMLNPointAnnotation) {
            RCTMLNPointAnnotation rCTMLNPointAnnotation = (RCTMLNPointAnnotation) abstractMapFeature;
            if (rCTMLNPointAnnotation.getMapboxID() == this.mActiveMarkerID) {
                this.mActiveMarkerID = -1L;
            }
            this.mPointAnnotations.remove(rCTMLNPointAnnotation.getID());
        } else if (abstractMapFeature instanceof RCTMLNImages) {
            this.mImages.remove((RCTMLNImages) abstractMapFeature);
        }
        abstractMapFeature.removeFromMap(this);
        features().remove(abstractMapFeature);
    }

    private List<AbstractMapFeature> features() {
        List<AbstractMapFeature> list = this.mQueuedFeatures;
        if (list != null && list.size() > 0) {
            return this.mQueuedFeatures;
        }
        return this.mFeatures;
    }

    public int getFeatureCount() {
        return features().size();
    }

    public AbstractMapFeature getFeatureAt(int i) {
        return features().get(i);
    }

    public synchronized void dispose() {
        if (this.mDestroyed) {
            return;
        }
        if (!this.layerWaiters.isEmpty()) {
            this.layerWaiters.clear();
        }
        ((ReactContext) this.mContext).removeLifecycleEventListener(this.mLifeCycleListener);
        if (!this.mPaused) {
            onPause();
        }
        onStop();
        onDestroy();
    }

    public VisibleRegion getVisibleRegion(LatLng latLng, double d) {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        int[] padding = this.mMap.getPadding();
        return GeoViewport.getRegion(latLng, (int) d, (int) (((this.mMap.getWidth() * 0.75d) - (padding[0] + padding[2])) / displayMetrics.scaledDensity), (int) (((this.mMap.getHeight() * 0.75d) - (padding[1] + padding[3])) / displayMetrics.scaledDensity));
    }

    public CameraPosition getCameraPosition() {
        return this.mMap.getCameraPosition();
    }

    public void animateCamera(CameraUpdate cameraUpdate, MapLibreMap.CancelableCallback cancelableCallback) {
        this.mMap.animateCamera(cameraUpdate, cancelableCallback);
    }

    public void moveCamera(CameraUpdate cameraUpdate, MapLibreMap.CancelableCallback cancelableCallback) {
        this.mMap.moveCamera(cameraUpdate, cancelableCallback);
    }

    public void moveCamera(CameraUpdate cameraUpdate) {
        this.mMap.moveCamera(cameraUpdate);
    }

    public void easeCamera(CameraUpdate cameraUpdate, int i, boolean z, MapLibreMap.CancelableCallback cancelableCallback) {
        this.mMap.easeCamera(cameraUpdate, i, z, cancelableCallback);
    }

    public void easeCamera(CameraUpdate cameraUpdate) {
        this.mMap.easeCamera(cameraUpdate);
    }

    public RCTMLNPointAnnotation getPointAnnotationByID(String str) {
        if (str == null) {
            return null;
        }
        Iterator<String> it = this.mPointAnnotations.keySet().iterator();
        while (it.hasNext()) {
            RCTMLNPointAnnotation rCTMLNPointAnnotation = this.mPointAnnotations.get(it.next());
            if (rCTMLNPointAnnotation != null && str.equals(rCTMLNPointAnnotation.getID())) {
                return rCTMLNPointAnnotation;
            }
        }
        return null;
    }

    public RCTMLNPointAnnotation getPointAnnotationByMarkerID(long j) {
        Iterator<String> it = this.mPointAnnotations.keySet().iterator();
        while (it.hasNext()) {
            RCTMLNPointAnnotation rCTMLNPointAnnotation = this.mPointAnnotations.get(it.next());
            if (rCTMLNPointAnnotation != null && j == rCTMLNPointAnnotation.getMapboxID()) {
                return rCTMLNPointAnnotation;
            }
        }
        return null;
    }

    public MapLibreMap getMapboxMap() {
        return this.mMap;
    }

    public SymbolManager getSymbolManager() {
        return this.symbolManager;
    }

    public void layerAdded(Layer layer) {
        String id = layer.getId();
        List<FoundLayerCallback> list = this.layerWaiters.get(id);
        if (list != null) {
            Iterator<FoundLayerCallback> it = list.iterator();
            while (it.hasNext()) {
                it.next().found(layer);
            }
        }
        this.layerWaiters.remove(id);
    }

    public void waitForLayer(String str, FoundLayerCallback foundLayerCallback) {
        Layer layer = this.mMap.getStyle().getLayer(str);
        if (layer != null) {
            foundLayerCallback.found(layer);
            return;
        }
        List<FoundLayerCallback> arrayList = this.layerWaiters.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.layerWaiters.put(str, arrayList);
        }
        arrayList.add(foundLayerCallback);
    }

    public boolean isJSONValid(String str) {
        try {
            new JSONObject(str);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    @Override // org.maplibre.android.maps.OnMapReadyCallback
    public void onMapReady(MapLibreMap mapLibreMap) {
        this.mMap = mapLibreMap;
        if (isJSONValid(this.mStyleURL)) {
            this.mMap.setStyle(new Style.Builder().fromJson(this.mStyleURL));
        } else {
            this.mMap.setStyle(new Style.Builder().fromUri(this.mStyleURL));
        }
        reflow();
        this.mMap.getStyle(new Style.OnStyleLoaded() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.1
            @Override // org.maplibre.android.maps.Style.OnStyleLoaded
            public void onStyleLoaded(Style style) {
                RCTMLNMapView.this.createSymbolManager(style);
                RCTMLNMapView.this.setUpImage(style);
                RCTMLNMapView.this.addQueuedFeatures();
                RCTMLNMapView.this.setupLocalization(style);
            }
        });
        updatePreferredFramesPerSecond();
        updateInsets();
        updateUISettings();
        this.mMap.addOnCameraIdleListener(new MapLibreMap.OnCameraIdleListener() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.2
            @Override // org.maplibre.android.maps.MapLibreMap.OnCameraIdleListener
            public void onCameraIdle() {
                RCTMLNMapView.this.sendRegionDidChangeEvent();
            }
        });
        this.mMap.addOnCameraMoveStartedListener(new MapLibreMap.OnCameraMoveStartedListener() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.3
            @Override // org.maplibre.android.maps.MapLibreMap.OnCameraMoveStartedListener
            public void onCameraMoveStarted(int i) {
                RCTMLNMapView.this.mCameraChangeTracker.setReason(i);
                RCTMLNMapView.this.handleMapChangedEvent(EventTypes.REGION_WILL_CHANGE);
            }
        });
        this.mMap.addOnCameraMoveListener(new MapLibreMap.OnCameraMoveListener() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.4
            @Override // org.maplibre.android.maps.MapLibreMap.OnCameraMoveListener
            public void onCameraMove() {
                if (RCTMLNMapView.this.markerViewManager != null) {
                    RCTMLNMapView.this.markerViewManager.updateMarkers();
                }
            }
        });
        this.mMap.addOnMoveListener(new MapLibreMap.OnMoveListener() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.5
            @Override // org.maplibre.android.maps.MapLibreMap.OnMoveListener
            public void onMoveEnd(MoveGestureDetector moveGestureDetector) {
            }

            @Override // org.maplibre.android.maps.MapLibreMap.OnMoveListener
            public void onMoveBegin(MoveGestureDetector moveGestureDetector) {
                RCTMLNMapView.this.mCameraChangeTracker.setReason(1);
                RCTMLNMapView.this.handleMapChangedEvent(EventTypes.REGION_WILL_CHANGE);
            }

            @Override // org.maplibre.android.maps.MapLibreMap.OnMoveListener
            public void onMove(MoveGestureDetector moveGestureDetector) {
                RCTMLNMapView.this.mCameraChangeTracker.setReason(1);
                RCTMLNMapView.this.handleMapChangedEvent(EventTypes.REGION_IS_CHANGING);
            }
        });
    }

    public void reflow() {
        this.mHandler.post(new Runnable() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.6
            @Override // java.lang.Runnable
            public void run() {
                RCTMLNMapView rCTMLNMapView = RCTMLNMapView.this;
                rCTMLNMapView.measure(View.MeasureSpec.makeMeasureSpec(rCTMLNMapView.getMeasuredWidth(), BasicMeasure.EXACTLY), View.MeasureSpec.makeMeasureSpec(RCTMLNMapView.this.getMeasuredHeight(), BasicMeasure.EXACTLY));
                RCTMLNMapView rCTMLNMapView2 = RCTMLNMapView.this;
                rCTMLNMapView2.layout(rCTMLNMapView2.getLeft(), RCTMLNMapView.this.getTop(), RCTMLNMapView.this.getRight(), RCTMLNMapView.this.getBottom());
            }
        });
    }

    public void createSymbolManager(Style style) {
        SymbolManager symbolManager = new SymbolManager(this, this.mMap, style);
        this.symbolManager = symbolManager;
        symbolManager.setIconAllowOverlap(true);
        this.symbolManager.addClickListener(new OnSymbolClickListener() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.7
            @Override // org.maplibre.android.plugins.annotation.OnAnnotationClickListener
            public boolean onAnnotationClick(Symbol symbol) {
                RCTMLNMapView.this.onMarkerClick(symbol);
                return true;
            }
        });
        this.symbolManager.addDragListener(new OnSymbolDragListener() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.8
            @Override // org.maplibre.android.plugins.annotation.OnAnnotationDragListener
            public void onAnnotationDragStarted(Symbol symbol) {
                RCTMLNMapView.this.mAnnotationClicked = true;
                RCTMLNPointAnnotation pointAnnotationByMarkerID = RCTMLNMapView.this.getPointAnnotationByMarkerID(symbol.getId());
                if (pointAnnotationByMarkerID != null) {
                    pointAnnotationByMarkerID.onDragStart();
                }
            }

            @Override // org.maplibre.android.plugins.annotation.OnAnnotationDragListener
            public void onAnnotationDrag(Symbol symbol) {
                RCTMLNPointAnnotation pointAnnotationByMarkerID = RCTMLNMapView.this.getPointAnnotationByMarkerID(symbol.getId());
                if (pointAnnotationByMarkerID != null) {
                    pointAnnotationByMarkerID.onDrag();
                }
            }

            @Override // org.maplibre.android.plugins.annotation.OnAnnotationDragListener
            public void onAnnotationDragFinished(Symbol symbol) {
                RCTMLNMapView.this.mAnnotationClicked = false;
                RCTMLNPointAnnotation pointAnnotationByMarkerID = RCTMLNMapView.this.getPointAnnotationByMarkerID(symbol.getId());
                if (pointAnnotationByMarkerID != null) {
                    pointAnnotationByMarkerID.onDragEnd();
                }
            }
        });
        this.mMap.addOnMapClickListener(this);
        this.mMap.addOnMapLongClickListener(this);
    }

    public void addQueuedFeatures() {
        List<AbstractMapFeature> list = this.mQueuedFeatures;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i = 0; i < this.mQueuedFeatures.size(); i++) {
            AbstractMapFeature abstractMapFeature = this.mQueuedFeatures.get(i);
            abstractMapFeature.addToMap(this);
            this.mFeatures.add(abstractMapFeature);
        }
        this.mQueuedFeatures = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupLocalization(Style style) {
        LocalizationPlugin localizationPlugin = new LocalizationPlugin(this, this.mMap, style);
        this.mLocalizationPlugin = localizationPlugin;
        if (this.mLocalizeLabels) {
            try {
                localizationPlugin.matchMapLanguageWithDeviceDefault();
            } catch (Exception unused) {
                Logger.w("RCTMLNMapView", String.format("Could not find matching locale for %s", Locale.getDefault().toString()));
            }
        }
    }

    @Override // org.maplibre.android.maps.MapView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
        if (zOnTouchEvent && this.mScrollEnabled.booleanValue()) {
            requestDisallowInterceptTouchEvent(true);
        }
        return zOnTouchEvent;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mPaused) {
            return;
        }
        MarkerViewManager markerViewManager = this.markerViewManager;
        if (markerViewManager != null) {
            markerViewManager.removeViews();
        }
        super.onLayout(z, i, i2, i3, i4);
        MarkerViewManager markerViewManager2 = this.markerViewManager;
        if (markerViewManager2 != null) {
            markerViewManager2.restoreViews();
        }
    }

    @Override // org.maplibre.android.maps.MapLibreMap.OnMapClickListener
    public boolean onMapClick(LatLng latLng) {
        RCTSource touchableSourceWithHighestZIndex;
        if (this.mAnnotationClicked) {
            this.mAnnotationClicked = false;
            return true;
        }
        PointF screenLocation = this.mMap.getProjection().toScreenLocation(latLng);
        List<RCTSource> allTouchableSources = getAllTouchableSources();
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (RCTSource rCTSource : allTouchableSources) {
            Map<String, Double> touchHitbox = rCTSource.getTouchHitbox();
            if (touchHitbox != null) {
                float fFloatValue = touchHitbox.get("width").floatValue() / 2.0f;
                float fFloatValue2 = touchHitbox.get("height").floatValue() / 2.0f;
                RectF rectF = new RectF();
                rectF.set(screenLocation.x - fFloatValue, screenLocation.y - fFloatValue2, screenLocation.x + fFloatValue, screenLocation.y + fFloatValue2);
                List<Feature> listQueryRenderedFeatures = this.mMap.queryRenderedFeatures(rectF, rCTSource.getLayerIDs());
                if (listQueryRenderedFeatures.size() > 0) {
                    map.put(rCTSource.getID(), listQueryRenderedFeatures);
                    arrayList.add(rCTSource);
                }
            }
        }
        if (map.size() > 0 && (touchableSourceWithHighestZIndex = getTouchableSourceWithHighestZIndex(arrayList)) != null && touchableSourceWithHighestZIndex.hasPressListener()) {
            touchableSourceWithHighestZIndex.onPress(new RCTSource.OnPressEvent((List) map.get(touchableSourceWithHighestZIndex.getID()), latLng, screenLocation));
            return true;
        }
        this.mManager.handleEvent(new MapClickEvent(this, latLng, screenLocation));
        return false;
    }

    @Override // org.maplibre.android.maps.MapLibreMap.OnMapLongClickListener
    public boolean onMapLongClick(LatLng latLng) {
        if (this.mAnnotationClicked) {
            this.mAnnotationClicked = false;
            return true;
        }
        this.mManager.handleEvent(new MapClickEvent(this, latLng, this.mMap.getProjection().toScreenLocation(latLng), EventTypes.MAP_LONG_CLICK));
        return false;
    }

    public void onMarkerClick(Symbol symbol) {
        this.mAnnotationClicked = true;
        long id = symbol.getId();
        Iterator<String> it = this.mPointAnnotations.keySet().iterator();
        RCTMLNPointAnnotation rCTMLNPointAnnotation = null;
        RCTMLNPointAnnotation rCTMLNPointAnnotation2 = null;
        while (it.hasNext()) {
            RCTMLNPointAnnotation rCTMLNPointAnnotation3 = this.mPointAnnotations.get(it.next());
            long mapboxID = rCTMLNPointAnnotation3.getMapboxID();
            long j = this.mActiveMarkerID;
            if (j == mapboxID) {
                rCTMLNPointAnnotation = rCTMLNPointAnnotation3;
            }
            if (id == mapboxID && j != mapboxID) {
                rCTMLNPointAnnotation2 = rCTMLNPointAnnotation3;
            }
        }
        if (rCTMLNPointAnnotation != null) {
            deselectAnnotation(rCTMLNPointAnnotation);
        }
        if (rCTMLNPointAnnotation2 != null) {
            selectAnnotation(rCTMLNPointAnnotation2);
        }
    }

    public void selectAnnotation(RCTMLNPointAnnotation rCTMLNPointAnnotation) {
        this.mActiveMarkerID = rCTMLNPointAnnotation.getMapboxID();
        rCTMLNPointAnnotation.onSelect(true);
    }

    public void deselectAnnotation(RCTMLNPointAnnotation rCTMLNPointAnnotation) {
        this.mActiveMarkerID = -1L;
        rCTMLNPointAnnotation.onDeselect();
    }

    @Override // org.maplibre.android.maps.MapView.OnCameraDidChangeListener
    public void onCameraDidChange(boolean z) {
        this.mCameraChangeTracker.setIsAnimating(z);
    }

    @Override // org.maplibre.android.maps.MapView.OnCameraIsChangingListener
    public void onCameraIsChanging() {
        handleMapChangedEvent(EventTypes.REGION_IS_CHANGING);
    }

    @Override // org.maplibre.android.maps.MapView.OnDidFailLoadingMapListener
    public void onDidFailLoadingMap(String str) {
        handleMapChangedEvent(EventTypes.DID_FAIL_LOADING_MAP);
    }

    @Override // org.maplibre.android.maps.MapView.OnDidFinishLoadingMapListener
    public void onDidFinishLoadingMap() {
        handleMapChangedEvent(EventTypes.DID_FINISH_LOADING_MAP);
    }

    @Override // org.maplibre.android.maps.MapView.OnWillStartRenderingFrameListener
    public void onWillStartRenderingFrame() {
        handleMapChangedEvent(EventTypes.WILL_START_RENDERING_FRAME);
    }

    @Override // org.maplibre.android.maps.MapView.OnDidFinishRenderingFrameListener
    public void onDidFinishRenderingFrame(boolean z, double d, double d2) {
        if (z) {
            handleMapChangedEvent(EventTypes.DID_FINISH_RENDERING_FRAME_FULLY);
        } else {
            handleMapChangedEvent(EventTypes.DID_FINISH_RENDERING_FRAME);
        }
    }

    @Override // org.maplibre.android.maps.MapView.OnWillStartRenderingMapListener
    public void onWillStartRenderingMap() {
        handleMapChangedEvent(EventTypes.WILL_START_RENDERING_MAP);
    }

    @Override // org.maplibre.android.maps.MapView.OnDidFinishRenderingMapListener
    public void onDidFinishRenderingMap(boolean z) {
        if (z) {
            for (Pair<Integer, ReadableArray> pair : this.mPreRenderMethods) {
                Integer num = (Integer) pair.first;
                this.mManager.receiveCommand(this, num.intValue(), (ReadableArray) pair.second);
            }
            this.mPreRenderMethods.clear();
            handleMapChangedEvent(EventTypes.DID_FINISH_RENDERING_MAP_FULLY);
            return;
        }
        handleMapChangedEvent(EventTypes.DID_FINISH_RENDERING_MAP);
    }

    @Override // org.maplibre.android.maps.MapView.OnDidFinishLoadingStyleListener
    public void onDidFinishLoadingStyle() {
        handleMapChangedEvent(EventTypes.DID_FINISH_LOADING_STYLE);
    }

    @Override // org.maplibre.android.maps.MapView.OnStyleImageMissingListener
    public void onStyleImageMissing(String str) {
        Iterator<RCTMLNImages> it = this.mImages.iterator();
        while (it.hasNext()) {
            if (it.next().addMissingImageToStyle(str, this.mMap)) {
                return;
            }
        }
        Iterator<RCTMLNImages> it2 = this.mImages.iterator();
        while (it2.hasNext()) {
            it2.next().sendImageMissingEvent(str, this.mMap);
        }
    }

    private float getDisplayDensity() {
        return this.mContext.getResources().getDisplayMetrics().density;
    }

    public void setReactStyleURL(String str) {
        this.mStyleURL = str;
        if (this.mMap != null) {
            removeAllSourcesFromMap();
            if (isJSONValid(this.mStyleURL)) {
                this.mMap.setStyle(new Style.Builder().fromJson(this.mStyleURL), new Style.OnStyleLoaded() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.9
                    @Override // org.maplibre.android.maps.Style.OnStyleLoaded
                    public void onStyleLoaded(Style style) {
                        RCTMLNMapView.this.addAllSourcesToMap();
                    }
                });
            } else {
                this.mMap.setStyle(str, new Style.OnStyleLoaded() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.10
                    @Override // org.maplibre.android.maps.Style.OnStyleLoaded
                    public void onStyleLoaded(Style style) {
                        RCTMLNMapView.this.addAllSourcesToMap();
                    }
                });
            }
        }
    }

    public void setReactPreferredFramesPerSecond(Integer num) {
        this.mPreferredFramesPerSecond = num;
        updatePreferredFramesPerSecond();
    }

    public void setReactContentInset(ReadableArray readableArray) {
        this.mInsets = readableArray;
        updateInsets();
    }

    public void setLocalizeLabels(boolean z) {
        this.mLocalizeLabels = z;
    }

    public void setReactZoomEnabled(boolean z) {
        this.mZoomEnabled = Boolean.valueOf(z);
        updateUISettings();
    }

    public void setReactScrollEnabled(boolean z) {
        this.mScrollEnabled = Boolean.valueOf(z);
        updateUISettings();
    }

    public void setReactPitchEnabled(boolean z) {
        this.mPitchEnabled = Boolean.valueOf(z);
        updateUISettings();
    }

    public void setReactRotateEnabled(boolean z) {
        this.mRotateEnabled = Boolean.valueOf(z);
        updateUISettings();
    }

    public void setReactLogoEnabled(boolean z) {
        this.mLogoEnabled = Boolean.valueOf(z);
        updateUISettings();
    }

    public void setReactLogoPosition(ReadableMap readableMap) {
        if (readableMap == null) {
            if (this.mLogoGravity != null) {
                MapLibreMapOptions mapLibreMapOptionsCreateFromAttributes = MapLibreMapOptions.createFromAttributes(this.mContext);
                this.mLogoGravity = Integer.valueOf(mapLibreMapOptionsCreateFromAttributes.getLogoGravity());
                this.mLogoMargins = Arrays.copyOf(mapLibreMapOptionsCreateFromAttributes.getLogoMargins(), 4);
                updateUISettings();
                return;
            }
            return;
        }
        this.mLogoGravity = 0;
        if (readableMap.hasKey("left")) {
            this.mLogoGravity = Integer.valueOf(this.mLogoGravity.intValue() | GravityCompat.START);
        }
        if (readableMap.hasKey("right")) {
            this.mLogoGravity = Integer.valueOf(this.mLogoGravity.intValue() | GravityCompat.END);
        }
        if (readableMap.hasKey("top")) {
            this.mLogoGravity = Integer.valueOf(this.mLogoGravity.intValue() | 48);
        }
        if (readableMap.hasKey("bottom")) {
            this.mLogoGravity = Integer.valueOf(this.mLogoGravity.intValue() | 80);
        }
        float displayDensity = getDisplayDensity();
        int[] iArr = new int[4];
        iArr[0] = readableMap.hasKey("left") ? ((int) displayDensity) * readableMap.getInt("left") : 0;
        iArr[1] = readableMap.hasKey("top") ? ((int) displayDensity) * readableMap.getInt("top") : 0;
        iArr[2] = readableMap.hasKey("right") ? ((int) displayDensity) * readableMap.getInt("right") : 0;
        iArr[3] = readableMap.hasKey("bottom") ? ((int) displayDensity) * readableMap.getInt("bottom") : 0;
        this.mLogoMargins = iArr;
        updateUISettings();
    }

    public void setReactCompassEnabled(boolean z) {
        this.mCompassEnabled = Boolean.valueOf(z);
        updateUISettings();
    }

    public void setReactCompassViewMargins(ReadableMap readableMap) {
        this.mCompassViewMargins = readableMap;
        updateUISettings();
    }

    public void setReactCompassViewPosition(int i) {
        this.mCompassViewPosition = i;
        updateUISettings();
    }

    public void setReactAttributionEnabled(boolean z) {
        this.mAttributionEnabled = Boolean.valueOf(z);
        updateUISettings();
    }

    public void setReactAttributionPosition(ReadableMap readableMap) {
        if (readableMap == null) {
            if (this.mAttributionGravity != null) {
                MapLibreMapOptions mapLibreMapOptionsCreateFromAttributes = MapLibreMapOptions.createFromAttributes(this.mContext);
                this.mAttributionGravity = Integer.valueOf(mapLibreMapOptionsCreateFromAttributes.getAttributionGravity());
                this.mAttributionMargin = Arrays.copyOf(mapLibreMapOptionsCreateFromAttributes.getAttributionMargins(), 4);
                updateUISettings();
                return;
            }
            return;
        }
        this.mAttributionGravity = 0;
        if (readableMap.hasKey("left")) {
            this.mAttributionGravity = Integer.valueOf(this.mAttributionGravity.intValue() | GravityCompat.START);
        }
        if (readableMap.hasKey("right")) {
            this.mAttributionGravity = Integer.valueOf(this.mAttributionGravity.intValue() | GravityCompat.END);
        }
        if (readableMap.hasKey("top")) {
            this.mAttributionGravity = Integer.valueOf(this.mAttributionGravity.intValue() | 48);
        }
        if (readableMap.hasKey("bottom")) {
            this.mAttributionGravity = Integer.valueOf(this.mAttributionGravity.intValue() | 80);
        }
        float displayDensity = getDisplayDensity();
        int[] iArr = new int[4];
        iArr[0] = readableMap.hasKey("left") ? Math.round(readableMap.getInt("left") * displayDensity) : 0;
        iArr[1] = readableMap.hasKey("top") ? Math.round(readableMap.getInt("top") * displayDensity) : 0;
        iArr[2] = readableMap.hasKey("right") ? Math.round(readableMap.getInt("right") * displayDensity) : 0;
        iArr[3] = readableMap.hasKey("bottom") ? Math.round(displayDensity * readableMap.getInt("bottom")) : 0;
        this.mAttributionMargin = iArr;
        updateUISettings();
    }

    public void queryRenderedFeaturesAtPoint(String str, PointF pointF, Expression expression, List<String> list) {
        List<Feature> listQueryRenderedFeatures = this.mMap.queryRenderedFeatures(pointF, expression, (String[]) list.toArray(new String[list.size()]));
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("data", FeatureCollection.fromFeatures(listQueryRenderedFeatures).toJson());
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
    }

    public void getZoom(String str) {
        CameraPosition cameraPosition = this.mMap.getCameraPosition();
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putDouble("zoom", cameraPosition.zoom);
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
    }

    public void queryRenderedFeaturesInRect(String str, RectF rectF, Expression expression, List<String> list) {
        List<Feature> listQueryRenderedFeatures = this.mMap.queryRenderedFeatures(rectF, expression, (String[]) list.toArray(new String[list.size()]));
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("data", FeatureCollection.fromFeatures(listQueryRenderedFeatures).toJson());
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
    }

    public void getVisibleBounds(String str) {
        VisibleRegion visibleRegion = this.mMap.getProjection().getVisibleRegion();
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putArray("visibleBounds", GeoJSONUtils.fromLatLngBounds(visibleRegion.latLngBounds));
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
    }

    public void getPointInView(String str, LatLng latLng) {
        PointF screenLocation = this.mMap.getProjection().toScreenLocation(latLng);
        float displayDensity = getDisplayDensity();
        screenLocation.x /= displayDensity;
        screenLocation.y /= displayDensity;
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        writableNativeArray.pushDouble(screenLocation.x);
        writableNativeArray.pushDouble(screenLocation.y);
        writableNativeMap.putArray("pointInView", writableNativeArray);
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
    }

    public void getCoordinateFromView(String str, PointF pointF) {
        float displayDensity = getDisplayDensity();
        pointF.x *= displayDensity;
        pointF.y *= displayDensity;
        LatLng latLngFromScreenLocation = this.mMap.getProjection().fromScreenLocation(pointF);
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        writableNativeArray.pushDouble(latLngFromScreenLocation.getLongitude());
        writableNativeArray.pushDouble(latLngFromScreenLocation.getLatitude());
        writableNativeMap.putArray("coordinateFromView", writableNativeArray);
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
    }

    public void takeSnap(final String str, final boolean z) {
        MapLibreMap mapLibreMap = this.mMap;
        if (mapLibreMap == null) {
            throw new Error("takeSnap should only be called after the map has rendered");
        }
        mapLibreMap.snapshot(new MapLibreMap.SnapshotReadyCallback() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.11
            @Override // org.maplibre.android.maps.MapLibreMap.SnapshotReadyCallback
            public void onSnapshotReady(Bitmap bitmap) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString(ReactNativeBlobUtilConst.DATA_ENCODE_URI, z ? BitmapUtils.createTempFile(RCTMLNMapView.this.mContext, bitmap) : BitmapUtils.createBase64(bitmap));
                RCTMLNMapView.this.mManager.handleEvent(new AndroidCallbackEvent(RCTMLNMapView.this, str, writableNativeMap));
            }
        });
    }

    public void getCenter(String str) {
        LatLng latLng = this.mMap.getCameraPosition().target;
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        writableNativeArray.pushDouble(latLng.getLongitude());
        writableNativeArray.pushDouble(latLng.getLatitude());
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putArray("center", writableNativeArray);
        this.mManager.handleEvent(new AndroidCallbackEvent(this, str, writableNativeMap));
    }

    public void showAttribution() {
        new AttributionDialogManager(this.mContext, this.mMap).onClick(this);
    }

    public void setSourceVisibility(final boolean z, final String str, @Nullable final String str2) {
        MapLibreMap mapLibreMap = this.mMap;
        if (mapLibreMap == null) {
            return;
        }
        mapLibreMap.getStyle(new Style.OnStyleLoaded() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.12
            @Override // org.maplibre.android.maps.Style.OnStyleLoaded
            public void onStyleLoaded(Style style) {
                String str3;
                for (Layer layer : style.getLayers()) {
                    LayerSourceInfo layerSourceInfo = new LayerSourceInfo(layer);
                    if (layerSourceInfo.sourceId.equals(str) && ((str3 = str2) == null || str3.equals(layerSourceInfo.sourceLayerId))) {
                        PropertyValue<?>[] propertyValueArr = new PropertyValue[1];
                        propertyValueArr[0] = PropertyFactory.visibility(z ? "visible" : "none");
                        layer.setProperties(propertyValueArr);
                    }
                }
            }
        });
    }

    public void init() {
        getViewTreeObserver().dispatchOnGlobalLayout();
    }

    @Override // org.maplibre.android.maps.MapView
    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public void getStyle(Style.OnStyleLoaded onStyleLoaded) {
        MapLibreMap mapLibreMap = this.mMap;
        if (mapLibreMap == null) {
            return;
        }
        mapLibreMap.getStyle(onStyleLoaded);
    }

    private void updateUISettings() {
        MapLibreMap mapLibreMap = this.mMap;
        if (mapLibreMap == null) {
            return;
        }
        UiSettings uiSettings = mapLibreMap.getUiSettings();
        if (this.mScrollEnabled != null && uiSettings.isScrollGesturesEnabled() != this.mScrollEnabled.booleanValue()) {
            uiSettings.setScrollGesturesEnabled(this.mScrollEnabled.booleanValue());
            if (!this.mScrollEnabled.booleanValue()) {
                this.mMap.getGesturesManager().getMoveGestureDetector().interrupt();
            }
        }
        if (this.mPitchEnabled != null && uiSettings.isTiltGesturesEnabled() != this.mPitchEnabled.booleanValue()) {
            uiSettings.setTiltGesturesEnabled(this.mPitchEnabled.booleanValue());
        }
        if (this.mRotateEnabled != null && uiSettings.isRotateGesturesEnabled() != this.mRotateEnabled.booleanValue()) {
            uiSettings.setRotateGesturesEnabled(this.mRotateEnabled.booleanValue());
            if (!this.mRotateEnabled.booleanValue()) {
                this.mMap.getGesturesManager().getRotateGestureDetector().interrupt();
            }
        }
        if (this.mAttributionEnabled != null && uiSettings.isAttributionEnabled() != this.mAttributionEnabled.booleanValue()) {
            uiSettings.setAttributionEnabled(this.mAttributionEnabled.booleanValue());
        }
        if (this.mAttributionGravity != null && uiSettings.getAttributionGravity() != this.mAttributionGravity.intValue()) {
            uiSettings.setAttributionGravity(this.mAttributionGravity.intValue());
        }
        if (this.mAttributionMargin != null && (uiSettings.getAttributionMarginLeft() != this.mAttributionMargin[0] || uiSettings.getAttributionMarginTop() != this.mAttributionMargin[1] || uiSettings.getAttributionMarginRight() != this.mAttributionMargin[2] || uiSettings.getAttributionMarginBottom() != this.mAttributionMargin[3])) {
            int[] iArr = this.mAttributionMargin;
            uiSettings.setAttributionMargins(iArr[0], iArr[1], iArr[2], iArr[3]);
        }
        Integer num = this.mTintColor;
        if (num != null) {
            uiSettings.setAttributionTintColor(num.intValue());
        }
        if (this.mLogoEnabled != null && uiSettings.isLogoEnabled() != this.mLogoEnabled.booleanValue()) {
            uiSettings.setLogoEnabled(this.mLogoEnabled.booleanValue());
        }
        if (this.mLogoGravity != null && uiSettings.getLogoGravity() != this.mLogoGravity.intValue()) {
            uiSettings.setLogoGravity(this.mLogoGravity.intValue());
        }
        if (this.mLogoMargins != null && (uiSettings.getLogoMarginLeft() != this.mLogoMargins[0] || uiSettings.getLogoMarginTop() != this.mLogoMargins[1] || uiSettings.getLogoMarginRight() != this.mLogoMargins[2] || uiSettings.getLogoMarginBottom() != this.mLogoMargins[3])) {
            int[] iArr2 = this.mLogoMargins;
            uiSettings.setLogoMargins(iArr2[0], iArr2[1], iArr2[2], iArr2[3]);
        }
        if (this.mCompassEnabled != null && uiSettings.isCompassEnabled() != this.mCompassEnabled.booleanValue()) {
            uiSettings.setCompassEnabled(this.mCompassEnabled.booleanValue());
        }
        if (this.mCompassViewPosition != -1 && uiSettings.isCompassEnabled()) {
            int i = this.mCompassViewPosition;
            if (i == 0) {
                uiSettings.setCompassGravity(8388659);
            } else if (i == 1) {
                uiSettings.setCompassGravity(8388661);
            } else if (i == 2) {
                uiSettings.setCompassGravity(8388691);
            } else if (i == 3) {
                uiSettings.setCompassGravity(8388693);
            }
        }
        if (this.mCompassViewMargins != null && uiSettings.isCompassEnabled()) {
            float f = getResources().getDisplayMetrics().density;
            int iRound = Math.round(this.mCompassViewMargins.getInt("x") * f);
            int iRound2 = Math.round(this.mCompassViewMargins.getInt("y") * f);
            int compassGravity = uiSettings.getCompassGravity();
            if (compassGravity == 8388659) {
                uiSettings.setCompassMargins(iRound, iRound2, 0, 0);
            } else if (compassGravity == 8388691) {
                uiSettings.setCompassMargins(iRound, 0, 0, iRound2);
            } else if (compassGravity != 8388693) {
                uiSettings.setCompassMargins(0, iRound2, iRound, 0);
            } else {
                uiSettings.setCompassMargins(0, 0, iRound, iRound2);
            }
        }
        if (this.mZoomEnabled == null || uiSettings.isZoomGesturesEnabled() == this.mZoomEnabled.booleanValue()) {
            return;
        }
        uiSettings.setZoomGesturesEnabled(this.mZoomEnabled.booleanValue());
        if (this.mZoomEnabled.booleanValue()) {
            return;
        }
        this.mMap.getGesturesManager().getStandardScaleGestureDetector().interrupt();
    }

    private void updatePreferredFramesPerSecond() {
        Integer num = this.mPreferredFramesPerSecond;
        if (num == null) {
            return;
        }
        setMaximumFps(num.intValue());
    }

    public double[] getContentInset() {
        double d;
        double d2;
        double d3;
        double d4;
        ReadableArray readableArray = this.mInsets;
        if (readableArray == null) {
            return new double[]{0.0d, 0.0d, 0.0d, 0.0d};
        }
        if (readableArray.size() == 4) {
            d = this.mInsets.getInt(0);
            d2 = this.mInsets.getInt(1);
            d3 = this.mInsets.getInt(2);
            d4 = this.mInsets.getInt(3);
        } else if (this.mInsets.size() == 2) {
            d = this.mInsets.getInt(0);
            d2 = this.mInsets.getInt(1);
            d3 = d;
            d4 = d2;
        } else {
            d = this.mInsets.size() == 1 ? this.mInsets.getInt(0) : 0.0d;
            d2 = d;
            d3 = d2;
            d4 = d3;
        }
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        return new double[]{d4 * displayMetrics.scaledDensity, d * displayMetrics.scaledDensity, d2 * displayMetrics.scaledDensity, d3 * displayMetrics.scaledDensity};
    }

    private void updateInsets() {
        if (this.mMap == null || this.mInsets == null) {
            return;
        }
        double[] contentInset = getContentInset();
        double d = contentInset[1];
        double d2 = contentInset[2];
        double d3 = contentInset[3];
        this.mMap.setPadding(Double.valueOf(contentInset[0]).intValue(), Double.valueOf(d).intValue(), Double.valueOf(d2).intValue(), Double.valueOf(d3).intValue());
    }

    private void setLifecycleListeners() {
        ReactContext reactContext = (ReactContext) this.mContext;
        LifecycleEventListener lifecycleEventListener = new LifecycleEventListener() { // from class: com.maplibre.rctmln.components.mapview.RCTMLNMapView.13
            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostResume() {
                RCTMLNMapView.this.onResume();
            }

            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostPause() {
                RCTMLNMapView.this.onPause();
            }

            @Override // com.facebook.react.bridge.LifecycleEventListener
            public void onHostDestroy() {
                RCTMLNMapView.this.dispose();
            }
        };
        this.mLifeCycleListener = lifecycleEventListener;
        reactContext.addLifecycleEventListener(lifecycleEventListener);
    }

    private WritableMap makeRegionPayload(Boolean bool) {
        CameraPosition cameraPosition = this.mMap.getCameraPosition();
        if (cameraPosition == null || cameraPosition.target == null) {
            return new WritableNativeMap();
        }
        LatLng latLng = new LatLng(cameraPosition.target.getLatitude(), cameraPosition.target.getLongitude());
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putDouble("zoomLevel", cameraPosition.zoom);
        writableNativeMap.putDouble("heading", cameraPosition.bearing);
        writableNativeMap.putDouble("pitch", cameraPosition.tilt);
        writableNativeMap.putBoolean("animated", bool == null ? this.mCameraChangeTracker.isAnimated() : bool.booleanValue());
        writableNativeMap.putBoolean("isUserInteraction", this.mCameraChangeTracker.isUserInteraction());
        try {
            writableNativeMap.putArray("visibleBounds", GeoJSONUtils.fromLatLngBounds(this.mMap.getProjection().getVisibleRegion().latLngBounds));
        } catch (Exception e) {
            Logger.e("RCTMLNMapView", String.format("An error occurred while attempting to make the region: %s", e.getMessage()));
        }
        return GeoJSONUtils.toPointFeature(latLng, writableNativeMap);
    }

    public void sendRegionChangeEvent(boolean z) {
        this.mManager.handleEvent(new MapChangeEvent(this, EventTypes.REGION_DID_CHANGE, makeRegionPayload(new Boolean(z))));
        this.mCameraChangeTracker.setReason(-1);
    }

    private void removeAllSourcesFromMap() {
        if (this.mSources.size() == 0) {
            return;
        }
        Iterator<String> it = this.mSources.keySet().iterator();
        while (it.hasNext()) {
            this.mSources.get(it.next()).removeFromMap(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllSourcesToMap() {
        if (this.mSources.size() == 0) {
            return;
        }
        Iterator<String> it = this.mSources.keySet().iterator();
        while (it.hasNext()) {
            this.mSources.get(it.next()).addToMap(this);
        }
    }

    private List<RCTSource> getAllTouchableSources() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.mSources.keySet().iterator();
        while (it.hasNext()) {
            RCTSource rCTSource = this.mSources.get(it.next());
            if (rCTSource != null && rCTSource.hasPressListener()) {
                arrayList.add(rCTSource);
            }
        }
        return arrayList;
    }

    private List<RCTMLNShapeSource> getAllShapeSources() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.mSources.keySet().iterator();
        while (it.hasNext()) {
            RCTSource rCTSource = this.mSources.get(it.next());
            if (rCTSource instanceof RCTMLNShapeSource) {
                arrayList.add((RCTMLNShapeSource) rCTSource);
            }
        }
        return arrayList;
    }

    private RCTSource getTouchableSourceWithHighestZIndex(List<RCTSource> list) {
        if (list != null && list.size() != 0) {
            if (list.size() == 1) {
                return list.get(0);
            }
            HashMap map = new HashMap();
            for (RCTSource rCTSource : list) {
                for (String str : rCTSource.getLayerIDs()) {
                    map.put(str, rCTSource);
                }
            }
            List<Layer> layers = this.mMap.getStyle().getLayers();
            for (int size = layers.size() - 1; size >= 0; size--) {
                String id = layers.get(size).getId();
                if (map.containsKey(id)) {
                    return (RCTSource) map.get(id);
                }
            }
        }
        return null;
    }

    private boolean hasSetCenterCoordinate() {
        LatLng latLng = this.mMap.getCameraPosition().target;
        return (latLng.getLatitude() == 0.0d || latLng.getLongitude() == 0.0d) ? false : true;
    }

    private double getMapRotation() {
        return this.mMap.getCameraPosition().bearing;
    }

    public void sendRegionDidChangeEvent() {
        handleMapChangedEvent(EventTypes.REGION_DID_CHANGE);
        this.mCameraChangeTracker.setReason(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMapChangedEvent(String str) {
        MapChangeEvent mapChangeEvent;
        if (canHandleEvent(str)) {
            str.hashCode();
            switch (str) {
                case "regiondidchange":
                case "regionischanging":
                case "regionwillchange":
                    mapChangeEvent = new MapChangeEvent(this, str, makeRegionPayload(null));
                    break;
                default:
                    mapChangeEvent = new MapChangeEvent(this, str);
                    break;
            }
            this.mManager.handleEvent(mapChangeEvent);
        }
    }

    private boolean canHandleEvent(String str) {
        HashSet<String> hashSet = this.mHandledMapChangedEvents;
        return hashSet == null || hashSet.contains(str);
    }

    public void setHandledMapChangedEvents(ArrayList<String> arrayList) {
        this.mHandledMapChangedEvents = new HashSet<>(arrayList);
    }

    private void sendUserLocationUpdateEvent(Location location) {
        if (location == null) {
            return;
        }
        this.mManager.handleEvent(new MapChangeEvent(this, EventTypes.USER_LOCATION_UPDATED, makeLocationChangePayload(location)));
    }

    private WritableMap makeLocationChangePayload(Location location) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        WritableNativeMap writableNativeMap2 = new WritableNativeMap();
        writableNativeMap2.putDouble("longitude", location.getLongitude());
        writableNativeMap2.putDouble("latitude", location.getLatitude());
        writableNativeMap2.putDouble("altitude", location.getAltitude());
        writableNativeMap2.putDouble("accuracy", location.getAccuracy());
        writableNativeMap2.putDouble("heading", location.getBearing());
        writableNativeMap2.putDouble("course", location.getBearing());
        writableNativeMap2.putDouble("speed", location.getSpeed());
        writableNativeMap.putMap("coords", writableNativeMap2);
        writableNativeMap.putDouble("timestamp", location.getTime());
        return writableNativeMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpImage(Style style) {
        style.addImage("MARKER_IMAGE_ID", BitmapFactory.decodeResource(getResources(), R.drawable.red_marker));
    }

    public ViewGroup offscreenAnnotationViewContainer() {
        if (this.mOffscreenAnnotationViewContainer == null) {
            this.mOffscreenAnnotationViewContainer = new FrameLayout(getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(0, 0);
            layoutParams.setMargins(-10000, -10000, -10000, -10000);
            this.mOffscreenAnnotationViewContainer.setLayoutParams(layoutParams);
            addView(this.mOffscreenAnnotationViewContainer);
        }
        return this.mOffscreenAnnotationViewContainer;
    }

    public MarkerViewManager getMarkerViewManager(MapLibreMap mapLibreMap) {
        if (this.markerViewManager == null) {
            if (mapLibreMap == null) {
                throw new Error("makerViewManager should be called one the map has loaded");
            }
            this.markerViewManager = new MarkerViewManager(this, mapLibreMap);
        }
        return this.markerViewManager;
    }

    public LocationComponentManager getLocationComponentManager() {
        if (this.mLocationComponentManager == null) {
            this.mLocationComponentManager = new LocationComponentManager(this, this.mContext);
        }
        return this.mLocationComponentManager;
    }

    @Nullable
    public Integer getTintColor() {
        return this.mTintColor;
    }

    public void setTintColor(@Nullable Integer num) {
        if (this.mTintColor == num) {
            return;
        }
        this.mTintColor = num;
        updateUISettings();
        LocationComponentManager locationComponentManager = this.mLocationComponentManager;
        if (locationComponentManager == null) {
            return;
        }
        locationComponentManager.update(getMapboxMap().getStyle());
    }
}

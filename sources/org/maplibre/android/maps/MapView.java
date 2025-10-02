package org.maplibre.android.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.maplibre.android.MapLibre;
import org.maplibre.android.MapStrictMode;
import org.maplibre.android.R;
import org.maplibre.android.constants.MapLibreConstants;
import org.maplibre.android.exceptions.MapLibreConfigurationException;
import org.maplibre.android.gestures.AndroidGesturesManager;
import org.maplibre.android.location.LocationComponent;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.NativeMapView;
import org.maplibre.android.maps.renderer.MapRenderer;
import org.maplibre.android.maps.widgets.CompassView;
import org.maplibre.android.net.ConnectivityReceiver;
import org.maplibre.android.storage.FileSource;
import org.maplibre.android.tile.TileOperation;
import org.maplibre.android.utils.BitmapUtils;
import timber.log.Timber;

/* loaded from: classes2.dex */
public class MapView extends FrameLayout implements NativeMapView.ViewCallback {
    private AttributionClickListener attributionClickListener;
    private final CameraChangeDispatcher cameraDispatcher;
    private CompassView compassView;
    private boolean destroyed;
    private final FocalPointInvalidator focalInvalidator;
    private PointF focalPoint;
    private final InitialRenderCallback initialRenderCallback;
    private boolean isStarted;
    private final MapCallback mapCallback;
    private final MapChangeReceiver mapChangeReceiver;
    private MapGestureDetector mapGestureDetector;
    private MapKeyListener mapKeyListener;
    private MapRenderer mapRenderer;
    private MapLibreMap maplibreMap;
    MapLibreMapOptions maplibreMapOptions;
    private NativeMap nativeMapView;
    private final GesturesManagerInteractionListener registerTouchListener;
    private View renderView;
    private Bundle savedInstanceState;

    public interface OnCameraDidChangeListener {
        void onCameraDidChange(boolean z);
    }

    public interface OnCameraIsChangingListener {
        void onCameraIsChanging();
    }

    public interface OnCameraWillChangeListener {
        void onCameraWillChange(boolean z);
    }

    public interface OnCanRemoveUnusedStyleImageListener {
        boolean onCanRemoveUnusedStyleImage(String str);
    }

    public interface OnDidBecomeIdleListener {
        void onDidBecomeIdle();
    }

    public interface OnDidFailLoadingMapListener {
        void onDidFailLoadingMap(String str);
    }

    public interface OnDidFinishLoadingMapListener {
        void onDidFinishLoadingMap();
    }

    public interface OnDidFinishLoadingStyleListener {
        void onDidFinishLoadingStyle();
    }

    public interface OnDidFinishRenderingFrameListener {
        void onDidFinishRenderingFrame(boolean z, double d, double d2);
    }

    public interface OnDidFinishRenderingMapListener {
        void onDidFinishRenderingMap(boolean z);
    }

    public interface OnGlyphsErrorListener {
        void onGlyphsError(String[] strArr, int i, int i2);
    }

    public interface OnGlyphsLoadedListener {
        void onGlyphsLoaded(String[] strArr, int i, int i2);
    }

    public interface OnGlyphsRequestedListener {
        void onGlyphsRequested(String[] strArr, int i, int i2);
    }

    public interface OnPostCompileShaderListener {
        void onPostCompileShader(int i, int i2, String str);
    }

    public interface OnPreCompileShaderListener {
        void onPreCompileShader(int i, int i2, String str);
    }

    public interface OnShaderCompileFailedListener {
        void onShaderCompileFailed(int i, int i2, String str);
    }

    public interface OnSourceChangedListener {
        void onSourceChangedListener(String str);
    }

    public interface OnSpriteErrorListener {
        void onSpriteError(String str, String str2);
    }

    public interface OnSpriteLoadedListener {
        void onSpriteLoaded(String str, String str2);
    }

    public interface OnSpriteRequestedListener {
        void onSpriteRequested(String str, String str2);
    }

    public interface OnStyleImageMissingListener {
        void onStyleImageMissing(String str);
    }

    public interface OnTileActionListener {
        void onTileAction(TileOperation tileOperation, int i, int i2, int i3, int i4, int i5, String str);
    }

    public interface OnWillStartLoadingMapListener {
        void onWillStartLoadingMap();
    }

    public interface OnWillStartRenderingFrameListener {
        void onWillStartRenderingFrame();
    }

    public interface OnWillStartRenderingMapListener {
        void onWillStartRenderingMap();
    }

    public MapView(Context context) {
        super(context);
        this.mapChangeReceiver = new MapChangeReceiver();
        this.mapCallback = new MapCallback();
        this.initialRenderCallback = new InitialRenderCallback();
        this.focalInvalidator = new FocalPointInvalidator();
        this.registerTouchListener = new GesturesManagerInteractionListener();
        this.cameraDispatcher = new CameraChangeDispatcher();
        Timber.d("MapView constructed with context", new Object[0]);
        initialize(context, MapLibreMapOptions.createFromAttributes(context));
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mapChangeReceiver = new MapChangeReceiver();
        this.mapCallback = new MapCallback();
        this.initialRenderCallback = new InitialRenderCallback();
        this.focalInvalidator = new FocalPointInvalidator();
        this.registerTouchListener = new GesturesManagerInteractionListener();
        this.cameraDispatcher = new CameraChangeDispatcher();
        Timber.d("MapView constructed with context and attribute set", new Object[0]);
        initialize(context, MapLibreMapOptions.createFromAttributes(context, attributeSet));
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mapChangeReceiver = new MapChangeReceiver();
        this.mapCallback = new MapCallback();
        this.initialRenderCallback = new InitialRenderCallback();
        this.focalInvalidator = new FocalPointInvalidator();
        this.registerTouchListener = new GesturesManagerInteractionListener();
        this.cameraDispatcher = new CameraChangeDispatcher();
        Timber.d("MapView constructed with context, attributeSet and defStyleAttr", new Object[0]);
        initialize(context, MapLibreMapOptions.createFromAttributes(context, attributeSet));
    }

    public MapView(Context context, MapLibreMapOptions mapLibreMapOptions) {
        super(context);
        this.mapChangeReceiver = new MapChangeReceiver();
        this.mapCallback = new MapCallback();
        this.initialRenderCallback = new InitialRenderCallback();
        this.focalInvalidator = new FocalPointInvalidator();
        this.registerTouchListener = new GesturesManagerInteractionListener();
        this.cameraDispatcher = new CameraChangeDispatcher();
        Timber.d("MapView constructed with context and MapLibreMapOptions", new Object[0]);
        initialize(context, mapLibreMapOptions == null ? MapLibreMapOptions.createFromAttributes(context) : mapLibreMapOptions);
    }

    protected void initialize(Context context, MapLibreMapOptions mapLibreMapOptions) {
        if (isInEditMode()) {
            return;
        }
        if (!MapLibre.hasInstance()) {
            throw new MapLibreConfigurationException();
        }
        setForeground(new ColorDrawable(mapLibreMapOptions.getForegroundLoadColor()));
        this.maplibreMapOptions = mapLibreMapOptions;
        setContentDescription(context.getString(R.string.maplibre_mapActionDescription));
        setWillNotDraw(false);
        initializeDrawingSurface(mapLibreMapOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeMap() {
        Context context = getContext();
        this.focalInvalidator.addListener(createFocalPointChangeListener());
        Projection projection = new Projection(this.nativeMapView, this);
        UiSettings uiSettings = new UiSettings(projection, this.focalInvalidator, getPixelRatio(), this);
        LongSparseArray longSparseArray = new LongSparseArray();
        IconManager iconManager = new IconManager(this.nativeMapView);
        AnnotationManager annotationManager = new AnnotationManager(this, longSparseArray, iconManager, new AnnotationContainer(this.nativeMapView, longSparseArray), new MarkerContainer(this.nativeMapView, longSparseArray, iconManager), new PolygonContainer(this.nativeMapView, longSparseArray), new PolylineContainer(this.nativeMapView, longSparseArray), new ShapeAnnotationContainer(this.nativeMapView, longSparseArray));
        Transform transform = new Transform(this, this.nativeMapView, this.cameraDispatcher);
        ArrayList arrayList = new ArrayList();
        MapLibreMap mapLibreMap = new MapLibreMap(this.nativeMapView, transform, uiSettings, projection, this.registerTouchListener, this.cameraDispatcher, arrayList);
        this.maplibreMap = mapLibreMap;
        mapLibreMap.injectAnnotationManager(annotationManager);
        this.mapGestureDetector = new MapGestureDetector(context, transform, projection, uiSettings, annotationManager, this.cameraDispatcher);
        this.mapKeyListener = new MapKeyListener(transform, uiSettings, this.mapGestureDetector);
        this.maplibreMap.injectLocationComponent(new LocationComponent(this.maplibreMap, transform, arrayList));
        setClickable(true);
        setLongClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestDisallowInterceptTouchEvent(true);
        this.nativeMapView.setReachability(MapLibre.isConnected().booleanValue());
        Bundle bundle = this.savedInstanceState;
        if (bundle == null) {
            this.maplibreMap.initialise(context, this.maplibreMapOptions);
        } else {
            this.maplibreMap.onRestoreInstanceState(bundle);
        }
        this.mapCallback.initialised();
    }

    protected CompassView initialiseCompassView() {
        CompassView compassView = new CompassView(getContext());
        this.compassView = compassView;
        addView(compassView);
        this.compassView.setTag("compassView");
        this.compassView.getLayoutParams().width = -2;
        this.compassView.getLayoutParams().height = -2;
        this.compassView.setContentDescription(getResources().getString(R.string.maplibre_compassContentDescription));
        this.compassView.injectCompassAnimationListener(createCompassAnimationListener(this.cameraDispatcher));
        this.compassView.setOnClickListener(createCompassClickListener(this.cameraDispatcher));
        return this.compassView;
    }

    protected ImageView initialiseAttributionView() {
        ImageView imageView = new ImageView(getContext());
        addView(imageView);
        imageView.setTag("attrView");
        imageView.getLayoutParams().width = -2;
        imageView.getLayoutParams().height = -2;
        imageView.setAdjustViewBounds(true);
        imageView.setClickable(true);
        imageView.setFocusable(true);
        imageView.setContentDescription(getResources().getString(R.string.maplibre_attributionsIconContentDescription));
        imageView.setImageDrawable(BitmapUtils.getDrawableFromRes(getContext(), R.drawable.maplibre_info_bg_selector));
        AttributionClickListener attributionClickListener = new AttributionClickListener(getContext(), this.maplibreMap);
        this.attributionClickListener = attributionClickListener;
        imageView.setOnClickListener(attributionClickListener);
        return imageView;
    }

    protected ImageView initialiseLogoView() {
        ImageView imageView = new ImageView(getContext());
        addView(imageView);
        imageView.setTag("logoView");
        imageView.getLayoutParams().width = -2;
        imageView.getLayoutParams().height = -2;
        imageView.setImageDrawable(BitmapUtils.getDrawableFromRes(getContext(), R.drawable.maplibre_logo_icon));
        return imageView;
    }

    private FocalPointChangeListener createFocalPointChangeListener() {
        return new FocalPointChangeListener() { // from class: org.maplibre.android.maps.MapView.1
            @Override // org.maplibre.android.maps.FocalPointChangeListener
            public void onFocalPointChanged(PointF pointF) {
                MapView.this.focalPoint = pointF;
            }
        };
    }

    private MapLibreMap.OnCompassAnimationListener createCompassAnimationListener(final CameraChangeDispatcher cameraChangeDispatcher) {
        return new MapLibreMap.OnCompassAnimationListener() { // from class: org.maplibre.android.maps.MapView.2
            @Override // org.maplibre.android.maps.MapLibreMap.OnCompassAnimationListener
            public void onCompassAnimation() {
                cameraChangeDispatcher.onCameraMove();
            }

            @Override // org.maplibre.android.maps.MapLibreMap.OnCompassAnimationListener
            public void onCompassAnimationFinished() {
                if (MapView.this.compassView != null) {
                    MapView.this.compassView.isAnimating(false);
                }
                cameraChangeDispatcher.onCameraIdle();
            }
        };
    }

    private View.OnClickListener createCompassClickListener(final CameraChangeDispatcher cameraChangeDispatcher) {
        return new View.OnClickListener() { // from class: org.maplibre.android.maps.MapView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (MapView.this.maplibreMap == null || MapView.this.compassView == null) {
                    return;
                }
                if (MapView.this.focalPoint != null) {
                    MapView.this.maplibreMap.setFocalBearing(0.0d, MapView.this.focalPoint.x, MapView.this.focalPoint.y, 150L);
                } else {
                    MapView.this.maplibreMap.setFocalBearing(0.0d, MapView.this.maplibreMap.getWidth() / 2.0f, MapView.this.maplibreMap.getHeight() / 2.0f, 150L);
                }
                cameraChangeDispatcher.onCameraMoveStarted(3);
                MapView.this.compassView.isAnimating(true);
                MapView.this.compassView.postDelayed(MapView.this.compassView, 650L);
            }
        };
    }

    public void onCreate(Bundle bundle) {
        if (bundle == null || !bundle.getBoolean(MapLibreConstants.STATE_HAS_SAVED_STATE)) {
            return;
        }
        this.savedInstanceState = bundle;
    }

    private void initializeDrawingSurface(MapLibreMapOptions mapLibreMapOptions) {
        MapRenderer mapRendererCreate = MapRenderer.create(mapLibreMapOptions, getContext(), new Runnable() { // from class: org.maplibre.android.maps.MapView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m2952x6869dc55();
            }
        });
        this.mapRenderer = mapRendererCreate;
        View view = mapRendererCreate.getView();
        this.renderView = view;
        addView(view, 0);
        this.nativeMapView = new NativeMapView(getContext(), getPixelRatio(), mapLibreMapOptions.getCrossSourceCollisions(), this, this.mapChangeReceiver, this.mapRenderer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onSurfaceCreated, reason: merged with bridge method [inline-methods] */
    public void m2952x6869dc55() {
        post(new Runnable() { // from class: org.maplibre.android.maps.MapView.4
            @Override // java.lang.Runnable
            public void run() {
                if (MapView.this.destroyed || MapView.this.maplibreMap != null) {
                    return;
                }
                MapView.this.initializeMap();
                MapView.this.maplibreMap.onStart();
            }
        });
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (this.maplibreMap != null) {
            bundle.putBoolean(MapLibreConstants.STATE_HAS_SAVED_STATE, true);
            this.maplibreMap.onSaveInstanceState(bundle);
        }
    }

    public void onStart() {
        if (!this.isStarted) {
            ConnectivityReceiver.instance(getContext()).activate();
            FileSource.getInstance(getContext()).activate();
            this.isStarted = true;
        }
        MapLibreMap mapLibreMap = this.maplibreMap;
        if (mapLibreMap != null) {
            mapLibreMap.onStart();
        }
        MapRenderer mapRenderer = this.mapRenderer;
        if (mapRenderer != null) {
            mapRenderer.onStart();
        }
    }

    public void onResume() {
        MapRenderer mapRenderer = this.mapRenderer;
        if (mapRenderer != null) {
            mapRenderer.onResume();
        }
    }

    public void onPause() {
        MapRenderer mapRenderer = this.mapRenderer;
        if (mapRenderer != null) {
            mapRenderer.onPause();
        }
    }

    public void onStop() {
        AttributionClickListener attributionClickListener = this.attributionClickListener;
        if (attributionClickListener != null) {
            attributionClickListener.onStop();
        }
        if (this.maplibreMap != null) {
            this.mapGestureDetector.cancelAnimators();
            this.maplibreMap.onStop();
        }
        MapRenderer mapRenderer = this.mapRenderer;
        if (mapRenderer != null) {
            mapRenderer.onStop();
        }
        if (this.isStarted) {
            ConnectivityReceiver.instance(getContext()).deactivate();
            FileSource.getInstance(getContext()).deactivate();
            this.isStarted = false;
        }
    }

    public void onDestroy() {
        this.destroyed = true;
        this.mapChangeReceiver.clear();
        this.mapCallback.onDestroy();
        this.initialRenderCallback.onDestroy();
        CompassView compassView = this.compassView;
        if (compassView != null) {
            compassView.resetAnimation();
        }
        MapLibreMap mapLibreMap = this.maplibreMap;
        if (mapLibreMap != null) {
            mapLibreMap.onDestroy();
        }
        NativeMap nativeMap = this.nativeMapView;
        if (nativeMap != null) {
            nativeMap.destroy();
            this.nativeMapView = null;
        }
        MapRenderer mapRenderer = this.mapRenderer;
        if (mapRenderer != null) {
            mapRenderer.onDestroy();
        }
    }

    public void queueEvent(Runnable runnable) {
        MapRenderer mapRenderer = this.mapRenderer;
        if (mapRenderer == null) {
            throw new IllegalStateException("Calling MapView#queueEvent before mapRenderer is created.");
        }
        mapRenderer.queueEvent(runnable);
    }

    public void setMaximumFps(int i) {
        MapRenderer mapRenderer = this.mapRenderer;
        if (mapRenderer != null) {
            mapRenderer.setMaximumFps(i);
            return;
        }
        throw new IllegalStateException("Calling MapView#setMaximumFps before mapRenderer is created.");
    }

    public void setRenderingRefreshMode(MapRenderer.RenderingRefreshMode renderingRefreshMode) {
        MapRenderer mapRenderer = this.mapRenderer;
        if (mapRenderer != null) {
            mapRenderer.setRenderingRefreshMode(renderingRefreshMode);
            return;
        }
        throw new IllegalStateException("Calling MapView#setRenderingRefreshMode before mapRenderer is created.");
    }

    public MapRenderer.RenderingRefreshMode getRenderingRefreshMode() {
        MapRenderer mapRenderer = this.mapRenderer;
        if (mapRenderer == null) {
            throw new IllegalStateException("Calling MapView#getRenderingRefreshMode before mapRenderer is created.");
        }
        return mapRenderer.getRenderingRefreshMode();
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public View getRenderView() {
        return this.renderView;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (isGestureDetectorInitialized()) {
            return this.mapGestureDetector.onTouchEvent(motionEvent) || super.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (isKeyDetectorInitialized()) {
            return this.mapKeyListener.onKeyDown(i, keyEvent) || super.onKeyDown(i, keyEvent);
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        if (isKeyDetectorInitialized()) {
            return this.mapKeyListener.onKeyLongPress(i, keyEvent) || super.onKeyLongPress(i, keyEvent);
        }
        return super.onKeyLongPress(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (isKeyDetectorInitialized()) {
            return this.mapKeyListener.onKeyUp(i, keyEvent) || super.onKeyUp(i, keyEvent);
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (isKeyDetectorInitialized()) {
            return this.mapKeyListener.onTrackballEvent(motionEvent) || super.onTrackballEvent(motionEvent);
        }
        return super.onTrackballEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (isGestureDetectorInitialized()) {
            return this.mapGestureDetector.onGenericMotionEvent(motionEvent) || super.onGenericMotionEvent(motionEvent);
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public void onLowMemory() {
        NativeMap nativeMap = this.nativeMapView;
        if (nativeMap == null || this.maplibreMap == null || this.destroyed) {
            return;
        }
        nativeMap.onLowMemory();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        NativeMap nativeMap;
        if (isInEditMode() || (nativeMap = this.nativeMapView) == null) {
            return;
        }
        nativeMap.resizeView(i, i2);
    }

    public float getPixelRatio() {
        float pixelRatio = this.maplibreMapOptions.getPixelRatio();
        return pixelRatio == 0.0f ? getResources().getDisplayMetrics().density : pixelRatio;
    }

    @Override // org.maplibre.android.maps.NativeMapView.ViewCallback
    public Bitmap getViewContent() {
        return BitmapUtils.createBitmapFromView(this);
    }

    public void addOnCameraWillChangeListener(OnCameraWillChangeListener onCameraWillChangeListener) {
        this.mapChangeReceiver.addOnCameraWillChangeListener(onCameraWillChangeListener);
    }

    public void removeOnCameraWillChangeListener(OnCameraWillChangeListener onCameraWillChangeListener) {
        this.mapChangeReceiver.removeOnCameraWillChangeListener(onCameraWillChangeListener);
    }

    public void addOnCameraIsChangingListener(OnCameraIsChangingListener onCameraIsChangingListener) {
        this.mapChangeReceiver.addOnCameraIsChangingListener(onCameraIsChangingListener);
    }

    public void removeOnCameraIsChangingListener(OnCameraIsChangingListener onCameraIsChangingListener) {
        this.mapChangeReceiver.removeOnCameraIsChangingListener(onCameraIsChangingListener);
    }

    public void addOnCameraDidChangeListener(OnCameraDidChangeListener onCameraDidChangeListener) {
        this.mapChangeReceiver.addOnCameraDidChangeListener(onCameraDidChangeListener);
    }

    public void removeOnCameraDidChangeListener(OnCameraDidChangeListener onCameraDidChangeListener) {
        this.mapChangeReceiver.removeOnCameraDidChangeListener(onCameraDidChangeListener);
    }

    public void addOnWillStartLoadingMapListener(OnWillStartLoadingMapListener onWillStartLoadingMapListener) {
        this.mapChangeReceiver.addOnWillStartLoadingMapListener(onWillStartLoadingMapListener);
    }

    public void removeOnWillStartLoadingMapListener(OnWillStartLoadingMapListener onWillStartLoadingMapListener) {
        this.mapChangeReceiver.removeOnWillStartLoadingMapListener(onWillStartLoadingMapListener);
    }

    public void addOnDidFinishLoadingMapListener(OnDidFinishLoadingMapListener onDidFinishLoadingMapListener) {
        this.mapChangeReceiver.addOnDidFinishLoadingMapListener(onDidFinishLoadingMapListener);
    }

    public void removeOnDidFinishLoadingMapListener(OnDidFinishLoadingMapListener onDidFinishLoadingMapListener) {
        this.mapChangeReceiver.removeOnDidFinishLoadingMapListener(onDidFinishLoadingMapListener);
    }

    public void addOnDidFailLoadingMapListener(OnDidFailLoadingMapListener onDidFailLoadingMapListener) {
        this.mapChangeReceiver.addOnDidFailLoadingMapListener(onDidFailLoadingMapListener);
    }

    public void removeOnDidFailLoadingMapListener(OnDidFailLoadingMapListener onDidFailLoadingMapListener) {
        this.mapChangeReceiver.removeOnDidFailLoadingMapListener(onDidFailLoadingMapListener);
    }

    public void addOnWillStartRenderingFrameListener(OnWillStartRenderingFrameListener onWillStartRenderingFrameListener) {
        this.mapChangeReceiver.addOnWillStartRenderingFrameListener(onWillStartRenderingFrameListener);
    }

    public void removeOnWillStartRenderingFrameListener(OnWillStartRenderingFrameListener onWillStartRenderingFrameListener) {
        this.mapChangeReceiver.removeOnWillStartRenderingFrameListener(onWillStartRenderingFrameListener);
    }

    public void addOnDidFinishRenderingFrameListener(OnDidFinishRenderingFrameListener onDidFinishRenderingFrameListener) {
        this.mapChangeReceiver.addOnDidFinishRenderingFrameListener(onDidFinishRenderingFrameListener);
    }

    public void removeOnDidFinishRenderingFrameListener(OnDidFinishRenderingFrameListener onDidFinishRenderingFrameListener) {
        this.mapChangeReceiver.removeOnDidFinishRenderingFrameListener(onDidFinishRenderingFrameListener);
    }

    public void addOnWillStartRenderingMapListener(OnWillStartRenderingMapListener onWillStartRenderingMapListener) {
        this.mapChangeReceiver.addOnWillStartRenderingMapListener(onWillStartRenderingMapListener);
    }

    public void removeOnWillStartRenderingMapListener(OnWillStartRenderingMapListener onWillStartRenderingMapListener) {
        this.mapChangeReceiver.removeOnWillStartRenderingMapListener(onWillStartRenderingMapListener);
    }

    public void addOnDidFinishRenderingMapListener(OnDidFinishRenderingMapListener onDidFinishRenderingMapListener) {
        this.mapChangeReceiver.addOnDidFinishRenderingMapListener(onDidFinishRenderingMapListener);
    }

    public void removeOnDidFinishRenderingMapListener(OnDidFinishRenderingMapListener onDidFinishRenderingMapListener) {
        this.mapChangeReceiver.removeOnDidFinishRenderingMapListener(onDidFinishRenderingMapListener);
    }

    public void addOnDidBecomeIdleListener(OnDidBecomeIdleListener onDidBecomeIdleListener) {
        this.mapChangeReceiver.addOnDidBecomeIdleListener(onDidBecomeIdleListener);
    }

    public void removeOnDidBecomeIdleListener(OnDidBecomeIdleListener onDidBecomeIdleListener) {
        this.mapChangeReceiver.removeOnDidBecomeIdleListener(onDidBecomeIdleListener);
    }

    public void addOnDidFinishLoadingStyleListener(OnDidFinishLoadingStyleListener onDidFinishLoadingStyleListener) {
        this.mapChangeReceiver.addOnDidFinishLoadingStyleListener(onDidFinishLoadingStyleListener);
    }

    public void removeOnDidFinishLoadingStyleListener(OnDidFinishLoadingStyleListener onDidFinishLoadingStyleListener) {
        this.mapChangeReceiver.removeOnDidFinishLoadingStyleListener(onDidFinishLoadingStyleListener);
    }

    public void addOnSourceChangedListener(OnSourceChangedListener onSourceChangedListener) {
        this.mapChangeReceiver.addOnSourceChangedListener(onSourceChangedListener);
    }

    public void removeOnSourceChangedListener(OnSourceChangedListener onSourceChangedListener) {
        this.mapChangeReceiver.removeOnSourceChangedListener(onSourceChangedListener);
    }

    public void addOnStyleImageMissingListener(OnStyleImageMissingListener onStyleImageMissingListener) {
        this.mapChangeReceiver.addOnStyleImageMissingListener(onStyleImageMissingListener);
    }

    public void removeOnStyleImageMissingListener(OnStyleImageMissingListener onStyleImageMissingListener) {
        this.mapChangeReceiver.removeOnStyleImageMissingListener(onStyleImageMissingListener);
    }

    public void addOnCanRemoveUnusedStyleImageListener(OnCanRemoveUnusedStyleImageListener onCanRemoveUnusedStyleImageListener) {
        this.mapChangeReceiver.addOnCanRemoveUnusedStyleImageListener(onCanRemoveUnusedStyleImageListener);
    }

    public void removeOnCanRemoveUnusedStyleImageListener(OnCanRemoveUnusedStyleImageListener onCanRemoveUnusedStyleImageListener) {
        this.mapChangeReceiver.removeOnCanRemoveUnusedStyleImageListener(onCanRemoveUnusedStyleImageListener);
    }

    public void addOnPreCompileShaderListener(OnPreCompileShaderListener onPreCompileShaderListener) {
        this.mapChangeReceiver.addOnPreCompileShaderListener(onPreCompileShaderListener);
    }

    public void removeOnPreCompileShaderListener(OnPreCompileShaderListener onPreCompileShaderListener) {
        this.mapChangeReceiver.removeOnPreCompileShaderListener(onPreCompileShaderListener);
    }

    public void addOnPostCompileShaderListener(OnPostCompileShaderListener onPostCompileShaderListener) {
        this.mapChangeReceiver.addOnPostCompileShaderListener(onPostCompileShaderListener);
    }

    public void removeOnPostCompileShaderListener(OnPostCompileShaderListener onPostCompileShaderListener) {
        this.mapChangeReceiver.removeOnPostCompileShaderListener(onPostCompileShaderListener);
    }

    public void addOnShaderCompileFailedListener(OnShaderCompileFailedListener onShaderCompileFailedListener) {
        this.mapChangeReceiver.addOnShaderCompileFailedListener(onShaderCompileFailedListener);
    }

    public void removeOnShaderCompileFailedListener(OnShaderCompileFailedListener onShaderCompileFailedListener) {
        this.mapChangeReceiver.removeOnShaderCompileFailedListener(onShaderCompileFailedListener);
    }

    public void addOnGlyphsLoadedListener(OnGlyphsLoadedListener onGlyphsLoadedListener) {
        this.mapChangeReceiver.addOnGlyphsLoadedListener(onGlyphsLoadedListener);
    }

    public void removeOnGlyphsLoadedListener(OnGlyphsLoadedListener onGlyphsLoadedListener) {
        this.mapChangeReceiver.removeOnGlyphsLoadedListener(onGlyphsLoadedListener);
    }

    public void addOnGlyphsErrorListener(OnGlyphsErrorListener onGlyphsErrorListener) {
        this.mapChangeReceiver.addOnGlyphsErrorListener(onGlyphsErrorListener);
    }

    public void removeOnGlyphsErrorListener(OnGlyphsErrorListener onGlyphsErrorListener) {
        this.mapChangeReceiver.removeOnGlyphsErrorListener(onGlyphsErrorListener);
    }

    public void addOnGlyphsRequestedListener(OnGlyphsRequestedListener onGlyphsRequestedListener) {
        this.mapChangeReceiver.addOnGlyphsRequestedListener(onGlyphsRequestedListener);
    }

    public void removeOnGlyphsRequestedListener(OnGlyphsRequestedListener onGlyphsRequestedListener) {
        this.mapChangeReceiver.removeOnGlyphsRequestedListener(onGlyphsRequestedListener);
    }

    public void addOnTileActionListener(OnTileActionListener onTileActionListener) {
        this.mapChangeReceiver.addOnTileActionListener(onTileActionListener);
    }

    public void removeOnTileActionListener(OnTileActionListener onTileActionListener) {
        this.mapChangeReceiver.removeOnTileActionListener(onTileActionListener);
    }

    public void addOnSpriteLoadedListener(OnSpriteLoadedListener onSpriteLoadedListener) {
        this.mapChangeReceiver.addOnSpriteLoadedListener(onSpriteLoadedListener);
    }

    public void removeOnSpriteLoadedListener(OnSpriteLoadedListener onSpriteLoadedListener) {
        this.mapChangeReceiver.removeOnSpriteLoadedListener(onSpriteLoadedListener);
    }

    public void addOnSpriteErrorListener(OnSpriteErrorListener onSpriteErrorListener) {
        this.mapChangeReceiver.addOnSpriteErrorListener(onSpriteErrorListener);
    }

    public void removeOnSpriteErrorListener(OnSpriteErrorListener onSpriteErrorListener) {
        this.mapChangeReceiver.removeOnSpriteErrorListener(onSpriteErrorListener);
    }

    public void addOnSpriteRequestedListener(OnSpriteRequestedListener onSpriteRequestedListener) {
        this.mapChangeReceiver.addOnSpriteRequestedListener(onSpriteRequestedListener);
    }

    public void removeOnSpriteRequestedListener(OnSpriteRequestedListener onSpriteRequestedListener) {
        this.mapChangeReceiver.removeOnSpriteRequestedListener(onSpriteRequestedListener);
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        MapLibreMap mapLibreMap = this.maplibreMap;
        if (mapLibreMap == null) {
            this.mapCallback.addOnMapReadyCallback(onMapReadyCallback);
        } else {
            onMapReadyCallback.onMapReady(mapLibreMap);
        }
    }

    private boolean isGestureDetectorInitialized() {
        return this.mapGestureDetector != null;
    }

    private boolean isKeyDetectorInitialized() {
        return this.mapKeyListener != null;
    }

    MapLibreMap getMapLibreMap() {
        return this.maplibreMap;
    }

    void setMapLibreMap(MapLibreMap mapLibreMap) {
        this.maplibreMap = mapLibreMap;
    }

    private class FocalPointInvalidator implements FocalPointChangeListener {
        private final List<FocalPointChangeListener> focalPointChangeListeners;

        private FocalPointInvalidator() {
            this.focalPointChangeListeners = new ArrayList();
        }

        void addListener(FocalPointChangeListener focalPointChangeListener) {
            this.focalPointChangeListeners.add(focalPointChangeListener);
        }

        @Override // org.maplibre.android.maps.FocalPointChangeListener
        public void onFocalPointChanged(PointF pointF) {
            MapView.this.mapGestureDetector.setFocalPoint(pointF);
            Iterator<FocalPointChangeListener> it = this.focalPointChangeListeners.iterator();
            while (it.hasNext()) {
                it.next().onFocalPointChanged(pointF);
            }
        }
    }

    private class InitialRenderCallback implements OnDidFinishRenderingFrameListener {
        private int renderCount;

        InitialRenderCallback() {
            MapView.this.addOnDidFinishRenderingFrameListener(this);
        }

        @Override // org.maplibre.android.maps.MapView.OnDidFinishRenderingFrameListener
        public void onDidFinishRenderingFrame(boolean z, double d, double d2) {
            if (MapView.this.maplibreMap == null || MapView.this.maplibreMap.getStyle() == null || !MapView.this.maplibreMap.getStyle().isFullyLoaded()) {
                return;
            }
            int i = this.renderCount + 1;
            this.renderCount = i;
            if (i == 3) {
                MapView.this.setForeground(null);
                MapView.this.removeOnDidFinishRenderingFrameListener(this);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onDestroy() {
            MapView.this.removeOnDidFinishRenderingFrameListener(this);
        }
    }

    private class GesturesManagerInteractionListener implements MapLibreMap.OnGesturesManagerInteractionListener {
        private GesturesManagerInteractionListener() {
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onAddMapClickListener(MapLibreMap.OnMapClickListener onMapClickListener) {
            MapView.this.mapGestureDetector.addOnMapClickListener(onMapClickListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onRemoveMapClickListener(MapLibreMap.OnMapClickListener onMapClickListener) {
            MapView.this.mapGestureDetector.removeOnMapClickListener(onMapClickListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onAddMapLongClickListener(MapLibreMap.OnMapLongClickListener onMapLongClickListener) {
            MapView.this.mapGestureDetector.addOnMapLongClickListener(onMapLongClickListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onRemoveMapLongClickListener(MapLibreMap.OnMapLongClickListener onMapLongClickListener) {
            MapView.this.mapGestureDetector.removeOnMapLongClickListener(onMapLongClickListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onAddFlingListener(MapLibreMap.OnFlingListener onFlingListener) {
            MapView.this.mapGestureDetector.addOnFlingListener(onFlingListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onRemoveFlingListener(MapLibreMap.OnFlingListener onFlingListener) {
            MapView.this.mapGestureDetector.removeOnFlingListener(onFlingListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onAddMoveListener(MapLibreMap.OnMoveListener onMoveListener) {
            MapView.this.mapGestureDetector.addOnMoveListener(onMoveListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onRemoveMoveListener(MapLibreMap.OnMoveListener onMoveListener) {
            MapView.this.mapGestureDetector.removeOnMoveListener(onMoveListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onAddRotateListener(MapLibreMap.OnRotateListener onRotateListener) {
            MapView.this.mapGestureDetector.addOnRotateListener(onRotateListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onRemoveRotateListener(MapLibreMap.OnRotateListener onRotateListener) {
            MapView.this.mapGestureDetector.removeOnRotateListener(onRotateListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onAddScaleListener(MapLibreMap.OnScaleListener onScaleListener) {
            MapView.this.mapGestureDetector.addOnScaleListener(onScaleListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onRemoveScaleListener(MapLibreMap.OnScaleListener onScaleListener) {
            MapView.this.mapGestureDetector.removeOnScaleListener(onScaleListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onAddShoveListener(MapLibreMap.OnShoveListener onShoveListener) {
            MapView.this.mapGestureDetector.addShoveListener(onShoveListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void onRemoveShoveListener(MapLibreMap.OnShoveListener onShoveListener) {
            MapView.this.mapGestureDetector.removeShoveListener(onShoveListener);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public AndroidGesturesManager getGesturesManager() {
            return MapView.this.mapGestureDetector.getGesturesManager();
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void setGesturesManager(AndroidGesturesManager androidGesturesManager, boolean z, boolean z2) {
            MapView.this.mapGestureDetector.setGesturesManager(MapView.this.getContext(), androidGesturesManager, z, z2);
        }

        @Override // org.maplibre.android.maps.MapLibreMap.OnGesturesManagerInteractionListener
        public void cancelAllVelocityAnimations() {
            MapView.this.mapGestureDetector.cancelAnimators();
        }
    }

    private class MapCallback implements OnDidFinishLoadingStyleListener, OnDidFinishRenderingFrameListener, OnDidFinishLoadingMapListener, OnCameraIsChangingListener, OnCameraDidChangeListener, OnDidFailLoadingMapListener {
        private final List<OnMapReadyCallback> onMapReadyCallbackList = new ArrayList();

        MapCallback() {
            MapView.this.addOnDidFinishLoadingStyleListener(this);
            MapView.this.addOnDidFinishRenderingFrameListener(this);
            MapView.this.addOnDidFinishLoadingMapListener(this);
            MapView.this.addOnCameraIsChangingListener(this);
            MapView.this.addOnCameraDidChangeListener(this);
            MapView.this.addOnDidFailLoadingMapListener(this);
        }

        void initialised() {
            MapView.this.maplibreMap.onPreMapReady();
            onMapReady();
            MapView.this.maplibreMap.onPostMapReady();
        }

        private void onMapReady() {
            if (this.onMapReadyCallbackList.size() > 0) {
                Iterator<OnMapReadyCallback> it = this.onMapReadyCallbackList.iterator();
                while (it.hasNext()) {
                    OnMapReadyCallback next = it.next();
                    if (next != null) {
                        next.onMapReady(MapView.this.maplibreMap);
                    }
                    it.remove();
                }
            }
        }

        void addOnMapReadyCallback(OnMapReadyCallback onMapReadyCallback) {
            this.onMapReadyCallbackList.add(onMapReadyCallback);
        }

        void onDestroy() {
            this.onMapReadyCallbackList.clear();
            MapView.this.removeOnDidFinishLoadingStyleListener(this);
            MapView.this.removeOnDidFinishRenderingFrameListener(this);
            MapView.this.removeOnDidFinishLoadingMapListener(this);
            MapView.this.removeOnCameraIsChangingListener(this);
            MapView.this.removeOnCameraDidChangeListener(this);
            MapView.this.removeOnDidFailLoadingMapListener(this);
        }

        @Override // org.maplibre.android.maps.MapView.OnDidFinishLoadingStyleListener
        public void onDidFinishLoadingStyle() {
            if (MapView.this.maplibreMap != null) {
                MapView.this.maplibreMap.onFinishLoadingStyle();
            }
        }

        @Override // org.maplibre.android.maps.MapView.OnDidFailLoadingMapListener
        public void onDidFailLoadingMap(String str) {
            if (MapView.this.maplibreMap != null) {
                MapView.this.maplibreMap.onFailLoadingStyle();
            }
        }

        @Override // org.maplibre.android.maps.MapView.OnDidFinishRenderingFrameListener
        public void onDidFinishRenderingFrame(boolean z, double d, double d2) {
            if (MapView.this.maplibreMap != null) {
                MapView.this.maplibreMap.onUpdateFullyRendered();
            }
        }

        @Override // org.maplibre.android.maps.MapView.OnDidFinishLoadingMapListener
        public void onDidFinishLoadingMap() {
            if (MapView.this.maplibreMap != null) {
                MapView.this.maplibreMap.onUpdateRegionChange();
            }
        }

        @Override // org.maplibre.android.maps.MapView.OnCameraIsChangingListener
        public void onCameraIsChanging() {
            if (MapView.this.maplibreMap != null) {
                MapView.this.maplibreMap.onUpdateRegionChange();
            }
        }

        @Override // org.maplibre.android.maps.MapView.OnCameraDidChangeListener
        public void onCameraDidChange(boolean z) {
            if (MapView.this.maplibreMap != null) {
                MapView.this.maplibreMap.onUpdateRegionChange();
            }
        }
    }

    private static class AttributionClickListener implements View.OnClickListener {
        private final AttributionDialogManager defaultDialogManager;
        private UiSettings uiSettings;

        private AttributionClickListener(Context context, MapLibreMap mapLibreMap) {
            this.defaultDialogManager = new AttributionDialogManager(context, mapLibreMap);
            this.uiSettings = mapLibreMap.getUiSettings();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            getDialogManager().onClick(view);
        }

        public void onStop() {
            getDialogManager().onStop();
        }

        private AttributionDialogManager getDialogManager() {
            if (this.uiSettings.getAttributionDialogManager() != null) {
                return this.uiSettings.getAttributionDialogManager();
            }
            return this.defaultDialogManager;
        }
    }

    public static void setMapStrictModeEnabled(boolean z) {
        MapStrictMode.setStrictModeEnabled(z);
    }
}

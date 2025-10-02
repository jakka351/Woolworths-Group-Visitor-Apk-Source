package org.maplibre.android.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.maplibre.android.LibraryLoader;
import org.maplibre.android.MapStrictMode;
import org.maplibre.android.annotations.Marker;
import org.maplibre.android.annotations.Polygon;
import org.maplibre.android.annotations.Polyline;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.exceptions.CalledFromWorkerThreadException;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.android.geometry.ProjectedMeters;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.renderer.MapRenderer;
import org.maplibre.android.storage.FileSource;
import org.maplibre.android.style.expressions.Expression;
import org.maplibre.android.style.layers.CannotAddLayerException;
import org.maplibre.android.style.layers.Layer;
import org.maplibre.android.style.layers.TransitionOptions;
import org.maplibre.android.style.light.Light;
import org.maplibre.android.style.sources.CannotAddSourceException;
import org.maplibre.android.style.sources.Source;
import org.maplibre.android.tile.TileOperation;
import org.maplibre.android.utils.BitmapUtils;
import org.maplibre.geojson.Feature;
import org.maplibre.geojson.Geometry;

/* loaded from: classes2.dex */
final class NativeMapView implements NativeMap {
    private static final String TAG = "Mbgl-NativeMapView";
    private boolean destroyed;
    private double[] edgeInsets;
    private final FileSource fileSource;
    private final MapRenderer mapRenderer;
    private long nativePtr;
    private final float pixelRatio;
    private MapLibreMap.SnapshotReadyCallback snapshotReadyCallback;
    private StateCallback stateCallback;
    private final Thread thread;
    private ViewCallback viewCallback;

    interface StateCallback extends StyleCallback {
        void onCameraDidChange(boolean z);

        void onCameraIsChanging();

        void onCameraWillChange(boolean z);

        boolean onCanRemoveUnusedStyleImage(String str);

        void onDidBecomeIdle();

        void onDidFailLoadingMap(String str);

        void onDidFinishLoadingMap();

        void onDidFinishRenderingFrame(boolean z, double d, double d2);

        void onDidFinishRenderingMap(boolean z);

        void onGlyphsError(String[] strArr, int i, int i2);

        void onGlyphsLoaded(String[] strArr, int i, int i2);

        void onGlyphsRequested(String[] strArr, int i, int i2);

        void onPostCompileShader(int i, int i2, String str);

        void onPreCompileShader(int i, int i2, String str);

        void onShaderCompileFailed(int i, int i2, String str);

        void onSourceChanged(String str);

        void onSpriteError(String str, String str2);

        void onSpriteLoaded(String str, String str2);

        void onSpriteRequested(String str, String str2);

        void onStyleImageMissing(String str);

        void onTileAction(TileOperation tileOperation, int i, int i2, int i3, int i4, int i5, String str);

        void onWillStartRenderingFrame();

        void onWillStartRenderingMap();
    }

    interface StyleCallback {
        void onDidFinishLoadingStyle();

        void onWillStartLoadingMap();
    }

    public interface ViewCallback {
        Bitmap getViewContent();
    }

    private native void nativeAddAnnotationIcon(String str, int i, int i2, float f, byte[] bArr);

    private native void nativeAddImage(String str, Bitmap bitmap, float f, boolean z);

    private native void nativeAddImages(Image[] imageArr);

    private native void nativeAddLayer(long j, String str) throws CannotAddLayerException;

    private native void nativeAddLayerAbove(long j, String str) throws CannotAddLayerException;

    private native void nativeAddLayerAt(long j, int i) throws CannotAddLayerException;

    private native long[] nativeAddMarkers(Marker[] markerArr);

    private native long[] nativeAddPolygons(Polygon[] polygonArr);

    private native long[] nativeAddPolylines(Polyline[] polylineArr);

    private native void nativeAddSource(Source source, long j) throws CannotAddSourceException;

    private native void nativeCancelTransitions();

    private native void nativeDestroy();

    private native void nativeEaseTo(double d, double d2, double d3, long j, double d4, double d5, double[] dArr, boolean z);

    private native void nativeFlyTo(double d, double d2, double d3, long j, double d4, double d5, double[] dArr);

    private native double nativeGetBearing();

    private native CameraPosition nativeGetCameraForGeometry(Geometry geometry, double d, double d2, double d3, double d4, double d5, double d6);

    private native CameraPosition nativeGetCameraForLatLngBounds(LatLngBounds latLngBounds, double d, double d2, double d3, double d4, double d5, double d6);

    private native CameraPosition nativeGetCameraPosition();

    private native boolean nativeGetDebug();

    private native Bitmap nativeGetImage(String str);

    private native LatLng nativeGetLatLng();

    private native Layer nativeGetLayer(String str);

    private native Layer[] nativeGetLayers();

    private native Light nativeGetLight();

    private native double nativeGetMaxPitch();

    private native double nativeGetMaxZoom();

    private native double nativeGetMetersPerPixelAtLatitude(double d, double d2);

    private native double nativeGetMinPitch();

    private native double nativeGetMinZoom();

    private native double nativeGetPitch();

    private native boolean nativeGetPrefetchTiles();

    private native int nativeGetPrefetchZoomDelta();

    private native Source nativeGetSource(String str);

    private native Source[] nativeGetSources();

    private native String nativeGetStyleJson();

    private native String nativeGetStyleUrl();

    private native boolean nativeGetTileCacheEnabled();

    private native double nativeGetTopOffsetPixelsForAnnotationSymbol(String str);

    private native long nativeGetTransitionDelay();

    private native long nativeGetTransitionDuration();

    private native TransitionOptions nativeGetTransitionOptions();

    private native void nativeGetVisibleCoordinateBounds(double[] dArr);

    private native double nativeGetZoom();

    private native void nativeInitialize(NativeMapView nativeMapView, FileSource fileSource, MapRenderer mapRenderer, float f, boolean z);

    private native boolean nativeIsFullyLoaded();

    private native void nativeJumpTo(double d, double d2, double d3, double d4, double d5, double[] dArr);

    private native LatLng nativeLatLngForPixel(float f, float f2);

    private native LatLng nativeLatLngForProjectedMeters(double d, double d2);

    private native void nativeLatLngsForPixels(double[] dArr, double[] dArr2, float f);

    private native void nativeMoveBy(double d, double d2, long j);

    private native void nativeOnLowMemory();

    private native PointF nativePixelForLatLng(double d, double d2);

    private native void nativePixelsForLatLngs(double[] dArr, double[] dArr2, float f);

    private native ProjectedMeters nativeProjectedMetersForLatLng(double d, double d2);

    private native long[] nativeQueryPointAnnotations(RectF rectF);

    private native Feature[] nativeQueryRenderedFeaturesForBox(float f, float f2, float f3, float f4, String[] strArr, Object[] objArr);

    private native Feature[] nativeQueryRenderedFeaturesForPoint(float f, float f2, String[] strArr, Object[] objArr);

    private native long[] nativeQueryShapeAnnotations(RectF rectF);

    private native void nativeRemoveAnnotationIcon(String str);

    private native void nativeRemoveAnnotations(long[] jArr);

    private native void nativeRemoveImage(String str);

    private native boolean nativeRemoveLayer(long j);

    private native boolean nativeRemoveLayerAt(int i);

    private native boolean nativeRemoveSource(Source source, long j);

    private native void nativeResetNorth();

    private native void nativeResetPosition();

    private native void nativeResetZoom();

    private native void nativeResizeView(int i, int i2);

    private native void nativeRotateBy(double d, double d2, double d3, double d4, long j);

    private native void nativeSetBearing(double d, long j);

    private native void nativeSetBearingXY(double d, double d2, double d3, long j);

    private native void nativeSetDebug(boolean z);

    private native void nativeSetGestureInProgress(boolean z);

    private native void nativeSetLatLng(double d, double d2, double[] dArr, long j);

    private native void nativeSetLatLngBounds(LatLngBounds latLngBounds);

    private native void nativeSetMaxPitch(double d);

    private native void nativeSetMaxZoom(double d);

    private native void nativeSetMinPitch(double d);

    private native void nativeSetMinZoom(double d);

    private native void nativeSetPitch(double d, long j);

    private native void nativeSetPrefetchTiles(boolean z);

    private native void nativeSetPrefetchZoomDelta(int i);

    private native void nativeSetReachability(boolean z);

    private native void nativeSetStyleJson(String str);

    private native void nativeSetStyleUrl(String str);

    private native void nativeSetTileCacheEnabled(boolean z);

    private native void nativeSetTransitionDelay(long j);

    private native void nativeSetTransitionDuration(long j);

    private native void nativeSetTransitionOptions(TransitionOptions transitionOptions);

    private native void nativeSetVisibleCoordinateBounds(LatLng[] latLngArr, RectF rectF, double d, long j);

    private native void nativeSetZoom(double d, double d2, double d3, long j);

    private native void nativeTakeSnapshot();

    private native void nativeTriggerRepaint();

    private native void nativeUpdateMarker(long j, double d, double d2, String str);

    private native void nativeUpdatePolygon(long j, Polygon polygon);

    private native void nativeUpdatePolyline(long j, Polyline polyline);

    static {
        LibraryLoader.load();
    }

    public NativeMapView(Context context, boolean z, ViewCallback viewCallback, StateCallback stateCallback, MapRenderer mapRenderer) {
        this(context, context.getResources().getDisplayMetrics().density, z, viewCallback, stateCallback, mapRenderer);
    }

    public NativeMapView(Context context, float f, boolean z, ViewCallback viewCallback, StateCallback stateCallback, MapRenderer mapRenderer) {
        this.destroyed = false;
        this.nativePtr = 0L;
        this.mapRenderer = mapRenderer;
        this.viewCallback = viewCallback;
        FileSource fileSource = FileSource.getInstance(context);
        this.fileSource = fileSource;
        this.pixelRatio = f;
        this.thread = Thread.currentThread();
        this.stateCallback = stateCallback;
        nativeInitialize(this, fileSource, mapRenderer, f, z);
    }

    private boolean checkState(String str) {
        if (this.thread != Thread.currentThread()) {
            throw new CalledFromWorkerThreadException(String.format("Map interactions should happen on the UI thread. Method invoked from wrong thread is %s.", str));
        }
        if (this.destroyed && !TextUtils.isEmpty(str)) {
            String str2 = String.format("You're calling `%s` after the `MapView` was destroyed, were you invoking it after `onDestroy()`?", str);
            Logger.e(TAG, str2);
            MapStrictMode.strictModeViolation(str2);
        }
        return this.destroyed;
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void destroy() {
        this.destroyed = true;
        this.viewCallback = null;
        nativeDestroy();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void resizeView(int i, int i2) {
        if (checkState("resizeView")) {
            return;
        }
        int iCeil = (int) Math.ceil(i / this.pixelRatio);
        int iCeil2 = (int) Math.ceil(i2 / this.pixelRatio);
        if (iCeil < 0) {
            Logger.e(TAG, String.format("Device returned a negative width size, setting value to 0 instead of %s", Integer.valueOf(iCeil)));
            iCeil = 0;
        }
        if (iCeil2 < 0) {
            Logger.e(TAG, String.format("Device returned a negative height size, setting value to 0 instead of %s", Integer.valueOf(iCeil2)));
            iCeil2 = 0;
        }
        if (iCeil > 65535) {
            Logger.e(TAG, String.format("Device returned an out of range width size, capping value at 65535 instead of %s", Integer.valueOf(iCeil)));
            iCeil = 65535;
        }
        if (iCeil2 > 65535) {
            Logger.e(TAG, String.format("Device returned an out of range height size, capping value at 65535 instead of %s", Integer.valueOf(iCeil2)));
            iCeil2 = 65535;
        }
        nativeResizeView(iCeil, iCeil2);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setStyleUri(String str) {
        if (checkState("setStyleUri")) {
            return;
        }
        nativeSetStyleUrl(str);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public String getStyleUri() {
        return checkState("getStyleUri") ? "" : nativeGetStyleUrl();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setStyleJson(String str) {
        if (checkState("setStyleJson")) {
            return;
        }
        nativeSetStyleJson(str);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public String getStyleJson() {
        return checkState("getStyleJson") ? "" : nativeGetStyleJson();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setLatLngBounds(LatLngBounds latLngBounds) {
        if (checkState("setLatLngBounds")) {
            return;
        }
        nativeSetLatLngBounds(latLngBounds);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void cancelTransitions() {
        if (checkState("cancelTransitions")) {
            return;
        }
        nativeCancelTransitions();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setGestureInProgress(boolean z) {
        if (checkState("setGestureInProgress")) {
            return;
        }
        nativeSetGestureInProgress(z);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void moveBy(double d, double d2, long j) {
        if (checkState("moveBy")) {
            return;
        }
        try {
            float f = this.pixelRatio;
            nativeMoveBy(d / f, d2 / f, j);
        } catch (Error e) {
            Logger.d(TAG, "Error when executing NativeMapView#moveBy", e);
        }
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setLatLng(LatLng latLng, long j) {
        if (checkState("setLatLng")) {
            return;
        }
        nativeSetLatLng(latLng.getLatitude(), latLng.getLongitude(), getAnimationPaddingAndClearCachedInsets(null), j);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public LatLng getLatLng() {
        if (checkState("")) {
            return new LatLng();
        }
        return nativeGetLatLng();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public CameraPosition getCameraForLatLngBounds(LatLngBounds latLngBounds, int[] iArr, double d, double d2) {
        if (checkState("getCameraForLatLngBounds")) {
            return null;
        }
        float f = iArr[1];
        float f2 = this.pixelRatio;
        return nativeGetCameraForLatLngBounds(latLngBounds, f / f2, iArr[0] / f2, iArr[3] / f2, iArr[2] / f2, d, d2);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public CameraPosition getCameraForGeometry(Geometry geometry, int[] iArr, double d, double d2) {
        if (checkState("getCameraForGeometry")) {
            return null;
        }
        float f = iArr[1];
        float f2 = this.pixelRatio;
        return nativeGetCameraForGeometry(geometry, f / f2, iArr[0] / f2, iArr[3] / f2, iArr[2] / f2, d, d2);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void resetPosition() {
        if (checkState("resetPosition")) {
            return;
        }
        nativeResetPosition();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public double getPitch() {
        if (checkState("getPitch")) {
            return 0.0d;
        }
        return nativeGetPitch();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setPitch(double d, long j) {
        if (checkState("setPitch")) {
            return;
        }
        nativeSetPitch(d, j);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setZoom(double d, PointF pointF, long j) {
        if (checkState("setZoom")) {
            return;
        }
        nativeSetZoom(d, pointF.x / this.pixelRatio, pointF.y / this.pixelRatio, j);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public double getZoom() {
        if (checkState("getZoom")) {
            return 0.0d;
        }
        return nativeGetZoom();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void resetZoom() {
        if (checkState("resetZoom")) {
            return;
        }
        nativeResetZoom();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setMinZoom(double d) {
        if (checkState("setMinZoom")) {
            return;
        }
        nativeSetMinZoom(d);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public double getMinZoom() {
        if (checkState("getMinZoom")) {
            return 0.0d;
        }
        return nativeGetMinZoom();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setMaxZoom(double d) {
        if (checkState("setMaxZoom")) {
            return;
        }
        nativeSetMaxZoom(d);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public double getMaxZoom() {
        if (checkState("getMaxZoom")) {
            return 0.0d;
        }
        return nativeGetMaxZoom();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setMinPitch(double d) {
        if (checkState("setMinPitch")) {
            return;
        }
        nativeSetMinPitch(d);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public double getMinPitch() {
        if (checkState("getMinPitch")) {
            return 0.0d;
        }
        return nativeGetMinPitch();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setMaxPitch(double d) {
        if (checkState("setMaxPitch")) {
            return;
        }
        nativeSetMaxPitch(d);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public double getMaxPitch() {
        if (checkState("getMaxPitch")) {
            return 0.0d;
        }
        return nativeGetMaxPitch();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void rotateBy(double d, double d2, double d3, double d4, long j) {
        if (checkState("rotateBy")) {
            return;
        }
        float f = this.pixelRatio;
        nativeRotateBy(d / f, d2 / f, d3, d4, j);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setContentPadding(double[] dArr) {
        if (checkState("setContentPadding")) {
            return;
        }
        this.edgeInsets = dArr;
    }

    @Override // org.maplibre.android.maps.NativeMap
    public double[] getContentPadding() {
        if (checkState("getContentPadding")) {
            return new double[]{0.0d, 0.0d, 0.0d, 0.0d};
        }
        double[] dArr = this.edgeInsets;
        return dArr != null ? dArr : getCameraPosition().padding;
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setBearing(double d, long j) {
        if (checkState("setBearing")) {
            return;
        }
        nativeSetBearing(d, j);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setBearing(double d, double d2, double d3, long j) {
        if (checkState("setBearing")) {
            return;
        }
        float f = this.pixelRatio;
        nativeSetBearingXY(d, d2 / f, d3 / f, j);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public double getBearing() {
        if (checkState("getBearing")) {
            return 0.0d;
        }
        return nativeGetBearing();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void resetNorth() {
        if (checkState("resetNorth")) {
            return;
        }
        nativeResetNorth();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public long addMarker(Marker marker) {
        if (checkState("addMarker")) {
            return 0L;
        }
        return nativeAddMarkers(new Marker[]{marker})[0];
    }

    @Override // org.maplibre.android.maps.NativeMap
    public long[] addMarkers(List<Marker> list) {
        return checkState("addMarkers") ? new long[0] : nativeAddMarkers((Marker[]) list.toArray(new Marker[list.size()]));
    }

    @Override // org.maplibre.android.maps.NativeMap
    public long addPolyline(Polyline polyline) {
        if (checkState("addPolyline")) {
            return 0L;
        }
        return nativeAddPolylines(new Polyline[]{polyline})[0];
    }

    @Override // org.maplibre.android.maps.NativeMap
    public long[] addPolylines(List<Polyline> list) {
        return checkState("addPolylines") ? new long[0] : nativeAddPolylines((Polyline[]) list.toArray(new Polyline[list.size()]));
    }

    @Override // org.maplibre.android.maps.NativeMap
    public long addPolygon(Polygon polygon) {
        if (checkState("addPolygon")) {
            return 0L;
        }
        return nativeAddPolygons(new Polygon[]{polygon})[0];
    }

    @Override // org.maplibre.android.maps.NativeMap
    public long[] addPolygons(List<Polygon> list) {
        return checkState("addPolygons") ? new long[0] : nativeAddPolygons((Polygon[]) list.toArray(new Polygon[list.size()]));
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void updateMarker(Marker marker) {
        if (checkState("updateMarker")) {
            return;
        }
        LatLng position = marker.getPosition();
        nativeUpdateMarker(marker.getId(), position.getLatitude(), position.getLongitude(), marker.getIcon().getId());
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void updatePolygon(Polygon polygon) {
        if (checkState("updatePolygon")) {
            return;
        }
        nativeUpdatePolygon(polygon.getId(), polygon);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void updatePolyline(Polyline polyline) {
        if (checkState("updatePolyline")) {
            return;
        }
        nativeUpdatePolyline(polyline.getId(), polyline);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void removeAnnotation(long j) {
        if (checkState("removeAnnotation")) {
            return;
        }
        removeAnnotations(new long[]{j});
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void removeAnnotations(long[] jArr) {
        if (checkState("removeAnnotations")) {
            return;
        }
        nativeRemoveAnnotations(jArr);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public long[] queryPointAnnotations(RectF rectF) {
        return checkState("queryPointAnnotations") ? new long[0] : nativeQueryPointAnnotations(rectF);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public long[] queryShapeAnnotations(RectF rectF) {
        return checkState("queryShapeAnnotations") ? new long[0] : nativeQueryShapeAnnotations(rectF);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void addAnnotationIcon(String str, int i, int i2, float f, byte[] bArr) {
        if (checkState("addAnnotationIcon")) {
            return;
        }
        nativeAddAnnotationIcon(str, i, i2, f, bArr);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void removeAnnotationIcon(String str) {
        if (checkState("removeAnnotationIcon")) {
            return;
        }
        nativeRemoveAnnotationIcon(str);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setVisibleCoordinateBounds(LatLng[] latLngArr, RectF rectF, double d, long j) {
        if (checkState("setVisibleCoordinateBounds")) {
            return;
        }
        nativeSetVisibleCoordinateBounds(latLngArr, rectF, d, j);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void onLowMemory() {
        if (checkState("onLowMemory")) {
            return;
        }
        nativeOnLowMemory();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setDebug(boolean z) {
        if (checkState("setDebug")) {
            return;
        }
        nativeSetDebug(z);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public boolean getDebug() {
        if (checkState("getDebug")) {
            return false;
        }
        return nativeGetDebug();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public boolean isFullyLoaded() {
        if (checkState("isFullyLoaded")) {
            return false;
        }
        return nativeIsFullyLoaded();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setReachability(boolean z) {
        if (checkState("setReachability")) {
            return;
        }
        nativeSetReachability(z);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public double getMetersPerPixelAtLatitude(double d) {
        if (checkState("getMetersPerPixelAtLatitude")) {
            return 0.0d;
        }
        return nativeGetMetersPerPixelAtLatitude(d, getZoom());
    }

    @Override // org.maplibre.android.maps.NativeMap
    public ProjectedMeters projectedMetersForLatLng(LatLng latLng) {
        if (checkState("projectedMetersForLatLng")) {
            return null;
        }
        return nativeProjectedMetersForLatLng(latLng.getLatitude(), latLng.getLongitude());
    }

    @Override // org.maplibre.android.maps.NativeMap
    public LatLng latLngForProjectedMeters(ProjectedMeters projectedMeters) {
        if (checkState("latLngForProjectedMeters")) {
            return new LatLng();
        }
        return nativeLatLngForProjectedMeters(projectedMeters.getNorthing(), projectedMeters.getEasting());
    }

    @Override // org.maplibre.android.maps.NativeMap
    public PointF pixelForLatLng(LatLng latLng) {
        if (checkState("pixelForLatLng")) {
            return new PointF();
        }
        PointF pointFNativePixelForLatLng = nativePixelForLatLng(latLng.getLatitude(), latLng.getLongitude());
        pointFNativePixelForLatLng.set(pointFNativePixelForLatLng.x * this.pixelRatio, pointFNativePixelForLatLng.y * this.pixelRatio);
        return pointFNativePixelForLatLng;
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void pixelsForLatLngs(double[] dArr, double[] dArr2) {
        if (checkState("pixelsForLatLngs")) {
            return;
        }
        nativePixelsForLatLngs(dArr, dArr2, this.pixelRatio);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void getVisibleCoordinateBounds(double[] dArr) {
        if (checkState("getVisibleCoordinateBounds")) {
            return;
        }
        nativeGetVisibleCoordinateBounds(dArr);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public LatLng latLngForPixel(PointF pointF) {
        if (checkState("latLngForPixel")) {
            return new LatLng();
        }
        return nativeLatLngForPixel(pointF.x / this.pixelRatio, pointF.y / this.pixelRatio);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void latLngsForPixels(double[] dArr, double[] dArr2) {
        if (checkState("latLngsForPixels")) {
            return;
        }
        nativeLatLngsForPixels(dArr, dArr2, this.pixelRatio);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public double getTopOffsetPixelsForAnnotationSymbol(String str) {
        if (checkState("getTopOffsetPixelsForAnnotationSymbol")) {
            return 0.0d;
        }
        return nativeGetTopOffsetPixelsForAnnotationSymbol(str);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void jumpTo(LatLng latLng, double d, double d2, double d3, double[] dArr) {
        if (checkState("jumpTo")) {
            return;
        }
        nativeJumpTo(d3, latLng.getLatitude(), latLng.getLongitude(), d2, d, getAnimationPaddingAndClearCachedInsets(dArr));
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void easeTo(LatLng latLng, double d, double d2, double d3, double[] dArr, long j, boolean z) {
        if (checkState("easeTo")) {
            return;
        }
        nativeEaseTo(d2, latLng.getLatitude(), latLng.getLongitude(), j, d3, d, getAnimationPaddingAndClearCachedInsets(dArr), z);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void flyTo(LatLng latLng, double d, double d2, double d3, double[] dArr, long j) {
        if (checkState("flyTo")) {
            return;
        }
        nativeFlyTo(d2, latLng.getLatitude(), latLng.getLongitude(), j, d3, d, getAnimationPaddingAndClearCachedInsets(dArr));
    }

    @Override // org.maplibre.android.maps.NativeMap
    public CameraPosition getCameraPosition() {
        if (checkState("getCameraValues")) {
            return new CameraPosition.Builder().build();
        }
        if (this.edgeInsets != null) {
            return new CameraPosition.Builder(nativeGetCameraPosition()).padding(this.edgeInsets).build();
        }
        return nativeGetCameraPosition();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setPrefetchTiles(boolean z) {
        if (checkState("setPrefetchTiles")) {
            return;
        }
        nativeSetPrefetchTiles(z);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public boolean getPrefetchTiles() {
        if (checkState("getPrefetchTiles")) {
            return false;
        }
        return nativeGetPrefetchTiles();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setPrefetchZoomDelta(int i) {
        if (checkState("nativeSetPrefetchZoomDelta")) {
            return;
        }
        nativeSetPrefetchZoomDelta(i);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public int getPrefetchZoomDelta() {
        if (checkState("nativeGetPrefetchZoomDelta")) {
            return 0;
        }
        return nativeGetPrefetchZoomDelta();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setTileCacheEnabled(boolean z) {
        if (checkState("setTileCacheEnabled")) {
            return;
        }
        nativeSetTileCacheEnabled(z);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public boolean getTileCacheEnabled() {
        if (checkState("getTileCacheEnabled")) {
            return false;
        }
        return nativeGetTileCacheEnabled();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setTransitionOptions(TransitionOptions transitionOptions) {
        nativeSetTransitionOptions(transitionOptions);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public TransitionOptions getTransitionOptions() {
        return nativeGetTransitionOptions();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public List<Layer> getLayers() {
        if (checkState("getLayers")) {
            return new ArrayList();
        }
        return Arrays.asList(nativeGetLayers());
    }

    @Override // org.maplibre.android.maps.NativeMap
    public Layer getLayer(String str) {
        if (checkState("getLayer")) {
            return null;
        }
        return nativeGetLayer(str);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void addLayer(Layer layer) throws CannotAddLayerException {
        if (checkState("addLayer")) {
            return;
        }
        nativeAddLayer(layer.getNativePtr(), null);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void addLayerBelow(Layer layer, String str) throws CannotAddLayerException {
        if (checkState("addLayerBelow")) {
            return;
        }
        nativeAddLayer(layer.getNativePtr(), str);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void addLayerAbove(Layer layer, String str) throws CannotAddLayerException {
        if (checkState("addLayerAbove")) {
            return;
        }
        nativeAddLayerAbove(layer.getNativePtr(), str);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void addLayerAt(Layer layer, int i) throws CannotAddLayerException {
        if (checkState("addLayerAt")) {
            return;
        }
        nativeAddLayerAt(layer.getNativePtr(), i);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public boolean removeLayer(String str) {
        Layer layer;
        if (checkState("removeLayer") || (layer = getLayer(str)) == null) {
            return false;
        }
        return removeLayer(layer);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public boolean removeLayer(Layer layer) {
        if (checkState("removeLayer")) {
            return false;
        }
        return nativeRemoveLayer(layer.getNativePtr());
    }

    @Override // org.maplibre.android.maps.NativeMap
    public boolean removeLayerAt(int i) {
        if (checkState("removeLayerAt")) {
            return false;
        }
        return nativeRemoveLayerAt(i);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public List<Source> getSources() {
        if (checkState("getSources")) {
            return new ArrayList();
        }
        return Arrays.asList(nativeGetSources());
    }

    @Override // org.maplibre.android.maps.NativeMap
    public Source getSource(String str) {
        if (checkState("getSource")) {
            return null;
        }
        return nativeGetSource(str);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void addSource(Source source) throws CannotAddSourceException {
        if (checkState("addSource")) {
            return;
        }
        nativeAddSource(source, source.getNativePtr());
    }

    @Override // org.maplibre.android.maps.NativeMap
    public boolean removeSource(String str) {
        Source source;
        if (checkState("removeSource") || (source = getSource(str)) == null) {
            return false;
        }
        return removeSource(source);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public boolean removeSource(Source source) {
        if (checkState("removeSource")) {
            return false;
        }
        return nativeRemoveSource(source, source.getNativePtr());
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void addImages(Image[] imageArr) {
        if (checkState("addImages")) {
            return;
        }
        nativeAddImages(imageArr);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void removeImage(String str) {
        if (checkState("removeImage")) {
            return;
        }
        nativeRemoveImage(str);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public Bitmap getImage(String str) {
        if (checkState("getImage")) {
            return null;
        }
        return nativeGetImage(str);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public List<Feature> queryRenderedFeatures(PointF pointF, String[] strArr, Expression expression) {
        if (checkState("queryRenderedFeatures")) {
            return new ArrayList();
        }
        Feature[] featureArrNativeQueryRenderedFeaturesForPoint = nativeQueryRenderedFeaturesForPoint(pointF.x / this.pixelRatio, pointF.y / this.pixelRatio, strArr, expression != null ? expression.toArray() : null);
        return featureArrNativeQueryRenderedFeaturesForPoint != null ? Arrays.asList(featureArrNativeQueryRenderedFeaturesForPoint) : new ArrayList();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public List<Feature> queryRenderedFeatures(RectF rectF, String[] strArr, Expression expression) {
        if (checkState("queryRenderedFeatures")) {
            return new ArrayList();
        }
        Feature[] featureArrNativeQueryRenderedFeaturesForBox = nativeQueryRenderedFeaturesForBox(rectF.left / this.pixelRatio, rectF.top / this.pixelRatio, rectF.right / this.pixelRatio, rectF.bottom / this.pixelRatio, strArr, expression != null ? expression.toArray() : null);
        return featureArrNativeQueryRenderedFeaturesForBox != null ? Arrays.asList(featureArrNativeQueryRenderedFeaturesForBox) : new ArrayList();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setApiBaseUrl(String str) {
        if (checkState("setApiBaseUrl")) {
            return;
        }
        this.fileSource.setApiBaseUrl(str);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public Light getLight() {
        if (checkState("getLight")) {
            return null;
        }
        return nativeGetLight();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public float getPixelRatio() {
        return this.pixelRatio;
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void triggerRepaint() {
        nativeTriggerRepaint();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setSwapBehaviorFlush(boolean z) {
        this.mapRenderer.setSwapBehaviorFlush(z);
    }

    @Override // org.maplibre.android.maps.NativeMap
    public RectF getDensityDependantRectangle(RectF rectF) {
        return new RectF(rectF.left / this.pixelRatio, rectF.top / this.pixelRatio, rectF.right / this.pixelRatio, rectF.bottom / this.pixelRatio);
    }

    private void onCameraWillChange(boolean z) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onCameraWillChange(z);
        }
    }

    private void onCameraIsChanging() {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onCameraIsChanging();
        }
    }

    private void onCameraDidChange(boolean z) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onCameraDidChange(z);
        }
    }

    private void onWillStartLoadingMap() {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onWillStartLoadingMap();
        }
    }

    private void onDidFinishLoadingMap() {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onDidFinishLoadingMap();
        }
    }

    private void onDidFailLoadingMap(String str) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onDidFailLoadingMap(str);
        }
    }

    private void onWillStartRenderingFrame() {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onWillStartRenderingFrame();
        }
    }

    private void onDidFinishRenderingFrame(boolean z, double d, double d2) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onDidFinishRenderingFrame(z, d, d2);
        }
    }

    private void onWillStartRenderingMap() {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onWillStartRenderingMap();
        }
    }

    private void onDidFinishRenderingMap(boolean z) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onDidFinishRenderingMap(z);
        }
    }

    private void onDidBecomeIdle() {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onDidBecomeIdle();
        }
    }

    private void onDidFinishLoadingStyle() {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onDidFinishLoadingStyle();
        }
    }

    private void onSourceChanged(String str) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onSourceChanged(str);
        }
    }

    private void onStyleImageMissing(String str) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onStyleImageMissing(str);
        }
    }

    private boolean onCanRemoveUnusedStyleImage(String str) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            return stateCallback.onCanRemoveUnusedStyleImage(str);
        }
        return true;
    }

    protected void onSnapshotReady(Bitmap bitmap) {
        if (checkState("OnSnapshotReady")) {
            return;
        }
        try {
            MapLibreMap.SnapshotReadyCallback snapshotReadyCallback = this.snapshotReadyCallback;
            if (snapshotReadyCallback == null || bitmap == null) {
                return;
            }
            ViewCallback viewCallback = this.viewCallback;
            if (viewCallback == null) {
                snapshotReadyCallback.onSnapshotReady(bitmap);
                return;
            }
            Bitmap viewContent = viewCallback.getViewContent();
            if (viewContent != null) {
                this.snapshotReadyCallback.onSnapshotReady(BitmapUtils.mergeBitmaps(bitmap, viewContent));
            }
        } catch (Throwable th) {
            Logger.e(TAG, "Exception in onSnapshotReady", th);
            throw th;
        }
    }

    private void onPreCompileShader(int i, int i2, String str) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onPreCompileShader(i, i2, str);
        }
    }

    private void onPostCompileShader(int i, int i2, String str) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onPostCompileShader(i, i2, str);
        }
    }

    private void onShaderCompileFailed(int i, int i2, String str) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onShaderCompileFailed(i, i2, str);
        }
    }

    private void onGlyphsLoaded(String[] strArr, int i, int i2) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onGlyphsLoaded(strArr, i, i2);
        }
    }

    private void onGlyphsError(String[] strArr, int i, int i2) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onGlyphsError(strArr, i, i2);
        }
    }

    private void onGlyphsRequested(String[] strArr, int i, int i2) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onGlyphsRequested(strArr, i, i2);
        }
    }

    private void onTileAction(TileOperation tileOperation, int i, int i2, int i3, int i4, int i5, String str) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onTileAction(tileOperation, i, i2, i3, i4, i5, str);
        }
    }

    private void onSpriteLoaded(String str, String str2) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onSpriteLoaded(str, str2);
        }
    }

    private void onSpriteError(String str, String str2) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onSpriteError(str, str2);
        }
    }

    private void onSpriteRequested(String str, String str2) {
        StateCallback stateCallback = this.stateCallback;
        if (stateCallback != null) {
            stateCallback.onSpriteRequested(str, str2);
        }
    }

    @Override // org.maplibre.android.maps.NativeMap
    public long getNativePtr() {
        return this.nativePtr;
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void addSnapshotCallback(MapLibreMap.SnapshotReadyCallback snapshotReadyCallback) {
        if (checkState("addSnapshotCallback")) {
            return;
        }
        this.snapshotReadyCallback = snapshotReadyCallback;
        nativeTakeSnapshot();
    }

    @Override // org.maplibre.android.maps.NativeMap
    public void setOnFpsChangedListener(final MapLibreMap.OnFpsChangedListener onFpsChangedListener) {
        final Handler handler = new Handler(Looper.getMainLooper());
        this.mapRenderer.queueEvent(new Runnable() { // from class: org.maplibre.android.maps.NativeMapView.1

            /* renamed from: org.maplibre.android.maps.NativeMapView$1$1, reason: invalid class name and collision with other inner class name */
            class C00291 implements MapLibreMap.OnFpsChangedListener {
                C00291() {
                }

                @Override // org.maplibre.android.maps.MapLibreMap.OnFpsChangedListener
                public void onFpsChanged(final double d) {
                    Handler handler = handler;
                    final MapLibreMap.OnFpsChangedListener onFpsChangedListener = onFpsChangedListener;
                    handler.post(new Runnable() { // from class: org.maplibre.android.maps.NativeMapView$1$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            onFpsChangedListener.onFpsChanged(d);
                        }
                    });
                }
            }

            @Override // java.lang.Runnable
            public void run() {
                if (onFpsChangedListener != null) {
                    NativeMapView.this.mapRenderer.setOnFpsChangedListener(new C00291());
                } else {
                    NativeMapView.this.mapRenderer.setOnFpsChangedListener(null);
                }
            }
        });
    }

    @Override // org.maplibre.android.maps.NativeMap
    public boolean isDestroyed() {
        return this.destroyed;
    }

    private double[] getAnimationPaddingAndClearCachedInsets(double[] dArr) {
        if (dArr == null) {
            dArr = this.edgeInsets;
        }
        this.edgeInsets = null;
        if (dArr == null) {
            return null;
        }
        double d = dArr[1];
        float f = this.pixelRatio;
        return new double[]{d / f, dArr[0] / f, dArr[3] / f, dArr[2] / f};
    }
}

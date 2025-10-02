package org.maplibre.android.snapshotter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.uimanager.ViewProps;
import com.oblador.keychain.KeychainModule;
import io.sentry.clientreport.DiscardedEvent;
import io.sentry.protocol.DebugMeta;
import io.sentry.protocol.Geo;
import io.sentry.protocol.SentryStackTrace;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.maplibre.android.R;
import org.maplibre.android.attribution.AttributionLayout;
import org.maplibre.android.attribution.AttributionMeasure;
import org.maplibre.android.attribution.AttributionParser;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.constants.MapLibreConstants;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.android.log.Logger;
import org.maplibre.android.maps.Image;
import org.maplibre.android.maps.Style;
import org.maplibre.android.storage.FileSource;
import org.maplibre.android.style.layers.Layer;
import org.maplibre.android.style.sources.Source;
import org.maplibre.android.utils.FontUtils;
import org.maplibre.android.utils.ThreadUtils;

/* compiled from: MapSnapshotter.kt */
@Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0017\u0018\u0000 {2\u00020\u0001:\u0006vwxyz{B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\u0012\u001a\u00020\u00132\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007J\u0019\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0087 J\u0013\u0010\u0018\u001a\u00020\u00132\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0087 J\u0013\u0010\u001b\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0087 J\u0013\u0010\u001e\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0087 J\u0013\u0010!\u001a\u00020\u00132\b\u0010\"\u001a\u0004\u0018\u00010 H\u0087 J\u0018\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020 H\u0002J\u0018\u0010'\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%2\u0006\u0010(\u001a\u00020 H\u0002J\u0018\u0010)\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%2\u0006\u0010*\u001a\u00020\u0016H\u0002J\u0010\u0010+\u001a\u00020\u00132\u0006\u0010,\u001a\u00020-H\u0002J\u001e\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u00020 2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u000bJ\u0006\u00103\u001a\u00020\u0013J\u0010\u00104\u001a\u00020\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0010\u00105\u001a\u00020\u00132\u0006\u00106\u001a\u000207H\u0014J(\u00108\u001a\u00020\u00132\u0006\u00106\u001a\u0002072\u0006\u00109\u001a\u0002012\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u0016H\u0002J \u0010=\u001a\u00020>2\u0006\u00106\u001a\u0002072\u0006\u00109\u001a\u0002012\u0006\u0010<\u001a\u00020\u0016H\u0002J(\u0010?\u001a\u00020\u00132\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u00162\u0006\u0010@\u001a\u00020AH\u0002J(\u0010?\u001a\u00020\u00132\u0006\u00109\u001a\u0002012\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u00162\u0006\u0010B\u001a\u00020AH\u0002J*\u0010C\u001a\u00020\u00132\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u00020;2\u0006\u0010D\u001a\u00020>2\b\u0010@\u001a\u0004\u0018\u00010AH\u0002J \u0010C\u001a\u00020\u00132\u0006\u0010:\u001a\u00020;2\u0006\u0010D\u001a\u00020>2\u0006\u0010E\u001a\u00020FH\u0002J \u0010G\u001a\u00020H2\u0006\u00106\u001a\u0002072\u0006\u0010I\u001a\u00020\u000b2\u0006\u0010J\u001a\u00020KH\u0002J\u0018\u0010L\u001a\u00020 2\u0006\u00106\u001a\u0002072\u0006\u0010I\u001a\u00020\u000bH\u0002J\u0014\u0010M\u001a\u00060NR\u00020\u00002\u0006\u00109\u001a\u000201H\u0002J\u0018\u0010O\u001a\u00020K2\u0006\u00109\u001a\u0002012\u0006\u0010P\u001a\u000201H\u0002J\u0010\u0010Q\u001a\u00020\u00132\u0006\u00109\u001a\u000207H\u0005J\u0010\u0010R\u001a\u00020\u00132\u0006\u0010S\u001a\u00020 H\u0005J\u0010\u0010T\u001a\u00020\u00132\u0006\u0010S\u001a\u00020 H\u0005J\b\u0010U\u001a\u00020\u0013H\u0005J\u0010\u0010V\u001a\u0004\u0018\u00010%2\u0006\u0010W\u001a\u00020 J\u0010\u0010X\u001a\u0004\u0018\u00010-2\u0006\u0010Y\u001a\u00020 J\u0010\u0010Z\u001a\u00020\u00132\u0006\u0010[\u001a\u00020 H\u0005J\b\u0010\\\u001a\u00020\u0013H\u0002J\b\u0010]\u001a\u00020\u0013H\u0004Jo\u0010^\u001a\u00020\u00132\b\u0010_\u001a\u0004\u0018\u00010\u00002\b\u0010`\u001a\u0004\u0018\u00010a2\u0006\u0010b\u001a\u00020K2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010\"\u001a\u0004\u0018\u00010 2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010c\u001a\u0004\u0018\u00010\u001a2\u0006\u0010d\u001a\u00020\u000b2\b\u0010e\u001a\u0004\u0018\u00010 H\u0085 J\t\u0010f\u001a\u00020\u0013H\u0085 J\t\u0010g\u001a\u00020\u0013H\u0085 J\u0019\u0010h\u001a\u00020\u00132\u0006\u0010i\u001a\u00020\t2\u0006\u0010&\u001a\u00020 H\u0083 J\u0019\u0010j\u001a\u00020\u00132\u0006\u0010i\u001a\u00020\t2\u0006\u0010(\u001a\u00020 H\u0083 J\u0019\u0010k\u001a\u00020\u00132\u0006\u0010i\u001a\u00020\t2\u0006\u0010*\u001a\u00020\u0016H\u0083 J\u0019\u0010l\u001a\u00020\u00132\u0006\u0010,\u001a\u00020-2\u0006\u0010m\u001a\u00020\tH\u0083 J\u001c\u0010n\u001a\u00020\u00132\f\u0010o\u001a\b\u0012\u0004\u0012\u00020q0pH\u0083 ¢\u0006\u0002\u0010rJ\u0011\u0010s\u001a\u00020%2\u0006\u0010W\u001a\u00020 H\u0083 J\u0011\u0010t\u001a\u00020-2\u0006\u0010Y\u001a\u00020 H\u0083 J\t\u0010u\u001a\u00020\u0013H\u0095 R\u0010\u0010\b\u001a\u00020\t8\u0002X\u0083D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006|"}, d2 = {"Lorg/maplibre/android/snapshotter/MapSnapshotter;", "", "context", "Landroid/content/Context;", "options", "Lorg/maplibre/android/snapshotter/MapSnapshotter$Options;", "<init>", "(Landroid/content/Context;Lorg/maplibre/android/snapshotter/MapSnapshotter$Options;)V", "nativePtr", "", "fullyLoaded", "", "callback", "Lorg/maplibre/android/snapshotter/MapSnapshotter$SnapshotReadyCallback;", "errorHandler", "Lorg/maplibre/android/snapshotter/MapSnapshotter$ErrorHandler;", "observer", "Lorg/maplibre/android/snapshotter/MapSnapshotter$Observer;", "start", "", "setSize", "width", "", "height", "setCameraPosition", "cameraPosition", "Lorg/maplibre/android/camera/CameraPosition;", "setRegion", Geo.JsonKeys.REGION, "Lorg/maplibre/android/geometry/LatLngBounds;", "setStyleUrl", "styleUrl", "", "setStyleJson", "styleJson", "addLayerBelow", "layer", "Lorg/maplibre/android/style/layers/Layer;", "below", "addLayerAbove", "above", "addLayerAt", "index", "addSource", "source", "Lorg/maplibre/android/style/sources/Source;", "addImage", "name", "bitmap", "Landroid/graphics/Bitmap;", "sdf", KeychainModule.AuthPromptOptions.CANCEL, "setObserver", "addOverlay", "mapSnapshot", "Lorg/maplibre/android/snapshotter/MapSnapshot;", "drawOverlay", SentryStackTrace.JsonKeys.SNAPSHOT, "canvas", "Landroid/graphics/Canvas;", ViewProps.MARGIN, "getAttributionMeasure", "Lorg/maplibre/android/attribution/AttributionMeasure;", "drawLogo", "layout", "Lorg/maplibre/android/attribution/AttributionLayout;", "placement", "drawAttribution", "measure", "anchorPoint", "Landroid/graphics/PointF;", "createTextView", "Landroid/widget/TextView;", "shortText", "scale", "", "createAttributionString", "createScaledLogo", "Lorg/maplibre/android/snapshotter/MapSnapshotter$Logo;", "calculateLogoScale", "logo", "onSnapshotReady", "onSnapshotFailed", DiscardedEvent.JsonKeys.REASON, "onDidFailLoadingStyle", "onDidFinishLoadingStyle", "getLayer", "layerId", "getSource", "sourceId", "onStyleImageMissing", "imageName", "checkThread", "reset", "nativeInitialize", "mapSnapshotter", "fileSource", "Lorg/maplibre/android/storage/FileSource;", "pixelRatio", ViewProps.POSITION, "showLogo", "localIdeographFontFamily", "nativeStart", "nativeCancel", "nativeAddLayerBelow", "layerPtr", "nativeAddLayerAbove", "nativeAddLayerAt", "nativeAddSource", "sourcePtr", "nativeAddImages", DebugMeta.JsonKeys.IMAGES, "", "Lorg/maplibre/android/maps/Image;", "([Lorg/maplibre/android/maps/Image;)V", "nativeGetLayer", "nativeGetSource", "finalize", "SnapshotReadyCallback", "ErrorHandler", "Observer", "Options", "Logo", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public class MapSnapshotter {
    private static final int LOGO_MARGIN_DP = 4;
    private static final String TAG = "Mbgl-MapSnapshotter";
    private SnapshotReadyCallback callback;
    private final Context context;
    private ErrorHandler errorHandler;
    private boolean fullyLoaded;
    private final long nativePtr;
    private Observer observer;
    private final Options options;

    /* compiled from: MapSnapshotter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lorg/maplibre/android/snapshotter/MapSnapshotter$ErrorHandler;", "", "onError", "", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface ErrorHandler {
        void onError(String error);
    }

    /* compiled from: MapSnapshotter.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lorg/maplibre/android/snapshotter/MapSnapshotter$Observer;", "", "onDidFinishLoadingStyle", "", "onStyleImageMissing", "imageName", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface Observer {
        void onDidFinishLoadingStyle();

        void onStyleImageMissing(String imageName);
    }

    /* compiled from: MapSnapshotter.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lorg/maplibre/android/snapshotter/MapSnapshotter$SnapshotReadyCallback;", "", "onSnapshotReady", "", SentryStackTrace.JsonKeys.SNAPSHOT, "Lorg/maplibre/android/snapshotter/MapSnapshot;", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface SnapshotReadyCallback {
        void onSnapshotReady(MapSnapshot snapshot);
    }

    private final native void nativeAddImages(Image[] images);

    private final native void nativeAddLayerAbove(long layerPtr, String above);

    private final native void nativeAddLayerAt(long layerPtr, int index);

    private final native void nativeAddLayerBelow(long layerPtr, String below);

    private final native void nativeAddSource(Source source, long sourcePtr);

    private final native Layer nativeGetLayer(String layerId);

    private final native Source nativeGetSource(String sourceId);

    protected native void finalize() throws Throwable;

    protected final native void nativeCancel();

    protected final native void nativeInitialize(MapSnapshotter mapSnapshotter, FileSource fileSource, float pixelRatio, int width, int height, String styleUrl, String styleJson, LatLngBounds region, CameraPosition position, boolean showLogo, String localIdeographFontFamily);

    protected final native void nativeStart();

    public final native void setCameraPosition(CameraPosition cameraPosition);

    public final native void setRegion(LatLngBounds region);

    public final native void setSize(int width, int height);

    public final native void setStyleJson(String styleJson);

    public final native void setStyleUrl(String styleUrl);

    public final void start(SnapshotReadyCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        start$default(this, callback, null, 2, null);
    }

    public MapSnapshotter(Context context, Options options) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        checkThread();
        this.context = context.getApplicationContext();
        this.options = options;
        FileSource fileSource = FileSource.getInstance(context);
        String apiBaseUri = options.getApiBaseUri();
        if (!TextUtils.isEmpty(apiBaseUri)) {
            fileSource.setApiBaseUrl(apiBaseUri);
        }
        nativeInitialize(this, fileSource, options.getPixelRatio(), options.getWidth(), options.getHeight(), options.getStyleUri(), options.getStyleJson(), options.getRegion(), options.getCameraPosition(), options.getShowLogo(), options.getLocalIdeographFontFamily());
    }

    /* compiled from: MapSnapshotter.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0011\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010'\u001a\u00020\u00002\b\u0010$\u001a\u0004\u0018\u00010#J\u0012\u0010(\u001a\u00020\u00002\b\u0010)\u001a\u0004\u0018\u00010\u001dH\u0007J\u0012\u0010*\u001a\u00020\u00002\b\u0010+\u001a\u0004\u0018\u00010\u001dH\u0007J\u0010\u0010,\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fJ\u000e\u0010-\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bJ\u0010\u0010.\u001a\u00020\u00002\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013J\u000e\u0010/\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u00100\u001a\u00020\u00002\b\u00101\u001a\u0004\u0018\u00010\u001dJ#\u00100\u001a\u00020\u00002\u0016\u00102\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001d03\"\u0004\u0018\u00010\u001d¢\u0006\u0002\u00104J\u0012\u00105\u001a\u00020\u00002\b\u00106\u001a\u0004\u0018\u00010\u001dH\u0007J\u0010\u00107\u001a\u00020\u00002\b\u0010!\u001a\u0004\u0018\u00010\u001dR\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\"\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0007\u001a\u0004\u0018\u00010\u0013@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0007\u001a\u00020\u001d@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\"\u0010!\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0007\u001a\u0004\u0018\u00010\u001d@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\"\u0010$\u001a\u0004\u0018\u00010#2\b\u0010\u0007\u001a\u0004\u0018\u00010#@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0013\u00108\u001a\u0004\u0018\u00010\u001d8G¢\u0006\u0006\u001a\u0004\b9\u0010 R\u0013\u0010:\u001a\u0004\u0018\u00010\u001d8F¢\u0006\u0006\u001a\u0004\b;\u0010 R\u0013\u0010+\u001a\u0004\u0018\u00010\u001d8F¢\u0006\u0006\u001a\u0004\b<\u0010 ¨\u0006="}, d2 = {"Lorg/maplibre/android/snapshotter/MapSnapshotter$Options;", "", "width", "", "height", "<init>", "(II)V", "value", "", "pixelRatio", "getPixelRatio", "()F", "getWidth", "()I", "getHeight", "Lorg/maplibre/android/geometry/LatLngBounds;", Geo.JsonKeys.REGION, "getRegion", "()Lorg/maplibre/android/geometry/LatLngBounds;", "Lorg/maplibre/android/camera/CameraPosition;", "cameraPosition", "getCameraPosition", "()Lorg/maplibre/android/camera/CameraPosition;", "showLogo", "", "getShowLogo", "()Z", "setShowLogo", "(Z)V", "", "localIdeographFontFamily", "getLocalIdeographFontFamily", "()Ljava/lang/String;", "apiBaseUri", "getApiBaseUri", "Lorg/maplibre/android/maps/Style$Builder;", "builder", "getBuilder", "()Lorg/maplibre/android/maps/Style$Builder;", "withStyleBuilder", "withStyle", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "withStyleJson", "styleJson", "withRegion", "withPixelRatio", "withCameraPosition", "withLogo", "withLocalIdeographFontFamily", ViewProps.FONT_FAMILY, "fontFamilies", "", "([Ljava/lang/String;)Lorg/maplibre/android/snapshotter/MapSnapshotter$Options;", "withApiBaseUrl", "apiBaseUrl", "withApiBaseUri", "styleUrl", "getStyleUrl", "styleUri", "getStyleUri", "getStyleJson", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Options {
        private String apiBaseUri;
        private Style.Builder builder;
        private CameraPosition cameraPosition;
        private final int height;
        private LatLngBounds region;
        private final int width;
        private float pixelRatio = 1.0f;
        private boolean showLogo = true;
        private String localIdeographFontFamily = MapLibreConstants.DEFAULT_FONT;

        public Options(int i, int i2) {
            if (!((i == 0 || i2 == 0) ? false : true)) {
                throw new IllegalArgumentException("Unable to create a snapshot with width or height set to 0".toString());
            }
            this.width = i;
            this.height = i2;
        }

        public final float getPixelRatio() {
            return this.pixelRatio;
        }

        public final int getWidth() {
            return this.width;
        }

        public final int getHeight() {
            return this.height;
        }

        public final LatLngBounds getRegion() {
            return this.region;
        }

        public final CameraPosition getCameraPosition() {
            return this.cameraPosition;
        }

        public final boolean getShowLogo() {
            return this.showLogo;
        }

        public final void setShowLogo(boolean z) {
            this.showLogo = z;
        }

        public final String getLocalIdeographFontFamily() {
            return this.localIdeographFontFamily;
        }

        public final String getApiBaseUri() {
            return this.apiBaseUri;
        }

        public final Style.Builder getBuilder() {
            return this.builder;
        }

        public final Options withStyleBuilder(Style.Builder builder) {
            this.builder = builder;
            return this;
        }

        @Deprecated(message = "use {@link  #withStyleBuilder(Style.Builder)} instead")
        public final Options withStyle(String uri) {
            Style.Builder builder = new Style.Builder();
            Intrinsics.checkNotNull(uri);
            withStyleBuilder(builder.fromUri(uri));
            return this;
        }

        @Deprecated(message = "use {@link  #withStyleBuilder(Style.Builder)} instead")
        public final Options withStyleJson(String styleJson) {
            Style.Builder builder = new Style.Builder();
            Intrinsics.checkNotNull(styleJson);
            withStyleBuilder(builder.fromJson(styleJson));
            return this;
        }

        public final Options withRegion(LatLngBounds region) {
            this.region = region;
            return this;
        }

        public final Options withPixelRatio(float pixelRatio) {
            this.pixelRatio = pixelRatio;
            return this;
        }

        public final Options withCameraPosition(CameraPosition cameraPosition) {
            this.cameraPosition = cameraPosition;
            return this;
        }

        public final Options withLogo(boolean showLogo) {
            this.showLogo = showLogo;
            return this;
        }

        public final Options withLocalIdeographFontFamily(String fontFamily) {
            this.localIdeographFontFamily = FontUtils.extractValidFont(fontFamily);
            return this;
        }

        public final Options withLocalIdeographFontFamily(String... fontFamilies) {
            Intrinsics.checkNotNullParameter(fontFamilies, "fontFamilies");
            this.localIdeographFontFamily = FontUtils.extractValidFont((String[]) Arrays.copyOf(fontFamilies, fontFamilies.length));
            return this;
        }

        @Deprecated(message = "use {@link  #withApiBaseUri(String)} instead")
        public final Options withApiBaseUrl(String apiBaseUrl) {
            this.apiBaseUri = apiBaseUrl;
            return this;
        }

        public final Options withApiBaseUri(String apiBaseUri) {
            this.apiBaseUri = apiBaseUri;
            return this;
        }

        @Deprecated(message = "use {@link #getStyleUri()} instead")
        public final String getStyleUrl() {
            Style.Builder builder = this.builder;
            if (builder == null) {
                return null;
            }
            Intrinsics.checkNotNull(builder);
            return builder.getUri();
        }

        public final String getStyleUri() {
            Style.Builder builder = this.builder;
            if (builder == null) {
                return null;
            }
            Intrinsics.checkNotNull(builder);
            return builder.getUri();
        }

        public final String getStyleJson() {
            Style.Builder builder = this.builder;
            if (builder == null) {
                return null;
            }
            Intrinsics.checkNotNull(builder);
            return builder.getJson();
        }
    }

    public static /* synthetic */ void start$default(MapSnapshotter mapSnapshotter, SnapshotReadyCallback snapshotReadyCallback, ErrorHandler errorHandler, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: start");
        }
        if ((i & 2) != 0) {
            errorHandler = null;
        }
        mapSnapshotter.start(snapshotReadyCallback, errorHandler);
    }

    public final void start(SnapshotReadyCallback callback, ErrorHandler errorHandler) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (!(this.callback == null)) {
            throw new IllegalStateException("Snapshotter was already started".toString());
        }
        checkThread();
        this.callback = callback;
        this.errorHandler = errorHandler;
        nativeStart();
    }

    private final void addLayerBelow(Layer layer, String below) {
        nativeAddLayerBelow(layer.getNativePtr(), below);
    }

    private final void addLayerAbove(Layer layer, String above) {
        nativeAddLayerAbove(layer.getNativePtr(), above);
    }

    private final void addLayerAt(Layer layer, int index) {
        nativeAddLayerAt(layer.getNativePtr(), index);
    }

    private final void addSource(Source source) {
        nativeAddSource(source, source.getNativePtr());
    }

    public final void addImage(String name, Bitmap bitmap, boolean sdf) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        nativeAddImages(new Image[]{Style.toImage(new Style.Builder.ImageWrapper(name, bitmap, sdf))});
    }

    public final void cancel() {
        checkThread();
        reset();
        nativeCancel();
    }

    public final void setObserver(Observer observer) {
        checkThread();
        this.observer = observer;
    }

    protected void addOverlay(MapSnapshot mapSnapshot) throws Resources.NotFoundException {
        Intrinsics.checkNotNullParameter(mapSnapshot, "mapSnapshot");
        Bitmap bitmap = mapSnapshot.getBitmap();
        Intrinsics.checkNotNull(bitmap);
        drawOverlay(mapSnapshot, bitmap, new Canvas(bitmap), ((int) this.context.getResources().getDisplayMetrics().density) * 4);
    }

    private final void drawOverlay(MapSnapshot mapSnapshot, Bitmap snapshot, Canvas canvas, int margin) throws Resources.NotFoundException {
        AttributionMeasure attributionMeasure = getAttributionMeasure(mapSnapshot, snapshot, margin);
        AttributionLayout attributionLayoutMeasure = attributionMeasure.measure();
        Intrinsics.checkNotNull(attributionLayoutMeasure);
        drawLogo(mapSnapshot, canvas, margin, attributionLayoutMeasure);
        drawAttribution(mapSnapshot, canvas, attributionMeasure, attributionLayoutMeasure);
    }

    private final AttributionMeasure getAttributionMeasure(MapSnapshot mapSnapshot, Bitmap snapshot, int margin) throws Resources.NotFoundException {
        Logo logoCreateScaledLogo = createScaledLogo(snapshot);
        AttributionMeasure attributionMeasureBuild = new AttributionMeasure.Builder().setSnapshot(snapshot).setLogo(logoCreateScaledLogo.getLarge()).setLogoSmall(logoCreateScaledLogo.getSmall()).setTextView(createTextView(mapSnapshot, false, logoCreateScaledLogo.getScale())).setTextViewShort(createTextView(mapSnapshot, true, logoCreateScaledLogo.getScale())).setMarginPadding(margin).build();
        Intrinsics.checkNotNullExpressionValue(attributionMeasureBuild, "build(...)");
        return attributionMeasureBuild;
    }

    private final void drawLogo(MapSnapshot mapSnapshot, Canvas canvas, int margin, AttributionLayout layout) {
        if (mapSnapshot.getIsShowLogo()) {
            drawLogo(mapSnapshot.getBitmap(), canvas, margin, layout);
        }
    }

    private final void drawLogo(Bitmap snapshot, Canvas canvas, int margin, AttributionLayout placement) {
        Bitmap logo = placement.getLogo();
        if (logo != null) {
            canvas.drawBitmap(logo, margin, (snapshot.getHeight() - logo.getHeight()) - margin, (Paint) null);
        }
    }

    private final void drawAttribution(MapSnapshot mapSnapshot, Canvas canvas, AttributionMeasure measure, AttributionLayout layout) {
        Intrinsics.checkNotNull(layout);
        PointF anchorPoint = layout.getAnchorPoint();
        if (anchorPoint != null) {
            drawAttribution(canvas, measure, anchorPoint);
            return;
        }
        Bitmap bitmap = mapSnapshot.getBitmap();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("Could not generate attribution for snapshot size: %s x %s. You are required to provide your own attribution for the used sources: %s", Arrays.copyOf(new Object[]{Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), mapSnapshot.getAttributions()}, 3));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        Logger.e(TAG, str);
    }

    private final void drawAttribution(Canvas canvas, AttributionMeasure measure, PointF anchorPoint) {
        canvas.save();
        canvas.translate(anchorPoint.x, anchorPoint.y);
        measure.getTextView().draw(canvas);
        canvas.restore();
    }

    private final TextView createTextView(MapSnapshot mapSnapshot, boolean shortText, float scale) throws Resources.NotFoundException {
        int color = ResourcesCompat.getColor(this.context.getResources(), R.color.maplibre_gray_dark, this.context.getTheme());
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        TextView textView = new TextView(this.context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        textView.setSingleLine(true);
        textView.setTextSize(10 * scale);
        textView.setTextColor(color);
        textView.setBackgroundResource(R.drawable.maplibre_rounded_corner);
        textView.setText(Html.fromHtml(createAttributionString(mapSnapshot, shortText)));
        textView.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
        return textView;
    }

    private final String createAttributionString(MapSnapshot mapSnapshot, boolean shortText) {
        AttributionParser.Options options = new AttributionParser.Options(this.context);
        String[] attributions = mapSnapshot.getAttributions();
        AttributionParser attributionParserBuild = options.withAttributionData((String[]) Arrays.copyOf(attributions, attributions.length)).withCopyrightSign(false).withImproveMap(false).build();
        Intrinsics.checkNotNullExpressionValue(attributionParserBuild, "build(...)");
        String strCreateAttributionString = attributionParserBuild.createAttributionString(shortText);
        Intrinsics.checkNotNullExpressionValue(strCreateAttributionString, "createAttributionString(...)");
        return strCreateAttributionString;
    }

    private final Logo createScaledLogo(Bitmap snapshot) {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.maplibre_logo_icon, null);
        Intrinsics.checkNotNull(bitmapDecodeResource);
        float fCalculateLogoScale = calculateLogoScale(snapshot, bitmapDecodeResource);
        Matrix matrix = new Matrix();
        matrix.postScale(fCalculateLogoScale, fCalculateLogoScale);
        Bitmap bitmapDecodeResource2 = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.maplibre_logo_helmet, null);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeResource, 0, 0, bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight(), matrix, true);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapDecodeResource2, 0, 0, bitmapDecodeResource2.getWidth(), bitmapDecodeResource2.getHeight(), matrix, true);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap2, "createBitmap(...)");
        return new Logo(this, bitmapCreateBitmap, bitmapCreateBitmap2, fCalculateLogoScale);
    }

    private final float calculateLogoScale(Bitmap snapshot, Bitmap logo) {
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        float fMin = Math.min((logo.getWidth() / (displayMetrics.widthPixels / snapshot.getWidth())) / logo.getWidth(), (logo.getHeight() / (displayMetrics.heightPixels / snapshot.getHeight())) / logo.getHeight()) * 2;
        float f = 1.0f;
        if (fMin <= 1.0f) {
            f = 0.6f;
            if (fMin >= 0.6f) {
                return fMin;
            }
        }
        return f;
    }

    protected final void onSnapshotReady(final MapSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: org.maplibre.android.snapshotter.MapSnapshotter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws Resources.NotFoundException {
                MapSnapshotter.onSnapshotReady$lambda$1(this.f$0, snapshot);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSnapshotReady$lambda$1(MapSnapshotter mapSnapshotter, MapSnapshot mapSnapshot) throws Resources.NotFoundException {
        if (mapSnapshotter.callback != null) {
            mapSnapshotter.addOverlay(mapSnapshot);
            SnapshotReadyCallback snapshotReadyCallback = mapSnapshotter.callback;
            Intrinsics.checkNotNull(snapshotReadyCallback);
            snapshotReadyCallback.onSnapshotReady(mapSnapshot);
            mapSnapshotter.reset();
        }
    }

    protected final void onSnapshotFailed(final String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: org.maplibre.android.snapshotter.MapSnapshotter$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MapSnapshotter.onSnapshotFailed$lambda$2(this.f$0, reason);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onSnapshotFailed$lambda$2(MapSnapshotter mapSnapshotter, String str) {
        ErrorHandler errorHandler = mapSnapshotter.errorHandler;
        if (errorHandler != null) {
            Intrinsics.checkNotNull(errorHandler);
            errorHandler.onError(str);
            mapSnapshotter.reset();
        }
    }

    protected final void onDidFailLoadingStyle(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        onSnapshotFailed(reason);
    }

    protected final void onDidFinishLoadingStyle() {
        if (!this.fullyLoaded) {
            this.fullyLoaded = true;
            Style.Builder builder = this.options.getBuilder();
            if (builder != null) {
                for (Source source : builder.getSources()) {
                    Intrinsics.checkNotNull(source);
                    nativeAddSource(source, source.getNativePtr());
                }
                for (Style.Builder.LayerWrapper layerWrapper : builder.getLayers()) {
                    if (layerWrapper instanceof Style.Builder.LayerAtWrapper) {
                        Style.Builder.LayerAtWrapper layerAtWrapper = (Style.Builder.LayerAtWrapper) layerWrapper;
                        Layer layer = layerAtWrapper.getLayer();
                        Intrinsics.checkNotNullExpressionValue(layer, "getLayer(...)");
                        addLayerAt(layer, layerAtWrapper.getIndex());
                    } else if (layerWrapper instanceof Style.Builder.LayerAboveWrapper) {
                        Style.Builder.LayerAboveWrapper layerAboveWrapper = (Style.Builder.LayerAboveWrapper) layerWrapper;
                        Layer layer2 = layerAboveWrapper.getLayer();
                        Intrinsics.checkNotNullExpressionValue(layer2, "getLayer(...)");
                        String aboveLayer = layerAboveWrapper.getAboveLayer();
                        Intrinsics.checkNotNullExpressionValue(aboveLayer, "getAboveLayer(...)");
                        addLayerAbove(layer2, aboveLayer);
                    } else if (layerWrapper instanceof Style.Builder.LayerBelowWrapper) {
                        Style.Builder.LayerBelowWrapper layerBelowWrapper = (Style.Builder.LayerBelowWrapper) layerWrapper;
                        Layer layer3 = layerBelowWrapper.getLayer();
                        Intrinsics.checkNotNullExpressionValue(layer3, "getLayer(...)");
                        String belowLayer = layerBelowWrapper.getBelowLayer();
                        Intrinsics.checkNotNullExpressionValue(belowLayer, "getBelowLayer(...)");
                        addLayerBelow(layer3, belowLayer);
                    } else {
                        Layer layer4 = layerWrapper.getLayer();
                        Intrinsics.checkNotNullExpressionValue(layer4, "getLayer(...)");
                        addLayerBelow(layer4, MapLibreConstants.LAYER_ID_ANNOTATIONS);
                    }
                }
                for (Style.Builder.ImageWrapper imageWrapper : builder.getImages()) {
                    String id = imageWrapper.getId();
                    Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                    Bitmap bitmap = imageWrapper.getBitmap();
                    Intrinsics.checkNotNullExpressionValue(bitmap, "getBitmap(...)");
                    addImage(id, bitmap, imageWrapper.isSdf());
                }
            }
        }
        Observer observer = this.observer;
        if (observer != null) {
            Intrinsics.checkNotNull(observer);
            observer.onDidFinishLoadingStyle();
        }
    }

    public final Layer getLayer(String layerId) {
        Intrinsics.checkNotNullParameter(layerId, "layerId");
        checkThread();
        if (this.fullyLoaded) {
            return nativeGetLayer(layerId);
        }
        return null;
    }

    public final Source getSource(String sourceId) {
        Intrinsics.checkNotNullParameter(sourceId, "sourceId");
        checkThread();
        if (this.fullyLoaded) {
            return nativeGetSource(sourceId);
        }
        return null;
    }

    protected final void onStyleImageMissing(String imageName) {
        Intrinsics.checkNotNullParameter(imageName, "imageName");
        Observer observer = this.observer;
        if (observer != null) {
            Intrinsics.checkNotNull(observer);
            observer.onStyleImageMissing(imageName);
        }
    }

    private final void checkThread() {
        ThreadUtils.checkThread(TAG);
    }

    protected final void reset() {
        this.callback = null;
        this.errorHandler = null;
    }

    /* compiled from: MapSnapshotter.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\b\u0082\u0004\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/maplibre/android/snapshotter/MapSnapshotter$Logo;", "", "large", "Landroid/graphics/Bitmap;", "small", "scale", "", "<init>", "(Lorg/maplibre/android/snapshotter/MapSnapshotter;Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;F)V", "getLarge", "()Landroid/graphics/Bitmap;", "getSmall", "getScale", "()F", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private final class Logo {
        private final Bitmap large;
        private final float scale;
        private final Bitmap small;
        final /* synthetic */ MapSnapshotter this$0;

        public Logo(MapSnapshotter mapSnapshotter, Bitmap large, Bitmap small, float f) {
            Intrinsics.checkNotNullParameter(large, "large");
            Intrinsics.checkNotNullParameter(small, "small");
            this.this$0 = mapSnapshotter;
            this.large = large;
            this.small = small;
            this.scale = f;
        }

        public final Bitmap getLarge() {
            return this.large;
        }

        public final float getScale() {
            return this.scale;
        }

        public final Bitmap getSmall() {
            return this.small;
        }
    }
}

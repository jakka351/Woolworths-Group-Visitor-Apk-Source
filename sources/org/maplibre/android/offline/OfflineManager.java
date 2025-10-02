package org.maplibre.android.offline;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.File;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.maplibre.android.LibraryLoader;
import org.maplibre.android.R;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.android.net.ConnectivityReceiver;
import org.maplibre.android.offline.OfflineManager;
import org.maplibre.android.storage.FileSource;
import org.maplibre.android.utils.FileUtils;

/* compiled from: OfflineManager.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0007\u0018\u0000 B2\u00020\u0001:\u0006=>?@ABB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0019J\u0010\u0010\u001a\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0019J\u0010\u0010\u001b\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0019J\u0010\u0010\u001c\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0019J\u0018\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0019J \u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u000f\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#H\u0002J\u001e\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010\u000f\u001a\u00020)J\u0010\u0010*\u001a\u00020#2\u0006\u0010%\u001a\u00020&H\u0002J\u0011\u0010+\u001a\u00020\r2\u0006\u0010,\u001a\u00020\u0007H\u0087 J\u0011\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020#H\u0087 J\u0011\u0010/\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tH\u0083 J\t\u00100\u001a\u00020\rH\u0085 J\u0019\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0083 J!\u0010\u0011\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0013H\u0083 J)\u0010$\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010\u000f\u001a\u00020)H\u0083 J!\u0010\u0014\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0017H\u0083 J\u0013\u00101\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0019H\u0083 J\u0013\u00102\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0019H\u0083 J\u0013\u00103\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0019H\u0083 J\u0013\u00104\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0019H\u0083 J\u001b\u00105\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0019H\u0083 J?\u00106\u001a\u00020\r2\b\u00107\u001a\u0004\u0018\u00010\u00162\b\u00108\u001a\u0004\u0018\u00010(2\u0006\u00109\u001a\u00020\u00072\u0006\u0010:\u001a\u00020\u00072\b\u0010;\u001a\u0004\u0018\u00010\u00162\u0006\u0010<\u001a\u00020#H\u0087 R\u0010\u0010\u0006\u001a\u00020\u00078\u0002X\u0083D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lorg/maplibre/android/offline/OfflineManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "nativePtr", "", "fileSource", "Lorg/maplibre/android/storage/FileSource;", "handler", "Landroid/os/Handler;", "deleteAmbientDatabase", "", "listOfflineRegions", "callback", "Lorg/maplibre/android/offline/OfflineManager$ListOfflineRegionsCallback;", "getOfflineRegion", "regionID", "Lorg/maplibre/android/offline/OfflineManager$GetOfflineRegionCallback;", "mergeOfflineRegions", ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, "", "Lorg/maplibre/android/offline/OfflineManager$MergeOfflineRegionsCallback;", "resetDatabase", "Lorg/maplibre/android/offline/OfflineManager$FileSourceCallback;", "packDatabase", "invalidateAmbientCache", "clearAmbientCache", "setMaximumAmbientCacheSize", RRWebVideoEvent.JsonKeys.SIZE, "mergeOfflineDatabaseFiles", "file", "Ljava/io/File;", "isTemporaryFile", "", "createOfflineRegion", "definition", "Lorg/maplibre/android/offline/OfflineRegionDefinition;", "metadata", "", "Lorg/maplibre/android/offline/OfflineManager$CreateOfflineRegionCallback;", "isValidOfflineRegionDefinition", "setOfflineMapboxTileCountLimit", "limit", "runPackDatabaseAutomatically", "autopack", "initialize", "finalize", "nativeResetDatabase", "nativePackDatabase", "nativeInvalidateAmbientCache", "nativeClearAmbientCache", "nativeSetMaximumAmbientCacheSize", "putResourceWithUrl", "url", "data", "modified", "expires", "etag", "mustRevalidate", "ListOfflineRegionsCallback", "GetOfflineRegionCallback", "CreateOfflineRegionCallback", "MergeOfflineRegionsCallback", "FileSourceCallback", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OfflineManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "Mbgl - OfflineManager";
    private static OfflineManager instance;
    private final Context context;
    private final FileSource fileSource;
    private final Handler handler;
    private final long nativePtr;

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lorg/maplibre/android/offline/OfflineManager$CreateOfflineRegionCallback;", "", "onCreate", "", "offlineRegion", "Lorg/maplibre/android/offline/OfflineRegion;", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface CreateOfflineRegionCallback {
        void onCreate(OfflineRegion offlineRegion);

        void onError(String error);
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lorg/maplibre/android/offline/OfflineManager$FileSourceCallback;", "", "onSuccess", "", "onError", "message", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface FileSourceCallback {
        void onError(String message);

        void onSuccess();
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lorg/maplibre/android/offline/OfflineManager$GetOfflineRegionCallback;", "", "onRegion", "", "offlineRegion", "Lorg/maplibre/android/offline/OfflineRegion;", "onRegionNotFound", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface GetOfflineRegionCallback {
        void onError(String error);

        void onRegion(OfflineRegion offlineRegion);

        void onRegionNotFound();
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u001d\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lorg/maplibre/android/offline/OfflineManager$ListOfflineRegionsCallback;", "", "onList", "", "offlineRegions", "", "Lorg/maplibre/android/offline/OfflineRegion;", "([Lorg/maplibre/android/offline/OfflineRegion;)V", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface ListOfflineRegionsCallback {
        void onError(String error);

        void onList(OfflineRegion[] offlineRegions);
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u001d\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lorg/maplibre/android/offline/OfflineManager$MergeOfflineRegionsCallback;", "", "onMerge", "", "offlineRegions", "", "Lorg/maplibre/android/offline/OfflineRegion;", "([Lorg/maplibre/android/offline/OfflineRegion;)V", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface MergeOfflineRegionsCallback {
        void onError(String error);

        void onMerge(OfflineRegion[] offlineRegions);
    }

    public /* synthetic */ OfflineManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private final native void createOfflineRegion(FileSource fileSource, OfflineRegionDefinition definition, byte[] metadata, CreateOfflineRegionCallback callback);

    private final native void getOfflineRegion(FileSource fileSource, long regionID, GetOfflineRegionCallback callback);

    private final native void initialize(FileSource fileSource);

    private final native void listOfflineRegions(FileSource fileSource, ListOfflineRegionsCallback callback);

    private final native void mergeOfflineRegions(FileSource fileSource, String path, MergeOfflineRegionsCallback callback);

    private final native void nativeClearAmbientCache(FileSourceCallback callback);

    private final native void nativeInvalidateAmbientCache(FileSourceCallback callback);

    private final native void nativePackDatabase(FileSourceCallback callback);

    private final native void nativeResetDatabase(FileSourceCallback callback);

    private final native void nativeSetMaximumAmbientCacheSize(long size, FileSourceCallback callback);

    protected final native void finalize() throws Throwable;

    public final native void putResourceWithUrl(String url, byte[] data, long modified, long expires, String etag, boolean mustRevalidate);

    public final native void runPackDatabaseAutomatically(boolean autopack);

    public final native void setOfflineMapboxTileCountLimit(long limit);

    private OfflineManager(Context context) {
        this.handler = new Handler(Looper.getMainLooper());
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext;
        FileSource fileSource = FileSource.getInstance(applicationContext);
        this.fileSource = fileSource;
        initialize(fileSource);
        deleteAmbientDatabase(applicationContext);
    }

    private final void deleteAmbientDatabase(Context context) {
        FileUtils.deleteFile(FileSource.getInternalCachePath(context) + File.separator + "mbgl-cache.db");
    }

    public final void listOfflineRegions(ListOfflineRegionsCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.fileSource.activate();
        listOfflineRegions(this.fileSource, new C02131(callback));
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001d\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"org/maplibre/android/offline/OfflineManager$listOfflineRegions$1", "Lorg/maplibre/android/offline/OfflineManager$ListOfflineRegionsCallback;", "onList", "", "offlineRegions", "", "Lorg/maplibre/android/offline/OfflineRegion;", "([Lorg/maplibre/android/offline/OfflineRegion;)V", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineManager$listOfflineRegions$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02131 implements ListOfflineRegionsCallback {
        final /* synthetic */ ListOfflineRegionsCallback $callback;

        C02131(ListOfflineRegionsCallback listOfflineRegionsCallback) {
            this.$callback = listOfflineRegionsCallback;
        }

        @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
        public void onList(final OfflineRegion[] offlineRegions) {
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final ListOfflineRegionsCallback listOfflineRegionsCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$listOfflineRegions$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02131.onList$lambda$0(offlineManager, listOfflineRegionsCallback, offlineRegions);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onList$lambda$0(OfflineManager offlineManager, ListOfflineRegionsCallback listOfflineRegionsCallback, OfflineRegion[] offlineRegionArr) {
            offlineManager.fileSource.deactivate();
            listOfflineRegionsCallback.onList(offlineRegionArr);
        }

        @Override // org.maplibre.android.offline.OfflineManager.ListOfflineRegionsCallback
        public void onError(final String error) {
            Intrinsics.checkNotNullParameter(error, "error");
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final ListOfflineRegionsCallback listOfflineRegionsCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$listOfflineRegions$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02131.onError$lambda$1(offlineManager, listOfflineRegionsCallback, error);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$1(OfflineManager offlineManager, ListOfflineRegionsCallback listOfflineRegionsCallback, String str) {
            offlineManager.fileSource.deactivate();
            listOfflineRegionsCallback.onError(str);
        }
    }

    public final void getOfflineRegion(long regionID, GetOfflineRegionCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.fileSource.activate();
        getOfflineRegion(this.fileSource, regionID, new C02111(callback));
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"org/maplibre/android/offline/OfflineManager$getOfflineRegion$1", "Lorg/maplibre/android/offline/OfflineManager$GetOfflineRegionCallback;", "onRegion", "", "offlineRegion", "Lorg/maplibre/android/offline/OfflineRegion;", "onRegionNotFound", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineManager$getOfflineRegion$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02111 implements GetOfflineRegionCallback {
        final /* synthetic */ GetOfflineRegionCallback $callback;

        C02111(GetOfflineRegionCallback getOfflineRegionCallback) {
            this.$callback = getOfflineRegionCallback;
        }

        @Override // org.maplibre.android.offline.OfflineManager.GetOfflineRegionCallback
        public void onRegion(final OfflineRegion offlineRegion) {
            Intrinsics.checkNotNullParameter(offlineRegion, "offlineRegion");
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final GetOfflineRegionCallback getOfflineRegionCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$getOfflineRegion$1$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02111.onRegion$lambda$0(offlineManager, getOfflineRegionCallback, offlineRegion);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onRegion$lambda$0(OfflineManager offlineManager, GetOfflineRegionCallback getOfflineRegionCallback, OfflineRegion offlineRegion) {
            offlineManager.fileSource.deactivate();
            getOfflineRegionCallback.onRegion(offlineRegion);
        }

        @Override // org.maplibre.android.offline.OfflineManager.GetOfflineRegionCallback
        public void onRegionNotFound() {
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final GetOfflineRegionCallback getOfflineRegionCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$getOfflineRegion$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02111.onRegionNotFound$lambda$1(offlineManager, getOfflineRegionCallback);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onRegionNotFound$lambda$1(OfflineManager offlineManager, GetOfflineRegionCallback getOfflineRegionCallback) {
            offlineManager.fileSource.deactivate();
            getOfflineRegionCallback.onRegionNotFound();
        }

        @Override // org.maplibre.android.offline.OfflineManager.GetOfflineRegionCallback
        public void onError(final String error) {
            Intrinsics.checkNotNullParameter(error, "error");
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final GetOfflineRegionCallback getOfflineRegionCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$getOfflineRegion$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02111.onError$lambda$2(offlineManager, getOfflineRegionCallback, error);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$2(OfflineManager offlineManager, GetOfflineRegionCallback getOfflineRegionCallback, String str) {
            offlineManager.fileSource.deactivate();
            getOfflineRegionCallback.onError(str);
        }
    }

    public final void mergeOfflineRegions(String path, final MergeOfflineRegionsCallback callback) {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final File file = new File(path);
        new Thread(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                OfflineManager.mergeOfflineRegions$lambda$3(file, this, callback);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void mergeOfflineRegions$lambda$3(final java.io.File r3, final org.maplibre.android.offline.OfflineManager r4, final org.maplibre.android.offline.OfflineManager.MergeOfflineRegionsCallback r5) throws java.lang.Throwable {
        /*
            boolean r0 = r3.canWrite()
            if (r0 == 0) goto L11
            android.os.Handler r0 = r4.handler
            org.maplibre.android.offline.OfflineManager$$ExternalSyntheticLambda0 r1 = new org.maplibre.android.offline.OfflineManager$$ExternalSyntheticLambda0
            r1.<init>()
            r0.post(r1)
            goto L39
        L11:
            boolean r0 = r3.canRead()
            if (r0 == 0) goto L46
            java.io.File r0 = new java.io.File
            android.content.Context r1 = r4.context
            java.lang.String r1 = org.maplibre.android.storage.FileSource.getInternalCachePath(r1)
            java.lang.String r2 = r3.getName()
            r0.<init>(r1, r2)
            org.maplibre.android.offline.OfflineManager$Companion r1 = org.maplibre.android.offline.OfflineManager.INSTANCE     // Catch: java.io.IOException -> L3b
            org.maplibre.android.offline.OfflineManager.Companion.access$copyTempDatabaseFile(r1, r3, r0)     // Catch: java.io.IOException -> L3b
            android.os.Handler r3 = r4.handler     // Catch: java.io.IOException -> L3b
            org.maplibre.android.offline.OfflineManager$$ExternalSyntheticLambda1 r1 = new org.maplibre.android.offline.OfflineManager$$ExternalSyntheticLambda1     // Catch: java.io.IOException -> L3b
            r1.<init>()     // Catch: java.io.IOException -> L3b
            boolean r3 = r3.post(r1)     // Catch: java.io.IOException -> L3b
            java.lang.Boolean.valueOf(r3)     // Catch: java.io.IOException -> L3b
        L39:
            r3 = 0
            goto L48
        L3b:
            r3 = move-exception
            r3.printStackTrace()
            java.lang.String r3 = r3.getMessage()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            goto L48
        L46:
            java.lang.String r3 = "Secondary database needs to be located in a readable path."
        L48:
            if (r3 == 0) goto L54
            android.os.Handler r4 = r4.handler
            org.maplibre.android.offline.OfflineManager$$ExternalSyntheticLambda2 r0 = new org.maplibre.android.offline.OfflineManager$$ExternalSyntheticLambda2
            r0.<init>()
            r4.post(r0)
        L54:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.maplibre.android.offline.OfflineManager.mergeOfflineRegions$lambda$3(java.io.File, org.maplibre.android.offline.OfflineManager, org.maplibre.android.offline.OfflineManager$MergeOfflineRegionsCallback):void");
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"org/maplibre/android/offline/OfflineManager$resetDatabase$1", "Lorg/maplibre/android/offline/OfflineManager$FileSourceCallback;", "onSuccess", "", "onError", "message", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineManager$resetDatabase$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02161 implements FileSourceCallback {
        final /* synthetic */ FileSourceCallback $callback;

        C02161(FileSourceCallback fileSourceCallback) {
            this.$callback = fileSourceCallback;
        }

        @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
        public void onSuccess() {
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final FileSourceCallback fileSourceCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$resetDatabase$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02161.onSuccess$lambda$0(offlineManager, fileSourceCallback);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(OfflineManager offlineManager, FileSourceCallback fileSourceCallback) {
            offlineManager.fileSource.deactivate();
            if (fileSourceCallback != null) {
                fileSourceCallback.onSuccess();
            }
        }

        @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
        public void onError(final String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final FileSourceCallback fileSourceCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$resetDatabase$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02161.onError$lambda$1(offlineManager, fileSourceCallback, message);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$1(OfflineManager offlineManager, FileSourceCallback fileSourceCallback, String str) {
            offlineManager.fileSource.deactivate();
            if (fileSourceCallback != null) {
                fileSourceCallback.onError(str);
            }
        }
    }

    public final void resetDatabase(FileSourceCallback callback) {
        this.fileSource.activate();
        nativeResetDatabase(new C02161(callback));
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"org/maplibre/android/offline/OfflineManager$packDatabase$1", "Lorg/maplibre/android/offline/OfflineManager$FileSourceCallback;", "onSuccess", "", "onError", "message", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineManager$packDatabase$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02151 implements FileSourceCallback {
        final /* synthetic */ FileSourceCallback $callback;

        C02151(FileSourceCallback fileSourceCallback) {
            this.$callback = fileSourceCallback;
        }

        @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
        public void onSuccess() {
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final FileSourceCallback fileSourceCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$packDatabase$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02151.onSuccess$lambda$0(offlineManager, fileSourceCallback);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(OfflineManager offlineManager, FileSourceCallback fileSourceCallback) {
            offlineManager.fileSource.deactivate();
            if (fileSourceCallback != null) {
                fileSourceCallback.onSuccess();
            }
        }

        @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
        public void onError(final String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final FileSourceCallback fileSourceCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$packDatabase$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02151.onError$lambda$1(offlineManager, fileSourceCallback, message);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$1(OfflineManager offlineManager, FileSourceCallback fileSourceCallback, String str) {
            offlineManager.fileSource.deactivate();
            if (fileSourceCallback != null) {
                fileSourceCallback.onError(str);
            }
        }
    }

    public final void packDatabase(FileSourceCallback callback) {
        this.fileSource.activate();
        nativePackDatabase(new C02151(callback));
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"org/maplibre/android/offline/OfflineManager$invalidateAmbientCache$1", "Lorg/maplibre/android/offline/OfflineManager$FileSourceCallback;", "onSuccess", "", "onError", "message", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineManager$invalidateAmbientCache$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02121 implements FileSourceCallback {
        final /* synthetic */ FileSourceCallback $callback;

        C02121(FileSourceCallback fileSourceCallback) {
            this.$callback = fileSourceCallback;
        }

        @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
        public void onSuccess() {
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final FileSourceCallback fileSourceCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$invalidateAmbientCache$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02121.onSuccess$lambda$0(offlineManager, fileSourceCallback);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(OfflineManager offlineManager, FileSourceCallback fileSourceCallback) {
            offlineManager.fileSource.deactivate();
            if (fileSourceCallback != null) {
                fileSourceCallback.onSuccess();
            }
        }

        @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
        public void onError(final String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final FileSourceCallback fileSourceCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$invalidateAmbientCache$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02121.onError$lambda$1(offlineManager, fileSourceCallback, message);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$1(OfflineManager offlineManager, FileSourceCallback fileSourceCallback, String str) {
            offlineManager.fileSource.deactivate();
            if (fileSourceCallback != null) {
                fileSourceCallback.onError(str);
            }
        }
    }

    public final void invalidateAmbientCache(FileSourceCallback callback) {
        this.fileSource.activate();
        nativeInvalidateAmbientCache(new C02121(callback));
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"org/maplibre/android/offline/OfflineManager$clearAmbientCache$1", "Lorg/maplibre/android/offline/OfflineManager$FileSourceCallback;", "onSuccess", "", "onError", "message", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineManager$clearAmbientCache$1, reason: invalid class name */
    public static final class AnonymousClass1 implements FileSourceCallback {
        final /* synthetic */ FileSourceCallback $callback;

        AnonymousClass1(FileSourceCallback fileSourceCallback) {
            this.$callback = fileSourceCallback;
        }

        @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
        public void onSuccess() {
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final FileSourceCallback fileSourceCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$clearAmbientCache$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.AnonymousClass1.onSuccess$lambda$0(offlineManager, fileSourceCallback);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(OfflineManager offlineManager, FileSourceCallback fileSourceCallback) {
            offlineManager.fileSource.deactivate();
            if (fileSourceCallback != null) {
                fileSourceCallback.onSuccess();
            }
        }

        @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
        public void onError(final String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final FileSourceCallback fileSourceCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$clearAmbientCache$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.AnonymousClass1.onError$lambda$1(offlineManager, fileSourceCallback, message);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$1(OfflineManager offlineManager, FileSourceCallback fileSourceCallback, String str) {
            offlineManager.fileSource.deactivate();
            if (fileSourceCallback != null) {
                fileSourceCallback.onError(str);
            }
        }
    }

    public final void clearAmbientCache(FileSourceCallback callback) {
        this.fileSource.activate();
        nativeClearAmbientCache(new AnonymousClass1(callback));
    }

    public final void setMaximumAmbientCacheSize(long size, FileSourceCallback callback) {
        this.fileSource.activate();
        nativeSetMaximumAmbientCacheSize(size, new C02171(callback));
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"org/maplibre/android/offline/OfflineManager$setMaximumAmbientCacheSize$1", "Lorg/maplibre/android/offline/OfflineManager$FileSourceCallback;", "onSuccess", "", "onError", "message", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineManager$setMaximumAmbientCacheSize$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02171 implements FileSourceCallback {
        final /* synthetic */ FileSourceCallback $callback;

        C02171(FileSourceCallback fileSourceCallback) {
            this.$callback = fileSourceCallback;
        }

        @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
        public void onSuccess() {
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final FileSourceCallback fileSourceCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$setMaximumAmbientCacheSize$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02171.onSuccess$lambda$0(offlineManager, fileSourceCallback);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onSuccess$lambda$0(OfflineManager offlineManager, FileSourceCallback fileSourceCallback) {
            offlineManager.fileSource.deactivate();
            if (fileSourceCallback != null) {
                fileSourceCallback.onSuccess();
            }
        }

        @Override // org.maplibre.android.offline.OfflineManager.FileSourceCallback
        public void onError(final String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            OfflineManager.this.fileSource.activate();
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final FileSourceCallback fileSourceCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$setMaximumAmbientCacheSize$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02171.onError$lambda$1(offlineManager, fileSourceCallback, message);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$1(OfflineManager offlineManager, FileSourceCallback fileSourceCallback, String str) {
            offlineManager.fileSource.deactivate();
            if (fileSourceCallback != null) {
                fileSourceCallback.onError(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void mergeOfflineDatabaseFiles(File file, MergeOfflineRegionsCallback callback, boolean isTemporaryFile) {
        this.fileSource.activate();
        FileSource fileSource = this.fileSource;
        String absolutePath = file.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        mergeOfflineRegions(fileSource, absolutePath, new C02141(isTemporaryFile, file, this, callback));
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001d\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"org/maplibre/android/offline/OfflineManager$mergeOfflineDatabaseFiles$1", "Lorg/maplibre/android/offline/OfflineManager$MergeOfflineRegionsCallback;", "onMerge", "", "offlineRegions", "", "Lorg/maplibre/android/offline/OfflineRegion;", "([Lorg/maplibre/android/offline/OfflineRegion;)V", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineManager$mergeOfflineDatabaseFiles$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02141 implements MergeOfflineRegionsCallback {
        final /* synthetic */ MergeOfflineRegionsCallback $callback;
        final /* synthetic */ File $file;
        final /* synthetic */ boolean $isTemporaryFile;
        final /* synthetic */ OfflineManager this$0;

        C02141(boolean z, File file, OfflineManager offlineManager, MergeOfflineRegionsCallback mergeOfflineRegionsCallback) {
            this.$isTemporaryFile = z;
            this.$file = file;
            this.this$0 = offlineManager;
            this.$callback = mergeOfflineRegionsCallback;
        }

        @Override // org.maplibre.android.offline.OfflineManager.MergeOfflineRegionsCallback
        public void onMerge(final OfflineRegion[] offlineRegions) {
            if (this.$isTemporaryFile) {
                this.$file.delete();
            }
            Handler handler = this.this$0.handler;
            final OfflineManager offlineManager = this.this$0;
            final MergeOfflineRegionsCallback mergeOfflineRegionsCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$mergeOfflineDatabaseFiles$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02141.onMerge$lambda$0(offlineManager, mergeOfflineRegionsCallback, offlineRegions);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onMerge$lambda$0(OfflineManager offlineManager, MergeOfflineRegionsCallback mergeOfflineRegionsCallback, OfflineRegion[] offlineRegionArr) {
            offlineManager.fileSource.deactivate();
            mergeOfflineRegionsCallback.onMerge(offlineRegionArr);
        }

        @Override // org.maplibre.android.offline.OfflineManager.MergeOfflineRegionsCallback
        public void onError(final String error) {
            Intrinsics.checkNotNullParameter(error, "error");
            if (this.$isTemporaryFile) {
                this.$file.delete();
            }
            Handler handler = this.this$0.handler;
            final OfflineManager offlineManager = this.this$0;
            final MergeOfflineRegionsCallback mergeOfflineRegionsCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$mergeOfflineDatabaseFiles$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02141.onError$lambda$1(offlineManager, mergeOfflineRegionsCallback, error);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$1(OfflineManager offlineManager, MergeOfflineRegionsCallback mergeOfflineRegionsCallback, String str) {
            offlineManager.fileSource.deactivate();
            mergeOfflineRegionsCallback.onError(str);
        }
    }

    public final void createOfflineRegion(OfflineRegionDefinition definition, byte[] metadata, CreateOfflineRegionCallback callback) {
        Intrinsics.checkNotNullParameter(definition, "definition");
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (!isValidOfflineRegionDefinition(definition)) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = this.context.getString(R.string.maplibre_offline_error_region_definition_invalid);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String str = String.format(string, Arrays.copyOf(new Object[]{definition.getBounds()}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            callback.onError(str);
            return;
        }
        ConnectivityReceiver.instance(this.context).activate();
        FileSource.getInstance(this.context).activate();
        createOfflineRegion(this.fileSource, definition, metadata, new C02101(callback));
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"org/maplibre/android/offline/OfflineManager$createOfflineRegion$1", "Lorg/maplibre/android/offline/OfflineManager$CreateOfflineRegionCallback;", "onCreate", "", "offlineRegion", "Lorg/maplibre/android/offline/OfflineRegion;", "onError", "error", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* renamed from: org.maplibre.android.offline.OfflineManager$createOfflineRegion$1, reason: invalid class name and case insensitive filesystem */
    public static final class C02101 implements CreateOfflineRegionCallback {
        final /* synthetic */ CreateOfflineRegionCallback $callback;

        C02101(CreateOfflineRegionCallback createOfflineRegionCallback) {
            this.$callback = createOfflineRegionCallback;
        }

        @Override // org.maplibre.android.offline.OfflineManager.CreateOfflineRegionCallback
        public void onCreate(final OfflineRegion offlineRegion) {
            Intrinsics.checkNotNullParameter(offlineRegion, "offlineRegion");
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final CreateOfflineRegionCallback createOfflineRegionCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$createOfflineRegion$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02101.onCreate$lambda$0(offlineManager, createOfflineRegionCallback, offlineRegion);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onCreate$lambda$0(OfflineManager offlineManager, CreateOfflineRegionCallback createOfflineRegionCallback, OfflineRegion offlineRegion) {
            ConnectivityReceiver.instance(offlineManager.context).deactivate();
            FileSource.getInstance(offlineManager.context).deactivate();
            createOfflineRegionCallback.onCreate(offlineRegion);
        }

        @Override // org.maplibre.android.offline.OfflineManager.CreateOfflineRegionCallback
        public void onError(final String error) {
            Intrinsics.checkNotNullParameter(error, "error");
            Handler handler = OfflineManager.this.handler;
            final OfflineManager offlineManager = OfflineManager.this;
            final CreateOfflineRegionCallback createOfflineRegionCallback = this.$callback;
            handler.post(new Runnable() { // from class: org.maplibre.android.offline.OfflineManager$createOfflineRegion$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OfflineManager.C02101.onError$lambda$1(offlineManager, createOfflineRegionCallback, error);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onError$lambda$1(OfflineManager offlineManager, CreateOfflineRegionCallback createOfflineRegionCallback, String str) {
            ConnectivityReceiver.instance(offlineManager.context).deactivate();
            FileSource.getInstance(offlineManager.context).deactivate();
            createOfflineRegionCallback.onError(str);
        }
    }

    private final boolean isValidOfflineRegionDefinition(OfflineRegionDefinition definition) {
        LatLngBounds latLngBoundsWorld = LatLngBounds.INSTANCE.world();
        LatLngBounds bounds = definition.getBounds();
        Intrinsics.checkNotNull(bounds);
        return latLngBoundsWorld.contains(bounds);
    }

    /* compiled from: OfflineManager.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0007J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/maplibre/android/offline/OfflineManager$Companion;", "", "<init>", "()V", "TAG", "", "instance", "Lorg/maplibre/android/offline/OfflineManager;", "getInstance", "context", "Landroid/content/Context;", "copyTempDatabaseFile", "", "sourceFile", "Ljava/io/File;", "destFile", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final synchronized OfflineManager getInstance(Context context) {
            OfflineManager offlineManager;
            Intrinsics.checkNotNullParameter(context, "context");
            if (OfflineManager.instance == null) {
                OfflineManager.instance = new OfflineManager(context, null);
            }
            offlineManager = OfflineManager.instance;
            Intrinsics.checkNotNull(offlineManager);
            return offlineManager;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0078  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void copyTempDatabaseFile(java.io.File r10, java.io.File r11) throws java.lang.Throwable {
            /*
                r9 = this;
                boolean r0 = r11.exists()
                if (r0 != 0) goto L15
                boolean r0 = r11.createNewFile()
                if (r0 == 0) goto Ld
                goto L15
            Ld:
                java.io.IOException r10 = new java.io.IOException
                java.lang.String r11 = "Unable to copy database file for merge."
                r10.<init>(r11)
                throw r10
            L15:
                r0 = 0
                java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
                r1.<init>(r10)     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
                java.nio.channels.FileChannel r10 = r1.getChannel()     // Catch: java.lang.Throwable -> L4a java.io.IOException -> L4d
                java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45
                r1.<init>(r11)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45
                java.nio.channels.FileChannel r0 = r1.getChannel()     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45
                r3 = r10
                java.nio.channels.ReadableByteChannel r3 = (java.nio.channels.ReadableByteChannel) r3     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45
                r4 = 0
                long r6 = r10.size()     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45
                r2 = r0
                r2.transferFrom(r3, r4, r6)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45
                if (r10 == 0) goto L3a
                r10.close()
            L3a:
                if (r0 == 0) goto L3f
                r0.close()
            L3f:
                return
            L40:
                r11 = move-exception
                r8 = r0
                r0 = r10
                r10 = r8
                goto L71
            L45:
                r11 = move-exception
                r8 = r0
                r0 = r10
                r10 = r8
                goto L4f
            L4a:
                r11 = move-exception
                r10 = r0
                goto L71
            L4d:
                r11 = move-exception
                r10 = r0
            L4f:
                java.io.IOException r1 = new java.io.IOException     // Catch: java.lang.Throwable -> L70
                kotlin.jvm.internal.StringCompanionObject r2 = kotlin.jvm.internal.StringCompanionObject.INSTANCE     // Catch: java.lang.Throwable -> L70
                java.lang.String r2 = "Unable to copy database file for merge. %s"
                r3 = 1
                java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L70
                java.lang.String r11 = r11.getMessage()     // Catch: java.lang.Throwable -> L70
                r5 = 0
                r4[r5] = r11     // Catch: java.lang.Throwable -> L70
                java.lang.Object[] r11 = java.util.Arrays.copyOf(r4, r3)     // Catch: java.lang.Throwable -> L70
                java.lang.String r11 = java.lang.String.format(r2, r11)     // Catch: java.lang.Throwable -> L70
                java.lang.String r2 = "format(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r2)     // Catch: java.lang.Throwable -> L70
                r1.<init>(r11)     // Catch: java.lang.Throwable -> L70
                throw r1     // Catch: java.lang.Throwable -> L70
            L70:
                r11 = move-exception
            L71:
                if (r0 == 0) goto L76
                r0.close()
            L76:
                if (r10 == 0) goto L7b
                r10.close()
            L7b:
                throw r11
            */
            throw new UnsupportedOperationException("Method not decompiled: org.maplibre.android.offline.OfflineManager.Companion.copyTempDatabaseFile(java.io.File, java.io.File):void");
        }
    }

    static {
        LibraryLoader.load();
    }

    @JvmStatic
    public static final synchronized OfflineManager getInstance(Context context) {
        return INSTANCE.getInstance(context);
    }
}

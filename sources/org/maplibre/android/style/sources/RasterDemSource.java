package org.maplibre.android.style.sources;

import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import java.net.URI;
import java.net.URL;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RasterDemSource.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u0011\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u001b\b\u0017\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0004\u0010\nB\u001b\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\u0004\u0010\rB\u001d\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0004\u0010\u000eB%\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0004\u0010\u0011B\u001b\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0004\u0010\u0014B#\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0004\u0010\u0015J%\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u00072\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u000f\u001a\u00020\u0010H\u0085 J\t\u0010\u001e\u001a\u00020\u001aH\u0085 J\t\u0010\u001f\u001a\u00020\u0007H\u0083 R\u0013\u0010\b\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0017¨\u0006!"}, d2 = {"Lorg/maplibre/android/style/sources/RasterDemSource;", "Lorg/maplibre/android/style/sources/Source;", "nativePtr", "", "<init>", "(J)V", "id", "", "url", "Ljava/net/URL;", "(Ljava/lang/String;Ljava/net/URL;)V", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "Ljava/net/URI;", "(Ljava/lang/String;Ljava/net/URI;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "tileSize", "", "(Ljava/lang/String;Ljava/lang/String;I)V", "tileSet", "Lorg/maplibre/android/style/sources/TileSet;", "(Ljava/lang/String;Lorg/maplibre/android/style/sources/TileSet;)V", "(Ljava/lang/String;Lorg/maplibre/android/style/sources/TileSet;I)V", "getUrl", "()Ljava/lang/String;", "getUri", "initialize", "", "layerId", "payload", "", "finalize", "nativeGetUrl", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RasterDemSource extends Source {
    public static final int DEFAULT_TILE_SIZE = 512;

    private final native String nativeGetUrl();

    protected final native void finalize() throws Throwable;

    protected final native void initialize(String layerId, Object payload, int tileSize);

    public RasterDemSource(long j) {
        super(j);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "use {@link #RasterDemSource(String, URI)} instead")
    public RasterDemSource(String str, URL url) {
        this(str, url.toExternalForm());
        Intrinsics.checkNotNullParameter(url, "url");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RasterDemSource(String str, URI uri) {
        this(str, uri.toString());
        Intrinsics.checkNotNullParameter(uri, "uri");
    }

    public RasterDemSource(String str, String str2) {
        initialize(str, str2, 512);
    }

    public RasterDemSource(String str, String str2, int i) {
        initialize(str, str2, i);
    }

    public RasterDemSource(String str, TileSet tileSet) {
        Intrinsics.checkNotNullParameter(tileSet, "tileSet");
        initialize(str, tileSet.toValueObject(), 512);
    }

    public RasterDemSource(String str, TileSet tileSet, int i) {
        Intrinsics.checkNotNullParameter(tileSet, "tileSet");
        initialize(str, tileSet.toValueObject(), i);
    }

    @Deprecated(message = "use {@link #getUri()} instead")
    public final String getUrl() {
        checkThread();
        return nativeGetUrl();
    }

    public final String getUri() {
        checkThread();
        return nativeGetUrl();
    }
}

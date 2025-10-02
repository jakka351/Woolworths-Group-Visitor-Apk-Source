package org.maplibre.android.style.sources;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import java.net.URI;
import java.net.URL;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.MapLibre;
import org.maplibre.android.geometry.LatLngQuad;
import org.maplibre.android.utils.BitmapUtils;

/* compiled from: ImageSource.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0011\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B%\b\u0017\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\u0004\u0010\fB%\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u0004\u0010\u000fB%\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0004\u0010\u0012B'\b\u0016\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0001\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0004\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\n\u001a\u0004\u0018\u00010\u0007H\u0007J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u0018\u001a\u00020\u00172\b\u0010\r\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0019\u001a\u00020\u00172\b\b\u0001\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u001d\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\tJ\u001d\u0010\u001f\u001a\u00020\u00172\b\u0010 \u001a\u0004\u0018\u00010\u00072\b\u0010!\u001a\u0004\u0018\u00010\tH\u0085 J\u0013\u0010\"\u001a\u00020\u00172\b\u0010\n\u001a\u0004\u0018\u00010\u0007H\u0085 J\t\u0010#\u001a\u00020\u0007H\u0085 J\u0013\u0010$\u001a\u00020\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0085 J\u0013\u0010%\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\tH\u0085 J\t\u0010&\u001a\u00020\u0017H\u0085 R\u0013\u0010\n\u001a\u0004\u0018\u00010\u00078G¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\r\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001b¨\u0006'"}, d2 = {"Lorg/maplibre/android/style/sources/ImageSource;", "Lorg/maplibre/android/style/sources/Source;", "nativePtr", "", "<init>", "(J)V", "id", "", "coordinates", "Lorg/maplibre/android/geometry/LatLngQuad;", "url", "Ljava/net/URL;", "(Ljava/lang/String;Lorg/maplibre/android/geometry/LatLngQuad;Ljava/net/URL;)V", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "Ljava/net/URI;", "(Ljava/lang/String;Lorg/maplibre/android/geometry/LatLngQuad;Ljava/net/URI;)V", "bitmap", "Landroid/graphics/Bitmap;", "(Ljava/lang/String;Lorg/maplibre/android/geometry/LatLngQuad;Landroid/graphics/Bitmap;)V", "resourceId", "", "(Ljava/lang/String;Lorg/maplibre/android/geometry/LatLngQuad;I)V", "setUrl", "", "setUri", "setImage", "getUrl", "()Ljava/lang/String;", "getUri", "setCoordinates", "latLngQuad", "initialize", "layerId", "payload", "nativeSetUrl", "nativeGetUrl", "nativeSetImage", "nativeSetCoordinates", "finalize", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageSource extends Source {
    protected final native void finalize() throws Throwable;

    protected final native void initialize(String layerId, LatLngQuad payload);

    protected final native String nativeGetUrl();

    protected final native void nativeSetCoordinates(LatLngQuad latLngQuad);

    protected final native void nativeSetImage(Bitmap bitmap);

    protected final native void nativeSetUrl(String url);

    public ImageSource(long j) {
        super(j);
    }

    @Deprecated(message = "use {@link ImageSource#ImageSource(String, LatLngQuad, URI)} instead")
    public ImageSource(String str, LatLngQuad latLngQuad, URL url) {
        Intrinsics.checkNotNullParameter(url, "url");
        initialize(str, latLngQuad);
        setUrl(url);
    }

    public ImageSource(String str, LatLngQuad latLngQuad, URI uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        initialize(str, latLngQuad);
        setUri(uri);
    }

    public ImageSource(String str, LatLngQuad latLngQuad, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        initialize(str, latLngQuad);
        setImage(bitmap);
    }

    public ImageSource(String str, LatLngQuad latLngQuad, int i) throws IllegalArgumentException {
        initialize(str, latLngQuad);
        setImage(i);
    }

    @Deprecated(message = "use {@link #setUri(URI)} instead")
    public final void setUrl(URL url) {
        Intrinsics.checkNotNullParameter(url, "url");
        setUrl(url.toExternalForm());
    }

    @Deprecated(message = "use {@link #setUri(String)} instead")
    public final void setUrl(String url) {
        checkThread();
        nativeSetUrl(url);
    }

    public final void setUri(URI uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        checkThread();
        nativeSetUrl(uri.toString());
    }

    public final void setUri(String uri) {
        checkThread();
        nativeSetUrl(uri);
    }

    public final void setImage(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        checkThread();
        nativeSetImage(bitmap);
    }

    public final void setImage(int resourceId) throws IllegalArgumentException {
        checkThread();
        Context applicationContext = MapLibre.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        Drawable drawableFromRes = BitmapUtils.getDrawableFromRes(applicationContext, resourceId);
        if (drawableFromRes instanceof BitmapDrawable) {
            nativeSetImage(((BitmapDrawable) drawableFromRes).getBitmap());
            return;
        }
        throw new IllegalArgumentException("Failed to decode image. The resource provided must be a Bitmap.");
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

    public final void setCoordinates(LatLngQuad latLngQuad) {
        checkThread();
        nativeSetCoordinates(latLngQuad);
    }
}

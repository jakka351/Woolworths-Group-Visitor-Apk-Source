package org.maplibre.android.annotations;

import android.os.Parcelable;
import org.maplibre.android.annotations.BaseMarkerOptions;
import org.maplibre.android.annotations.Marker;
import org.maplibre.android.geometry.LatLng;

@Deprecated
/* loaded from: classes2.dex */
public abstract class BaseMarkerOptions<U extends Marker, T extends BaseMarkerOptions<U, T>> implements Parcelable {
    protected Icon icon;
    protected LatLng position;
    protected String snippet;
    protected String title;

    public abstract U getMarker();

    public abstract T getThis();

    public T position(LatLng latLng) {
        this.position = latLng;
        return (T) getThis();
    }

    public T snippet(String str) {
        this.snippet = str;
        return (T) getThis();
    }

    public T title(String str) {
        this.title = str;
        return (T) getThis();
    }

    public T icon(Icon icon) {
        this.icon = icon;
        return (T) getThis();
    }

    public T setIcon(Icon icon) {
        return (T) icon(icon);
    }

    public T setPosition(LatLng latLng) {
        return (T) position(latLng);
    }

    public T setSnippet(String str) {
        return (T) snippet(str);
    }

    public T setTitle(String str) {
        return (T) title(str);
    }
}

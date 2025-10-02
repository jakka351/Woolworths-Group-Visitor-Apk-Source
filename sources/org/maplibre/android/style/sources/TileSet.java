package org.maplibre.android.style.sources;

import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.geometry.LatLngBounds;

/* compiled from: TileSet.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b#\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005\"\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u00105\u001a\u0002062\u0012\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005\"\u00020\u0003¢\u0006\u0002\u00107J\u001f\u00108\u001a\u0002062\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0005\"\u00020\u0003¢\u0006\u0002\u00107J\u0006\u00109\u001a\u00020)J\u000e\u0010:\u001a\u0002062\u0006\u0010(\u001a\u00020)J\u0006\u0010;\u001a\u00020)J\u000e\u0010<\u001a\u0002062\u0006\u0010+\u001a\u00020)J\u0014\u0010=\u001a\u0002062\f\b\u0001\u0010,\u001a\u00020>\"\u00020)J&\u0010=\u001a\u0002062\u0006\u0010?\u001a\u00020)2\u0006\u0010@\u001a\u00020)2\u0006\u0010A\u001a\u00020)2\u0006\u0010B\u001a\u00020)J\u001d\u0010=\u001a\u0002062\u000e\b\u0001\u0010,\u001a\b\u0012\u0004\u0012\u00020)0\u0005H\u0007¢\u0006\u0002\u0010CJ\u000e\u0010=\u001a\u0002062\u0006\u0010,\u001a\u00020DJ\u0016\u0010E\u001a\u0002062\f\b\u0001\u00100\u001a\u00020>\"\u00020)H\u0007J\u000e\u0010E\u001a\u0002062\u0006\u00100\u001a\u00020FJ\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010HR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\t\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\rR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\t\"\u0004\b\u0013\u0010\rR\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\t\"\u0004\b\u0016\u0010\rR\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\t\"\u0004\b\u0019\u0010\rR\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\t\"\u0004\b\u001c\u0010\rR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\t\"\u0004\b\u001f\u0010\rR\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b \u0010!R0\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b%\u0010!R0\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\"\u001a\u0004\b'\u0010!R\u0016\u0010(\u001a\u0004\u0018\u00010)8\u0006@\u0006X\u0087\u000e¢\u0006\u0004\n\u0002\u0010*R\u0016\u0010+\u001a\u0004\u0018\u00010)8\u0006@\u0006X\u0087\u000e¢\u0006\u0004\n\u0002\u0010*R0\u0010,\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010\u00052\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\n\n\u0002\u0010/\u001a\u0004\b-\u0010.R0\u00100\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010\u00052\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\n\n\u0002\u0010/\u001a\u0004\b1\u0010.R\u001c\u00102\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\t\"\u0004\b4\u0010\r¨\u0006I"}, d2 = {"Lorg/maplibre/android/style/sources/TileSet;", "", "tilejson", "", "tiles", "", "<init>", "(Ljava/lang/String;[Ljava/lang/String;)V", "getTilejson", "()Ljava/lang/String;", "name", "getName", "setName", "(Ljava/lang/String;)V", "description", "getDescription", "setDescription", "version", "getVersion", "setVersion", "attribution", "getAttribution", "setAttribution", "template", "getTemplate", "setTemplate", "legend", "getLegend", "setLegend", "scheme", "getScheme", "setScheme", "getTiles", "()[Ljava/lang/String;", "[Ljava/lang/String;", "value", "grids", "getGrids", "data", "getData", "minZoom", "", "Ljava/lang/Float;", "maxZoom", "bounds", "getBounds", "()[Ljava/lang/Float;", "[Ljava/lang/Float;", "center", "getCenter", RRWebVideoEvent.JsonKeys.ENCODING, "getEncoding", "setEncoding", "setGrids", "", "([Ljava/lang/String;)V", "setData", "getMinZoom", "setMinZoom", "getMaxZoom", "setMaxZoom", "setBounds", "", "left", "bottom", "right", "top", "([Ljava/lang/Float;)V", "Lorg/maplibre/android/geometry/LatLngBounds;", "setCenter", "Lorg/maplibre/android/geometry/LatLng;", "toValueObject", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TileSet {
    private String attribution;
    private Float[] bounds;
    private Float[] center;
    private String[] data;
    private String description;
    private String encoding;
    private String[] grids;
    private String legend;
    public Float maxZoom;
    public Float minZoom;
    private String name;
    private String scheme;
    private String template;
    private final String tilejson;
    private final String[] tiles;
    private String version;

    public TileSet(String tilejson, String... tiles) {
        Intrinsics.checkNotNullParameter(tilejson, "tilejson");
        Intrinsics.checkNotNullParameter(tiles, "tiles");
        this.tilejson = tilejson;
        this.tiles = (String[]) Arrays.copyOf(tiles, tiles.length);
    }

    public final String getTilejson() {
        return this.tilejson;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public final String getVersion() {
        return this.version;
    }

    public final void setVersion(String str) {
        this.version = str;
    }

    public final String getAttribution() {
        return this.attribution;
    }

    public final void setAttribution(String str) {
        this.attribution = str;
    }

    public final String getTemplate() {
        return this.template;
    }

    public final void setTemplate(String str) {
        this.template = str;
    }

    public final String getLegend() {
        return this.legend;
    }

    public final void setLegend(String str) {
        this.legend = str;
    }

    public final String getScheme() {
        return this.scheme;
    }

    public final void setScheme(String str) {
        this.scheme = str;
    }

    public final String[] getTiles() {
        return this.tiles;
    }

    public final String[] getGrids() {
        return this.grids;
    }

    public final String[] getData() {
        return this.data;
    }

    public final Float[] getBounds() {
        return this.bounds;
    }

    public final Float[] getCenter() {
        return this.center;
    }

    public final String getEncoding() {
        return this.encoding;
    }

    public final void setEncoding(String str) {
        this.encoding = str;
    }

    public final void setGrids(String... grids) {
        Intrinsics.checkNotNullParameter(grids, "grids");
        this.grids = (String[]) Arrays.copyOf(grids, grids.length);
    }

    public final void setData(String... data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = (String[]) Arrays.copyOf(data, data.length);
    }

    public final float getMinZoom() {
        Float f = this.minZoom;
        Intrinsics.checkNotNull(f);
        return f.floatValue();
    }

    public final void setMinZoom(float minZoom) {
        this.minZoom = Float.valueOf(minZoom);
    }

    public final float getMaxZoom() {
        Float f = this.maxZoom;
        Intrinsics.checkNotNull(f);
        return f.floatValue();
    }

    public final void setMaxZoom(float maxZoom) {
        this.maxZoom = Float.valueOf(maxZoom);
    }

    public final void setBounds(float... bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        this.bounds = ArraysKt.toTypedArray(bounds);
    }

    public final void setBounds(float left, float bottom, float right, float top) {
        setBounds(left, bottom, right, top);
    }

    @Deprecated(message = "Not strongly typed", replaceWith = @ReplaceWith(expression = "setBounds(bounds: LatLngBounds", imports = {}))
    public final void setBounds(Float[] bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        this.bounds = bounds;
    }

    public final void setBounds(LatLngBounds bounds) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        setBounds((float) bounds.longitudeWest, (float) bounds.latitudeSouth, (float) bounds.longitudeEast, (float) bounds.latitudeNorth);
    }

    @Deprecated(message = "This function is not type safe", replaceWith = @ReplaceWith(expression = "setCenter(center:LatLng)", imports = {}))
    public final void setCenter(float... center) {
        Intrinsics.checkNotNullParameter(center, "center");
        setCenter(new LatLng(center[1], center[0]));
    }

    public final void setCenter(LatLng center) {
        Intrinsics.checkNotNullParameter(center, "center");
        this.center = new Float[]{Float.valueOf((float) center.getLongitude()), Float.valueOf((float) center.getLatitude())};
    }

    public final Map<String, Object> toValueObject() {
        HashMap map = new HashMap();
        map.put("tilejson", this.tilejson);
        map.put("tiles", this.tiles);
        String str = this.name;
        if (str != null) {
            Intrinsics.checkNotNull(str);
            map.put("name", str);
        }
        String str2 = this.description;
        if (str2 != null) {
            Intrinsics.checkNotNull(str2);
            map.put("description", str2);
        }
        String str3 = this.version;
        if (str3 != null) {
            Intrinsics.checkNotNull(str3);
            map.put("version", str3);
        }
        String str4 = this.attribution;
        if (str4 != null) {
            Intrinsics.checkNotNull(str4);
            map.put("attribution", str4);
        }
        String str5 = this.template;
        if (str5 != null) {
            Intrinsics.checkNotNull(str5);
            map.put("template", str5);
        }
        String str6 = this.legend;
        if (str6 != null) {
            Intrinsics.checkNotNull(str6);
            map.put("legend", str6);
        }
        String str7 = this.scheme;
        if (str7 != null) {
            Intrinsics.checkNotNull(str7);
            map.put("scheme", str7);
        }
        String[] strArr = this.grids;
        if (strArr != null) {
            Intrinsics.checkNotNull(strArr);
            map.put("grids", strArr);
        }
        String[] strArr2 = this.data;
        if (strArr2 != null) {
            Intrinsics.checkNotNull(strArr2);
            map.put("data", strArr2);
        }
        Float f = this.minZoom;
        if (f != null) {
            Intrinsics.checkNotNull(f);
            map.put("minzoom", f);
        }
        Float f2 = this.maxZoom;
        if (f2 != null) {
            Intrinsics.checkNotNull(f2);
            map.put("maxzoom", f2);
        }
        Float[] fArr = this.bounds;
        if (fArr != null) {
            Intrinsics.checkNotNull(fArr);
            map.put("bounds", fArr);
        }
        Float[] fArr2 = this.center;
        if (fArr2 != null) {
            Intrinsics.checkNotNull(fArr2);
            map.put("center", fArr2);
        }
        String str8 = this.encoding;
        if (str8 != null) {
            Intrinsics.checkNotNull(str8);
            map.put(RRWebVideoEvent.JsonKeys.ENCODING, str8);
        }
        return map;
    }
}

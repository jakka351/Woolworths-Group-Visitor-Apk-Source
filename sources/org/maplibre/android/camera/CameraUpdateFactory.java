package org.maplibre.android.camera;

import android.graphics.Point;
import android.graphics.PointF;
import com.facebook.react.uimanager.ViewProps;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.android.maps.MapLibreMap;
import timber.log.Timber;

/* compiled from: CameraUpdateFactory.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0013\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0003)*+B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0007J\u0018\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J(\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J0\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fH\u0007J@\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fH\u0007J\u0018\u0010\u0017\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0011H\u0007J0\u0010\u0019\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0011H\u0007J\u0018\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!H\u0007J\u0010\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0011H\u0007J\b\u0010\"\u001a\u00020\u0005H\u0007J\b\u0010#\u001a\u00020\u0005H\u0007J\u0010\u0010$\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0011H\u0007J\u0010\u0010%\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0010\u0010&\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0011H\u0007J\u0012\u0010'\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010(H\u0007J(\u0010'\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0011H\u0007¨\u0006,"}, d2 = {"Lorg/maplibre/android/camera/CameraUpdateFactory;", "", "<init>", "()V", "newCameraPosition", "Lorg/maplibre/android/camera/CameraUpdate;", "cameraPosition", "Lorg/maplibre/android/camera/CameraPosition;", "newLatLng", "latLng", "Lorg/maplibre/android/geometry/LatLng;", "newLatLngBounds", "bounds", "Lorg/maplibre/android/geometry/LatLngBounds;", ViewProps.PADDING, "", "bearing", "", "tilt", ViewProps.PADDING_LEFT, ViewProps.PADDING_TOP, ViewProps.PADDING_RIGHT, ViewProps.PADDING_BOTTOM, "newLatLngZoom", "zoom", "newLatLngPadding", "left", "top", "right", "bottom", "zoomBy", "amount", "focus", "Landroid/graphics/Point;", "zoomIn", "zoomOut", "zoomTo", "bearingTo", "tiltTo", "paddingTo", "", "CameraPositionUpdate", "CameraBoundsUpdate", "ZoomUpdate", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraUpdateFactory {
    public static final CameraUpdateFactory INSTANCE = new CameraUpdateFactory();

    private CameraUpdateFactory() {
    }

    @JvmStatic
    public static final CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        Intrinsics.checkNotNullParameter(cameraPosition, "cameraPosition");
        return new CameraPositionUpdate(cameraPosition.bearing, cameraPosition.target, cameraPosition.tilt, cameraPosition.zoom, cameraPosition.padding);
    }

    @JvmStatic
    public static final CameraUpdate newLatLng(LatLng latLng) {
        Intrinsics.checkNotNullParameter(latLng, "latLng");
        return new CameraPositionUpdate(-1.0d, latLng, -1.0d, -1.0d, null);
    }

    @JvmStatic
    public static final CameraUpdate newLatLngBounds(LatLngBounds bounds, int padding) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        return newLatLngBounds(bounds, padding, padding, padding, padding);
    }

    @JvmStatic
    public static final CameraUpdate newLatLngBounds(LatLngBounds bounds, double bearing, double tilt, int padding) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        return newLatLngBounds(bounds, bearing, tilt, padding, padding, padding, padding);
    }

    @JvmStatic
    public static final CameraUpdate newLatLngBounds(LatLngBounds bounds, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        return new CameraBoundsUpdate(bounds, null, null, paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    @JvmStatic
    public static final CameraUpdate newLatLngBounds(LatLngBounds bounds, double bearing, double tilt, int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
        Intrinsics.checkNotNullParameter(bounds, "bounds");
        return new CameraBoundsUpdate(bounds, Double.valueOf(bearing), Double.valueOf(tilt), paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    @JvmStatic
    public static final CameraUpdate newLatLngZoom(LatLng latLng, double zoom) {
        Intrinsics.checkNotNullParameter(latLng, "latLng");
        return new CameraPositionUpdate(-1.0d, latLng, -1.0d, zoom, null);
    }

    @JvmStatic
    public static final CameraUpdate newLatLngPadding(LatLng latLng, double left, double top, double right, double bottom) {
        Intrinsics.checkNotNullParameter(latLng, "latLng");
        return new CameraPositionUpdate(-1.0d, latLng, -1.0d, -1.0d, new double[]{left, top, right, bottom});
    }

    @JvmStatic
    public static final CameraUpdate zoomBy(double amount, Point focus) {
        Intrinsics.checkNotNullParameter(focus, "focus");
        return new ZoomUpdate(amount, focus.x, focus.y);
    }

    @JvmStatic
    public static final CameraUpdate zoomBy(double amount) {
        return new ZoomUpdate(2, amount);
    }

    @JvmStatic
    public static final CameraUpdate zoomIn() {
        return new ZoomUpdate(0);
    }

    @JvmStatic
    public static final CameraUpdate zoomOut() {
        return new ZoomUpdate(1);
    }

    @JvmStatic
    public static final CameraUpdate zoomTo(double zoom) {
        return new ZoomUpdate(3, zoom);
    }

    @JvmStatic
    public static final CameraUpdate bearingTo(double bearing) {
        return new CameraPositionUpdate(bearing, null, -1.0d, -1.0d, null);
    }

    @JvmStatic
    public static final CameraUpdate tiltTo(double tilt) {
        return new CameraPositionUpdate(-1.0d, null, tilt, -1.0d, null);
    }

    @JvmStatic
    public static final CameraUpdate paddingTo(double[] padding) {
        return new CameraPositionUpdate(-1.0d, null, -1.0d, -1.0d, padding);
    }

    @JvmStatic
    public static final CameraUpdate paddingTo(double left, double top, double right, double bottom) {
        return paddingTo(new double[]{left, top, right, bottom});
    }

    /* compiled from: CameraUpdateFactory.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0096\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lorg/maplibre/android/camera/CameraUpdateFactory$CameraPositionUpdate;", "Lorg/maplibre/android/camera/CameraUpdate;", "bearing", "", "target", "Lorg/maplibre/android/geometry/LatLng;", "tilt", "zoom", ViewProps.PADDING, "", "<init>", "(DLorg/maplibre/android/geometry/LatLng;DD[D)V", "getBearing", "()D", "getTarget", "()Lorg/maplibre/android/geometry/LatLng;", "getTilt", "getZoom", "getPadding", "()[D", "getCameraPosition", "Lorg/maplibre/android/camera/CameraPosition;", "maplibreMap", "Lorg/maplibre/android/maps/MapLibreMap;", "equals", "", "other", "", "hashCode", "", "toString", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class CameraPositionUpdate implements CameraUpdate {
        private final double bearing;
        private final double[] padding;
        private final LatLng target;
        private final double tilt;
        private final double zoom;

        public CameraPositionUpdate(double d, LatLng latLng, double d2, double d3, double[] dArr) {
            this.bearing = d;
            this.target = latLng;
            this.tilt = d2;
            this.zoom = d3;
            this.padding = dArr;
        }

        public final double getBearing() {
            return this.bearing;
        }

        public final double[] getPadding() {
            return this.padding;
        }

        public final LatLng getTarget() {
            return this.target;
        }

        public final double getTilt() {
            return this.tilt;
        }

        public final double getZoom() {
            return this.zoom;
        }

        @Override // org.maplibre.android.camera.CameraUpdate
        public CameraPosition getCameraPosition(MapLibreMap maplibreMap) {
            Intrinsics.checkNotNullParameter(maplibreMap, "maplibreMap");
            if (this.target == null) {
                CameraPosition cameraPosition = maplibreMap.getCameraPosition();
                Intrinsics.checkNotNullExpressionValue(cameraPosition, "getCameraPosition(...)");
                return new CameraPosition.Builder(this).target(cameraPosition.target).build();
            }
            return new CameraPosition.Builder(this).build();
        }

        public boolean equals(Object other) {
            boolean z = true;
            if (this == other) {
                return true;
            }
            if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
                return false;
            }
            CameraPositionUpdate cameraPositionUpdate = (CameraPositionUpdate) other;
            if (Double.compare(cameraPositionUpdate.bearing, this.bearing) != 0 || Double.compare(cameraPositionUpdate.tilt, this.tilt) != 0 || Double.compare(cameraPositionUpdate.zoom, this.zoom) != 0) {
                return false;
            }
            LatLng latLng = this.target;
            if (latLng == null ? cameraPositionUpdate.target == null : Intrinsics.areEqual(latLng, cameraPositionUpdate.target)) {
                z = false;
            }
            if (z) {
                return false;
            }
            return Arrays.equals(this.padding, cameraPositionUpdate.padding);
        }

        public int hashCode() {
            long jDoubleToLongBits = Double.doubleToLongBits(this.bearing);
            int i = ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) * 31;
            LatLng latLng = this.target;
            int iHashCode = latLng != null ? latLng.hashCode() : 0;
            long jDoubleToLongBits2 = Double.doubleToLongBits(this.tilt);
            int i2 = ((i + iHashCode) * 31) + ((int) (jDoubleToLongBits2 ^ (jDoubleToLongBits2 >>> 32)));
            long jDoubleToLongBits3 = Double.doubleToLongBits(this.zoom);
            return (((i2 * 31) + ((int) ((jDoubleToLongBits3 >>> 32) ^ jDoubleToLongBits3))) * 31) + Arrays.hashCode(this.padding);
        }

        public String toString() {
            return "CameraPositionUpdate{bearing=" + this.bearing + ", target=" + this.target + ", tilt=" + this.tilt + ", zoom=" + this.zoom + ", padding=" + Arrays.toString(this.padding) + "}";
        }
    }

    /* compiled from: CameraUpdateFactory.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nBE\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f¢\u0006\u0004\b\t\u0010\u0010J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0096\u0002J\b\u0010\u001e\u001a\u00020\fH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006!"}, d2 = {"Lorg/maplibre/android/camera/CameraUpdateFactory$CameraBoundsUpdate;", "Lorg/maplibre/android/camera/CameraUpdate;", "bounds", "Lorg/maplibre/android/geometry/LatLngBounds;", "bearing", "", "tilt", ViewProps.PADDING, "", "<init>", "(Lorg/maplibre/android/geometry/LatLngBounds;Ljava/lang/Double;Ljava/lang/Double;[I)V", ViewProps.PADDING_LEFT, "", ViewProps.PADDING_TOP, ViewProps.PADDING_RIGHT, ViewProps.PADDING_BOTTOM, "(Lorg/maplibre/android/geometry/LatLngBounds;Ljava/lang/Double;Ljava/lang/Double;IIII)V", "getBounds", "()Lorg/maplibre/android/geometry/LatLngBounds;", "Ljava/lang/Double;", "getPadding", "()[I", "getCameraPosition", "Lorg/maplibre/android/camera/CameraPosition;", "maplibreMap", "Lorg/maplibre/android/maps/MapLibreMap;", "equals", "", "other", "", "hashCode", "toString", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class CameraBoundsUpdate implements CameraUpdate {
        private final Double bearing;
        private final LatLngBounds bounds;
        private final int[] padding;
        private final Double tilt;

        public CameraBoundsUpdate(LatLngBounds bounds, Double d, Double d2, int[] padding) {
            Intrinsics.checkNotNullParameter(bounds, "bounds");
            Intrinsics.checkNotNullParameter(padding, "padding");
            this.bounds = bounds;
            this.bearing = d;
            this.tilt = d2;
            this.padding = padding;
        }

        public final LatLngBounds getBounds() {
            return this.bounds;
        }

        public final int[] getPadding() {
            return this.padding;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public CameraBoundsUpdate(LatLngBounds bounds, Double d, Double d2, int i, int i2, int i3, int i4) {
            this(bounds, d, d2, new int[]{i, i2, i3, i4});
            Intrinsics.checkNotNullParameter(bounds, "bounds");
        }

        @Override // org.maplibre.android.camera.CameraUpdate
        public CameraPosition getCameraPosition(MapLibreMap maplibreMap) {
            Intrinsics.checkNotNullParameter(maplibreMap, "maplibreMap");
            Double d = this.bearing;
            if (d == null && this.tilt == null) {
                return maplibreMap.getCameraForLatLngBounds(this.bounds, this.padding);
            }
            LatLngBounds latLngBounds = this.bounds;
            int[] iArr = this.padding;
            Intrinsics.checkNotNull(d);
            double dDoubleValue = d.doubleValue();
            Double d2 = this.tilt;
            Intrinsics.checkNotNull(d2);
            return maplibreMap.getCameraForLatLngBounds(latLngBounds, iArr, dDoubleValue, d2.doubleValue());
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
                return false;
            }
            CameraBoundsUpdate cameraBoundsUpdate = (CameraBoundsUpdate) other;
            if (Intrinsics.areEqual(this.bounds, cameraBoundsUpdate.bounds)) {
                return Arrays.equals(this.padding, cameraBoundsUpdate.padding);
            }
            return false;
        }

        public int hashCode() {
            return (this.bounds.hashCode() * 31) + Arrays.hashCode(this.padding);
        }

        public String toString() {
            LatLngBounds latLngBounds = this.bounds;
            String string = Arrays.toString(this.padding);
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return "CameraBoundsUpdate{bounds=" + latLngBounds + ", padding=" + string + "}";
        }
    }

    /* compiled from: CameraUpdateFactory.kt */
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 %2\u00020\u0001:\u0002$%B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bB!\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0004\u0010\fJ\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0007H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0096\u0002J\b\u0010!\u001a\u00020\u0003H\u0016J\b\u0010\"\u001a\u00020#H\u0016R\u0017\u0010\u0002\u001a\u00020\u0003¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015¨\u0006&"}, d2 = {"Lorg/maplibre/android/camera/CameraUpdateFactory$ZoomUpdate;", "Lorg/maplibre/android/camera/CameraUpdate;", "type", "", "<init>", "(I)V", "zoom", "", "(ID)V", "x", "", "y", "(DFF)V", "getType$annotations", "()V", "getType", "()I", "getZoom", "()D", "value", "getX", "()F", "getY", "transformZoom", "currentZoomArg", "getCameraPosition", "Lorg/maplibre/android/camera/CameraPosition;", "maplibreMap", "Lorg/maplibre/android/maps/MapLibreMap;", "equals", "", "other", "", "hashCode", "toString", "", "Type", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class ZoomUpdate implements CameraUpdate {
        public static final int ZOOM_BY = 2;
        public static final int ZOOM_IN = 0;
        public static final int ZOOM_OUT = 1;
        public static final int ZOOM_TO = 3;
        public static final int ZOOM_TO_POINT = 4;
        private final int type;
        private float x;
        private float y;
        private final double zoom;

        /* compiled from: CameraUpdateFactory.kt */
        @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0081\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lorg/maplibre/android/camera/CameraUpdateFactory$ZoomUpdate$Type;", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
        @Retention(RetentionPolicy.SOURCE)
        @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
        public @interface Type {
        }

        public static /* synthetic */ void getType$annotations() {
        }

        public final int getType() {
            return this.type;
        }

        public final double getZoom() {
            return this.zoom;
        }

        public final float getX() {
            return this.x;
        }

        public final float getY() {
            return this.y;
        }

        public ZoomUpdate(int i) {
            this.type = i;
            this.zoom = 0.0d;
        }

        public ZoomUpdate(int i, double d) {
            this.type = i;
            this.zoom = d;
        }

        public ZoomUpdate(double d, float f, float f2) {
            this.type = 4;
            this.zoom = d;
            this.x = f;
            this.y = f2;
        }

        private final double transformZoom(double currentZoomArg) {
            double d;
            int i = this.type;
            if (i == 0) {
                d = 1;
            } else {
                if (i == 1) {
                    return Double.max(currentZoomArg - 1, 0.0d);
                }
                if (i != 2) {
                    if (i == 3) {
                        return this.zoom;
                    }
                    if (i != 4) {
                        Timber.INSTANCE.e("Unprocessed when branch", new Object[0]);
                        return 4.0d;
                    }
                }
                d = this.zoom;
            }
            return currentZoomArg + d;
        }

        @Override // org.maplibre.android.camera.CameraUpdate
        public CameraPosition getCameraPosition(MapLibreMap maplibreMap) {
            Intrinsics.checkNotNullParameter(maplibreMap, "maplibreMap");
            CameraPosition cameraPosition = maplibreMap.getCameraPosition();
            Intrinsics.checkNotNullExpressionValue(cameraPosition, "getCameraPosition(...)");
            if (this.type != 4) {
                return new CameraPosition.Builder(cameraPosition).zoom(transformZoom(cameraPosition.zoom)).build();
            }
            return new CameraPosition.Builder(cameraPosition).zoom(transformZoom(cameraPosition.zoom)).target(maplibreMap.getProjection().fromScreenLocation(new PointF(this.x, this.y))).build();
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
                return false;
            }
            ZoomUpdate zoomUpdate = (ZoomUpdate) other;
            if (this.type == zoomUpdate.type && Double.compare(zoomUpdate.zoom, this.zoom) == 0) {
                return Float.compare(zoomUpdate.x, this.x) == 0 && Float.compare(zoomUpdate.y, this.y) == 0;
            }
            return false;
        }

        public int hashCode() {
            int i = this.type;
            long jDoubleToLongBits = Double.doubleToLongBits(this.zoom);
            int i2 = ((i * 31) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)))) * 31;
            float f = this.x;
            int iFloatToIntBits = (i2 + (!((f > 0.0f ? 1 : (f == 0.0f ? 0 : -1)) == 0) ? Float.floatToIntBits(f) : 0)) * 31;
            float f2 = this.y;
            return iFloatToIntBits + (f2 == 0.0f ? 0 : Float.floatToIntBits(f2));
        }

        public String toString() {
            return "ZoomUpdate{type=" + this.type + ", zoom=" + this.zoom + ", x=" + this.x + ", y=" + this.y + "}";
        }
    }
}

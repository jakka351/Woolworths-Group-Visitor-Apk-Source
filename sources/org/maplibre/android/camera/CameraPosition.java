package org.maplibre.android.camera;

import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.OperatingSystem;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.R;
import org.maplibre.android.camera.CameraUpdateFactory;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.utils.MathUtils;

/* compiled from: CameraPosition.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001b\u001cB5\b\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bB+\b\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0096\u0002J\b\u0010\u001a\u001a\u00020\u000eH\u0016R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/maplibre/android/camera/CameraPosition;", "Landroid/os/Parcelable;", "target", "Lorg/maplibre/android/geometry/LatLng;", "zoom", "", "tilt", "bearing", ViewProps.PADDING, "", "<init>", "(Lorg/maplibre/android/geometry/LatLng;DDD[D)V", "(Lorg/maplibre/android/geometry/LatLng;DDD)V", "describeContents", "", "writeToParcel", "", "out", "Landroid/os/Parcel;", "flags", "toString", "", "equals", "", "other", "", "hashCode", "Builder", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraPosition implements Parcelable {
    public final double bearing;
    public final double[] padding;
    public final LatLng target;
    public final double tilt;
    public final double zoom;
    public static final CameraPosition DEFAULT = new CameraPosition(new LatLng(), 0.0d, 0.0d, 0.0d, new double[]{0.0d, 0.0d, 0.0d, 0.0d});
    public static final Parcelable.Creator<CameraPosition> CREATOR = new Parcelable.Creator<CameraPosition>() { // from class: org.maplibre.android.camera.CameraPosition$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public CameraPosition createFromParcel(Parcel parcel) {
            LatLng latLng;
            double[] dArr;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            double d = parcel.readDouble();
            if (Build.VERSION.SDK_INT >= 33) {
                latLng = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader(), LatLng.class);
            } else {
                latLng = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
            }
            LatLng latLng2 = latLng;
            double d2 = parcel.readDouble();
            double d3 = parcel.readDouble();
            int i = parcel.readInt();
            if (i > 0) {
                double[] dArr2 = new double[i];
                for (int i2 = 0; i2 < i; i2++) {
                    dArr2[i2] = parcel.readDouble();
                }
                dArr = dArr2;
            } else {
                dArr = null;
            }
            return new CameraPosition(latLng2, d3, d2, d, dArr);
        }

        @Override // android.os.Parcelable.Creator
        public CameraPosition[] newArray(int size) {
            return new CameraPosition[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public CameraPosition(LatLng latLng, double d, double d2, double d3, double[] dArr) {
        this.target = latLng;
        this.zoom = d;
        this.tilt = d2;
        this.bearing = d3;
        this.padding = dArr;
    }

    @Deprecated(message = "use {@link CameraPosition#CameraPosition(LatLng, double, double, double, double[])} instead.")
    public CameraPosition(LatLng latLng, double d, double d2, double d3) {
        this(latLng, d, d2, d3, null);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeDouble(this.bearing);
        out.writeParcelable(this.target, flags);
        out.writeDouble(this.tilt);
        out.writeDouble(this.zoom);
        double[] dArr = this.padding;
        if (dArr != null) {
            out.writeInt(dArr.length);
            for (double d : this.padding) {
                out.writeDouble(d);
            }
            return;
        }
        out.writeInt(-1);
    }

    public String toString() {
        return "Target: " + this.target + ", Zoom:" + this.zoom + ", Bearing:" + this.bearing + ", Tilt:" + this.tilt + ", Padding:" + Arrays.toString(this.padding);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) other;
        LatLng latLng = this.target;
        if (latLng != null && !Intrinsics.areEqual(latLng, cameraPosition.target)) {
            return false;
        }
        if (!(this.zoom == cameraPosition.zoom)) {
            return false;
        }
        if (this.tilt == cameraPosition.tilt) {
            return ((this.bearing > cameraPosition.bearing ? 1 : (this.bearing == cameraPosition.bearing ? 0 : -1)) == 0) && Arrays.equals(this.padding, cameraPosition.padding);
        }
        return false;
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

    /* compiled from: CameraPosition.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0013\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B\u0013\b\u0016\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0002\u0010\u0006B\u0013\b\u0016\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0002\u0010\tB\u0013\b\u0016\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0002\u0010\fB\u0013\b\u0016\u0012\b\u0010\n\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0002\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\u0012J\u0010\u0010\u0013\u001a\u00020\u00002\b\b\u0001\u0010\u0013\u001a\u00020\u0010J\u0010\u0010\u0014\u001a\u00020\u00002\b\b\u0001\u0010\u0014\u001a\u00020\u0010J\u0012\u0010\u0015\u001a\u00020\u00002\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0016J&\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u0010J\u0006\u0010\u001c\u001a\u00020\u0005R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/maplibre/android/camera/CameraPosition$Builder;", "", "<init>", "()V", "previous", "Lorg/maplibre/android/camera/CameraPosition;", "(Lorg/maplibre/android/camera/CameraPosition;)V", "typedArray", "Landroid/content/res/TypedArray;", "(Landroid/content/res/TypedArray;)V", "update", "Lorg/maplibre/android/camera/CameraUpdateFactory$CameraPositionUpdate;", "(Lorg/maplibre/android/camera/CameraUpdateFactory$CameraPositionUpdate;)V", "Lorg/maplibre/android/camera/CameraUpdateFactory$ZoomUpdate;", "(Lorg/maplibre/android/camera/CameraUpdateFactory$ZoomUpdate;)V", "bearing", "", "target", "Lorg/maplibre/android/geometry/LatLng;", "tilt", "zoom", ViewProps.PADDING, "", "location", "left", "top", "right", "bottom", OperatingSystem.JsonKeys.BUILD, "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Builder {
        private double bearing;
        private double[] padding;
        private LatLng target;
        private double tilt;
        private double zoom;

        public Builder() {
            this.bearing = -1.0d;
            this.tilt = -1.0d;
            this.zoom = -1.0d;
        }

        public Builder(CameraPosition cameraPosition) {
            this.bearing = -1.0d;
            this.tilt = -1.0d;
            this.zoom = -1.0d;
            if (cameraPosition != null) {
                this.bearing = cameraPosition.bearing;
                this.target = cameraPosition.target;
                this.tilt = cameraPosition.tilt;
                this.zoom = cameraPosition.zoom;
                this.padding = cameraPosition.padding;
            }
        }

        public Builder(TypedArray typedArray) {
            this.bearing = -1.0d;
            this.tilt = -1.0d;
            this.zoom = -1.0d;
            if (typedArray != null) {
                this.bearing = typedArray.getFloat(R.styleable.maplibre_MapView_maplibre_cameraBearing, 0.0f);
                this.target = new LatLng(typedArray.getFloat(R.styleable.maplibre_MapView_maplibre_cameraTargetLat, 0.0f), typedArray.getFloat(R.styleable.maplibre_MapView_maplibre_cameraTargetLng, 0.0f));
                this.tilt = typedArray.getFloat(R.styleable.maplibre_MapView_maplibre_cameraTilt, 0.0f);
                this.zoom = typedArray.getFloat(R.styleable.maplibre_MapView_maplibre_cameraZoom, 0.0f);
            }
        }

        public Builder(CameraUpdateFactory.CameraPositionUpdate cameraPositionUpdate) {
            this.bearing = -1.0d;
            this.tilt = -1.0d;
            this.zoom = -1.0d;
            if (cameraPositionUpdate != null) {
                this.bearing = cameraPositionUpdate.getBearing();
                this.target = cameraPositionUpdate.getTarget();
                this.tilt = cameraPositionUpdate.getTilt();
                this.zoom = cameraPositionUpdate.getZoom();
                this.padding = cameraPositionUpdate.getPadding();
            }
        }

        public Builder(CameraUpdateFactory.ZoomUpdate zoomUpdate) {
            this.bearing = -1.0d;
            this.tilt = -1.0d;
            this.zoom = -1.0d;
            if (zoomUpdate != null) {
                this.zoom = zoomUpdate.getZoom();
            }
        }

        public final Builder bearing(double bearing) {
            while (bearing >= 360.0d) {
                bearing -= 360.0d;
            }
            while (bearing < 0.0d) {
                bearing += 360.0d;
            }
            this.bearing = bearing;
            return this;
        }

        public final Builder target(LatLng location) {
            this.target = location;
            return this;
        }

        public final Builder tilt(double tilt) {
            this.tilt = MathUtils.clamp(tilt, 0.0d, 60.0d);
            return this;
        }

        public final Builder zoom(double zoom) {
            this.zoom = zoom;
            return this;
        }

        public final Builder padding(double[] padding) {
            this.padding = padding;
            return this;
        }

        public final Builder padding(double left, double top, double right, double bottom) {
            this.padding = new double[]{left, top, right, bottom};
            return this;
        }

        public final CameraPosition build() {
            return new CameraPosition(this.target, this.zoom, this.tilt, this.bearing, this.padding);
        }
    }
}

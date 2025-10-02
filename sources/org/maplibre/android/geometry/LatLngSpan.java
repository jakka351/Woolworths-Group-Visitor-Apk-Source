package org.maplibre.android.geometry;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LatLngSpan.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0011\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\tJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u0015H\u0016J\b\u0010\u001a\u001a\u00020\u0015H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\r¨\u0006\u001c"}, d2 = {"Lorg/maplibre/android/geometry/LatLngSpan;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "<init>", "(Landroid/os/Parcel;)V", "latitudeSpan", "", "longitudeSpan", "(DD)V", "getLatitudeSpan", "()D", "setLatitudeSpan", "(D)V", "getLongitudeSpan", "setLongitudeSpan", "equals", "", "other", "", "describeContents", "", "writeToParcel", "", "out", "flags", "hashCode", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LatLngSpan implements Parcelable {
    private double latitudeSpan;
    private double longitudeSpan;
    public static final Parcelable.Creator<LatLngSpan> CREATOR = new Parcelable.Creator<LatLngSpan>() { // from class: org.maplibre.android.geometry.LatLngSpan$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public LatLngSpan createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new LatLngSpan(parcel, (DefaultConstructorMarker) null);
        }

        @Override // android.os.Parcelable.Creator
        public LatLngSpan[] newArray(int size) {
            return new LatLngSpan[size];
        }
    };

    public /* synthetic */ LatLngSpan(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final double getLatitudeSpan() {
        return this.latitudeSpan;
    }

    public final void setLatitudeSpan(double d) {
        this.latitudeSpan = d;
    }

    public final double getLongitudeSpan() {
        return this.longitudeSpan;
    }

    public final void setLongitudeSpan(double d) {
        this.longitudeSpan = d;
    }

    private LatLngSpan(Parcel parcel) {
        this.latitudeSpan = parcel.readDouble();
        this.longitudeSpan = parcel.readDouble();
    }

    public LatLngSpan(double d, double d2) {
        this.latitudeSpan = d;
        this.longitudeSpan = d2;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LatLngSpan)) {
            return false;
        }
        LatLngSpan latLngSpan = (LatLngSpan) other;
        if (this.longitudeSpan == latLngSpan.longitudeSpan) {
            if (this.latitudeSpan == latLngSpan.latitudeSpan) {
                return true;
            }
        }
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeDouble(this.latitudeSpan);
        out.writeDouble(this.longitudeSpan);
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.latitudeSpan);
        int i = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.longitudeSpan);
        return (i * 31) + ((int) ((jDoubleToLongBits2 >>> 32) ^ jDoubleToLongBits2));
    }
}

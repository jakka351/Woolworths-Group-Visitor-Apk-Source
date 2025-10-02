package org.maplibre.android.geometry;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProjectedMeters.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0019\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\u0000¢\u0006\u0004\b\u0005\u0010\bB\u0011\b\u0012\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u0005\u0010\u000bJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0015H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u0015H\u0016R\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0004\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001e"}, d2 = {"Lorg/maplibre/android/geometry/ProjectedMeters;", "Landroid/os/Parcelable;", "northing", "", "easting", "<init>", "(DD)V", "projectedMeters", "(Lorg/maplibre/android/geometry/ProjectedMeters;)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "value", "getNorthing", "()D", "getEasting", "equals", "", "other", "", "hashCode", "", "toString", "", "describeContents", "writeToParcel", "", "out", "flags", "Companion", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ProjectedMeters implements Parcelable {
    private double easting;
    private double northing;
    public static final Parcelable.Creator<ProjectedMeters> CREATOR = new Parcelable.Creator<ProjectedMeters>() { // from class: org.maplibre.android.geometry.ProjectedMeters$Companion$CREATOR$1
        @Override // android.os.Parcelable.Creator
        public ProjectedMeters createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new ProjectedMeters(parcel, (DefaultConstructorMarker) null);
        }

        @Override // android.os.Parcelable.Creator
        public ProjectedMeters[] newArray(int size) {
            return new ProjectedMeters[size];
        }
    };

    public /* synthetic */ ProjectedMeters(Parcel parcel, DefaultConstructorMarker defaultConstructorMarker) {
        this(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final double getNorthing() {
        return this.northing;
    }

    public final double getEasting() {
        return this.easting;
    }

    public ProjectedMeters(double d, double d2) {
        this.northing = d;
        this.easting = d2;
    }

    public ProjectedMeters(ProjectedMeters projectedMeters) {
        Intrinsics.checkNotNullParameter(projectedMeters, "projectedMeters");
        this.northing = projectedMeters.northing;
        this.easting = projectedMeters.easting;
    }

    private ProjectedMeters(Parcel parcel) {
        this.northing = parcel.readDouble();
        this.easting = parcel.readDouble();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
            return false;
        }
        ProjectedMeters projectedMeters = (ProjectedMeters) other;
        return Double.compare(projectedMeters.easting, this.easting) == 0 && Double.compare(projectedMeters.northing, this.northing) == 0;
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.easting);
        int i = (int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32));
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.northing);
        return (i * 31) + ((int) ((jDoubleToLongBits2 >>> 32) ^ jDoubleToLongBits2));
    }

    public String toString() {
        return "ProjectedMeters [northing=" + this.northing + ", easting=" + this.easting + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeDouble(this.northing);
        out.writeDouble(this.easting);
    }
}

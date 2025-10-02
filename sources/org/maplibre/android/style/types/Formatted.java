package org.maplibre.android.style.types;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Formatted.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003¢\u0006\u0002\u0010\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lorg/maplibre/android/style/types/Formatted;", "", "formattedSections", "", "Lorg/maplibre/android/style/types/FormattedSection;", "<init>", "([Lorg/maplibre/android/style/types/FormattedSection;)V", "getFormattedSections", "()[Lorg/maplibre/android/style/types/FormattedSection;", "[Lorg/maplibre/android/style/types/FormattedSection;", "toArray", "()[Ljava/lang/Object;", "equals", "", "o", "hashCode", "", "toString", "", "MapLibreAndroid_drawableRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Formatted {
    private final FormattedSection[] formattedSections;

    public Formatted(FormattedSection... formattedSections) {
        Intrinsics.checkNotNullParameter(formattedSections, "formattedSections");
        this.formattedSections = formattedSections;
    }

    public final FormattedSection[] getFormattedSections() {
        return this.formattedSections;
    }

    public final Object[] toArray() {
        FormattedSection[] formattedSectionArr = this.formattedSections;
        Object[] objArr = new Object[formattedSectionArr.length];
        int length = formattedSectionArr.length;
        for (int i = 0; i < length; i++) {
            objArr[i] = this.formattedSections[i].toArray();
        }
        return objArr;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !Intrinsics.areEqual(getClass(), o.getClass())) {
            return false;
        }
        return Arrays.equals(this.formattedSections, ((Formatted) o).formattedSections);
    }

    public int hashCode() {
        return Arrays.hashCode(this.formattedSections);
    }

    public String toString() {
        return "Formatted{formattedSections=" + Arrays.toString(this.formattedSections) + "}";
    }
}

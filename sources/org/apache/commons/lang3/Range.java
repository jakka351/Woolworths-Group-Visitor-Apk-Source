package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes2.dex */
public final class Range<T> implements Serializable {
    private static final long serialVersionUID = 1;
    private final Comparator<T> comparator;
    private transient int hashCode;
    private final T maximum;
    private final T minimum;
    private transient String toString;

    /* JADX WARN: Incorrect types in method signature: <T::Ljava/lang/Comparable<TT;>;>(TT;)Lorg/apache/commons/lang3/Range<TT;>; */
    public static Range is(Comparable comparable) {
        return between(comparable, comparable, null);
    }

    public static <T> Range<T> is(T t, Comparator<T> comparator) {
        return between(t, t, comparator);
    }

    /* JADX WARN: Incorrect types in method signature: <T::Ljava/lang/Comparable<TT;>;>(TT;TT;)Lorg/apache/commons/lang3/Range<TT;>; */
    public static Range between(Comparable comparable, Comparable comparable2) {
        return between(comparable, comparable2, null);
    }

    public static <T> Range<T> between(T t, T t2, Comparator<T> comparator) {
        return new Range<>(t, t2, comparator);
    }

    private Range(T t, T t2, Comparator<T> comparator) {
        if (t == null || t2 == null) {
            throw new IllegalArgumentException("Elements in a range must not be null: element1=" + t + ", element2=" + t2);
        }
        comparator = comparator == null ? ComparableComparator.INSTANCE : comparator;
        if (comparator.compare(t, t2) < 1) {
            this.minimum = t;
            this.maximum = t2;
        } else {
            this.minimum = t2;
            this.maximum = t;
        }
        this.comparator = comparator;
    }

    public T getMinimum() {
        return this.minimum;
    }

    public T getMaximum() {
        return this.maximum;
    }

    public Comparator<T> getComparator() {
        return this.comparator;
    }

    public boolean isNaturalOrdering() {
        return this.comparator == ComparableComparator.INSTANCE;
    }

    public boolean contains(T t) {
        return t != null && this.comparator.compare(t, this.minimum) > -1 && this.comparator.compare(t, this.maximum) < 1;
    }

    public boolean isAfter(T t) {
        return t != null && this.comparator.compare(t, this.minimum) < 0;
    }

    public boolean isStartedBy(T t) {
        return t != null && this.comparator.compare(t, this.minimum) == 0;
    }

    public boolean isEndedBy(T t) {
        return t != null && this.comparator.compare(t, this.maximum) == 0;
    }

    public boolean isBefore(T t) {
        return t != null && this.comparator.compare(t, this.maximum) > 0;
    }

    public int elementCompareTo(T t) {
        if (t == null) {
            throw new NullPointerException("Element is null");
        }
        if (isAfter(t)) {
            return -1;
        }
        return isBefore(t) ? 1 : 0;
    }

    public boolean containsRange(Range<T> range) {
        return range != null && contains(range.minimum) && contains(range.maximum);
    }

    public boolean isAfterRange(Range<T> range) {
        if (range == null) {
            return false;
        }
        return isAfter(range.maximum);
    }

    public boolean isOverlappedBy(Range<T> range) {
        if (range == null) {
            return false;
        }
        return range.contains(this.minimum) || range.contains(this.maximum) || contains(range.minimum);
    }

    public boolean isBeforeRange(Range<T> range) {
        if (range == null) {
            return false;
        }
        return isBefore(range.minimum);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Range range = (Range) obj;
        return this.minimum.equals(range.minimum) && this.maximum.equals(range.maximum);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = this.maximum.hashCode() + ((((629 + getClass().hashCode()) * 37) + this.minimum.hashCode()) * 37);
        this.hashCode = iHashCode;
        return iHashCode;
    }

    public String toString() {
        String str = this.toString;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append('[');
        sb.append(this.minimum);
        sb.append("..");
        sb.append(this.maximum);
        sb.append(']');
        String string = sb.toString();
        this.toString = string;
        return string;
    }

    public String toString(String str) {
        return String.format(str, this.minimum, this.maximum, this.comparator);
    }

    private enum ComparableComparator implements Comparator {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }
}

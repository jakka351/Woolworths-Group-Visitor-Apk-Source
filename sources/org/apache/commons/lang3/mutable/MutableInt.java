package org.apache.commons.lang3.mutable;

/* loaded from: classes2.dex */
public class MutableInt extends Number implements Comparable<MutableInt>, Mutable<Number> {
    private static final long serialVersionUID = 512176391864L;
    private int value;

    public MutableInt() {
    }

    public MutableInt(int i) {
        this.value = i;
    }

    public MutableInt(Number number) {
        this.value = number.intValue();
    }

    public MutableInt(String str) throws NumberFormatException {
        this.value = Integer.parseInt(str);
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    /* renamed from: getValue */
    public Number getValue2() {
        return new Integer(this.value);
    }

    public void setValue(int i) {
        this.value = i;
    }

    @Override // org.apache.commons.lang3.mutable.Mutable
    public void setValue(Number number) {
        this.value = number.intValue();
    }

    public void increment() {
        this.value++;
    }

    public void decrement() {
        this.value--;
    }

    public void add(int i) {
        this.value += i;
    }

    public void add(Number number) {
        this.value += number.intValue();
    }

    public void subtract(int i) {
        this.value -= i;
    }

    public void subtract(Number number) {
        this.value -= number.intValue();
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public float floatValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return this.value;
    }

    public Integer toInteger() {
        return Integer.valueOf(intValue());
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableInt) && this.value == ((MutableInt) obj).intValue();
    }

    public int hashCode() {
        return this.value;
    }

    @Override // java.lang.Comparable
    public int compareTo(MutableInt mutableInt) {
        int i = mutableInt.value;
        int i2 = this.value;
        if (i2 < i) {
            return -1;
        }
        return i2 == i ? 0 : 1;
    }

    public String toString() {
        return String.valueOf(this.value);
    }
}

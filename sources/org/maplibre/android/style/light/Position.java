package org.maplibre.android.style.light;

/* loaded from: classes2.dex */
public class Position {
    private float azimuthalAngle;
    private float polarAngle;
    private float radialCoordinate;

    public Position(float f, float f2, float f3) {
        this.radialCoordinate = f;
        this.azimuthalAngle = f2;
        this.polarAngle = f3;
    }

    public static Position fromPosition(float f, float f2, float f3) {
        return new Position(f, f2, f3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position position = (Position) obj;
        return Float.compare(position.radialCoordinate, this.radialCoordinate) == 0 && Float.compare(position.azimuthalAngle, this.azimuthalAngle) == 0 && Float.compare(position.polarAngle, this.polarAngle) == 0;
    }

    public int hashCode() {
        float f = this.radialCoordinate;
        int iFloatToIntBits = (f != 0.0f ? Float.floatToIntBits(f) : 0) * 31;
        float f2 = this.azimuthalAngle;
        int iFloatToIntBits2 = (iFloatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31;
        float f3 = this.polarAngle;
        return iFloatToIntBits2 + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0);
    }

    public String toString() {
        return "Position{radialCoordinate=" + this.radialCoordinate + ", azimuthalAngle=" + this.azimuthalAngle + ", polarAngle=" + this.polarAngle + '}';
    }
}

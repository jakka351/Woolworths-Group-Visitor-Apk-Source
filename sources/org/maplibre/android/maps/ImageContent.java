package org.maplibre.android.maps;

/* loaded from: classes2.dex */
public final class ImageContent {
    private final float bottom;
    private final float left;
    private final float right;
    private final float top;

    public ImageContent(float f, float f2, float f3, float f4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
    }

    public float[] getContentArray() {
        return new float[]{this.left, this.top, this.right, this.bottom};
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ImageContent)) {
            return false;
        }
        ImageContent imageContent = (ImageContent) obj;
        return this.left == imageContent.left && this.top == imageContent.top && this.right == imageContent.right && this.bottom == imageContent.bottom;
    }

    public int hashCode() {
        float f = this.left;
        int iFloatToIntBits = (f != 0.0f ? Float.floatToIntBits(f) : 0) * 31;
        float f2 = this.top;
        int iFloatToIntBits2 = (iFloatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31;
        float f3 = this.right;
        int iFloatToIntBits3 = (iFloatToIntBits2 + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0)) * 31;
        float f4 = this.bottom;
        return iFloatToIntBits3 + (f4 != 0.0f ? Float.floatToIntBits(f4) : 0);
    }

    public String toString() {
        return "[ left: " + this.left + ", top: " + this.top + ", right: " + this.right + ", bottom: " + this.bottom + " ]";
    }
}

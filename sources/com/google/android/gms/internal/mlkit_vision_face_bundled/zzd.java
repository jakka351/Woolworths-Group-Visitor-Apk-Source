package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzd {
    public static ByteBuffer zza(Bitmap bitmap, boolean z) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int iCeil = (int) Math.ceil(height / 2.0d);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(((iCeil + iCeil) * ((int) Math.ceil(width / 2.0d))) + i);
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < height; i4++) {
            int i5 = 0;
            while (i5 < width) {
                int i6 = iArr[i3];
                int i7 = i6 >> 16;
                int i8 = i6 >> 8;
                int i9 = i6 & 255;
                int i10 = i2 + 1;
                int i11 = i7 & 255;
                int i12 = i8 & 255;
                byteBufferAllocateDirect.put(i2, (byte) Math.min(255, (((((i11 * 66) + (i12 * 129)) + (i9 * 25)) + 128) >> 8) + 16));
                if (i4 % 2 == 0 && i3 % 2 == 0) {
                    int i13 = ((((i11 * 112) - (i12 * 94)) - (i9 * 18)) + 128) >> 8;
                    int i14 = (((((i11 * (-38)) - (i12 * 74)) + (i9 * 112)) + 128) >> 8) + 128;
                    int i15 = i + 1;
                    byteBufferAllocateDirect.put(i, (byte) Math.min(255, i13 + 128));
                    i = i15 + 1;
                    byteBufferAllocateDirect.put(i15, (byte) Math.min(255, i14));
                }
                i3++;
                i5++;
                i2 = i10;
            }
        }
        return byteBufferAllocateDirect;
    }
}

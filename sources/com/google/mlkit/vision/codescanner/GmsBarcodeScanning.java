package com.google.mlkit.vision.codescanner;

import android.content.Context;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.internal.zze;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class GmsBarcodeScanning {
    private GmsBarcodeScanning() {
    }

    public static GmsBarcodeScanner getClient(Context context) {
        return new zze(context, new GmsBarcodeScannerOptions.Builder().build());
    }

    public static GmsBarcodeScanner getClient(Context context, GmsBarcodeScannerOptions gmsBarcodeScannerOptions) {
        return new zze(context, gmsBarcodeScannerOptions);
    }
}

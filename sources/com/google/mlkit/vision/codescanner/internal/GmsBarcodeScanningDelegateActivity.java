package com.google.mlkit.vision.codescanner.internal;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.mlkit_code_scanner.zzoz;
import com.google.mlkit.vision.barcode.common.Barcode;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public class GmsBarcodeScanningDelegateActivity extends ComponentActivity {
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent action = new Intent().setPackage("com.google.android.gms").setAction("com.google.android.gms.mlkit.ACTION_SCAN_BARCODE");
        ApplicationInfo applicationInfo = getApplicationInfo();
        int i = applicationInfo.labelRes;
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.google.mlkit.vision.codescanner.internal.zzf
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                GmsBarcodeScanningDelegateActivity gmsBarcodeScanningDelegateActivity = this.zza;
                ActivityResult activityResult = (ActivityResult) obj;
                Intent data = activityResult.getData();
                if (activityResult.getResultCode() == -1 && data != null && data.hasExtra("extra_barcode_result")) {
                    zze.zzc(new Barcode(new zza((zzoz) SafeParcelableSerializer.deserializeFromBytes((byte[]) Preconditions.checkNotNull(data.getByteArrayExtra("extra_barcode_result")), zzoz.CREATOR))), 0);
                } else {
                    zze.zzc(null, data != null ? data.getIntExtra("extra_error_code", 13) : 13);
                }
                gmsBarcodeScanningDelegateActivity.finish();
            }
        }).launch(action.putExtra("extra_calling_app_name", i != 0 ? getString(i) : getPackageManager().getApplicationLabel(applicationInfo).toString()).putExtra("extra_supported_formats", getIntent().getIntExtra("extra_supported_formats", 0)).putExtra("extra_allow_manual_input", getIntent().getBooleanExtra("extra_allow_manual_input", false)).putExtra("extra_enable_auto_zoom", getIntent().getBooleanExtra("extra_enable_auto_zoom", false)));
    }
}

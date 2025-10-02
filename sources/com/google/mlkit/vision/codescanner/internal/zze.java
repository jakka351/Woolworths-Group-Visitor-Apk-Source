package com.google.mlkit.vision.codescanner.internal;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstall;
import com.google.android.gms.internal.mlkit_code_scanner.zziv;
import com.google.android.gms.internal.mlkit_code_scanner.zzka;
import com.google.android.gms.internal.mlkit_code_scanner.zzkb;
import com.google.android.gms.internal.mlkit_code_scanner.zzkc;
import com.google.android.gms.internal.mlkit_code_scanner.zzny;
import com.google.android.gms.internal.mlkit_code_scanner.zzoa;
import com.google.android.gms.internal.mlkit_code_scanner.zzob;
import com.google.android.gms.internal.mlkit_code_scanner.zzoj;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zze implements GmsBarcodeScanner {
    public static final /* synthetic */ int zzb = 0;
    private static boolean zzd;
    private final Context zze;
    private final GmsBarcodeScannerOptions zzf;
    private final zzny zzg;
    private final zzoa zzh;
    static final AtomicReference zza = new AtomicReference();
    private static final Object zzc = new Object();

    public zze(Context context, GmsBarcodeScannerOptions gmsBarcodeScannerOptions) {
        zzoa zzoaVarZza = zzoa.zza(context);
        this.zzg = zzoj.zzb("play-services-code-scanner");
        this.zze = context;
        this.zzf = gmsBarcodeScannerOptions;
        this.zzh = zzoaVarZza;
    }

    static void zzc(Barcode barcode, int i) {
        Pair pair = (Pair) zza.getAndSet(null);
        if (pair == null) {
            Log.e("GmsBarcodeScannerImpl", "Scanning task source doesn't exist when setting back result.");
            return;
        }
        if (barcode != null) {
            ((TaskCompletionSource) pair.first).setResult(barcode);
        } else if (i == 201) {
            ((CancellationTokenSource) pair.second).cancel();
        } else {
            ((TaskCompletionSource) pair.first).setException(new MlKitException("Failed to scan code.", i));
        }
    }

    @Override // com.google.android.gms.common.api.OptionalModuleApi
    public final Feature[] getOptionalFeatures() {
        return new Feature[]{OptionalModuleUtils.FEATURE_MLKIT_BARCODE_UI};
    }

    @Override // com.google.mlkit.vision.codescanner.GmsBarcodeScanner
    public final Task<Barcode> startScan() {
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zze) >= 221500000) {
            return ModuleInstall.getClient(this.zze).areModulesAvailable(new OptionalModuleApi() { // from class: com.google.mlkit.vision.codescanner.internal.zzc
                @Override // com.google.android.gms.common.api.OptionalModuleApi
                public final Feature[] getOptionalFeatures() {
                    int i = zze.zzb;
                    return new Feature[]{OptionalModuleUtils.FEATURE_BARCODE};
                }
            }).onSuccessTask(new SuccessContinuation() { // from class: com.google.mlkit.vision.codescanner.internal.zzd
                @Override // com.google.android.gms.tasks.SuccessContinuation
                public final Task then(Object obj) {
                    return this.zza.zza((ModuleAvailabilityResponse) obj);
                }
            });
        }
        zzb(MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD, SystemClock.elapsedRealtime(), System.currentTimeMillis());
        return Tasks.forException(new MlKitException("Code scanner module is not supported on current Google Play Services version, please upgrade.", MlKitException.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD));
    }

    final /* synthetic */ Task zza(ModuleAvailabilityResponse moduleAvailabilityResponse) throws Exception {
        Task taskAddOnCompleteListener;
        boolean z = false;
        if (moduleAvailabilityResponse.areModulesAvailable()) {
            if (new Intent().setPackage("com.google.android.gms").setAction("com.google.android.gms.mlkit.ACTION_SCAN_BARCODE").resolveActivity(this.zze.getApplicationContext().getPackageManager()) != null) {
                z = true;
            }
        }
        synchronized (zzc) {
            final long jElapsedRealtime = SystemClock.elapsedRealtime();
            final long jCurrentTimeMillis = System.currentTimeMillis();
            if (z) {
                AtomicReference atomicReference = zza;
                Pair pair = (Pair) atomicReference.getAndSet(null);
                if (pair != null) {
                    ((CancellationTokenSource) pair.second).cancel();
                }
                CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
                TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
                atomicReference.set(new Pair(taskCompletionSource, cancellationTokenSource));
                Intent intent = new Intent(this.zze, (Class<?>) GmsBarcodeScanningDelegateActivity.class);
                intent.putExtra("extra_supported_formats", this.zzf.zza());
                intent.putExtra("extra_allow_manual_input", this.zzf.zzc());
                intent.putExtra("extra_enable_auto_zoom", this.zzf.zzb());
                intent.setFlags(268435456);
                this.zze.startActivity(intent);
                taskAddOnCompleteListener = taskCompletionSource.getTask().addOnCompleteListener(new OnCompleteListener() { // from class: com.google.mlkit.vision.codescanner.internal.zzb
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public final void onComplete(Task task) {
                        this.zza.zzb(task.isCanceled() ? MlKitException.CODE_SCANNER_CANCELLED : !task.isSuccessful() ? ((MlKitException) Preconditions.checkNotNull((MlKitException) task.getException())).getErrorCode() : 0, jElapsedRealtime, jCurrentTimeMillis);
                    }
                });
            } else {
                if (!zzd) {
                    OptionalModuleUtils.requestDownload(this.zze, OptionalModuleUtils.MLKIT_BARCODE_UI);
                    zzd = true;
                }
                zzb(200, jElapsedRealtime, jCurrentTimeMillis);
                taskAddOnCompleteListener = Tasks.forException(new MlKitException("Waiting for the Barcode UI module to be downloaded.", 200));
            }
        }
        return taskAddOnCompleteListener;
    }

    final void zzb(int i, long j, long j2) {
        zzka zzkaVar;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        long jCurrentTimeMillis = System.currentTimeMillis();
        zzny zznyVar = this.zzg;
        zzkc zzkcVar = new zzkc();
        zziv zzivVar = new zziv();
        zzivVar.zzd(Integer.valueOf(this.zzf.zza()));
        zzivVar.zza(Boolean.valueOf(this.zzf.zzc()));
        zzivVar.zzb(Long.valueOf(jElapsedRealtime - j));
        if (i == 0) {
            zzkaVar = zzka.NO_ERROR;
        } else if (i != 207) {
            switch (i) {
                case 200:
                    zzkaVar = zzka.CODE_SCANNER_UNAVAILABLE;
                    break;
                case MlKitException.CODE_SCANNER_CANCELLED /* 201 */:
                    zzkaVar = zzka.CODE_SCANNER_CANCELLED;
                    break;
                case MlKitException.CODE_SCANNER_CAMERA_PERMISSION_NOT_GRANTED /* 202 */:
                    zzkaVar = zzka.CODE_SCANNER_CAMERA_PERMISSION_NOT_GRANTED;
                    break;
                case MlKitException.CODE_SCANNER_APP_NAME_UNAVAILABLE /* 203 */:
                    zzkaVar = zzka.CODE_SCANNER_APP_NAME_UNAVAILABLE;
                    break;
                case MlKitException.CODE_SCANNER_TASK_IN_PROGRESS /* 204 */:
                    zzkaVar = zzka.CODE_SCANNER_TASK_IN_PROGRESS;
                    break;
                case MlKitException.CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR /* 205 */:
                    zzkaVar = zzka.CODE_SCANNER_PIPELINE_INITIALIZATION_ERROR;
                    break;
                default:
                    zzkaVar = zzka.UNKNOWN_ERROR;
                    break;
            }
        } else {
            zzkaVar = zzka.CODE_SCANNER_GOOGLE_PLAY_SERVICES_VERSION_TOO_OLD;
        }
        zzivVar.zzc(zzkaVar);
        zzkcVar.zzc(zzivVar.zze());
        zznyVar.zzc(zzob.zze(zzkcVar), zzkb.CODE_SCANNER_SCAN_API);
        this.zzh.zzc(24323, i, j2, jCurrentTimeMillis);
    }
}

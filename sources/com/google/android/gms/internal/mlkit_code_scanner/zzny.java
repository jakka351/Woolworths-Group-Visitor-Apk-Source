package com.google.android.gms.internal.mlkit_code_scanner;

import android.content.Context;
import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zzny {
    private static zzp zza;
    private static final zzr zzb = zzr.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zznr zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk = new HashMap();
    private final Map zzl = new HashMap();

    public zzny(Context context, final SharedPrefManager sharedPrefManager, zznr zznrVar, String str) {
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zznrVar;
        zzok.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zznv
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zza.zza();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.zzh = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zznw
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return sharedPrefManager.getMlSdkInstanceId();
            }
        });
        zzr zzrVar = zzb;
        this.zzj = zzrVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzrVar.get(str)) : -1;
    }

    private static synchronized zzp zzd() {
        zzp zzpVar = zza;
        if (zzpVar != null) {
            return zzpVar;
        }
        LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
        zzm zzmVar = new zzm();
        for (int i = 0; i < locales.size(); i++) {
            zzmVar.zza(CommonUtils.languageTagFromLocale(locales.get(i)));
        }
        zzp zzpVarZzb = zzmVar.zzb();
        zza = zzpVarZzb;
        return zzpVarZzb;
    }

    final /* synthetic */ String zza() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    final /* synthetic */ void zzb(zznq zznqVar, zzkb zzkbVar, String str) {
        zznqVar.zza(zzkbVar);
        String strZzc = zznqVar.zzc();
        zzmq zzmqVar = new zzmq();
        zzmqVar.zzb(this.zzc);
        zzmqVar.zzc(this.zzd);
        zzmqVar.zzh(zzd());
        zzmqVar.zzg(true);
        zzmqVar.zzl(strZzc);
        zzmqVar.zzj(str);
        zzmqVar.zzi(this.zzh.isSuccessful() ? (String) this.zzh.getResult() : this.zzf.getMlSdkInstanceId());
        zzmqVar.zzd(10);
        zzmqVar.zzk(Integer.valueOf(this.zzj));
        zznqVar.zzb(zzmqVar);
        this.zze.zza(zznqVar);
    }

    public final void zzc(final zznq zznqVar, final zzkb zzkbVar) {
        final String version = this.zzg.isSuccessful() ? (String) this.zzg.getResult() : LibraryVersion.getInstance().getVersion(this.zzi);
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zznx
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzb(zznqVar, zzkbVar, version);
            }
        });
    }
}

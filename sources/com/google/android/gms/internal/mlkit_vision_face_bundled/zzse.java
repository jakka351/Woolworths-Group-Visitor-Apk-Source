package com.google.android.gms.internal.mlkit_vision_face_bundled;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
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
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzse {
    private static zzau zza;
    private static final zzaw zzb = zzaw.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);
    private final String zzc;
    private final String zzd;
    private final zzrx zze;
    private final SharedPrefManager zzf;
    private final Task zzg;
    private final Task zzh;
    private final String zzi;
    private final int zzj;
    private final Map zzk = new HashMap();

    public zzse(Context context, final SharedPrefManager sharedPrefManager, zzrx zzrxVar, String str) {
        new HashMap();
        this.zzc = context.getPackageName();
        this.zzd = CommonUtils.getAppVersion(context);
        this.zzf = sharedPrefManager;
        this.zze = zzrxVar;
        zzsn.zza();
        this.zzi = str;
        this.zzg = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.zzsc
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zza.zza();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        Objects.requireNonNull(sharedPrefManager);
        this.zzh = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.zzsd
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return sharedPrefManager.getMlSdkInstanceId();
            }
        });
        zzaw zzawVar = zzb;
        this.zzj = zzawVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzawVar.get(str)) : -1;
    }

    private static synchronized zzau zzd() {
        zzau zzauVar = zza;
        if (zzauVar != null) {
            return zzauVar;
        }
        LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
        zzar zzarVar = new zzar();
        for (int i = 0; i < locales.size(); i++) {
            zzarVar.zza(CommonUtils.languageTagFromLocale(locales.get(i)));
        }
        zzau zzauVarZzb = zzarVar.zzb();
        zza = zzauVarZzb;
        return zzauVarZzb;
    }

    final /* synthetic */ String zza() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.zzi);
    }

    final /* synthetic */ void zzb(zzrw zzrwVar, zznh zznhVar, String str) {
        zzrwVar.zzb(zznhVar);
        String strZzd = zzrwVar.zzd();
        zzqw zzqwVar = new zzqw();
        zzqwVar.zzb(this.zzc);
        zzqwVar.zzc(this.zzd);
        zzqwVar.zzh(zzd());
        zzqwVar.zzg(true);
        zzqwVar.zzl(strZzd);
        zzqwVar.zzj(str);
        zzqwVar.zzi(this.zzh.isSuccessful() ? (String) this.zzh.getResult() : this.zzf.getMlSdkInstanceId());
        zzqwVar.zzd(10);
        zzqwVar.zzk(Integer.valueOf(this.zzj));
        zzrwVar.zzc(zzqwVar);
        this.zze.zza(zzrwVar);
    }

    public final void zzc(com.google.android.gms.vision.face.mlkit.zzc zzcVar, final zznh zznhVar) throws Throwable {
        final String version;
        Map map = this.zzk;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (map.get(zznhVar) != null && jElapsedRealtime - ((Long) this.zzk.get(zznhVar)).longValue() <= TimeUnit.SECONDS.toMillis(30L)) {
            return;
        }
        this.zzk.put(zznhVar, Long.valueOf(jElapsedRealtime));
        zznf zznfVar = zzcVar.zza;
        zzmp zzmpVar = zzcVar.zzb;
        zzng zzngVar = zzcVar.zzc;
        int i = zzcVar.zzd;
        zzni zzniVar = new zzni();
        zzniVar.zzd(zznfVar);
        zzmg zzmgVar = new zzmg();
        zzmgVar.zzb(zzmpVar);
        zzmgVar.zza(zzngVar);
        zzniVar.zzf(zzmgVar.zzc());
        final zzrw zzrwVarZzf = zzsh.zzf(zzniVar, i);
        if (this.zzg.isSuccessful()) {
            version = (String) this.zzg.getResult();
        } else {
            version = LibraryVersion.getInstance().getVersion(this.zzi);
        }
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.zzsb
            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zzb(zzrwVarZzf, zznhVar, version);
            }
        });
    }
}

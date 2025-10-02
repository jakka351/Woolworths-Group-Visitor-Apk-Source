package com.google.android.gms.internal.identity;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.location.DeviceOrientationListener;
import com.google.android.gms.location.DeviceOrientationRequest;
import com.google.android.gms.location.FusedOrientationProviderClient;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
/* loaded from: classes3.dex */
public final class zzci extends GoogleApi implements FusedOrientationProviderClient {
    public static final /* synthetic */ int zza = 0;

    public zzci(Activity activity) {
        super(activity, (Api<Api.ApiOptions.NoOptions>) zzbi.zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.common.api.GoogleApi
    protected final String getApiFallbackAttributionTag(Context context) {
        return null;
    }

    @Override // com.google.android.gms.location.FusedOrientationProviderClient
    public final Task<Void> removeOrientationUpdates(DeviceOrientationListener deviceOrientationListener) {
        return doUnregisterEventListener(ListenerHolders.createListenerKey(deviceOrientationListener, "DeviceOrientationListener"), 2440).continueWith(zzcm.zza, zzck.zza);
    }

    @Override // com.google.android.gms.location.FusedOrientationProviderClient
    public final Task<Void> requestOrientationUpdates(final DeviceOrientationRequest deviceOrientationRequest, Executor executor, DeviceOrientationListener deviceOrientationListener) {
        final ListenerHolder listenerHolderCreateListenerHolder = ListenerHolders.createListenerHolder(deviceOrientationListener, executor, "DeviceOrientationListener");
        RemoteCall remoteCall = new RemoteCall() { // from class: com.google.android.gms.internal.location.zzcl
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                int i = zzci.zza;
                ((zzdz) obj).zzC(listenerHolderCreateListenerHolder, deviceOrientationRequest, (TaskCompletionSource) obj2);
            }
        };
        return doRegisterEventListener(RegistrationMethods.builder().register(remoteCall).unregister(new RemoteCall() { // from class: com.google.android.gms.internal.location.zzcj
            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final /* synthetic */ void accept(Object obj, Object obj2) throws RemoteException {
                TaskCompletionSource taskCompletionSource = (TaskCompletionSource) obj2;
                zzdz zzdzVar = (zzdz) obj;
                int i = zzci.zza;
                ListenerHolder.ListenerKey listenerKey = listenerHolderCreateListenerHolder.getListenerKey();
                if (listenerKey != null) {
                    zzdzVar.zzD(listenerKey, taskCompletionSource);
                }
            }
        }).withHolder(listenerHolderCreateListenerHolder).setMethodKey(2434).build());
    }

    public zzci(Context context) {
        super(context, (Api<Api.ApiOptions.NoOptions>) zzbi.zzb, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }
}

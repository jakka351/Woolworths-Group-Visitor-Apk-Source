package com.google.android.gms.internal.mlkit_code_scanner;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.inject.Provider;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public final class zzog implements zznr {
    private Provider zza;
    private final Provider zzb;
    private final zznt zzc;

    public zzog(Context context, zznt zzntVar) {
        this.zzc = zzntVar;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        final TransportFactory transportFactoryNewFactory = TransportRuntime.getInstance().newFactory(cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.zza = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zzod
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return transportFactoryNewFactory.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("json"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zzof
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.zzb = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zzoe
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return transportFactoryNewFactory.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zzoc
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    static Event zzb(zznt zzntVar, zznq zznqVar) {
        return Event.ofTelemetry(zznqVar.zzd(zzntVar.zza(), false));
    }

    @Override // com.google.android.gms.internal.mlkit_code_scanner.zznr
    public final void zza(zznq zznqVar) {
        if (this.zzc.zza() != 0) {
            ((Transport) this.zzb.get()).send(zzb(this.zzc, zznqVar));
            return;
        }
        Provider provider = this.zza;
        if (provider != null) {
            ((Transport) provider.get()).send(zzb(this.zzc, zznqVar));
        }
    }
}

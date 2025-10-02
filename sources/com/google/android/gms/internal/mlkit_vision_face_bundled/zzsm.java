package com.google.android.gms.internal.mlkit_vision_face_bundled;

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

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzsm implements zzrx {
    private Provider zza;
    private final Provider zzb;
    private final zzrz zzc;

    public zzsm(Context context, zzrz zzrzVar) {
        this.zzc = zzrzVar;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        final TransportFactory transportFactoryNewFactory = TransportRuntime.getInstance().newFactory(cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.zza = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.zzsj
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return transportFactoryNewFactory.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("json"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.zzsl
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.zzb = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.zzsk
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return transportFactoryNewFactory.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_face_bundled.zzsi
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzrx
    public final void zza(zzrw zzrwVar) {
        if (this.zzc.zza() != 0) {
            ((Transport) this.zzb.get()).send(zzb(this.zzc, zzrwVar));
            return;
        }
        Provider provider = this.zza;
        if (provider != null) {
            ((Transport) provider.get()).send(zzb(this.zzc, zzrwVar));
        }
    }

    static Event zzb(zzrz zzrzVar, zzrw zzrwVar) {
        int iZza = zzrzVar.zza();
        return zzrwVar.zza() != 0 ? Event.ofData(zzrwVar.zze(iZza, false)) : Event.ofTelemetry(zzrwVar.zze(iZza, false));
    }
}

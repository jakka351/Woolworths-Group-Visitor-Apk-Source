package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
final class zzgj implements ObjectEncoder {
    static final zzgj zza = new zzgj();
    private static final FieldDescriptor zzb;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("errorCode");
        zzad zzadVar = new zzad();
        zzadVar.zza(1);
        zzb = builder.withProperty(zzadVar.zzb()).build();
    }

    private zzgj() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}

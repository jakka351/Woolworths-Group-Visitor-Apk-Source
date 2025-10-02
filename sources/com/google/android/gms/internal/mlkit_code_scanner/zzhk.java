package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
final class zzhk implements ObjectEncoder {
    static final zzhk zza = new zzhk();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzad zzadVar = new zzad();
        zzadVar.zza(3);
        zzb = builder.withProperty(zzadVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzad zzadVar2 = new zzad();
        zzadVar2.zza(4);
        zzc = builder2.withProperty(zzadVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("sdkVersion");
        zzad zzadVar3 = new zzad();
        zzadVar3.zza(5);
        zzd = builder3.withProperty(zzadVar3.zzb()).build();
    }

    private zzhk() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}

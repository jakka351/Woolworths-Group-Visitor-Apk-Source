package com.google.android.gms.internal.mlkit_code_scanner;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
final class zzho implements ObjectEncoder {
    static final zzho zza = new zzho();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("pipelineNamespace");
        zzad zzadVar = new zzad();
        zzadVar.zza(1);
        zzb = builder.withProperty(zzadVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("name");
        zzad zzadVar2 = new zzad();
        zzadVar2.zza(2);
        zzc = builder2.withProperty(zzadVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("clientLibraryName");
        zzad zzadVar3 = new zzad();
        zzadVar3.zza(3);
        zzd = builder3.withProperty(zzadVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("clientLibraryVersion");
        zzad zzadVar4 = new zzad();
        zzadVar4.zza(4);
        zze = builder4.withProperty(zzadVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("minClientLibraryVersion");
        zzad zzadVar5 = new zzad();
        zzadVar5.zza(5);
        zzf = builder5.withProperty(zzadVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("maxClientLibraryVersion");
        zzad zzadVar6 = new zzad();
        zzadVar6.zza(6);
        zzg = builder6.withProperty(zzadVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("sourceProduct");
        zzad zzadVar7 = new zzad();
        zzadVar7.zza(7);
        zzh = builder7.withProperty(zzadVar7.zzb()).build();
    }

    private zzho() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}

package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzkd implements ObjectEncoder {
    static final zzkd zza = new zzkd();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("pipelineNamespace");
        zzbi zzbiVar = new zzbi();
        zzbiVar.zza(1);
        builder.withProperty(zzbiVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("name");
        zzbi zzbiVar2 = new zzbi();
        zzbiVar2.zza(2);
        builder2.withProperty(zzbiVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("clientLibraryName");
        zzbi zzbiVar3 = new zzbi();
        zzbiVar3.zza(3);
        builder3.withProperty(zzbiVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("clientLibraryVersion");
        zzbi zzbiVar4 = new zzbi();
        zzbiVar4.zza(4);
        builder4.withProperty(zzbiVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("minClientLibraryVersion");
        zzbi zzbiVar5 = new zzbi();
        zzbiVar5.zza(5);
        builder5.withProperty(zzbiVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("maxClientLibraryVersion");
        zzbi zzbiVar6 = new zzbi();
        zzbiVar6.zza(6);
        builder6.withProperty(zzbiVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("sourceProduct");
        zzbi zzbiVar7 = new zzbi();
        zzbiVar7.zza(7);
        builder7.withProperty(zzbiVar7.zzb()).build();
    }

    private zzkd() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}

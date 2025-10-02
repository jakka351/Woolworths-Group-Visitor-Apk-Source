package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzis implements ObjectEncoder {
    static final zzis zza = new zzis();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("inferenceCommonLogEvent");
        zzbi zzbiVar = new zzbi();
        zzbiVar.zza(1);
        builder.withProperty(zzbiVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("imageInfo");
        zzbi zzbiVar2 = new zzbi();
        zzbiVar2.zza(2);
        builder2.withProperty(zzbiVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("captionCount");
        zzbi zzbiVar3 = new zzbi();
        zzbiVar3.zza(4);
        builder3.withProperty(zzbiVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("highestScore");
        zzbi zzbiVar4 = new zzbi();
        zzbiVar4.zza(5);
        builder4.withProperty(zzbiVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("imageType");
        zzbi zzbiVar5 = new zzbi();
        zzbiVar5.zza(6);
        builder5.withProperty(zzbiVar5.zzb()).build();
    }

    private zzis() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}

package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzhu implements ObjectEncoder {
    static final zzhu zza = new zzhu();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("handwritingRecognitionStrategy");
        zzbi zzbiVar = new zzbi();
        zzbiVar.zza(1);
        builder.withProperty(zzbiVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("recognitionSchedulingStrategy");
        zzbi zzbiVar2 = new zzbi();
        zzbiVar2.zza(2);
        builder2.withProperty(zzbiVar2.zzb()).build();
    }

    private zzhu() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}

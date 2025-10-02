package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzjz implements ObjectEncoder {
    static final zzjz zza = new zzjz();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("languageOption");
        zzbi zzbiVar = new zzbi();
        zzbiVar.zza(3);
        builder.withProperty(zzbiVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("isUsingLegacyApi");
        zzbi zzbiVar2 = new zzbi();
        zzbiVar2.zza(4);
        builder2.withProperty(zzbiVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("sdkVersion");
        zzbi zzbiVar3 = new zzbi();
        zzbiVar3.zza(5);
        builder3.withProperty(zzbiVar3.zzb()).build();
    }

    private zzjz() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}

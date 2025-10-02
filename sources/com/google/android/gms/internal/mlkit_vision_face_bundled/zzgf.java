package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzgf implements ObjectEncoder {
    static final zzgf zza = new zzgf();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("options");
        zzbi zzbiVar = new zzbi();
        zzbiVar.zza(1);
        zzb = builder.withProperty(zzbiVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("eventType");
        zzbi zzbiVar2 = new zzbi();
        zzbiVar2.zza(2);
        zzc = builder2.withProperty(zzbiVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("errorCode");
        zzbi zzbiVar3 = new zzbi();
        zzbiVar3.zza(3);
        zzd = builder3.withProperty(zzbiVar3.zzb()).build();
    }

    private zzgf() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzmi zzmiVar = (zzmi) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzmiVar.zza());
        objectEncoderContext2.add(zzc, (Object) null);
        objectEncoderContext2.add(zzd, zzmiVar.zzb());
    }
}

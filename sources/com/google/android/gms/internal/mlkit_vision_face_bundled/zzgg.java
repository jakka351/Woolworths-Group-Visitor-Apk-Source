package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzgg implements ObjectEncoder {
    static final zzgg zza = new zzgg();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("landmarkMode");
        zzbi zzbiVar = new zzbi();
        zzbiVar.zza(1);
        zzb = builder.withProperty(zzbiVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("classificationMode");
        zzbi zzbiVar2 = new zzbi();
        zzbiVar2.zza(2);
        zzc = builder2.withProperty(zzbiVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("performanceMode");
        zzbi zzbiVar3 = new zzbi();
        zzbiVar3.zza(3);
        zzd = builder3.withProperty(zzbiVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("contourMode");
        zzbi zzbiVar4 = new zzbi();
        zzbiVar4.zza(4);
        zze = builder4.withProperty(zzbiVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("isTrackingEnabled");
        zzbi zzbiVar5 = new zzbi();
        zzbiVar5.zza(5);
        zzf = builder5.withProperty(zzbiVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("minFaceSize");
        zzbi zzbiVar6 = new zzbi();
        zzbiVar6.zza(6);
        zzg = builder6.withProperty(zzbiVar6.zzb()).build();
    }

    private zzgg() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzmp zzmpVar = (zzmp) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzmpVar.zzc());
        objectEncoderContext2.add(zzc, zzmpVar.zza());
        objectEncoderContext2.add(zzd, zzmpVar.zzd());
        objectEncoderContext2.add(zze, zzmpVar.zzb());
        objectEncoderContext2.add(zzf, zzmpVar.zze());
        objectEncoderContext2.add(zzg, zzmpVar.zzf());
    }
}

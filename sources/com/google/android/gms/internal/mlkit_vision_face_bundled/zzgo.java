package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzgo implements ObjectEncoder {
    static final zzgo zza = new zzgo();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("durationMs");
        zzbi zzbiVar = new zzbi();
        zzbiVar.zza(1);
        builder.withProperty(zzbiVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("errorCode");
        zzbi zzbiVar2 = new zzbi();
        zzbiVar2.zza(2);
        builder2.withProperty(zzbiVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("isColdCall");
        zzbi zzbiVar3 = new zzbi();
        zzbiVar3.zza(3);
        builder3.withProperty(zzbiVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("autoManageModelOnBackground");
        zzbi zzbiVar4 = new zzbi();
        zzbiVar4.zza(4);
        builder4.withProperty(zzbiVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("autoManageModelOnLowMemory");
        zzbi zzbiVar5 = new zzbi();
        zzbiVar5.zza(5);
        builder5.withProperty(zzbiVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("isNnApiEnabled");
        zzbi zzbiVar6 = new zzbi();
        zzbiVar6.zza(6);
        builder6.withProperty(zzbiVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("eventsCount");
        zzbi zzbiVar7 = new zzbi();
        zzbiVar7.zza(7);
        builder7.withProperty(zzbiVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("otherErrors");
        zzbi zzbiVar8 = new zzbi();
        zzbiVar8.zza(8);
        builder8.withProperty(zzbiVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("remoteConfigValueForAcceleration");
        zzbi zzbiVar9 = new zzbi();
        zzbiVar9.zza(9);
        builder9.withProperty(zzbiVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isAccelerated");
        zzbi zzbiVar10 = new zzbi();
        zzbiVar10.zza(10);
        builder10.withProperty(zzbiVar10.zzb()).build();
    }

    private zzgo() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}

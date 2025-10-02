package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import io.sentry.SentryEvent;
import io.sentry.protocol.Device;
import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzfv implements ObjectEncoder {
    static final zzfv zza = new zzfv();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("sdkVersion");
        zzbi zzbiVar = new zzbi();
        zzbiVar.zza(1);
        builder.withProperty(zzbiVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("osBuild");
        zzbi zzbiVar2 = new zzbi();
        zzbiVar2.zza(2);
        builder2.withProperty(zzbiVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder(Device.JsonKeys.BRAND);
        zzbi zzbiVar3 = new zzbi();
        zzbiVar3.zza(3);
        builder3.withProperty(zzbiVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder(Device.TYPE);
        zzbi zzbiVar4 = new zzbi();
        zzbiVar4.zza(4);
        builder4.withProperty(zzbiVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("hardware");
        zzbi zzbiVar5 = new zzbi();
        zzbiVar5.zza(5);
        builder5.withProperty(zzbiVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder(Device.JsonKeys.MANUFACTURER);
        zzbi zzbiVar6 = new zzbi();
        zzbiVar6.zza(6);
        builder6.withProperty(zzbiVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(Device.JsonKeys.MODEL);
        zzbi zzbiVar7 = new zzbi();
        zzbiVar7.zza(7);
        builder7.withProperty(zzbiVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("product");
        zzbi zzbiVar8 = new zzbi();
        zzbiVar8.zza(8);
        builder8.withProperty(zzbiVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("soc");
        zzbi zzbiVar9 = new zzbi();
        zzbiVar9.zza(9);
        builder9.withProperty(zzbiVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("socMetaBuildId");
        zzbi zzbiVar10 = new zzbi();
        zzbiVar10.zza(10);
        builder10.withProperty(zzbiVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder(SentryEvent.JsonKeys.FINGERPRINT);
        zzbi zzbiVar11 = new zzbi();
        zzbiVar11.zza(11);
        builder11.withProperty(zzbiVar11.zzb()).build();
    }

    private zzfv() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}

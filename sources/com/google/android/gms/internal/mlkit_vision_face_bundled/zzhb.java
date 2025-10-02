package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import io.sentry.rrweb.RRWebVideoEvent;
import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzhb implements ObjectEncoder {
    static final zzhb zza = new zzhb();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("name");
        zzbi zzbiVar = new zzbi();
        zzbiVar.zza(1);
        builder.withProperty(zzbiVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("version");
        zzbi zzbiVar2 = new zzbi();
        zzbiVar2.zza(2);
        builder2.withProperty(zzbiVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("source");
        zzbi zzbiVar3 = new zzbi();
        zzbiVar3.zza(3);
        builder3.withProperty(zzbiVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder(ReactNativeBlobUtilConst.DATA_ENCODE_URI);
        zzbi zzbiVar4 = new zzbi();
        zzbiVar4.zza(4);
        builder4.withProperty(zzbiVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("hash");
        zzbi zzbiVar5 = new zzbi();
        zzbiVar5.zza(5);
        builder5.withProperty(zzbiVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("modelType");
        zzbi zzbiVar6 = new zzbi();
        zzbiVar6.zza(6);
        builder6.withProperty(zzbiVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder(RRWebVideoEvent.JsonKeys.SIZE);
        zzbi zzbiVar7 = new zzbi();
        zzbiVar7.zza(7);
        builder7.withProperty(zzbiVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("hasLabelMap");
        zzbi zzbiVar8 = new zzbi();
        zzbiVar8.zza(8);
        builder8.withProperty(zzbiVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("isManifestModel");
        zzbi zzbiVar9 = new zzbi();
        zzbiVar9.zza(9);
        builder9.withProperty(zzbiVar9.zzb()).build();
    }

    private zzhb() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}

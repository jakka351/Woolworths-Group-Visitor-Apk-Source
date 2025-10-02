package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzgc implements ObjectEncoder {
    static final zzgc zza = new zzgc();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("initialImageUriCount");
        zzbi zzbiVar = new zzbi();
        zzbiVar.zza(1);
        builder.withProperty(zzbiVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("defaultCaptureMode");
        zzbi zzbiVar2 = new zzbi();
        zzbiVar2.zza(2);
        builder2.withProperty(zzbiVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("flashModeChangeAllowed");
        zzbi zzbiVar3 = new zzbi();
        zzbiVar3.zza(3);
        builder3.withProperty(zzbiVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("galleryImportAllowed");
        zzbi zzbiVar4 = new zzbi();
        zzbiVar4.zza(4);
        builder4.withProperty(zzbiVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("multiPageAllowed");
        zzbi zzbiVar5 = new zzbi();
        zzbiVar5.zza(5);
        builder5.withProperty(zzbiVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("filterAllowed");
        zzbi zzbiVar6 = new zzbi();
        zzbiVar6.zza(6);
        builder6.withProperty(zzbiVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("targetResolutionWidth");
        zzbi zzbiVar7 = new zzbi();
        zzbiVar7.zza(7);
        builder7.withProperty(zzbiVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("targetResolutionHeight");
        zzbi zzbiVar8 = new zzbi();
        zzbiVar8.zza(8);
        builder8.withProperty(zzbiVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("resultFormats");
        zzbi zzbiVar9 = new zzbi();
        zzbiVar9.zza(9);
        builder9.withProperty(zzbiVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("pageEditListenerSet");
        zzbi zzbiVar10 = new zzbi();
        zzbiVar10.zza(10);
        builder10.withProperty(zzbiVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("shadowRemovalAllowed");
        zzbi zzbiVar11 = new zzbi();
        zzbiVar11.zza(11);
        builder11.withProperty(zzbiVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("stainRemovalAllowed");
        zzbi zzbiVar12 = new zzbi();
        zzbiVar12.zza(12);
        builder12.withProperty(zzbiVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("enableAllNewFeaturesByDefault");
        zzbi zzbiVar13 = new zzbi();
        zzbiVar13.zza(13);
        builder13.withProperty(zzbiVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("pageLimitMax");
        zzbi zzbiVar14 = new zzbi();
        zzbiVar14.zza(14);
        builder14.withProperty(zzbiVar14.zzb()).build();
        FieldDescriptor.Builder builder15 = FieldDescriptor.builder("enableGalleryImportAutoTransform");
        zzbi zzbiVar15 = new zzbi();
        zzbiVar15.zza(15);
        builder15.withProperty(zzbiVar15.zzb()).build();
    }

    private zzgc() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}

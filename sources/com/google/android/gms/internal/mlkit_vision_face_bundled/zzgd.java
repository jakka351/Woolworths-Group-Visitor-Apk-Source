package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzgd implements ObjectEncoder {
    static final zzgd zza = new zzgd();

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("source");
        zzbi zzbiVar = new zzbi();
        zzbiVar.zza(1);
        builder.withProperty(zzbiVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("appliedFilter");
        zzbi zzbiVar2 = new zzbi();
        zzbiVar2.zza(2);
        builder2.withProperty(zzbiVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("isAutoCaptureManuallyTriggered");
        zzbi zzbiVar3 = new zzbi();
        zzbiVar3.zza(3);
        builder3.withProperty(zzbiVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("isRotated");
        zzbi zzbiVar4 = new zzbi();
        zzbiVar4.zza(4);
        builder4.withProperty(zzbiVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("hasLowConfidenceProposedCorners");
        zzbi zzbiVar5 = new zzbi();
        zzbiVar5.zza(5);
        builder5.withProperty(zzbiVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("autoCaptureTriggerLatencyMs");
        zzbi zzbiVar6 = new zzbi();
        zzbiVar6.zza(6);
        builder6.withProperty(zzbiVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("galleryImportProcessingMs");
        zzbi zzbiVar7 = new zzbi();
        zzbiVar7.zza(7);
        builder7.withProperty(zzbiVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("imageWidth");
        zzbi zzbiVar8 = new zzbi();
        zzbiVar8.zza(8);
        builder8.withProperty(zzbiVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("imageHeight");
        zzbi zzbiVar9 = new zzbi();
        zzbiVar9.zza(9);
        builder9.withProperty(zzbiVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("proposedCorners");
        zzbi zzbiVar10 = new zzbi();
        zzbiVar10.zza(10);
        builder10.withProperty(zzbiVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("adjustedCorners");
        zzbi zzbiVar11 = new zzbi();
        zzbiVar11.zza(11);
        builder11.withProperty(zzbiVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isShadowRemoved");
        zzbi zzbiVar12 = new zzbi();
        zzbiVar12.zza(12);
        builder12.withProperty(zzbiVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("numOfAppliedCleanUpStrokes");
        zzbi zzbiVar13 = new zzbi();
        zzbiVar13.zza(13);
        builder13.withProperty(zzbiVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("numOfAttemptedCleanUpStrokes");
        zzbi zzbiVar14 = new zzbi();
        zzbiVar14.zza(14);
        builder14.withProperty(zzbiVar14.zzb()).build();
    }

    private zzgd() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        throw null;
    }
}

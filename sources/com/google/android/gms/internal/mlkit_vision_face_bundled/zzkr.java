package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.io.IOException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
final class zzkr implements ObjectEncoder {
    static final zzkr zza = new zzkr();
    private static final FieldDescriptor zzb;
    private static final FieldDescriptor zzc;
    private static final FieldDescriptor zzd;
    private static final FieldDescriptor zze;
    private static final FieldDescriptor zzf;
    private static final FieldDescriptor zzg;
    private static final FieldDescriptor zzh;
    private static final FieldDescriptor zzi;
    private static final FieldDescriptor zzj;
    private static final FieldDescriptor zzk;
    private static final FieldDescriptor zzl;
    private static final FieldDescriptor zzm;
    private static final FieldDescriptor zzn;
    private static final FieldDescriptor zzo;

    static {
        FieldDescriptor.Builder builder = FieldDescriptor.builder("appId");
        zzbi zzbiVar = new zzbi();
        zzbiVar.zza(1);
        zzb = builder.withProperty(zzbiVar.zzb()).build();
        FieldDescriptor.Builder builder2 = FieldDescriptor.builder("appVersion");
        zzbi zzbiVar2 = new zzbi();
        zzbiVar2.zza(2);
        zzc = builder2.withProperty(zzbiVar2.zzb()).build();
        FieldDescriptor.Builder builder3 = FieldDescriptor.builder("firebaseProjectId");
        zzbi zzbiVar3 = new zzbi();
        zzbiVar3.zza(3);
        zzd = builder3.withProperty(zzbiVar3.zzb()).build();
        FieldDescriptor.Builder builder4 = FieldDescriptor.builder("mlSdkVersion");
        zzbi zzbiVar4 = new zzbi();
        zzbiVar4.zza(4);
        zze = builder4.withProperty(zzbiVar4.zzb()).build();
        FieldDescriptor.Builder builder5 = FieldDescriptor.builder("tfliteSchemaVersion");
        zzbi zzbiVar5 = new zzbi();
        zzbiVar5.zza(5);
        zzf = builder5.withProperty(zzbiVar5.zzb()).build();
        FieldDescriptor.Builder builder6 = FieldDescriptor.builder("gcmSenderId");
        zzbi zzbiVar6 = new zzbi();
        zzbiVar6.zza(6);
        zzg = builder6.withProperty(zzbiVar6.zzb()).build();
        FieldDescriptor.Builder builder7 = FieldDescriptor.builder("apiKey");
        zzbi zzbiVar7 = new zzbi();
        zzbiVar7.zza(7);
        zzh = builder7.withProperty(zzbiVar7.zzb()).build();
        FieldDescriptor.Builder builder8 = FieldDescriptor.builder("languages");
        zzbi zzbiVar8 = new zzbi();
        zzbiVar8.zza(8);
        zzi = builder8.withProperty(zzbiVar8.zzb()).build();
        FieldDescriptor.Builder builder9 = FieldDescriptor.builder("mlSdkInstanceId");
        zzbi zzbiVar9 = new zzbi();
        zzbiVar9.zza(9);
        zzj = builder9.withProperty(zzbiVar9.zzb()).build();
        FieldDescriptor.Builder builder10 = FieldDescriptor.builder("isClearcutClient");
        zzbi zzbiVar10 = new zzbi();
        zzbiVar10.zza(10);
        zzk = builder10.withProperty(zzbiVar10.zzb()).build();
        FieldDescriptor.Builder builder11 = FieldDescriptor.builder("isStandaloneMlkit");
        zzbi zzbiVar11 = new zzbi();
        zzbiVar11.zza(11);
        zzl = builder11.withProperty(zzbiVar11.zzb()).build();
        FieldDescriptor.Builder builder12 = FieldDescriptor.builder("isJsonLogging");
        zzbi zzbiVar12 = new zzbi();
        zzbiVar12.zza(12);
        zzm = builder12.withProperty(zzbiVar12.zzb()).build();
        FieldDescriptor.Builder builder13 = FieldDescriptor.builder("buildLevel");
        zzbi zzbiVar13 = new zzbi();
        zzbiVar13.zza(13);
        zzn = builder13.withProperty(zzbiVar13.zzb()).build();
        FieldDescriptor.Builder builder14 = FieldDescriptor.builder("optionalModuleVersion");
        zzbi zzbiVar14 = new zzbi();
        zzbiVar14.zza(14);
        zzo = builder14.withProperty(zzbiVar14.zzb()).build();
    }

    private zzkr() {
    }

    @Override // com.google.firebase.encoders.Encoder
    public final /* bridge */ /* synthetic */ void encode(Object obj, ObjectEncoderContext objectEncoderContext) throws IOException {
        zzqy zzqyVar = (zzqy) obj;
        ObjectEncoderContext objectEncoderContext2 = objectEncoderContext;
        objectEncoderContext2.add(zzb, zzqyVar.zzg());
        objectEncoderContext2.add(zzc, zzqyVar.zzh());
        objectEncoderContext2.add(zzd, (Object) null);
        objectEncoderContext2.add(zze, zzqyVar.zzj());
        objectEncoderContext2.add(zzf, zzqyVar.zzk());
        objectEncoderContext2.add(zzg, (Object) null);
        objectEncoderContext2.add(zzh, (Object) null);
        objectEncoderContext2.add(zzi, zzqyVar.zza());
        objectEncoderContext2.add(zzj, zzqyVar.zzi());
        objectEncoderContext2.add(zzk, zzqyVar.zzb());
        objectEncoderContext2.add(zzl, zzqyVar.zzd());
        objectEncoderContext2.add(zzm, zzqyVar.zzc());
        objectEncoderContext2.add(zzn, zzqyVar.zze());
        objectEncoderContext2.add(zzo, zzqyVar.zzf());
    }
}

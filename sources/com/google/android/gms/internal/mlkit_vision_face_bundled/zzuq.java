package com.google.android.gms.internal.mlkit_vision_face_bundled;

import com.google.android.gms.internal.mlkit_vision_face_bundled.zzuq;
import com.google.android.gms.internal.mlkit_vision_face_bundled.zzuw;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public class zzuq<MessageType extends zzuw<MessageType, BuilderType>, BuilderType extends zzuq<MessageType, BuilderType>> extends zzte<MessageType, BuilderType> {
    protected zzuw zza;
    private final zzuw zzb;

    protected zzuq(MessageType messagetype) {
        this.zzb = messagetype;
        if (messagetype.zzI()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.zza = messagetype.zzy();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzte
    /* renamed from: zzm, reason: merged with bridge method [inline-methods] */
    public final zzuq clone() {
        zzuq zzuqVar = (zzuq) this.zzb.zzf(5, null, null);
        zzuqVar.zza = zzp();
        return zzuqVar;
    }

    public final MessageType zzn() {
        MessageType messagetype = (MessageType) zzp();
        if (zzuw.zzH(messagetype, true)) {
            return messagetype;
        }
        throw new zzwu(messagetype);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzvv
    /* renamed from: zzo, reason: merged with bridge method [inline-methods] */
    public MessageType zzp() {
        if (!this.zza.zzI()) {
            return (MessageType) this.zza;
        }
        this.zza.zzD();
        return (MessageType) this.zza;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzvx
    public final /* bridge */ /* synthetic */ zzvw zzq() {
        throw null;
    }

    protected final void zzr() {
        if (this.zza.zzI()) {
            return;
        }
        zzs();
    }

    protected void zzs() {
        zzuw zzuwVarZzy = this.zzb.zzy();
        zzwe.zza().zzb(zzuwVarZzy.getClass()).zzg(zzuwVarZzy, this.zza);
        this.zza = zzuwVarZzy;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_face_bundled.zzvx
    public final boolean zzt() {
        return zzuw.zzH(this.zza, false);
    }
}

package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public enum zzxg {
    DOUBLE(zzxh.DOUBLE, 1),
    FLOAT(zzxh.FLOAT, 5),
    INT64(zzxh.LONG, 0),
    UINT64(zzxh.LONG, 0),
    INT32(zzxh.INT, 0),
    FIXED64(zzxh.LONG, 1),
    FIXED32(zzxh.INT, 5),
    BOOL(zzxh.BOOLEAN, 0),
    STRING(zzxh.STRING, 2),
    GROUP(zzxh.MESSAGE, 3),
    MESSAGE(zzxh.MESSAGE, 2),
    BYTES(zzxh.BYTE_STRING, 2),
    UINT32(zzxh.INT, 0),
    ENUM(zzxh.ENUM, 0),
    SFIXED32(zzxh.INT, 5),
    SFIXED64(zzxh.LONG, 1),
    SINT32(zzxh.INT, 0),
    SINT64(zzxh.LONG, 0);

    private final zzxh zzt;

    zzxg(zzxh zzxhVar, int i) {
        this.zzt = zzxhVar;
    }

    public final zzxh zza() {
        return this.zzt;
    }
}

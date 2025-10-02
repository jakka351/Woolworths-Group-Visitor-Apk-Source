package com.google.android.gms.internal.mlkit_vision_face_bundled;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public enum zzun {
    DOUBLE(0, 1, zzvf.DOUBLE),
    FLOAT(1, 1, zzvf.FLOAT),
    INT64(2, 1, zzvf.LONG),
    UINT64(3, 1, zzvf.LONG),
    INT32(4, 1, zzvf.INT),
    FIXED64(5, 1, zzvf.LONG),
    FIXED32(6, 1, zzvf.INT),
    BOOL(7, 1, zzvf.BOOLEAN),
    STRING(8, 1, zzvf.STRING),
    MESSAGE(9, 1, zzvf.MESSAGE),
    BYTES(10, 1, zzvf.BYTE_STRING),
    UINT32(11, 1, zzvf.INT),
    ENUM(12, 1, zzvf.ENUM),
    SFIXED32(13, 1, zzvf.INT),
    SFIXED64(14, 1, zzvf.LONG),
    SINT32(15, 1, zzvf.INT),
    SINT64(16, 1, zzvf.LONG),
    GROUP(17, 1, zzvf.MESSAGE),
    DOUBLE_LIST(18, 2, zzvf.DOUBLE),
    FLOAT_LIST(19, 2, zzvf.FLOAT),
    INT64_LIST(20, 2, zzvf.LONG),
    UINT64_LIST(21, 2, zzvf.LONG),
    INT32_LIST(22, 2, zzvf.INT),
    FIXED64_LIST(23, 2, zzvf.LONG),
    FIXED32_LIST(24, 2, zzvf.INT),
    BOOL_LIST(25, 2, zzvf.BOOLEAN),
    STRING_LIST(26, 2, zzvf.STRING),
    MESSAGE_LIST(27, 2, zzvf.MESSAGE),
    BYTES_LIST(28, 2, zzvf.BYTE_STRING),
    UINT32_LIST(29, 2, zzvf.INT),
    ENUM_LIST(30, 2, zzvf.ENUM),
    SFIXED32_LIST(31, 2, zzvf.INT),
    SFIXED64_LIST(32, 2, zzvf.LONG),
    SINT32_LIST(33, 2, zzvf.INT),
    SINT64_LIST(34, 2, zzvf.LONG),
    DOUBLE_LIST_PACKED(35, 3, zzvf.DOUBLE),
    FLOAT_LIST_PACKED(36, 3, zzvf.FLOAT),
    INT64_LIST_PACKED(37, 3, zzvf.LONG),
    UINT64_LIST_PACKED(38, 3, zzvf.LONG),
    INT32_LIST_PACKED(39, 3, zzvf.INT),
    FIXED64_LIST_PACKED(40, 3, zzvf.LONG),
    FIXED32_LIST_PACKED(41, 3, zzvf.INT),
    BOOL_LIST_PACKED(42, 3, zzvf.BOOLEAN),
    UINT32_LIST_PACKED(43, 3, zzvf.INT),
    ENUM_LIST_PACKED(44, 3, zzvf.ENUM),
    SFIXED32_LIST_PACKED(45, 3, zzvf.INT),
    SFIXED64_LIST_PACKED(46, 3, zzvf.LONG),
    SINT32_LIST_PACKED(47, 3, zzvf.INT),
    SINT64_LIST_PACKED(48, 3, zzvf.LONG),
    GROUP_LIST(49, 2, zzvf.MESSAGE),
    MAP(50, 4, zzvf.VOID);

    private static final zzun[] zzZ;
    private final int zzab;

    static {
        zzun[] zzunVarArrValues = values();
        zzZ = new zzun[zzunVarArrValues.length];
        for (zzun zzunVar : zzunVarArrValues) {
            zzZ[zzunVar.zzab] = zzunVar;
        }
    }

    zzun(int i, int i2, zzvf zzvfVar) {
        this.zzab = i;
        int i3 = i2 - 1;
        if (i3 == 1 || i3 == 3) {
            zzvfVar.zza();
        }
        if (i2 == 1) {
            zzvf zzvfVar2 = zzvf.VOID;
            zzvfVar.ordinal();
        }
    }

    public final int zza() {
        return this.zzab;
    }
}

package com.google.android.gms.internal.mlkit_vision_face_bundled;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.facebook.imageutils.JfifUtil;
import com.google.mlkit.common.MlKitException;

/* compiled from: com.google.mlkit:face-detection@@16.1.7 */
/* loaded from: classes3.dex */
public final class zzxw {
    public static int zza(int i) {
        if (i == 200) {
            return MlKitException.CODE_SCANNER_CANCELLED;
        }
        if (i == 300) {
            return 301;
        }
        if (i == 302) {
            return 303;
        }
        if (i == 312) {
            return 313;
        }
        if (i == 15000) {
            return 15001;
        }
        if (i == 304) {
            return 305;
        }
        if (i == 305) {
            return 306;
        }
        switch (i) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            default:
                switch (i) {
                    case 9:
                        return 10;
                    case 10:
                        return 11;
                    case 11:
                        return 12;
                    case 12:
                        return 13;
                    case 13:
                        return 14;
                    default:
                        switch (i) {
                            case 43:
                                return 44;
                            case 44:
                                return 45;
                            case 45:
                                return 46;
                            default:
                                switch (i) {
                                    case 220:
                                        return 221;
                                    case 221:
                                        return 222;
                                    case 222:
                                        return 223;
                                    case 223:
                                        return 224;
                                    case 224:
                                        return JfifUtil.MARKER_APP1;
                                    case JfifUtil.MARKER_APP1 /* 225 */:
                                        return 226;
                                    case 226:
                                        return 227;
                                    case 227:
                                        return 228;
                                    default:
                                        switch (i) {
                                            case 238:
                                                return 239;
                                            case 239:
                                                return 240;
                                            case 240:
                                                return 241;
                                            case 241:
                                                return 242;
                                            case 242:
                                                return 243;
                                            case 243:
                                                return 244;
                                            default:
                                                switch (i) {
                                                    case 314:
                                                        return 315;
                                                    case 315:
                                                        return TypedValues.AttributesType.TYPE_PATH_ROTATE;
                                                    case TypedValues.AttributesType.TYPE_PATH_ROTATE /* 316 */:
                                                        return TypedValues.AttributesType.TYPE_EASING;
                                                    default:
                                                        return 0;
                                                }
                                        }
                                }
                        }
                }
        }
    }
}

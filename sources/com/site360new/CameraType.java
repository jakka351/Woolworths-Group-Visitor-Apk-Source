package com.site360new;

import androidx.camera.core.CameraSelector;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraActivity.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/site360new/CameraType;", "", "(Ljava/lang/String;I)V", "cameraSelector", "Landroidx/camera/core/CameraSelector;", "FACE", "CARD", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public enum CameraType {
    FACE { // from class: com.site360new.CameraType.FACE
        @Override // com.site360new.CameraType
        public CameraSelector cameraSelector() {
            CameraSelector DEFAULT_FRONT_CAMERA = CameraSelector.DEFAULT_FRONT_CAMERA;
            Intrinsics.checkNotNullExpressionValue(DEFAULT_FRONT_CAMERA, "DEFAULT_FRONT_CAMERA");
            return DEFAULT_FRONT_CAMERA;
        }
    },
    CARD { // from class: com.site360new.CameraType.CARD
        @Override // com.site360new.CameraType
        public CameraSelector cameraSelector() {
            CameraSelector DEFAULT_BACK_CAMERA = CameraSelector.DEFAULT_BACK_CAMERA;
            Intrinsics.checkNotNullExpressionValue(DEFAULT_BACK_CAMERA, "DEFAULT_BACK_CAMERA");
            return DEFAULT_BACK_CAMERA;
        }
    };

    /* synthetic */ CameraType(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract CameraSelector cameraSelector();
}

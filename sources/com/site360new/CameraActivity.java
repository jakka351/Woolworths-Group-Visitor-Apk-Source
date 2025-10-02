package com.site360new;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.view.LifecycleCameraController;
import androidx.camera.view.PreviewView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import com.facebook.hermes.intl.Constants;
import io.sentry.Sentry;
import io.sentry.protocol.App;
import java.io.File;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CameraActivity.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J-\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\bH\u0002J\b\u0010\u001b\u001a\u00020\bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/site360new/CameraActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "cameraController", "Landroidx/camera/view/LifecycleCameraController;", "type", "Lcom/site360new/CameraType;", "attachBaseContext", "", Constants.SENSITIVITY_BASE, "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "requestCode", "", App.JsonKeys.APP_PERMISSIONS, "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onWindowFocusChanged", "hasFocus", "", "startCamera", "takePhoto", "Companion", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraActivity extends AppCompatActivity {
    public static final int CAMERA_PERMISSION_REQUEST = 1;
    public static final String ERROR = "error";
    private static final String TAG = "CameraActivity";
    private LifecycleCameraController cameraController;
    private CameraType type;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Serializable serializableExtra = getIntent().getSerializableExtra("type");
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.site360new.CameraType");
        this.type = (CameraType) serializableExtra;
        setContentView(R.layout.activity_camera);
        if (ContextCompat.checkSelfPermission(getBaseContext(), "android.permission.CAMERA") == 0) {
            startCamera();
        } else {
            requestPermissions(new String[]{"android.permission.CAMERA"}, 1);
        }
        ((Button) findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.site360new.CameraActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraActivity.onCreate$lambda$0(this.f$0, view);
            }
        });
        ((Button) findViewById(R.id.btn_camera)).setOnClickListener(new View.OnClickListener() { // from class: com.site360new.CameraActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CameraActivity.onCreate$lambda$1(this.f$0, view);
            }
        });
        CameraType cameraType = this.type;
        if (cameraType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("type");
            cameraType = null;
        }
        if (cameraType != CameraType.CARD) {
            ((ImageView) findViewById(R.id.card_cutout)).setVisibility(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(CameraActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setResult(0);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(CameraActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.takePhoto();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleService.INSTANCE.updateBaseContextLocale(base));
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        CameraType cameraType = this.type;
        if (cameraType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("type");
            cameraType = null;
        }
        if (cameraType == CameraType.FACE) {
            ((CardView) findViewById(R.id.card_view)).setRadius(r3.getWidth() / 2);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == 0) {
                startCamera();
            } else {
                setResult(0);
                finish();
            }
        }
    }

    private final void startCamera() {
        LifecycleCameraController lifecycleCameraController = new LifecycleCameraController(getBaseContext());
        this.cameraController = lifecycleCameraController;
        lifecycleCameraController.bindToLifecycle(this);
        LifecycleCameraController lifecycleCameraController2 = this.cameraController;
        if (lifecycleCameraController2 != null) {
            CameraType cameraType = this.type;
            if (cameraType == null) {
                Intrinsics.throwUninitializedPropertyAccessException("type");
                cameraType = null;
            }
            lifecycleCameraController2.setCameraSelector(cameraType.cameraSelector());
        }
        ((PreviewView) findViewById(R.id.view_finder)).setController(this.cameraController);
    }

    private final void takePhoto() {
        LifecycleCameraController lifecycleCameraController = this.cameraController;
        if (lifecycleCameraController == null) {
            return;
        }
        ImageCapture.OutputFileOptions outputFileOptionsBuild = new ImageCapture.OutputFileOptions.Builder(File.createTempFile("photo", ".jpg")).build();
        Intrinsics.checkNotNullExpressionValue(outputFileOptionsBuild, "Builder(file)\n            .build()");
        lifecycleCameraController.takePicture(outputFileOptionsBuild, ContextCompat.getMainExecutor(this), new ImageCapture.OnImageSavedCallback() { // from class: com.site360new.CameraActivity.takePhoto.1
            @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
            public void onError(ImageCaptureException exc) {
                Intrinsics.checkNotNullParameter(exc, "exc");
                Sentry.captureException(exc);
                Intent intent = new Intent();
                intent.putExtra("error", ErrorKey.PHOTO_PROCESSING.getValue());
                CameraActivity.this.setResult(0, intent);
                CameraActivity.this.finish();
            }

            @Override // androidx.camera.core.ImageCapture.OnImageSavedCallback
            public void onImageSaved(ImageCapture.OutputFileResults output) {
                Intrinsics.checkNotNullParameter(output, "output");
                Intent intent = new Intent();
                intent.putExtra(ResultKey.FILE.getValue(), String.valueOf(output.getSavedUri()));
                CameraActivity.this.setResult(-1, intent);
                CameraActivity.this.finish();
            }
        });
    }
}

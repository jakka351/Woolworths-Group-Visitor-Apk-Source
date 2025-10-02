package com.site360new;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import io.sentry.Sentry;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.maplibre.turf.TurfConstants;

/* compiled from: RNPhotoPicker.kt */
@Metadata(d1 = {"\u0000S\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n*\u0001\u0006\u0018\u0000 $2\u00020\u0001:\u0001$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J \u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0007J(\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0007J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u0015H\u0002J\b\u0010\u001e\u001a\u00020\u0015H\u0016J\u000e\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u0015J\u0018\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u0018H\u0002J\u0010\u0010#\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0007R\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/site360new/RNPhotoPicker;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "activityEventListener", "com/site360new/RNPhotoPicker$activityEventListener$1", "Lcom/site360new/RNPhotoPicker$activityEventListener$1;", "callback", "Lcom/facebook/react/bridge/Callback;", "errorCallback", "profilePhoto", "", "analyse", "", "imageBitmap", "Landroid/graphics/Bitmap;", "results", "Lcom/facebook/react/bridge/WritableMap;", "analysePhoto", "base64Photo", "", "choosePhoto", "source", "", "createBitmap", "imageUri", "Landroid/net/Uri;", "createFileMetadata", ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH, "getName", "processPhoto", "rotateBitmap", "bitmap", TurfConstants.UNIT_DEGREES, "scanQrCode", "Companion", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RNPhotoPicker extends ReactContextBaseJavaModule {
    public static final int CAMERA_REQUEST = 1;
    public static final int PICKER_REQUEST = 0;
    private static final String TAG = "RNPhotoPicker";
    private final RNPhotoPicker$activityEventListener$1 activityEventListener;
    private Callback callback;
    private Callback errorCallback;
    private boolean profilePhoto;
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return TAG;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.site360new.RNPhotoPicker$activityEventListener$1] */
    public RNPhotoPicker(ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        ?? r0 = new BaseActivityEventListener() { // from class: com.site360new.RNPhotoPicker$activityEventListener$1
            @Override // com.facebook.react.bridge.BaseActivityEventListener, com.facebook.react.bridge.ActivityEventListener
            public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) throws FileNotFoundException {
                String strValueOf;
                String stringExtra;
                String stringExtra2;
                Unit unit;
                if (requestCode == 0) {
                    if (intent == null || (strValueOf = String.valueOf(intent.getData())) == null) {
                        strValueOf = "";
                    }
                    if (!(strValueOf.length() > 0)) {
                        Callback callback = this.this$0.callback;
                        if (callback != null) {
                            callback.invoke(new WritableNativeMap());
                            return;
                        }
                        return;
                    }
                    this.this$0.processPhoto(strValueOf);
                    return;
                }
                if (requestCode != 1) {
                    return;
                }
                if (resultCode == -1) {
                    if (intent == null || (stringExtra = intent.getStringExtra(ResultKey.FILE.getValue())) == null) {
                        return;
                    }
                    this.this$0.processPhoto(stringExtra);
                    return;
                }
                if (resultCode != 0) {
                    return;
                }
                if (intent != null && (stringExtra2 = intent.getStringExtra("error")) != null) {
                    Callback callback2 = this.this$0.errorCallback;
                    if (callback2 != null) {
                        callback2.invoke(stringExtra2);
                        unit = Unit.INSTANCE;
                    } else {
                        unit = null;
                    }
                    if (unit != null) {
                        return;
                    }
                }
                Callback callback3 = this.this$0.callback;
                if (callback3 != null) {
                    callback3.invoke(new WritableNativeMap());
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        };
        this.activityEventListener = r0;
        reactContext.addActivityEventListener((ActivityEventListener) r0);
    }

    @ReactMethod
    public final void scanQrCode(final Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        GmsBarcodeScannerOptions gmsBarcodeScannerOptionsBuild = new GmsBarcodeScannerOptions.Builder().setBarcodeFormats(256, new int[0]).enableAutoZoom().build();
        Intrinsics.checkNotNullExpressionValue(gmsBarcodeScannerOptionsBuild, "Builder()\n            .s…om()\n            .build()");
        GmsBarcodeScanner client = GmsBarcodeScanning.getClient(this.reactContext, gmsBarcodeScannerOptionsBuild);
        Intrinsics.checkNotNullExpressionValue(client, "getClient(reactContext, options)");
        Task<Barcode> taskStartScan = client.startScan();
        final Function1<Barcode, Unit> function1 = new Function1<Barcode, Unit>() { // from class: com.site360new.RNPhotoPicker.scanQrCode.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Barcode barcode) {
                invoke2(barcode);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Barcode barcode) {
                callback.invoke(barcode.getRawValue());
            }
        };
        taskStartScan.addOnSuccessListener(new OnSuccessListener() { // from class: com.site360new.RNPhotoPicker$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                RNPhotoPicker.scanQrCode$lambda$0(function1, obj);
            }
        }).addOnCanceledListener(new OnCanceledListener() { // from class: com.site360new.RNPhotoPicker$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCanceledListener
            public final void onCanceled() {
                RNPhotoPicker.scanQrCode$lambda$1(callback);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.site360new.RNPhotoPicker$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                RNPhotoPicker.scanQrCode$lambda$2(callback, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scanQrCode$lambda$0(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scanQrCode$lambda$1(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        callback.invoke(new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void scanQrCode$lambda$2(Callback callback, Exception e) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullParameter(e, "e");
        callback.invoke(new Object[0]);
    }

    @ReactMethod
    public final void choosePhoto(int source, boolean profilePhoto, Callback callback, Callback errorCallback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        this.profilePhoto = profilePhoto;
        this.callback = callback;
        this.errorCallback = errorCallback;
        if (source == 0) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("image/*");
            Activity currentActivity = getCurrentActivity();
            if (currentActivity != null) {
                currentActivity.startActivityForResult(intent, 0);
                return;
            }
            return;
        }
        Intent intent2 = new Intent(getCurrentActivity(), (Class<?>) CameraActivity.class);
        intent2.putExtra("type", profilePhoto ? CameraType.FACE : CameraType.CARD);
        Activity currentActivity2 = getCurrentActivity();
        if (currentActivity2 != null) {
            currentActivity2.startActivityForResult(intent2, 1);
        }
    }

    @ReactMethod
    public final void analysePhoto(String base64Photo, Callback callback, Callback errorCallback) {
        Intrinsics.checkNotNullParameter(base64Photo, "base64Photo");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        this.callback = callback;
        this.errorCallback = errorCallback;
        try {
            byte[] bArrDecode = Base64.decode(new Regex("^data:.*?;base64,").replace(base64Photo, ""), 0);
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
            if (bitmapDecodeByteArray != null) {
                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString(ResultKey.FILE.getValue(), base64Photo);
                writableNativeMap.putString(ResultKey.FILE_TYPE.getValue(), "base64");
                analyse(bitmapDecodeByteArray, writableNativeMap);
            } else {
                errorCallback.invoke(ErrorKey.PHOTO_PROCESSING.getValue());
            }
        } catch (Exception e) {
            Sentry.captureException(e);
            errorCallback.invoke(ErrorKey.PHOTO_PROCESSING.getValue());
        }
    }

    public final void processPhoto(String path) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(path, "path");
        Uri imageUri = Uri.parse(path);
        Intrinsics.checkNotNullExpressionValue(imageUri, "imageUri");
        Bitmap bitmapCreateBitmap = createBitmap(imageUri);
        if (bitmapCreateBitmap == null) {
            Callback callback = this.errorCallback;
            if (callback != null) {
                callback.invoke(ErrorKey.PHOTO_PROCESSING.getValue());
                return;
            }
            return;
        }
        if (bitmapCreateBitmap.getWidth() > 1400 || bitmapCreateBitmap.getHeight() > 1400) {
            double width = bitmapCreateBitmap.getWidth() / bitmapCreateBitmap.getHeight();
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCreateBitmap, (width > 1.0d ? 1400 : Double.valueOf(1400 * width)).intValue(), (width < 1.0d ? 1400 : Double.valueOf(1400 / width)).intValue(), true);
            Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, "createScaledBitmap(image…ewWidth, newHeight, true)");
            File file = new File(this.reactContext.getCacheDir(), UUID.randomUUID().toString() + ".jpg");
            String string = file.toURI().toString();
            Intrinsics.checkNotNullExpressionValue(string, "file.toURI().toString()");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    bitmapCreateScaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                    CloseableKt.closeFinally(fileOutputStream, null);
                    path = string;
                } finally {
                }
            } catch (Exception e) {
                Sentry.captureException(e);
                Callback callback2 = this.errorCallback;
                if (callback2 != null) {
                    callback2.invoke(ErrorKey.PHOTO_PROCESSING.getValue());
                    return;
                }
                return;
            }
        }
        WritableMap writableMapCreateFileMetadata = createFileMetadata(path);
        if (this.profilePhoto) {
            analyse(bitmapCreateBitmap, writableMapCreateFileMetadata);
            return;
        }
        Callback callback3 = this.callback;
        if (callback3 != null) {
            callback3.invoke(writableMapCreateFileMetadata);
        }
    }

    private final void analyse(Bitmap imageBitmap, final WritableMap results) {
        FaceDetectorOptions faceDetectorOptionsBuild = new FaceDetectorOptions.Builder().setClassificationMode(2).setContourMode(1).setLandmarkMode(1).setPerformanceMode(2).build();
        Intrinsics.checkNotNullExpressionValue(faceDetectorOptionsBuild, "Builder()\n            .s…ATE)\n            .build()");
        FaceDetector client = FaceDetection.getClient(faceDetectorOptionsBuild);
        Intrinsics.checkNotNullExpressionValue(client, "getClient(options)");
        Task<List<Face>> taskProcess = client.process(imageBitmap, 0);
        final Function1<List<Face>, Unit> function1 = new Function1<List<Face>, Unit>() { // from class: com.site360new.RNPhotoPicker.analyse.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<Face> list) {
                invoke2(list);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Removed duplicated region for block: B:23:0x00ab  */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void invoke2(java.util.List<com.google.mlkit.vision.face.Face> r8) {
                /*
                    r7 = this;
                    java.lang.String r0 = "faces"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)
                    java.lang.Object r0 = kotlin.collections.CollectionsKt.firstOrNull(r8)
                    com.google.mlkit.vision.face.Face r0 = (com.google.mlkit.vision.face.Face) r0
                    r1 = 0
                    if (r0 == 0) goto Lb2
                    com.facebook.react.bridge.WritableMap r2 = r2
                    r3 = -1049624576(0xffffffffc1700000, float:-15.0)
                    r4 = 1097859072(0x41700000, float:15.0)
                    kotlin.ranges.ClosedFloatingPointRange r3 = kotlin.ranges.RangesKt.rangeTo(r3, r4)
                    r4 = -1054867456(0xffffffffc1200000, float:-10.0)
                    r5 = 1092616192(0x41200000, float:10.0)
                    kotlin.ranges.ClosedFloatingPointRange r6 = kotlin.ranges.RangesKt.rangeTo(r4, r5)
                    kotlin.ranges.ClosedFloatingPointRange r4 = kotlin.ranges.RangesKt.rangeTo(r4, r5)
                    com.site360new.ResultKey r5 = com.site360new.ResultKey.FACES
                    java.lang.String r5 = r5.getValue()
                    int r8 = r8.size()
                    r2.putInt(r5, r8)
                    com.site360new.ResultKey r8 = com.site360new.ResultKey.ANGLE
                    java.lang.String r8 = r8.getValue()
                    float r5 = r0.getHeadEulerAngleX()
                    java.lang.Float r5 = java.lang.Float.valueOf(r5)
                    java.lang.Comparable r5 = (java.lang.Comparable) r5
                    boolean r3 = r3.contains(r5)
                    r5 = 1
                    if (r3 == 0) goto L6a
                    float r3 = r0.getHeadEulerAngleY()
                    java.lang.Float r3 = java.lang.Float.valueOf(r3)
                    java.lang.Comparable r3 = (java.lang.Comparable) r3
                    boolean r3 = r6.contains(r3)
                    if (r3 == 0) goto L6a
                    float r3 = r0.getHeadEulerAngleZ()
                    java.lang.Float r3 = java.lang.Float.valueOf(r3)
                    java.lang.Comparable r3 = (java.lang.Comparable) r3
                    boolean r3 = r4.contains(r3)
                    if (r3 == 0) goto L6a
                    r3 = r5
                    goto L6b
                L6a:
                    r3 = r1
                L6b:
                    r2.putBoolean(r8, r3)
                    com.site360new.ResultKey r8 = com.site360new.ResultKey.EYES
                    java.lang.String r8 = r8.getValue()
                    java.lang.Float r3 = r0.getLeftEyeOpenProbability()
                    r4 = 0
                    if (r3 != 0) goto L7f
                    java.lang.Float r3 = java.lang.Float.valueOf(r4)
                L7f:
                    java.lang.String r6 = "face.leftEyeOpenProbability ?: 0F"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r6)
                    java.lang.Number r3 = (java.lang.Number) r3
                    float r3 = r3.floatValue()
                    r6 = 1008981770(0x3c23d70a, float:0.01)
                    int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
                    if (r3 <= 0) goto Lab
                    java.lang.Float r0 = r0.getRightEyeOpenProbability()
                    if (r0 != 0) goto L9b
                    java.lang.Float r0 = java.lang.Float.valueOf(r4)
                L9b:
                    java.lang.String r3 = "face.rightEyeOpenProbability ?: 0F"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
                    java.lang.Number r0 = (java.lang.Number) r0
                    float r0 = r0.floatValue()
                    int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
                    if (r0 <= 0) goto Lab
                    goto Lac
                Lab:
                    r5 = r1
                Lac:
                    r2.putBoolean(r8, r5)
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE
                    goto Lb3
                Lb2:
                    r8 = 0
                Lb3:
                    if (r8 != 0) goto Lc0
                    com.facebook.react.bridge.WritableMap r8 = r2
                    com.site360new.ResultKey r0 = com.site360new.ResultKey.FACES
                    java.lang.String r0 = r0.getValue()
                    r8.putInt(r0, r1)
                Lc0:
                    com.site360new.RNPhotoPicker r8 = com.site360new.RNPhotoPicker.this
                    com.facebook.react.bridge.Callback r8 = com.site360new.RNPhotoPicker.access$getCallback$p(r8)
                    if (r8 == 0) goto Ld1
                    com.facebook.react.bridge.WritableMap r0 = r2
                    java.lang.Object[] r0 = new java.lang.Object[]{r0}
                    r8.invoke(r0)
                Ld1:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.site360new.RNPhotoPicker.AnonymousClass1.invoke2(java.util.List):void");
            }
        };
        taskProcess.addOnSuccessListener(new OnSuccessListener() { // from class: com.site360new.RNPhotoPicker$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                RNPhotoPicker.analyse$lambda$6(function1, obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: com.site360new.RNPhotoPicker$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                RNPhotoPicker.analyse$lambda$7(this.f$0, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void analyse$lambda$6(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void analyse$lambda$7(RNPhotoPicker this$0, Exception e) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(e, "e");
        String message = e.getMessage();
        if (message == null) {
            message = "";
        }
        Log.e(TAG, message);
        Callback callback = this$0.errorCallback;
        if (callback != null) {
            callback.invoke(ErrorKey.PHOTO_PROCESSING.getValue());
        }
    }

    private final WritableMap createFileMetadata(String path) {
        Uri uri = Uri.parse(path);
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString(ResultKey.FILE.getValue(), path);
        if (StringsKt.equals$default(uri.getScheme(), UriUtil.LOCAL_CONTENT_SCHEME, false, 2, null)) {
            Cursor cursorQuery = this.reactContext.getContentResolver().query(uri, null, null, null, null);
            if (cursorQuery != null) {
                cursorQuery.moveToFirst();
                writableNativeMap.putString(ResultKey.FILE_TYPE.getValue(), cursorQuery.getString(cursorQuery.getColumnIndex("mime_type")));
                writableNativeMap.putString(ResultKey.FILE_SIZE.getValue(), String.valueOf(cursorQuery.getLong(cursorQuery.getColumnIndex("_size"))));
                cursorQuery.close();
            }
        } else {
            writableNativeMap.putString(ResultKey.FILE_TYPE.getValue(), "image/jpeg");
            String path2 = uri.getPath();
            if (path2 == null) {
                path2 = "";
            }
            writableNativeMap.putString(ResultKey.FILE_SIZE.getValue(), String.valueOf(new File(path2).length()));
        }
        return writableNativeMap;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:
    
        if (r5 == null) goto L10;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final android.graphics.Bitmap createBitmap(android.net.Uri r5) throws java.io.FileNotFoundException {
        /*
            r4 = this;
            java.lang.String r0 = r5.getScheme()
            java.lang.String r1 = "file"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            r1 = 0
            if (r0 == 0) goto L21
            java.lang.String r5 = r5.getPath()
            if (r5 != 0) goto L15
            java.lang.String r5 = ""
        L15:
            androidx.exifinterface.media.ExifInterface r0 = new androidx.exifinterface.media.ExifInterface     // Catch: java.lang.Exception -> L20
            r0.<init>(r5)     // Catch: java.lang.Exception -> L20
            android.graphics.Bitmap r5 = android.graphics.BitmapFactory.decodeFile(r5)
            if (r5 != 0) goto L55
        L20:
            return r1
        L21:
            com.facebook.react.bridge.ReactApplicationContext r0 = r4.reactContext
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.io.InputStream r0 = r0.openInputStream(r5)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r2 = r0
            java.io.InputStream r2 = (java.io.InputStream) r2     // Catch: java.lang.Throwable -> L80
            if (r2 == 0) goto L7c
            androidx.exifinterface.media.ExifInterface r3 = new androidx.exifinterface.media.ExifInterface     // Catch: java.lang.Throwable -> L80
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L80
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            com.facebook.react.bridge.ReactApplicationContext r0 = r4.reactContext
            android.content.ContentResolver r0 = r0.getContentResolver()
            java.io.InputStream r5 = r0.openInputStream(r5)
            java.io.Closeable r5 = (java.io.Closeable) r5
            r0 = r5
            java.io.InputStream r0 = (java.io.InputStream) r0     // Catch: java.lang.Throwable -> L75
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r0)     // Catch: java.lang.Throwable -> L75
            kotlin.io.CloseableKt.closeFinally(r5, r1)
            if (r0 != 0) goto L53
            return r1
        L53:
            r5 = r0
            r0 = r3
        L55:
            java.lang.String r1 = "Orientation"
            r2 = 1
            int r0 = r0.getAttributeInt(r1, r2)
            switch(r0) {
                case 3: goto L6e;
                case 4: goto L6e;
                case 5: goto L67;
                case 6: goto L67;
                case 7: goto L60;
                case 8: goto L60;
                default: goto L5f;
            }
        L5f:
            goto L74
        L60:
            r0 = 270(0x10e, float:3.78E-43)
            android.graphics.Bitmap r5 = r4.rotateBitmap(r5, r0)
            goto L74
        L67:
            r0 = 90
            android.graphics.Bitmap r5 = r4.rotateBitmap(r5, r0)
            goto L74
        L6e:
            r0 = 180(0xb4, float:2.52E-43)
            android.graphics.Bitmap r5 = r4.rotateBitmap(r5, r0)
        L74:
            return r5
        L75:
            r0 = move-exception
            throw r0     // Catch: java.lang.Throwable -> L77
        L77:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r5, r0)
            throw r1
        L7c:
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            return r1
        L80:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> L82
        L82:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r5)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.site360new.RNPhotoPicker.createBitmap(android.net.Uri):android.graphics.Bitmap");
    }

    private final Bitmap rotateBitmap(Bitmap bitmap, int degrees) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degrees);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(bitmap, 0, …map.height, matrix, true)");
        bitmap.recycle();
        return bitmapCreateBitmap;
    }
}

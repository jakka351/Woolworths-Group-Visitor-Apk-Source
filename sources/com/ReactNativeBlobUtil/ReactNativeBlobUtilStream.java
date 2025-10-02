package com.ReactNativeBlobUtil;

import android.net.Uri;
import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;

/* loaded from: classes.dex */
public class ReactNativeBlobUtilStream {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final HashMap<String, ReactNativeBlobUtilStream> fileStreams = new HashMap<>();
    private final DeviceEventManagerModule.RCTDeviceEventEmitter emitter;
    private String encoding = "base64";
    private OutputStream writeStreamInstance = null;

    ReactNativeBlobUtilStream(ReactApplicationContext reactApplicationContext) {
        this.emitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0116 A[Catch: Exception -> 0x011f, FileNotFoundException -> 0x013f, TryCatch #0 {Exception -> 0x011f, blocks: (B:6:0x001a, B:14:0x002d, B:16:0x0033, B:20:0x005d, B:23:0x006a, B:24:0x007c, B:26:0x0082, B:28:0x008c, B:30:0x0093, B:58:0x0116, B:59:0x011b, B:31:0x009a, B:33:0x00a2, B:34:0x00a4, B:36:0x00ab, B:38:0x00b2, B:39:0x00ba, B:41:0x00bf, B:43:0x00c7, B:45:0x00cd, B:46:0x00cf, B:50:0x00d9, B:54:0x00f3, B:51:0x00e7, B:56:0x00fa, B:18:0x0044, B:19:0x0053), top: B:67:0x001a }] */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void readStream(java.lang.String r17, java.lang.String r18, int r19, int r20, java.lang.String r21, com.facebook.react.bridge.ReactApplicationContext r22) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ReactNativeBlobUtil.ReactNativeBlobUtilStream.readStream(java.lang.String, java.lang.String, int, int, java.lang.String, com.facebook.react.bridge.ReactApplicationContext):void");
    }

    void writeStream(String str, String str2, boolean z, Callback callback) {
        OutputStream fileOutputStream;
        String strNormalizePath = ReactNativeBlobUtilUtils.normalizePath(str);
        if (strNormalizePath != null) {
            str = strNormalizePath;
        }
        try {
            File file = new File(str);
            File parentFile = file.getParentFile();
            if (strNormalizePath != null && !file.exists()) {
                if (parentFile != null && !parentFile.exists() && !parentFile.mkdirs()) {
                    callback.invoke("ENOTDIR", "Failed to create parent directory of '" + str + "'");
                    return;
                } else if (!file.createNewFile()) {
                    callback.invoke("ENOENT", "File '" + str + "' does not exist and could not be created");
                    return;
                }
            } else if (file.isDirectory()) {
                callback.invoke("EISDIR", "Expecting a file but '" + str + "' is a directory");
                return;
            }
            if (strNormalizePath != null && str.startsWith(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET)) {
                fileOutputStream = ReactNativeBlobUtilImpl.RCTContext.getAssets().openFd(str.replace(ReactNativeBlobUtilConst.FILE_PREFIX_BUNDLE_ASSET, "")).createOutputStream();
            } else if (strNormalizePath == null) {
                fileOutputStream = ReactNativeBlobUtilImpl.RCTContext.getContentResolver().openOutputStream(Uri.parse(str));
            } else {
                fileOutputStream = new FileOutputStream(str, z);
            }
            this.encoding = str2;
            String string = UUID.randomUUID().toString();
            fileStreams.put(string, this);
            this.writeStreamInstance = fileOutputStream;
            callback.invoke(null, null, string);
        } catch (Exception e) {
            callback.invoke("EUNSPECIFIED", "Failed to create write stream at path `" + str + "`; " + e.getLocalizedMessage());
        }
    }

    static void writeChunk(String str, String str2, Callback callback) {
        ReactNativeBlobUtilStream reactNativeBlobUtilStream = fileStreams.get(str);
        try {
            reactNativeBlobUtilStream.writeStreamInstance.write(ReactNativeBlobUtilUtils.stringToBytes(str2, reactNativeBlobUtilStream.encoding));
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage());
        }
    }

    static void writeArrayChunk(String str, ReadableArray readableArray, Callback callback) {
        try {
            OutputStream outputStream = fileStreams.get(str).writeStreamInstance;
            byte[] bArr = new byte[readableArray.size()];
            for (int i = 0; i < readableArray.size(); i++) {
                bArr[i] = (byte) readableArray.getInt(i);
            }
            outputStream.write(bArr);
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage());
        }
    }

    static void closeStream(String str, Callback callback) {
        try {
            HashMap<String, ReactNativeBlobUtilStream> map = fileStreams;
            OutputStream outputStream = map.get(str).writeStreamInstance;
            map.remove(str);
            outputStream.close();
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.getLocalizedMessage());
        }
    }

    private void emitStreamEvent(String str, String str2, String str3) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString(NotificationCompat.CATEGORY_EVENT, str2);
        writableMapCreateMap.putString("detail", str3);
        writableMapCreateMap.putString("streamId", str);
        this.emitter.emit(ReactNativeBlobUtilConst.EVENT_FILESYSTEM, writableMapCreateMap);
    }

    private void emitStreamEvent(String str, String str2, WritableArray writableArray) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString(NotificationCompat.CATEGORY_EVENT, str2);
        writableMapCreateMap.putArray("detail", writableArray);
        writableMapCreateMap.putString("streamId", str);
        this.emitter.emit(ReactNativeBlobUtilConst.EVENT_FILESYSTEM, writableMapCreateMap);
    }

    private void emitStreamEvent(String str, String str2, String str3, String str4) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString(NotificationCompat.CATEGORY_EVENT, str2);
        writableMapCreateMap.putString("code", str3);
        writableMapCreateMap.putString("detail", str4);
        writableMapCreateMap.putString("streamId", str);
        this.emitter.emit(ReactNativeBlobUtilConst.EVENT_FILESYSTEM, writableMapCreateMap);
    }
}

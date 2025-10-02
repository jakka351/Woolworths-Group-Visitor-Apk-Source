package com.site360new;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RNTokenManager.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000bH\u0007J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000bH\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/site360new/RNTokenManager;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "keychainManager", "Lcom/site360new/KeychainManager;", "clearAllTokens", "", "emitTokenUpdate", "getName", "", "setAccessToken", "token", "setRefreshToken", "Companion", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RNTokenManager extends ReactContextBaseJavaModule {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static RNTokenManager instance;
    private final KeychainManager keychainManager;
    private final ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNTokenManager";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RNTokenManager(ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        this.keychainManager = new KeychainManager(reactContext);
        instance = this;
    }

    /* compiled from: RNTokenManager.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/site360new/RNTokenManager$Companion;", "", "()V", "instance", "Lcom/site360new/RNTokenManager;", "notifyTokenUpdate", "", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void notifyTokenUpdate() {
            RNTokenManager rNTokenManager = RNTokenManager.instance;
            if (rNTokenManager != null) {
                rNTokenManager.emitTokenUpdate();
            }
        }
    }

    @ReactMethod
    public final void setRefreshToken(String token) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(token, "token");
        this.keychainManager.setRefreshToken(token);
    }

    @ReactMethod
    public final void setAccessToken(String token) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(token, "token");
        this.keychainManager.setAccessToken(token);
    }

    @ReactMethod
    public final void clearAllTokens() throws KeyStoreException {
        this.keychainManager.clearAllTokens();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitTokenUpdate() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("accessToken", this.keychainManager.getAccessToken());
        writableMapCreateMap.putString("refreshToken", this.keychainManager.getRefreshToken());
        Intrinsics.checkNotNullExpressionValue(writableMapCreateMap, "createMap().apply {\n    …RefreshToken())\n        }");
        DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("tokenUpdate", writableMapCreateMap);
        }
    }
}

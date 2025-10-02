package com.site360new;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import com.google.android.gms.stats.CodePackage;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import io.sentry.SentryEvent;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: KeychainManager.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J\u0006\u0010\u0014\u001a\u00020\u0011J\b\u0010\u0015\u001a\u0004\u0018\u00010\u000fJ\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J\u0010\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0018\u001a\u0004\u0018\u00010\u000fJ\u000e\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u000fJ \u0010\u001b\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u000fH\u0002J\u000e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/site360new/KeychainManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "keyStore", "Ljava/security/KeyStore;", "kotlin.jvm.PlatformType", SentryEvent.JsonKeys.LOGGER, "Lcom/site360new/Logger;", "clearAllTokens", "", "createSecretKey", "Ljavax/crypto/SecretKey;", "alias", "", "deleteAccessToken", "", "deleteEncryptedValue", "key", "deleteRefreshToken", "getAccessToken", "getEncryptedValue", "getOrCreateSecretKey", "getRefreshToken", "setAccessToken", "token", "setEncryptedValue", "value", "setRefreshToken", "Companion", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class KeychainManager {
    private static final String ACCESS_TOKEN_ALIAS = "access_token_alias";
    private static final String ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY";
    private static final String KEYSTORE_PROVIDER = "AndroidKeyStore";
    private static final String REFRESH_TOKEN_ALIAS = "refresh_token_alias";
    private static final String REFRESH_TOKEN_KEY = "REFRESH_TOKEN_KEY";
    private static final String TAG = "KeychainManager";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private final Context context;
    private final KeyStore keyStore;
    private final Logger logger;

    public KeychainManager(Context context) throws NoSuchAlgorithmException, IOException, KeyStoreException, CertificateException {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        this.keyStore = keyStore;
        this.logger = Logger.INSTANCE.getInstance(context);
    }

    public final String getAccessToken() throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, KeyStoreException, InvalidAlgorithmParameterException {
        Logger.log$default(this.logger, "Retrieving access token from keychain", LogLevel.DEBUG, TAG, null, null, 0, 56, null);
        String encryptedValue = getEncryptedValue(ACCESS_TOKEN_ALIAS, ACCESS_TOKEN_KEY);
        boolean z = false;
        if (encryptedValue != null) {
            if (encryptedValue.length() > 0) {
                z = true;
            }
        }
        Logger.log$default(this.logger, "Access token retrieval: ".concat(z ? "found" : "not found"), LogLevel.DEBUG, TAG, null, null, 0, 56, null);
        return encryptedValue;
    }

    public final boolean setAccessToken(String token) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(token, "token");
        Logger.log$default(this.logger, "Storing access token in keychain", LogLevel.DEBUG, TAG, null, null, 0, 56, null);
        boolean encryptedValue = setEncryptedValue(ACCESS_TOKEN_ALIAS, ACCESS_TOKEN_KEY, token);
        Logger.log$default(this.logger, "Access token storage: ".concat(encryptedValue ? "successful" : "failed"), encryptedValue ? LogLevel.DEBUG : LogLevel.ERROR, TAG, null, null, 0, 56, null);
        return encryptedValue;
    }

    public final boolean deleteAccessToken() throws KeyStoreException {
        Logger.log$default(this.logger, "Deleting access token from keychain", LogLevel.DEBUG, TAG, null, null, 0, 56, null);
        boolean zDeleteEncryptedValue = deleteEncryptedValue(ACCESS_TOKEN_ALIAS, ACCESS_TOKEN_KEY);
        Logger.log$default(this.logger, "Access token deletion: ".concat(zDeleteEncryptedValue ? "successful" : "failed"), zDeleteEncryptedValue ? LogLevel.DEBUG : LogLevel.WARNING, TAG, null, null, 0, 56, null);
        return zDeleteEncryptedValue;
    }

    public final String getRefreshToken() throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, KeyStoreException, InvalidAlgorithmParameterException {
        Logger.log$default(this.logger, "Retrieving refresh token from keychain", LogLevel.DEBUG, TAG, null, null, 0, 56, null);
        String encryptedValue = getEncryptedValue(REFRESH_TOKEN_ALIAS, REFRESH_TOKEN_KEY);
        boolean z = false;
        if (encryptedValue != null) {
            if (encryptedValue.length() > 0) {
                z = true;
            }
        }
        Logger.log$default(this.logger, "Refresh token retrieval: ".concat(z ? "found" : "not found"), LogLevel.DEBUG, TAG, null, null, 0, 56, null);
        return encryptedValue;
    }

    public final boolean setRefreshToken(String token) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Intrinsics.checkNotNullParameter(token, "token");
        Logger.log$default(this.logger, "Storing refresh token in keychain", LogLevel.DEBUG, TAG, null, null, 0, 56, null);
        boolean encryptedValue = setEncryptedValue(REFRESH_TOKEN_ALIAS, REFRESH_TOKEN_KEY, token);
        if (encryptedValue) {
            boolean zAreEqual = Intrinsics.areEqual(getEncryptedValue(REFRESH_TOKEN_ALIAS, REFRESH_TOKEN_KEY), token);
            Logger.log$default(this.logger, "Refresh token storage verification: ".concat(zAreEqual ? "passed" : "failed"), zAreEqual ? LogLevel.DEBUG : LogLevel.ERROR, TAG, null, null, 0, 56, null);
        } else {
            Logger.log$default(this.logger, "Refresh token storage failed", LogLevel.ERROR, TAG, null, null, 0, 56, null);
        }
        return encryptedValue;
    }

    public final boolean deleteRefreshToken() throws KeyStoreException {
        Logger.log$default(this.logger, "Deleting refresh token from keychain", LogLevel.DEBUG, TAG, null, null, 0, 56, null);
        boolean zDeleteEncryptedValue = deleteEncryptedValue(REFRESH_TOKEN_ALIAS, REFRESH_TOKEN_KEY);
        Logger.log$default(this.logger, "Refresh token deletion: ".concat(zDeleteEncryptedValue ? "successful" : "failed"), zDeleteEncryptedValue ? LogLevel.DEBUG : LogLevel.WARNING, TAG, null, null, 0, 56, null);
        return zDeleteEncryptedValue;
    }

    private final String getEncryptedValue(String alias, String key) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, KeyStoreException, InvalidAlgorithmParameterException {
        Logger.log$default(this.logger, "Querying encrypted storage for key: " + key, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
        try {
            String string = this.context.getSharedPreferences("encrypted_prefs", 0).getString(key, null);
            if (string == null) {
                Logger.log$default(this.logger, "No encrypted value found for key: " + key, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
                return null;
            }
            if (!this.keyStore.containsAlias(alias)) {
                Logger.log$default(this.logger, "Keystore alias not found: " + alias, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
                return null;
            }
            Key key2 = this.keyStore.getKey(alias, null);
            SecretKey secretKey = key2 instanceof SecretKey ? (SecretKey) key2 : null;
            if (secretKey == null) {
                Logger.log$default(this.logger, "Failed to retrieve secret key for alias: " + alias, LogLevel.ERROR, TAG, null, null, 0, 56, null);
                return null;
            }
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            byte[] encryptedData = Base64.decode(string, 0);
            if (encryptedData.length < 12) {
                Logger.log$default(this.logger, "Encrypted data too short for key: " + key, LogLevel.ERROR, TAG, null, null, 0, 56, null);
                return null;
            }
            Intrinsics.checkNotNullExpressionValue(encryptedData, "encryptedData");
            byte[] bArrCopyOfRange = ArraysKt.copyOfRange(encryptedData, 0, 12);
            byte[] bArrCopyOfRange2 = ArraysKt.copyOfRange(encryptedData, 12, encryptedData.length);
            cipher.init(2, secretKey, new GCMParameterSpec(128, bArrCopyOfRange));
            byte[] decryptedBytes = cipher.doFinal(bArrCopyOfRange2);
            Intrinsics.checkNotNullExpressionValue(decryptedBytes, "decryptedBytes");
            String str = new String(decryptedBytes, Charsets.UTF_8);
            Logger.log$default(this.logger, "Successfully retrieved value for key: " + key, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
            return str;
        } catch (Exception e) {
            Logger.log$default(this.logger, "Decryption failed for alias: " + alias + ", key: " + key + " with error: " + e.getMessage(), LogLevel.ERROR, TAG, null, null, 0, 56, null);
            return null;
        }
    }

    private final boolean setEncryptedValue(String alias, String key, String value) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException {
        Logger.log$default(this.logger, "Setting encrypted value for key: " + key, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
        try {
            SecretKey orCreateSecretKey = getOrCreateSecretKey(alias);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(1, orCreateSecretKey);
            byte[] bytes = value.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            byte[] encryptedBytes = cipher.doFinal(bytes);
            byte[] iv = cipher.getIV();
            Intrinsics.checkNotNullExpressionValue(iv, "iv");
            Intrinsics.checkNotNullExpressionValue(encryptedBytes, "encryptedBytes");
            if (!this.context.getSharedPreferences("encrypted_prefs", 0).edit().putString(key, Base64.encodeToString(ArraysKt.plus(iv, encryptedBytes), 0)).commit()) {
                Logger.log$default(this.logger, "Failed to save encrypted value to shared preferences for key: " + key, LogLevel.ERROR, TAG, null, null, 0, 56, null);
                return false;
            }
            Logger.log$default(this.logger, "Successfully stored encrypted value for key: " + key, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
            return true;
        } catch (Exception e) {
            Logger.log$default(this.logger, "Encryption failed for alias: " + alias + ", key: " + key + " with error: " + e.getMessage(), LogLevel.ERROR, TAG, null, null, 0, 56, null);
            return false;
        }
    }

    private final boolean deleteEncryptedValue(String alias, String key) throws KeyStoreException {
        Logger.log$default(this.logger, "Deleting encrypted value for key: " + key, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
        try {
            if (!this.context.getSharedPreferences("encrypted_prefs", 0).edit().remove(key).commit()) {
                Logger.log$default(this.logger, "Failed to remove encrypted value from shared preferences for key: " + key, LogLevel.ERROR, TAG, null, null, 0, 56, null);
                return false;
            }
            if (this.keyStore.containsAlias(alias)) {
                try {
                    this.keyStore.deleteEntry(alias);
                    Logger.log$default(this.logger, "Successfully deleted keystore alias: " + alias, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
                } catch (Exception e) {
                    Logger.log$default(this.logger, "Failed to delete keystore alias: " + alias + " with error: " + e.getMessage(), LogLevel.WARNING, TAG, null, null, 0, 56, null);
                }
            } else {
                Logger.log$default(this.logger, "Keystore alias not found for deletion: " + alias, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
            }
            Logger.log$default(this.logger, "Successfully deleted encrypted value for key: " + key, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
            return true;
        } catch (Exception e2) {
            Logger.log$default(this.logger, "Delete failed for alias: " + alias + ", key: " + key + " with error: " + e2.getMessage(), LogLevel.ERROR, TAG, null, null, 0, 56, null);
            return false;
        }
    }

    private final SecretKey getOrCreateSecretKey(String alias) throws NoSuchAlgorithmException, UnrecoverableKeyException, KeyStoreException {
        if (this.keyStore.containsAlias(alias)) {
            Logger.log$default(this.logger, "Using existing secret key for alias: " + alias, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
            Key key = this.keyStore.getKey(alias, null);
            Intrinsics.checkNotNull(key, "null cannot be cast to non-null type javax.crypto.SecretKey");
            return (SecretKey) key;
        }
        Logger.log$default(this.logger, "Creating new secret key for alias: " + alias, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
        return createSecretKey(alias);
    }

    private final SecretKey createSecretKey(String alias) throws Exception {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(CipherStorageKeystoreAesCbc.ALGORITHM_AES, "AndroidKeyStore");
            KeyGenParameterSpec keyGenParameterSpecBuild = new KeyGenParameterSpec.Builder(alias, 3).setBlockModes(CodePackage.GCM).setEncryptionPaddings("NoPadding").setUserAuthenticationRequired(false).setRandomizedEncryptionRequired(true).build();
            Intrinsics.checkNotNullExpressionValue(keyGenParameterSpecBuild, "Builder(\n               …\n                .build()");
            keyGenerator.init(keyGenParameterSpecBuild);
            SecretKey secretKeyGenerateKey = keyGenerator.generateKey();
            Logger.log$default(this.logger, "Successfully created secret key for alias: " + alias, LogLevel.DEBUG, TAG, null, null, 0, 56, null);
            Intrinsics.checkNotNullExpressionValue(secretKeyGenerateKey, "{\n            val keyGen…      secretKey\n        }");
            return secretKeyGenerateKey;
        } catch (Exception e) {
            Logger.log$default(this.logger, "Failed to create secret key for alias: " + alias + " with error: " + e.getMessage(), LogLevel.ERROR, TAG, null, null, 0, 56, null);
            throw e;
        }
    }

    public final void clearAllTokens() throws KeyStoreException {
        Logger.log$default(this.logger, "Clearing all tokens from keychain", LogLevel.INFO, TAG, null, null, 0, 56, null);
        Logger.log$default(this.logger, "Token clearing completed - access: " + (deleteAccessToken() ? "success" : "failed") + ", refresh: " + (deleteRefreshToken() ? "success" : "failed"), LogLevel.INFO, TAG, null, null, 0, 56, null);
    }
}

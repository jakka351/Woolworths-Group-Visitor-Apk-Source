package com.faizal.OtpVerify;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class AppSignatureHelper extends ContextWrapper {
    private static final String HASH_TYPE = "SHA-256";
    public static final int NUM_BASE64_CHAR = 11;
    public static final int NUM_HASHED_BYTES = 9;
    public static final String TAG = "AppSignatureHelper";

    public AppSignatureHelper(Context context) {
        super(context);
    }

    public ArrayList<String> getAppSignatures() throws NoSuchAlgorithmException {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String packageName = getPackageName();
            for (Signature signature : getPackageManager().getPackageInfo(packageName, 64).signatures) {
                String strHash = hash(packageName, signature.toCharsString());
                if (strHash != null) {
                    arrayList.add(String.format("%s", strHash));
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Unable to find package to obtain hash.", e);
        }
        return arrayList;
    }

    private static String hash(String str, String str2) throws NoSuchAlgorithmException {
        String str3 = str + " " + str2;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASH_TYPE);
            messageDigest.update(str3.getBytes(StandardCharsets.UTF_8));
            String strSubstring = Base64.encodeToString(Arrays.copyOfRange(messageDigest.digest(), 0, 9), 3).substring(0, 11);
            Log.d(TAG, String.format("pkg: %s -- hash: %s", str, strSubstring));
            return strSubstring;
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "hash:NoSuchAlgorithm", e);
            return null;
        }
    }
}

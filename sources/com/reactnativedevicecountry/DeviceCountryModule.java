package com.reactnativedevicecountry;

import android.content.res.Resources;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import org.json.JSONObject;

@ReactModule(name = DeviceCountryModule.NAME)
/* loaded from: classes3.dex */
public class DeviceCountryModule extends ReactContextBaseJavaModule {
    public static final String NAME = "DeviceCountryModule";
    public static final String TYPE_ANY = "any";
    public static final String TYPE_CONFIGURATION = "config";
    public static final String TYPE_TELEPHONY = "telephony";
    private static ReactApplicationContext reactContext;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public DeviceCountryModule(ReactApplicationContext reactApplicationContext) {
        super(reactContext);
        reactContext = reactApplicationContext;
    }

    @ReactMethod
    public void getCountryCode(String str, Promise promise) {
        String countryCodeFromTelephonyManager;
        String str2;
        if (str.equals(TYPE_TELEPHONY) || str.equals(TYPE_ANY)) {
            countryCodeFromTelephonyManager = getCountryCodeFromTelephonyManager();
            str2 = TYPE_TELEPHONY;
        } else {
            countryCodeFromTelephonyManager = null;
            str2 = str;
        }
        if (str.equals(TYPE_CONFIGURATION) || (countryCodeFromTelephonyManager == null && !str.equals(TYPE_TELEPHONY))) {
            countryCodeFromTelephonyManager = getCountryCodeFromConfiguration();
            str2 = TYPE_CONFIGURATION;
        }
        try {
            promise.resolve(getResultJSON(countryCodeFromTelephonyManager, str2).toString());
        } catch (Exception unused) {
            promise.reject("No country code found exception");
        }
    }

    protected JSONObject getResultJSON(String str, String str2) throws Exception {
        if (str == null) {
            throw new Exception();
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("code", str);
        jSONObject.put("type", str2);
        return jSONObject;
    }

    protected String getCountryCodeFromTelephonyManager() {
        try {
            String networkCountryIso = ((TelephonyManager) reactContext.getSystemService(HintConstants.AUTOFILL_HINT_PHONE)).getNetworkCountryIso();
            if (networkCountryIso.isEmpty()) {
                return null;
            }
            return networkCountryIso;
        } catch (Exception unused) {
            return null;
        }
    }

    protected String getCountryCodeFromConfiguration() {
        try {
            return Resources.getSystem().getConfiguration().getLocales().get(0).getCountry();
        } catch (Exception unused) {
            return null;
        }
    }
}

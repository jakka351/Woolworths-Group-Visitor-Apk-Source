package com.faizal.OtpVerify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.Status;

/* loaded from: classes3.dex */
public class OtpBroadcastReceiver extends BroadcastReceiver {
    private static final String EVENT = "com.faizalshap.otpVerify:otpReceived";
    private ReactApplicationContext mContext;

    public OtpBroadcastReceiver(ReactApplicationContext reactApplicationContext) {
        this.mContext = reactApplicationContext;
    }

    private void receiveMessage(String str) {
        ReactApplicationContext reactApplicationContext = this.mContext;
        if (reactApplicationContext != null && reactApplicationContext.hasActiveCatalystInstance()) {
            new WritableNativeMap().putString("message", str);
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(EVENT, str);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras;
        Status status;
        if (!SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction()) || (extras = intent.getExtras()) == null || (status = (Status) extras.get("com.google.android.gms.auth.api.phone.EXTRA_STATUS")) == null) {
            return;
        }
        int statusCode = status.getStatusCode();
        if (statusCode != 0) {
            if (statusCode != 15) {
                return;
            }
            Log.d("SMS", "Timeout error");
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) this.mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(EVENT, "Timeout Error.");
            return;
        }
        String str = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
        receiveMessage(str);
        if (str != null) {
            Log.d("SMS", "" + str);
        }
    }
}

package com.faizal.OtpVerify;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Build;
import android.util.Log;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayList;
import java.util.Iterator;

@ReactModule(name = OtpVerifyModule.NAME)
/* loaded from: classes3.dex */
public class OtpVerifyModule extends ReactContextBaseJavaModule implements LifecycleEventListener, ActivityEventListener {
    public static final String NAME = "OtpVerify";
    private static final int RESOLVE_HINT = 10001;
    private static final String TAG = "OtpVerifyModule";
    private GoogleApiClient apiClient;
    private boolean isReceiverRegistered;
    private BroadcastReceiver mReceiver;
    private final ReactApplicationContext reactContext;
    private Promise requestHintCallback;

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public OtpVerifyModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.isReceiverRegistered = false;
        this.reactContext = reactApplicationContext;
        this.mReceiver = new OtpBroadcastReceiver(reactApplicationContext);
        getReactApplicationContext().addLifecycleEventListener(this);
        registerReceiverIfNecessary(this.mReceiver);
        reactApplicationContext.addActivityEventListener(this);
        this.apiClient = new GoogleApiClient.Builder(reactApplicationContext).addApi(Auth.GOOGLE_SIGN_IN_API).build();
    }

    @ReactMethod
    public void requestHint(Promise promise) {
        final Activity currentActivity = getCurrentActivity();
        this.requestHintCallback = promise;
        if (currentActivity == null) {
            promise.reject("No Activity Found", "Current Activity Null.");
            return;
        }
        try {
            Identity.getSignInClient(currentActivity).getPhoneNumberHintIntent(GetPhoneNumberHintIntentRequest.builder().build()).addOnSuccessListener(new OnSuccessListener() { // from class: com.faizal.OtpVerify.OtpVerifyModule$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnSuccessListener
                public final void onSuccess(Object obj) throws IntentSender.SendIntentException {
                    OtpVerifyModule.lambda$requestHint$0(currentActivity, (PendingIntent) obj);
                }
            }).addOnFailureListener(new OnFailureListener() { // from class: com.faizal.OtpVerify.OtpVerifyModule$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    Log.e(OtpVerifyModule.TAG, "Phone Number Hint failed", exc);
                }
            });
        } catch (Exception e) {
            this.requestHintCallback.reject(e);
        }
    }

    static /* synthetic */ void lambda$requestHint$0(Activity activity, PendingIntent pendingIntent) throws IntentSender.SendIntentException {
        try {
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(), 10001, null, 0, 0, 0);
        } catch (Exception e) {
            Log.e(TAG, "Launching the PendingIntent failed", e);
        }
    }

    @ReactMethod
    public void getOtp(Promise promise) {
        requestOtp(promise);
    }

    @ReactMethod
    public void getHash(Promise promise) {
        try {
            ArrayList<String> appSignatures = new AppSignatureHelper(this.reactContext).getAppSignatures();
            WritableArray writableArrayCreateArray = Arguments.createArray();
            Iterator<String> it = appSignatures.iterator();
            while (it.hasNext()) {
                writableArrayCreateArray.pushString(it.next());
            }
            promise.resolve(writableArrayCreateArray);
        } catch (Exception e) {
            promise.reject(e);
        }
    }

    private void registerReceiverIfNecessary(BroadcastReceiver broadcastReceiver) {
        if (getCurrentActivity() == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                getCurrentActivity().registerReceiver(broadcastReceiver, new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION), SmsRetriever.SEND_PERMISSION, null, 2);
            } else {
                getCurrentActivity().registerReceiver(broadcastReceiver, new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION));
            }
            Log.d(TAG, "Receiver Registered");
            this.isReceiverRegistered = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestOtp(final Promise promise) {
        Task<Void> taskStartSmsRetriever = SmsRetriever.getClient(this.reactContext).startSmsRetriever();
        taskStartSmsRetriever.addOnCanceledListener(new OnCanceledListener() { // from class: com.faizal.OtpVerify.OtpVerifyModule.1
            @Override // com.google.android.gms.tasks.OnCanceledListener
            public void onCanceled() {
                Log.e(OtpVerifyModule.TAG, "sms listener cancelled");
            }
        });
        taskStartSmsRetriever.addOnCompleteListener(new OnCompleteListener<Void>() { // from class: com.faizal.OtpVerify.OtpVerifyModule.2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> task) {
                Log.e(OtpVerifyModule.TAG, "sms listener complete");
            }
        });
        taskStartSmsRetriever.addOnSuccessListener(new OnSuccessListener<Void>() { // from class: com.faizal.OtpVerify.OtpVerifyModule.3
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(Void r2) {
                Log.e(OtpVerifyModule.TAG, "started sms listener");
                promise.resolve(true);
            }
        });
        taskStartSmsRetriever.addOnFailureListener(new OnFailureListener() { // from class: com.faizal.OtpVerify.OtpVerifyModule.4
            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(Exception exc) {
                Log.e(OtpVerifyModule.TAG, "Could not start sms listener", exc);
                promise.reject(exc);
            }
        });
    }

    private void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        if (!this.isReceiverRegistered || getCurrentActivity() == null || broadcastReceiver == null) {
            return;
        }
        try {
            getCurrentActivity().unregisterReceiver(broadcastReceiver);
            Log.d(TAG, "Receiver UnRegistered");
            this.isReceiverRegistered = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (i == 10001 && i2 == -1) {
            try {
                this.requestHintCallback.resolve(Identity.getSignInClient(activity).getPhoneNumberFromIntent(intent));
            } catch (ApiException e) {
                this.requestHintCallback.reject(e.getMessage());
            }
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
        registerReceiverIfNecessary(this.mReceiver);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
        unregisterReceiver(this.mReceiver);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        unregisterReceiver(this.mReceiver);
    }
}

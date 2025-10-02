package com.facebook.react.modules.vibration;

import android.os.VibrationEffect;
import android.os.Vibrator;
import com.facebook.fbreact.specs.NativeVibrationSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = NativeVibrationSpec.NAME)
/* loaded from: classes3.dex */
public class VibrationModule extends NativeVibrationSpec {
    public VibrationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.fbreact.specs.NativeVibrationSpec
    public void vibrate(double d) {
        int i = (int) d;
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator == null) {
            return;
        }
        vibrator.vibrate(VibrationEffect.createOneShot(i, -1));
    }

    @Override // com.facebook.fbreact.specs.NativeVibrationSpec
    public void vibrateByPattern(ReadableArray readableArray, double d) {
        int i = (int) d;
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator == null) {
            return;
        }
        long[] jArr = new long[readableArray.size()];
        for (int i2 = 0; i2 < readableArray.size(); i2++) {
            jArr[i2] = readableArray.getInt(i2);
        }
        vibrator.vibrate(VibrationEffect.createWaveform(jArr, i));
    }

    @Override // com.facebook.fbreact.specs.NativeVibrationSpec
    public void cancel() {
        Vibrator vibrator = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (vibrator != null) {
            vibrator.cancel();
        }
    }
}

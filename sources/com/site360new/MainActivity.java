package com.site360new;

import android.os.Bundle;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.zoontek.rnbootsplash.RNBootSplash;
import kotlin.Metadata;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0014J\b\u0010\u0005\u001a\u00020\u0006H\u0014J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014¨\u0006\u000b"}, d2 = {"Lcom/site360new/MainActivity;", "Lcom/facebook/react/ReactActivity;", "()V", "createReactActivityDelegate", "Lcom/facebook/react/ReactActivityDelegate;", "getMainComponentName", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MainActivity extends ReactActivity {
    @Override // com.facebook.react.ReactActivity
    protected String getMainComponentName() {
        return "site360";
    }

    @Override // com.facebook.react.ReactActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        RNBootSplash.init(this, R.style.BootTheme);
        super.onCreate(null);
    }

    @Override // com.facebook.react.ReactActivity
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) { // from class: com.site360new.MainActivity.createReactActivityDelegate.1
            {
                MainActivity mainActivity = this;
            }

            @Override // com.facebook.react.ReactActivityDelegate
            protected Bundle getLaunchOptions() {
                Bundle bundle = new Bundle();
                bundle.putString("appThemeId", BuildConfig.APP_THEME_ID);
                return bundle;
            }
        };
    }
}

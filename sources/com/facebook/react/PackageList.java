package com.facebook.react;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import cl.json.RNSharePackage;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilPackage;
import com.airbnb.android.react.lottie.LottiePackage;
import com.facebook.react.shell.MainPackageConfig;
import com.facebook.react.shell.MainReactPackage;
import com.faizal.OtpVerify.OtpVerifyPackage;
import com.horcrux.svg.SvgPackage;
import com.lugg.RNCConfig.RNCConfigPackage;
import com.maplibre.rctmln.RCTMLNPackage;
import com.oblador.keychain.KeychainPackage;
import com.reactnativecommunity.netinfo.NetInfoPackage;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.reactnativedevicecountry.DeviceCountryPackage;
import com.reactnativemmkv.MmkvPackage;
import com.swmansion.gesturehandler.RNGestureHandlerPackage;
import com.swmansion.reanimated.ReanimatedPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import com.zoontek.rnbootsplash.RNBootSplashPackage;
import com.zoontek.rnpermissions.RNPermissionsPackage;
import fr.greweb.reactnativeviewshot.RNViewShotPackage;
import io.invertase.firebase.app.ReactNativeFirebaseAppPackage;
import io.invertase.firebase.messaging.ReactNativeFirebaseMessagingPackage;
import io.invertase.notifee.NotifeePackage;
import io.sentry.react.RNSentryPackage;
import java.util.ArrayList;
import java.util.Arrays;
import org.wonday.pdf.RNPDFPackage;

/* loaded from: classes.dex */
public class PackageList {
    private Application application;
    private MainPackageConfig mConfig;
    private ReactNativeHost reactNativeHost;

    public PackageList(ReactNativeHost reactNativeHost) {
        this(reactNativeHost, (MainPackageConfig) null);
    }

    public PackageList(Application application) {
        this(application, (MainPackageConfig) null);
    }

    public PackageList(ReactNativeHost reactNativeHost, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = reactNativeHost;
        this.mConfig = mainPackageConfig;
    }

    public PackageList(Application application, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = null;
        this.application = application;
        this.mConfig = mainPackageConfig;
    }

    private ReactNativeHost getReactNativeHost() {
        return this.reactNativeHost;
    }

    private Resources getResources() {
        return getApplication().getResources();
    }

    private Application getApplication() {
        ReactNativeHost reactNativeHost = this.reactNativeHost;
        return reactNativeHost == null ? this.application : reactNativeHost.getApplication();
    }

    private Context getApplicationContext() {
        return getApplication().getApplicationContext();
    }

    public ArrayList<ReactPackage> getPackages() {
        return new ArrayList<>(Arrays.asList(new MainReactPackage(this.mConfig), new RCTMLNPackage(), new NotifeePackage(), new NetInfoPackage(), new ReactNativeFirebaseAppPackage(), new ReactNativeFirebaseMessagingPackage(), new RNSentryPackage(), new LottiePackage(), new ReactNativeBlobUtilPackage(), new RNBootSplashPackage(), new RNCConfigPackage(), new DeviceCountryPackage(), new RNGestureHandlerPackage(), new KeychainPackage(), new MmkvPackage(), new OtpVerifyPackage(), new RNPDFPackage(), new RNPermissionsPackage(), new ReanimatedPackage(), new SafeAreaContextPackage(), new RNScreensPackage(), new RNSharePackage(), new SvgPackage(), new RNViewShotPackage(), new RNCWebViewPackage()));
    }
}

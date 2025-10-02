package com.site360new;

import android.view.View;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Site360Package.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J&\u0010\b\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\n\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\t0\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\f"}, d2 = {"Lcom/site360new/Site360Package;", "Lcom/facebook/react/ReactPackage;", "()V", "createNativeModules", "", "Lcom/facebook/react/bridge/NativeModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "createViewManagers", "Lcom/facebook/react/uimanager/ViewManager;", "Landroid/view/View;", "Lcom/facebook/react/uimanager/ReactShadowNode;", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Site360Package implements ReactPackage {
    @Override // com.facebook.react.ReactPackage
    public List<ViewManager<View, ReactShadowNode<?>>> createViewManagers(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return new ArrayList();
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return CollectionsKt.toMutableList((Collection) CollectionsKt.listOf((Object[]) new ReactContextBaseJavaModule[]{new RNDeviceInfo(reactContext), new RNPhotoPicker(reactContext), new RNDatePicker(reactContext), new RNLocationManager(reactContext), new RNTokenManager(reactContext), new RNLogger(reactContext)}));
    }
}

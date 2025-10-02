package com.airbnb.android.react.lottie;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.airbnb.lottie.LottieAnimationView;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: LottieAnimationViewManagerImpl.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\u0006\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u000fH\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0007J \u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0007J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0007J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0007J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\u0010\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000bH\u0007J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007J\u0018\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u0010$\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u001fH\u0007J\u001a\u0010&\u001a\u00020\u00112\b\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u0010-\u001a\u00020\u00112\b\u0010.\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u00101\u001a\u00020\u00112\u0006\u00102\u001a\u0002032\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u00104\u001a\u00020\u00112\b\u00105\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u00106\u001a\u00020\u00112\b\u00107\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u00108\u001a\u00020\u00112\b\u00109\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u0010:\u001a\u00020\u00112\b\u0010;\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u0010<\u001a\u00020\u00112\b\u0010=\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u0010>\u001a\u00020\u00112\b\u0010?\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020#H\u0007J\u0018\u0010@\u001a\u00020\u00112\u0006\u0010A\u001a\u00020B2\u0006\u0010\"\u001a\u00020#H\u0007J\u001a\u0010C\u001a\u00020\u00112\b\u0010D\u001a\u0004\u0018\u00010(2\u0006\u0010\"\u001a\u00020#H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u00068FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\t¨\u0006E"}, d2 = {"Lcom/airbnb/android/react/lottie/LottieAnimationViewManagerImpl;", "", "()V", "REACT_CLASS", "", "exportedViewConstants", "", "getExportedViewConstants$annotations", "getExportedViewConstants", "()Ljava/util/Map;", "createViewInstance", "Lcom/airbnb/lottie/LottieAnimationView;", "context", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getExportedCustomDirectEventTypeConstants", "", "pause", "", "view", "play", "startFrame", "", "endFrame", "reset", "resume", "sendAnimationFailureEvent", "error", "", "sendAnimationLoadedEvent", "sendOnAnimationFinishEvent", "isCancelled", "", "setAutoPlay", "autoPlay", "viewManager", "Lcom/airbnb/android/react/lottie/LottieAnimationViewPropertyManager;", "setCacheComposition", "cacheComposition", "setColorFilters", "colorFilters", "Lcom/facebook/react/bridge/ReadableArray;", "setEnableMergePaths", "enableMergePaths", "setHardwareAcceleration", "hardwareAccelerationAndroid", "setImageAssetsFolder", "imageAssetsFolder", "setLoop", "loop", "setProgress", "progress", "", "setRenderMode", "renderMode", "setResizeMode", ViewProps.RESIZE_MODE, "setSourceDotLottieURI", ReactNativeBlobUtilConst.DATA_ENCODE_URI, "setSourceJson", "json", "setSourceName", "name", "setSourceURL", "urlString", "setSpeed", "speed", "", "setTextFilters", "textFilters", "lottie-react-native_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LottieAnimationViewManagerImpl {
    public static final LottieAnimationViewManagerImpl INSTANCE = new LottieAnimationViewManagerImpl();
    public static final String REACT_CLASS = "LottieAnimationView";

    @JvmStatic
    public static /* synthetic */ void getExportedViewConstants$annotations() {
    }

    private LottieAnimationViewManagerImpl() {
    }

    public static final Map<String, Object> getExportedViewConstants() {
        Map<String, Object> mapBuild = MapBuilder.builder().put("VERSION", 1).build();
        Intrinsics.checkNotNullExpressionValue(mapBuild, "builder<String, Any>()\n …, 1)\n            .build()");
        return mapBuild;
    }

    @JvmStatic
    public static final LottieAnimationView createViewInstance(ThemedReactContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        LottieAnimationView lottieAnimationView = new LottieAnimationView(context);
        lottieAnimationView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        return lottieAnimationView;
    }

    @JvmStatic
    public static final void sendOnAnimationFinishEvent(LottieAnimationView view, boolean isCancelled) {
        Intrinsics.checkNotNullParameter(view, "view");
        Context context = view.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ThemedReactContext themedReactContext = (ThemedReactContext) context;
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, view.getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new OnAnimationFinishEvent(themedReactContext.getSurfaceId(), view.getId(), isCancelled));
        }
    }

    @JvmStatic
    public static final void sendAnimationFailureEvent(LottieAnimationView view, Throwable error) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(error, "error");
        Context context = view.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ThemedReactContext themedReactContext = (ThemedReactContext) context;
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, view.getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new OnAnimationFailureEvent(themedReactContext.getSurfaceId(), view.getId(), error));
        }
    }

    @JvmStatic
    public static final void sendAnimationLoadedEvent(LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Context context = view.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.uimanager.ThemedReactContext");
        ThemedReactContext themedReactContext = (ThemedReactContext) context;
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(themedReactContext, view.getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new OnAnimationLoadedEvent(themedReactContext.getSurfaceId(), view.getId()));
        }
    }

    @JvmStatic
    public static final Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> mapOf = MapBuilder.of(OnAnimationFinishEvent.EVENT_NAME, MapBuilder.of("registrationName", "onAnimationFinish"), OnAnimationFailureEvent.EVENT_NAME, MapBuilder.of("registrationName", "onAnimationFailure"), OnAnimationLoadedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onAnimationLoaded"));
        Intrinsics.checkNotNullExpressionValue(mapOf, "of(\n            OnAnimat…mationLoaded\"),\n        )");
        return mapOf;
    }

    @JvmStatic
    public static final void play(final LottieAnimationView view, final int startFrame, final int endFrame) {
        Intrinsics.checkNotNullParameter(view, "view");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManagerImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LottieAnimationViewManagerImpl.play$lambda$1(startFrame, endFrame, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void play$lambda$1(int i, int i2, LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "$view");
        if (i != -1 && i2 != -1) {
            if (i > i2) {
                view.setMinAndMaxFrame(i2, i);
                if (view.getSpeed() > 0.0f) {
                    view.reverseAnimationSpeed();
                }
            } else {
                view.setMinAndMaxFrame(i, i2);
                if (view.getSpeed() < 0.0f) {
                    view.reverseAnimationSpeed();
                }
            }
        }
        if (ViewCompat.isAttachedToWindow(view)) {
            view.setProgress(0.0f);
            view.playAnimation();
        } else {
            view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManagerImpl$play$1$1
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    LottieAnimationView lottieAnimationView = (LottieAnimationView) v;
                    lottieAnimationView.setProgress(0.0f);
                    lottieAnimationView.playAnimation();
                    lottieAnimationView.removeOnAttachStateChangeListener(this);
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View v) {
                    Intrinsics.checkNotNullParameter(v, "v");
                    ((LottieAnimationView) v).removeOnAttachStateChangeListener(this);
                }
            });
        }
    }

    @JvmStatic
    public static final void reset(final LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManagerImpl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                LottieAnimationViewManagerImpl.reset$lambda$2(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void reset$lambda$2(LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "$view");
        if (ViewCompat.isAttachedToWindow(view)) {
            view.cancelAnimation();
            view.setProgress(0.0f);
        }
    }

    @JvmStatic
    public static final void pause(final LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManagerImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                LottieAnimationViewManagerImpl.pause$lambda$3(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void pause$lambda$3(LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "$view");
        if (ViewCompat.isAttachedToWindow(view)) {
            view.pauseAnimation();
        }
    }

    @JvmStatic
    public static final void resume(final LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.airbnb.android.react.lottie.LottieAnimationViewManagerImpl$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                LottieAnimationViewManagerImpl.resume$lambda$4(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resume$lambda$4(LottieAnimationView view) {
        Intrinsics.checkNotNullParameter(view, "$view");
        if (ViewCompat.isAttachedToWindow(view)) {
            view.resumeAnimation();
        }
    }

    @JvmStatic
    public static final void setSourceName(String name, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        boolean z = false;
        if (name != null && !StringsKt.contains$default((CharSequence) name, (CharSequence) ".", false, 2, (Object) null)) {
            z = true;
        }
        if (z) {
            name = name + ".json";
        }
        viewManager.setAnimationName(name);
        viewManager.commitChanges();
    }

    @JvmStatic
    public static final void setSourceJson(String json, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setAnimationJson(json);
        viewManager.commitChanges();
    }

    @JvmStatic
    public static final void setSourceURL(String urlString, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setAnimationURL(urlString);
        viewManager.commitChanges();
    }

    @JvmStatic
    public static final void setSourceDotLottieURI(String uri, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setSourceDotLottie(uri);
        viewManager.commitChanges();
    }

    @JvmStatic
    public static final void setCacheComposition(LottieAnimationView view, boolean cacheComposition) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setCacheComposition(cacheComposition);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0040  */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void setResizeMode(java.lang.String r2, com.airbnb.android.react.lottie.LottieAnimationViewPropertyManager r3) {
        /*
            java.lang.String r0 = "viewManager"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            if (r2 == 0) goto L40
            int r0 = r2.hashCode()
            r1 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            if (r0 == r1) goto L34
            r1 = 94852023(0x5a753b7, float:1.5735357E-35)
            if (r0 == r1) goto L28
            r1 = 951526612(0x38b724d4, float:8.73298E-5)
            if (r0 == r1) goto L1c
            goto L40
        L1c:
            java.lang.String r0 = "contain"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L25
            goto L40
        L25:
            android.widget.ImageView$ScaleType r2 = android.widget.ImageView.ScaleType.FIT_CENTER
            goto L41
        L28:
            java.lang.String r0 = "cover"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L31
            goto L40
        L31:
            android.widget.ImageView$ScaleType r2 = android.widget.ImageView.ScaleType.CENTER_CROP
            goto L41
        L34:
            java.lang.String r0 = "center"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L3d
            goto L40
        L3d:
            android.widget.ImageView$ScaleType r2 = android.widget.ImageView.ScaleType.CENTER_INSIDE
            goto L41
        L40:
            r2 = 0
        L41:
            r3.setScaleType(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.lottie.LottieAnimationViewManagerImpl.setResizeMode(java.lang.String, com.airbnb.android.react.lottie.LottieAnimationViewPropertyManager):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0040  */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void setRenderMode(java.lang.String r2, com.airbnb.android.react.lottie.LottieAnimationViewPropertyManager r3) {
        /*
            java.lang.String r0 = "viewManager"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            if (r2 == 0) goto L40
            int r0 = r2.hashCode()
            r1 = 165298699(0x9da420b, float:5.2543697E-33)
            if (r0 == r1) goto L34
            r1 = 899536360(0x359dd5e8, float:1.1759666E-6)
            if (r0 == r1) goto L28
            r1 = 2101957031(0x7d4951a7, float:1.6724924E37)
            if (r0 == r1) goto L1c
            goto L40
        L1c:
            java.lang.String r0 = "SOFTWARE"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L25
            goto L40
        L25:
            com.airbnb.lottie.RenderMode r2 = com.airbnb.lottie.RenderMode.SOFTWARE
            goto L41
        L28:
            java.lang.String r0 = "HARDWARE"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L31
            goto L40
        L31:
            com.airbnb.lottie.RenderMode r2 = com.airbnb.lottie.RenderMode.HARDWARE
            goto L41
        L34:
            java.lang.String r0 = "AUTOMATIC"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L3d
            goto L40
        L3d:
            com.airbnb.lottie.RenderMode r2 = com.airbnb.lottie.RenderMode.AUTOMATIC
            goto L41
        L40:
            r2 = 0
        L41:
            r3.setRenderMode(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.lottie.LottieAnimationViewManagerImpl.setRenderMode(java.lang.String, com.airbnb.android.react.lottie.LottieAnimationViewPropertyManager):void");
    }

    @JvmStatic
    public static final void setHardwareAcceleration(boolean hardwareAccelerationAndroid, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        int i = 1;
        if (hardwareAccelerationAndroid) {
            i = 2;
        }
        viewManager.setLayerType(i);
    }

    @JvmStatic
    public static final void setProgress(float progress, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setProgress(Float.valueOf(progress));
    }

    @JvmStatic
    public static final void setSpeed(double speed, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setSpeed(Float.valueOf((float) speed));
    }

    @JvmStatic
    public static final void setLoop(boolean loop, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setLoop(Boolean.valueOf(loop));
    }

    @JvmStatic
    public static final void setAutoPlay(boolean autoPlay, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setAutoPlay(Boolean.valueOf(autoPlay));
    }

    @JvmStatic
    public static final void setEnableMergePaths(boolean enableMergePaths, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setEnableMergePaths(Boolean.valueOf(enableMergePaths));
    }

    @JvmStatic
    public static final void setImageAssetsFolder(String imageAssetsFolder, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setImageAssetsFolder(imageAssetsFolder);
    }

    @JvmStatic
    public static final void setColorFilters(ReadableArray colorFilters, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setColorFilters(colorFilters);
    }

    @JvmStatic
    public static final void setTextFilters(ReadableArray textFilters, LottieAnimationViewPropertyManager viewManager) {
        Intrinsics.checkNotNullParameter(viewManager, "viewManager");
        viewManager.setTextFilters(textFilters);
    }
}

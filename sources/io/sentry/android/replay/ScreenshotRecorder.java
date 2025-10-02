package io.sentry.android.replay;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import androidx.core.view.ViewCompat;
import com.reactnativedevicecountry.DeviceCountryModule;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.android.replay.ScreenshotRecorder;
import io.sentry.android.replay.util.ExecutorsKt;
import io.sentry.android.replay.util.MainLooperHandler;
import io.sentry.android.replay.util.ViewsKt;
import io.sentry.android.replay.viewhierarchy.ViewHierarchyNode;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ScreenshotRecorder.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001:\u00019B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020&J\u0006\u0010-\u001a\u00020+J\u0006\u0010.\u001a\u00020+J\b\u0010/\u001a\u00020+H\u0016J\u0006\u00100\u001a\u00020+J\u0006\u00101\u001a\u00020+J\u0010\u00102\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010&J\u0014\u00103\u001a\u000204*\u00020\u00112\u0006\u00105\u001a\u000206H\u0002J\u0014\u00107\u001a\u00020+*\u00020&2\u0006\u00108\u001a\u00020\u001aH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u001d\u001a\n \u001f*\u0004\u0018\u00010\u001e0\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b \u0010!R\u0016\u0010$\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lio/sentry/android/replay/ScreenshotRecorder;", "Landroid/view/ViewTreeObserver$OnDrawListener;", DeviceCountryModule.TYPE_CONFIGURATION, "Lio/sentry/android/replay/ScreenshotRecorderConfig;", "options", "Lio/sentry/SentryOptions;", "mainLooperHandler", "Lio/sentry/android/replay/util/MainLooperHandler;", "screenshotRecorderCallback", "Lio/sentry/android/replay/ScreenshotRecorderCallback;", "(Lio/sentry/android/replay/ScreenshotRecorderConfig;Lio/sentry/SentryOptions;Lio/sentry/android/replay/util/MainLooperHandler;Lio/sentry/android/replay/ScreenshotRecorderCallback;)V", "getConfig", "()Lio/sentry/android/replay/ScreenshotRecorderConfig;", "contentChanged", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isCapturing", "lastScreenshot", "Landroid/graphics/Bitmap;", "getMainLooperHandler", "()Lio/sentry/android/replay/util/MainLooperHandler;", "maskingPaint", "Landroid/graphics/Paint;", "getOptions", "()Lio/sentry/SentryOptions;", "pendingViewHierarchy", "Ljava/util/concurrent/atomic/AtomicReference;", "Lio/sentry/android/replay/viewhierarchy/ViewHierarchyNode;", "prescaledMatrix", "Landroid/graphics/Matrix;", "recorder", "Ljava/util/concurrent/ScheduledExecutorService;", "kotlin.jvm.PlatformType", "getRecorder", "()Ljava/util/concurrent/ScheduledExecutorService;", "recorder$delegate", "Lkotlin/Lazy;", "rootView", "Ljava/lang/ref/WeakReference;", "Landroid/view/View;", "singlePixelBitmap", "singlePixelBitmapCanvas", "Landroid/graphics/Canvas;", "bind", "", "root", "capture", "close", "onDraw", "pause", "resume", "unbind", "dominantColorForRect", "", "rect", "Landroid/graphics/Rect;", "traverse", "parentNode", "RecorderExecutorServiceThreadFactory", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreenshotRecorder implements ViewTreeObserver.OnDrawListener {
    private final ScreenshotRecorderConfig config;
    private final AtomicBoolean contentChanged;
    private final AtomicBoolean isCapturing;
    private Bitmap lastScreenshot;
    private final MainLooperHandler mainLooperHandler;
    private final Paint maskingPaint;
    private final SentryOptions options;
    private final AtomicReference<ViewHierarchyNode> pendingViewHierarchy;
    private final Matrix prescaledMatrix;

    /* renamed from: recorder$delegate, reason: from kotlin metadata */
    private final Lazy recorder;
    private WeakReference<View> rootView;
    private final ScreenshotRecorderCallback screenshotRecorderCallback;
    private final Bitmap singlePixelBitmap;
    private final Canvas singlePixelBitmapCanvas;

    public ScreenshotRecorder(ScreenshotRecorderConfig config, SentryOptions options, MainLooperHandler mainLooperHandler, ScreenshotRecorderCallback screenshotRecorderCallback) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(mainLooperHandler, "mainLooperHandler");
        this.config = config;
        this.options = options;
        this.mainLooperHandler = mainLooperHandler;
        this.screenshotRecorderCallback = screenshotRecorderCallback;
        this.recorder = LazyKt.lazy(new Function0<ScheduledExecutorService>() { // from class: io.sentry.android.replay.ScreenshotRecorder$recorder$2
            @Override // kotlin.jvm.functions.Function0
            public final ScheduledExecutorService invoke() {
                return Executors.newSingleThreadScheduledExecutor(new ScreenshotRecorder.RecorderExecutorServiceThreadFactory());
            }
        });
        this.pendingViewHierarchy = new AtomicReference<>();
        this.maskingPaint = new Paint();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n        1,…ap.Config.ARGB_8888\n    )");
        this.singlePixelBitmap = bitmapCreateBitmap;
        this.singlePixelBitmapCanvas = new Canvas(bitmapCreateBitmap);
        Matrix matrix = new Matrix();
        matrix.preScale(config.getScaleFactorX(), config.getScaleFactorY());
        this.prescaledMatrix = matrix;
        this.contentChanged = new AtomicBoolean(false);
        this.isCapturing = new AtomicBoolean(true);
    }

    public final ScreenshotRecorderConfig getConfig() {
        return this.config;
    }

    public final SentryOptions getOptions() {
        return this.options;
    }

    public final MainLooperHandler getMainLooperHandler() {
        return this.mainLooperHandler;
    }

    private final ScheduledExecutorService getRecorder() {
        return (ScheduledExecutorService) this.recorder.getValue();
    }

    public final void capture() {
        Bitmap bitmap;
        ScreenshotRecorderCallback screenshotRecorderCallback;
        if (!this.isCapturing.get()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "ScreenshotRecorder is paused, not capturing screenshot", new Object[0]);
            return;
        }
        if (!this.contentChanged.get() && (bitmap = this.lastScreenshot) != null) {
            Intrinsics.checkNotNull(bitmap);
            if (!bitmap.isRecycled()) {
                this.options.getLogger().log(SentryLevel.DEBUG, "Content hasn't changed, repeating last known frame", new Object[0]);
                Bitmap bitmap2 = this.lastScreenshot;
                if (bitmap2 == null || (screenshotRecorderCallback = this.screenshotRecorderCallback) == null) {
                    return;
                }
                Bitmap bitmapCopy = bitmap2.copy(Bitmap.Config.ARGB_8888, false);
                Intrinsics.checkNotNullExpressionValue(bitmapCopy, "it.copy(ARGB_8888, false)");
                screenshotRecorderCallback.onScreenshotRecorded(bitmapCopy);
                return;
            }
        }
        WeakReference<View> weakReference = this.rootView;
        final View view = weakReference != null ? weakReference.get() : null;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0 || !view.isShown()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Root view is invalid, not capturing screenshot", new Object[0]);
            return;
        }
        final Window phoneWindow = WindowsKt.getPhoneWindow(view);
        if (phoneWindow == null) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Window is invalid, not capturing screenshot", new Object[0]);
            return;
        }
        final Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.config.getRecordingWidth(), this.config.getRecordingHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n          …onfig.ARGB_8888\n        )");
        this.mainLooperHandler.post(new Runnable() { // from class: io.sentry.android.replay.ScreenshotRecorder$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                ScreenshotRecorder.capture$lambda$4(this.f$0, phoneWindow, bitmapCreateBitmap, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void capture$lambda$4(final ScreenshotRecorder this$0, Window window, final Bitmap bitmap, final View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        try {
            this$0.contentChanged.set(false);
            PixelCopy.request(window, bitmap, new PixelCopy.OnPixelCopyFinishedListener() { // from class: io.sentry.android.replay.ScreenshotRecorder$$ExternalSyntheticLambda2
                @Override // android.view.PixelCopy.OnPixelCopyFinishedListener
                public final void onPixelCopyFinished(int i) {
                    ScreenshotRecorder.capture$lambda$4$lambda$3(this.f$0, bitmap, view, i);
                }
            }, this$0.mainLooperHandler.getHandler());
        } catch (Throwable th) {
            this$0.options.getLogger().log(SentryLevel.WARNING, "Failed to capture replay recording", th);
            bitmap.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void capture$lambda$4$lambda$3(final ScreenshotRecorder this$0, final Bitmap bitmap, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        if (i != 0) {
            this$0.options.getLogger().log(SentryLevel.INFO, "Failed to capture replay recording: %d", Integer.valueOf(i));
            bitmap.recycle();
        } else {
            if (this$0.contentChanged.get()) {
                this$0.options.getLogger().log(SentryLevel.INFO, "Failed to determine view hierarchy, not capturing", new Object[0]);
                bitmap.recycle();
                return;
            }
            final ViewHierarchyNode viewHierarchyNodeFromView = ViewHierarchyNode.INSTANCE.fromView(view, null, 0, this$0.options);
            this$0.traverse(view, viewHierarchyNodeFromView);
            ScheduledExecutorService recorder = this$0.getRecorder();
            Intrinsics.checkNotNullExpressionValue(recorder, "recorder");
            ExecutorsKt.submitSafely(recorder, this$0.options, "screenshot_recorder.redact", new Runnable() { // from class: io.sentry.android.replay.ScreenshotRecorder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ScreenshotRecorder.capture$lambda$4$lambda$3$lambda$2(bitmap, this$0, viewHierarchyNodeFromView);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void capture$lambda$4$lambda$3$lambda$2(final Bitmap bitmap, final ScreenshotRecorder this$0, ViewHierarchyNode viewHierarchy) {
        Intrinsics.checkNotNullParameter(bitmap, "$bitmap");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(viewHierarchy, "$viewHierarchy");
        final Canvas canvas = new Canvas(bitmap);
        canvas.setMatrix(this$0.prescaledMatrix);
        viewHierarchy.traverse(new Function1<ViewHierarchyNode, Boolean>() { // from class: io.sentry.android.replay.ScreenshotRecorder$capture$2$1$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(ViewHierarchyNode node) {
                Pair pair;
                Intrinsics.checkNotNullParameter(node, "node");
                if (node.getShouldRedact() && node.getWidth() > 0 && node.getHeight() > 0) {
                    if (node.getVisibleRect() == null) {
                        return false;
                    }
                    if (node instanceof ViewHierarchyNode.ImageViewHierarchyNode) {
                        pair = TuplesKt.to(CollectionsKt.listOf(node.getVisibleRect()), Integer.valueOf(this.this$0.dominantColorForRect(bitmap, node.getVisibleRect())));
                    } else {
                        boolean z = node instanceof ViewHierarchyNode.TextViewHierarchyNode;
                        int iIntValue = ViewCompat.MEASURED_STATE_MASK;
                        if (z) {
                            ViewHierarchyNode.TextViewHierarchyNode textViewHierarchyNode = (ViewHierarchyNode.TextViewHierarchyNode) node;
                            List<Rect> visibleRects = ViewsKt.getVisibleRects(textViewHierarchyNode.getLayout(), node.getVisibleRect(), textViewHierarchyNode.getPaddingLeft(), textViewHierarchyNode.getPaddingTop());
                            Integer dominantColor = textViewHierarchyNode.getDominantColor();
                            if (dominantColor != null) {
                                iIntValue = dominantColor.intValue();
                            }
                            pair = TuplesKt.to(visibleRects, Integer.valueOf(iIntValue));
                        } else {
                            pair = TuplesKt.to(CollectionsKt.listOf(node.getVisibleRect()), Integer.valueOf(ViewCompat.MEASURED_STATE_MASK));
                        }
                    }
                    List list = (List) pair.component1();
                    this.this$0.maskingPaint.setColor(((Number) pair.component2()).intValue());
                    Canvas canvas2 = canvas;
                    ScreenshotRecorder screenshotRecorder = this.this$0;
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        canvas2.drawRoundRect(new RectF((Rect) it.next()), 10.0f, 10.0f, screenshotRecorder.maskingPaint);
                    }
                }
                return true;
            }
        });
        Bitmap screenshot = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        ScreenshotRecorderCallback screenshotRecorderCallback = this$0.screenshotRecorderCallback;
        if (screenshotRecorderCallback != null) {
            Intrinsics.checkNotNullExpressionValue(screenshot, "screenshot");
            screenshotRecorderCallback.onScreenshotRecorded(screenshot);
        }
        Bitmap bitmap2 = this$0.lastScreenshot;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        this$0.lastScreenshot = screenshot;
        this$0.contentChanged.set(false);
        bitmap.recycle();
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public void onDraw() {
        WeakReference<View> weakReference = this.rootView;
        View view = weakReference != null ? weakReference.get() : null;
        if (view == null || view.getWidth() <= 0 || view.getHeight() <= 0 || !view.isShown()) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Root view is invalid, not capturing screenshot", new Object[0]);
        } else {
            this.contentChanged.set(true);
        }
    }

    public final void bind(View root) {
        Intrinsics.checkNotNullParameter(root, "root");
        WeakReference<View> weakReference = this.rootView;
        unbind(weakReference != null ? weakReference.get() : null);
        WeakReference<View> weakReference2 = this.rootView;
        if (weakReference2 != null) {
            weakReference2.clear();
        }
        this.rootView = new WeakReference<>(root);
        ViewTreeObserver viewTreeObserver = root.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnDrawListener(this);
        }
    }

    public final void unbind(View root) {
        ViewTreeObserver viewTreeObserver;
        if (root == null || (viewTreeObserver = root.getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.removeOnDrawListener(this);
    }

    public final void pause() {
        this.isCapturing.set(false);
        WeakReference<View> weakReference = this.rootView;
        unbind(weakReference != null ? weakReference.get() : null);
    }

    public final void resume() {
        View view;
        ViewTreeObserver viewTreeObserver;
        WeakReference<View> weakReference = this.rootView;
        if (weakReference != null && (view = weakReference.get()) != null && (viewTreeObserver = view.getViewTreeObserver()) != null) {
            viewTreeObserver.addOnDrawListener(this);
        }
        this.isCapturing.set(true);
    }

    public final void close() {
        WeakReference<View> weakReference = this.rootView;
        unbind(weakReference != null ? weakReference.get() : null);
        WeakReference<View> weakReference2 = this.rootView;
        if (weakReference2 != null) {
            weakReference2.clear();
        }
        Bitmap bitmap = this.lastScreenshot;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.pendingViewHierarchy.set(null);
        this.isCapturing.set(false);
        ScheduledExecutorService recorder = getRecorder();
        Intrinsics.checkNotNullExpressionValue(recorder, "recorder");
        ExecutorsKt.gracefullyShutdown(recorder, this.options);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dominantColorForRect(Bitmap bitmap, Rect rect) {
        Rect rect2 = new Rect(rect);
        RectF rectF = new RectF(rect2);
        this.prescaledMatrix.mapRect(rectF);
        rectF.round(rect2);
        this.singlePixelBitmapCanvas.drawBitmap(bitmap, rect2, new Rect(0, 0, 1, 1), (Paint) null);
        return this.singlePixelBitmap.getPixel(0, 0);
    }

    private final void traverse(View view, ViewHierarchyNode viewHierarchyNode) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 0) {
                return;
            }
            ArrayList arrayList = new ArrayList(viewGroup.getChildCount());
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt != null) {
                    ViewHierarchyNode viewHierarchyNodeFromView = ViewHierarchyNode.INSTANCE.fromView(childAt, viewHierarchyNode, viewGroup.indexOfChild(childAt), this.options);
                    arrayList.add(viewHierarchyNodeFromView);
                    traverse(childAt, viewHierarchyNodeFromView);
                }
            }
            viewHierarchyNode.setChildren(arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ScreenshotRecorder.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lio/sentry/android/replay/ScreenshotRecorder$RecorderExecutorServiceThreadFactory;", "Ljava/util/concurrent/ThreadFactory;", "()V", "cnt", "", "newThread", "Ljava/lang/Thread;", "r", "Ljava/lang/Runnable;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class RecorderExecutorServiceThreadFactory implements ThreadFactory {
        private int cnt;

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Intrinsics.checkNotNullParameter(r, "r");
            StringBuilder sb = new StringBuilder("SentryReplayRecorder-");
            int i = this.cnt;
            this.cnt = i + 1;
            Thread thread = new Thread(r, sb.append(i).toString());
            thread.setDaemon(true);
            return thread;
        }
    }
}

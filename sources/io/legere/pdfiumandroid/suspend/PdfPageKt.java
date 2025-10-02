package io.legere.pdfiumandroid.suspend;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.Surface;
import io.legere.pdfiumandroid.Logger;
import io.legere.pdfiumandroid.PdfDocument;
import io.legere.pdfiumandroid.PdfPage;
import io.legere.pdfiumandroid.util.Size;
import java.io.Closeable;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PdfPageKt.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u000e\u0010\u000b\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ\u000e\u0010\u000f\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ\u000e\u0010\u0010\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\rJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0086@¢\u0006\u0002\u0010\rJ\u000e\u0010\u0019\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0014J\u000e\u0010\u001c\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ\u0016\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\u0014J\u000e\u0010\u001e\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010\rJF\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u00122\u0006\u0010'\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010(JF\u0010)\u001a\u00020*2\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00122\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0086@¢\u0006\u0002\u0010.J>\u0010/\u001a\u0002002\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00122\u0006\u00101\u001a\u00020\fH\u0086@¢\u0006\u0002\u00102J>\u00103\u001a\u00020\f2\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u00122\u0006\u00101\u001a\u000200H\u0086@¢\u0006\u0002\u00104J\u000e\u00105\u001a\u000206H\u0086@¢\u0006\u0002\u0010\rJ8\u00107\u001a\u00020\n2\b\u00108\u001a\u0004\u0018\u0001092\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010:\u001a\u00020\u00122\u0006\u0010;\u001a\u00020\u0012H\u0086@¢\u0006\u0002\u0010<JJ\u0010=\u001a\u00020\n2\u0006\u0010>\u001a\u00020?2\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010:\u001a\u00020\u00122\u0006\u0010;\u001a\u00020\u00122\b\b\u0002\u0010@\u001a\u00020A2\b\b\u0002\u0010B\u001a\u00020AH\u0086@¢\u0006\u0002\u0010CJ<\u0010=\u001a\u00020\n2\b\u0010>\u001a\u0004\u0018\u00010?2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020\f2\b\b\u0002\u0010@\u001a\u00020A2\b\b\u0002\u0010B\u001a\u00020AH\u0086@¢\u0006\u0002\u0010GJ\u0006\u0010H\u001a\u00020AR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006I"}, d2 = {"Lio/legere/pdfiumandroid/suspend/PdfPageKt;", "Ljava/io/Closeable;", "page", "Lio/legere/pdfiumandroid/PdfPage;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lio/legere/pdfiumandroid/PdfPage;Lkotlinx/coroutines/CoroutineDispatcher;)V", "getPage", "()Lio/legere/pdfiumandroid/PdfPage;", "close", "", "getPageArtBox", "Landroid/graphics/RectF;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPageBleedBox", "getPageBoundingBox", "getPageCropBox", "getPageHeight", "", "screenDpi", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPageHeightPoint", "getPageLinks", "", "Lio/legere/pdfiumandroid/PdfDocument$Link;", "getPageMediaBox", "getPageSize", "Lio/legere/pdfiumandroid/util/Size;", "getPageTrimBox", "getPageWidth", "getPageWidthPoint", "mapDeviceCoordsToPage", "Landroid/graphics/PointF;", "startX", "startY", "sizeX", "sizeY", "rotate", "deviceX", "deviceY", "(IIIIIIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapPageCoordsToDevice", "Landroid/graphics/Point;", "pageX", "", "pageY", "(IIIIIDDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapRectToDevice", "Landroid/graphics/Rect;", "coords", "(IIIIILandroid/graphics/RectF;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapRectToPage", "(IIIIILandroid/graphics/Rect;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openTextPage", "Lio/legere/pdfiumandroid/suspend/PdfTextPageKt;", "renderPage", "surface", "Landroid/view/Surface;", "drawSizeX", "drawSizeY", "(Landroid/view/Surface;IIIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "renderPageBitmap", "bitmap", "Landroid/graphics/Bitmap;", "renderAnnot", "", "textMask", "(Landroid/graphics/Bitmap;IIIIZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "matrix", "Landroid/graphics/Matrix;", "clipRect", "(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/RectF;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "safeClose", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PdfPageKt implements Closeable {
    private final CoroutineDispatcher dispatcher;
    private final PdfPage page;

    public PdfPageKt(PdfPage page, CoroutineDispatcher dispatcher) {
        Intrinsics.checkNotNullParameter(page, "page");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        this.page = page;
        this.dispatcher = dispatcher;
    }

    public final PdfPage getPage() {
        return this.page;
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lio/legere/pdfiumandroid/suspend/PdfTextPageKt;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$openTextPage$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$openTextPage$2, reason: invalid class name and case insensitive filesystem */
    static final class C00772 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PdfTextPageKt>, Object> {
        int label;

        C00772(Continuation<? super C00772> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00772(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PdfTextPageKt> continuation) {
            return ((C00772) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return new PdfTextPageKt(PdfPageKt.this.getPage().openTextPage(), PdfPageKt.this.dispatcher);
        }
    }

    public final Object openTextPage(Continuation<? super PdfTextPageKt> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00772(null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$getPageWidth$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$getPageWidth$2, reason: invalid class name and case insensitive filesystem */
    static final class C00712 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final /* synthetic */ int $screenDpi;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00712(int i, Continuation<? super C00712> continuation) {
            super(2, continuation);
            this.$screenDpi = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00712(this.$screenDpi, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C00712) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(PdfPageKt.this.getPage().getPageWidth(this.$screenDpi));
        }
    }

    public final Object getPageWidth(int i, Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00712(i, null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$getPageHeight$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$getPageHeight$2, reason: invalid class name and case insensitive filesystem */
    static final class C00652 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final /* synthetic */ int $screenDpi;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00652(int i, Continuation<? super C00652> continuation) {
            super(2, continuation);
            this.$screenDpi = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00652(this.$screenDpi, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C00652) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(PdfPageKt.this.getPage().getPageHeight(this.$screenDpi));
        }
    }

    public final Object getPageHeight(int i, Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00652(i, null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$getPageWidthPoint$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$getPageWidthPoint$2, reason: invalid class name and case insensitive filesystem */
    static final class C00722 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        C00722(Continuation<? super C00722> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00722(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C00722) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(PdfPageKt.this.getPage().getPageWidthPoint());
        }
    }

    public final Object getPageWidthPoint(Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00722(null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$getPageHeightPoint$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$getPageHeightPoint$2, reason: invalid class name and case insensitive filesystem */
    static final class C00662 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        C00662(Continuation<? super C00662> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00662(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C00662) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(PdfPageKt.this.getPage().getPageHeightPoint());
        }
    }

    public final Object getPageHeightPoint(Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00662(null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/RectF;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$getPageCropBox$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$getPageCropBox$2, reason: invalid class name and case insensitive filesystem */
    static final class C00642 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RectF>, Object> {
        int label;

        C00642(Continuation<? super C00642> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00642(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super RectF> continuation) {
            return ((C00642) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfPageKt.this.getPage().getPageCropBox();
        }
    }

    public final Object getPageCropBox(Continuation<? super RectF> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00642(null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/RectF;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$getPageMediaBox$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$getPageMediaBox$2, reason: invalid class name and case insensitive filesystem */
    static final class C00682 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RectF>, Object> {
        int label;

        C00682(Continuation<? super C00682> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00682(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super RectF> continuation) {
            return ((C00682) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfPageKt.this.getPage().getPageMediaBox();
        }
    }

    public final Object getPageMediaBox(Continuation<? super RectF> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00682(null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/RectF;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$getPageBleedBox$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$getPageBleedBox$2, reason: invalid class name and case insensitive filesystem */
    static final class C00622 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RectF>, Object> {
        int label;

        C00622(Continuation<? super C00622> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00622(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super RectF> continuation) {
            return ((C00622) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfPageKt.this.getPage().getPageBleedBox();
        }
    }

    public final Object getPageBleedBox(Continuation<? super RectF> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00622(null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/RectF;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$getPageTrimBox$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$getPageTrimBox$2, reason: invalid class name and case insensitive filesystem */
    static final class C00702 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RectF>, Object> {
        int label;

        C00702(Continuation<? super C00702> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00702(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super RectF> continuation) {
            return ((C00702) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfPageKt.this.getPage().getPageTrimBox();
        }
    }

    public final Object getPageTrimBox(Continuation<? super RectF> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00702(null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/RectF;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$getPageArtBox$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$getPageArtBox$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RectF>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super RectF> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfPageKt.this.getPage().getPageArtBox();
        }
    }

    public final Object getPageArtBox(Continuation<? super RectF> continuation) {
        return BuildersKt.withContext(this.dispatcher, new AnonymousClass2(null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/RectF;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$getPageBoundingBox$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$getPageBoundingBox$2, reason: invalid class name and case insensitive filesystem */
    static final class C00632 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RectF>, Object> {
        int label;

        C00632(Continuation<? super C00632> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00632(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super RectF> continuation) {
            return ((C00632) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfPageKt.this.getPage().getPageBoundingBox();
        }
    }

    public final Object getPageBoundingBox(Continuation<? super RectF> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00632(null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lio/legere/pdfiumandroid/util/Size;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$getPageSize$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$getPageSize$2, reason: invalid class name and case insensitive filesystem */
    static final class C00692 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Size>, Object> {
        final /* synthetic */ int $screenDpi;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00692(int i, Continuation<? super C00692> continuation) {
            super(2, continuation);
            this.$screenDpi = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00692(this.$screenDpi, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Size> continuation) {
            return ((C00692) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfPageKt.this.getPage().getPageSize(this.$screenDpi);
        }
    }

    public final Object getPageSize(int i, Continuation<? super Size> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00692(i, null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$renderPage$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$renderPage$2, reason: invalid class name and case insensitive filesystem */
    static final class C00782 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $drawSizeX;
        final /* synthetic */ int $drawSizeY;
        final /* synthetic */ int $startX;
        final /* synthetic */ int $startY;
        final /* synthetic */ Surface $surface;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00782(Surface surface, int i, int i2, int i3, int i4, Continuation<? super C00782> continuation) {
            super(2, continuation);
            this.$surface = surface;
            this.$startX = i;
            this.$startY = i2;
            this.$drawSizeX = i3;
            this.$drawSizeY = i4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00782(this.$surface, this.$startX, this.$startY, this.$drawSizeX, this.$drawSizeY, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00782) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            PdfPage.renderPage$default(PdfPageKt.this.getPage(), this.$surface, this.$startX, this.$startY, this.$drawSizeX, this.$drawSizeY, false, 32, null);
            return Unit.INSTANCE;
        }
    }

    public final Object renderPage(Surface surface, int i, int i2, int i3, int i4, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(this.dispatcher, new C00782(surface, i, i2, i3, i4, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$renderPageBitmap$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$renderPageBitmap$2, reason: invalid class name and case insensitive filesystem */
    static final class C00792 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Bitmap $bitmap;
        final /* synthetic */ int $drawSizeX;
        final /* synthetic */ int $drawSizeY;
        final /* synthetic */ boolean $renderAnnot;
        final /* synthetic */ int $startX;
        final /* synthetic */ int $startY;
        final /* synthetic */ boolean $textMask;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00792(Bitmap bitmap, int i, int i2, int i3, int i4, boolean z, boolean z2, Continuation<? super C00792> continuation) {
            super(2, continuation);
            this.$bitmap = bitmap;
            this.$startX = i;
            this.$startY = i2;
            this.$drawSizeX = i3;
            this.$drawSizeY = i4;
            this.$renderAnnot = z;
            this.$textMask = z2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00792(this.$bitmap, this.$startX, this.$startY, this.$drawSizeX, this.$drawSizeY, this.$renderAnnot, this.$textMask, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C00792) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            PdfPageKt.this.getPage().renderPageBitmap(this.$bitmap, this.$startX, this.$startY, this.$drawSizeX, this.$drawSizeY, this.$renderAnnot, this.$textMask);
            return Unit.INSTANCE;
        }
    }

    public final Object renderPageBitmap(Bitmap bitmap, int i, int i2, int i3, int i4, boolean z, boolean z2, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(this.dispatcher, new C00792(bitmap, i, i2, i3, i4, z, z2, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$renderPageBitmap$4", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$renderPageBitmap$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Bitmap $bitmap;
        final /* synthetic */ RectF $clipRect;
        final /* synthetic */ Matrix $matrix;
        final /* synthetic */ boolean $renderAnnot;
        final /* synthetic */ boolean $textMask;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Bitmap bitmap, Matrix matrix, RectF rectF, boolean z, boolean z2, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$bitmap = bitmap;
            this.$matrix = matrix;
            this.$clipRect = rectF;
            this.$renderAnnot = z;
            this.$textMask = z2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new AnonymousClass4(this.$bitmap, this.$matrix, this.$clipRect, this.$renderAnnot, this.$textMask, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            PdfPageKt.this.getPage().renderPageBitmap(this.$bitmap, this.$matrix, this.$clipRect, this.$renderAnnot, this.$textMask);
            return Unit.INSTANCE;
        }
    }

    public final Object renderPageBitmap(Bitmap bitmap, Matrix matrix, RectF rectF, boolean z, boolean z2, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(this.dispatcher, new AnonymousClass4(bitmap, matrix, rectF, z, z2, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/legere/pdfiumandroid/PdfDocument$Link;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$getPageLinks$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$getPageLinks$2, reason: invalid class name and case insensitive filesystem */
    static final class C00672 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends PdfDocument.Link>>, Object> {
        int label;

        C00672(Continuation<? super C00672> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00672(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends PdfDocument.Link>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<PdfDocument.Link>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<PdfDocument.Link>> continuation) {
            return ((C00672) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfPageKt.this.getPage().getPageLinks();
        }
    }

    public final Object getPageLinks(Continuation<? super List<PdfDocument.Link>> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00672(null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/Point;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$mapPageCoordsToDevice$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$mapPageCoordsToDevice$2, reason: invalid class name and case insensitive filesystem */
    static final class C00742 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Point>, Object> {
        final /* synthetic */ double $pageX;
        final /* synthetic */ double $pageY;
        final /* synthetic */ int $rotate;
        final /* synthetic */ int $sizeX;
        final /* synthetic */ int $sizeY;
        final /* synthetic */ int $startX;
        final /* synthetic */ int $startY;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00742(int i, int i2, int i3, int i4, int i5, double d, double d2, Continuation<? super C00742> continuation) {
            super(2, continuation);
            this.$startX = i;
            this.$startY = i2;
            this.$sizeX = i3;
            this.$sizeY = i4;
            this.$rotate = i5;
            this.$pageX = d;
            this.$pageY = d2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00742(this.$startX, this.$startY, this.$sizeX, this.$sizeY, this.$rotate, this.$pageX, this.$pageY, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Point> continuation) {
            return ((C00742) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfPageKt.this.getPage().mapPageCoordsToDevice(this.$startX, this.$startY, this.$sizeX, this.$sizeY, this.$rotate, this.$pageX, this.$pageY);
        }
    }

    public final Object mapPageCoordsToDevice(int i, int i2, int i3, int i4, int i5, double d, double d2, Continuation<? super Point> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00742(i, i2, i3, i4, i5, d, d2, null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/PointF;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$mapDeviceCoordsToPage$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$mapDeviceCoordsToPage$2, reason: invalid class name and case insensitive filesystem */
    static final class C00732 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PointF>, Object> {
        final /* synthetic */ int $deviceX;
        final /* synthetic */ int $deviceY;
        final /* synthetic */ int $rotate;
        final /* synthetic */ int $sizeX;
        final /* synthetic */ int $sizeY;
        final /* synthetic */ int $startX;
        final /* synthetic */ int $startY;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00732(int i, int i2, int i3, int i4, int i5, int i6, int i7, Continuation<? super C00732> continuation) {
            super(2, continuation);
            this.$startX = i;
            this.$startY = i2;
            this.$sizeX = i3;
            this.$sizeY = i4;
            this.$rotate = i5;
            this.$deviceX = i6;
            this.$deviceY = i7;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00732(this.$startX, this.$startY, this.$sizeX, this.$sizeY, this.$rotate, this.$deviceX, this.$deviceY, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PointF> continuation) {
            return ((C00732) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfPageKt.this.getPage().mapDeviceCoordsToPage(this.$startX, this.$startY, this.$sizeX, this.$sizeY, this.$rotate, this.$deviceX, this.$deviceY);
        }
    }

    public final Object mapDeviceCoordsToPage(int i, int i2, int i3, int i4, int i5, int i6, int i7, Continuation<? super PointF> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00732(i, i2, i3, i4, i5, i6, i7, null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/Rect;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$mapRectToDevice$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$mapRectToDevice$2, reason: invalid class name and case insensitive filesystem */
    static final class C00752 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Rect>, Object> {
        final /* synthetic */ RectF $coords;
        final /* synthetic */ int $rotate;
        final /* synthetic */ int $sizeX;
        final /* synthetic */ int $sizeY;
        final /* synthetic */ int $startX;
        final /* synthetic */ int $startY;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00752(int i, int i2, int i3, int i4, int i5, RectF rectF, Continuation<? super C00752> continuation) {
            super(2, continuation);
            this.$startX = i;
            this.$startY = i2;
            this.$sizeX = i3;
            this.$sizeY = i4;
            this.$rotate = i5;
            this.$coords = rectF;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00752(this.$startX, this.$startY, this.$sizeX, this.$sizeY, this.$rotate, this.$coords, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Rect> continuation) {
            return ((C00752) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfPageKt.this.getPage().mapRectToDevice(this.$startX, this.$startY, this.$sizeX, this.$sizeY, this.$rotate, this.$coords);
        }
    }

    public final Object mapRectToDevice(int i, int i2, int i3, int i4, int i5, RectF rectF, Continuation<? super Rect> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00752(i, i2, i3, i4, i5, rectF, null), continuation);
    }

    /* compiled from: PdfPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/RectF;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfPageKt$mapRectToPage$2", f = "PdfPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfPageKt$mapRectToPage$2, reason: invalid class name and case insensitive filesystem */
    static final class C00762 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RectF>, Object> {
        final /* synthetic */ Rect $coords;
        final /* synthetic */ int $rotate;
        final /* synthetic */ int $sizeX;
        final /* synthetic */ int $sizeY;
        final /* synthetic */ int $startX;
        final /* synthetic */ int $startY;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00762(int i, int i2, int i3, int i4, int i5, Rect rect, Continuation<? super C00762> continuation) {
            super(2, continuation);
            this.$startX = i;
            this.$startY = i2;
            this.$sizeX = i3;
            this.$sizeY = i4;
            this.$rotate = i5;
            this.$coords = rect;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfPageKt.this.new C00762(this.$startX, this.$startY, this.$sizeX, this.$sizeY, this.$rotate, this.$coords, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super RectF> continuation) {
            return ((C00762) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfPageKt.this.getPage().mapRectToPage(this.$startX, this.$startY, this.$sizeX, this.$sizeY, this.$rotate, this.$coords);
        }
    }

    public final Object mapRectToPage(int i, int i2, int i3, int i4, int i5, Rect rect, Continuation<? super RectF> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00762(i, i2, i3, i4, i5, rect, null), continuation);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.page.close();
    }

    public final boolean safeClose() {
        try {
            this.page.close();
            return true;
        } catch (IllegalStateException e) {
            Logger.INSTANCE.e("PdfPageKt", e, "PdfPageKt.safeClose");
            return false;
        }
    }
}

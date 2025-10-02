package io.legere.pdfiumandroid.suspend;

import android.graphics.RectF;
import io.legere.pdfiumandroid.Logger;
import io.legere.pdfiumandroid.PdfTextPage;
import io.sentry.SentryEnvelopeItemHeader;
import io.sentry.protocol.MetricSummary;
import java.io.Closeable;
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

/* compiled from: PdfTextPageKt.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\f\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010\u0013J\u001e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010\u0017J \u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010\u001dJ\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001f\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010\u000fJ.\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010%J\u0018\u0010&\u001a\u0004\u0018\u00010\u001b2\u0006\u0010'\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010\u000fJ \u0010(\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010)\u001a\u00020*2\u0006\u0010\u001f\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006+"}, d2 = {"Lio/legere/pdfiumandroid/suspend/PdfTextPageKt;", "Ljava/io/Closeable;", "page", "Lio/legere/pdfiumandroid/PdfTextPage;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lio/legere/pdfiumandroid/PdfTextPage;Lkotlinx/coroutines/CoroutineDispatcher;)V", "getPage", "()Lio/legere/pdfiumandroid/PdfTextPage;", "close", "", "getFontSize", "", "charIndex", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "safeClose", "", "textPageCountChars", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "textPageCountRects", "startIndex", MetricSummary.JsonKeys.COUNT, "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "textPageGetBoundedText", "", "rect", "Landroid/graphics/RectF;", SentryEnvelopeItemHeader.JsonKeys.LENGTH, "(Landroid/graphics/RectF;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "textPageGetCharBox", "index", "textPageGetCharIndexAtPos", "x", "y", "xTolerance", "yTolerance", "(DDDDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "textPageGetRect", "rectIndex", "textPageGetText", "textPageGetUnicode", "", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PdfTextPageKt implements Closeable {
    private final CoroutineDispatcher dispatcher;
    private final PdfTextPage page;

    public PdfTextPageKt(PdfTextPage page, CoroutineDispatcher dispatcher) {
        Intrinsics.checkNotNullParameter(page, "page");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        this.page = page;
        this.dispatcher = dispatcher;
    }

    public final PdfTextPage getPage() {
        return this.page;
    }

    /* compiled from: PdfTextPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageCountChars$2", f = "PdfTextPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageCountChars$2, reason: invalid class name and case insensitive filesystem */
    static final class C00802 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        C00802(Continuation<? super C00802> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfTextPageKt.this.new C00802(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C00802) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(PdfTextPageKt.this.getPage().textPageCountChars());
        }
    }

    public final Object textPageCountChars(Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00802(null), continuation);
    }

    /* compiled from: PdfTextPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageGetText$2", f = "PdfTextPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageGetText$2, reason: invalid class name and case insensitive filesystem */
    static final class C00862 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ int $length;
        final /* synthetic */ int $startIndex;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00862(int i, int i2, Continuation<? super C00862> continuation) {
            super(2, continuation);
            this.$startIndex = i;
            this.$length = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfTextPageKt.this.new C00862(this.$startIndex, this.$length, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((C00862) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfTextPageKt.this.getPage().textPageGetText(this.$startIndex, this.$length);
        }
    }

    public final Object textPageGetText(int i, int i2, Continuation<? super String> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00862(i, i2, null), continuation);
    }

    /* compiled from: PdfTextPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\f\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageGetUnicode$2", f = "PdfTextPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageGetUnicode$2, reason: invalid class name and case insensitive filesystem */
    static final class C00872 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Character>, Object> {
        final /* synthetic */ int $index;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00872(int i, Continuation<? super C00872> continuation) {
            super(2, continuation);
            this.$index = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfTextPageKt.this.new C00872(this.$index, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Character> continuation) {
            return ((C00872) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxChar(PdfTextPageKt.this.getPage().textPageGetUnicode(this.$index));
        }
    }

    public final Object textPageGetUnicode(int i, Continuation<? super Character> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00872(i, null), continuation);
    }

    /* compiled from: PdfTextPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/RectF;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageGetCharBox$2", f = "PdfTextPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageGetCharBox$2, reason: invalid class name and case insensitive filesystem */
    static final class C00832 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RectF>, Object> {
        final /* synthetic */ int $index;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00832(int i, Continuation<? super C00832> continuation) {
            super(2, continuation);
            this.$index = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfTextPageKt.this.new C00832(this.$index, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super RectF> continuation) {
            return ((C00832) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfTextPageKt.this.getPage().textPageGetCharBox(this.$index);
        }
    }

    public final Object textPageGetCharBox(int i, Continuation<? super RectF> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00832(i, null), continuation);
    }

    /* compiled from: PdfTextPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageGetCharIndexAtPos$2", f = "PdfTextPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageGetCharIndexAtPos$2, reason: invalid class name and case insensitive filesystem */
    static final class C00842 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final /* synthetic */ double $x;
        final /* synthetic */ double $xTolerance;
        final /* synthetic */ double $y;
        final /* synthetic */ double $yTolerance;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00842(double d, double d2, double d3, double d4, Continuation<? super C00842> continuation) {
            super(2, continuation);
            this.$x = d;
            this.$y = d2;
            this.$xTolerance = d3;
            this.$yTolerance = d4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfTextPageKt.this.new C00842(this.$x, this.$y, this.$xTolerance, this.$yTolerance, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C00842) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(PdfTextPageKt.this.getPage().textPageGetCharIndexAtPos(this.$x, this.$y, this.$xTolerance, this.$yTolerance));
        }
    }

    public final Object textPageGetCharIndexAtPos(double d, double d2, double d3, double d4, Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00842(d, d2, d3, d4, null), continuation);
    }

    /* compiled from: PdfTextPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageCountRects$2", f = "PdfTextPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageCountRects$2, reason: invalid class name and case insensitive filesystem */
    static final class C00812 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final /* synthetic */ int $count;
        final /* synthetic */ int $startIndex;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00812(int i, int i2, Continuation<? super C00812> continuation) {
            super(2, continuation);
            this.$startIndex = i;
            this.$count = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfTextPageKt.this.new C00812(this.$startIndex, this.$count, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C00812) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(PdfTextPageKt.this.getPage().textPageCountRects(this.$startIndex, this.$count));
        }
    }

    public final Object textPageCountRects(int i, int i2, Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00812(i, i2, null), continuation);
    }

    /* compiled from: PdfTextPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/graphics/RectF;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageGetRect$2", f = "PdfTextPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageGetRect$2, reason: invalid class name and case insensitive filesystem */
    static final class C00852 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super RectF>, Object> {
        final /* synthetic */ int $rectIndex;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00852(int i, Continuation<? super C00852> continuation) {
            super(2, continuation);
            this.$rectIndex = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfTextPageKt.this.new C00852(this.$rectIndex, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super RectF> continuation) {
            return ((C00852) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfTextPageKt.this.getPage().textPageGetRect(this.$rectIndex);
        }
    }

    public final Object textPageGetRect(int i, Continuation<? super RectF> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00852(i, null), continuation);
    }

    /* compiled from: PdfTextPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageGetBoundedText$2", f = "PdfTextPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfTextPageKt$textPageGetBoundedText$2, reason: invalid class name and case insensitive filesystem */
    static final class C00822 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ int $length;
        final /* synthetic */ RectF $rect;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00822(RectF rectF, int i, Continuation<? super C00822> continuation) {
            super(2, continuation);
            this.$rect = rectF;
            this.$length = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfTextPageKt.this.new C00822(this.$rect, this.$length, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((C00822) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfTextPageKt.this.getPage().textPageGetBoundedText(this.$rect, this.$length);
        }
    }

    public final Object textPageGetBoundedText(RectF rectF, int i, Continuation<? super String> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00822(rectF, i, null), continuation);
    }

    /* compiled from: PdfTextPageKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0006\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfTextPageKt$getFontSize$2", f = "PdfTextPageKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfTextPageKt$getFontSize$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Double>, Object> {
        final /* synthetic */ int $charIndex;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(int i, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$charIndex = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfTextPageKt.this.new AnonymousClass2(this.$charIndex, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Double> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxDouble(PdfTextPageKt.this.getPage().getFontSize(this.$charIndex));
        }
    }

    public final Object getFontSize(int i, Continuation<? super Double> continuation) {
        return BuildersKt.withContext(this.dispatcher, new AnonymousClass2(i, null), continuation);
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
            Logger.INSTANCE.e("PdfTextPageKt", e, "PdfTextPageKt.safeClose");
            return false;
        }
    }
}

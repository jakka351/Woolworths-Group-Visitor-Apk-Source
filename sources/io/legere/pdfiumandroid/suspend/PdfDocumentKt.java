package io.legere.pdfiumandroid.suspend;

import io.legere.pdfiumandroid.Logger;
import io.legere.pdfiumandroid.PdfDocument;
import io.legere.pdfiumandroid.PdfPage;
import io.legere.pdfiumandroid.PdfTextPage;
import io.legere.pdfiumandroid.PdfWriteCallback;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
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

/* compiled from: PdfDocumentKt.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u000e\u0010\u000b\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010\rJ\u000e\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\rJ\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0086@¢\u0006\u0002\u0010\rJ\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0018J$\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00160\u00132\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0016H\u0087@¢\u0006\u0002\u0010 J$\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00132\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u001b\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u001cJ\u0006\u0010\"\u001a\u00020#J\u0016\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020&H\u0086@¢\u0006\u0002\u0010'R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006("}, d2 = {"Lio/legere/pdfiumandroid/suspend/PdfDocumentKt;", "Ljava/io/Closeable;", "document", "Lio/legere/pdfiumandroid/PdfDocument;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lio/legere/pdfiumandroid/PdfDocument;Lkotlinx/coroutines/CoroutineDispatcher;)V", "getDocument", "()Lio/legere/pdfiumandroid/PdfDocument;", "close", "", "getDocumentMeta", "Lio/legere/pdfiumandroid/PdfDocument$Meta;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPageCharCounts", "", "getPageCount", "", "getTableOfContents", "", "Lio/legere/pdfiumandroid/PdfDocument$Bookmark;", "openPage", "Lio/legere/pdfiumandroid/suspend/PdfPageKt;", "pageIndex", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openPages", "fromIndex", "toIndex", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openTextPage", "Lio/legere/pdfiumandroid/suspend/PdfTextPageKt;", "page", "(Lio/legere/pdfiumandroid/suspend/PdfPageKt;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openTextPages", "safeClose", "", "saveAsCopy", "callback", "Lio/legere/pdfiumandroid/PdfWriteCallback;", "(Lio/legere/pdfiumandroid/PdfWriteCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PdfDocumentKt implements Closeable {
    private final CoroutineDispatcher dispatcher;
    private final PdfDocument document;

    public PdfDocumentKt(PdfDocument document, CoroutineDispatcher dispatcher) {
        Intrinsics.checkNotNullParameter(document, "document");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        this.document = document;
        this.dispatcher = dispatcher;
    }

    public final PdfDocument getDocument() {
        return this.document;
    }

    /* compiled from: PdfDocumentKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfDocumentKt$getPageCount$2", f = "PdfDocumentKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfDocumentKt$getPageCount$2, reason: invalid class name and case insensitive filesystem */
    static final class C00552 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        C00552(Continuation<? super C00552> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfDocumentKt.this.new C00552(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C00552) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(PdfDocumentKt.this.getDocument().getPageCount());
        }
    }

    public final Object getPageCount(Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00552(null), continuation);
    }

    /* compiled from: PdfDocumentKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0015\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfDocumentKt$getPageCharCounts$2", f = "PdfDocumentKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfDocumentKt$getPageCharCounts$2, reason: invalid class name and case insensitive filesystem */
    static final class C00542 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super int[]>, Object> {
        int label;

        C00542(Continuation<? super C00542> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfDocumentKt.this.new C00542(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super int[]> continuation) {
            return ((C00542) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfDocumentKt.this.getDocument().getPageCharCounts();
        }
    }

    public final Object getPageCharCounts(Continuation<? super int[]> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00542(null), continuation);
    }

    /* compiled from: PdfDocumentKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lio/legere/pdfiumandroid/suspend/PdfPageKt;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfDocumentKt$openPage$2", f = "PdfDocumentKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfDocumentKt$openPage$2, reason: invalid class name and case insensitive filesystem */
    static final class C00572 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PdfPageKt>, Object> {
        final /* synthetic */ int $pageIndex;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00572(int i, Continuation<? super C00572> continuation) {
            super(2, continuation);
            this.$pageIndex = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfDocumentKt.this.new C00572(this.$pageIndex, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PdfPageKt> continuation) {
            return ((C00572) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return new PdfPageKt(PdfDocumentKt.this.getDocument().openPage(this.$pageIndex), PdfDocumentKt.this.dispatcher);
        }
    }

    public final Object openPage(int i, Continuation<? super PdfPageKt> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00572(i, null), continuation);
    }

    /* compiled from: PdfDocumentKt.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/legere/pdfiumandroid/suspend/PdfPageKt;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfDocumentKt$openPages$2", f = "PdfDocumentKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfDocumentKt$openPages$2, reason: invalid class name and case insensitive filesystem */
    static final class C00582 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends PdfPageKt>>, Object> {
        final /* synthetic */ int $fromIndex;
        final /* synthetic */ int $toIndex;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00582(int i, int i2, Continuation<? super C00582> continuation) {
            super(2, continuation);
            this.$fromIndex = i;
            this.$toIndex = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfDocumentKt.this.new C00582(this.$fromIndex, this.$toIndex, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends PdfPageKt>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<PdfPageKt>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<PdfPageKt>> continuation) {
            return ((C00582) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List<PdfPage> listOpenPages = PdfDocumentKt.this.getDocument().openPages(this.$fromIndex, this.$toIndex);
            PdfDocumentKt pdfDocumentKt = PdfDocumentKt.this;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listOpenPages, 10));
            Iterator<T> it = listOpenPages.iterator();
            while (it.hasNext()) {
                arrayList.add(new PdfPageKt((PdfPage) it.next(), pdfDocumentKt.dispatcher));
            }
            return arrayList;
        }
    }

    public final Object openPages(int i, int i2, Continuation<? super List<PdfPageKt>> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00582(i, i2, null), continuation);
    }

    /* compiled from: PdfDocumentKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lio/legere/pdfiumandroid/PdfDocument$Meta;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfDocumentKt$getDocumentMeta$2", f = "PdfDocumentKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfDocumentKt$getDocumentMeta$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PdfDocument.Meta>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfDocumentKt.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PdfDocument.Meta> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfDocumentKt.this.getDocument().getDocumentMeta();
        }
    }

    public final Object getDocumentMeta(Continuation<? super PdfDocument.Meta> continuation) {
        return BuildersKt.withContext(this.dispatcher, new AnonymousClass2(null), continuation);
    }

    /* compiled from: PdfDocumentKt.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/legere/pdfiumandroid/PdfDocument$Bookmark;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfDocumentKt$getTableOfContents$2", f = "PdfDocumentKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfDocumentKt$getTableOfContents$2, reason: invalid class name and case insensitive filesystem */
    static final class C00562 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends PdfDocument.Bookmark>>, Object> {
        int label;

        C00562(Continuation<? super C00562> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfDocumentKt.this.new C00562(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends PdfDocument.Bookmark>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<PdfDocument.Bookmark>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<PdfDocument.Bookmark>> continuation) {
            return ((C00562) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PdfDocumentKt.this.getDocument().getTableOfContents();
        }
    }

    public final Object getTableOfContents(Continuation<? super List<PdfDocument.Bookmark>> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00562(null), continuation);
    }

    /* compiled from: PdfDocumentKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lio/legere/pdfiumandroid/suspend/PdfTextPageKt;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfDocumentKt$openTextPage$2", f = "PdfDocumentKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfDocumentKt$openTextPage$2, reason: invalid class name and case insensitive filesystem */
    static final class C00592 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PdfTextPageKt>, Object> {
        final /* synthetic */ PdfPageKt $page;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00592(PdfPageKt pdfPageKt, Continuation<? super C00592> continuation) {
            super(2, continuation);
            this.$page = pdfPageKt;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfDocumentKt.this.new C00592(this.$page, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PdfTextPageKt> continuation) {
            return ((C00592) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return new PdfTextPageKt(PdfDocumentKt.this.getDocument().openTextPage(this.$page.getPage()), PdfDocumentKt.this.dispatcher);
        }
    }

    @Deprecated(message = "use PdfPageKt.openTextPage", replaceWith = @ReplaceWith(expression = "page.openTextPage()", imports = {}))
    public final Object openTextPage(PdfPageKt pdfPageKt, Continuation<? super PdfTextPageKt> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00592(pdfPageKt, null), continuation);
    }

    /* compiled from: PdfDocumentKt.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lio/legere/pdfiumandroid/suspend/PdfTextPageKt;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfDocumentKt$openTextPages$2", f = "PdfDocumentKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfDocumentKt$openTextPages$2, reason: invalid class name and case insensitive filesystem */
    static final class C00602 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends PdfTextPageKt>>, Object> {
        final /* synthetic */ int $fromIndex;
        final /* synthetic */ int $toIndex;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00602(int i, int i2, Continuation<? super C00602> continuation) {
            super(2, continuation);
            this.$fromIndex = i;
            this.$toIndex = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfDocumentKt.this.new C00602(this.$fromIndex, this.$toIndex, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends PdfTextPageKt>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<PdfTextPageKt>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<PdfTextPageKt>> continuation) {
            return ((C00602) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            List<PdfTextPage> listOpenTextPages = PdfDocumentKt.this.getDocument().openTextPages(this.$fromIndex, this.$toIndex);
            PdfDocumentKt pdfDocumentKt = PdfDocumentKt.this;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listOpenTextPages, 10));
            Iterator<T> it = listOpenTextPages.iterator();
            while (it.hasNext()) {
                arrayList.add(new PdfTextPageKt((PdfTextPage) it.next(), pdfDocumentKt.dispatcher));
            }
            return arrayList;
        }
    }

    public final Object openTextPages(int i, int i2, Continuation<? super List<PdfTextPageKt>> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00602(i, i2, null), continuation);
    }

    /* compiled from: PdfDocumentKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfDocumentKt$saveAsCopy$2", f = "PdfDocumentKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfDocumentKt$saveAsCopy$2, reason: invalid class name and case insensitive filesystem */
    static final class C00612 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ PdfWriteCallback $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00612(PdfWriteCallback pdfWriteCallback, Continuation<? super C00612> continuation) {
            super(2, continuation);
            this.$callback = pdfWriteCallback;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfDocumentKt.this.new C00612(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((C00612) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(PdfDocument.saveAsCopy$default(PdfDocumentKt.this.getDocument(), this.$callback, 0, 2, null));
        }
    }

    public final Object saveAsCopy(PdfWriteCallback pdfWriteCallback, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(this.dispatcher, new C00612(pdfWriteCallback, null), continuation);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.document.close();
    }

    public final boolean safeClose() {
        try {
            this.document.close();
            return true;
        } catch (IllegalStateException e) {
            Logger.INSTANCE.e("PdfDocumentKt", e, "PdfDocumentKt.safeClose");
            return false;
        }
    }
}

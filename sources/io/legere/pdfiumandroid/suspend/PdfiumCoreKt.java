package io.legere.pdfiumandroid.suspend;

import android.os.ParcelFileDescriptor;
import com.reactnativedevicecountry.DeviceCountryModule;
import io.legere.pdfiumandroid.PdfiumCore;
import io.legere.pdfiumandroid.util.Config;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PdfiumCoreKt.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@¢\u0006\u0002\u0010\rJ \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0086@¢\u0006\u0002\u0010\u0010J\u0018\u0010\t\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0086@¢\u0006\u0002\u0010\u0013J\"\u0010\t\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0086@¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lio/legere/pdfiumandroid/suspend/PdfiumCoreKt;", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", DeviceCountryModule.TYPE_CONFIGURATION, "Lio/legere/pdfiumandroid/util/Config;", "(Lkotlinx/coroutines/CoroutineDispatcher;Lio/legere/pdfiumandroid/util/Config;)V", "coreInternal", "Lio/legere/pdfiumandroid/PdfiumCore;", "newDocument", "Lio/legere/pdfiumandroid/suspend/PdfDocumentKt;", "fd", "Landroid/os/ParcelFileDescriptor;", "(Landroid/os/ParcelFileDescriptor;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "password", "", "(Landroid/os/ParcelFileDescriptor;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data", "", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "([BLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PdfiumCoreKt {
    private final PdfiumCore coreInternal;
    private final CoroutineDispatcher dispatcher;

    public PdfiumCoreKt(CoroutineDispatcher dispatcher, Config config) {
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(config, "config");
        this.dispatcher = dispatcher;
        this.coreInternal = new PdfiumCore(null, config, 1, null);
    }

    public /* synthetic */ PdfiumCoreKt(CoroutineDispatcher coroutineDispatcher, Config config, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(coroutineDispatcher, (i & 2) != 0 ? new Config(null, null, 3, null) : config);
    }

    /* compiled from: PdfiumCoreKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lio/legere/pdfiumandroid/suspend/PdfDocumentKt;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfiumCoreKt$newDocument$2", f = "PdfiumCoreKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfiumCoreKt$newDocument$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PdfDocumentKt>, Object> {
        final /* synthetic */ ParcelFileDescriptor $fd;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ParcelFileDescriptor parcelFileDescriptor, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$fd = parcelFileDescriptor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfiumCoreKt.this.new AnonymousClass2(this.$fd, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PdfDocumentKt> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return new PdfDocumentKt(PdfiumCoreKt.this.coreInternal.newDocument(this.$fd), PdfiumCoreKt.this.dispatcher);
        }
    }

    public final Object newDocument(ParcelFileDescriptor parcelFileDescriptor, Continuation<? super PdfDocumentKt> continuation) {
        return BuildersKt.withContext(this.dispatcher, new AnonymousClass2(parcelFileDescriptor, null), continuation);
    }

    /* compiled from: PdfiumCoreKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lio/legere/pdfiumandroid/suspend/PdfDocumentKt;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfiumCoreKt$newDocument$4", f = "PdfiumCoreKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfiumCoreKt$newDocument$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PdfDocumentKt>, Object> {
        final /* synthetic */ ParcelFileDescriptor $fd;
        final /* synthetic */ String $password;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(ParcelFileDescriptor parcelFileDescriptor, String str, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$fd = parcelFileDescriptor;
            this.$password = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfiumCoreKt.this.new AnonymousClass4(this.$fd, this.$password, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PdfDocumentKt> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return new PdfDocumentKt(PdfiumCoreKt.this.coreInternal.newDocument(this.$fd, this.$password), PdfiumCoreKt.this.dispatcher);
        }
    }

    public final Object newDocument(ParcelFileDescriptor parcelFileDescriptor, String str, Continuation<? super PdfDocumentKt> continuation) {
        return BuildersKt.withContext(this.dispatcher, new AnonymousClass4(parcelFileDescriptor, str, null), continuation);
    }

    /* compiled from: PdfiumCoreKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lio/legere/pdfiumandroid/suspend/PdfDocumentKt;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfiumCoreKt$newDocument$6", f = "PdfiumCoreKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfiumCoreKt$newDocument$6, reason: invalid class name */
    static final class AnonymousClass6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PdfDocumentKt>, Object> {
        final /* synthetic */ byte[] $data;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass6(byte[] bArr, Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
            this.$data = bArr;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfiumCoreKt.this.new AnonymousClass6(this.$data, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PdfDocumentKt> continuation) {
            return ((AnonymousClass6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return new PdfDocumentKt(PdfiumCoreKt.this.coreInternal.newDocument(this.$data), PdfiumCoreKt.this.dispatcher);
        }
    }

    public final Object newDocument(byte[] bArr, Continuation<? super PdfDocumentKt> continuation) {
        return BuildersKt.withContext(this.dispatcher, new AnonymousClass6(bArr, null), continuation);
    }

    /* compiled from: PdfiumCoreKt.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lio/legere/pdfiumandroid/suspend/PdfDocumentKt;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "io.legere.pdfiumandroid.suspend.PdfiumCoreKt$newDocument$8", f = "PdfiumCoreKt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.legere.pdfiumandroid.suspend.PdfiumCoreKt$newDocument$8, reason: invalid class name */
    static final class AnonymousClass8 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PdfDocumentKt>, Object> {
        final /* synthetic */ byte[] $data;
        final /* synthetic */ String $password;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass8(byte[] bArr, String str, Continuation<? super AnonymousClass8> continuation) {
            super(2, continuation);
            this.$data = bArr;
            this.$password = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PdfiumCoreKt.this.new AnonymousClass8(this.$data, this.$password, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PdfDocumentKt> continuation) {
            return ((AnonymousClass8) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return new PdfDocumentKt(PdfiumCoreKt.this.coreInternal.newDocument(this.$data, this.$password), PdfiumCoreKt.this.dispatcher);
        }
    }

    public final Object newDocument(byte[] bArr, String str, Continuation<? super PdfDocumentKt> continuation) {
        return BuildersKt.withContext(this.dispatcher, new AnonymousClass8(bArr, str, null), continuation);
    }
}

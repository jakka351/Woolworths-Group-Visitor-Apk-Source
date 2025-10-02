package retrofit2;

import androidx.exifinterface.media.ExifInterface;
import kotlin.KotlinNullPointerException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: KotlinExtensions.kt */
@Metadata(d1 = {"\u00002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\u0010\u0003\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086@¢\u0006\u0002\u0010\u0004\u001a(\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0003H\u0087@¢\u0006\u0004\b\u0005\u0010\u0004\u001a\u001a\u0010\u0000\u001a\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00060\u0003H\u0087@¢\u0006\u0004\b\u0007\u0010\u0004\u001a$\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\t\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0086@¢\u0006\u0002\u0010\u0004\u001a\u001e\u0010\n\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u000bH\u0086\b¢\u0006\u0002\u0010\f\u001a\u0012\u0010\r\u001a\u00020\u000e*\u00020\u000fH\u0080@¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"await", ExifInterface.GPS_DIRECTION_TRUE, "", "Lretrofit2/Call;", "(Lretrofit2/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitNullable", "", "awaitUnit", "awaitResponse", "Lretrofit2/Response;", "create", "Lretrofit2/Retrofit;", "(Lretrofit2/Retrofit;)Ljava/lang/Object;", "suspendAndThrow", "", "", "(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "retrofit"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class KotlinExtensions {

    /* compiled from: KotlinExtensions.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "retrofit2.KotlinExtensions", f = "KotlinExtensions.kt", i = {0}, l = {119}, m = "suspendAndThrow", n = {"$this$suspendAndThrow"}, s = {"L$0"})
    /* renamed from: retrofit2.KotlinExtensions$suspendAndThrow$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return KotlinExtensions.suspendAndThrow(null, this);
        }
    }

    public static final /* synthetic */ <T> T create(Retrofit retrofit) {
        Intrinsics.checkNotNullParameter(retrofit, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        T t = (T) retrofit.create(Object.class);
        Intrinsics.checkNotNullExpressionValue(t, "create(...)");
        return t;
    }

    public static final Object awaitUnit(Call<Unit> call, Continuation<? super Unit> continuation) {
        Intrinsics.checkNotNull(call, "null cannot be cast to non-null type retrofit2.Call<kotlin.Unit?>");
        return awaitNullable(call, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object suspendAndThrow(final java.lang.Throwable r4, kotlin.coroutines.Continuation<?> r5) throws java.lang.Throwable {
        /*
            boolean r0 = r5 instanceof retrofit2.KotlinExtensions.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r5
            retrofit2.KotlinExtensions$suspendAndThrow$1 r0 = (retrofit2.KotlinExtensions.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L19
        L14:
            retrofit2.KotlinExtensions$suspendAndThrow$1 r0 = new retrofit2.KotlinExtensions$suspendAndThrow$1
            r0.<init>(r5)
        L19:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 == r3) goto L2e
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L2e:
            java.lang.Object r4 = r0.L$0
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            kotlin.ResultKt.throwOnFailure(r5)
            goto L61
        L36:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.L$0 = r4
            r0.label = r3
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getDefault()
            kotlin.coroutines.CoroutineContext r2 = r0.get$context()
            retrofit2.KotlinExtensions$suspendAndThrow$2$1 r3 = new retrofit2.KotlinExtensions$suspendAndThrow$2$1
            r3.<init>()
            java.lang.Runnable r3 = (java.lang.Runnable) r3
            r5.mo2669dispatch(r2, r3)
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r4 != r5) goto L5e
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L5e:
            if (r4 != r1) goto L61
            return r1
        L61:
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: retrofit2.KotlinExtensions.suspendAndThrow(java.lang.Throwable, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final <T> Object await(final Call<T> call, Continuation<? super T> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: retrofit2.KotlinExtensions$await$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                call.cancel();
            }
        });
        call.enqueue(new Callback<T>() { // from class: retrofit2.KotlinExtensions$await$2$2
            @Override // retrofit2.Callback
            public void onResponse(Call<T> call2, Response<T> response) {
                Intrinsics.checkNotNullParameter(call2, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    T tBody = response.body();
                    if (tBody == null) {
                        Object objTag = call2.request().tag(Invocation.class);
                        Intrinsics.checkNotNull(objTag);
                        Invocation invocation = (Invocation) objTag;
                        KotlinNullPointerException kotlinNullPointerException = new KotlinNullPointerException("Response from " + invocation.service().getName() + ClassUtils.PACKAGE_SEPARATOR_CHAR + invocation.method().getName() + " was null but response body type was declared as non-null");
                        CancellableContinuation<T> cancellableContinuation = cancellableContinuationImpl2;
                        Result.Companion companion = Result.INSTANCE;
                        cancellableContinuation.resumeWith(Result.m1110constructorimpl(ResultKt.createFailure(kotlinNullPointerException)));
                        return;
                    }
                    CancellableContinuation<T> cancellableContinuation2 = cancellableContinuationImpl2;
                    Result.Companion companion2 = Result.INSTANCE;
                    cancellableContinuation2.resumeWith(Result.m1110constructorimpl(tBody));
                    return;
                }
                CancellableContinuation<T> cancellableContinuation3 = cancellableContinuationImpl2;
                Result.Companion companion3 = Result.INSTANCE;
                cancellableContinuation3.resumeWith(Result.m1110constructorimpl(ResultKt.createFailure(new HttpException(response))));
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<T> call2, Throwable t) {
                Intrinsics.checkNotNullParameter(call2, "call");
                Intrinsics.checkNotNullParameter(t, "t");
                CancellableContinuation<T> cancellableContinuation = cancellableContinuationImpl2;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m1110constructorimpl(ResultKt.createFailure(t)));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public static final <T> Object awaitNullable(final Call<T> call, Continuation<? super T> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: retrofit2.KotlinExtensions$await$4$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                call.cancel();
            }
        });
        call.enqueue(new Callback<T>() { // from class: retrofit2.KotlinExtensions$await$4$2
            @Override // retrofit2.Callback
            public void onResponse(Call<T> call2, Response<T> response) {
                Intrinsics.checkNotNullParameter(call2, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    CancellableContinuation<T> cancellableContinuation = cancellableContinuationImpl2;
                    Result.Companion companion = Result.INSTANCE;
                    cancellableContinuation.resumeWith(Result.m1110constructorimpl(response.body()));
                } else {
                    CancellableContinuation<T> cancellableContinuation2 = cancellableContinuationImpl2;
                    Result.Companion companion2 = Result.INSTANCE;
                    cancellableContinuation2.resumeWith(Result.m1110constructorimpl(ResultKt.createFailure(new HttpException(response))));
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<T> call2, Throwable t) {
                Intrinsics.checkNotNullParameter(call2, "call");
                Intrinsics.checkNotNullParameter(t, "t");
                CancellableContinuation<T> cancellableContinuation = cancellableContinuationImpl2;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m1110constructorimpl(ResultKt.createFailure(t)));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public static final <T> Object awaitResponse(final Call<T> call, Continuation<? super Response<T>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: retrofit2.KotlinExtensions$awaitResponse$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                call.cancel();
            }
        });
        call.enqueue(new Callback<T>() { // from class: retrofit2.KotlinExtensions$awaitResponse$2$2
            @Override // retrofit2.Callback
            public void onResponse(Call<T> call2, Response<T> response) {
                Intrinsics.checkNotNullParameter(call2, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                CancellableContinuation<Response<T>> cancellableContinuation = cancellableContinuationImpl2;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m1110constructorimpl(response));
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<T> call2, Throwable t) {
                Intrinsics.checkNotNullParameter(call2, "call");
                Intrinsics.checkNotNullParameter(t, "t");
                CancellableContinuation<Response<T>> cancellableContinuation = cancellableContinuationImpl2;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuation.resumeWith(Result.m1110constructorimpl(ResultKt.createFailure(t)));
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}

package io.legere.pdfiumandroid;

import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Logger.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016J$\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u000b"}, d2 = {"Lio/legere/pdfiumandroid/DefaultLogger;", "Lio/legere/pdfiumandroid/LoggerInterface;", "()V", "d", "", "tag", "", "message", "e", "t", "", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DefaultLogger implements LoggerInterface {
    @Override // io.legere.pdfiumandroid.LoggerInterface
    public void d(String tag, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        if (message != null) {
            Log.d(tag, message);
        }
    }

    @Override // io.legere.pdfiumandroid.LoggerInterface
    public void e(String tag, Throwable t, String message) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Log.e(tag, message, t);
    }
}

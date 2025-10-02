package io.legere.pdfiumandroid;

import android.graphics.RectF;
import io.legere.pdfiumandroid.PdfDocument;
import io.legere.pdfiumandroid.util.ConfigKt;
import io.sentry.SentryEnvelopeItemHeader;
import io.sentry.protocol.MetricSummary;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PdfTextPage.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u0017\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u000b\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\f\n\u0002\b\u0002\u0018\u0000 F2\u00020\u0001:\u0001FB1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0005J\u0011\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0006\u001a\u00020\u0007H\u0082 J\u0019\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u0005H\u0082 J\u0011\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0007H\u0082 J!\u0010\u001f\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0005H\u0082 J9\u0010\"\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\u00192\u0006\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020(H\u0082 J\u0019\u0010)\u001a\u00020*2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0005H\u0082 J1\u0010,\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u00192\u0006\u0010/\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u0019H\u0082 J\u0019\u00101\u001a\u00020*2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u0005H\u0082 J)\u00103\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\u0006\u00104\u001a\u00020(H\u0082 J)\u00105\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u00052\u0006\u00104\u001a\u000206H\u0082 J\u0019\u00107\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010+\u001a\u00020\u0005H\u0082 J\u0006\u00108\u001a\u00020\u0005J\u0016\u00109\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0005J\u0018\u0010:\u001a\u0004\u0018\u00010;2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0005J\u0010\u0010?\u001a\u0004\u0018\u00010=2\u0006\u0010+\u001a\u00020\u0005J&\u0010@\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\u00192\u0006\u0010/\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u0019J\u0010\u0010A\u001a\u0004\u0018\u00010=2\u0006\u00102\u001a\u00020\u0005J\u0018\u0010B\u001a\u0004\u0018\u00010;2\u0006\u0010 \u001a\u00020\u00052\u0006\u0010>\u001a\u00020\u0005J\u0018\u0010C\u001a\u0004\u0018\u00010;2\u0006\u0010 \u001a\u00020\u00052\u0006\u0010>\u001a\u00020\u0005J\u000e\u0010D\u001a\u00020E2\u0006\u0010+\u001a\u00020\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006G"}, d2 = {"Lio/legere/pdfiumandroid/PdfTextPage;", "Ljava/io/Closeable;", "doc", "Lio/legere/pdfiumandroid/PdfDocument;", "pageIndex", "", "pagePtr", "", "pageMap", "", "Lio/legere/pdfiumandroid/PdfDocument$PageCount;", "(Lio/legere/pdfiumandroid/PdfDocument;IJLjava/util/Map;)V", "getDoc", "()Lio/legere/pdfiumandroid/PdfDocument;", "isClosed", "", "getPageIndex", "()I", "getPageMap", "()Ljava/util/Map;", "getPagePtr", "()J", "close", "", "getFontSize", "", "charIndex", "nativeCloseTextPage", "nativeGetFontSize", "nativeTextCountChars", "textPagePtr", "nativeTextCountRects", "startIndex", MetricSummary.JsonKeys.COUNT, "nativeTextGetBoundedText", "left", "top", "right", "bottom", "arr", "", "nativeTextGetCharBox", "", "index", "nativeTextGetCharIndexAtPos", "x", "y", "xTolerance", "yTolerance", "nativeTextGetRect", "rectIndex", "nativeTextGetText", "result", "nativeTextGetTextByteArray", "", "nativeTextGetUnicode", "textPageCountChars", "textPageCountRects", "textPageGetBoundedText", "", "rect", "Landroid/graphics/RectF;", SentryEnvelopeItemHeader.JsonKeys.LENGTH, "textPageGetCharBox", "textPageGetCharIndexAtPos", "textPageGetRect", "textPageGetText", "textPageGetTextLegacy", "textPageGetUnicode", "", "Companion", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PdfTextPage implements Closeable {
    private static final String TAG = PdfTextPage.class.getName();
    private final PdfDocument doc;
    private boolean isClosed;
    private final int pageIndex;
    private final Map<Integer, PdfDocument.PageCount> pageMap;
    private final long pagePtr;

    private final native void nativeCloseTextPage(long pagePtr);

    private final native double nativeGetFontSize(long pagePtr, int charIndex);

    private final native int nativeTextCountChars(long textPagePtr);

    private final native int nativeTextCountRects(long textPagePtr, int startIndex, int count);

    private final native int nativeTextGetBoundedText(long textPagePtr, double left, double top, double right, double bottom, short[] arr);

    private final native double[] nativeTextGetCharBox(long textPagePtr, int index);

    private final native int nativeTextGetCharIndexAtPos(long textPagePtr, double x, double y, double xTolerance, double yTolerance);

    private final native double[] nativeTextGetRect(long textPagePtr, int rectIndex);

    private final native int nativeTextGetText(long textPagePtr, int startIndex, int count, short[] result);

    private final native int nativeTextGetTextByteArray(long textPagePtr, int startIndex, int count, byte[] result);

    private final native int nativeTextGetUnicode(long textPagePtr, int index);

    public PdfTextPage(PdfDocument doc, int i, long j, Map<Integer, PdfDocument.PageCount> pageMap) {
        Intrinsics.checkNotNullParameter(doc, "doc");
        Intrinsics.checkNotNullParameter(pageMap, "pageMap");
        this.doc = doc;
        this.pageIndex = i;
        this.pagePtr = j;
        this.pageMap = pageMap;
    }

    public final PdfDocument getDoc() {
        return this.doc;
    }

    public final int getPageIndex() {
        return this.pageIndex;
    }

    public final long getPagePtr() {
        return this.pagePtr;
    }

    public final Map<Integer, PdfDocument.PageCount> getPageMap() {
        return this.pageMap;
    }

    public final int textPageCountChars() {
        int iNativeTextCountChars;
        if (ConfigKt.handleAlreadyClosed(this.isClosed || this.doc.getIsClosed())) {
            return -1;
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            iNativeTextCountChars = nativeTextCountChars(this.pagePtr);
        }
        return iNativeTextCountChars;
    }

    public final String textPageGetTextLegacy(int startIndex, int length) {
        if (ConfigKt.handleAlreadyClosed(this.isClosed || this.doc.getIsClosed())) {
            return null;
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            try {
                short[] sArr = new short[length + 1];
                int iNativeTextGetText = nativeTextGetText(this.pagePtr, startIndex, length, sArr);
                if (iNativeTextGetText <= 0) {
                    return "";
                }
                int i = iNativeTextGetText - 1;
                byte[] bArr = new byte[i * 2];
                ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
                byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
                for (int i2 = 0; i2 < i; i2++) {
                    byteBufferWrap.putShort(sArr[i2]);
                }
                Charset UTF_16LE = StandardCharsets.UTF_16LE;
                Intrinsics.checkNotNullExpressionValue(UTF_16LE, "UTF_16LE");
                return new String(bArr, UTF_16LE);
            } catch (NullPointerException e) {
                Logger logger = Logger.INSTANCE;
                String TAG2 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                logger.e(TAG2, e, "mContext may be null");
                return null;
            } catch (Exception e2) {
                Logger logger2 = Logger.INSTANCE;
                String TAG3 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                logger2.e(TAG3, e2, "Exception throw from native");
                return null;
            }
        }
    }

    public final String textPageGetText(int startIndex, int length) {
        if (ConfigKt.handleAlreadyClosed(this.isClosed || this.doc.getIsClosed())) {
            return null;
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            try {
                try {
                    byte[] bArr = new byte[length * 2];
                    if (nativeTextGetTextByteArray(this.pagePtr, startIndex, length, bArr) <= 0) {
                        return "";
                    }
                    Charset UTF_16LE = StandardCharsets.UTF_16LE;
                    Intrinsics.checkNotNullExpressionValue(UTF_16LE, "UTF_16LE");
                    return new String(bArr, UTF_16LE);
                } catch (Exception e) {
                    Logger logger = Logger.INSTANCE;
                    String TAG2 = TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    logger.e(TAG2, e, "Exception throw from native");
                    return null;
                }
            } catch (NullPointerException e2) {
                Logger logger2 = Logger.INSTANCE;
                String TAG3 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                logger2.e(TAG3, e2, "mContext may be null");
                return null;
            }
        }
    }

    public final char textPageGetUnicode(int index) {
        char cNativeTextGetUnicode;
        if (!((this.isClosed || this.doc.getIsClosed()) ? false : true)) {
            throw new IllegalStateException("Already closed".toString());
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            cNativeTextGetUnicode = (char) nativeTextGetUnicode(this.pagePtr, index);
        }
        return cNativeTextGetUnicode;
    }

    public final RectF textPageGetCharBox(int index) {
        if (ConfigKt.handleAlreadyClosed(this.isClosed || this.doc.getIsClosed())) {
            return null;
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            try {
                try {
                    double[] dArrNativeTextGetCharBox = nativeTextGetCharBox(this.pagePtr, index);
                    RectF rectF = new RectF();
                    rectF.left = (float) dArrNativeTextGetCharBox[0];
                    rectF.right = (float) dArrNativeTextGetCharBox[1];
                    rectF.bottom = (float) dArrNativeTextGetCharBox[2];
                    rectF.top = (float) dArrNativeTextGetCharBox[3];
                    return rectF;
                } catch (NullPointerException e) {
                    Logger logger = Logger.INSTANCE;
                    String TAG2 = TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    logger.e(TAG2, e, "mContext may be null");
                    Unit unit = Unit.INSTANCE;
                    return null;
                }
            } catch (Exception e2) {
                Logger logger2 = Logger.INSTANCE;
                String TAG3 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                logger2.e(TAG3, e2, "Exception throw from native");
                Unit unit2 = Unit.INSTANCE;
                return null;
            }
        }
    }

    public final int textPageGetCharIndexAtPos(double x, double y, double xTolerance, double yTolerance) {
        int iNativeTextGetCharIndexAtPos;
        if (ConfigKt.handleAlreadyClosed(this.isClosed || this.doc.getIsClosed())) {
            return -1;
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            try {
                iNativeTextGetCharIndexAtPos = nativeTextGetCharIndexAtPos(this.pagePtr, x, y, xTolerance, yTolerance);
            } catch (Exception e) {
                Logger logger = Logger.INSTANCE;
                String TAG2 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                logger.e(TAG2, e, "Exception throw from native");
                Unit unit = Unit.INSTANCE;
                return -1;
            }
        }
        return iNativeTextGetCharIndexAtPos;
    }

    public final int textPageCountRects(int startIndex, int count) {
        if (!((this.isClosed || this.doc.getIsClosed()) ? false : true)) {
            throw new IllegalStateException("Already closed".toString());
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            try {
                try {
                    return nativeTextCountRects(this.pagePtr, startIndex, count);
                } catch (Exception e) {
                    Logger logger = Logger.INSTANCE;
                    String TAG2 = TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    logger.e(TAG2, e, "Exception throw from native");
                    Unit unit = Unit.INSTANCE;
                    return -1;
                }
            } catch (NullPointerException e2) {
                Logger logger2 = Logger.INSTANCE;
                String TAG3 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                logger2.e(TAG3, e2, "mContext may be null");
                Unit unit2 = Unit.INSTANCE;
                return -1;
            }
        }
    }

    public final RectF textPageGetRect(int rectIndex) {
        RectF rectF = null;
        if (ConfigKt.handleAlreadyClosed(this.isClosed || this.doc.getIsClosed())) {
            return null;
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            try {
                try {
                    double[] dArrNativeTextGetRect = nativeTextGetRect(this.pagePtr, rectIndex);
                    RectF rectF2 = new RectF();
                    rectF2.left = (float) dArrNativeTextGetRect[0];
                    rectF2.top = (float) dArrNativeTextGetRect[1];
                    rectF2.right = (float) dArrNativeTextGetRect[2];
                    rectF2.bottom = (float) dArrNativeTextGetRect[3];
                    rectF = rectF2;
                } catch (NullPointerException e) {
                    Logger logger = Logger.INSTANCE;
                    String TAG2 = TAG;
                    Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                    logger.e(TAG2, e, "mContext may be null");
                }
            } catch (Exception e2) {
                Logger logger2 = Logger.INSTANCE;
                String TAG3 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                logger2.e(TAG3, e2, "Exception throw from native");
            }
        }
        return rectF;
    }

    public final String textPageGetBoundedText(RectF rect, int length) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        String str = null;
        if (ConfigKt.handleAlreadyClosed(this.isClosed || this.doc.getIsClosed())) {
            return null;
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            try {
                short[] sArr = new short[length + 1];
                int iNativeTextGetBoundedText = nativeTextGetBoundedText(this.pagePtr, rect.left, rect.top, rect.right, rect.bottom, sArr) - 1;
                byte[] bArr = new byte[iNativeTextGetBoundedText * 2];
                ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
                byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
                for (int i = 0; i < iNativeTextGetBoundedText; i++) {
                    byteBufferWrap.putShort(sArr[i]);
                }
                Charset UTF_16LE = StandardCharsets.UTF_16LE;
                Intrinsics.checkNotNullExpressionValue(UTF_16LE, "UTF_16LE");
                str = new String(bArr, UTF_16LE);
            } catch (NullPointerException e) {
                Logger logger = Logger.INSTANCE;
                String TAG2 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
                logger.e(TAG2, e, "mContext may be null");
            } catch (Exception e2) {
                Logger logger2 = Logger.INSTANCE;
                String TAG3 = TAG;
                Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
                logger2.e(TAG3, e2, "Exception throw from native");
            }
        }
        return str;
    }

    public final double getFontSize(int charIndex) {
        double dNativeGetFontSize;
        if (ConfigKt.handleAlreadyClosed(this.isClosed || this.doc.getIsClosed())) {
            return 0.0d;
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            dNativeGetFontSize = nativeGetFontSize(this.pagePtr, charIndex);
        }
        return dNativeGetFontSize;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (ConfigKt.handleAlreadyClosed(this.isClosed || this.doc.getIsClosed())) {
            return;
        }
        synchronized (PdfiumCore.INSTANCE.getLock()) {
            PdfDocument.PageCount pageCount = this.pageMap.get(Integer.valueOf(this.pageIndex));
            if (pageCount != null && pageCount.getCount() > 1) {
                pageCount.setCount(pageCount.getCount() - 1);
                return;
            }
            this.pageMap.remove(Integer.valueOf(this.pageIndex));
            this.isClosed = true;
            nativeCloseTextPage(this.pagePtr);
            Unit unit = Unit.INSTANCE;
        }
    }
}

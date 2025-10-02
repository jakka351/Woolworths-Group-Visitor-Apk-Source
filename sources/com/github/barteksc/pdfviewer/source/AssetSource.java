package com.github.barteksc.pdfviewer.source;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import com.github.barteksc.pdfviewer.util.FileUtils;
import io.legere.pdfiumandroid.PdfDocument;
import io.legere.pdfiumandroid.PdfiumCore;
import java.io.IOException;

/* loaded from: classes3.dex */
public class AssetSource implements DocumentSource {
    private final String assetName;

    public AssetSource(String str) {
        this.assetName = str;
    }

    @Override // com.github.barteksc.pdfviewer.source.DocumentSource
    public PdfDocument createDocument(Context context, PdfiumCore pdfiumCore, String str) throws IOException {
        return pdfiumCore.newDocument(ParcelFileDescriptor.open(FileUtils.fileFromAsset(context, this.assetName), 268435456), str);
    }
}

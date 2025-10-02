package com.github.barteksc.pdfviewer.util;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/* loaded from: classes3.dex */
public class FileUtils {
    private FileUtils() {
    }

    public static File fileFromAsset(Context context, String str) throws Throwable {
        File file = new File(context.getCacheDir(), str + "-pdfview.pdf");
        if (str.contains("/")) {
            file.getParentFile().mkdirs();
        }
        copy(context.getAssets().open(str), file);
        return file;
    }

    public static void copy(InputStream inputStream, File file) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                } else {
                    fileOutputStream.write(bArr, 0, i);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } finally {
                    fileOutputStream.close();
                }
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } finally {
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                }
            }
            throw th;
        }
    }
}

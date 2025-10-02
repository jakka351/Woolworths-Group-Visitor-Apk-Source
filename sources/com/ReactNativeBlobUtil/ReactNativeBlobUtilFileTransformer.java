package com.ReactNativeBlobUtil;

/* loaded from: classes.dex */
public class ReactNativeBlobUtilFileTransformer {
    public static FileTransformer sharedFileTransformer;

    public interface FileTransformer {
        byte[] onReadFile(byte[] bArr);

        byte[] onWriteFile(byte[] bArr);
    }
}

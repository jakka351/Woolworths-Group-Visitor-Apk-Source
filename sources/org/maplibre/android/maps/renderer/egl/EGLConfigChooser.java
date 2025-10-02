package org.maplibre.android.maps.renderer.egl;

import android.opengl.GLSurfaceView;
import android.os.Build;
import java.util.ArrayList;
import java.util.Collections;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;
import org.maplibre.android.constants.MapLibreConstants;
import org.maplibre.android.log.Logger;
import org.maplibre.android.utils.Compare;

/* loaded from: classes2.dex */
public class EGLConfigChooser implements GLSurfaceView.EGLConfigChooser {
    private static final int EGL_OPENGL_ES3_BIT = 64;
    private static final String TAG = "Mbgl-EGLConfigChooser";
    private boolean translucentSurface;

    public EGLConfigChooser() {
        this(false);
    }

    public EGLConfigChooser(boolean z) {
        this.translucentSurface = z;
    }

    @Override // android.opengl.GLSurfaceView.EGLConfigChooser
    public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
        int[] configAttributes = getConfigAttributes();
        int[] numberOfConfigurations = getNumberOfConfigurations(egl10, eGLDisplay, configAttributes);
        if (numberOfConfigurations[0] < 1) {
            Logger.e(TAG, "eglChooseConfig() returned no configs.");
        }
        EGLConfig eGLConfigChooseBestMatchConfig = chooseBestMatchConfig(egl10, eGLDisplay, getPossibleConfigurations(egl10, eGLDisplay, configAttributes, numberOfConfigurations));
        if (eGLConfigChooseBestMatchConfig == null) {
            Logger.e(TAG, "No config chosen");
        }
        return eGLConfigChooseBestMatchConfig;
    }

    private int[] getNumberOfConfigurations(EGL10 egl10, EGLDisplay eGLDisplay, int[] iArr) {
        int[] iArr2 = new int[1];
        if (!egl10.eglChooseConfig(eGLDisplay, iArr, null, 0, iArr2)) {
            Logger.e(TAG, String.format(MapLibreConstants.MAPLIBRE_LOCALE, "eglChooseConfig(NULL) returned error %d", Integer.valueOf(egl10.eglGetError())));
        }
        return iArr2;
    }

    private EGLConfig[] getPossibleConfigurations(EGL10 egl10, EGLDisplay eGLDisplay, int[] iArr, int[] iArr2) {
        int i = iArr2[0];
        EGLConfig[] eGLConfigArr = new EGLConfig[i];
        if (!egl10.eglChooseConfig(eGLDisplay, iArr, eGLConfigArr, i, iArr2)) {
            Logger.e(TAG, String.format(MapLibreConstants.MAPLIBRE_LOCALE, "eglChooseConfig() returned error %d", Integer.valueOf(egl10.eglGetError())));
        }
        return eGLConfigArr;
    }

    enum BufferFormat {
        Format16Bit(3),
        Format32BitNoAlpha(1),
        Format32BitAlpha(2),
        Format24Bit(0),
        Unknown(4);

        int value;

        BufferFormat(int i) {
            this.value = i;
        }
    }

    enum DepthStencilFormat {
        Format16Depth8Stencil(1),
        Format24Depth8Stencil(0);

        int value;

        DepthStencilFormat(int i) {
            this.value = i;
        }
    }

    private EGLConfig chooseBestMatchConfig(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
        int i;
        int i2;
        BufferFormat bufferFormat;
        DepthStencilFormat depthStencilFormat;
        EGLConfigChooser eGLConfigChooser = this;
        EGL10 egl102 = egl10;
        EGLConfig[] eGLConfigArr2 = eGLConfigArr;
        ArrayList arrayList = new ArrayList();
        int length = eGLConfigArr2.length;
        int i3 = 0;
        int i4 = 0;
        while (i4 < length) {
            EGLConfig eGLConfig = eGLConfigArr2[i4];
            if (eGLConfig == null) {
                i = length;
                i2 = i4;
            } else {
                int i5 = i3 + 1;
                int configAttr = eGLConfigChooser.getConfigAttr(egl102, eGLDisplay, eGLConfig, 12327);
                int configAttr2 = eGLConfigChooser.getConfigAttr(egl102, eGLDisplay, eGLConfig, 12320);
                int configAttr3 = eGLConfigChooser.getConfigAttr(egl102, eGLDisplay, eGLConfig, 12324);
                int configAttr4 = eGLConfigChooser.getConfigAttr(egl102, eGLDisplay, eGLConfig, 12323);
                int configAttr5 = eGLConfigChooser.getConfigAttr(egl102, eGLDisplay, eGLConfig, 12322);
                int configAttr6 = eGLConfigChooser.getConfigAttr(egl102, eGLDisplay, eGLConfig, 12321);
                eGLConfigChooser.getConfigAttr(egl102, eGLDisplay, eGLConfig, 12350);
                int configAttr7 = eGLConfigChooser.getConfigAttr(egl102, eGLDisplay, eGLConfig, 12325);
                int configAttr8 = eGLConfigChooser.getConfigAttr(egl102, eGLDisplay, eGLConfig, 12326);
                i = length;
                i2 = i4;
                if ((configAttr7 == 24 || configAttr7 == 16) & (configAttr8 == 8) & (eGLConfigChooser.getConfigAttr(egl102, eGLDisplay, eGLConfig, 12338) == 0) & (eGLConfigChooser.getConfigAttr(egl102, eGLDisplay, eGLConfig, 12337) == 0)) {
                    if (configAttr2 == 16 && configAttr3 == 5 && configAttr4 == 6 && configAttr5 == 5 && configAttr6 == 0) {
                        bufferFormat = BufferFormat.Format16Bit;
                    } else if (configAttr2 == 32 && configAttr3 == 8 && configAttr4 == 8 && configAttr5 == 8 && configAttr6 == 0) {
                        bufferFormat = BufferFormat.Format32BitNoAlpha;
                    } else if (configAttr2 == 32 && configAttr3 == 8 && configAttr4 == 8 && configAttr5 == 8 && configAttr6 == 8) {
                        bufferFormat = BufferFormat.Format32BitAlpha;
                    } else if (configAttr2 == 24 && configAttr3 == 8 && configAttr4 == 8 && configAttr5 == 8 && configAttr6 == 0) {
                        bufferFormat = BufferFormat.Format24Bit;
                    } else {
                        bufferFormat = BufferFormat.Unknown;
                    }
                    BufferFormat bufferFormat2 = bufferFormat;
                    if (configAttr7 == 16 && configAttr8 == 8) {
                        depthStencilFormat = DepthStencilFormat.Format16Depth8Stencil;
                    } else {
                        depthStencilFormat = DepthStencilFormat.Format24Depth8Stencil;
                    }
                    DepthStencilFormat depthStencilFormat2 = depthStencilFormat;
                    boolean z = configAttr != 12344;
                    if (bufferFormat2 != BufferFormat.Unknown) {
                        arrayList.add(new Comparable<C1Config>(bufferFormat2, depthStencilFormat2, z, i5, eGLConfig) { // from class: org.maplibre.android.maps.renderer.egl.EGLConfigChooser.1Config
                            private final BufferFormat bufferFormat;
                            private final EGLConfig config;
                            private final DepthStencilFormat depthStencilFormat;
                            private final int index;
                            private final boolean isCaveat;

                            {
                                this.bufferFormat = bufferFormat2;
                                this.depthStencilFormat = depthStencilFormat2;
                                this.isCaveat = z;
                                this.index = i5;
                                this.config = eGLConfig;
                            }

                            @Override // java.lang.Comparable
                            public int compareTo(C1Config c1Config) {
                                int iCompare = Compare.compare(this.bufferFormat.value, c1Config.bufferFormat.value);
                                if (iCompare != 0) {
                                    return iCompare;
                                }
                                int iCompare2 = Compare.compare(this.depthStencilFormat.value, c1Config.depthStencilFormat.value);
                                if (iCompare2 != 0) {
                                    return iCompare2;
                                }
                                int iCompare3 = Compare.compare(this.isCaveat, c1Config.isCaveat);
                                if (iCompare3 != 0) {
                                    return iCompare3;
                                }
                                int iCompare4 = Compare.compare(this.index, c1Config.index);
                                if (iCompare4 != 0) {
                                    return iCompare4;
                                }
                                return 0;
                            }
                        });
                    }
                }
                i3 = i5;
            }
            i4 = i2 + 1;
            eGLConfigChooser = this;
            egl102 = egl10;
            eGLConfigArr2 = eGLConfigArr;
            length = i;
        }
        Collections.sort(arrayList);
        if (arrayList.size() == 0) {
            Logger.e(TAG, "No matching configurations after filtering");
            return null;
        }
        C1Config c1Config = (C1Config) arrayList.get(0);
        if (c1Config.isCaveat) {
            Logger.w(TAG, "Chosen config has a caveat.");
        }
        return c1Config.config;
    }

    private int getConfigAttr(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i) {
        int[] iArr = new int[1];
        if (!egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, iArr)) {
            Logger.e(TAG, String.format(MapLibreConstants.MAPLIBRE_LOCALE, "eglGetConfigAttrib(%d) returned error %d", Integer.valueOf(i), Integer.valueOf(egl10.eglGetError())));
        }
        return iArr[0];
    }

    private int[] getConfigAttributes() {
        boolean z = inEmulator() || inGenymotion();
        Logger.i(TAG, String.format("In emulator: %s", Boolean.valueOf(z)));
        int[] iArr = new int[23];
        iArr[0] = 12327;
        iArr[1] = 12344;
        iArr[2] = 12339;
        iArr[3] = 4;
        iArr[4] = 12320;
        iArr[5] = 16;
        iArr[6] = 12324;
        iArr[7] = 5;
        iArr[8] = 12323;
        iArr[9] = 6;
        iArr[10] = 12322;
        iArr[11] = 5;
        iArr[12] = 12321;
        iArr[13] = this.translucentSurface ? 8 : 0;
        iArr[14] = 12325;
        iArr[15] = 16;
        iArr[16] = 12326;
        iArr[17] = 8;
        iArr[18] = z ? 12344 : 12351;
        iArr[19] = 12430;
        iArr[20] = 12352;
        iArr[21] = 64;
        iArr[22] = 12344;
        return iArr;
    }

    private boolean inEmulator() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT) || System.getProperty("ro.kernel.qemu") != null;
    }

    private boolean inGenymotion() {
        return Build.MANUFACTURER.contains("Genymotion");
    }
}

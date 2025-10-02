package com.google.mlkit.vision.codescanner;

import com.google.android.gms.common.internal.Objects;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
public class GmsBarcodeScannerOptions {
    private final int zza;
    private final boolean zzb;
    private final boolean zzc;

    /* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
    public static class Builder {
        private int zza = 0;
        private boolean zzb;
        private boolean zzc;

        public Builder allowManualInput() {
            this.zzb = true;
            return this;
        }

        public GmsBarcodeScannerOptions build() {
            return new GmsBarcodeScannerOptions(this.zza, this.zzb, this.zzc, null);
        }

        public Builder enableAutoZoom() {
            this.zzc = true;
            return this;
        }

        public Builder setBarcodeFormats(int i, int... iArr) {
            this.zza = i;
            for (int i2 : iArr) {
                this.zza = i2 | this.zza;
            }
            return this;
        }
    }

    /* synthetic */ GmsBarcodeScannerOptions(int i, boolean z, boolean z2, zza zzaVar) {
        this.zza = i;
        this.zzb = z;
        this.zzc = z2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GmsBarcodeScannerOptions)) {
            return false;
        }
        GmsBarcodeScannerOptions gmsBarcodeScannerOptions = (GmsBarcodeScannerOptions) obj;
        return this.zza == gmsBarcodeScannerOptions.zza && this.zzb == gmsBarcodeScannerOptions.zzb && this.zzc == gmsBarcodeScannerOptions.zzc;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), Boolean.valueOf(this.zzb), Boolean.valueOf(this.zzc));
    }

    public final int zza() {
        return this.zza;
    }

    public final boolean zzb() {
        return this.zzc;
    }

    public final boolean zzc() {
        return this.zzb;
    }
}

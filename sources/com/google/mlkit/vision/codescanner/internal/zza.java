package com.google.mlkit.vision.codescanner.internal;

import android.graphics.Point;
import android.graphics.Rect;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_code_scanner.zzon;
import com.google.android.gms.internal.mlkit_code_scanner.zzoo;
import com.google.android.gms.internal.mlkit_code_scanner.zzop;
import com.google.android.gms.internal.mlkit_code_scanner.zzoq;
import com.google.android.gms.internal.mlkit_code_scanner.zzor;
import com.google.android.gms.internal.mlkit_code_scanner.zzos;
import com.google.android.gms.internal.mlkit_code_scanner.zzot;
import com.google.android.gms.internal.mlkit_code_scanner.zzou;
import com.google.android.gms.internal.mlkit_code_scanner.zzov;
import com.google.android.gms.internal.mlkit_code_scanner.zzow;
import com.google.android.gms.internal.mlkit_code_scanner.zzox;
import com.google.android.gms.internal.mlkit_code_scanner.zzoy;
import com.google.android.gms.internal.mlkit_code_scanner.zzoz;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.barcode.common.internal.BarcodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-code-scanner@@16.1.0 */
/* loaded from: classes3.dex */
final class zza implements BarcodeSource {
    private final zzoz zza;

    public zza(zzoz zzozVar) {
        this.zza = zzozVar;
    }

    private static Barcode.CalendarDateTime zza(zzoo zzooVar) {
        if (zzooVar == null) {
            return null;
        }
        return new Barcode.CalendarDateTime(zzooVar.zzf(), zzooVar.zzd(), zzooVar.zza(), zzooVar.zzb(), zzooVar.zzc(), zzooVar.zze(), zzooVar.zzh(), zzooVar.zzg());
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Rect getBoundingBox() {
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.CalendarEvent getCalendarEvent() {
        zzop zzopVarZzc = this.zza.zzc();
        if (zzopVarZzc != null) {
            return new Barcode.CalendarEvent(zzopVarZzc.zzg(), zzopVarZzc.zzc(), zzopVarZzc.zzd(), zzopVarZzc.zze(), zzopVarZzc.zzf(), zza(zzopVarZzc.zzb()), zza(zzopVarZzc.zza()));
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.ContactInfo getContactInfo() {
        zzoq zzoqVarZzd = this.zza.zzd();
        if (zzoqVarZzd == null) {
            return null;
        }
        zzou zzouVarZza = zzoqVarZzd.zza();
        Barcode.PersonName personName = zzouVarZza != null ? new Barcode.PersonName(zzouVarZza.zzb(), zzouVarZza.zzf(), zzouVarZza.zze(), zzouVarZza.zza(), zzouVarZza.zzd(), zzouVarZza.zzc(), zzouVarZza.zzg()) : null;
        String strZzb = zzoqVarZzd.zzb();
        String strZzc = zzoqVarZzd.zzc();
        zzov[] zzovVarArrZzf = zzoqVarZzd.zzf();
        ArrayList arrayList = new ArrayList();
        if (zzovVarArrZzf != null) {
            for (zzov zzovVar : zzovVarArrZzf) {
                if (zzovVar != null) {
                    arrayList.add(new Barcode.Phone(zzovVar.zzb(), zzovVar.zza()));
                }
            }
        }
        zzos[] zzosVarArrZze = zzoqVarZzd.zze();
        ArrayList arrayList2 = new ArrayList();
        if (zzosVarArrZze != null) {
            for (zzos zzosVar : zzosVarArrZze) {
                if (zzosVar != null) {
                    arrayList2.add(new Barcode.Email(zzosVar.zza(), zzosVar.zzb(), zzosVar.zzd(), zzosVar.zzc()));
                }
            }
        }
        List listAsList = zzoqVarZzd.zzg() != null ? Arrays.asList((String[]) Preconditions.checkNotNull(zzoqVarZzd.zzg())) : new ArrayList();
        zzon[] zzonVarArrZzd = zzoqVarZzd.zzd();
        ArrayList arrayList3 = new ArrayList();
        if (zzonVarArrZzd != null) {
            for (zzon zzonVar : zzonVarArrZzd) {
                if (zzonVar != null) {
                    arrayList3.add(new Barcode.Address(zzonVar.zza(), zzonVar.zzb()));
                }
            }
        }
        return new Barcode.ContactInfo(personName, strZzb, strZzc, arrayList, arrayList2, listAsList, arrayList3);
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Point[] getCornerPoints() {
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final String getDisplayValue() {
        return this.zza.zzl();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.DriverLicense getDriverLicense() {
        zzor zzorVarZze = this.zza.zze();
        if (zzorVarZze != null) {
            return new Barcode.DriverLicense(zzorVarZze.zzf(), zzorVarZze.zzh(), zzorVarZze.zzn(), zzorVarZze.zzl(), zzorVarZze.zzi(), zzorVarZze.zzc(), zzorVarZze.zza(), zzorVarZze.zzb(), zzorVarZze.zzd(), zzorVarZze.zzm(), zzorVarZze.zzj(), zzorVarZze.zzg(), zzorVarZze.zze(), zzorVarZze.zzk());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.Email getEmail() {
        zzos zzosVarZzf = this.zza.zzf();
        if (zzosVarZzf == null) {
            return null;
        }
        return new Barcode.Email(zzosVarZzf.zza(), zzosVarZzf.zzb(), zzosVarZzf.zzd(), zzosVarZzf.zzc());
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final int getFormat() {
        return this.zza.zza();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.GeoPoint getGeoPoint() {
        zzot zzotVarZzg = this.zza.zzg();
        if (zzotVarZzg != null) {
            return new Barcode.GeoPoint(zzotVarZzg.zza(), zzotVarZzg.zzb());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.Phone getPhone() {
        zzov zzovVarZzh = this.zza.zzh();
        if (zzovVarZzh != null) {
            return new Barcode.Phone(zzovVarZzh.zzb(), zzovVarZzh.zza());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final byte[] getRawBytes() {
        return this.zza.zzn();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final String getRawValue() {
        return this.zza.zzm();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.Sms getSms() {
        zzow zzowVarZzi = this.zza.zzi();
        if (zzowVarZzi != null) {
            return new Barcode.Sms(zzowVarZzi.zza(), zzowVarZzi.zzb());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.UrlBookmark getUrl() {
        zzox zzoxVarZzj = this.zza.zzj();
        if (zzoxVarZzj != null) {
            return new Barcode.UrlBookmark(zzoxVarZzj.zza(), zzoxVarZzj.zzb());
        }
        return null;
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final int getValueType() {
        return this.zza.zzb();
    }

    @Override // com.google.mlkit.vision.barcode.common.internal.BarcodeSource
    public final Barcode.WiFi getWifi() {
        zzoy zzoyVarZzk = this.zza.zzk();
        if (zzoyVarZzk != null) {
            return new Barcode.WiFi(zzoyVarZzk.zzc(), zzoyVarZzk.zzb(), zzoyVarZzk.zza());
        }
        return null;
    }
}

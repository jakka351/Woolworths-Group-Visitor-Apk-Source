package com.site360new;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationService.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0017\u0010\u001e\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\tHÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0014Jb\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020\f2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0003HÖ\u0001J\t\u0010%\u001a\u00020\u0006HÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u001f\u0010\b\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0018\u0010\u000fR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0019\u0010\u000f¨\u0006&"}, d2 = {"Lcom/site360new/Visit;", "", "visitId", "", "userId", "checkinTime", "", "autoCheckoutTime", "offsetGeometry", "", "", "doNotSystemCheckout", "", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Boolean;)V", "getAutoCheckoutTime", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCheckinTime", "()Ljava/lang/String;", "getDoNotSystemCheckout", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getOffsetGeometry", "()Ljava/util/List;", "getUserId", "getVisitId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/List;Ljava/lang/Boolean;)Lcom/site360new/Visit;", "equals", "other", "hashCode", "toString", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class Visit {
    private final Integer autoCheckoutTime;
    private final String checkinTime;
    private final Boolean doNotSystemCheckout;
    private final List<List<Double>> offsetGeometry;
    private final Integer userId;
    private final Integer visitId;

    public static /* synthetic */ Visit copy$default(Visit visit, Integer num, Integer num2, String str, Integer num3, List list, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            num = visit.visitId;
        }
        if ((i & 2) != 0) {
            num2 = visit.userId;
        }
        Integer num4 = num2;
        if ((i & 4) != 0) {
            str = visit.checkinTime;
        }
        String str2 = str;
        if ((i & 8) != 0) {
            num3 = visit.autoCheckoutTime;
        }
        Integer num5 = num3;
        if ((i & 16) != 0) {
            list = visit.offsetGeometry;
        }
        List list2 = list;
        if ((i & 32) != 0) {
            bool = visit.doNotSystemCheckout;
        }
        return visit.copy(num, num4, str2, num5, list2, bool);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getVisitId() {
        return this.visitId;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getUserId() {
        return this.userId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCheckinTime() {
        return this.checkinTime;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getAutoCheckoutTime() {
        return this.autoCheckoutTime;
    }

    public final List<List<Double>> component5() {
        return this.offsetGeometry;
    }

    /* renamed from: component6, reason: from getter */
    public final Boolean getDoNotSystemCheckout() {
        return this.doNotSystemCheckout;
    }

    public final Visit copy(Integer visitId, Integer userId, String checkinTime, Integer autoCheckoutTime, List<? extends List<Double>> offsetGeometry, Boolean doNotSystemCheckout) {
        return new Visit(visitId, userId, checkinTime, autoCheckoutTime, offsetGeometry, doNotSystemCheckout);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Visit)) {
            return false;
        }
        Visit visit = (Visit) other;
        return Intrinsics.areEqual(this.visitId, visit.visitId) && Intrinsics.areEqual(this.userId, visit.userId) && Intrinsics.areEqual(this.checkinTime, visit.checkinTime) && Intrinsics.areEqual(this.autoCheckoutTime, visit.autoCheckoutTime) && Intrinsics.areEqual(this.offsetGeometry, visit.offsetGeometry) && Intrinsics.areEqual(this.doNotSystemCheckout, visit.doNotSystemCheckout);
    }

    public int hashCode() {
        Integer num = this.visitId;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.userId;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str = this.checkinTime;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        Integer num3 = this.autoCheckoutTime;
        int iHashCode4 = (iHashCode3 + (num3 == null ? 0 : num3.hashCode())) * 31;
        List<List<Double>> list = this.offsetGeometry;
        int iHashCode5 = (iHashCode4 + (list == null ? 0 : list.hashCode())) * 31;
        Boolean bool = this.doNotSystemCheckout;
        return iHashCode5 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "Visit(visitId=" + this.visitId + ", userId=" + this.userId + ", checkinTime=" + this.checkinTime + ", autoCheckoutTime=" + this.autoCheckoutTime + ", offsetGeometry=" + this.offsetGeometry + ", doNotSystemCheckout=" + this.doNotSystemCheckout + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Visit(Integer num, Integer num2, String str, Integer num3, List<? extends List<Double>> list, Boolean bool) {
        this.visitId = num;
        this.userId = num2;
        this.checkinTime = str;
        this.autoCheckoutTime = num3;
        this.offsetGeometry = list;
        this.doNotSystemCheckout = bool;
    }

    public final Integer getVisitId() {
        return this.visitId;
    }

    public final Integer getUserId() {
        return this.userId;
    }

    public final String getCheckinTime() {
        return this.checkinTime;
    }

    public final Integer getAutoCheckoutTime() {
        return this.autoCheckoutTime;
    }

    public final List<List<Double>> getOffsetGeometry() {
        return this.offsetGeometry;
    }

    public final Boolean getDoNotSystemCheckout() {
        return this.doNotSystemCheckout;
    }
}

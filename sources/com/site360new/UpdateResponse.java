package com.site360new;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApiService.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0005J\u001a\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\tJ\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0002\u0010\u0005¨\u0006\u0010"}, d2 = {"Lcom/site360new/UpdateResponse;", "", "isCheckedIn", "", "(Ljava/lang/Boolean;)V", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "copy", "(Ljava/lang/Boolean;)Lcom/site360new/UpdateResponse;", "equals", "other", "hashCode", "", "toString", "", "app_wooliesRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes3.dex */
public final /* data */ class UpdateResponse {

    @SerializedName("conf_checked_in")
    private final Boolean isCheckedIn;

    public static /* synthetic */ UpdateResponse copy$default(UpdateResponse updateResponse, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = updateResponse.isCheckedIn;
        }
        return updateResponse.copy(bool);
    }

    /* renamed from: component1, reason: from getter */
    public final Boolean getIsCheckedIn() {
        return this.isCheckedIn;
    }

    public final UpdateResponse copy(Boolean isCheckedIn) {
        return new UpdateResponse(isCheckedIn);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof UpdateResponse) && Intrinsics.areEqual(this.isCheckedIn, ((UpdateResponse) other).isCheckedIn);
    }

    public int hashCode() {
        Boolean bool = this.isCheckedIn;
        if (bool == null) {
            return 0;
        }
        return bool.hashCode();
    }

    public String toString() {
        return "UpdateResponse(isCheckedIn=" + this.isCheckedIn + ")";
    }

    public UpdateResponse(Boolean bool) {
        this.isCheckedIn = bool;
    }

    public final Boolean isCheckedIn() {
        return this.isCheckedIn;
    }
}

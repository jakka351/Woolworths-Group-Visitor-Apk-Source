package org.maplibre.android.util;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class DefaultStyle implements Parcelable {
    public static final Parcelable.Creator<DefaultStyle> CREATOR = new Parcelable.Creator<DefaultStyle>() { // from class: org.maplibre.android.util.DefaultStyle.1
        @Override // android.os.Parcelable.Creator
        public DefaultStyle createFromParcel(Parcel parcel) {
            return new DefaultStyle(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public DefaultStyle[] newArray(int i) {
            return new DefaultStyle[i];
        }
    };
    private String name;
    private String url;
    private int version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DefaultStyle(String str, String str2, int i) {
        setUrl(str);
        setName(str2);
        setVersion(i);
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public int getVersion() {
        return this.version;
    }

    protected DefaultStyle(Parcel parcel) {
        setUrl(parcel.readString());
        setName(parcel.readString());
        setVersion(parcel.readInt());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeString(this.name);
        parcel.writeInt(this.version);
    }
}

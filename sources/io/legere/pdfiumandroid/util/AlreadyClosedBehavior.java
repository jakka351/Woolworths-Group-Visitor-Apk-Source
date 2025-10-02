package io.legere.pdfiumandroid.util;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: Config.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lio/legere/pdfiumandroid/util/AlreadyClosedBehavior;", "", "(Ljava/lang/String;I)V", "EXCEPTION", "IGNORE", "pdfiumandroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AlreadyClosedBehavior {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AlreadyClosedBehavior[] $VALUES;
    public static final AlreadyClosedBehavior EXCEPTION = new AlreadyClosedBehavior("EXCEPTION", 0);
    public static final AlreadyClosedBehavior IGNORE = new AlreadyClosedBehavior("IGNORE", 1);

    private static final /* synthetic */ AlreadyClosedBehavior[] $values() {
        return new AlreadyClosedBehavior[]{EXCEPTION, IGNORE};
    }

    public static EnumEntries<AlreadyClosedBehavior> getEntries() {
        return $ENTRIES;
    }

    public static AlreadyClosedBehavior valueOf(String str) {
        return (AlreadyClosedBehavior) Enum.valueOf(AlreadyClosedBehavior.class, str);
    }

    public static AlreadyClosedBehavior[] values() {
        return (AlreadyClosedBehavior[]) $VALUES.clone();
    }

    private AlreadyClosedBehavior(String str, int i) {
    }

    static {
        AlreadyClosedBehavior[] alreadyClosedBehaviorArr$values = $values();
        $VALUES = alreadyClosedBehaviorArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(alreadyClosedBehaviorArr$values);
    }
}

package app.notifee.core.model;

import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import n.o.t.i.f.e.e.l;
import org.apache.commons.lang3.time.DateUtils;

/* loaded from: classes.dex */
public class a {
    public Bundle a;
    public int b;
    public TimeUnit c;
    public Boolean d;
    public EnumC0009a e;
    public String f;
    public Long g;

    /* renamed from: app.notifee.core.model.a$a, reason: collision with other inner class name */
    public enum EnumC0009a {
        SET,
        SET_AND_ALLOW_WHILE_IDLE,
        SET_EXACT,
        SET_EXACT_AND_ALLOW_WHILE_IDLE,
        SET_ALARM_CLOCK
    }

    public a(Bundle bundle) {
        this.b = -1;
        this.c = null;
        this.d = Boolean.FALSE;
        EnumC0009a enumC0009a = EnumC0009a.SET_EXACT;
        this.e = enumC0009a;
        this.f = null;
        this.g = null;
        this.a = bundle;
        if (bundle.containsKey("repeatFrequency")) {
            int iA = l.a(this.a.get("repeatFrequency"));
            this.g = Long.valueOf(l.b(this.a.get("timestamp")));
            if (iA == 0) {
                this.b = 1;
                this.c = TimeUnit.HOURS;
                this.f = "HOURLY";
            } else if (iA == 1) {
                this.b = 1;
                this.c = TimeUnit.DAYS;
                this.f = "DAILY";
            } else if (iA == 2) {
                this.b = 7;
                this.c = TimeUnit.DAYS;
                this.f = "WEEKLY";
            }
        }
        if (!this.a.containsKey("alarmManager")) {
            if (this.a.containsKey("allowWhileIdle")) {
                this.d = Boolean.TRUE;
                this.e = EnumC0009a.SET_EXACT_AND_ALLOW_WHILE_IDLE;
                return;
            }
            return;
        }
        this.d = Boolean.TRUE;
        Bundle bundle2 = this.a.getBundle("alarmManager");
        Object obj = bundle2.get("type");
        int iA2 = obj != null ? l.a(obj) : 2;
        if (bundle2.containsKey("allowWhileIdle") && bundle2.getBoolean("allowWhileIdle")) {
            iA2 = 3;
        }
        if (iA2 == 0) {
            this.e = EnumC0009a.SET;
            return;
        }
        if (iA2 == 1) {
            this.e = EnumC0009a.SET_AND_ALLOW_WHILE_IDLE;
            return;
        }
        if (iA2 == 3) {
            this.e = EnumC0009a.SET_EXACT_AND_ALLOW_WHILE_IDLE;
        } else if (iA2 != 4) {
            this.e = enumC0009a;
        } else {
            this.e = EnumC0009a.SET_ALARM_CLOCK;
        }
    }

    public void a() {
        long j;
        if (this.f == null) {
            return;
        }
        long jLongValue = this.g.longValue();
        String str = this.f;
        str.getClass();
        str.hashCode();
        switch (str) {
            case "WEEKLY":
                j = 604800000;
                break;
            case "DAILY":
                j = DateUtils.MILLIS_PER_DAY;
                break;
            case "HOURLY":
                j = DateUtils.MILLIS_PER_HOUR;
                break;
            default:
                j = 0;
                break;
        }
        while (jLongValue < System.currentTimeMillis()) {
            jLongValue += j;
        }
        this.g = Long.valueOf(jLongValue);
    }
}

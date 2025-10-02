package kotlin.time;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: TimeSources.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0002¢\u0006\u0004\b\f\u0010\nJ\b\u0010\r\u001a\u00020\u0004H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlin/time/TestTimeSource;", "Lkotlin/time/AbstractLongTimeSource;", "()V", "reading", "", ViewProps.OVERFLOW, "", "duration", "Lkotlin/time/Duration;", "overflow-LRDsOJo", "(J)V", "plusAssign", "plusAssign-LRDsOJo", "read", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TestTimeSource extends AbstractLongTimeSource {
    private long reading;

    public TestTimeSource() {
        super(DurationUnit.NANOSECONDS);
        markNow();
    }

    @Override // kotlin.time.AbstractLongTimeSource
    /* renamed from: read, reason: from getter */
    protected long getReading() {
        return this.reading;
    }

    /* renamed from: plusAssign-LRDsOJo, reason: not valid java name */
    public final void m2572plusAssignLRDsOJo(long duration) {
        long jM2482toLongimpl = Duration.m2482toLongimpl(duration, getUnit());
        if (!(((jM2482toLongimpl - 1) | 1) == Long.MAX_VALUE)) {
            long j = this.reading;
            long j2 = j + jM2482toLongimpl;
            if ((jM2482toLongimpl ^ j) >= 0 && (j ^ j2) < 0) {
                m2571overflowLRDsOJo(duration);
            }
            this.reading = j2;
            return;
        }
        long jM2439divUwyO8pc = Duration.m2439divUwyO8pc(duration, 2);
        if (!((1 | (Duration.m2482toLongimpl(jM2439divUwyO8pc, getUnit()) - 1)) == Long.MAX_VALUE)) {
            long j3 = this.reading;
            try {
                m2572plusAssignLRDsOJo(jM2439divUwyO8pc);
                m2572plusAssignLRDsOJo(Duration.m2471minusLRDsOJo(duration, jM2439divUwyO8pc));
                return;
            } catch (IllegalStateException e) {
                this.reading = j3;
                throw e;
            }
        }
        m2571overflowLRDsOJo(duration);
    }

    /* renamed from: overflow-LRDsOJo, reason: not valid java name */
    private final void m2571overflowLRDsOJo(long duration) {
        throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.reading + DurationUnitKt.shortName(getUnit()) + " is advanced by " + ((Object) Duration.m2485toStringimpl(duration)) + ClassUtils.PACKAGE_SEPARATOR_CHAR);
    }
}

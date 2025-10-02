package retrofit2;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
final class DefaultMethodSupport {

    @Nullable
    private static Constructor<MethodHandles.Lookup> lookupConstructor;

    @Nullable
    static Object invoke(Method method, Class<?> cls, Object obj, @Nullable Object[] objArr) throws Throwable {
        Constructor<MethodHandles.Lookup> declaredConstructor = lookupConstructor;
        if (declaredConstructor == null) {
            declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
            declaredConstructor.setAccessible(true);
            lookupConstructor = declaredConstructor;
        }
        return declaredConstructor.newInstance(cls, -1).unreflectSpecial(method, cls).bindTo(obj).invokeWithArguments(objArr);
    }

    private DefaultMethodSupport() {
    }
}

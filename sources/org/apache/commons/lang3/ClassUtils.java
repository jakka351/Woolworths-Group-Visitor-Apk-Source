package org.apache.commons.lang3;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.exifinterface.media.ExifInterface;
import io.sentry.profilemeasurements.ProfileMeasurement;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;

/* loaded from: classes2.dex */
public class ClassUtils {
    public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
    private static final Map<String, String> abbreviationMap;
    private static final Map<Class<?>, Class<?>> primitiveWrapperMap;
    private static final Map<String, String> reverseAbbreviationMap;
    private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap;
    public static final char PACKAGE_SEPARATOR_CHAR = '.';
    public static final String PACKAGE_SEPARATOR = String.valueOf(PACKAGE_SEPARATOR_CHAR);
    public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');

    static {
        HashMap map = new HashMap();
        primitiveWrapperMap = map;
        map.put(Boolean.TYPE, Boolean.class);
        map.put(Byte.TYPE, Byte.class);
        map.put(Character.TYPE, Character.class);
        map.put(Short.TYPE, Short.class);
        map.put(Integer.TYPE, Integer.class);
        map.put(Long.TYPE, Long.class);
        map.put(Double.TYPE, Double.class);
        map.put(Float.TYPE, Float.class);
        map.put(Void.TYPE, Void.TYPE);
        wrapperPrimitiveMap = new HashMap();
        for (Class<?> cls : map.keySet()) {
            Class<?> cls2 = primitiveWrapperMap.get(cls);
            if (!cls.equals(cls2)) {
                wrapperPrimitiveMap.put(cls2, cls);
            }
        }
        abbreviationMap = new HashMap();
        reverseAbbreviationMap = new HashMap();
        addAbbreviation("int", "I");
        addAbbreviation(TypedValues.Custom.S_BOOLEAN, "Z");
        addAbbreviation(TypedValues.Custom.S_FLOAT, "F");
        addAbbreviation("long", "J");
        addAbbreviation("short", ExifInterface.LATITUDE_SOUTH);
        addAbbreviation(ProfileMeasurement.UNIT_BYTES, "B");
        addAbbreviation("double", "D");
        addAbbreviation("char", "C");
    }

    private static void addAbbreviation(String str, String str2) {
        abbreviationMap.put(str, str2);
        reverseAbbreviationMap.put(str2, str);
    }

    public static String getShortClassName(Object obj, String str) {
        return obj == null ? str : getShortClassName(obj.getClass());
    }

    public static String getShortClassName(Class<?> cls) {
        return cls == null ? "" : getShortClassName(cls.getName());
    }

    public static String getShortClassName(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (str.startsWith("[")) {
            while (str.charAt(0) == '[') {
                str = str.substring(1);
                sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            }
            if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
                str = str.substring(1, str.length() - 1);
            }
        }
        Map<String, String> map = reverseAbbreviationMap;
        if (map.containsKey(str)) {
            str = map.get(str);
        }
        int iLastIndexOf = str.lastIndexOf(46);
        int iIndexOf = str.indexOf(36, iLastIndexOf != -1 ? iLastIndexOf + 1 : 0);
        String strSubstring = str.substring(iLastIndexOf + 1);
        if (iIndexOf != -1) {
            strSubstring = strSubstring.replace('$', PACKAGE_SEPARATOR_CHAR);
        }
        return strSubstring + ((Object) sb);
    }

    public static String getSimpleName(Class<?> cls) {
        return cls == null ? "" : cls.getSimpleName();
    }

    public static String getSimpleName(Object obj, String str) {
        return obj == null ? str : getSimpleName(obj.getClass());
    }

    public static String getPackageName(Object obj, String str) {
        return obj == null ? str : getPackageName(obj.getClass());
    }

    public static String getPackageName(Class<?> cls) {
        return cls == null ? "" : getPackageName(cls.getName());
    }

    public static String getPackageName(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        while (str.charAt(0) == '[') {
            str = str.substring(1);
        }
        if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
            str = str.substring(1);
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf == -1 ? "" : str.substring(0, iLastIndexOf);
    }

    public static List<Class<?>> getAllSuperclasses(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            arrayList.add(superclass);
        }
        return arrayList;
    }

    public static List<Class<?>> getAllInterfaces(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        getAllInterfaces(cls, linkedHashSet);
        return new ArrayList(linkedHashSet);
    }

    private static void getAllInterfaces(Class<?> cls, HashSet<Class<?>> hashSet) {
        while (cls != null) {
            for (Class<?> cls2 : cls.getInterfaces()) {
                if (hashSet.add(cls2)) {
                    getAllInterfaces(cls2, hashSet);
                }
            }
            cls = cls.getSuperclass();
        }
    }

    public static List<Class<?>> convertClassNamesToClasses(List<String> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(Class.forName(it.next()));
            } catch (Exception unused) {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public static List<String> convertClassesToClassNames(List<Class<?>> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (Class<?> cls : list) {
            if (cls == null) {
                arrayList.add(null);
            } else {
                arrayList.add(cls.getName());
            }
        }
        return arrayList;
    }

    public static boolean isAssignable(Class<?>[] clsArr, Class<?>... clsArr2) {
        return isAssignable(clsArr, clsArr2, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
    }

    public static boolean isAssignable(Class<?>[] clsArr, Class<?>[] clsArr2, boolean z) {
        if (!ArrayUtils.isSameLength(clsArr, clsArr2)) {
            return false;
        }
        if (clsArr == null) {
            clsArr = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        if (clsArr2 == null) {
            clsArr2 = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        for (int i = 0; i < clsArr.length; i++) {
            if (!isAssignable(clsArr[i], clsArr2[i], z)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAssignable(Class<?> cls, Class<?> cls2) {
        return isAssignable(cls, cls2, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
    }

    public static boolean isAssignable(Class<?> cls, Class<?> cls2, boolean z) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (z) {
            if (cls.isPrimitive() && !cls2.isPrimitive() && (cls = primitiveToWrapper(cls)) == null) {
                return false;
            }
            if (cls2.isPrimitive() && !cls.isPrimitive() && (cls = wrapperToPrimitive(cls)) == null) {
                return false;
            }
        }
        if (cls.equals(cls2)) {
            return true;
        }
        if (cls.isPrimitive()) {
            if (!cls2.isPrimitive()) {
                return false;
            }
            if (Integer.TYPE.equals(cls)) {
                return Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            }
            if (Long.TYPE.equals(cls)) {
                return Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            }
            if (Boolean.TYPE.equals(cls) || Double.TYPE.equals(cls)) {
                return false;
            }
            if (Float.TYPE.equals(cls)) {
                return Double.TYPE.equals(cls2);
            }
            if (Character.TYPE.equals(cls)) {
                return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            }
            if (Short.TYPE.equals(cls)) {
                return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            }
            if (Byte.TYPE.equals(cls)) {
                return Short.TYPE.equals(cls2) || Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
            }
            return false;
        }
        return cls2.isAssignableFrom(cls);
    }

    public static Class<?> primitiveToWrapper(Class<?> cls) {
        return (cls == null || !cls.isPrimitive()) ? cls : primitiveWrapperMap.get(cls);
    }

    public static Class<?>[] primitivesToWrappers(Class<?>... clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class<?>[] clsArr2 = new Class[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            clsArr2[i] = primitiveToWrapper(clsArr[i]);
        }
        return clsArr2;
    }

    public static Class<?> wrapperToPrimitive(Class<?> cls) {
        return wrapperPrimitiveMap.get(cls);
    }

    public static Class<?>[] wrappersToPrimitives(Class<?>... clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class<?>[] clsArr2 = new Class[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            clsArr2[i] = wrapperToPrimitive(clsArr[i]);
        }
        return clsArr2;
    }

    public static boolean isInnerClass(Class<?> cls) {
        return (cls == null || cls.getEnclosingClass() == null) ? false : true;
    }

    public static Class<?> getClass(ClassLoader classLoader, String str, boolean z) throws ClassNotFoundException {
        try {
            Map<String, String> map = abbreviationMap;
            if (map.containsKey(str)) {
                return Class.forName("[" + map.get(str), z, classLoader).getComponentType();
            }
            return Class.forName(toCanonicalName(str), z, classLoader);
        } catch (ClassNotFoundException e) {
            int iLastIndexOf = str.lastIndexOf(46);
            if (iLastIndexOf != -1) {
                try {
                    return getClass(classLoader, str.substring(0, iLastIndexOf) + '$' + str.substring(iLastIndexOf + 1), z);
                } catch (ClassNotFoundException unused) {
                    throw e;
                }
            }
            throw e;
        }
    }

    public static Class<?> getClass(ClassLoader classLoader, String str) throws ClassNotFoundException {
        return getClass(classLoader, str, true);
    }

    public static Class<?> getClass(String str) throws ClassNotFoundException {
        return getClass(str, true);
    }

    public static Class<?> getClass(String str, boolean z) throws ClassNotFoundException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader == null) {
            contextClassLoader = ClassUtils.class.getClassLoader();
        }
        return getClass(contextClassLoader, str, z);
    }

    public static Method getPublicMethod(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException, SecurityException {
        Method method = cls.getMethod(str, clsArr);
        if (Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
            return method;
        }
        ArrayList<Class> arrayList = new ArrayList();
        arrayList.addAll(getAllInterfaces(cls));
        arrayList.addAll(getAllSuperclasses(cls));
        for (Class cls2 : arrayList) {
            if (Modifier.isPublic(cls2.getModifiers())) {
                try {
                    Method method2 = cls2.getMethod(str, clsArr);
                    if (Modifier.isPublic(method2.getDeclaringClass().getModifiers())) {
                        return method2;
                    }
                } catch (NoSuchMethodException unused) {
                    continue;
                }
            }
        }
        throw new NoSuchMethodException("Can't find a public method for " + str + " " + ArrayUtils.toString(clsArr));
    }

    private static String toCanonicalName(String str) {
        String strDeleteWhitespace = StringUtils.deleteWhitespace(str);
        if (strDeleteWhitespace == null) {
            throw new NullPointerException("className must not be null.");
        }
        if (!strDeleteWhitespace.endsWith(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)) {
            return strDeleteWhitespace;
        }
        StringBuilder sb = new StringBuilder();
        while (strDeleteWhitespace.endsWith(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)) {
            strDeleteWhitespace = strDeleteWhitespace.substring(0, strDeleteWhitespace.length() - 2);
            sb.append("[");
        }
        String str2 = abbreviationMap.get(strDeleteWhitespace);
        if (str2 != null) {
            sb.append(str2);
        } else {
            sb.append("L").append(strDeleteWhitespace).append(";");
        }
        return sb.toString();
    }

    public static Class<?>[] toClass(Object... objArr) {
        if (objArr == null) {
            return null;
        }
        if (objArr.length == 0) {
            return ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            clsArr[i] = obj == null ? null : obj.getClass();
        }
        return clsArr;
    }

    public static String getShortCanonicalName(Object obj, String str) {
        return obj == null ? str : getShortCanonicalName(obj.getClass().getName());
    }

    public static String getShortCanonicalName(Class<?> cls) {
        return cls == null ? "" : getShortCanonicalName(cls.getName());
    }

    public static String getShortCanonicalName(String str) {
        return getShortClassName(getCanonicalName(str));
    }

    public static String getPackageCanonicalName(Object obj, String str) {
        return obj == null ? str : getPackageCanonicalName(obj.getClass().getName());
    }

    public static String getPackageCanonicalName(Class<?> cls) {
        return cls == null ? "" : getPackageCanonicalName(cls.getName());
    }

    public static String getPackageCanonicalName(String str) {
        return getPackageName(getCanonicalName(str));
    }

    private static String getCanonicalName(String str) {
        String strDeleteWhitespace = StringUtils.deleteWhitespace(str);
        if (strDeleteWhitespace == null) {
            return null;
        }
        int i = 0;
        while (strDeleteWhitespace.startsWith("[")) {
            i++;
            strDeleteWhitespace = strDeleteWhitespace.substring(1);
        }
        if (i < 1) {
            return strDeleteWhitespace;
        }
        if (strDeleteWhitespace.startsWith("L")) {
            strDeleteWhitespace = strDeleteWhitespace.substring(1, strDeleteWhitespace.endsWith(";") ? strDeleteWhitespace.length() - 1 : strDeleteWhitespace.length());
        } else if (strDeleteWhitespace.length() > 0) {
            strDeleteWhitespace = reverseAbbreviationMap.get(strDeleteWhitespace.substring(0, 1));
        }
        StringBuilder sb = new StringBuilder(strDeleteWhitespace);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
        }
        return sb.toString();
    }
}

package net.paoding.rose.jade.statement;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ʵ�ֹ����࣬�����������͵Ĳ������͡�
 * 
 * @author han.liao
 * @author ��־�� [qieqie.wang@gmail.com]
 */
@SuppressWarnings({ "rawtypes" })
public class GenericUtils {

    private static final Class[] EMPTY_CLASSES = new Class[0];

    /**
     * ��������List<E>��Map<K,V>�е�E��K��V
     * 
     * @param invocationClass
     * @param targetType
     * 
     */
    public static Class[] resolveTypeParameters(Class invocationClass, Type targetType) {
        if (targetType instanceof ParameterizedType) {

            Type[] actualTypes = ((ParameterizedType) targetType).getActualTypeArguments();
            Class[] actualClasses = new Class[actualTypes.length];
            for (int i = 0; i < actualTypes.length; i++) {
                actualClasses[i] = resolveTypeVariable(invocationClass, actualTypes[i]);
            }
            return actualClasses;
        }

        return EMPTY_CLASSES;
    }

    /**
     * ��declaringClass���������ķ������ͱ�����invocationClass��������ֵ
     * 
     * @param invocationClass ���ʱʹ�õ���
     * @param declaringClass �������ͱ���typeVarName����
     * @param typeVarName ���ͱ�����
     * @return
     */
    public static final Class resolveTypeVariable(Class invocationClass, Class declaringClass,
                                                  String typeVarName) {
        TypeVariable typeVariable = null;
        for (TypeVariable typeParemeter : declaringClass.getTypeParameters()) {
            if (typeParemeter.getName().equals(typeVarName)) {
                typeVariable = typeParemeter;
                break;
            }
        }
        if (typeVariable == null) {
            throw new NullPointerException("not found TypeVariable name " + typeVarName);
        }
        return resolveTypeVariable(invocationClass, typeVariable);
    }

    /**
     * 
     * �������invocationClass���У�Ŀ������type��ֵ
     * 
     * ���type�������ͣ���ֱ�ӷ�������
     * ���type�����ͱ���( {@link TypeVariable})����������ֵ��
     * 
     * @param invocationClass
     * @param targetType
     * @return
     */
    public static final Class resolveTypeVariable(Class invocationClass, Type targetType) {
        if (targetType == null) {
            throw new NullPointerException("TypeVariable is null");
        }
        // Class����
        if (targetType instanceof Class) {
            return (Class) targetType;
        }
        // �����������ͣ�ע�⣺���ص����������ͣ����Ǿ���Ĳ������͡���Ҫ��������
        if (targetType instanceof ParameterizedType) {
            return resolveTypeVariable(invocationClass,
                (Type) ((ParameterizedType) targetType).getRawType());
        }
        // ��������
        if (targetType instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) targetType).getGenericComponentType();
            componentType = resolveTypeVariable(invocationClass, componentType);
            return Array.newInstance((Class) componentType, 0).getClass();
        }

        Map<TypeVariable, Type> refs = new HashMap<TypeVariable, Type>();

        // 
        List<Type> allSuperTypes = new LinkedList<Type>();
        allSuperTypes.addAll(Arrays.asList(invocationClass.getGenericInterfaces()));
        for (int i = 0; i < allSuperTypes.size(); i++) {
            Type type = allSuperTypes.get(i);
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = ((ParameterizedType) type);
                Class interfaceClass = (Class) parameterizedType.getRawType();
                int j = 0;
                for (Type actualTypeArgument : parameterizedType.getActualTypeArguments()) {
                    TypeVariable v = interfaceClass.getTypeParameters()[j++];
                    refs.put(v, actualTypeArgument);
                    // System.out.println("put " + v + "  --->  " + actualTypeArgument);
                }

                for (Type t : interfaceClass.getGenericInterfaces()) {
                    if (!allSuperTypes.contains(t)) {
                        allSuperTypes.add(t);
                    }
                }
            } else {
                for (Type t : ((Class) type).getGenericInterfaces()) {
                    if (!allSuperTypes.contains(t)) {
                        allSuperTypes.add(t);
                    }
                }
            }
        }

        Type returnType = targetType;
        while (true) {
            Type old = returnType;
            returnType = refs.get(returnType);
            if (returnType instanceof Class) {
                return (Class) returnType;
            }
            if (returnType == null) {
                returnType = old;
                if (returnType instanceof WildcardType) {
                    return (Class) ((WildcardType) returnType).getUpperBounds()[0];
                }
                return (Class) ((TypeVariable) returnType).getBounds()[0];
            }
        }
    }

    /**
     * �ռ�������г�����
     * 
     * @param clazz - �ռ�Ŀ��
     * @param findAncestor - �Ƿ���Ҹ���
     * @param findInterfaces - �Ƿ���ҽӿ�
     * 
     * @return {@link Map} ����������г���
     */
    public static Map<String, Object> getConstantFrom(Class clazz, // NL
                                                 boolean findAncestor, boolean findInterfaces) {

        HashMap<String, Object> map = new HashMap<String, Object>();

        if (findInterfaces) {
            for (Class interfaceClass : clazz.getInterfaces()) {
                fillConstantFrom(interfaceClass, map);
            }
        }

        if (findAncestor) {
            Class superClass = clazz;
            while (superClass != null) {
                fillConstantFrom(superClass, map);
                superClass = superClass.getSuperclass();
            }
        }

        fillConstantFrom(clazz, map);

        return map;
    }

    // ��侲̬����
    protected static void fillConstantFrom(Class clazz, HashMap<String, Object> map) {

        Field fields[] = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isSynthetic()) {
                continue; // ����ϵͳ����
            }

            int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers)) {
                continue; // ���ԷǾ�̬����
            }

            try {
                if (field.isAccessible()) {
                    field.setAccessible(true);
                }
                map.put(field.getName(), field.get(null));

            } catch (SecurityException e) {
                // Do nothing
            } catch (IllegalAccessException e) {
                // Do nothing
            }
        }
    }

    // ���Դ���
    public static void main(String... args) {

        // ������г���
        System.out.println("������г���");
        Map<String, ?> constants = getConstantFrom(Character.class, true, true);
        System.out.println(constants);

        // ��������ķ�������
        System.out.println();
        System.out.println("��������ķ�������" + java.lang.ClassLoader.class.getName());
        for (Method method : ClassLoader.class.getMethods()) {
            Class<?>[] classes = resolveTypeParameters(ClassLoader.class,
                method.getGenericReturnType());
            System.out.print(method.getName() + " = ");
            System.out.println(Arrays.toString(classes));
        }

        // ������������
        System.out.println();
        System.out.println("������������" + java.util.Properties.class.getName());
        Type genericSuperclassType = java.util.Properties.class.getGenericSuperclass();
        System.out.print(genericSuperclassType + " = ");
        System.out.println(Arrays
            .toString(resolveTypeParameters(java.util.Properties.class, genericSuperclassType)));

        System.out.println();
        System.out.println("��������������" + java.util.Properties.class.getName());
        for (Type genericInterfaceType : java.util.Properties.class.getGenericInterfaces()) {
            // ��������������
            Class<?>[] classes = resolveTypeParameters(java.util.Properties.class,
                genericInterfaceType);
            System.out.print(genericInterfaceType + " = ");
            System.out.println(Arrays.toString(classes));
        }
    }
}

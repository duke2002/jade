package net.paoding.rose.jade.statement;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link DAOMetaData} ��װ����һ��DAO�ӿ��౾���һЩ��Ϣ������������ೣ���ȵ�
 *
 * @author ��־�� [qieqie.wang@gmail.com]
 * @author �κ� [in355hz@gmail.com]
 */
public class DAOMetaData {

    /**
     *
     */
    private final DAOConfig config;

    /**
     * DAO����
     */
    private final Class<?> daoClass;

    /**
     * ������DAO�ӿ��ϵĳ������������ӿڵģ�
     */
    private final Map<String, Object> constants;

    /**
     * DAO���ϵ�����
     */
    private final Map<String, Object> attributes;

    /**
     * @param daoClass
     */
    public DAOMetaData(Class<?> daoClass, DAOConfig config) {
        this.daoClass = daoClass;
        this.config = config;
        this.constants = Collections
                .unmodifiableMap(GenericUtils.getConstantFrom(daoClass, true, true));
        this.attributes = new ConcurrentHashMap<String, Object>(4);
    }

    /**
     * ֧�ֱ�DAO��Ļ������ã�����Դ���á����������á�ORӳ�����õȵȣ�
     *
     * @return
     */
    public DAOConfig getConfig() {
        return config;
    }

    public Class<?> getDAOClass() {
        return daoClass;
    }

    /**
     * �������ͱ����ڱ�DAO��������������
     *
     * @param declaringClass �������ͱ���typeVarName����
     * @param typeVarName    ���ͱ�����
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Class resolveTypeVariable(Class<?> declaringClass, String typeVarName) {
        return GenericUtils.resolveTypeVariable(daoClass, declaringClass, typeVarName);
    }

    public Map<String, Object> getConstants() {
        return constants;
    }

    public Object getConstant(String fieldName) {
        return constants.get(fieldName);
    }

    /**
     * ���ù���DAO�ϵ�����
     *
     * @param name
     * @param value
     */
    public void setAttribute(String name, Object value) {
        this.attributes.put(name, value);
    }

    /**
     * @param name
     * @return ��ȡ�� {@link #setAttribute(String, Object)} ������
     */
    public Object getAttribute(String name) {
        return attributes.get(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DAOMetaData) {
            DAOMetaData other = (DAOMetaData) obj;
            return daoClass.equals(other.daoClass);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return daoClass.hashCode() * 13;
    }

    @Override
    public String toString() {
        return daoClass.getName();
    }
}

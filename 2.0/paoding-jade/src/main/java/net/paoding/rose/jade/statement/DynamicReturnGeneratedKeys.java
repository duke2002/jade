package net.paoding.rose.jade.statement;

import org.apache.commons.lang.ClassUtils;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;

/**
 * @see ReturnGeneratedKeys
 */
public abstract class DynamicReturnGeneratedKeys {

    /**
     * �Ƿ�Ҫ���� return generated keys����
     *
     * @param runtime
     */
    public abstract boolean shouldReturnGerneratedKeys(StatementRuntime runtime);

    /**
     * ���DAO���ص������Ƿ�ϸ�
     *
     * @param returnType DAO�����ķ������ͣ�������������ķ��������Ƿ��ͣ���ܻ������������Ϣ����Ϊ����ʱʵ��Ӧ�÷��ص���������)
     * @throws InvalidDataAccessApiUsageException DAO�����ķ������Ͳ��ϸ�
     */
    public void checkMethodReturnType(Class<?> returnType, StatementMetaData metaData) {
        returnType = ClassUtils.primitiveToWrapper(returnType);
        if (returnType != void.class && !Number.class.isAssignableFrom(returnType)) {
            throw new InvalidDataAccessApiUsageException(
                    "error return type, only support int/long/double/float/void type for method with @ReturnGeneratedKeys:"
                            + metaData.getMethod());
        }
    }
}

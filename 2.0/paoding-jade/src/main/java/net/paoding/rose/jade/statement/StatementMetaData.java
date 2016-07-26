package net.paoding.rose.jade.statement;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.annotation.SQLType;
import net.paoding.rose.jade.annotation.ShardBy;

/**
 * {@link StatementMetaData} ��װ��������һ��DAO�����������Ϣ
 * <p>
 * 
 * 
 * @author ��־�� [qieqie.wang@gmail.com]
 */
@SuppressWarnings({ "rawtypes" })
public class StatementMetaData {

    /**
     * ������DAO���classMetaData
     */
    private final DAOMetaData daoMetaData;

    /**
     * ���ڵ�DAO����
     */
    private final Method method;

    /**
     * DAO�����ϵ�ԭʼSQL��䣬���û��ִ��SQL��䣬����ݷ���ǩ��������Ӧ�Ĵ�����debug
     */
    private final String sql;

    /**
     * SQL���ͣ���ѯ���ͻ��߸������ͣ���Ĭ���ɷ�������SQL����жϣ�����ǿ��ָ����
     * @see SQLType
     */
    private final SQLType sqlType;

    /**
     * DAO�����ϵ�ReturnGeneratedKeysע��
     */
    private DynamicReturnGeneratedKeys returnGeneratedKeys;

    /**
     * 
     */
    private AfterInvocationCallback afterInvocationCallback;

    /**
     * DAO�����ġ���ͷ������͡���
     * <P>
     * �󲿷����returnType��method.getReturnType����ͬ�ģ�������һЩ����Ϊ���͵ķ������ͣ�
     * Jade�ᾡ����ȡ��ʵ�ʵ�������ΪreturnType
     * <P>
     * ���磺
     * 
     * <pre>
     * //@DAO��@SQLע�����
     * public interface BaseDAO&lt;E&gt; {
     * 
     *     public E getById(Long id);
     * 
     * }
     * public interface UserDAO extends BaseDAO[User] {
     * 
     * }
     * </pre>
     * 
     * ��ʱ��UserDAO#getById������returnType��User������Object;
     */
    private final Class returnType;

    /**
     * �������ز����ķ������ͣ���֧�ֶ༶������method�л�ȡ������
     * 
     * <pre>
     * ʾ����
     * ��1�� List<E>�е�E
     * ��2�� Map<K, V>�е�K��V
     */
    private final Class[] parameterTypesOfReturnType;

    /**
     * {@link SQLParam} ע�����飭��method�л�ȡ������
     * <p>
     * ������ĳ���Ϊ�����Ĳ��������������Ӧλ�õķ�������û��ע�� {@link SQLParam},��λ�õ�Ԫ��ֵΪnull
     */
    private final SQLParam[] sqlParams;

    /**
     * <code>@{@link ShardBy}</code>��ע���ĸ������ϣ�(��0��ʼ������������)����method�л�ȡ������
     */
    private final int shardByIndex;

    private final ShardBy shardBy;

    private final int parameterCount;

    /**
     * ��ܻ������õ�����
     */
    private Map<String, Object> attributes;

    private static final DynamicReturnGeneratedKeys nullDynamicReturnGeneratedKeys = new DynamicReturnGeneratedKeys() {

        @Override
        public boolean shouldReturnGerneratedKeys(StatementRuntime runtime) {
            return false;
        }
    };

    // --------------------------------------------

    public StatementMetaData(DAOMetaData daoMetaData, Method method) {
        this.daoMetaData = daoMetaData;
        this.method = method;
        SQL sqlAnnotation = method.getAnnotation(SQL.class);
        if (sqlAnnotation == null) {
            sqlAnnotation = new SQL() {

                @Override
                public Class<? extends Annotation> annotationType() {
                    return SQL.class;
                }

                @Override
                public String value() {
                    String toString = StatementMetaData.this.method.toString();
                    int paramStart = toString.indexOf("(");
                    int methodNameStart = toString.lastIndexOf('.', paramStart) + 1;
                    return toString.substring(methodNameStart) + "@" //
                           + StatementMetaData.this.method.getDeclaringClass().getName();
                }

                @Override
                public SQLType type() {
                    return SQLType.AUTO_DETECT;
                }
            };
        }
        this.sql = sqlAnnotation.value();
        this.sqlType = resolveSQLType(sqlAnnotation);
        ReturnGeneratedKeys generatedKeysAnnotation = method
            .getAnnotation(ReturnGeneratedKeys.class);
        if (generatedKeysAnnotation != null) {
            try {
                this.returnGeneratedKeys = generatedKeysAnnotation.value().newInstance();
            } catch (InstantiationException e) {
                throw new IllegalArgumentException(e);
            } catch (IllegalAccessException e) {
                throw new IllegalArgumentException(e);
            }
        } else {
            this.returnGeneratedKeys = nullDynamicReturnGeneratedKeys;
        }

        this.returnType = GenericUtils.resolveTypeVariable(daoMetaData.getDAOClass(),
            method.getGenericReturnType());
        this.parameterTypesOfReturnType = GenericUtils
            .resolveTypeParameters(daoMetaData.getDAOClass(), method.getGenericReturnType());

        Annotation[][] annotations = method.getParameterAnnotations();
        this.parameterCount = annotations.length;
        this.sqlParams = new SQLParam[annotations.length];
        int shardByIndex = -1;
        ShardBy shardBy = null;
        for (int index = 0; index < annotations.length; index++) {
            for (Annotation annotation : annotations[index])

            {
                if (annotation instanceof ShardBy) {
                    if (shardByIndex >= 0) {
                        throw new IllegalArgumentException(
                            "duplicated @" + ShardBy.class.getName());
                    }
                    shardByIndex = index;
                    shardBy = (ShardBy) annotation;
                } else if (annotation instanceof SQLParam) {
                    this.sqlParams[index] = (SQLParam) annotation;
                }
            }

        }
        this.shardByIndex = shardByIndex;
        this.shardBy = shardBy;
    }

    public DAOMetaData getDAOMetaData() {
        return daoMetaData;
    }

    public Method getMethod() {
        return method;
    }

    public DynamicReturnGeneratedKeys getReturnGeneratedKeys() {
        return returnGeneratedKeys;
    }

    public void setReturnGeneratedKeys(DynamicReturnGeneratedKeys returnGeneratedKeys) {
        this.returnGeneratedKeys = returnGeneratedKeys;
    }

    public AfterInvocationCallback getAfterInvocationCallback() {
        return afterInvocationCallback;
    }

    public void setAfterInvocationCallback(AfterInvocationCallback afterInvocationCallback) {
        this.afterInvocationCallback = afterInvocationCallback;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public String getSQL() {
        return sql;
    }

    public int getParameterCount() {
        return parameterCount;
    }

    public SQLParam getSQLParamAt(int argIndex) {
        return sqlParams[argIndex];
    }

    public int getShardByIndex() {
        return shardByIndex;
    }

    public ShardBy getShardBy() {
        return shardBy;
    }

    public Class<?>[] getGenericReturnTypes() {
        return parameterTypesOfReturnType;
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return method.getAnnotation(annotationClass);
    }

    public SQLType getSQLType() {
        return sqlType;
    }

    /**
     * ���ù���DAO�����ϵ�����
     * 
     * @param name
     * @param value
     */
    public void setAttribute(String name, Object value) {
        if (attributes == null) {
            synchronized (this) {
                if (attributes == null) {
                    attributes = new ConcurrentHashMap<String, Object>(4);
                }
            }
        }
        this.attributes.put(name, value);
    }

    /**
     * 
     * @param name
     * @return ��ȡ�� {@link #setAttribute(String, Object)} ������
     */
    @SuppressWarnings("unchecked")
    public <T> T getAttribute(String name) {
        return (T) (attributes == null ? null : attributes.get(name));
    }

    protected SQLType resolveSQLType(SQL sql) {
        SQLType sqlType = sql.type();
        if (sqlType == SQLType.AUTO_DETECT) {
            for (int i = 0; i < SELECT_PATTERNS.length; i++) {
                // ��������ʽƥ�� SELECT ���
                if (SELECT_PATTERNS[i].matcher(getSQL()).find() //
                    || SELECT_PATTERNS[i].matcher(getMethod().getName()).find()) {
                    sqlType = SQLType.READ;
                    break;
                }
            }
            if (sqlType == SQLType.AUTO_DETECT) {
                sqlType = SQLType.WRITE;
            }
        }
        return sqlType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StatementMetaData) {
            StatementMetaData modifier = (StatementMetaData) obj;
            return daoMetaData.equals(modifier.daoMetaData) && method.equals(modifier.method);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return daoMetaData.hashCode() ^ method.hashCode();
    }

    @Override
    public String toString() {
        return daoMetaData.getDAOClass().getName() + '#' + method.getName();
    }

    private static Pattern[] SELECT_PATTERNS = new Pattern[] {
                                                               //
                                                               Pattern.compile("^\\s*SELECT.*",
                                                                   Pattern.CASE_INSENSITIVE), //
                                                               Pattern.compile("^\\s*GET.*",
                                                                   Pattern.CASE_INSENSITIVE), //
                                                               Pattern.compile("^\\s*FIND.*",
                                                                   Pattern.CASE_INSENSITIVE), //
                                                               Pattern.compile("^\\s*READ.*",
                                                                   Pattern.CASE_INSENSITIVE), //
                                                               Pattern.compile("^\\s*QUERY.*",
                                                                   Pattern.CASE_INSENSITIVE), //
                                                               Pattern.compile("^\\s*COUNT.*",
                                                                   Pattern.CASE_INSENSITIVE), //
                                                               Pattern.compile("^\\s*SHOW.*",
                                                                   Pattern.CASE_INSENSITIVE), //
                                                               Pattern.compile("^\\s*DESC.*",
                                                                   Pattern.CASE_INSENSITIVE), //
                                                               Pattern.compile("^\\s*DESCRIBE.*",
                                                                   Pattern.CASE_INSENSITIVE), //
    };

}

package net.paoding.rose.jade.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.paoding.rose.jade.rowmapper.RowMapperFactory;
import net.paoding.rose.jade.statement.StatementMetaData;

/**
 * @author ��־�� [qieqie.wang@gmail.com]
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RowHandler {

    /**
     * ָ���Լ����õ� rowMapper �ࣻrowMapper��Ӧ��������״̬��<p>
     */
    @SuppressWarnings("rawtypes") Class<? extends RowMapper> rowMapper() default NotSettingRowMapper.class;

    /**
     * ͨ���Զ���� {@link RowMapperFactory} ָ���Լ����õ� rowMapper �ࣻ���ص�rowMapper��Ӧ��������״̬��<p>
     */
    Class<? extends RowMapperFactory> rowMapperFactory() default NotSettingRowMapperFactory.class;

    /**
     * ����һ����鿪��,Ĭ��Ϊtrue��
     * <p/>
     * true����������������ж���ӳ���һ�� Bean �����ԣ��׳��쳣��
     */
    boolean checkColumns() default true;

    /**
     * ����һ����鿪�أ�Ĭ��Ϊfalse; true�����������ÿһ��bean ���Զ�������SQL��ѯ�����ֵ���׳��쳣��
     */
    boolean checkProperties() default false;

    class NotSettingRowMapper implements RowMapper<Object> {

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            return null;
        }

    }

    class NotSettingRowMapperFactory implements RowMapperFactory {

        @Override
        public RowMapper<?> getRowMapper(StatementMetaData metaData) {
            return null;
        }

    }

}

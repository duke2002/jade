package net.paoding.rose.jade.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.paoding.rose.jade.statement.DynamicReturnGeneratedKeys;
import net.paoding.rose.jade.statement.StatementRuntime;

/**
 * 
 * �� {@link ReturnGeneratedKeys} ������insert���ķ����ϣ���ʾ���ص��ǲ����id��
 * 
 * <pre>
 * 1����@SQL������ע�� @ReturnGeneratedKeys
 * 2����������ֵ��Ϊ�����ص���ֵ���ͣ�����long��int��
 * ���ӣ�
 * 
 * &#064;ReturnGeneratedKeys
 * &#064;SQL(&quot;insert into role(id, name) values(myseq.nextal, :1)&quot;)
 * public long save(String name);
 * </pre>
 * 
 * 
 * @author ��־�� [qieqie.wang@gmail.com]
 * @author �κ� [in355hz@gmail.com]
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReturnGeneratedKeys {

    Class<? extends DynamicReturnGeneratedKeys>value() default Yes.class;

    static class Yes extends DynamicReturnGeneratedKeys {

        @Override
        public boolean shouldReturnGerneratedKeys(StatementRuntime runtime) {
            return true;
        }

    }
}

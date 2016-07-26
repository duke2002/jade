package net.paoding.rose.jade.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ʹ�ã�{#link UseMaster} ��ע��Ҫǿ�Ʋ�ѯ master ���ݿ�� DAO �ӿڷ�����
 * 
 * @author han.liao
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UseMaster {

    /**
     * �Ƿ���Ҫǿ�Ʋ�ѯ master ���ݿ⡣
     * 
     * @return ǿ�Ʋ�ѯ master ���ݿ�
     */
    boolean value() default true;
}

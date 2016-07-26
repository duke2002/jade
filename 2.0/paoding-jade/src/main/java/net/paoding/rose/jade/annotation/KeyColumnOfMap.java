package net.paoding.rose.jade.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Jade֧��DAO��������Map��ʽ�ģ�Ĭ�������Jadeѡȡ��һ����ΪMap��key��
 * <p>
 * �����Ƽ�����д����map��SQLʱ����key�ŵ���һ�У���������治���������������ͨ����ע�⣬��{@link KeyColumnOfMap}
 * ����ָ����
 * 
 * @author ��־�� [qieqie.wang@gmail.com]
 * @author �κ� [in355hz@gmail.com]
 */
@Target( { ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KeyColumnOfMap {

    /**
     * ָ��Ҫ������map key���ֶ�����
     * 
     * @return
     */
    String value();
}

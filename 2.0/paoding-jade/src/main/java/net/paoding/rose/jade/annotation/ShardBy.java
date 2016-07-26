package net.paoding.rose.jade.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ����sql��û�к���ɢ������Ļ�ɢ��������ƺ����õ�ɢ�����Ʋ�һ�µģ�ͨ���� ShardBy ��������������ϣ���ʾʹ�øò�������ɢ��.
 * <p>
 * &#64;SQL(&quot;....where name like :1&quot;)<br>
 * public List<Xxx> find(String likeValue, &#64;ShardBy String pageId);
 * 
 * <pre>
 * </pre>
 * 
 * @author ��־�� [qieqie.wang@gmail.com]
 */
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShardBy {

    /**
     * bean��������µ�������
     * 
     * @return
     */
    String value() default "";
}

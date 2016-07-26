/*
 * Copyright 2009-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License i distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.paoding.rose.jade.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ��{@link SQL}ע���ע��Jade DAO�����ϣ���ʾ���DAO������Ҫִ�е�SQL��䡣
 * <p>
 * 
 * Jade��SQL����Ϊ��ѯ���ͺ͸������ͣ�Jade����SQL���ͷ���Ļ�������Ŀ�ģ�<br>
 * <ul>
 * <li>1����Ϊ���������͵�SQL���ؽ����һ������ѯ���ͷ��ؽ����һ��������� �������͵�SQL���ؽ��ֻ��һ�����ֱ�ʾ���µ���Ŀ��</li>
 * <li>2����Ϊ���ܹ�ʹSQL�ܹ���master- slave�����ݿ�ܹ��з�����ȷ��Ŀ�����ݿ�ִ�С�</li>
 * </ul>
 * <p>
 * 
 * �򵥵أ�Jade��Ϊ������SELECT��ʼ��SQL�ǲ�ѯ���͵ģ������Ķ��Ǹ������͵ġ�������Ȼ���ַַ��ǳ�������
 * ����SHOW���������ľ�Ӧ���ǲ�ѯ���͵ģ�����������£����ǻ���ϣ���ɿ���������{@link SQL}
 * ָ���ɣ��������Ҫִ��һЩ��SELECT�Ĳ�ѯ���͵����Ļ���
 * <p>
 * 
 * ��дSQLʱ�ɰ�SQL����ֱֵ�ӷŵ�SQL�У����£�<br>
 * <span style='margin-left:50px;'>
 * <code>@SQL("SELECT id, account, name FROM user WHERE id='12345'")</code>
 * </span>
 * <p>
 * Ҳ����DAO��������������ָ�������֧���˶�̬����������ð�ſ�ʼ������һ�������ַ�����ʾ�����£�<br>
 * <span style='margin-left:50px;'>
 * <code>@SQL("SELECT id, account, name FROM user WHERE id=:userId")<span>
 * <br>
 * <span style='margin-left:50px;'> public User getUser(@SQLParam("userId") String id);</code><span>
 * <p>
 * OR<br>
 * <span style='margin-left:50px;'>
 * <code>@SQL("SELECT id, account, name FROM user where id=:user.id")<span>
 * <br>
 * <span style='margin-left:50px;'> public User getUser(@SQLParam("user") User user);</code><span> <br>
 * <p>
 * �ڴˣ�����Ҳʾ����{@link SQL}ע����ʹ�����ͱ�׼��SQL����������Ϊ�˸�����Ч��֧�ֱ�̣��˴���SQL���н�Ϊ�ḻ�ķ������������
 * http://paoding-rose.googlecode.com/....
 * 
 * @author ��־�� [qieqie.wang@gmail.com]
 * @author �κ� [in355hz@gmail.com]
 */
@Target( { ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SQL {

    /**
     * 
     * @return Jade֧�ֵ�SQL���
     */
    String value();

    /**
     * ���ظ��������ͣ���ѯ���ͻ������͡�
     * Ĭ��Jade��Ϊֻ����SELECT��ʼ�Ĳ��ǲ�ѯ���ͣ�������Ϊ������͡�������ͨ����������������JadeĬ�ϵĴ���!
     * 
     * @return ��ѯ����
     */
    SQLType type() default SQLType.AUTO_DETECT;
}

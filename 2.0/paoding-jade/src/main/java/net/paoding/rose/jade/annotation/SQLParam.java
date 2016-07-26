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
 * ��{@link SQLParam} ע���עDAO�����Ĳ�����ָ�����������ƣ�ʹ�ÿ�����SQL��ͨ��":������"�ķ�ʽʹ������
 * Jadeͨ��PreparedStatment��̬�ذѲ���ֵ�ύ�����ݿ�ִ�С�
 * <p>
 * 
 * <span style='margin-left:50px;'>
 * <code>@SQL("SELECT id, account, name FROM user WHERE id=:userId")<span>
 * <br>
 * <span style='margin-left:50px;'> public User getUser(@SQLParam("userId") String id);</code><span>
 * <p>
 * 
 * @author ��־�� [qieqie.wang@gmail.com]
 * @author �κ� [in355hz@gmail.com]
 */
@Target( { ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SQLParam {

    /**
     * ָ�����ֵ�� SQL ������ĸ�������ֵ
     * 
     * @return ��Ӧ SQL ������ĸ�����
     */
    String value();
}

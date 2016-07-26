/*
 * Copyright 2009-2012 the original author or authors.
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
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;


/**
 * 
 * �ô�{@link DAO}ע���ע��һ������Jade��д�淶��DAO�ӿ����ϣ���ȷ��ע����Jade DAO�ӿڡ�
 * 
 * <p>
 * һ��Jade DAO��Ҫ�������»���Ҫ��
 * <ul>
 * <li>1�� ��dao package����package�£���com.renren.myapp.dao��</li>
 * <li>2�� ��һ��public��java interface ���ͣ�</li>
 * <li>3�� ���Ʊ����Դ�дDAO��ĸ��β����UserDAO��</li>
 * <li>4�� �����ע@DAO ע�⣻</li>
 * <li>5�� ������������ڲ��ӿڣ�</li>
 * <p>
 * 
 * ���DAO�ӿڱ������Ϊһ��jar�ģ�Ϊ��Ҫ��Jadeʶ�𣬱��������jar�� META-INFO/rose.properties
 * �ļ��а���������ԣ�rose=dao (rose=*���)��
 * 
 * 
 * @see http://code.google.com/p/paoding-rose/wiki/Jade_DAO_Spec
 * @author ��־�� [qieqie.wang@gmail.com]
 * @author �κ� [in355hz@gmail.com]
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DAO {

    /**
     * ָ����ʹ�����ݿ����ӵ�catalog���ԣ�����Ϊ��(�մ�)�ȼ���û�����ã���ʾ��ʹ��catalog���ԡ�
     * <p>
     * 
     * һ�������������Ҫ���κ����ã����������ڵĹ�˾����֯�н�һ���Ĺ淶��
     * <p>
     * 
     * catalog��������ο� {@link Connection#setCatalog(String)}
     * ���ر�أ���֧�ִ�ֱ�зֵ�����Դ�У�Ҳ����ʹ��catalog��Ϊ�зֵ�һ���ο�
     * <p>
     * 
     * @see Connection#setCatalog(String)
     * @return
     */
    String catalog() default "";
    
    
    
}

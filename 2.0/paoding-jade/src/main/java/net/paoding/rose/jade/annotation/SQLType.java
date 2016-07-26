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

/**
 * SQL���ͱ�ʶ��
 * <p>
 * ��ʹ��{@link SQL}
 * ע��ʱ��Jade����SELECT��ʼ�������Ϊ�ǲ�ѯ����SQL��䣬��������䱻��Ϊ�Ǹ������ͣ������߿��Ը���ʵ�ʸı�Jade��Ĭ���ж�
 * ������SHOW���ʵ��Ӧ���ǲ�ѯ������䣬���Ǹ���������䡣
 * 
 * @author ��־�� [qieqie.wang@gmail.com]
 * @author �κ� [in355hz@gmail.com]
 */
public enum SQLType {
    /**
     * ��ѯ�������
     */
    READ,

    /**
     * �����������
     */
    WRITE,

    /**
     * δ֪���ͣ���ʹ��Jade��Ĭ�Ϲ����жϣ�������SELECT��ʼ������ǲ�ѯ���͵ģ��������Ǹ������͵�
     */
    AUTO_DETECT,

}

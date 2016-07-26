package net.paoding.rose.jade.statement;

import java.util.Map;

/**
 * {@link Statement} ��ʾһ�����Ϲ淶��DAO����������һ�������ݿ���м�������µ���䡣
 * <p>
 * 
 * ����һ���ڲ��ӿ�
 * 
 * @author ��־�� [qieqie.wang@gmail.com]
 */
public interface Statement {

    /**
     * ������DAO����������ص���Ϣ
     * 
     * @return
     */
    public StatementMetaData getMetaData();

    /**
     * ���ո����ķ�������ֵ��ִ��һ�����ݿ���������
     * <p>
     * ������ͨ��parameters.get(":1")��parameters.get(":2")��÷����еĵ�1����2������(��1��ʼ)<br>
     * ���DAO�����еĲ���������<code>@SQLParam(name)</code>
     * ע�⣬�������Դ�parameters.get(name)ȡ�øò�����
     * 
     * @return
     */
    public Object execute(Map<String, Object> parameters);
}

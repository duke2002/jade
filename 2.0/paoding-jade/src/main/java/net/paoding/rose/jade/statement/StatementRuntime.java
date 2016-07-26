package net.paoding.rose.jade.statement;

import java.util.Map;

import net.paoding.rose.jade.annotation.SQLParam;
import net.paoding.rose.jade.dataaccess.DataSourceFactory;

/**
 * ��װһ��DAO������Ϣ
 *
 * @author ��־�� [qieqie.wang@gmail.com]
 */
public interface StatementRuntime {

    /**
     * @return ������Ҫִ�е�DAO������Ϣ
     */
    StatementMetaData getMetaData();

    /**
     * @return ���ص���DAO��������Ĳ�����keyΪ":1"��":2"���Լ� {@link SQLParam} ע��ָ��������(����ð��)
     */
    Map<String, Object> getParameters();

    /**
     * @return ���ش�ʱҪִ�е�SQL�����ֵΪ��ע��DAO�����ϵ�@SQL���ݣ��� {@link Interpreter}�ǻ�ͨ��{@link #setSQL(String)} ���Ըı�����
     * �����γɷ������ݿ��﷨Ҫ�������SQL���
     */
    String getSQL();

    /**
     * �������SQL����Ϊ�´����SQL���
     *
     * @param sql
     */
    void setSQL(String sql);

    /**
     * @return ��SQL����תΪ�����ʺ�?��ʱ�򣬷���SQL����ж�Ӧ�Ĳ���ֵ
     */
    Object[] getArgs();

    /**
     * ��SQL����תΪ�����ʺ�?��ʱ�򣬵��ñ�������SQL����ж�Ӧ�Ĳ�����˳�������
     *
     * @param args
     */
    void setArgs(Object[] args);

    /**
     * ����{@link Interpreter}�ڽ���SQL���ʱ�򣬿��Ը�runtime���õ�һЩ���ԣ����� {@link DataSourceFactory} ���ھ���
     *
     * @return �ǿ�
     */
    Map<String, Object> getAttributes();

    /**
     * {@link Interpreter}�ڽ���SQL���ʱ�򣬿��Ը�runtime���õ�һЩ���ԣ����� {@link DataSourceFactory} ���ھ���
     *
     * @param name
     * @param value
     */
    void setAttribute(String name, Object value);

    /**
     * ����{@link Interpreter}�ڽ���SQL���ʱ�򣬿��Ը�runtime���õ�һЩ���ԣ����� {@link DataSourceFactory} ���ھ���
     *
     * @param name
     * @return
     */
    <T> T getAttribute(String name);
}

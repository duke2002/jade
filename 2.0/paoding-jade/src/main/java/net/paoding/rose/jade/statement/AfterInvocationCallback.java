package net.paoding.rose.jade.statement;

/**
 * @author ��־�� [qieqie.wang@gmail.com]
 */
public interface AfterInvocationCallback {

    /**
     * @param runtime     ����ʱ
     * @param returnValue DAO�����ķ���ֵ
     * @return �ı��ķ���ֵ
     */
    public Object execute(StatementRuntime runtime, Object returnValue);
}

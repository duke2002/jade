package net.paoding.rose.jade.statement;

import org.springframework.core.annotation.Order;

/**
 * ���� {@link Order}���������ȼ������� {@link Order} ���壬ֵԽСԽ���ȣ�ֵԽ��Խ��
 * <p>
 * ���û�б�ע {@link Order} ʹ��Ĭ��ֵ0��
 * 
 * ��ʵ������jade����Ľ�����һ��Ӧ������Ϊ��������������ϵͳ��������
 * 
 * @author ��־�� [qieqie.wang@gmail.com]
 * @author �κ� [in355hz@gmail.com]
 * 
 */
//��Spring����涨������ {@link Order} ���壬ֵԽСԽ���ȣ�ֵԽ��Խ��
@Order(0)
public interface Interpreter {

    /**
     * 
     * @param runtime
     */
    void interpret(StatementRuntime runtime);

}

package net.paoding.rose.jade.rowmapper;



import net.paoding.rose.jade.statement.StatementMetaData;

import org.springframework.jdbc.core.RowMapper;

/**
 * {@link RowMapperFactory}����Ϊÿһ����ѯ����DAO����������Ӧ����ӳ����{@link RowMapper}
 * ����������SQL������е�ÿһ��ת��ΪDAO��������ķ���������Ҫ���������͡�
 * <p>
 * ����DAO��������������List<User>��User�ģ�RowMapper�������������ÿһ��ת��ΪUser����<br>
 * �������������String[]��String�ģ�RowMapper�������������ÿһ��ת��Ϊһ���ַ�������<br>
 * ������
 * <p>
 * 
 * @author ��־�� [qieqie.wang@gmail.com]
 * @author �κ� [in355hz@gmail.com]
 */
public interface RowMapperFactory {

    /**
     * ����DAO�����ķ������Ͷ��壬�������ܴ��ڵķ��ͣ�������Ӧ����ӳ��������
     * 
     * @return ����޷�����ʱ�ɷ���null
     */
    public RowMapper<?> getRowMapper(StatementMetaData metaData);
}

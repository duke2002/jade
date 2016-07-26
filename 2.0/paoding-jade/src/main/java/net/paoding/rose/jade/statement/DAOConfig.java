package net.paoding.rose.jade.statement;

import net.paoding.rose.jade.dataaccess.DataAccessFactory;
import net.paoding.rose.jade.rowmapper.RowMapperFactory;

/**
 * ֧��DAO��Ļ������ã�����Դ���á�SQL���������á�ORӳ�����õȵȣ�
 * 
 * @author ��־�� [qieqie.wang@gmail.com]
 * 
 */
public class DAOConfig {

    private final DataAccessFactory dataAccessFactory;

    private final RowMapperFactory rowMapperFactory;

    private final InterpreterFactory interpreterFactory;

    private final StatementWrapperProvider statementWrapperProvider;

    public DAOConfig(DataAccessFactory dataAccessFactory, //
                     RowMapperFactory rowMapperFactory, //
                     InterpreterFactory interpreterFactory,
                     StatementWrapperProvider statementWrapperProvider) {
        this.dataAccessFactory = dataAccessFactory;
        this.rowMapperFactory = rowMapperFactory;
        this.interpreterFactory = interpreterFactory;
        this.statementWrapperProvider = statementWrapperProvider;
    }

    /**
     * ��׼���ݷ���������
     * 
     * @return
     */
    public DataAccessFactory getDataAccessFactory() {
        return dataAccessFactory;
    }

    /**
     * SQL����������
     * 
     * @return
     */
    public InterpreterFactory getInterpreterFactory() {
        return interpreterFactory;
    }

    /**
     * ORӳ������
     * 
     * @return
     */
    public RowMapperFactory getRowMapperFactory() {
        return rowMapperFactory;
    }

    /**
     * 
     * @return
     */
    public StatementWrapperProvider getStatementWrapperProvider() {
        return statementWrapperProvider;
    }

}

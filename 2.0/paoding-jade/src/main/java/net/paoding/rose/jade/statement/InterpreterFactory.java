package net.paoding.rose.jade.statement;

/**
 * 
 * @author ��־�� [qieqie.wang@gmail.com]
 * 
 */
public interface InterpreterFactory {

    Interpreter[] getInterpreters(StatementMetaData metaData);
}

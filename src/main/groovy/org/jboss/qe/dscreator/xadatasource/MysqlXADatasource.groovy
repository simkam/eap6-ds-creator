package org.jboss.qe.dscreator.xadatasource

/**
 * @author Martin Simka
 */
class MysqlXADatasource extends AbstractXADatasource<MysqlXADatasource> {
    private static final String CHECKER_CLASS = "org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"
    private static final String EXCEPTION_SORTER_CLASS = "org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"
    
    @Override
    protected MysqlXADatasource me() {
        return this
    }

    MysqlXADatasource serverName(String serverName) {
        if(serverName == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("ServerName", serverName))
        return this
    }

    MysqlXADatasource portNumber(String portNumber) {
        if(portNumber == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("PortNumber", portNumber))
        return this
    }

    MysqlXADatasource databaseName(String databaseName) {
        if(databaseName == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("DatabaseName", databaseName))
        return this
    }
    
    def buildValidation(builder) {
        builder.validation{
            'valid-connection-checker'('class-name': CHECKER_CLASS)
            'exception-sorter'('class-name': EXCEPTION_SORTER_CLASS)
        }
    }
}

package org.jboss.qe.dscreator.xadatasource

/**
 * @author Martin Simka
 */
class MSSqlXADatasource extends AbstractXADatasource<MSSqlXADatasource> {
    private static final String CHECKER_CLASS = "org.jboss.jca.adapters.jdbc.extensions.mssql.MSSQLValidConnectionChecker"

    @Override
    protected MSSqlXADatasource me() {
        return this
    }

    MSSqlXADatasource serverName(String serverName) {
        if(serverName == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("ServerName", serverName))
        return this
    }

    MSSqlXADatasource portNumber(String portNumber) {
        if(portNumber == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("PortNumber", portNumber))
        return this
    }

    MSSqlXADatasource databaseName(String databaseName) {
        if(databaseName == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("DatabaseName", databaseName))
        return this
    }
    
    def buildValidation(builder) {
        builder.validation{
            'valid-connection-checker'('class-name': CHECKER_CLASS)
        }
    }
}

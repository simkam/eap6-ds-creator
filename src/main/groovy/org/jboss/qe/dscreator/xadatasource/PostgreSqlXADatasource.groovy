package org.jboss.qe.dscreator.xadatasource

/**
 * @author Martin Simka
 */
class PostgreSqlXADatasource extends AbstractXADatasource<PostgreSqlXADatasource> {

    @Override
    protected PostgreSqlXADatasource me() {
        return this
    }

    PostgreSqlXADatasource serverName(String serverName) {
        if(serverName == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("ServerName", serverName))
        return this
    }

    PostgreSqlXADatasource portNumber(String portNumber) {
        if(portNumber == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("PortNumber", portNumber))
        return this
    }

    PostgreSqlXADatasource databaseName(String databaseName) {
        if(databaseName == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("DatabaseName", databaseName))
        return this
    }
}

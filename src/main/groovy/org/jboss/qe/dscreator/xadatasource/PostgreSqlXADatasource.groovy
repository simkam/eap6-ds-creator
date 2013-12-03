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
        this.xaDatasourceProperty(new XADatasourceProperty("ServerName", serverName))
        return this
    }

    PostgreSqlXADatasource portNumber(String portNumber) {
        if(portNumber == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceProperty(new XADatasourceProperty("PortNumber", portNumber))
        return this
    }

    PostgreSqlXADatasource databaseName(String databaseName) {
        if(databaseName == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceProperty(new XADatasourceProperty("DatabaseName", databaseName))
        return this
    }
}

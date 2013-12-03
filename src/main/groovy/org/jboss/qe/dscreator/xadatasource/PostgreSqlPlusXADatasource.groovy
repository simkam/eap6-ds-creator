package org.jboss.qe.dscreator.xadatasource

/**
 * @author Martin Simka
 */
class PostgreSqlPlusXADatasource extends AbstractXADatasource<PostgreSqlPlusXADatasource> {

    @Override
    protected PostgreSqlPlusXADatasource me() {
        return this
    }

    PostgreSqlPlusXADatasource serverName(String serverName) {
        if(serverName == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceProperty(new XADatasourceProperty("ServerName", serverName))
        return this
    }

    PostgreSqlPlusXADatasource portNumber(String portNumber) {
        if(portNumber == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceProperty(new XADatasourceProperty("PortNumber", portNumber))
        return this
    }

    PostgreSqlPlusXADatasource databaseName(String databaseName) {
        if(databaseName == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceProperty(new XADatasourceProperty("DatabaseName", databaseName))
        return this
    }
}

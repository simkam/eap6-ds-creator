package org.jboss.qe.dscreator.xadatasource

/**
 * @author Martin Simka
 */
class MysqlXADatasource extends AbstractXADatasource<MysqlXADatasource> {

    @Override
    protected MysqlXADatasource me() {
        return this
    }

    MysqlXADatasource serverName(String serverName) {
        if(serverName == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceProperty(new XADatasourceProperty("ServerName", serverName))
        return this
    }

    MysqlXADatasource portNumber(String portNumber) {
        if(portNumber == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceProperty(new XADatasourceProperty("PortNumber", portNumber))
        return this
    }

    MysqlXADatasource databaseName(String databaseName) {
        if(databaseName == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceProperty(new XADatasourceProperty("DatabaseName", databaseName))
        return this
    }
}

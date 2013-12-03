package org.jboss.qe.dscreator.xadatasource

/**
 * @author Martin Simka
 */
class DB2XADatasource extends AbstractXADatasource<DB2XADatasource> {
    private static final XADatasourceProperty DEFAULT_DRIVER_TYPE_PROPERTY = new XADatasourceProperty("DriverType", "4")

    DB2XADatasource() {
        super()
        this.xaDatasourceProperty(DEFAULT_DRIVER_TYPE_PROPERTY)
    }

    @Override
    protected DB2XADatasource me() {
        return this
    }

    DB2XADatasource serverName(String serverName) {
        if(serverName == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceProperty(new XADatasourceProperty("ServerName", serverName))
        return this
    }

    DB2XADatasource portNumber(String portNumber) {
        if(portNumber == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceProperty(new XADatasourceProperty("PortNumber", portNumber))
        return this
    }

    DB2XADatasource databaseName(String databaseName) {
        if(databaseName == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceProperty(new XADatasourceProperty("DatabaseName", databaseName))
        return this
    }

    /*
     * private because it's really ugly :)
     */
    private DB2XADatasource driverType(String driverType) {
        if(driverType == null) {
            throw new NullPointerException()
        }
        int index = this.getXADatasourceProperties().indexOf(DEFAULT_DRIVER_TYPE_PROPERTY)
        this.getXADatasourceProperties().set(index, new XADatasourceProperty("DriverType", driverType))
        return this
    }
}

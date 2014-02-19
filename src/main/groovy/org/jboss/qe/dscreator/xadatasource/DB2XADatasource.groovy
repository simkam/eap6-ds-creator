package org.jboss.qe.dscreator.xadatasource

/**
 * @author Martin Simka
 */
class DB2XADatasource extends AbstractXADatasource<DB2XADatasource> {
    private static final XADatasourceProperty DEFAULT_DRIVER_TYPE_PROPERTY = new XADatasourceProperty("DriverType", "4")
    private static final VALID_CHECKER_CLASS = "org.jboss.jca.adapters.jdbc.extensions.db2.DB2ValidConnectionChecker"
    private static final STALE_CHECKER_CLASS = "org.jboss.jca.adapters.jdbc.extensions.db2.DB2StaleConnectionChecker"
    private static final EXCEPTION_SORTER_CLASS = "org.jboss.jca.adapters.jdbc.extensions.db2.DB2ExceptionSorter"
    private static final RECOVERY_PLUGIN_CLASS = "org.jboss.jca.core.recovery.ConfigurableRecoveryPlugin"

    DB2XADatasource() {
        super()
        this.dsXaDatasourceProperty(DEFAULT_DRIVER_TYPE_PROPERTY)
    }

    @Override
    protected DB2XADatasource me() {
        return this
    }

    DB2XADatasource serverName(String serverName) {
        if(serverName == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("ServerName", serverName))
        return this
    }

    DB2XADatasource portNumber(String portNumber) {
        if(portNumber == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("PortNumber", portNumber))
        return this
    }

    DB2XADatasource databaseName(String databaseName) {
        if(databaseName == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("DatabaseName", databaseName))
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
    
    def buildValidation(builder) {
        builder.validation {
            'valid-connection-checker'('class-name': VALID_CHECKER_CLASS)
            'stale-connection-checker'('class-name': STALE_CHECKER_CLASS)
            'exception-sorter'('class-name': EXCEPTION_SORTER_CLASS)
        }
        builder.recovery {
           'recover-plugin'('class-name': RECOVERY_PLUGIN_CLASS)
           'config-property'('name': "EnableIsValid", false)
           'config-property'('name': "IsValidOverride", false)
           'config-property'('name': "EnableClose", false)
        }
    }
}

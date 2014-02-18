package org.jboss.qe.dscreator.xadatasource

/**
 * @author Martin Simka
 */
class OracleXADatasource extends AbstractXADatasource<OracleXADatasource> {
    private static final String VALID_CHECKER_CLASS = "org.jboss.jca.adapters.jdbc.extensions.oracle.OracleValidConnectionChecker"
    private static final String STALE_CHECKER_CLASS = "org.jboss.jca.adapters.jdbc.extensions.oracle.OracleStaleConnectionChecker"
    private static final String EXCEPTION_SORTER_CLASS = "org.jboss.jca.adapters.jdbc.extensions.oracle.OracleExceptionSorter"
    
    @Override
    protected OracleXADatasource me() {
        return this
    }

    OracleXADatasource url(String url) {
        if(url == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("URL", url))
        return this
    }

    OracleXADatasource databaseName(String databaseName) {
        if(databaseName == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceProperty(new XADatasourceProperty("DatabaseName", databaseName))
        return this
    }
    
    def buildValidation(builder) {
        builder.validation{
            'valid-connection-checker'('class-name': VALID_CHECKER_CLASS)
            'stale-connection-checker'('class-name': STALE_CHECKER_CLASS)
            'exception-sorter'('class-name': EXCEPTION_SORTER_CLASS)
        }
    }
}

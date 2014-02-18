package org.jboss.qe.dscreator.xadatasource

/**
 * @author Martin Simka
 */
class OracleXADatasource extends AbstractXADatasource<OracleXADatasource> {

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


}

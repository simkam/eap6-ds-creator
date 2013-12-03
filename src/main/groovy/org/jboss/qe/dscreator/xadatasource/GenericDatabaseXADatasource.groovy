package org.jboss.qe.dscreator.xadatasource

/**
 * @author Martin Simka
 */
class GenericDatabaseXADatasource extends AbstractXADatasource<GenericDatabaseXADatasource> {
    @Override
    protected GenericDatabaseXADatasource me() {
        return this
    }

    GenericDatabaseXADatasource url(String url) {
        if(url == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceProperty(new XADatasourceProperty("URL", url))
        return this
    }


}

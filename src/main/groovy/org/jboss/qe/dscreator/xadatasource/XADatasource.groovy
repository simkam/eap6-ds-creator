package org.jboss.qe.dscreator.xadatasource

/**
 * @author Martin Simka
 */
public interface XADatasource {
    String getName()
    String getJndiName()
    String getPoolName()
    String getUsername()
    String getPassword()
    String getDriver()
    String getXADatasourceClass()
    Boolean getEnabled()
    List<XADatasourceProperty> getXADatasourceProperties();
    String toXml()
}
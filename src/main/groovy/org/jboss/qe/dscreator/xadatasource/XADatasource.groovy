package org.jboss.qe.dscreator.xadatasource

import org.jboss.qe.dscreator.common.XMLFormattable;

/**
 * @author Martin Simka
 */
public interface XADatasource extends XMLFormattable {
    String getDsName()
    String getDsJndiName()
    String getDsPoolName()
    String getDsUsername()
    String getDsPassword()
    String getDsDriver()
    String getDsXADatasourceClass()
    Boolean getDsEnabled()
    List<XADatasourceProperty> getDsXADatasourceProperties();
}
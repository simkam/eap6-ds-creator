package org.jboss.qe.dscreator.datasource

/**
 * @author Martin Simka
 */
interface Datasource {

   String getName()
   String getJndiName()
   String getPoolName()
   String getConnectionUrl()
   String getDriver()
   String getUsername()
   String getPassword()
   Boolean getUseJavaContext()
   Boolean getEnabled()
   Boolean getJta()
   String toXml()
}

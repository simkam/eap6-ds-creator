package org.jboss.qe.dscreator.datasource

import org.jboss.qe.dscreator.common.XMLFormattable;

import com.sun.xml.internal.ws.api.config.management.policy.ManagedServiceAssertion.ImplementationRecord;


/**
 * @author Martin Simka
 */
interface Datasource extends XMLFormattable {
   String getDsJndiName()
   String getDsPoolName()
   String getDsConnectionUrl()
   String getDsDriver()
   String getDsUsername()
   String getDsPassword()
   Boolean isDsUseJavaContext()
   Boolean isDsEnabled()
   Boolean isDsJta()
}

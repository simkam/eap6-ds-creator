package org.jboss.qe.dscreator.datasource

import groovy.xml.MarkupBuilder
import groovy.xml.StreamingMarkupBuilder;

/**
 * @author Martin Simka
 */
abstract class AbstractDatasource<T extends AbstractDatasource<T>> implements Datasource{
    private String dsName;
    private String dsJndiName
    private String dsPoolName
    private Boolean dsUseJavaContext
    private Boolean dsEnabled
    private Boolean dsJta
    private String dsDriver
    private String dsUsername
    private String dsPassword
    private String dsConnectionUrl

    protected abstract T me();

    @Override
    public String getDsName() {
        return dsName
    }

    T dsName(String dsName) {
        this.dsName = dsName
        return me()
    }

    @Override
    public String getDsJndiName() {
        return dsJndiName
    }

    T dsJndiName(String dsJndiName) {
        if(dsJndiName == null) {
            throw new NullPointerException()
        }
        this.dsJndiName = dsJndiName
        return me()
    }

    @Override
    public String getDsPoolName() {
        return dsPoolName
    }

    T dsPoolName(String dsPoolName) {
        if(dsPoolName == null) {
            throw new NullPointerException()
        }
        this.dsPoolName = dsPoolName
        return me()
    }

    @Override
    public String getDsConnectionUrl() {
        return dsConnectionUrl
    }

    T dsConnectionUrl(String dsConnectionUrl) {
        if(dsConnectionUrl == null) {
            throw new NullPointerException()
        }
        this.dsConnectionUrl = dsConnectionUrl
        return me()
    }

    @Override
    public String getDsDriver() {
        return dsDriver
    }

    T dsDriver(String dsDriver) {
        if(dsDriver == null) {
            throw new NullPointerException()
        }
        this.dsDriver = dsDriver
        return me()
    }


    @Override
    public String getDsUsername() {
        return dsUsername
    }

    T dsUsername(String dsUsername) {
        if(dsUsername == null) {
            throw new NullPointerException()
        }
        this.dsUsername = dsUsername
        return me()
    }

    @Override
    public String getDsPassword() {
        return dsPassword
    }

    T dsPassword(String dsPassword) {
        if(dsPassword == null) {
            throw new NullPointerException()
        }
        this.dsPassword = dsPassword
        return me()
    }

    @Override
    public Boolean isDsUseJavaContext() {
        return dsUseJavaContext
    }

    T dsUseJavaContext(boolean dsUseJavaContext) {
        this.dsUseJavaContext = dsUseJavaContext
        return me();
    }

    @Override
    public Boolean isDsEnabled() {
        return dsEnabled
    }

    T dsEnabled(boolean dsEnabled) {
        this.dsEnabled = dsEnabled
        return me()
    }

    @Override
    public Boolean isDsJta() {
        return dsJta
    }

    T dsJta(boolean dsJta) {
        this.dsJta = dsJta
        return me()
    }

    String toXml() {
		def markupBuilder = new StreamingMarkupBuilder()
        markupBuilder.encoding = "UTF-8"
        markupBuilder.useDoubleQuotes = true
		
		return markupBuilder.bind {
		    datasource(jta: dsJta, "jndi-name": dsJndiName, "pool-name": dsPoolName, enabled: dsEnabled, "use-java-context": dsUseJavaContext, name : dsName) {
				'connection-url'(dsConnectionUrl)
				driver(dsDriver)
				security() {
					'user-name'(dsUsername)
					password(dsPassword)
				}
			}
		}
	}
}

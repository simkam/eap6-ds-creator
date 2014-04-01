package org.jboss.qe.dscreator.xadatasource

import groovy.xml.MarkupBuilder

/**
 * @author Martin Simka
 */
abstract class AbstractXADatasource<T extends AbstractXADatasource<T>> implements XADatasource {

    private String dsJndiName
    private String dsPoolName
    private Boolean dsEnabled
    private String dsDriver
    private String dsUsername
    private String dsPassword
    private String dsXaDatasourceClass
    private List<XADatasourceProperty> dsXaDatasourceProperties

    protected AbstractXADatasource() {
        dsXaDatasourceProperties = new ArrayList<XADatasourceProperty>()
    }

    protected abstract T me();

    @Override
    String getDsJndiName() {
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
    String getDsPoolName() {
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
    String getDsUsername() {
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
    String getDsPassword() {
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
    String getDsDriver() {
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
    String getDsXADatasourceClass() {
        return dsXaDatasourceClass
    }

    T dsXaDatasourceClass(String dsXaDatasourceClass) {
        if(dsXaDatasourceClass == null) {
            throw new NullPointerException()
        }
        this.dsXaDatasourceClass = dsXaDatasourceClass
        return me()
    }

    @Override
    Boolean getDsEnabled() {
        return dsEnabled
    }

    T dsEnabled(boolean dsEnabled) {
        this.dsEnabled = dsEnabled
        return me()
    }

    @Override
    List<XADatasourceProperty> getDsXADatasourceProperties() {
        return dsXaDatasourceProperties
    }

    T dsXaDatasourceProperty(XADatasourceProperty dsXaDatasourceProperty) {
        if(dsXaDatasourceProperty == null) {
            throw new NullPointerException()
        }
        dsXaDatasourceProperties.add(dsXaDatasourceProperty)
        return me()
    }

    @Override
    String toXml() {
		def markupBuilder = new groovy.xml.StreamingMarkupBuilder()
		markupBuilder.encoding = "UTF-8"
		markupBuilder.useDoubleQuotes = true
		
		return markupBuilder.bind { builder ->
			'xa-datasource'("jndi-name": dsJndiName, "pool-name": dsPoolName, enabled: dsEnabled) {
	            getDsXADatasourceProperties().each { prop ->
	                'xa-datasource-property'(name: prop.getName(), prop.getValue())
	            }
	            'xa-datasource-class'(getDsXADatasourceClass())
	            driver(dsDriver)
	            security() {
	                'user-name'(dsUsername)
	                password(dsPassword)
	            }
                buildValidation builder
	        }
		}
    }
}

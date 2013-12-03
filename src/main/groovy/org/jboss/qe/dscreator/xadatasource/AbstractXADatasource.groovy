package org.jboss.qe.dscreator.xadatasource

import groovy.xml.MarkupBuilder

/**
 * @author Martin Simka
 */
abstract class AbstractXADatasource<T extends AbstractXADatasource<T>> implements XADatasource {
    private String name;
    private String jndiName
    private String poolName
    private Boolean enabled
    private String driver
    private String username
    private String password
    private String xaDatasourceClass
    private List<XADatasourceProperty> xaDatasourceProperties

    protected AbstractXADatasource() {
        xaDatasourceProperties = new ArrayList<XADatasourceProperty>()
    }

    protected abstract T me();

    @Override
    String getName() {
        return name
    }

    T name(String name) {
        this.name = name
        return me()
    }

    @Override
    String getJndiName() {
        return jndiName
    }

    T jndiName(String jndiName) {
        if(jndiName == null) {
            throw new NullPointerException()
        }
        this.jndiName = jndiName
        return me()
    }

    @Override
    String getPoolName() {
        return poolName
    }

    T poolName(String poolName) {
        if(poolName == null) {
            throw new NullPointerException()
        }
        this.poolName = poolName
        return me()
    }

    @Override
    String getUsername() {
        return username
    }

    T username(String username) {
        if(username == null) {
            throw new NullPointerException()
        }
        this.username = username
        return me()
    }

    @Override
    String getPassword() {
        return password
    }

    T password(String password) {
        if(password == null) {
            throw new NullPointerException()
        }
        this.password = password
        return me()
    }

    @Override
    String getDriver() {
        return driver
    }

    T driver(String driver) {
        if(driver == null) {
            throw new NullPointerException()
        }
        this.driver = driver
        return me()
    }

    @Override
    String getXADatasourceClass() {
        return xaDatasourceClass
    }

    T xaDatasourceClass(String xaDatasourceClass) {
        if(xaDatasourceClass == null) {
            throw new NullPointerException()
        }
        this.xaDatasourceClass = xaDatasourceClass
        return me()
    }

    @Override
    Boolean getEnabled() {
        return enabled
    }

    T enabled(boolean enabled) {
        this.enabled = enabled
        return me()
    }

    @Override
    List<XADatasourceProperty> getXADatasourceProperties() {
        return xaDatasourceProperties
    }

    T xaDatasourceProperty(XADatasourceProperty xaDatasourceProperty) {
        if(xaDatasourceProperty == null) {
            throw new NullPointerException()
        }
        xaDatasourceProperties.add(xaDatasourceProperty)
        return me()
    }

    @Override
    String toXml() {
        StringWriter sb = new StringWriter()
        MarkupBuilder xml = new MarkupBuilder(sb)
        xml.doubleQuotes = true
        xml.'xa-datasource'("jndi-name": jndiName, "pool-name": poolName, enabled: enabled, name: name) {
            getXADatasourceProperties().each { prop ->
                'xa-datasource-property'(name: prop.getName(), prop.getValue()){}
            }
            'xa-datasource-class'(getXADatasourceClass()) {}
            driver(driver){}
            security() {
                'user-name'(username){}
                password(password){}
            }
        }
        return sb.toString()
    }
}

package org.jboss.qe.dscreator.datasource

import groovy.xml.MarkupBuilder

/**
 * @author Martin Simka
 */
abstract class AbstractDatasource<T extends AbstractDatasource<T>> implements Datasource{
    private String name;
    private String jndiName
    private String poolName
    private Boolean useJavaContext
    private Boolean enabled
    private Boolean jta
    private String driver
    private String username
    private String password
    private String connectionUrl

    protected abstract T me();

    @Override
    public String getName() {
        return name
    }

    T name(String name) {
        this.name = name
        return me()
    }

    @Override
    public String getJndiName() {
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
    public String getPoolName() {
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
    public String getConnectionUrl() {
        return connectionUrl
    }

    T connectionUrl(String connectionUrl) {
        if(connectionUrl == null) {
            throw new NullPointerException()
        }
        this.connectionUrl = connectionUrl
        return me()
    }

    @Override
    public String getDriver() {
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
    public String getUsername() {
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
    public String getPassword() {
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
    public Boolean getUseJavaContext() {
        return useJavaContext
    }

    T useJavaContext(boolean useJavaContext) {
        this.useJavaContext = useJavaContext
        return me();
    }

    @Override
    public Boolean getEnabled() {
        return enabled
    }

    T enabled(boolean enabled) {
        this.enabled = enabled
        return me()
    }

    @Override
    public Boolean getJta() {
        return jta
    }

    T jta(boolean jta) {
        this.jta = jta
        return me()
    }

    public String toXml() {
        StringWriter sb = new StringWriter()
        MarkupBuilder xml = new MarkupBuilder(sb)
        xml.doubleQuotes = true
        xml.datasource(jta: jta, "jndi-name": jndiName, "pool-name": poolName, enabled: enabled, "use-java-context": useJavaContext, name : name) {
            'connection-url'(connectionUrl){}
            driver(driver){}
            security() {
                'user-name'(username){}
                password(password){}
            }
        }
        return sb.toString()
    }
}

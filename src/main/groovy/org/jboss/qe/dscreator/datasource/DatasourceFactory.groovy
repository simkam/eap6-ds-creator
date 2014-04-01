package org.jboss.qe.dscreator.datasource

import org.jboss.qe.dscreator.common.Utils;

/**
 * @author Martin Simka
 */
class DatasourceFactory {

    public static Datasource createDatasource(String dbAllocatorPropertiesPath, String name, String jndiName, String driver) {
        Properties loadedProps = Utils.loadDbAllocatorProperties(dbAllocatorPropertiesPath);
        return createDatasource(loadedProps, name, jndiName, driver);
    }
    
    public static Datasource createDatasource(Properties dbAllocatorProperties, String name, String jndiName, String driver) {
        if(dbAllocatorProperties["db.primary_label"] != null) {
            //add specific implementation
            return createGenericDatabaseDatasource(dbAllocatorProperties, name, jndiName, driver)
        }
    }

    public static Datasource createDatasource(String databaseFamily, String name, String jndiName, String driver, String connectionUrl, String username, String password, boolean jta, boolean useJavaContext) {
        if(databaseFamily.equals("add family")) {
            //add specific implementation
        } else {
            return createGenericDatabaseDatasource(name, jndiName, driver, connectionUrl, username, password, jta, useJavaContext)
        }
    }


    private static GenericDatabaseDatasource createGenericDatabaseDatasource(Properties props, String name, String jndiName, String driver) {
        GenericDatabaseDatasource ds = new GenericDatabaseDatasource()
                .dsConnectionUrl(props.getProperty("db.jdbc_url"))
                .dsUsername(props.getProperty("db.username"))
                .dsPassword(props.getProperty("db.password"))
                .dsJta(true)
                .dsPoolName(name) // name is set as poolname - jboss-cli.sh uses this in the same way
                .dsDriver(driver)
                .dsEnabled(true)
                .dsUseJavaContext(true)
                .dsJndiName(jndiName)
        return ds
    }

    private static GenericDatabaseDatasource createGenericDatabaseDatasource(String name, String jndiName, String driver, String connnectionUrl, String username, String password, boolean jta, boolean useJavaContext) {
        GenericDatabaseDatasource ds = new GenericDatabaseDatasource()
                .dsConnectionUrl(connnectionUrl)
                .dsUsername(username)
                .dsPassword(password)
                .dsJta(jta)
                .dsPoolName(name)
                .dsDriver(driver)
                .dsEnabled(true)
                .dsUseJavaContext(useJavaContext)
                .dsJndiName(jndiName)
        return ds
    }
}

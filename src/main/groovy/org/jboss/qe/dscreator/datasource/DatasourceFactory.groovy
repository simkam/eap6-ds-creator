package org.jboss.qe.dscreator.datasource

/**
 * @author Martin Simka
 */
class DatasourceFactory {

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
                .name(name)
                .connectionUrl(props.getProperty("db.jdbc_url"))
                .username(props.getProperty("db.username"))
                .password(props.getProperty("db.password"))
                .jta(true)
                .poolName(name + "_pool")
                .driver(driver)
                .enabled(true)
                .useJavaContext(true)
                .jndiName(jndiName)
        return ds
    }

    private static GenericDatabaseDatasource createGenericDatabaseDatasource(String name, String jndiName, String driver, String connnectionUrl, String username, String password, boolean jta, boolean useJavaContext) {
        GenericDatabaseDatasource ds = new GenericDatabaseDatasource()
                .name(name)
                .connectionUrl(connnectionUrl)
                .username(username)
                .password(password)
                .jta(jta)
                .poolName(name + "_pool")
                .driver(driver)
                .enabled(true)
                .useJavaContext(useJavaContext)
                .jndiName(jndiName)
        return ds
    }
}

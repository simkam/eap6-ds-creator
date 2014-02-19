package org.jboss.qe.dscreator.xadatasource


/**
 * @author Martin Simka
 */
class XADatasourceFactory {
    public static XADatasource createXADatasource(Properties dbAllocatorProperties, String name, String jndiName, String driver) {
        if (dbAllocatorProperties["db.primary_label"] != null) {
            String label = dbAllocatorProperties["db.primary_label"]
            if (label.startsWith("mysql")) {
                return createMysqlXADatasource(dbAllocatorProperties, name, jndiName, driver)
            }
            if (label.startsWith("oracle")) {
                return createOracleXADatasource(dbAllocatorProperties, name, jndiName, driver)
            }
            if (label.startsWith("db2")) {
                return createDB2XADatasource(dbAllocatorProperties, name, jndiName, driver)
            }
            if (label.startsWith("mssql")) {
                return createMSSqlXADatasource(dbAllocatorProperties, name, jndiName, driver)
            }
            if (label.startsWith("postgresql")) {
                return createPostgreSqlXADatasource(dbAllocatorProperties, name, jndiName, driver)
            }
            if (label.startsWith("postgresplus")) {
                return createPostgreSqlPlusXADatasource(dbAllocatorProperties, name, jndiName, driver)
            }
            //add specific implementation
            return createGenericDatabaseXADatasource(dbAllocatorProperties, name, jndiName, driver)
        }
    }

    public static XADatasource createXADatasource(String name, String jndiName, String driver, String username, String password, String xaDatasourceClass, List<XADatasourceProperty> xaProperties) {
        GenericDatabaseXADatasource ds = new GenericDatabaseXADatasource()
                .dsDriver(driver)
                .dsEnabled(true)
                .dsJndiName(jndiName)
                .dsName(name)
                .dsPoolName(name + "_pool")
                .dsUsername(username)
                .dsPassword(password)
                .dsXaDatasourceClass(xaDatasourceClass)
        xaProperties.each { XADatasourceProperty prop -> ds.dsXaDatasourceProperty(prop)}
        return ds
    }

    private static GenericDatabaseXADatasource createGenericDatabaseXADatasource(Properties dbAllocatorProperties, String name, String jndiName, String driver) {
        return new GenericDatabaseXADatasource()
                .url(dbAllocatorProperties["db.jdbc_url"])
                .dsDriver(driver)
                .dsEnabled(true)
                .dsJndiName(jndiName)
                .dsName(name)
                .dsPoolName(name + "_pool")
                .dsUsername(dbAllocatorProperties["db.username"])
                .dsPassword(dbAllocatorProperties["db.password"])
                .dsXaDatasourceClass(dbAllocatorProperties["datasource.class.xa"])
    }

    private static MysqlXADatasource createMysqlXADatasource(Properties dbAllocatorProperties, String name, String jndiName, String driver) {
        return new MysqlXADatasource()
                .databaseName(dbAllocatorProperties["db.name"])
                .portNumber(dbAllocatorProperties["db.port"])
                .serverName(dbAllocatorProperties["db.hostname"])
                .dsDriver(driver)
                .dsEnabled(true)
                .dsJndiName(jndiName)
                .dsName(name)
                .dsPoolName(name + "_pool")
                .dsUsername(dbAllocatorProperties["db.username"])
                .dsPassword(dbAllocatorProperties["db.password"])
                .dsXaDatasourceClass(dbAllocatorProperties["datasource.class.xa"])
    }

    private static DB2XADatasource createDB2XADatasource(Properties dbAllocatorProperties, String name, String jndiName, String driver) {
        return new DB2XADatasource()
                .databaseName(dbAllocatorProperties["db.name"])
                .portNumber(dbAllocatorProperties["db.port"])
                .serverName(dbAllocatorProperties["db.hostname"])
                .dsDriver(driver)
                .dsEnabled(true)
                .dsJndiName(jndiName)
                .dsName(name)
                .dsPoolName(name + "_pool")
                .dsUsername(dbAllocatorProperties["db.username"])
                .dsPassword(dbAllocatorProperties["db.password"])
                .dsXaDatasourceClass(dbAllocatorProperties["datasource.class.xa"])
    }

    private static MSSqlXADatasource createMSSqlXADatasource(Properties dbAllocatorProperties, String name, String jndiName, String driver) {
        return new MSSqlXADatasource()
                .databaseName(dbAllocatorProperties["db.name"])
                .portNumber(dbAllocatorProperties["db.port"])
                .serverName(dbAllocatorProperties["db.hostname"])
                .dsDriver(driver)
                .dsEnabled(true)
                .dsJndiName(jndiName)
                .dsName(name)
                .dsPoolName(name + "_pool")
                .dsUsername(dbAllocatorProperties["db.username"])
                .dsPassword(dbAllocatorProperties["db.password"])
                .dsXaDatasourceClass(dbAllocatorProperties["datasource.class.xa"])
    }

    private static OracleXADatasource createOracleXADatasource(Properties dbAllocatorProperties, String name, String jndiName, String driver) {
        return new OracleXADatasource()
                .url(dbAllocatorProperties["db.jdbc_url"])
                .databaseName(dbAllocatorProperties["db.name"])
                .dsDriver(driver)
                .dsEnabled(true)
                .dsJndiName(jndiName)
                .dsName(name)
                .dsPoolName(name + "_pool")
                .dsUsername(dbAllocatorProperties["db.username"])
                .dsPassword(dbAllocatorProperties["db.password"])
                .dsXaDatasourceClass(dbAllocatorProperties["datasource.class.xa"])
    }

    private static PostgreSqlXADatasource createPostgreSqlXADatasource(Properties dbAllocatorProperties, String name, String jndiName, String driver) {
        return new PostgreSqlXADatasource()
                .databaseName(dbAllocatorProperties["db.name"])
                .portNumber(dbAllocatorProperties["db.port"])
                .serverName(dbAllocatorProperties["db.hostname"])
                .dsDriver(driver)
                .dsEnabled(true)
                .dsJndiName(jndiName)
                .dsName(name)
                .dsPoolName(name + "_pool")
                .dsUsername(dbAllocatorProperties["db.username"])
                .dsPassword(dbAllocatorProperties["db.password"])
                .dsXaDatasourceClass(dbAllocatorProperties["datasource.class.xa"])
    }

    private static PostgreSqlPlusXADatasource createPostgreSqlPlusXADatasource(Properties dbAllocatorProperties, String name, String jndiName, String driver) {
        return new PostgreSqlPlusXADatasource()
                .databaseName(dbAllocatorProperties["db.name"])
                .portNumber(dbAllocatorProperties["db.port"])
                .serverName(dbAllocatorProperties["db.hostname"])
                .dsDriver(driver)
                .dsEnabled(true)
                .dsJndiName(jndiName)
                .dsName(name)
                .dsPoolName(name + "_pool")
                .dsUsername(dbAllocatorProperties["db.username"])
                .dsPassword(dbAllocatorProperties["db.password"])
                .dsXaDatasourceClass(dbAllocatorProperties["datasource.class.xa"])
    }

}

package org.jboss.qe.dscreator

import org.jboss.qe.dscreator.datasource.Datasource
import org.jboss.qe.dscreator.datasource.DatasourceFactory

/**
 * @author Martin Simka
 */
class Main {

    public static void main(String[] args) {
        CliBuilder cli = new CliBuilder()
        cli.datasourceName(args: 1, argName: "datasource name", "datasource name")
        cli.jndiName(args: 1, argName: "jndi name", "jndi name")
        cli.driverName(args: 1, argName: "driver name", "driver name")
        cli.dballocatorProperties(args: 1, argName: "path", "properties file path")

        cli.connectionUrl(args: 1, argName: "jdbc url", "jdbc url")
        cli.username(args: 1, argName: "username", "username")
        cli.password(args: 1, argName: "password", "password")
        cli.jta(args: 1, argName: "jta", "jta", type: Boolean)
        cli.useJavaContext(args: 1, argName: "use java context", "use java context", type: Boolean)
        cli.databaseFamily(args: 1, argName: "database family", "database family")


        OptionAccessor opt = cli.parse(args)

        if(opt.jndiName && opt.driverName && opt.dballocatorProperties && opt.datasourceName) {
            Properties props = new Properties()
            File propertiesFile = new File((String)opt.dballocatorProperties)
            if(!propertiesFile.exists()) {
                System.err.println("File " + propertiesFile.absoluteFile + " doesn't exist")
                System.exit(500)
            }
            propertiesFile.withInputStream {
                stream -> props.load(stream)
            }
            Datasource datasource = DatasourceFactory.createDatasource(props, (String) opt.datasourceName, (String) opt.jndiName, (String) opt.driverName)
            print datasource.toXml()
        } else if(opt.databaseFamily && opt.datasourceName && opt.jndiName && opt.driverName && opt.connectionUrl && opt.username && opt.password && opt.jta && opt.useJavaContext) {
           Datasource datasource = DatasourceFactory.createDatasource((String) opt.databaseFamily, (String) opt.datasourceName,
                   (String) opt.jndiName, (String) opt.driverName, (String) opt.connectionUrl, (String) opt.username,
                   (String) opt.password, (boolean) opt.jta, (boolean) opt.useJavaContext)
           print datasource.toXml()
        } else {
            printUsage()
        }
    }

    public static void printUsage() {
        print "Usage:\n\n" +
                "Dballocator:\n " +
                "-datasourceName <name> -jndiName <name> -driverName <driver> -dballocatorProperties <path>\n\n" +
                "Attributes:\n" +
                "-datasourceName <name> -jndiName <name> -driverName <driver> -connectionUrl <jdbc url> -jta <true/false> -useJava"
    }
}

package org.jboss.qe.dscreator

import groovy.xml.XmlUtil;

import org.jboss.qe.dscreator.common.XMLFormattable;
import org.jboss.qe.dscreator.common.XMLFormatter;
import org.jboss.qe.dscreator.datasource.Datasource
import org.jboss.qe.dscreator.datasource.DatasourceFactory
import org.jboss.qe.dscreator.xadatasource.XADatasource
import org.jboss.qe.dscreator.xadatasource.XADatasourceFactory
import org.jboss.qe.dscreator.xadatasource.XADatasourceProperty

/**
 * @author Martin Simka
 */
class Main {

    public static void main(String[] args) {
        CliBuilder cli = new CliBuilder();
		cli.with {
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
	        cli.xa("xa datasource")
	        cli.xaDatasourceClass("xa datasource class")
	        cli.xaProps(args: 1, argName: "xa properties=value pair separated by comma", "xa property name and value")
			cli.out(args:1, argName: "path of output file", "output file where data will be printed instead of standard output")
		}

        OptionAccessor opt = cli.parse(args)
		
		XMLFormattable datasourceOutput = null;
		
		// STANDARD datasource
        if (!opt.xa) {
            if (opt.jndiName && opt.driverName && opt.dballocatorProperties && opt.datasourceName) {
                datasourceOutput = DatasourceFactory.createDatasource(loadDbAllocatorProperties(opt.dballocatorProperties), (String) opt.datasourceName, (String) opt.jndiName, (String) opt.driverName)
            } else if (opt.databaseFamily && opt.datasourceName && opt.jndiName && opt.driverName && opt.connectionUrl && opt.username && opt.password && opt.jta && opt.useJavaContext) {
                datasourceOutput = DatasourceFactory.createDatasource((String) opt.databaseFamily, (String) opt.datasourceName,
                        (String) opt.jndiName, (String) opt.driverName, (String) opt.connectionUrl, (String) opt.username,
                        (String) opt.password, (boolean) opt.jta, (boolean) opt.useJavaContext)
            }
        }
		
		// XA datasource
		if (opt.xa) {
            if (opt.dballocatorProperties && opt.datasourceName && opt.jndiName && opt.driverName) {
                datasourceOutput = XADatasourceFactory.createXADatasource(loadDbAllocatorProperties(opt.dballocatorProperties), (String) opt.datasourceName, (String) opt.jndiName, (String) opt.driverName)
            } else if(opt.datasourceName && opt.jndiName && opt.driverName && opt.username && opt.password && opt.xaDatasourceClass) {
                List<XADatasourceProperty> xaProps = new ArrayList<XADatasourceProperty>();
                if(opt.xaProps) {
                    String propsString = (String) opt.xaProps
                    String[] props = propsString.split(",")
                    props.each { String prop ->
                        prop = prop.trim()
                        String[] nameValue = prop.split("=")
                        xaProps.add(new XADatasourceProperty(nameValue[0].trim(), nameValue[1].trim()))
                    }
                    datasourceOutput = XADatasourceFactory.createXADatasource((String) opt.datasourceName, (String) opt.jndiName,
                            (String) opt.driverName, (String) opt.username ,(String) opt.password, (String) opt.xaDatasourceClass, xaProps)
                }
            }
        }
		
		XMLFormatter formatter = new XMLFormatter();
		
		if(datasourceOutput == null) {
			// conditions for creating datasource were not fullfiled
			printUsage();
		} else if (opt.out) {
			// printing to file
			FileWriter fileWriter = new FileWriter(opt.out)
			def xmlOut = XmlUtil.serialize("\n<datasources>" + datasourceOutput.toXml() + "</datasources>");
            fileWriter.write(xmlOut)
            fileWriter.flush()
		} else {
			println formatter.prettyXML(datasourceOutput.toXml())
		}
    }
	
	private static Properties loadDbAllocatorProperties(String dbAllocPropsFile) {
		Properties props = new Properties()
		File propertiesFile = new File((String) dbAllocPropsFile)
		if (!propertiesFile.exists()) {
			System.err.println("File " + propertiesFile.absoluteFile + " doesn't exist")
			System.exit(500)
		}
		propertiesFile.withInputStream {
			stream -> props.load(stream)
		}
		return props
	} 

    public static void printUsage() {
        print "Usage:\n\n" +
                "Datasource:\n-------------\n" +
				"General optional arguments \n" +
				"-out filename\n" +
				"\n" +
                "Dballocator:\n " +
                "-datasourceName <name> -jndiName <name> -driverName <driver> -dballocatorProperties <path>\n\n" +
                "Attributes:\n" +
                "-databaseFamily <family> -datasourceName <name> -jndiName <name> -driverName <driver> -connectionUrl <jdbc url> -username <username> -password <password> -jta <true/false> -useJavaContext <true/false>\n\n" +
                "XA Datasource:\n-------------\n" +
                "Dballocator:\n " +
                "-xa -datasourceName <name> -jndiName <name> -driverName <driver> -dballocatorProperties <path>\n\n" +
                "Attributes:\n" +
                "-xa -datasourceName <name> -jndiName <name> -driverName <driver> -username <username> -password <password> -xaDatasourceClass <xa-datasource-class> -xaProps <name=value,name1=value1...>\n"
    }
}

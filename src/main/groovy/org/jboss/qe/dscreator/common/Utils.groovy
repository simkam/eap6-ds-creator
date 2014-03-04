package org.jboss.qe.dscreator.common

import java.util.Properties;

class Utils {
    
    /**
     * Loading java.util.Properties from file and putting them to Properties instance.
     * 
     * @param dbAllocPropsFile  path to properties file
     * @return  properties instance
     */
    public static Properties loadDbAllocatorProperties(String dbAllocPropsFile) {
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
}

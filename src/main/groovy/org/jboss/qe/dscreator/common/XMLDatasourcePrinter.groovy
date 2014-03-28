package org.jboss.qe.dscreator.common

import groovy.xml.XmlUtil;

/**
 * Class with utility methods to print xml string to file or something.
 */
class XMLDatasourcePrinter {
    /**
     * Printing xml datasource to -ds.xml file.
     *   
     * @param xmlFormat datasource created by datasource factory which implements methods of XMLFormattable and returns xml as string  
     * @param outFile  where to save the ds.xml file
     */
    def printToFile(XMLFormattable xmlFormat, String outFile) {
        FileWriter fileWriter = new FileWriter(outFile)
        def xmlOut = XmlUtil.serialize("\n<datasources>" + xmlFormat.toXml() + "</datasources>");
        fileWriter.write(xmlOut)
        fileWriter.flush()
    }

    /**
     * Taking the datasource and putting it inside of the standalone.xml configuration.
     *     
     * @param xmlFormat  datasource created by datasource factory which implements methods of XMLFormattable and returns xml as string
     * @param standaloneXmlFile  path to standalone.xml file
     * @param datasourceName  name of datasource which will be added to config xml file
     * @param isXA  is the datasource xa or not
     */
    def printToStandaloneXml(XMLFormattable xmlFormat, String standaloneXmlFile, String datasourceName, boolean isXA) {
        def standaloneXmlNode = new XmlParser(false, true).parseText(new File(standaloneXmlFile).text)
        def datasourceNode = new XmlParser(false, true).parseText(xmlFormat.toXml())

        // we want to have new datasource if it's not added yet
        String datasourceType = isXA ? 'xa-datasource' : 'datasource'
        Node oldDatasourceNode = standaloneXmlNode.profile.subsystem.datasources."${datasourceType}".find{it.'@name' == datasourceName || it.'@pool-name' == datasourceName};
        /*if(oldDatasourceNode == null) {
            oldDatasourceNode = standaloneXmlNode.profile.subsystem.datasources."${datasourceType}".find{it.'@pool-name' == datasourceName};
        }*/
        
        if(oldDatasourceNode == null) {
            // note: not sure why but appendNode does strange things here
            println "Adding " << datasourceType << " " << datasourceName << " as a new child of subsystem.datasources"
            standaloneXmlNode.profile.subsystem.datasources[0]?.append(datasourceNode)
        } else {
            println "Replacing " << datasourceType << " " << datasourceName << " by a new content"
            oldDatasourceNode.replaceNode(datasourceNode)
        }
        
        // writing results back to config file (standalone.xml)
        new XmlNodePrinter(new PrintWriter(standaloneXmlFile)).print(standaloneXmlNode)
    }
}

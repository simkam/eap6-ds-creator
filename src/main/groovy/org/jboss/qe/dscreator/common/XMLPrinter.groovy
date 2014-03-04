package org.jboss.qe.dscreator.common

import groovy.xml.XmlUtil;

class XMLPrinter {
    def printToFile(XMLFormattable xmlFormat, String outFile) {
        FileWriter fileWriter = new FileWriter(outFile)
        def xmlOut = XmlUtil.serialize("\n<datasources>" + xmlFormat.toXml() + "</datasources>");
        fileWriter.write(xmlOut)
        fileWriter.flush()
    }
    
    def printToStandaloneXml(XMLFormattable xmlFormat, String standaloneXmlFile, String datasourceName, boolean isXA) {
        def standaloneXmlNode = new XmlParser(false, true).parseText(new File(standaloneXmlFile).text)
        def datasourceNode = new XmlParser(false, true).parseText(xmlFormat.toXml())

        // we want to have new datasource if it's not added yet
        String datasourceType = isXA ? 'xa-datasource' : 'datasource'
        if(standaloneXmlNode.profile.subsystem.datasources."${datasourceType}".find{it.'@name' == datasourceName} == null) {
            // warn: not sure why but appendNode does strange things here
            standaloneXmlNode.profile.subsystem.datasources[0]?.append(datasourceNode)
         }
        
        // writing results back to config file (standalone.xml)
        new XmlNodePrinter(new PrintWriter(standaloneXmlFile)).print(standaloneXmlNode)
    }
}

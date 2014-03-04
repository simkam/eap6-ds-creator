package org.jboss.qe.dscreator.common

import groovy.xml.StreamingMarkupBuilder

class XMLFormatter {

    /** 
     * Taking XML Object used by StreamingMarkupBuilder and transforming it to XML string.
     * @param xmlObject  xml object from StreamingMarkupBuilderStreamingMarkupBuilder
     * @return pretiffied xml string
     */
	def String getSimpleXML(xmlObject) {
		return prettyXML(transformToXML(xmlObject))
	}
	
    /**
     * From XML object via StreamingMarkupBuilder to XML String.
     * @param xmlObject  xml type object
     * @return  xml string
     */
	def String transformToXML(xmlObject) {
		def markupBuilder = new StreamingMarkupBuilder()
		markupBuilder.encoding = "UTF-8"
		markupBuilder.useDoubleQuotes = true
		return markupBuilder.bind(xmlObject)
	}
	
    /**
     * Get xml string and format it to be nicer.
     * 
     * @param xmlString  string containing xml document
     * @return  prettier xml document in string
     */
	def String prettyXML(String xmlString) {
		if(xmlString) {
			def stringWriter = new StringWriter()
			def node = new XmlParser().parseText(xmlString);
			new XmlNodePrinter(new PrintWriter(stringWriter)).print(node)
			return stringWriter.toString()
		} else {
			return ""
		}
	}
}

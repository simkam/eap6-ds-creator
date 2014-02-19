package org.jboss.qe.dscreator.common

import groovy.xml.StreamingMarkupBuilder

class XMLFormatter {
	def String getSimpleXML(xmlObject) {
		return prettyXML(transformToXML(xmlObject))
	}
	
	def String transformToXML(xmlObject) {
		def markupBuilder = new StreamingMarkupBuilder()
		markupBuilder.encoding = "UTF-8"
		markupBuilder.useDoubleQuotes = true
		return markupBuilder.bind(xmlObject)
	}
	
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

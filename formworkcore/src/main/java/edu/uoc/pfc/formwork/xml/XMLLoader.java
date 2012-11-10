package edu.uoc.pfc.formwork.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;


public class XMLLoader {
	private XMLLoader() {
	
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T parseFile(Class<T> docClass, InputStream thePage) 
				throws JAXBException, SAXException {
		String packageName = docClass.getPackage().getName();
		JAXBContext context = JAXBContext.newInstance(packageName);
		
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(XMLLoader.class.getResource("/META-INF/formwork.xsd")); 

//		Marshaller marshaller = jaxbContext.createMarshaller();
//		marshaller.setSchema(schema);
//		marshaller.marshal(objectToMarshal, new DefaultHandler());		
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setSchema(schema);
		JAXBElement<T> doc = (JAXBElement<T>) unmarshaller.unmarshal(thePage);
		return doc.getValue();
	}
	
	public static <T> T  parseFile(Class<T> docClass, URL thePage) throws JAXBException, IOException, SAXException {
		return parseFile(docClass, thePage.openStream());
	}
}

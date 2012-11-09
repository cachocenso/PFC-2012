package edu.uoc.pfc.formwork.xml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLLoader {
	private XMLLoader() {
	
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T parseFile(Class<T> docClass, InputStream thePage) 
				throws JAXBException {
		String packageName = docClass.getPackage().getName();
		JAXBContext context = JAXBContext.newInstance(packageName);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		JAXBElement<T> doc = (JAXBElement<T>) unmarshaller.unmarshal(thePage);
		return doc.getValue();
	}
	
	public static <T> T  parseFile(Class<T> docClass, URL thePage) throws JAXBException, IOException {
		return parseFile(docClass, thePage.openStream());
	}
}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.09 at 12:31:36 PM CET 
//


package edu.uoc.pfc.formwork.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.uoc.pfc.formwork.xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Formulario_QNAME = new QName("", "formulario");
    private final static QName _Fw_QNAME = new QName("", "fw");
    private final static QName _Apartado_QNAME = new QName("", "apartado");
    private final static QName _Partida_QNAME = new QName("", "partida");
    private final static QName _Rules_QNAME = new QName("", "rules");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.uoc.pfc.formwork.xml
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TipoFormulario }
     * 
     */
    public TipoFormulario createTipoFormulario() {
        return new TipoFormulario();
    }

    /**
     * Create an instance of {@link TipoFW }
     * 
     */
    public TipoFW createTipoFW() {
        return new TipoFW();
    }

    /**
     * Create an instance of {@link TipoApartado }
     * 
     */
    public TipoApartado createTipoApartado() {
        return new TipoApartado();
    }

    /**
     * Create an instance of {@link TipoPartida }
     * 
     */
    public TipoPartida createTipoPartida() {
        return new TipoPartida();
    }

    /**
     * Create an instance of {@link TipoRules }
     * 
     */
    public TipoRules createTipoRules() {
        return new TipoRules();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoFormulario }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "formulario")
    public JAXBElement<TipoFormulario> createFormulario(TipoFormulario value) {
        return new JAXBElement<TipoFormulario>(_Formulario_QNAME, TipoFormulario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoFW }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fw")
    public JAXBElement<TipoFW> createFw(TipoFW value) {
        return new JAXBElement<TipoFW>(_Fw_QNAME, TipoFW.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoApartado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "apartado")
    public JAXBElement<TipoApartado> createApartado(TipoApartado value) {
        return new JAXBElement<TipoApartado>(_Apartado_QNAME, TipoApartado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoPartida }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "partida")
    public JAXBElement<TipoPartida> createPartida(TipoPartida value) {
        return new JAXBElement<TipoPartida>(_Partida_QNAME, TipoPartida.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoRules }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "rules")
    public JAXBElement<TipoRules> createRules(TipoRules value) {
        return new JAXBElement<TipoRules>(_Rules_QNAME, TipoRules.class, null, value);
    }

}

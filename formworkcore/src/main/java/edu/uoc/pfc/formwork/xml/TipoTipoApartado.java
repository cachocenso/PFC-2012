//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.22 at 08:49:13 PM CET 
//


package edu.uoc.pfc.formwork.xml;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoTipoApartado.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoTipoApartado">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="identificacion"/>
 *     &lt;enumeration value="devengo"/>
 *     &lt;enumeration value="partidas"/>
 *     &lt;enumeration value="pago"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoTipoApartado")
@XmlEnum
public enum TipoTipoApartado {

    @XmlEnumValue("identificacion")
    IDENTIFICACION("identificacion"),
    @XmlEnumValue("devengo")
    DEVENGO("devengo"),
    @XmlEnumValue("partidas")
    PARTIDAS("partidas"),
    @XmlEnumValue("pago")
    PAGO("pago");
    private final String value;

    TipoTipoApartado(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoTipoApartado fromValue(String v) {
        for (TipoTipoApartado c: TipoTipoApartado.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}


package org.datacontract.schemas._2004._07.wslayer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DailyResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DailyResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Fact" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="NameId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DailyResponse", propOrder = {
    "fact",
    "nameId"
})
public class DailyResponse {

    @XmlElement(name = "Fact")
    protected Long fact;
    @XmlElement(name = "NameId")
    protected Integer nameId;

    /**
     * Gets the value of the fact property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFact() {
        return fact;
    }

    /**
     * Sets the value of the fact property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFact(Long value) {
        this.fact = value;
    }

    /**
     * Gets the value of the nameId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNameId() {
        return nameId;
    }

    /**
     * Sets the value of the nameId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNameId(Integer value) {
        this.nameId = value;
    }

}

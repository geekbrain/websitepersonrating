
package org.datacontract.schemas._2004._07.wslayer;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatisticResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatisticResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Fact" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="PageURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatisticResponse", propOrder = {
    "fact",
    "pageURL"
})
public class StatisticResponse {

    @XmlElement(name = "Fact")
    protected Integer fact;
    @XmlElementRef(name = "PageURL", namespace = "http://schemas.datacontract.org/2004/07/WSLayer", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pageURL;

    /**
     * Gets the value of the fact property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFact() {
        return fact;
    }

    /**
     * Sets the value of the fact property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFact(Integer value) {
        this.fact = value;
    }

    /**
     * Gets the value of the pageURL property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPageURL() {
        return pageURL;
    }

    /**
     * Sets the value of the pageURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPageURL(JAXBElement<String> value) {
        this.pageURL = value;
    }

}

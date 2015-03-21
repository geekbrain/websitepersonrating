
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfSiteResponse;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetSitesResult" type="{http://schemas.datacontract.org/2004/07/WSLayer}ArrayOfSiteResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getSitesResult"
})
@XmlRootElement(name = "GetSitesResponse")
public class GetSitesResponse {

    @XmlElementRef(name = "GetSitesResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfSiteResponse> getSitesResult;

    /**
     * Gets the value of the getSitesResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSiteResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfSiteResponse> getGetSitesResult() {
        return getSitesResult;
    }

    /**
     * Sets the value of the getSitesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfSiteResponse }{@code >}
     *     
     */
    public void setGetSitesResult(JAXBElement<ArrayOfSiteResponse> value) {
        this.getSitesResult = value;
    }

}

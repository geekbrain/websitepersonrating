
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfDailyResponse;


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
 *         &lt;element name="GetDailyInfoResult" type="{http://schemas.datacontract.org/2004/07/WSLayer}ArrayOfDailyResponse" minOccurs="0"/>
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
    "getDailyInfoResult"
})
@XmlRootElement(name = "GetDailyInfoResponse")
public class GetDailyInfoResponse {

    @XmlElementRef(name = "GetDailyInfoResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfDailyResponse> getDailyInfoResult;

    /**
     * Gets the value of the getDailyInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDailyResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDailyResponse> getGetDailyInfoResult() {
        return getDailyInfoResult;
    }

    /**
     * Sets the value of the getDailyInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDailyResponse }{@code >}
     *     
     */
    public void setGetDailyInfoResult(JAXBElement<ArrayOfDailyResponse> value) {
        this.getDailyInfoResult = value;
    }

}

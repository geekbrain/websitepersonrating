
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfStatisticResponse;


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
 *         &lt;element name="GetStatisticInfoResult" type="{http://schemas.datacontract.org/2004/07/WSLayer}ArrayOfStatisticResponse" minOccurs="0"/>
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
    "getStatisticInfoResult"
})
@XmlRootElement(name = "GetStatisticInfoResponse")
public class GetStatisticInfoResponse {

    @XmlElementRef(name = "GetStatisticInfoResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfStatisticResponse> getStatisticInfoResult;

    /**
     * Gets the value of the getStatisticInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfStatisticResponse }{@code >}
     *     
     */
    public JAXBElement<ArrayOfStatisticResponse> getGetStatisticInfoResult() {
        return getStatisticInfoResult;
    }

    /**
     * Sets the value of the getStatisticInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfStatisticResponse }{@code >}
     *     
     */
    public void setGetStatisticInfoResult(JAXBElement<ArrayOfStatisticResponse> value) {
        this.getStatisticInfoResult = value;
    }

}

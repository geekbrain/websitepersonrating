
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfTaskRequest;


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
 *         &lt;element name="RequestTaskResult" type="{http://schemas.datacontract.org/2004/07/WSLayer}ArrayOfTaskRequest" minOccurs="0"/>
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
    "requestTaskResult"
})
@XmlRootElement(name = "RequestTaskResponse")
public class RequestTaskResponse {

    @XmlElementRef(name = "RequestTaskResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfTaskRequest> requestTaskResult;

    /**
     * Gets the value of the requestTaskResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTaskRequest }{@code >}
     *     
     */
    public JAXBElement<ArrayOfTaskRequest> getRequestTaskResult() {
        return requestTaskResult;
    }

    /**
     * Sets the value of the requestTaskResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfTaskRequest }{@code >}
     *     
     */
    public void setRequestTaskResult(JAXBElement<ArrayOfTaskRequest> value) {
        this.requestTaskResult = value;
    }

}

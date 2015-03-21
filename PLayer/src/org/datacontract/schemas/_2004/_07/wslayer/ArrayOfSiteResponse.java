
package org.datacontract.schemas._2004._07.wslayer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSiteResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSiteResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SiteResponse" type="{http://schemas.datacontract.org/2004/07/WSLayer}SiteResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSiteResponse", propOrder = {
    "siteResponse"
})
public class ArrayOfSiteResponse {

    @XmlElement(name = "SiteResponse", nillable = true)
    protected List<SiteResponse> siteResponse;

    /**
     * Gets the value of the siteResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the siteResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSiteResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SiteResponse }
     * 
     * 
     */
    public List<SiteResponse> getSiteResponse() {
        if (siteResponse == null) {
            siteResponse = new ArrayList<SiteResponse>();
        }
        return this.siteResponse;
    }

}

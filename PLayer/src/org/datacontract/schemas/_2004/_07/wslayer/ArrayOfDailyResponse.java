
package org.datacontract.schemas._2004._07.wslayer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDailyResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDailyResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DailyResponse" type="{http://schemas.datacontract.org/2004/07/WSLayer}DailyResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDailyResponse", propOrder = {
    "dailyResponse"
})
public class ArrayOfDailyResponse {

    @XmlElement(name = "DailyResponse", nillable = true)
    protected List<DailyResponse> dailyResponse;

    /**
     * Gets the value of the dailyResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dailyResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDailyResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DailyResponse }
     * 
     * 
     */
    public List<DailyResponse> getDailyResponse() {
        if (dailyResponse == null) {
            dailyResponse = new ArrayList<DailyResponse>();
        }
        return this.dailyResponse;
    }

}

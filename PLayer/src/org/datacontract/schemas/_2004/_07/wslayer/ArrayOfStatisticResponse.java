
package org.datacontract.schemas._2004._07.wslayer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfStatisticResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfStatisticResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StatisticResponse" type="{http://schemas.datacontract.org/2004/07/WSLayer}StatisticResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfStatisticResponse", propOrder = {
    "statisticResponse"
})
public class ArrayOfStatisticResponse {

    @XmlElement(name = "StatisticResponse", nillable = true)
    protected List<StatisticResponse> statisticResponse;

    /**
     * Gets the value of the statisticResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statisticResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatisticResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StatisticResponse }
     * 
     * 
     */
    public List<StatisticResponse> getStatisticResponse() {
        if (statisticResponse == null) {
            statisticResponse = new ArrayList<StatisticResponse>();
        }
        return this.statisticResponse;
    }

}

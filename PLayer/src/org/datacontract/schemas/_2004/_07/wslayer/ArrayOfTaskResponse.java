
package org.datacontract.schemas._2004._07.wslayer;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfTaskResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfTaskResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TaskResponse" type="{http://schemas.datacontract.org/2004/07/WSLayer}TaskResponse" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfTaskResponse", propOrder = {
    "taskResponse"
})
public class ArrayOfTaskResponse {

    @XmlElement(name = "TaskResponse", nillable = true)
    protected List<TaskResponse> taskResponse;

    /**
     * Gets the value of the taskResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taskResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaskResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaskResponse }
     * 
     * 
     */
    public List<TaskResponse> getTaskResponse() {
        if (taskResponse == null) {
            taskResponse = new ArrayList<TaskResponse>();
        }
        return this.taskResponse;
    }

}

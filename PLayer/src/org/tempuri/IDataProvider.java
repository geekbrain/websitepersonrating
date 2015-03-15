
package org.tempuri;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfCommonResponse;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfDailyResponse;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfNameResponse;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfSiteResponse;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfStatisticResponse;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfTaskRequest;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfTaskResponse;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IDataProvider", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
    com.microsoft.schemas._2003._10.serialization.ObjectFactory.class,
    org.datacontract.schemas._2004._07.wslayer.ObjectFactory.class,
    org.tempuri.ObjectFactory.class
})
public interface IDataProvider {


    /**
     * 
     * @return
     *     returns org.datacontract.schemas._2004._07.wslayer.ArrayOfNameResponse
     */
    @WebMethod(operationName = "GetNames", action = "http://tempuri.org/IDataProvider/GetNames")
    @WebResult(name = "GetNamesResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "GetNames", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetNames")
    @ResponseWrapper(localName = "GetNamesResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetNamesResponse")
    public ArrayOfNameResponse getNames();

    /**
     * 
     * @return
     *     returns org.datacontract.schemas._2004._07.wslayer.ArrayOfSiteResponse
     */
    @WebMethod(operationName = "GetSites", action = "http://tempuri.org/IDataProvider/GetSites")
    @WebResult(name = "GetSitesResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "GetSites", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetSites")
    @ResponseWrapper(localName = "GetSitesResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetSitesResponse")
    public ArrayOfSiteResponse getSites();

    /**
     * 
     * @param names
     */
    @WebMethod(operationName = "SetNames", action = "http://tempuri.org/IDataProvider/SetNames")
    @RequestWrapper(localName = "SetNames", targetNamespace = "http://tempuri.org/", className = "org.tempuri.SetNames")
    @ResponseWrapper(localName = "SetNamesResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.SetNamesResponse")
    public void setNames(
        @WebParam(name = "names", targetNamespace = "http://tempuri.org/")
        ArrayOfNameResponse names);

    /**
     * 
     * @param sites
     */
    @WebMethod(operationName = "SetSites", action = "http://tempuri.org/IDataProvider/SetSites")
    @RequestWrapper(localName = "SetSites", targetNamespace = "http://tempuri.org/", className = "org.tempuri.SetSites")
    @ResponseWrapper(localName = "SetSitesResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.SetSitesResponse")
    public void setSites(
        @WebParam(name = "sites", targetNamespace = "http://tempuri.org/")
        ArrayOfSiteResponse sites);

    /**
     * 
     * @param names
     */
    @WebMethod(operationName = "DeleteNames", action = "http://tempuri.org/IDataProvider/DeleteNames")
    @RequestWrapper(localName = "DeleteNames", targetNamespace = "http://tempuri.org/", className = "org.tempuri.DeleteNames")
    @ResponseWrapper(localName = "DeleteNamesResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.DeleteNamesResponse")
    public void deleteNames(
        @WebParam(name = "names", targetNamespace = "http://tempuri.org/")
        ArrayOfNameResponse names);

    /**
     * 
     * @param sites
     */
    @WebMethod(operationName = "DeleteSites", action = "http://tempuri.org/IDataProvider/DeleteSites")
    @RequestWrapper(localName = "DeleteSites", targetNamespace = "http://tempuri.org/", className = "org.tempuri.DeleteSites")
    @ResponseWrapper(localName = "DeleteSitesResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.DeleteSitesResponse")
    public void deleteSites(
        @WebParam(name = "sites", targetNamespace = "http://tempuri.org/")
        ArrayOfSiteResponse sites);

    /**
     * 
     * @param siteId
     * @return
     *     returns org.datacontract.schemas._2004._07.wslayer.ArrayOfCommonResponse
     */
    @WebMethod(operationName = "GetCommonInfo", action = "http://tempuri.org/IDataProvider/GetCommonInfo")
    @WebResult(name = "GetCommonInfoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "GetCommonInfo", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetCommonInfo")
    @ResponseWrapper(localName = "GetCommonInfoResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetCommonInfoResponse")
    public ArrayOfCommonResponse getCommonInfo(
        @WebParam(name = "siteId", targetNamespace = "http://tempuri.org/")
        Integer siteId);

    /**
     * 
     * @param siteId
     * @return
     *     returns org.datacontract.schemas._2004._07.wslayer.ArrayOfDailyResponse
     */
    @WebMethod(operationName = "GetDailyInfo", action = "http://tempuri.org/IDataProvider/GetDailyInfo")
    @WebResult(name = "GetDailyInfoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "GetDailyInfo", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetDailyInfo")
    @ResponseWrapper(localName = "GetDailyInfoResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetDailyInfoResponse")
    public ArrayOfDailyResponse getDailyInfo(
        @WebParam(name = "siteId", targetNamespace = "http://tempuri.org/")
        Integer siteId);

    /**
     * 
     * @param endDate
     * @param numberPages
     * @param siteId
     * @param nameId
     * @param startDate
     * @return
     *     returns org.datacontract.schemas._2004._07.wslayer.ArrayOfStatisticResponse
     */
    @WebMethod(operationName = "GetStatisticInfo", action = "http://tempuri.org/IDataProvider/GetStatisticInfo")
    @WebResult(name = "GetStatisticInfoResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "GetStatisticInfo", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetStatisticInfo")
    @ResponseWrapper(localName = "GetStatisticInfoResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.GetStatisticInfoResponse")
    public ArrayOfStatisticResponse getStatisticInfo(
        @WebParam(name = "siteId", targetNamespace = "http://tempuri.org/")
        Integer siteId,
        @WebParam(name = "nameId", targetNamespace = "http://tempuri.org/")
        Integer nameId,
        @WebParam(name = "startDate", targetNamespace = "http://tempuri.org/")
        XMLGregorianCalendar startDate,
        @WebParam(name = "endDate", targetNamespace = "http://tempuri.org/")
        XMLGregorianCalendar endDate,
        @WebParam(name = "numberPages", targetNamespace = "http://tempuri.org/")
        Integer numberPages);

    /**
     * 
     * @return
     *     returns org.datacontract.schemas._2004._07.wslayer.ArrayOfTaskRequest
     */
    @WebMethod(operationName = "RequestTask", action = "http://tempuri.org/IDataProvider/RequestTask")
    @WebResult(name = "RequestTaskResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "RequestTask", targetNamespace = "http://tempuri.org/", className = "org.tempuri.RequestTask")
    @ResponseWrapper(localName = "RequestTaskResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.RequestTaskResponse")
    public ArrayOfTaskRequest requestTask();

    /**
     * 
     * @param tasks
     */
    @WebMethod(operationName = "ResponseTask", action = "http://tempuri.org/IDataProvider/ResponseTask")
    @RequestWrapper(localName = "ResponseTask", targetNamespace = "http://tempuri.org/", className = "org.tempuri.ResponseTask")
    @ResponseWrapper(localName = "ResponseTaskResponse", targetNamespace = "http://tempuri.org/", className = "org.tempuri.ResponseTaskResponse")
    public void responseTask(
        @WebParam(name = "tasks", targetNamespace = "http://tempuri.org/")
        ArrayOfTaskResponse tasks);

}
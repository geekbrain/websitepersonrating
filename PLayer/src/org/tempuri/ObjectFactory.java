
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfCommonResponse;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfDailyResponse;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfNameResponse;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfSiteResponse;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfStatisticResponse;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfTaskRequest;
import org.datacontract.schemas._2004._07.wslayer.ArrayOfTaskResponse;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.tempuri package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ResponseTaskTasks_QNAME = new QName("http://tempuri.org/", "tasks");
    private final static QName _GetCommonInfoResponseGetCommonInfoResult_QNAME = new QName("http://tempuri.org/", "GetCommonInfoResult");
    private final static QName _GetNamesResponseGetNamesResult_QNAME = new QName("http://tempuri.org/", "GetNamesResult");
    private final static QName _GetDailyInfoResponseGetDailyInfoResult_QNAME = new QName("http://tempuri.org/", "GetDailyInfoResult");
    private final static QName _DeleteSitesSites_QNAME = new QName("http://tempuri.org/", "sites");
    private final static QName _SetNamesNames_QNAME = new QName("http://tempuri.org/", "names");
    private final static QName _GetSitesResponseGetSitesResult_QNAME = new QName("http://tempuri.org/", "GetSitesResult");
    private final static QName _RequestTaskResponseRequestTaskResult_QNAME = new QName("http://tempuri.org/", "RequestTaskResult");
    private final static QName _GetStatisticInfoResponseGetStatisticInfoResult_QNAME = new QName("http://tempuri.org/", "GetStatisticInfoResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.tempuri
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetNames }
     * 
     */
    public GetNames createGetNames() {
        return new GetNames();
    }

    /**
     * Create an instance of {@link RequestTaskResponse }
     * 
     */
    public RequestTaskResponse createRequestTaskResponse() {
        return new RequestTaskResponse();
    }

    /**
     * Create an instance of {@link RequestTask }
     * 
     */
    public RequestTask createRequestTask() {
        return new RequestTask();
    }

    /**
     * Create an instance of {@link SetSites }
     * 
     */
    public SetSites createSetSites() {
        return new SetSites();
    }

    /**
     * Create an instance of {@link SetNames }
     * 
     */
    public SetNames createSetNames() {
        return new SetNames();
    }

    /**
     * Create an instance of {@link GetSites }
     * 
     */
    public GetSites createGetSites() {
        return new GetSites();
    }

    /**
     * Create an instance of {@link DeleteNamesResponse }
     * 
     */
    public DeleteNamesResponse createDeleteNamesResponse() {
        return new DeleteNamesResponse();
    }

    /**
     * Create an instance of {@link GetCommonInfoResponse }
     * 
     */
    public GetCommonInfoResponse createGetCommonInfoResponse() {
        return new GetCommonInfoResponse();
    }

    /**
     * Create an instance of {@link DeleteNames }
     * 
     */
    public DeleteNames createDeleteNames() {
        return new DeleteNames();
    }

    /**
     * Create an instance of {@link GetSitesResponse }
     * 
     */
    public GetSitesResponse createGetSitesResponse() {
        return new GetSitesResponse();
    }

    /**
     * Create an instance of {@link ResponseTask }
     * 
     */
    public ResponseTask createResponseTask() {
        return new ResponseTask();
    }

    /**
     * Create an instance of {@link GetStatisticInfo }
     * 
     */
    public GetStatisticInfo createGetStatisticInfo() {
        return new GetStatisticInfo();
    }

    /**
     * Create an instance of {@link SetSitesResponse }
     * 
     */
    public SetSitesResponse createSetSitesResponse() {
        return new SetSitesResponse();
    }

    /**
     * Create an instance of {@link DeleteSitesResponse }
     * 
     */
    public DeleteSitesResponse createDeleteSitesResponse() {
        return new DeleteSitesResponse();
    }

    /**
     * Create an instance of {@link GetDailyInfoResponse }
     * 
     */
    public GetDailyInfoResponse createGetDailyInfoResponse() {
        return new GetDailyInfoResponse();
    }

    /**
     * Create an instance of {@link GetStatisticInfoResponse }
     * 
     */
    public GetStatisticInfoResponse createGetStatisticInfoResponse() {
        return new GetStatisticInfoResponse();
    }

    /**
     * Create an instance of {@link SetNamesResponse }
     * 
     */
    public SetNamesResponse createSetNamesResponse() {
        return new SetNamesResponse();
    }

    /**
     * Create an instance of {@link ResponseTaskResponse }
     * 
     */
    public ResponseTaskResponse createResponseTaskResponse() {
        return new ResponseTaskResponse();
    }

    /**
     * Create an instance of {@link GetNamesResponse }
     * 
     */
    public GetNamesResponse createGetNamesResponse() {
        return new GetNamesResponse();
    }

    /**
     * Create an instance of {@link GetDailyInfo }
     * 
     */
    public GetDailyInfo createGetDailyInfo() {
        return new GetDailyInfo();
    }

    /**
     * Create an instance of {@link GetCommonInfo }
     * 
     */
    public GetCommonInfo createGetCommonInfo() {
        return new GetCommonInfo();
    }

    /**
     * Create an instance of {@link DeleteSites }
     * 
     */
    public DeleteSites createDeleteSites() {
        return new DeleteSites();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "tasks", scope = ResponseTask.class)
    public JAXBElement<ArrayOfTaskResponse> createResponseTaskTasks(ArrayOfTaskResponse value) {
        return new JAXBElement<ArrayOfTaskResponse>(_ResponseTaskTasks_QNAME, ArrayOfTaskResponse.class, ResponseTask.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCommonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetCommonInfoResult", scope = GetCommonInfoResponse.class)
    public JAXBElement<ArrayOfCommonResponse> createGetCommonInfoResponseGetCommonInfoResult(ArrayOfCommonResponse value) {
        return new JAXBElement<ArrayOfCommonResponse>(_GetCommonInfoResponseGetCommonInfoResult_QNAME, ArrayOfCommonResponse.class, GetCommonInfoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetNamesResult", scope = GetNamesResponse.class)
    public JAXBElement<ArrayOfNameResponse> createGetNamesResponseGetNamesResult(ArrayOfNameResponse value) {
        return new JAXBElement<ArrayOfNameResponse>(_GetNamesResponseGetNamesResult_QNAME, ArrayOfNameResponse.class, GetNamesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfDailyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetDailyInfoResult", scope = GetDailyInfoResponse.class)
    public JAXBElement<ArrayOfDailyResponse> createGetDailyInfoResponseGetDailyInfoResult(ArrayOfDailyResponse value) {
        return new JAXBElement<ArrayOfDailyResponse>(_GetDailyInfoResponseGetDailyInfoResult_QNAME, ArrayOfDailyResponse.class, GetDailyInfoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSiteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "sites", scope = DeleteSites.class)
    public JAXBElement<ArrayOfSiteResponse> createDeleteSitesSites(ArrayOfSiteResponse value) {
        return new JAXBElement<ArrayOfSiteResponse>(_DeleteSitesSites_QNAME, ArrayOfSiteResponse.class, DeleteSites.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSiteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "sites", scope = SetSites.class)
    public JAXBElement<ArrayOfSiteResponse> createSetSitesSites(ArrayOfSiteResponse value) {
        return new JAXBElement<ArrayOfSiteResponse>(_DeleteSitesSites_QNAME, ArrayOfSiteResponse.class, SetSites.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "names", scope = SetNames.class)
    public JAXBElement<ArrayOfNameResponse> createSetNamesNames(ArrayOfNameResponse value) {
        return new JAXBElement<ArrayOfNameResponse>(_SetNamesNames_QNAME, ArrayOfNameResponse.class, SetNames.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfSiteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetSitesResult", scope = GetSitesResponse.class)
    public JAXBElement<ArrayOfSiteResponse> createGetSitesResponseGetSitesResult(ArrayOfSiteResponse value) {
        return new JAXBElement<ArrayOfSiteResponse>(_GetSitesResponseGetSitesResult_QNAME, ArrayOfSiteResponse.class, GetSitesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfTaskRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "RequestTaskResult", scope = RequestTaskResponse.class)
    public JAXBElement<ArrayOfTaskRequest> createRequestTaskResponseRequestTaskResult(ArrayOfTaskRequest value) {
        return new JAXBElement<ArrayOfTaskRequest>(_RequestTaskResponseRequestTaskResult_QNAME, ArrayOfTaskRequest.class, RequestTaskResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfStatisticResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "GetStatisticInfoResult", scope = GetStatisticInfoResponse.class)
    public JAXBElement<ArrayOfStatisticResponse> createGetStatisticInfoResponseGetStatisticInfoResult(ArrayOfStatisticResponse value) {
        return new JAXBElement<ArrayOfStatisticResponse>(_GetStatisticInfoResponseGetStatisticInfoResult_QNAME, ArrayOfStatisticResponse.class, GetStatisticInfoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "names", scope = DeleteNames.class)
    public JAXBElement<ArrayOfNameResponse> createDeleteNamesNames(ArrayOfNameResponse value) {
        return new JAXBElement<ArrayOfNameResponse>(_SetNamesNames_QNAME, ArrayOfNameResponse.class, DeleteNames.class, value);
    }

}

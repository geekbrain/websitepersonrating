package model;


import org.datacontract.schemas._2004._07.wslayer.*;
import org.tempuri.DataProvider;
import org.tempuri.IDataProvider;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.ws.BindingProvider;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WSModel implements IModel{

    private final int NUMBER_ROWS_IN_STATISTIC = 10;
    private IDataProvider servicePort;

    public WSModel() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File("config/config")));
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }
        servicePort = (new DataProvider()).getBasicHttpBindingIDataProvider();
        ((BindingProvider)servicePort).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, props.getProperty("wsdlLocation"));
    }

    public List<NameResponse> getNames(){
        return servicePort.getNames().getNameResponse();
    }

    public List<SiteResponse> getSites(){
        return servicePort.getSites().getSiteResponse();
    }

    public List<CommonResponse> getCommonResponse(int siteId){
        return servicePort.getCommonInfo(siteId).getCommonResponse();
    }

    public List<DailyResponse> getDailyResponse(int siteId){
        return servicePort.getDailyInfo(siteId).getDailyResponse();
    }

    public List<StatisticResponse> getStatisticResponse(int siteId, int nameId, Date startDate, Date endDate) throws Exception{
        GregorianCalendar gStartDate = new GregorianCalendar();
        GregorianCalendar gEndDate = new GregorianCalendar();
        gStartDate.setTime(startDate);
        gEndDate.setTime(endDate);
        XMLGregorianCalendar XMLStartDate;
        XMLGregorianCalendar XMLEndDate;
        try {
            XMLStartDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gStartDate);
            XMLEndDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gEndDate);
        }
        catch (Exception ex) {
            throw ex;
        }
        return servicePort.getStatisticInfo(siteId, nameId, XMLStartDate, XMLEndDate, NUMBER_ROWS_IN_STATISTIC).getStatisticResponse();
    }
}

package model;


import org.datacontract.schemas._2004._07.wslayer.*;
import org.tempuri.DataProvider;
import org.tempuri.IDataProvider;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class WSModel implements IModel{

    private final int NUMBER_ROWS_IN_STATISTIC = 10;
    private IDataProvider servicePort;

    public WSModel() {
        servicePort = (new DataProvider()).getBasicHttpBindingIDataProvider();
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

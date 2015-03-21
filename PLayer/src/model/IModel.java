package model;

import java.util.List;
import java.util.Date;

import org.datacontract.schemas._2004._07.wslayer.*;

public interface IModel {
    List<NameResponse> getNames();
    List<SiteResponse> getSites();
    List<CommonResponse> getCommonResponse(int siteId);
    List<DailyResponse> getDailyResponse(int siteId);
    List<StatisticResponse> getStatisticResponse(int siteId, int nameId, Date startDate, Date endDate) throws Exception;
}

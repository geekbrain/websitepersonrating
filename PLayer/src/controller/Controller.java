package controller;

import model.IModel;
import java.util.List;
import java.util.Date;

import org.datacontract.schemas._2004._07.wslayer.*;

public class Controller {

    private IModel model;

    private Controller() {}
    public Controller(IModel model) {
        this.model = model;
    }

    public List<NameResponse> getName() {
        return model.getNames();
    }

    public List<SiteResponse> getSite() {
        return model.getSites();
    }

    public List<CommonResponse> getCommonResponse(int siteId) {
        return model.getCommonResponse(siteId);
    }

    public List<DailyResponse> getDailyResponse(int siteId) {
        return model.getDailyResponse(siteId);
    }

    public List<StatisticResponse> getStatisticResponse(int siteId, int nameId, Date startDate, Date endDate) throws Exception{
        return model.getStatisticResponse(siteId, nameId, startDate, endDate);
    }

}

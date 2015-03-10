using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace WSLayer
{
    [ServiceContract]
    public interface IDataProvider
    {
        [OperationContract]
        List<NameResponse> GetNames();
        [OperationContract]
        List<SiteResponse> GetSites();
        [OperationContract]
        void SetNames(List<NameResponse> names);
        [OperationContract]
        void SetSites(List<SiteResponse> sites);
        [OperationContract]
        void DeleteNames(List<NameResponse> names);
        [OperationContract]
        void DeleteSites(List<SiteResponse> sites);
        [OperationContract]
        List<CommonResponse> GetCommonInfo(int siteId);
        [OperationContract]
        List<DailyResponse> GetDailyInfo(int siteId);
        [OperationContract]
        List<StatisticResponse> GetStatisticInfo(int siteId, int nameId, DateTime startDate, DateTime endDate, int numberPages);
        [OperationContract]
        List<TaskRequest> RequestTask();
        [OperationContract]
        void ResponseTask(List<TaskResponse> tasks);
    }
}

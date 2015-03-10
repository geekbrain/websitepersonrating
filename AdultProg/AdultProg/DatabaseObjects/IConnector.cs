using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WSLayer
{
    interface IConnector
    {
        List<NameResponse> GetNames();
        List<SiteResponse> GetSites();
        void SetNames(List<NameResponse> names);
        void SetSites(List<SiteResponse> sites);
        void DeleteNames(List<NameResponse> name);
        void DeleteSites(List<SiteResponse> site);
        List<CommonResponse> GetCommonInfo(int siteId);
        List<DailyResponse> GetDailyInfo(int siteId);
        List<StatisticResponse> GetStatisticInfo(int siteId, int nameId, DateTime startDate, DateTime endDate, int numPages);
        List<TaskRequest> RequestTask();
        void ResponseTask(List<TaskResponse> taskElements);
    }
}

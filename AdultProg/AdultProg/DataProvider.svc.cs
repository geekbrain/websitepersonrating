using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace WSLayer
{
    public class DataProvider : IDataProvider
    {
        private IConnector connector = new PostgresConnector();

        public List<NameResponse> GetNames()
        {
            return connector.GetNames();
        }

        public List<SiteResponse> GetSites()
        {
            return connector.GetSites();
        }


        public void SetNames(List<NameResponse> names)
        {
            connector.SetNames(names);
        }

        public void SetSites(List<SiteResponse> sites)
        {
            connector.SetSites(sites);
        }

        public void DeleteNames(List<NameResponse> names)
        {
            connector.DeleteNames(names);
        }

        public void DeleteSites(List<SiteResponse> sites)
        {
            connector.DeleteSites(sites);
        }

        public List<CommonResponse> GetCommonInfo(int siteId)
        {
            return connector.GetCommonInfo(siteId);
        }

        public List<DailyResponse> GetDailyInfo(int siteId)
        {
            return connector.GetDailyInfo(siteId);
        }

        public List<StatisticResponse> GetStatisticInfo(int siteId, int nameId, DateTime startDate, DateTime endDate, int numberPages)
        {
            return connector.GetStatisticInfo(siteId, nameId, startDate, endDate, numberPages);
        }

        public List<Task> RequestTask()
        {
            return connector.RequestTask();
        }

        public void ResponseTask(List<Task> taskElements)
        {
            connector.ResponseTask(taskElements);
        }
    }
}

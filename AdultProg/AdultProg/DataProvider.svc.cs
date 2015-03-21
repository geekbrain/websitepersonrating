using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;
using NLog;

namespace WSLayer
{
    public class DataProvider : IDataProvider
    {
        private IConnector connector = new PostgresConnector();
        private Logger logger = LogManager.GetLogger("errorLog");

        public List<NameResponse> GetNames()
        {
            List<NameResponse> names;
            try
            {
                names = connector.GetNames();
            }
            catch (Exception ex)
            {
                WriteErrorLog(ex);
                throw;
            }
            return names;
        }

        public List<SiteResponse> GetSites()
        {
            List<SiteResponse> sites;
            try
            {
                sites = connector.GetSites();
            }
            catch (Exception ex)
            {
                WriteErrorLog(ex);
                throw;
            }
            return sites;
        }

        public void SetNames(List<NameResponse> names)
        {
            try
            {
                connector.SetNames(names);
            }
            catch (Exception ex)
            {
                WriteErrorLog(ex);
                throw;
            }
        }

        public void SetSites(List<SiteResponse> sites)
        {
            try
            {
                connector.SetSites(sites);
            }
            catch (Exception ex)
            {
                WriteErrorLog(ex);
                throw;
            }
            
        }

        public void DeleteNames(List<NameResponse> names)
        {
            try
            {
                connector.DeleteNames(names);
            }
            catch (Exception ex)
            {
                WriteErrorLog(ex);
                throw;
            }
        }

        public void DeleteSites(List<SiteResponse> sites)
        {
            try
            {
                connector.DeleteSites(sites);
            }
            catch (Exception ex)
            {
                WriteErrorLog(ex);
                throw;
            }
        }

        public List<CommonResponse> GetCommonInfo(int siteId)
        {
            List<CommonResponse> commons;
            try
            {
                commons = connector.GetCommonInfo(siteId);
            }
            catch (Exception ex)
            {
                WriteErrorLog(ex);
                throw;
            }
            return commons;
        }

        public List<DailyResponse> GetDailyInfo(int siteId)
        {
            List<DailyResponse> dailies;
            try
            {
                dailies = connector.GetDailyInfo(siteId);
            }
            catch (Exception ex)
            {
                WriteErrorLog(ex);
                throw;
            }
            return dailies;
        }

        public List<StatisticResponse> GetStatisticInfo(int siteId, int nameId, DateTime startDate, DateTime endDate, int numberPages)
        {
            List<StatisticResponse> statistics;
            try
            {
                statistics = connector.GetStatisticInfo(siteId, nameId, startDate, endDate, numberPages);
            }
            catch (Exception ex)
            {
                WriteErrorLog(ex);
                throw;
            }
            return statistics;
        }

        public List<TaskRequest> RequestTask(int numberPages)
        {
            List<TaskRequest> tasks;
            try
            {
                tasks = connector.RequestTask(numberPages);
            }
            catch (Exception ex)
            {
                WriteErrorLog(ex);
                throw;
            }
            return tasks;
        }

        public void ResponseTask(List<TaskResponse> tasks)
        {
            try
            {
                connector.ResponseTask(tasks);
            }
            catch (Exception ex)
            {
                WriteErrorLog(ex);
                throw;
            }
        }

        private void WriteErrorLog(Exception ex)
        {
            logger.Error(ex.Message);
            logger.Error(ex.StackTrace);
        }
    }
}

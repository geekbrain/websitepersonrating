using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Npgsql;

namespace WSLayer
{
    public class PostgresConnector : IConnector
    {
        public List<NameResponse> GetNames()
        {
            List<NameResponse> nameResponse = new List<NameResponse>();

            List<PhraseResponse> phraseResponse = new List<PhraseResponse>();
            NameResponse name = new NameResponse();
            PhraseResponse phrase = new PhraseResponse();
            phrase.Id = 1;
            phrase.Name = "Путин";
            phraseResponse.Add(phrase);
            name.Id = 1;
            name.Name = "Путин";
            name.Phrases = phraseResponse;
            nameResponse.Add(name);

            phraseResponse = new List<PhraseResponse>();
            name = new NameResponse();
            phrase = new PhraseResponse();
            phrase.Id = 2;
            phrase.Name = "Навальный";
            phraseResponse.Add(phrase);
            phrase = new PhraseResponse();
            phrase.Id = 3;
            phrase.Name = "Овальный";
            phraseResponse.Add(phrase);
            name.Id = 2;
            name.Name = "Навальный";
            name.Phrases = phraseResponse;
            nameResponse.Add(name);

            return nameResponse;
        }

        public List<SiteResponse> GetSites()
        {
            List<SiteResponse> siteResponse = new List<SiteResponse>();

            List<PageResponse> pageResponse = new List<PageResponse>();
            SiteResponse site = new SiteResponse();
            PageResponse page = new PageResponse();
            page.Id = 1;
            page.URL = "www.lenta.ru";
            pageResponse.Add(page);
            site.Id = 1;
            site.Name = "Лента";
            site.URL = "www.lenta.ru";
            site.Pages = pageResponse;
            siteResponse.Add(site);

            return siteResponse;
        }

        public void SetNames(List<NameResponse> names) { }

        public void SetSites(List<SiteResponse> sites) { }

        public void DeleteNames(List<NameResponse> name) { }

        public void DeleteSites(List<SiteResponse> site) { }

        public List<CommonResponse> GetCommonInfo(int siteId)
        {
            List<CommonResponse> commonResponse = new List<CommonResponse>();
            CommonResponse response = new CommonResponse();
            response.NameId = 1;
            response.Fact = 10;
            commonResponse.Add(response);
            response = new CommonResponse();
            response.NameId = 2;
            response.Fact = 3;
            commonResponse.Add(response);

            return commonResponse;
        }

        public List<DailyResponse> GetDailyInfo(int siteId)
        {
            List<DailyResponse> dailyResponse = new List<DailyResponse>();
            DailyResponse response = new DailyResponse();
            response.NameId = 1;
            response.Fact = 2;
            dailyResponse.Add(response);
            response = new DailyResponse();
            response.NameId = 2;
            response.Fact = 1;
            dailyResponse.Add(response);

            return dailyResponse;
        }

        public List<StatisticResponse> GetStatisticInfo(int siteId, int nameId, DateTime startDate, DateTime endDate, int numPages)
        {
            List<StatisticResponse> statisticResponse = new List<StatisticResponse>();
            StatisticResponse response = new StatisticResponse();
            response.PageId = 1;
            response.Fact = 10;
            statisticResponse.Add(response);

            return statisticResponse;
        }

        public List<Task> RequestTask()
        {
            List<Task> taskResponse = new List<Task>();
            Task task = new Task();
            task.TaskId = 1;
            task.SiteId = 1;
            task.PageId = 1;
            task.PageURL = "www.lenta.ru";
            taskResponse.Add(task);
            return taskResponse;
        }

        public void ResponseTask(List<Task> taskElements) { }

        //connection = new NpgsqlConnection("Server=127.0.0.1;Port=5432;User Id=postgres;Password=Qwerty12345;Database=sitestatsDB;");
    }
}
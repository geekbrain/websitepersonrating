using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Npgsql;

namespace WSLayer
{
    public partial class PostgresConnector : IConnector
    {
        private string connectionString = "Server=127.0.0.1;Port=5432;User Id=postgres;Password=Qwerty12345;Database=sitestatsDB;";

        public List<NameResponse> GetNames()
        {
            List<NameResponse> names = new List<NameResponse>();

            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                connection.Open();
                try
                {
                    NpgsqlCommand command = new NpgsqlCommand(QueryContainer.SelectAllNames(), connection);
                    NpgsqlDataReader reared = command.ExecuteReader();
                    while (reared.Read())
                    {
                        NameResponse name = new NameResponse();
                        name.Id = (int)reared[QueryContainer.nameId.Trim('"')];
                        name.Name = (String)reared[QueryContainer.nameName.Trim('"')];
                        names.Add(name);
                    }
                    foreach (NameResponse nameResponse in names)
                    {
                        List<PhraseResponse> phrases = new List<PhraseResponse>();
                        command = new NpgsqlCommand(QueryContainer.SelectPhrasesDependsOnName(nameResponse.Id), connection);
                        reared = command.ExecuteReader();
                        while (reared.Read())
                        {
                            PhraseResponse phrase = new PhraseResponse();
                            phrase.Id = (int)reared[QueryContainer.phraseId.Trim('"')];
                            phrase.Name = (String)reared[QueryContainer.phraseName.Trim('"')];
                            phrases.Add(phrase);
                        }
                        nameResponse.Phrases = phrases;
                    }

                }
                catch (Exception ex) { throw ex; }
                connection.Close();
            }
            return names;
        }

        public List<SiteResponse> GetSites()
        {
            List<SiteResponse> sites = new List<SiteResponse>();

            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                connection.Open();
                try
                {
                    NpgsqlCommand command = new NpgsqlCommand(QueryContainer.SelectAllSites(), connection);
                    NpgsqlDataReader reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        SiteResponse site = new SiteResponse();
                        site.Id = (int)reader[QueryContainer.siteId.Trim('"')];
                        site.Name = (String)reader[QueryContainer.siteName.Trim('"')];
                        site.URL = (String)reader[QueryContainer.siteURL.Trim('"')];
                        sites.Add(site);
                    }
                }
                catch (Exception ex) { throw ex; }
                connection.Close();
            }
            return sites;
        }

        public void SetNames(List<NameResponse> names)
        {
            if (names != null)
            {
                using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
                {
                    connection.Open();
                    try
                    {
                        NpgsqlCommand command;
                        NpgsqlDataReader reader;
                        foreach (NameResponse name in names)
                        {
                            if (name != null)
                            {
                                if (name.Name == null || name.Name.Equals(""))
                                    throw new Exception("Name of name must not be empty or null");
                                else
                                {
                                    if (name.Id <= 0)
                                    {
                                        command = new NpgsqlCommand(QueryContainer.CreateName(name.Name), connection);
                                        reader = command.ExecuteReader();
                                        reader.Read();
                                        name.Id = (int)reader[QueryContainer.nameId.Trim('"')];
                                        reader.Close();
                                    }
                                    else
                                    {
                                        command = new NpgsqlCommand(QueryContainer.UpdateName(name.Id, name.Name), connection);
                                        command.ExecuteNonQuery();
                                        command = new NpgsqlCommand(QueryContainer.DeleteAllPhrasesOfName(name.Id), connection);
                                        command.ExecuteNonQuery();
                                    }
                                    if (name.Phrases != null && name.Phrases.Count() != 0)
                                    {
                                        Boolean isAllPhrasesAreNull = true;
                                        foreach (PhraseResponse phrase in name.Phrases)
                                        {
                                            if (phrase != null)
                                            {
                                                isAllPhrasesAreNull = false;
                                                if (phrase.Name == null || phrase.Name.Equals(""))
                                                    throw new Exception("Name of phrase must not be empty or null");
                                                else
                                                {
                                                    if (phrase.Id <= 0)
                                                    {
                                                        command = new NpgsqlCommand(QueryContainer.CreatePhrase(phrase.Name, name.Id), connection);
                                                        command.ExecuteNonQuery();
                                                    }
                                                    else
                                                    {
                                                        command = new NpgsqlCommand(QueryContainer.UpdatePhrase(phrase.Id, phrase.Name), connection);
                                                        command.ExecuteNonQuery();
                                                    }
                                                }
                                            }
                                        }
                                        if (isAllPhrasesAreNull)
                                        {
                                            command = new NpgsqlCommand(QueryContainer.CreatePhrase(name.Name, name.Id), connection);
                                            command.ExecuteNonQuery();
                                        }
                                    }
                                    else
                                    {
                                        command = new NpgsqlCommand(QueryContainer.CreatePhrase(name.Name, name.Id), connection);
                                        command.ExecuteNonQuery();
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception ex) { throw ex; }
                    connection.Close();
                }
            }
        }

        public void SetSites(List<SiteResponse> sites)
        {
            if (sites != null)
            {
                using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
                {
                    connection.Open();
                    try
                    {
                        NpgsqlCommand command;
                        NpgsqlDataReader reader;
                        foreach (SiteResponse site in sites)
                        {

                            if (site != null)
                            {
                                if (site.Name == null || site.Name.Equals(""))
                                    throw new Exception("Name of site must not be empty or null");
                                else
                                {
                                    if (site.Id <= 0)
                                    {
                                        if (site.URL == null || site.URL.Equals(""))
                                            throw new Exception("URL of site must not be empty or null");
                                        else
                                        {
                                            command = new NpgsqlCommand(QueryContainer.CreateSite(site.Name, site.URL), connection);
                                            reader = command.ExecuteReader();
                                            reader.Read();
                                            site.Id = (int)reader[QueryContainer.siteId.Trim('"')];
                                            reader.Close();
                                            command = new NpgsqlCommand(QueryContainer.CreatePage(site.URL, site.Id), connection);
                                            command.ExecuteNonQuery();
                                        }                                       
                                    }
                                    else
                                    {
                                        command = new NpgsqlCommand(QueryContainer.UpdateSite(site.Id, site.Name), connection);
                                        reader = command.ExecuteReader();
                                    }                                    
                                }
                            }
                        }
                    }
                    catch (Exception ex) { throw ex; }
                    connection.Close();
                }
            }
        }

        public void DeleteNames(List<NameResponse> names)
        {
            if (names != null)
            {
                using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
                {
                    connection.Open();
                    try
                    {
                        NpgsqlCommand command;
                        foreach (NameResponse name in names)
                        {
                            if (name != null && name.Id > 0)
                            {
                                command = new NpgsqlCommand(QueryContainer.DeleteName(name.Id));
                                command.ExecuteNonQuery();
                                command = new NpgsqlCommand(QueryContainer.DeleteAllPhrasesOfName(name.Id));
                                command.ExecuteNonQuery();
                            }
                        }
                        
                    }
                    catch (Exception ex) { throw ex; }
                    connection.Close();
                }
            }
        }

        public void DeleteSites(List<SiteResponse> sites)
        {
            if (sites != null)
            {
                using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
                {
                    connection.Open();
                    try
                    {
                        NpgsqlCommand command;
                        foreach (SiteResponse site in sites)
                        {
                            if (site != null && site.Id > 0)
                            {
                                command = new NpgsqlCommand(QueryContainer.DeleteAllPagesOfSite(site.Id));
                                command.ExecuteNonQuery();
                                command = new NpgsqlCommand(QueryContainer.DeleteSite(site.Id));
                                command.ExecuteNonQuery();
                            }
                        }

                    }
                    catch (Exception ex) { throw ex; }
                    connection.Close();
                }
            }
        }

        public List<CommonResponse> GetCommonInfo(int siteId)
        {
            List<CommonResponse> commonResponse = new List<CommonResponse>();
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                connection.Open();
                try
                {
                    NpgsqlCommand command = new NpgsqlCommand(QueryContainer.CubeCommonInformation(siteId));
                    NpgsqlDataReader reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        CommonResponse response = new CommonResponse();
                        response.NameId = (int)reader[QueryContainer.cubeNameId.Trim('"')];
                        response.Fact = (int)reader[QueryContainer.cubeFact.Trim('"')];
                        commonResponse.Add(response);
                    }
                }
                catch (Exception ex) { throw ex; }
                connection.Close();
            }
            return commonResponse;
        }

        public List<DailyResponse> GetDailyInfo(int siteId)
        {
            List<DailyResponse> dailyResponse = new List<DailyResponse>();
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                connection.Open();
                try
                {
                    NpgsqlCommand command = new NpgsqlCommand(QueryContainer.CubeDailyInformation(siteId));
                    NpgsqlDataReader reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        DailyResponse response = new DailyResponse();
                        response.NameId = (int)reader[QueryContainer.cubeNameId.Trim('"')];
                        response.Fact = (int)reader[QueryContainer.cubeFact.Trim('"')];
                        dailyResponse.Add(response);
                    }
                }
                catch (Exception ex) { throw ex; }
                connection.Close();
            }
            return dailyResponse;
        }

        public List<StatisticResponse> GetStatisticInfo(int siteId, int nameId, DateTime startDate, DateTime endDate, int numberPages)
        {
            List<StatisticResponse> statisticResponse = new List<StatisticResponse>();
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                connection.Open();
                try
                {
                    NpgsqlCommand command = new NpgsqlCommand(QueryContainer.CubeStatisticInformation(siteId, nameId, startDate, endDate, numberPages));
                    NpgsqlDataReader reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        StatisticResponse response = new StatisticResponse();
                        response.PageURL = (String)reader[QueryContainer.pageURL.Trim('"')];
                        response.Fact = (int)reader[QueryContainer.cubeFact.Trim('"')];
                        statisticResponse.Add(response);
                    }
                }
                catch (Exception ex) { throw ex; }
                connection.Close();
            }
            return statisticResponse;
        }

        public List<TaskRequest> RequestTask(int firstId, int numberRows)
        {
            List<TaskRequest> tasks = new List<TaskRequest>();
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                connection.Open();
                try
                {
                    NpgsqlCommand command = new NpgsqlCommand(QueryContainer.GetTask(firstId, numberRows));
                    NpgsqlDataReader reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        TaskRequest task = new TaskRequest();
                        task.PageId = (int)reader[QueryContainer.pageId.Trim('"')];
                        task.PageURL = (String)reader[QueryContainer.pageURL.Trim('"')];
                        task.SiteId = (int)reader[QueryContainer.pageSiteId.Trim('"')];
                        tasks.Add(task);
                    }
                }
                catch (Exception ex) { throw ex; }
                connection.Close();
            }
            return tasks;
        }

        public void ResponseTask(List<TaskResponse> tasks)
        {
            if (tasks != null)
            {
                using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
                {
                    connection.Open();
                    try
                    {
                        foreach (TaskResponse task in tasks)
                        {
                            if (task != null)
                            {
                                if (task.SiteId <= 0)
                                    throw new Exception("Site id must not be null");
                                else if (task.NameId <= 0)
                                    throw new Exception("Name id must not be null");
                                else if (task.PageURL == null || task.PageURL.Equals(""))
                                    throw new Exception("Page url must not be null or empty");
                                else if (task.Fact < 0 )
                                    throw new Exception("Fact must be non negative");
                                else
                                {
                                    if (task.PageId <= 0)
                                    {
                                        NpgsqlCommand command = new NpgsqlCommand(QueryContainer.CreatePage(task.PageURL, task.SiteId), connection);
                                        NpgsqlDataReader reader = command.ExecuteReader();
                                        reader.Read();
                                        task.PageId = (int)reader[QueryContainer.pageId.Trim('"')];
                                        reader.Close();
                                        command = new NpgsqlCommand(QueryContainer.CubeAddData(task.NameId, task.PageId, task.Fact), connection);
                                        command.ExecuteNonQuery();
                                    }
                                    else
                                    {
                                        NpgsqlCommand command = new NpgsqlCommand(QueryContainer.CubeGetActualData(task.NameId, task.PageId), connection);
                                        NpgsqlDataReader reader = command.ExecuteReader();
                                        if (reader.Read())
                                        {
                                            int newFact = (int)reader[QueryContainer.cubeFact.Trim('"')];
                                            reader.Close();
                                            if (task.Fact == newFact)
                                            {
                                                command = new NpgsqlCommand(QueryContainer.CubeUpdateData(task.NameId, task.PageId, task.Fact), connection);
                                                command.ExecuteNonQuery();
                                            }
                                            else
                                            {
                                                command = new NpgsqlCommand(QueryContainer.CubeAddData(task.NameId, task.PageId, newFact), connection);
                                                command.ExecuteNonQuery();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception ex) { throw ex; }
                    connection.Close();
                }
            }

        }
    }
}
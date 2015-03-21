using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Npgsql;

namespace WSLayer
{
    public partial class PostgresConnector : IConnector
    {
        private static class QueryTransformer
        {
            public static List<NameResponse> TransformIntoName(NpgsqlCommand command)
            {
                List<NameResponse> names = new List<NameResponse>();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    NameResponse name = new NameResponse();
                    name.Id = (int)reader[QueryConstructor.nameId.Trim('"')];
                    name.Name = (String)reader[QueryConstructor.nameName.Trim('"')];
                    names.Add(name);
                }
                reader.Close();
                return names;
            }

            public static List<PhraseResponse> TransformIntoPhrase(NpgsqlCommand command)
            {
                List<PhraseResponse> phrases = new List<PhraseResponse>();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    PhraseResponse phrase = new PhraseResponse();
                    phrase.Id = (int)reader[QueryConstructor.phraseId.Trim('"')];
                    phrase.Name = (String)reader[QueryConstructor.phraseName.Trim('"')];
                    phrases.Add(phrase);
                }
                reader.Close();
                return phrases;
            }

            public static List<SiteResponse> TransformIntoSite(NpgsqlCommand command)
            {
                List<SiteResponse> sites = new List<SiteResponse>();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    SiteResponse site = new SiteResponse();
                    site.Id = (int)reader[QueryConstructor.siteId.Trim('"')];
                    site.Name = (String)reader[QueryConstructor.siteName.Trim('"')];
                    site.URL = (String)reader[QueryConstructor.siteURL.Trim('"')];
                    sites.Add(site);
                }
                reader.Close();
                return sites;
            }

            public static List<CommonResponse> TransformIntoCommonResponse(NpgsqlCommand command)
            {
                List<CommonResponse> responses = new List<CommonResponse>();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    CommonResponse response = new CommonResponse();
                    response.NameId = (int)reader[QueryConstructor.cubeNameId.Trim('"')];
                    response.Fact = (long)reader[QueryConstructor.cubeFact.Trim('"')];
                    responses.Add(response);
                }
                reader.Close();
                return responses;
            }

            public static List<DailyResponse> TransformIntoDailyResponse(NpgsqlCommand command)
            {
                List<DailyResponse> responses = new List<DailyResponse>();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    DailyResponse response = new DailyResponse();
                    response.NameId = (int)reader[QueryConstructor.cubeNameId.Trim('"')];
                    response.Fact = (long)reader[QueryConstructor.cubeFact.Trim('"')];
                    responses.Add(response);
                }
                reader.Close();
                return responses;
            }

            public static List<StatisticResponse> TransformIntoStatisticResponse(NpgsqlCommand command)
            {
                List<StatisticResponse> responses = new List<StatisticResponse>();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    StatisticResponse response = new StatisticResponse();
                    response.PageURL = (String)reader[QueryConstructor.pageURL.Trim('"')];
                    response.Fact = (int)reader[QueryConstructor.cubeFact.Trim('"')];
                    responses.Add(response);
                }
                reader.Close();
                return responses;
            }

            public static List<TaskRequest> TransformIntoTask(NpgsqlCommand command)
            {
                List<TaskRequest> tasks = new List<TaskRequest>();
                NpgsqlDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    TaskRequest task = new TaskRequest();
                    task.PageId = (int)reader[QueryConstructor.pageId.Trim('"')];
                    task.PageURL = (String)reader[QueryConstructor.pageURL.Trim('"')];
                    task.SiteId = (int)reader[QueryConstructor.pageSiteId.Trim('"')];
                    tasks.Add(task);
                }
                reader.Close();
                return tasks;
            }

            public static int TransformIntoNameId(NpgsqlCommand command)
            {
                NpgsqlDataReader reader = command.ExecuteReader();
                int nameId = reader.Read() ? (int)reader[QueryConstructor.nameId.Trim('"')] : 0;
                reader.Close();
                return nameId;
            }

            public static int TransformIntoSiteId(NpgsqlCommand command)
            {
                NpgsqlDataReader reader = command.ExecuteReader();
                int siteId = reader.Read() ? (int)reader[QueryConstructor.siteId.Trim('"')] : 0;
                reader.Close();
                return siteId;
            }

            public static int TransformIntoPageId(NpgsqlCommand command)
            {
                NpgsqlDataReader reader = command.ExecuteReader();
                int pageId = reader.Read() ? (int)reader[QueryConstructor.pageId.Trim('"')] : 0;
                reader.Close();
                return pageId;
            }

            public static bool TransformIntoIsCubeFactExists(NpgsqlCommand command)
            {
                NpgsqlDataReader reader = command.ExecuteReader();
                bool isFactExists = reader.Read() ? true : false;
                reader.Close();
                return isFactExists;
            }

            public static int TransformIntoCubeFact(NpgsqlCommand command)
            {
                NpgsqlDataReader reader = command.ExecuteReader();
                int fact = reader.Read() ? (int)reader[QueryConstructor.cubeFact.Trim('"')] : 0;
                reader.Close();
                return fact;
            }
        }
    }
}
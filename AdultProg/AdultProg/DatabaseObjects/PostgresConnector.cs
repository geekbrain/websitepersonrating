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
            List<NameResponse> names;
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                try
                {
                    connection.Open();
                    names = QueryTransformer.TransformIntoName(new NpgsqlCommand(QueryConstructor.SelectAllNames(), connection));
                    foreach (NameResponse nameResponse in names)
                    {
                        nameResponse.Phrases = QueryTransformer.TransformIntoPhrase(new NpgsqlCommand(QueryConstructor.SelectPhrasesDependsOnName(nameResponse.Id), connection));
                    }
                }
                catch (Exception ex) { throw ex; }
                finally { connection.Close(); }
            }
            return names;
        }

        public List<SiteResponse> GetSites()
        {
            List<SiteResponse> sites;
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                try
                {
                    connection.Open();
                    sites = QueryTransformer.TransformIntoSite(new NpgsqlCommand(QueryConstructor.SelectAllSites(), connection));
                }
                catch (Exception ex) { throw ex; }
                finally { connection.Close(); }
            }
            return sites;
        }

        public void SetNames(List<NameResponse> names)
        {
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                try
                {
                    Validator.Validate<NameResponse>(names);
                    foreach (NameResponse name in names)
                    {
                        Validator.Validate(name);
                        foreach (PhraseResponse phrase in name.Phrases)
                        {
                            Validator.Validate(phrase);
                        }
                    }
                    connection.Open();
                    foreach (NameResponse name in names)
                    {
                        if (name.Id == 0)
                        {
                            name.Id = QueryTransformer.TransformIntoNameId(new NpgsqlCommand(QueryConstructor.CreateName(name.Name), connection));
                        }
                        else
                        {
                            (new NpgsqlCommand(QueryConstructor.UpdateName(name.Id, name.Name), connection)).ExecuteNonQuery();
                            (new NpgsqlCommand(QueryConstructor.DeleteAllPhrasesOfName(name.Id), connection)).ExecuteNonQuery();
                        }
                        foreach (PhraseResponse phrase in name.Phrases)
                        {
                            if (phrase.Id == 0)
                            {
                                (new NpgsqlCommand(QueryConstructor.CreatePhrase(phrase.Name, name.Id), connection)).ExecuteNonQuery();
                            }
                            else
                            {
                                (new NpgsqlCommand(QueryConstructor.UpdatePhrase(phrase.Id, phrase.Name), connection)).ExecuteNonQuery();
                            }
                        }
                    }
                }
                catch (ValidationException ex) { throw ex; }
                catch (Exception ex) { throw ex; }
                finally { connection.Close(); }
            }
        }

        public void SetSites(List<SiteResponse> sites)
        {
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                try
                {
                    Validator. Validate<SiteResponse>(sites);
                    foreach (SiteResponse site in sites)
                    {
                        Validator.Validate(site);
                    }
                    connection.Open();
                    foreach (SiteResponse site in sites)
                    {
                        if (site.Id == 0)
                        {
                            site.Id = QueryTransformer.TransformIntoSiteId(new NpgsqlCommand(QueryConstructor.CreateSite(site.Name, site.URL), connection));
                            (new NpgsqlCommand(QueryConstructor.CreatePage(site.URL, site.Id), connection)).ExecuteNonQuery();
                        }
                        else
                        {
                            (new NpgsqlCommand(QueryConstructor.UpdateSite(site.Id, site.Name, site.URL), connection)).ExecuteNonQuery();
                        }

                    }
                }
                catch (ValidationException ex) { throw ex; }
                catch (Exception ex) { throw ex; }
                finally { connection.Close(); }
            }
        }

        public void DeleteNames(List<NameResponse> names)
        {
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                try
                {
                    Validator.Validate<NameResponse>(names);
                    connection.Open();
                    foreach (NameResponse name in names)
                    {
                        (new NpgsqlCommand(QueryConstructor.DeleteName(name.Id), connection)).ExecuteNonQuery();
                        (new NpgsqlCommand(QueryConstructor.DeleteAllPhrasesOfName(name.Id), connection)).ExecuteNonQuery();
                    }
                }
                catch (ValidationException ex) { throw ex; }
                catch (Exception ex) { throw ex; }
                finally { connection.Close(); }
            }
        }

        public void DeleteSites(List<SiteResponse> sites)
        {
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {  
                try
                {
                    Validator.Validate<SiteResponse>(sites);
                    connection.Open();
                    foreach (SiteResponse site in sites)
                    {
                        (new NpgsqlCommand(QueryConstructor.DeleteAllPagesOfSite(site.Id), connection)).ExecuteNonQuery();
                        (new NpgsqlCommand(QueryConstructor.DeleteSite(site.Id), connection)).ExecuteNonQuery();
                    }
                }
                catch (ValidationException ex) { throw ex; }
                catch (Exception ex) { throw ex; }
                finally { connection.Close(); }
            }
        }

        public List<CommonResponse> GetCommonInfo(int siteId)
        {
            List<CommonResponse> responses;
            Validator.Validate(siteId);
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                try
                {
                    connection.Open();
                    responses = QueryTransformer.TransformIntoCommonResponse(new NpgsqlCommand(QueryConstructor.CubeCommonInformation(siteId), connection));
                }
                catch (ValidationException ex) { throw ex; }
                catch (Exception ex) { throw ex; }
                finally { connection.Close(); }
            }
            return responses;
        }

        public List<DailyResponse> GetDailyInfo(int siteId)
        {
            List<DailyResponse> responses;
            Validator.Validate(siteId);
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                try
                {
                    connection.Open();
                    responses = QueryTransformer.TransformIntoDailyResponse(new NpgsqlCommand(QueryConstructor.CubeDailyInformation(siteId), connection));
                }
                catch (ValidationException ex) { throw ex; }
                catch (Exception ex) { throw ex; }
                finally { connection.Close(); }
            }
            return responses;
        }

        public List<StatisticResponse> GetStatisticInfo(int siteId, int nameId, DateTime startDate, DateTime endDate, int numberPages)
        {
            List<StatisticResponse> responses;
            Validator.Validate(siteId);
            Validator.Validate(nameId);
            Validator.Validate(numberPages);
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                try
                {
                    connection.Open();
                    responses = QueryTransformer.TransformIntoStatisticResponse(new NpgsqlCommand(QueryConstructor.CubeStatisticInformation(siteId, nameId, startDate, endDate, numberPages), connection));
                }
                catch (ValidationException ex) { throw ex; }
                catch (Exception ex) { throw ex; }
                finally { connection.Close(); }
            }
            return responses;
        }

        public List<TaskRequest> RequestTask()
        {
            List<TaskRequest> tasks;
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                try
                {
                    connection.Open();
                    tasks = QueryTransformer.TransformIntoTask(new NpgsqlCommand(QueryConstructor.GetTask(Tasker.GetInstance.GetNextId(), Tasker.GetInstance.GetNumberIdInTask()), connection));
                    if (tasks.Count == 0)
                    {
                        Tasker.GetInstance.ResetId();
                        tasks = RequestTask();
                    }
                    Tasker.GetInstance.SetId(tasks[tasks.Count - 1].PageId + 1);
                }
                catch (Exception ex) { throw ex; }
                finally { connection.Close(); }
            }
            return tasks;
        }

        public void ResponseTask(List<TaskResponse> tasks)
        {
            using (NpgsqlConnection connection = new NpgsqlConnection(connectionString))
            {
                try
                {
                    Validator.Validate<TaskResponse>(tasks);
                    foreach (TaskResponse task in tasks)
                    {
                        Validator.Validate(task);
                    }
                    connection.Open();
                    foreach (TaskResponse task in tasks)
                    {
                        if (task.PageId == 0)
                        {
                            int pageId = QueryTransformer.TransformIntoPageId(new NpgsqlCommand(QueryConstructor.SelectPagesOfURL(task.PageURL), connection));
                            if (pageId != 0)
                            {
                                task.PageId = pageId;
                                if (QueryTransformer.TransformIntoIsCubeFactExists(new NpgsqlCommand(QueryConstructor.CubeGetActualData(task.NameId, task.PageId), connection)))
                                {
                                    int fact = QueryTransformer.TransformIntoCubeFact(new NpgsqlCommand(QueryConstructor.CubeGetActualData(task.NameId, task.PageId), connection));
                                    if (fact == task.Fact)
                                    {
                                        (new NpgsqlCommand(QueryConstructor.CubeUpdateData(task.NameId, task.PageId, task.Fact), connection)).ExecuteNonQuery();
                                    }
                                    else
                                    {
                                        (new NpgsqlCommand(QueryConstructor.CubeAddData(task.NameId, task.PageId, task.Fact), connection)).ExecuteNonQuery();
                                    }
                                }
                                else
                                {
                                    (new NpgsqlCommand(QueryConstructor.CubeAddData(task.NameId, task.PageId, task.Fact), connection)).ExecuteNonQuery();
                                }
                            }
                            else
                            {
                                task.PageId = QueryTransformer.TransformIntoPageId(new NpgsqlCommand(QueryConstructor.CreatePage(task.PageURL, task.SiteId), connection));
                                (new NpgsqlCommand(QueryConstructor.CubeAddData(task.NameId, task.PageId, task.Fact), connection)).ExecuteNonQuery();
                            }
                        }
                        else
                        {
                            if (QueryTransformer.TransformIntoIsCubeFactExists(new NpgsqlCommand(QueryConstructor.CubeGetActualData(task.NameId, task.PageId), connection)))
                            {
                                int fact = QueryTransformer.TransformIntoCubeFact(new NpgsqlCommand(QueryConstructor.CubeGetActualData(task.NameId, task.PageId), connection));
                                if (task.Fact == fact)
                                {
                                    (new NpgsqlCommand(QueryConstructor.CubeUpdateData(task.NameId, task.PageId, task.Fact), connection)).ExecuteNonQuery();
                                }
                                else
                                {
                                    (new NpgsqlCommand(QueryConstructor.CubeAddData(task.NameId, task.PageId, task.Fact), connection)).ExecuteNonQuery();
                                }
                            }
                            else
                            {
                                (new NpgsqlCommand(QueryConstructor.CubeAddData(task.NameId, task.PageId, task.Fact), connection)).ExecuteNonQuery();
                            }
                        }
                    }
                }
                catch (ValidationException ex) { throw ex; }
                catch (Exception ex) { throw ex; }
                connection.Close();
            }
        }
    }
}
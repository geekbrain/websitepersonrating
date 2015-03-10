using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSLayer
{
    public partial class PostgresConnector : IConnector
    {
        private static class QueryContainer
        {
            private static readonly String nameTable = "\"name\"";
            private static readonly String nameId = "\"id\"";
            private static readonly String nameName = "\"name\"";
            private static readonly String nameIsActual = "\"isActual\"";

            private static readonly String phraseTable = "\"phrase\"";
            private static readonly String phraseId = "\"id\"";
            private static readonly String phraseName = "\"name\"";
            private static readonly String phraseNameId = "\"nameId\"";

            private static readonly String siteTable = "\"site\"";
            private static readonly String siteId = "\"id\"";
            private static readonly String siteName = "\"name\"";
            private static readonly String siteURL = "\"url\"";
            private static readonly String siteIsActual = "\"isActual\"";

            private static readonly String pageTable = "\"page\"";
            private static readonly String pageId = "\"id\"";
            private static readonly String pageURL = "\"url\"";
            private static readonly String pageDateCreation = "\"dateCreation\"";
            private static readonly String pageSiteId = "\"siteId\"";

            private static readonly String cubeTable = "\"cube\"";
            private static readonly String cubeId = "\"id\"";
            private static readonly String cubeNameId = "\"nameId\"";
            private static readonly String cubePageId = "\"pageId\"";
            private static readonly String cubeDate = "\"date\"";
            private static readonly String cubeFact = "\"fact\"";
            private static readonly String cubeMaxId = "\"maxId\"";

            public static String SelectAllNames()
            {
                return String.Format("select {0}, {1} from {2} where {3} = true", nameId, nameName, nameTable, nameIsActual);
            }
            public static String SelectPhrasesDependsOnName(int nameId)
            {
                return String.Format("select {0}, {1} from {2} where {3} = {4}", phraseId, phraseName, phraseTable, phraseNameId, nameId.ToString());
            }
            public static String SelectAllSites()
            {
                return String.Format("select {0}, {1}, {2} from {3} where {4} = true", siteId, siteName, siteURL, siteTable, siteIsActual);
            }
            public static String SelectPagesDependsOnSite(int siteId)
            {
                return String.Format("select {0}, {1} from {2} where {3} = {4}", pageId, pageURL, pageTable, pageSiteId, siteId.ToString());
            }
            public static String CreateName(String name)
            {
                return String.Format("insert into {0}({1},{2}) values('{3}',true)", nameTable, nameId, nameIsActual, name);
            }
            public static String CreatePhrase(String phrase, int nameId)
            {
                return String.Format("insert into {0}({1},{2}) values('{3}',{4})", phraseTable, phraseName, phraseNameId, phrase, nameId.ToString());
            }
            public static String CreateSite(String site, String url)
            {
                return String.Format("insert into {0}({1},{2},{3}) values('{4}','{5}',true)", siteTable, siteName, siteURL, siteIsActual, site, url);
            }
            public static String CreatePage(String page, int siteId)
            {
                return String.Format("insert into {0}({1},{2},{3}) values('{4}',{5},current_date)", pageTable, pageURL, pageSiteId, pageDateCreation, page, siteId.ToString());
            }
            public static String UpdateName(int id, String name)
            {
                return String.Format("update {0} set {1} = '{2}' where {3} = {4}", nameTable, nameName, name, nameId, id);
            }
            public static String UpdatePhrase(int id, String phrase)
            {
                return String.Format("update {0} set {1} = '{2}' where {3} = {4}", phraseTable, phraseName, phrase, phraseId, id);
            }
            public static String UpdateSite(int id, String site, String url)
            {
                return String.Format("update {0} set {1} = '{2}', {3} = '{4}' where {5} = {6}", siteTable, siteName, site, siteURL, url, siteId, id.ToString());
            }
            public static String DeleteName(int id)
            {
                return String.Format("update {0} set {1} = false where {2} = {3}", nameTable, nameIsActual, nameId, id.ToString());
            }
            public static String DeleteSite(int id)
            {
                return String.Format("update {0} set {1} = false where {2} = {3}", siteTable, siteIsActual, siteId, id.ToString());
            }
            public static String CubeCommonInformation(int siteId)
            {
                return String.Format("select c.{0},sum(c.{1}) from {2} c join ({3}) mdr on c.{4} = mdr.{5} group by c.{0}", 
                    cubeNameId, cubeFact, cubeTable, GetCubeMaxDateName(siteId), cubeId, cubeMaxId);
            }
            public static String CubeDailyInformation(int siteId)
            {
                return String.Format("select c.{0},count(c.{1}) from {2} c join ({3}) mdr on c.{4} = mdr.{5} where c.{6} in ({7}) group by c.{0}",
                    cubeNameId, cubeFact, cubeTable, GetCubeMaxDateName(siteId), cubeId, cubeMaxId, cubePageId, GetIdTodayPagesOfSite(siteId));
            }
            public static String CubeStatisticInformation(int siteId, int nameId, int numberPages, DateTime startDate, DateTime endDate)
            {
                return String.Format("select p.{0},c.{1} from {2} c join ({3}) mdr on c.{4} = mdr.{5} join {6} p on c.{7} = p.{8} where c.{7} in ({9}) and c.{10} = {11} limit {12}",
                    pageURL, cubeFact, cubeTable, GetCubeMaxDateName(siteId), cubeId, cubeMaxId, pageTable, cubePageId, pageId,
                    GetIdDateOfRangePagesOfSite(siteId, startDate, endDate), cubeNameId, nameId, numberPages);
            }
            public static String CubeAddData(int nameId, int pageId, int fact)
            {
                return String.Format("insert into {0}({1},{2},{3},{4}) values({5},{6},current_date,{7})",
                    cubeTable, cubeNameId, cubePageId, cubeDate, cubeFact, nameId.ToString(), pageId.ToString(), fact.ToString());
            }
            public static String CubeUpdateData(int nameId, int pageId, int fact)
            {
                return String.Format("update {0} set {1} = {2}, {3} = current_date where {4} in ({5})", 
                    cubeTable, cubeFact, fact.ToString(), cubeDate, cubeId, GetCubeMaxIdNamePage(nameId, pageId));
            }
            public static String GetTask(long firstId, int numberId)
            {
                return String.Format("select {0},{1},{2} from {3} where {0} > {4} and {0} <= {5}",
                    pageId, pageURL, pageSiteId, pageTable, firstId.ToString(), ((long)numberId + firstId).ToString());
            }

            private static String GetCubeMaxDateName(int siteId)
            {
                return String.Format("select {0} {0},max({1}) {2} from {3} where {0} in ({4}) and {5} in ({6}) group by {0}",
                    cubeNameId, cubeId, cubeMaxId, cubeTable, GetIdActualNames(), cubePageId, GetIdPagesOfSite(siteId));
            }
            private static String GetIdActualNames()
            {
                return String.Format("select {0} from {1} where {2} = true", nameId, nameTable, nameIsActual);
            }
            private static String GetIdPagesOfSite(int siteId)
            {
                return String.Format("select {0} from {1} where {2} = {3}", pageId, pageTable, pageSiteId, siteId.ToString());
            }
            private static String GetIdTodayPagesOfSite(int siteId)
            {
                return String.Format("select {0} from {1} where {2} = {3} and {4} = current_date", pageId, pageTable, pageSiteId, siteId.ToString(), pageDateCreation);
            }
            private static String GetIdDateOfRangePagesOfSite(int siteId, DateTime startDate, DateTime endDate)
            {
                return String.Format("select {0} from {1} where {2} = {3} and {4} between '{5}' and '{6}'", 
                    pageId, pageTable, pageSiteId, siteId.ToString(), pageDateCreation,
                    startDate.Date.ToString("dd.MM.yyyy"), endDate.Date.ToString("dd.MM.yyyy"));
            }
            private static String GetCubeMaxIdNamePage(int nameId, int pageId)
            {
                return String.Format("select max({0}) from {1} where {2} = {3} and {4} = {5}",
                    cubeId, cubeTable, cubeNameId, nameId.ToString(), cubePageId, pageId.ToString());
            }
        }
    }
}
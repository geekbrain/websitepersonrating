using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSLayer
{
    public partial class PostgresConnector : IConnector
    {
        private static class QueryConstructor
        {
            public static readonly String nameTable = "\"name\"";
            public static readonly String nameId = "\"id\"";
            public static readonly String nameName = "\"name\"";
            public static readonly String nameIsActual = "\"isActual\"";

            public static readonly String phraseTable = "\"phrase\"";
            public static readonly String phraseId = "\"id\"";
            public static readonly String phraseName = "\"name\"";
            public static readonly String phraseNameId = "\"nameId\"";
            public static readonly String phraseIsActual = "\"isActual\"";

            public static readonly String siteTable = "\"site\"";
            public static readonly String siteId = "\"id\"";
            public static readonly String siteName = "\"name\"";
            public static readonly String siteURL = "\"url\"";
            public static readonly String siteIsActual = "\"isActual\"";

            public static readonly String pageTable = "\"page\"";
            public static readonly String pageId = "\"id\"";
            public static readonly String pageURL = "\"url\"";
            public static readonly String pageDateCreation = "\"dateCreation\"";
            public static readonly String pageSiteId = "\"siteId\"";

            public static readonly String cubeTable = "\"cube\"";
            public static readonly String cubeId = "\"id\"";
            public static readonly String cubeNameId = "\"nameId\"";
            public static readonly String cubePageId = "\"pageId\"";
            public static readonly String cubeDate = "\"date\"";
            public static readonly String cubeFact = "\"fact\"";
            public static readonly String cubeMaxId = "\"maxId\"";

            public static String SelectAllNames()
            {
                return String.Format("select {0}, {1} from {2} where {3} = true", nameId, nameName, nameTable, nameIsActual);
            }
            public static String SelectPhrasesDependsOnName(int nameId)
            {
                return String.Format("select {0}, {1} from {2} where {3} = {4} and {5} = true", 
                    phraseId, phraseName, phraseTable, phraseNameId, nameId.ToString(), phraseIsActual);
            }
            public static String SelectAllSites()
            {
                return String.Format("select {0}, {1}, {2} from {3} where {4} = true", siteId, siteName, siteURL, siteTable, siteIsActual);
            }
            public static String SelectPagesDependsOnSite(int siteId)
            {
                return String.Format("select {0}, {1} from {2} where {3} = {4}", pageId, pageURL, pageTable, pageSiteId, siteId.ToString());
            }
            public static String SelectPagesOfURL(string url)
            {
                return String.Format("select {0} from {1} where {2} = '{3}'", pageId, pageTable, pageURL, url);
            }
            public static String CreateName(String name)
            {
                return String.Format("insert into {0}({1},{2}) values('{3}',true) returning {4}", nameTable, nameName, nameIsActual, name, nameId);
            }
            public static String CreatePhrase(String phrase, int nameId)
            {
                return String.Format("insert into {0}({1},{2},{3}) values('{4}',{5}, true)", phraseTable, phraseName, phraseNameId, phraseIsActual, phrase, nameId.ToString());
            }
            public static String CreateSite(String site, String url)
            {
                return String.Format("insert into {0}({1},{2},{3}) values('{4}','{5}',true) returning {6}", 
                    siteTable, siteName, siteURL, siteIsActual, site, url, siteId);
            }
            public static String CreatePage(String page, int siteId)
            {
                return String.Format("insert into {0}({1},{2},{3}) values('{4}',{5},current_date) returning {6}", 
                    pageTable, pageURL, pageSiteId, pageDateCreation, page, siteId.ToString(), pageId);
            }
            public static String UpdateName(int id, String name)
            {
                return String.Format("update {0} set {1} = '{2}' where {3} = {4}", nameTable, nameName, name, nameId, id);
            }
            public static String UpdatePhrase(int id, String phrase)
            {
                return String.Format("update {0} set {1} = '{2}', {3} = true where {4} = {5}", phraseTable, phraseName, phrase, phraseIsActual, phraseId, id);
            }
            public static String UpdateSite(int id, String site, String url)
            {
                return String.Format("update {0} set {1} = '{2}', {3} = '{4}' where {5} = {6}", siteTable, siteName, site, siteURL, url, siteId, id.ToString());
            }
            public static String DeleteName(int id)
            {
                return String.Format("update {0} set {1} = false where {2} = {3}", nameTable, nameIsActual, nameId, id.ToString());
            }
            public static String DeleteAllPhrasesOfName(int nameId)
            {
                return String.Format("update {0} set {1} = false where {2} = {3}", phraseTable, phraseIsActual, phraseNameId, nameId.ToString());
            }
            public static String DeleteSite(int id)
            {
                return String.Format("update {0} set {1} = false where {2} = {3}", siteTable, siteIsActual, siteId, id.ToString());
            }
            public static String DeleteAllPagesOfSite(int siteId)
            {
                return String.Format("delete from {0} where {1} = {2}", pageTable, pageSiteId, siteId.ToString());
            }
            public static String CubeCommonInformation(int siteId)
            {
                return String.Format("select c.{0},sum(c.{1}) {1} from {2} c join ({3}) mdr on c.{4} = mdr.{5} group by c.{0}", 
                    cubeNameId, cubeFact, cubeTable, GetCubeMaxDateNamePage(siteId), cubeId, cubeMaxId);
            }
            public static String CubeDailyInformation(int siteId)
            {
                return String.Format("select c.{0},count(c.{1}) {1} from {2} c join ({3}) mdr on c.{4} = mdr.{5} where c.{6} in ({7}) group by c.{0}",
                    cubeNameId, cubeFact, cubeTable, GetCubeMaxDateNamePage(siteId), cubeId, cubeMaxId, cubePageId, GetIdTodayPagesOfSite(siteId));
            }
            public static String CubeStatisticInformation(int siteId, int nameId, DateTime startDate, DateTime endDate, int numberPages)
            {
                return String.Format("select p.{0},c.{1} from {2} c join ({3}) mdr on c.{4} = mdr.{5} join {6} p on c.{7} = p.{8} where c.{7} in ({9}) and c.{10} = {11} order by c.{1} desc limit {12}",
                    pageURL, cubeFact, cubeTable, GetCubeMaxDateNamePage(siteId), cubeId, cubeMaxId, pageTable, cubePageId, pageId,
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
            public static String GetTask(int id, int numberRecords)
            {
                return String.Format("select {0},{1},{2} from {3} where {0} >= {4} and {2} in ({5}) order by {0} limit {6}",
                    pageId, pageURL, pageSiteId, pageTable, id.ToString(), GetIdActualSites(), numberRecords.ToString());
            }
            public static String CubeGetActualData(int nameId, int pageId)
            {
                return String.Format("select {0} from {1} where {2} in ({3})", cubeFact, cubeTable, cubeId, GetCubeMaxIdNamePage(nameId, pageId));
            }

            private static String GetCubeMaxDateName(int siteId)
            {
                return String.Format("select {0} {0},max({1}) {2} from {3} where {0} in ({4}) and {5} in ({6}) group by {0}",
                    cubeNameId, cubeId, cubeMaxId, cubeTable, GetIdActualNames(), cubePageId, GetIdPagesOfSite(siteId));
            }
            private static String GetCubeMaxDateNamePage(int siteId)
            {
                return String.Format("select {0} {0},max({1}) {2} from {3} where {0} in ({4}) and {5} in ({6}) group by {0},{7}",
                    cubeNameId, cubeId, cubeMaxId, cubeTable, GetIdActualNames(), cubePageId, GetIdPagesOfSite(siteId), cubePageId);
            }
            private static String GetIdActualNames()
            {
                return String.Format("select {0} from {1} where {2} = true", nameId, nameTable, nameIsActual);
            }
            private static String GetIdActualSites()
            {
                return String.Format("select {0} from {1} where {2} = true", siteId, siteTable, siteIsActual);
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
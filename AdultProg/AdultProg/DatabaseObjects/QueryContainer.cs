using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSLayer
{
    public class QueryContainer
    {
        private static readonly String nameTable = "name";
        private static readonly String nameId = "id";
        private static readonly String nameName = "name";
        private static readonly String nameIsActual = "isActual";

        private static readonly String phraseTable = "phrase";
        private static readonly String phraseId = "id";
        private static readonly String phraseName = "name";
        private static readonly String phraseNameId = "nameId";

        private static readonly String siteTable = "site";
        private static readonly String siteId = "id";
        private static readonly String siteName = "name";
        private static readonly String siteURL = "url";
        private static readonly String siteIsActual = "isActual";

        private static readonly String pageTable = "page";
        private static readonly String pageId = "id";
        private static readonly String pageURL = "url";
        private static readonly String pageSiteId = "siteId";

        private static readonly String cubeTable = "cube";
        private static readonly String cubeId = "id";
        private static readonly String cubeNameId = "nameId";
        private static readonly String cubePageId = "pageId";
        private static readonly String cubeFact = "fact";

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
        public static String SelectCommon()
        {
            //return String.Format("select {2}.{0}, sum({2}.{1}) from {2} join {3}  where {3}.{4} = true and {2}.{5} = (select max{6} from) group by {2}.{0}");
            return "";
        }
    }
}
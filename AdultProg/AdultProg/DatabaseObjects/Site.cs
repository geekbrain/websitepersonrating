using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSLayer
{
    public class Site : DatabaseObject
    {
        private String url;
        private List<Page> pages;

        public String URL { get { return url; } }
        public List<Page> Pages { get { return pages; } }

        public Site(String url) : this(0, url, url, null) { }
        public Site(String name, String url) : this(0, name, url, null) { }
        public Site(int id, String name, String url) : this(0, name, url, null) { }
        public Site(int id, String name, String url, List<Page> pages) : base(id, name)
        {
            if (pages.Equals(null) || pages.Count == 0)
            {
                this.pages = new List<Page>();
                this.pages.Add(new Page(name));
            }
            else
                this.pages = pages;

            if (url.Equals(null) || url.Equals(""))
                throw new Exception("URL must not be empty or null");
            else
                this.url = url;
        }
    }
}
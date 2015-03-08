using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSLayer
{
    public class Page : DatabaseObject
    {
        public String URL { get { return name; } }

        public Page(String url) : this(0, url) { }
        public Page(int id, String url) : base(id, url) { }
    }
}
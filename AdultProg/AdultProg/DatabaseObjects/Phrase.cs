using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSLayer
{
    public class Phrase : DatabaseObject
    {
        public Phrase(String name) : this(0, name) { }
        public Phrase(int id, String name) : base(id, name) { }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSLayer
{
    public class Name : DatabaseObject
    {
        private List<Phrase> phrases;

        public List<Phrase> Phrases { get { return phrases; } }

        public Name(String name) : this(0, name, null) { }
        public Name(int id, String name) : this(0, name, null) { }
        public Name(int id, String name, List<Phrase> phrases) : base(id,name)
        {
            if (phrases.Equals(null) || phrases.Count == 0)
            {
                this.phrases = new List<Phrase>();
                this.phrases.Add(new Phrase(name));
            }
            else
                this.phrases = phrases;
        }
    }
}
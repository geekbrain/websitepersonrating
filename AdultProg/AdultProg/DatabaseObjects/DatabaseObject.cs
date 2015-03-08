using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSLayer
{
    public class DatabaseObject
    {
        protected int id;
        protected String name;

        public int Id { get { return id; } }
        public String Name { get { return name; } }

        private DatabaseObject() { }
        public DatabaseObject(String name) : this(0, name) { }
        public DatabaseObject(int id, String name)
        {
            if (id < 0)
                throw new Exception(String.Format("Param id of {0} must not be less than zero", this.GetType().Name));
            if (name.Equals(null) || name.Equals(""))
                throw new Exception(String.Format("Param name of {0} must not be empty or null", this.GetType().Name));
            this.id = id;
            this.name = name;
        }
    }
}
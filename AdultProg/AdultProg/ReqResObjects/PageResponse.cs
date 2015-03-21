using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace WSLayer
{
    [DataContract]
    public class PageResponse
    {
        [DataMember]
        public int Id;
        [DataMember]
        public String URL;
    }
}
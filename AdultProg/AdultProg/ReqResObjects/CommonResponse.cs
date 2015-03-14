using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace WSLayer
{
    [DataContract]
    public class CommonResponse
    {
        [DataMember]
        public int NameId;
        [DataMember]
        public long Fact;
    }
}
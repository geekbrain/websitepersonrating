using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace WSLayer
{
    [DataContract]
    public class StatisticResponse
    {
        [DataMember]
        public int PageId;
        [DataMember]
        public int Fact;
    }
}
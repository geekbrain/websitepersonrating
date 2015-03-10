﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace WSLayer
{
    [DataContract]
    public class TaskResponse
    {
        [DataMember]
        public int TaskId;
        [DataMember]
        public int SiteId;
        [DataMember]
        public int PageId;
        [DataMember]
        public String PageURL;
        [DataMember]
        public String NameId;
        [DataMember]
        public int Fact;
    }
}
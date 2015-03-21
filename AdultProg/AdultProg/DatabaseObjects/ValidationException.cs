using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSLayer
{
    public class ValidationException : Exception
    {
        private ValidationException() { }
        public ValidationException(String message) : base(message) { }
    }
}
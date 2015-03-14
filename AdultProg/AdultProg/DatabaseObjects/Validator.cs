using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSLayer
{
    public partial class PostgresConnector : IConnector
    {
        private static class Validator
        {

            public static void Validate<T>(List<T> someObjects)
            {
                if (someObjects == null)
                    throw new NullReferenceException();
                else if (someObjects.Count == 0)
                    throw new ValidationException(String.Format("{0} is empty", someObjects.GetType().ToString()));
                else
                {
                    foreach (T someObject in someObjects)
                    {
                        if (someObject == null)
                            throw new ValidationException(String.Format("Item of {0} is null", someObjects.GetType().ToString()));
                    }
                }
            }

            public static void Validate(NameResponse name)
            {
                Validate(name.Id + 1);
                Validate(name.Name);
                Validate<PhraseResponse>(name.Phrases);
            }

            public static void Validate(PhraseResponse phrase)
            {
                Validate(phrase.Id + 1);
                Validate(phrase.Name);
            }

            public static void Validate(SiteResponse site)
            {
                Validate(site.Id + 1);
                Validate(site.Name);
                Validate(site.URL);
            }

            public static void Validate(PageResponse page)
            {
                Validate(page.Id + 1);
                Validate(page.URL);
            }

            public static void Validate(TaskResponse task)
            {
                Validate(task.SiteId);
                Validate(task.NameId);
                Validate(task.PageId + 1);
                Validate(task.PageURL);
                Validate(task.Fact + 1);
            }

            public static void Validate(int id)
            {
                if (id <= 0)
                    throw new ValidationException("Id is not positive");
            }

            public static void Validate(String someString)
            {
                if (someString == null)
                {
                    throw new ValidationException("String is null");
                }
                else if (someString.Equals(""))
                {
                    throw new ValidationException("String is empty");
                }
            }
        }
    }
}
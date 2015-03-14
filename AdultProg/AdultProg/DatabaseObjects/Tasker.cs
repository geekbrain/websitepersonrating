using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSLayer
{
    public class Tasker
    {
        private static volatile Tasker instance;
        private static Object instanceLocker = new Object();
        private static Object instanceMethodsLocker = new Object();

        public static Tasker GetInstance
        {
            private set { }
            get
            {
                lock (instanceLocker)
                {
                    if (instance == null)
                        instance = new Tasker();
                    return instance;
                }
            }
        }

        private const int initialId = 1;
        private const int tasksInRequest = 10;
        private int currentId = initialId;
        
        private Tasker() { }

        public void ResetId()
        {
            lock (instanceMethodsLocker)
            {
                currentId = initialId;
            }
        }

        public int GetNumberIdInTask()
        {
            return tasksInRequest;
        }

        public int GetNextId()
        {
            return currentId;
        }

        public void SetId(int id)
        {
            lock (Tasker.instanceMethodsLocker)
            { 
                currentId = id; 
            }
        }
    }
}
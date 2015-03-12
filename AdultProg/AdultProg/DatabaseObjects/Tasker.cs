using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WSLayer
{
    public class Tasker
    {
        private static volatile Tasker instance;
        private static Object syncInstanse = new Object();
        private static Object syncInstanseMethods = new Object();

        public static Tasker GetInstance
        {
            private set { }
            get
            {
                lock (syncInstanse)
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

        public List<TaskRequest> GetTask(IConnector connector)
        {
            lock(syncInstanseMethods)
            {
                List<TaskRequest> tasks;
                try
                {
                    tasks = connector.RequestTask(currentId, tasksInRequest);
                    if (tasks == null || tasks.Count == 0)
                    {
                        currentId = initialId;
                        tasks = connector.RequestTask(currentId, tasksInRequest);
                    }
                }
                catch (Exception ex) { throw ex; }
                return tasks;
            }
        }

        public void SetTask(List<TaskResponse> tasks, IConnector connector)
        {
            lock (syncInstanseMethods)
            {
                try
                {
                    if (tasks == null || tasks.Count == 0)
                        throw new Exception("Tasks must not be null or empty");
                    else if (connector == null)
                        throw new Exception("Connector must not be null");
                    else
                    {
                        connector.ResponseTask(tasks);
                        //foreach (TaskResponse task in tasks)
                        //{
                        //    if (task != null)
                        //        currentTasks.Remove(task.PageId);
                        //}
                    }
                }
                catch (Exception ex) { throw ex; }
            }
        }
    }
}
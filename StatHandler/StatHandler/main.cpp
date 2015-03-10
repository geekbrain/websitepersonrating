#include <iostream>
#include <string>

#include "TaskManager.h"

int main(int argc, const char * argv[]) {
    
    TaskManager taskManager;
    FindTask task1("http://lenta.ru/news/2015/03/10/dogcancer/");
    task1.AddNameForFind("Фрэнки");
    task1.AddNameForFind("Путин");
    task1.AddNameForFind("Donald");
    task1.SetIsNeedFindURLs(true);
    
    FindTask task2("http://lenta.ru/news/2015/03/10/dogcancer/");
    task2.AddNameForFind("Фрэнки");
    task2.AddNameForFind("Путин");
    task2.AddNameForFind("Donald");
    task2.SetIsNeedFindURLs(true);
    
    taskManager.AddTask(task1);
    taskManager.AddTask(task2);
    
    taskManager.SolveTasks(true);
    
    const std::vector<FindTaskResult>& res = taskManager.GetResults();
    
    return 0;
}



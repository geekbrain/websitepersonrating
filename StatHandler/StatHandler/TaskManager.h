#ifndef __StatHandler__TaskManager__
#define __StatHandler__TaskManager__

#include <stdio.h>
#include <string>
#include <set>
#include <map>
#include <vector>

struct SimpleTask {
    
    SimpleTask(const std::string& _url, int _id = -1)
    : url(_url)
    , id(_id){}
    
    const std::string& GetURL() const {
        return url;
    }
    
    int GetID() const {
        return id;
    }
    
private:
    std::string url;
    int         id;
};

struct FindTask : public SimpleTask {
    typedef SimpleTask baseclass;
    
    FindTask(const std::string& _url, int _id = -1)
    :baseclass(_url, _id)
    ,find_urls(false) {}
    
    void SetIsNeedFindURLs(bool val) {
        find_urls = val;
    }
    
    bool GetIsNeedFindURLs() const {
        return find_urls;
    }
    
    void AssignNamesForFind(const std::vector<std::string>& names_dictionary) {
        this->names_dictionary = names_dictionary;
    }
    
    void AddNameForFind(const std::string& name) {
        this->names_dictionary.push_back(name);
    }
    
    const std::vector<std::string>& GetNamesForFind() const {
        return this->names_dictionary;
    }
private:
    std::vector<std::string> names_dictionary;
    bool find_urls;
};

enum TaskResultCode {
    TASK_RESULT_CODE_SUCCESS = 0,
    TASK_RESULT_CODE_ERROR
};

struct FindTaskResult {
    std::string                     URL;
    TaskResultCode                  result_code;
    std::map<std::string, size_t>   frequency_dictionary;
    std::vector<std::string>        new_tasks;
};

bool GetURLHost(const std::string& URL, std::string& host);

class TaskManager {
public:
    void AddTask(const FindTask& task) {
        tasks.push_back(task);
    }
    bool SolveTasks(bool clear_prev_results);
    const std::vector<FindTaskResult>& GetResults() const {
        return results;
    }
    
protected:
    bool    FindAllSubURLs(const std::string& html, const std::string& url, std::vector<std::string>& subURLs);
    size_t  EntiresCount(const std::string& html, const std::string& word);
    
private:
    std::vector<FindTask> tasks;
    std::vector<FindTaskResult> results;
};

#endif

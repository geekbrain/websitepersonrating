#include "TaskManager.h"
#include "HTMLDownloader.h"

bool GetURLHost(const std::string& URL, std::string& host) {

    host.clear();
    size_t start_host_pos = URL.find("//");
    if(start_host_pos == std::string::npos) {
        return false;
    }
    
    start_host_pos += std::string("//").length();
    size_t end_host_pos = URL.find("/", start_host_pos);
    if(end_host_pos != std::string::npos) {
        end_host_pos -= start_host_pos;
    }
    
    host = URL.substr(start_host_pos, end_host_pos);
    return true;
}

bool TaskManager::SolveTasks(bool clear_prev_results) {
    
    if(clear_prev_results) {
        results.clear();
    }
    
    for(std::vector<FindTask>::const_iterator it = tasks.begin(), it_end = tasks.end(); it != it_end; ++it) {
        const FindTask& task = *it;
        const std::string URL = task.GetURL();
        FindTaskResult result;
        result.URL = URL;
        
        std::string html;
        if( getHTMLByURL(URL, html) ) {
            // success get HTML
            std::string host;
            if(GetURLHost(URL, host)) {
                if(task.GetIsNeedFindURLs()) {
                    std::vector<std::string> subURLs;
                    FindAllSubURLs(html, host, subURLs);
                    
                    if(!subURLs.empty()) {
                        result.new_tasks = subURLs;
                    }
                }
            }
            
            // find entires
            const std::vector<std::string>& names_dictionary = task.GetNamesForFind();
            for(std::vector<std::string>::const_iterator it = names_dictionary.begin();
                                                               it != names_dictionary.end(); ++it)
            {
                const std::string word = *it;
                size_t count = EntiresCount(html, word);
                result.frequency_dictionary[word] = count;
            }
        
            result.result_code = TASK_RESULT_CODE_SUCCESS;
        } else {
            result.result_code = TASK_RESULT_CODE_ERROR;
        }
        
        results.push_back(result);
    }
    
    return true;
}

bool TaskManager::FindAllSubURLs(const std::string& html, const std::string& url, std::vector<std::string>& subURLs) {

    // TODO: need realize via PCRE - Perl Compatible Regular Expressions
    return true;
}

size_t TaskManager::EntiresCount(const std::string& html, const std::string& word) {

    if(html.empty()) {
        return 0;
    }
    
    size_t count = 0;
    size_t entire_pos = html.find(word, 0);
    while( entire_pos != std::string::npos ) {
        ++count;
        entire_pos += word.length();
        entire_pos = html.find(word, entire_pos);
    }
    
    return count;
}
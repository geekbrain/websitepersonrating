#include "TaskManager.h"
#include "HTMLDownloader.h"

#include <regex>
#include <iostream>

bool GetURLHost(const std::string& URL, std::string& host) {

    host.clear();
    size_t start_host_pos = URL.find("://");
    if(start_host_pos == std::string::npos) {
        return false;
    }
    
    start_host_pos += std::string("://").length();
    size_t end_host_pos = URL.find("/", start_host_pos);
    if(end_host_pos != std::string::npos) {
        end_host_pos -= 0/*start_host_pos*/;
    }
    
    host = URL.substr(/*start_host_pos*/0, end_host_pos);
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

const size_t URL_START_COUNT = 2;
const std::string URL_START[URL_START_COUNT] = {
    "\"http://",
    "href=\"/"
};

const std::string DOUBLE_QUOTES = "\"";

std::string::size_type findURLStartPos(const std::string& text, std::string::size_type start_find_pos, std::string::size_type& url_start_idx)
{
    std::string::size_type url_start_pos = std::string::npos;
    url_start_idx = -1;
    for(unsigned i = 0; i < URL_START_COUNT; ++i) {
        std::string::size_type pos = text.find(URL_START[i], start_find_pos);
        if(pos != std::string::npos) {
            if((url_start_pos == std::string::npos) || (pos < url_start_pos)){
                url_start_pos = pos;
                url_start_idx = i;
            }
        }
    }
    
    return url_start_pos;
}

bool TaskManager::FindAllSubURLs(const std::string& html, const std::string& url, std::vector<std::string>& subURLs)
{
    std::string::size_type url_start_idx = 0;
    std::string::size_type start = findURLStartPos(html, 0, url_start_idx);
    
    while(start != std::string::npos) {
        std::string::size_type end = html.find(DOUBLE_QUOTES, start + URL_START[url_start_idx].length());
        
        std::string host = "";
        if (url_start_idx == 1) {
            host = url;
        }
        
        if(url_start_idx == 0)
            start += DOUBLE_QUOTES.length();
        else if(url_start_idx == 1)
            start += std::string("href=\"").length();
        
        // for only host url
        std::string found_url = html.substr(start, end - start);
        if(url_start_idx == 0) {
            if( found_url.find(url) == std::string::npos ) {
                found_url.clear();
            }
        }
            
            
        if(found_url.length() >= 2) {
            subURLs.push_back(host + found_url);
        }
        
        start = findURLStartPos(html, end + DOUBLE_QUOTES.length(), url_start_idx);
    }
    
    return !subURLs.empty();
}

//bool TaskManager::FindAllSubURLs(const std::string& html, const std::string& url, std::vector<std::string>& subURLs) {
//
//    // TODO: need realize via PCRE - Perl Compatible Regular Expressions
//    //preg_match_all("/<[Aa][\s]{1}[^>]*[Hh][Rr][Ee][Ff][^=]*=[ '\"\s]*([^ \"'>\s#]+)[^>]*>/", $html, $matches);
//    subURLs.clear();
//
//    std::string first_url = find_url(html);
//    
//    std::regex url2("facebook");//(".*\\..*");
//    std::string url_test = ":fb=\"http://www.facebook.com/2008/fbml\"";
//    
//    if(regex_match(url_test, url2)) {
//        std::cout << "It's a url!" << std::endl;
//    } else {
//        std::cout << "Oh snap! It's not a url!" << std::endl;
//    }
//    
//    
//    std::regex e ("http://.*");
//    
//    std::smatch result_urls;
//    std::regex_match (html, result_urls, e);
//    std::cout << "found " << result_urls.size() << " URL matches\n";
//    
//    for (size_t i = 0; i < result_urls.size(); ++i) {
//        std::cout << "[" << result_urls[i] << "] ";
//        subURLs.push_back(result_urls[i]);
//    }
//    
//    return true;
//}

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
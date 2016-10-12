package com.example.administrator.searchdata.db;

import com.example.administrator.searchdata.bean.SearchHistory;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28 0028.
 */
public interface ISearchHistoryDao {
//
    //保存搜索历史
    void saveSearch(String word);
    List<SearchHistory> getSearchHistories(String word);
    void clearSearchHistory();
}

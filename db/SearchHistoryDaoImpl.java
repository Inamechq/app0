package com.example.administrator.searchdata.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.administrator.searchdata.bean.SearchHistory;

import java.util.List;

/**
 * Created by Administrator on 2016/9/28 0028.
 */
public class SearchHistoryDaoImpl implements ISearchHistoryDao {

    public static final String DB_NAME = "search.db";
    public final SearchHistoryDao mDao;

    public SearchHistoryDaoImpl(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        mDao = master.newSession().getSearchHistoryDao();

    }

    @Override
    public void saveSearch(String word) {
        //查看提交的数据是否在数据库存在
        List<SearchHistory> list = mDao.queryBuilder().where(SearchHistoryDao.Properties.Word.eq(word)).build().list();
        if (list.size() == 0) {
            SearchHistory searchHistory = new SearchHistory(word,1);
            mDao.insert(searchHistory);

        } else {
            SearchHistory searchHistory1 = list.get(0);
            searchHistory1.setCount(searchHistory1.getCount() + 1);
            mDao.update(searchHistory1);
//            List<SearchHistory> list1 = mDao.queryBuilder().build().list();
//            for (SearchHistory s:list1){
//               // Log.e("============","=========="+s);
//            }
        }

    }


    @Override
    public List<SearchHistory> getSearchHistories(String word) {
        //获取搜索框字符进行模糊查询
        List<SearchHistory> list = mDao.queryBuilder().where(SearchHistoryDao.Properties.Word.like(word + "%")).build().list();
        return list;
    }

    @Override
    public void clearSearchHistory() {
        //清空历史
        mDao.deleteAll();
    }
    public List<SearchHistory> queryAll(){
        List<SearchHistory> list1 = mDao.queryBuilder().build().list();
        return list1;
    }
}

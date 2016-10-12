package com.example.administrator.searchdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.administrator.searchdata.bean.SearchHistory;
import com.example.administrator.searchdata.db.ISearchHistoryDao;
import com.example.administrator.searchdata.db.SearchHistoryDaoImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {
///
    SearchView searchView;
    ListView listView;
    TextView clear;
    Button button;
    SearchHistoryDaoImpl search;
    public List<SearchHistory> searchHistories=null;
    public MyAdapter myadapter;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = (SearchView) findViewById(R.id.search_text);
        listView= (ListView) findViewById(R.id.list_view_1);
        clear= (TextView) findViewById(R.id.textView);
        button= (Button) findViewById(R.id.submit);
        search = new SearchHistoryDaoImpl(this);
        //searchHistories=search.getSearchHistories("");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.clearSearchHistory();
               searchHistories= search.queryAll();
                myadapter.notifyDataSetChanged();
            }
        });

        myadapter = new MyAdapter();
        listView.setAdapter(myadapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                //存在修改count ，不存在插入
                //SearchHistory searchHistory=new SearchHistory(query,1);
               //Log.e("==========","=========="+searchHistory.getId());
                search.saveSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchHistories = search.getSearchHistories(newText);
                myadapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return searchHistories == null ? 0 : searchHistories.size();
        }

        @Override
        public SearchHistory getItem(int position) {
            return searchHistories.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(android.R.layout.simple_list_item_1, null);
                viewHolder = new ViewHolder(convertView);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            viewHolder.tv.setText(searchHistories.get(position).getWord());
            return convertView;
        }

        class ViewHolder {
            TextView tv;

            public ViewHolder(View view) {
                tv = (TextView) view.findViewById(android.R.id.text1);
                view.setTag(this);
            }
        }
    }
}

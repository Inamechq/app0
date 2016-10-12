package com.example.administrator.searchdata.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by Administrator on 2016/9/28 0028.
 */
@Entity
public class SearchHistory {

    @Id(autoincrement = true)
    private Long id;
    private String word;
    private int count;
    @Generated(hash = 1207205440)
    public SearchHistory(Long id, String word, int count) {
        this.id = id;
        this.word = word;
        this.count = count;
    }

    public SearchHistory(String word, int count) {
        this.word = word;
        this.count = count;
    }

    @Generated(hash = 1905904755)
    public SearchHistory() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getWord() {
        return this.word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SearchHistory{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", count=" + count +
                '}';
    }
}

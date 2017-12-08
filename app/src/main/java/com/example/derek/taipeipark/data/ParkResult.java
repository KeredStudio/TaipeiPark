package com.example.derek.taipeipark.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by derek_chang on 2017/12/7.
 */

public class ParkResult {
    int offset;
    int limit;
    int count;
    String sort;
    List<ParkInfo> results = new ArrayList<>();

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<ParkInfo> getResults() {
        return results;
    }

    public void setResults(List<ParkInfo> results) {
        this.results = results;
    }
}

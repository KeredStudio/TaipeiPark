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
}

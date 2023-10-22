package org.dsaik65.group05.mapred.ex3;

import java.io.IOException;
import java.util.HashSet;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.IntWritable;

public class NumDistinctReducerRound1 extends
        Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

    @Override
    public void reduce(IntWritable key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        HashSet<Integer> unique_list = new HashSet<Integer>();

        for (IntWritable val : values) {
            int int_value = val.get();
            if (!unique_list.contains(int_value)) {
                context.write(key, val);
            }
            unique_list.add(int_value);
        }
    }
}

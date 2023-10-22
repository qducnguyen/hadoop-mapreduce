package org.dsaik65.group05.mapred.ex3;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.IntWritable;

public class NumDistinctReducerRound3 extends
        Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {

    @Override
    public void reduce(IntWritable key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        int count = 0;
        for (IntWritable val : values) {
            count += 1;
        }
        context.write(new IntWritable(0), new IntWritable(count));
    }
}

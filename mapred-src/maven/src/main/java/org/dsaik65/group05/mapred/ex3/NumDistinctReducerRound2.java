package org.dsaik65.group05.mapred.ex3;

import java.io.IOException;


import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.IntWritable;

public class NumDistinctReducerRound2 extends 
    Reducer<IntWritable,IntWritable,IntWritable,IntWritable> {

        @Override
        public void reduce(IntWritable key, Iterable<IntWritable> values, Context context
                        ) throws IOException, InterruptedException {
        

        for (IntWritable val : values) {
            context.write(key, val);
            break;
        }
  }
}

       
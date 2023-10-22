package org.dsaik65.group05.mapred.ex6;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

public class MatrixVectorProductReducerRound2 extends 
    Reducer<IntWritable,LongWritable,IntWritable,LongWritable> {

        @Override
        public void reduce(IntWritable key, Iterable<LongWritable> values, Context context
                        ) throws IOException, InterruptedException {
            
            long sum = 0;
            
            for (LongWritable val : values) {
                sum += val.get();
            }
            
            context.write(key, new LongWritable(sum));

  }
}

       
package org.dsaik65.group05.mapred.ex2;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

public class MeanCalculatorReducerRound1 extends
        Reducer<IntWritable, IntWritable, IntWritable, LongWritable> {

    final private IntWritable zero = new IntWritable(0);
    private LongWritable result = new LongWritable();

    @Override
    public void reduce(IntWritable key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        long sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        result.set(sum);
        context.write(zero, result);
    }
}

package org.dsaik65.group05.mapred.ex2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;

public class MeanCalculatorReducerRound2 extends
        Reducer<IntWritable, LongWritable, IntWritable, DoubleWritable> {

    final private IntWritable zero = new IntWritable(0);
    private DoubleWritable result = new DoubleWritable();

    @Override
    public void reduce(IntWritable key, Iterable<LongWritable> values, Context context)
            throws IOException, InterruptedException {

        Configuration conf = context.getConfiguration();
        // Asume N is square number -> square(N) is int number
        final int N = Integer.parseInt(conf.get("N"));
        long sum = 0;
        for (LongWritable val : values) {
            sum += val.get();
        }
        result.set((double) sum / N);
        context.write(zero, result);
    }
}
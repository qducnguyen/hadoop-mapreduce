package org.dsaik65.group05.mapred.ex6;

import java.io.IOException;

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;

public class MatrixVectorProductReducerRound1 extends 
    Reducer<CustomIntArrayWritable,CustomIntArrayWritable,IntWritable,LongWritable> {

        @Override
        public void reduce(CustomIntArrayWritable key, Iterable<CustomIntArrayWritable> values, Context context
                        ) throws IOException, InterruptedException {
            
            Configuration conf = context.getConfiguration();
            
            final int N = Integer.parseInt(conf.get("N"));

            IntWritable[] keyValues = key.get();
            IntWritable key_output =  keyValues[0];
            int[] arr = new int[N];

            for (CustomIntArrayWritable val : values) {
                IntWritable[] valValues = val.get();
                if(valValues[1].get() == -1){
                    if (arr[valValues[0].get()] == 0){
                        arr[valValues[0].get()] = valValues[2].get();
                    }
                    else{
                        arr[valValues[0].get()] = arr[valValues[0].get()] * valValues[2].get();
                    }
                }

                else{
                    if (arr[valValues[1].get()] == 0){
                        arr[valValues[1].get()] = valValues[1].get();
                    }
                    else{
                        arr[valValues[1].get()] = arr[valValues[1].get()] * valValues[2].get();
                    }
                }
            }
            
            long sum = 0;
            for (int i =0; i<N;i++){
                sum += arr[i];
            }

            context.write(key_output, new LongWritable(sum));

  }
}

       
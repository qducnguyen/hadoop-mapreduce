package org.dsaik65.group05.mapred.ex6;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class MatrixVectorProductMapperRound1 extends Mapper<Object, Text, CustomIntArrayWritable, CustomIntArrayWritable>{
        
        @Override
        public void map(Object key, Text value, Context context
                                        ) throws IOException, InterruptedException {
        
                Configuration conf = context.getConfiguration();

                final int M = Integer.parseInt(conf.get("M"));
                final int N = Integer.parseInt(conf.get("N"));
                final double N_Square_Root = Math.sqrt((double)N);
                String[] tokens = value.toString().trim().split(",");

                int firstValue = Integer.parseInt(tokens[1]);
                int secondValue = Integer.parseInt(tokens[2]);
                int thirdValue = Integer.parseInt(tokens[3]);

                IntWritable[] temp_key = new IntWritable[2];
                IntWritable[] temp_value = new IntWritable[3];

                if (secondValue == -1){
                    temp_key[1] = new IntWritable((int)Math.floor(((double)firstValue) / N_Square_Root));
                    for(int i =0; i < M; i++){
                        temp_key[0] = new IntWritable(i);

                        temp_value[0] = new IntWritable(firstValue);
                        temp_value[1] = new IntWritable(-1);
                        temp_value[2] = new IntWritable(thirdValue);

                        context.write(new CustomIntArrayWritable(temp_key), new CustomIntArrayWritable(temp_value));
                    }
                }
                else{
                    temp_key[0] = new IntWritable(firstValue);
                    temp_key[1] = new IntWritable((int)Math.floor(((double)secondValue) / N_Square_Root));
                    
                    temp_value[0] = new IntWritable(firstValue);
                    temp_value[1] = new IntWritable(secondValue);
                    temp_value[2] = new IntWritable(thirdValue);

                    context.write(new CustomIntArrayWritable(temp_key), new CustomIntArrayWritable(temp_value));

                }
            }
        }


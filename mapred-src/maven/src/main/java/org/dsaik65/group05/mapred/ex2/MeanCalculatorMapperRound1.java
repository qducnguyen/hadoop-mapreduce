package org.dsaik65.group05.mapred.ex2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;

public class MeanCalculatorMapperRound1
			 extends Mapper<Object, Text, IntWritable, IntWritable>{
		
		private IntWritable key_output = new IntWritable();
		private IntWritable value_output = new IntWritable();
		
		@Override
		public void map(Object key, Text value, Context context
										) throws IOException, InterruptedException {
		
				Configuration conf = context.getConfiguration();
				// Asume N is square number -> square(N) is int number
				final int N = Integer.parseInt(conf.get("N"));
				final int N_Square_Root = (int)Math.sqrt((double)N);
				
				String[] tokens = value.toString().trim().split("\t");
				int index = Integer.parseInt(tokens[0]);
				int x = Integer.parseInt(tokens[1]);
				
				key_output.set(index % N_Square_Root);
				value_output.set(x);

				context.write(key_output, value_output);
			}
		}

package org.dsaik65.group05.mapred.ex3;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class NumDistinctMapperRound3 extends Mapper<IntWritable, IntWritable, IntWritable, IntWritable> {

	@Override
	public void map(IntWritable key, IntWritable value, Context context) throws IOException, InterruptedException {

		context.write(value, key);
	}
}

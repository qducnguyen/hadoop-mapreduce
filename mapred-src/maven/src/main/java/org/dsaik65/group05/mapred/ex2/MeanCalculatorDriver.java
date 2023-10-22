package org.dsaik65.group05.mapred.ex2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;

import org.apache.hadoop.mapreduce.Job;

public class MeanCalculatorDriver {
    public static void main(String[] args) throws Exception{
        
        if(args.length != 3){
            System.out.println("Input Arguments: input_path output_path N");
        }


        Configuration conf = new Configuration();
        Path out = new Path(args[1]);

        final int N = Integer.valueOf(args[2]);
        final int N_Square_Root = (int)Math.sqrt((double)N);
        conf.set("N", String.valueOf(N));

        Job job1 = Job.getInstance(conf, "Ex1 Mean Calculator Round 1");
        job1.setJarByClass(MeanCalculatorDriver.class);
        job1.setMapperClass(MeanCalculatorMapperRound1.class);
        job1.setOutputKeyClass(IntWritable.class);
        job1.setOutputValueClass(LongWritable.class);
        job1.setMapOutputKeyClass(IntWritable.class);
        job1.setMapOutputValueClass(IntWritable.class);
        job1.setNumReduceTasks(N_Square_Root);
        job1.setReducerClass(MeanCalculatorReducerRound1.class);
        job1.setOutputFormatClass(SequenceFileOutputFormat.class);

        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(out, "out1"));

        if(!job1.waitForCompletion(true)){
            System.exit(1);
        }

        Job job2 = Job.getInstance(conf, "Ex1 Mean Calculator Round 2");
        job2.setJarByClass(MeanCalculatorDriver.class);
        job2.setInputFormatClass(SequenceFileInputFormat.class);
        job2.setNumReduceTasks(1);
        job2.setReducerClass(MeanCalculatorReducerRound2.class);
        job2.setMapOutputKeyClass(IntWritable.class);
        job2.setMapOutputValueClass(LongWritable.class);
        FileInputFormat.addInputPath(job2, new Path(out, "out1"));
        FileOutputFormat.setOutputPath(job2, new Path(out, "out2"));
        System.exit(job2.waitForCompletion(true) ? 0 : 1);
    }
}

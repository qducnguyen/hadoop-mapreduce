package org.dsaik65.group05.mapred.ex3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

public class NumDistinctDriver {
    public static void main(String[] args) throws Exception {

        if (args.length != 3) {
            System.out.println("Input Arguments: input_path output_path N");
        }

        Configuration conf = new Configuration();
        Path out = new Path(args[1]);

        final int N = Integer.valueOf(args[2]);
        final int N_Square_Root = (int) Math.ceil(Math.sqrt((double) N));

        conf.set("N", String.valueOf(N));

        Job job1 = Job.getInstance(conf, "Ex3 Num Distinct Integer Round 1");
        job1.setJarByClass(NumDistinctDriver.class);
        job1.setMapperClass(NumDistinctMapperRound1.class);
        job1.setOutputKeyClass(IntWritable.class);
        job1.setOutputValueClass(IntWritable.class);
        job1.setNumReduceTasks(N_Square_Root);
        job1.setReducerClass(NumDistinctReducerRound1.class);
        job1.setOutputFormatClass(SequenceFileOutputFormat.class);

        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(out, "out1"));

        if (!job1.waitForCompletion(true)) {
            System.exit(1);
        }

        Job job2 = Job.getInstance(conf, "Ex3 Num Distinct Integer Round 2");
        job2.setJarByClass(NumDistinctDriver.class);
        job2.setInputFormatClass(SequenceFileInputFormat.class);
        job2.setMapperClass(NumDistinctMapperRound2.class);
        job2.setReducerClass(NumDistinctReducerRound2.class);
        job2.setOutputKeyClass(IntWritable.class);
        job2.setOutputValueClass(IntWritable.class);
        job2.setNumReduceTasks(N_Square_Root);
        job2.setOutputFormatClass(SequenceFileOutputFormat.class);

        FileInputFormat.addInputPath(job2, new Path(out, "out1"));
        FileOutputFormat.setOutputPath(job2, new Path(out, "out2"));

        if (!job2.waitForCompletion(true)) {
            System.exit(1);
        }

        Job job3 = Job.getInstance(conf, "Ex3 Num Distinct Integer Round 3");

        job3.setJarByClass(NumDistinctDriver.class);
        job3.setInputFormatClass(SequenceFileInputFormat.class);
        job3.setMapperClass(NumDistinctMapperRound3.class);
        job3.setReducerClass(NumDistinctReducerRound3.class);
        job3.setOutputKeyClass(IntWritable.class);
        job3.setOutputValueClass(IntWritable.class);
        job3.setNumReduceTasks(N_Square_Root);
        job3.setOutputFormatClass(SequenceFileOutputFormat.class);

        FileInputFormat.addInputPath(job3, new Path(out, "out2"));
        FileOutputFormat.setOutputPath(job3, new Path(out, "out3"));

        if (!job3.waitForCompletion(true)) {
            System.exit(1);
        }

        Job job4 = Job.getInstance(conf, "Ex3 Num Distinct Integer Round 4");

        job4.setJarByClass(NumDistinctDriver.class);
        job4.setInputFormatClass(SequenceFileInputFormat.class);
        job4.setReducerClass(NumDistinctReducerRound4.class);
        job4.setOutputKeyClass(IntWritable.class);
        job4.setOutputValueClass(IntWritable.class);
        job4.setNumReduceTasks(1);

        FileInputFormat.addInputPath(job4, new Path(out, "out3"));
        FileOutputFormat.setOutputPath(job4, new Path(out, "out4"));

        System.exit(job4.waitForCompletion(true) ? 0 : 1);
    }
}

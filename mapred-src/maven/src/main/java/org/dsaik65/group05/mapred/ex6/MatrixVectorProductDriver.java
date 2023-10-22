package org.dsaik65.group05.mapred.ex6;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

public class MatrixVectorProductDriver {
        public static void main(String[] args) throws Exception{
        
        if(args.length != 4){
            System.out.println("Input Arguments: input_path output_path M N");
        }

        Configuration conf = new Configuration();
        Path out = new Path(args[1]);

        final int M = Integer.valueOf(args[2]);
        final int N = Integer.valueOf(args[3]);
        
        conf.set("M", String.valueOf(M));
        conf.set("N", String.valueOf(N));

        Job job1 = Job.getInstance(conf, "Ex6 Matric Vector Product Round 1");
        job1.setJarByClass(MatrixVectorProductDriver.class);
        job1.setMapperClass(MatrixVectorProductMapperRound1.class);
        job1.setReducerClass(MatrixVectorProductReducerRound1.class);
        job1.setOutputKeyClass(IntWritable.class);
        job1.setOutputValueClass(LongWritable.class);
        job1.setMapOutputKeyClass(CustomIntArrayWritable.class);
        job1.setMapOutputValueClass(CustomIntArrayWritable.class);
        job1.setOutputFormatClass(SequenceFileOutputFormat.class);

        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(out, "out1"));
        if(!job1.waitForCompletion(true)){
            System.exit(1);
        }

        Job job2 = Job.getInstance(conf, "Ex6 Matric Vector Product Round 2");
        job2.setJarByClass(MatrixVectorProductDriver.class);
        job2.setInputFormatClass(SequenceFileInputFormat.class);
        job2.setReducerClass(MatrixVectorProductReducerRound2.class);
        job2.setNumReduceTasks(1);
        job2.setOutputKeyClass(IntWritable.class);
        job2.setOutputValueClass(LongWritable.class);

        FileInputFormat.addInputPath(job2, new Path(out, "out1"));
        FileOutputFormat.setOutputPath(job2, new Path(out, "out2"));
        System.exit(job2.waitForCompletion(true) ? 0 : 1);
    }
}


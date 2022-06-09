package com.hadoop.wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class WordCountDriver {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        //获取job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //设置jar路径
        job.setJarByClass(WordCountDriver.class);

        //关联mapper和reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //设置map输出kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置最终输出的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置输入路径和输出路径
        FileInputFormat.setInputPaths(job, new Path("/Users/truster/Codes/wordCount.txt"));
        FileOutputFormat.setOutputPath(job, new Path("/Users/truster/Codes/output"));

        //提交job
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}

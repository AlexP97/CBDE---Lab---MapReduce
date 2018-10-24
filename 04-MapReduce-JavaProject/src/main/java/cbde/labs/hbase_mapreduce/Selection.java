package cbde.labs.hbase_mapreduce;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import cbde.labs.hbase_mapreduce.CartesianProduct.CartesianMapper;
import cbde.labs.hbase_mapreduce.CartesianProduct.CartesianReducer;

public class Selection extends JobMapReduce {
    
	//mirar el projection y será igual cogiendo type 3 y clave usar 1,2,3,....
	
	
	public Selection() {
		this.input = null;
		this.output = null;
	}
	
	public static class SelectionMapper extends Mapper<Text, Text, IntWritable, Text> {
		
		private static int N = 100;
		
		private int newKey = 0;
		
		public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
			// Obtain the parameters sent during the configuration of the job
			String where = context.getConfiguration().getStrings("where")[0];
			String valor = context.getConfiguration().getStrings("valor")[0];
			// Since the value is a CSV, just get the lines split by commas
			String[] arrayValues = value.toString().split(",");
			String whereValue = Utils.getAttribute(arrayValues, where);
			// Do the cartesian product and emit it
			if (whereValue.equals(valor)) {
				context.write(new IntWritable(this.newKey), value);
				newKey++;
			}
		}
		
	}
	
	public boolean run() throws IOException, ClassNotFoundException, InterruptedException {
		Configuration configuration = new Configuration();
		// Define the new job and the name it will be given
		Job job = Job.getInstance(configuration, "Selection");
		configureJob(job,this.input, this.output);
	    // Let's run it!
	    return job.waitForCompletion(true);
	}

    public static void configureJob(Job job, String pathIn, String pathOut) throws IOException, ClassNotFoundException, InterruptedException {
        job.setJarByClass(Selection.class);

        job.setMapperClass(SelectionMapper.class);
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);
        
        
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);
        
        job.setInputFormatClass(SequenceFileInputFormat.class);
        FileInputFormat.addInputPath(job, new Path(pathIn));
        FileOutputFormat.setOutputPath(job, new Path(pathOut));
        job.getConfiguration().setStrings("where", "type");
        job.getConfiguration().setStrings("valor", "type_1"); 
    }
}

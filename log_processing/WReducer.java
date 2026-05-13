import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WReducer extends Reducer<Text, IntWritable, Text, IntWritable> 
{
	private int maxcount = 0;
	private Text maxword = new Text();
	
	public void reduce(Text word, Iterable<IntWritable> values, Context con) throws IOException, InterruptedException
	{		
		int sum = 0;
		
		for(IntWritable value:values)
		{
			sum += value.get();
		}
			
		if(maxcount < sum)
		{
			maxcount = sum;
			maxword.set(word.toString());
		}		
	}
	
	protected void cleanup(Context con) throws IOException, InterruptedException
	{
		con.write(maxword, new IntWritable(maxcount));		
	}
}
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class WMapper extends Mapper<LongWritable, Text, Text, IntWritable>
{
    private final static IntWritable one = new IntWritable(1);
    private Text wordText = new Text();
    public void map(LongWritable key, Text value, Context con) throws IOException, InterruptedException
    {
        String[] words = value.toString().split(" ");
        for(String word : words)
        {
            wordText.set(word);
            con.write(wordText, one);
        }
    }
}
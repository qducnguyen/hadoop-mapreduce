package org.dsaik65.group05.mapred.ex6;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableFactories;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Stable;

@Public
@Stable
public class CustomIntArrayWritable implements WritableComparable<CustomIntArrayWritable> {

    private IntWritable[] values;


    public CustomIntArrayWritable(){
    }

    public CustomIntArrayWritable(IntWritable[] values) {
        this.values = values;
    }

    public IntWritable[] get() {
        return this.values;
    }

    @Override
    public String toString() {
        IntWritable[] values = get();
        String result = "(";
        for (IntWritable value : values){
            result += value.toString();
            result += ",";
        }
        return result.substring(0, result.length() - 1)  + ")";
    }

    @Override
    public int hashCode() {
        // only care about the second one
        IntWritable[] values = get(); 
        return values[0].get();
    }

    @Override
    public int compareTo(CustomIntArrayWritable o) {
        // only care two first one

        IntWritable[]  thisValues = this.get();
        IntWritable[]  thatValues = o.get();
        int firstThisValue = thisValues[0].get();
        int secondThisValue = thisValues[1].get();
        int firstThatValue = thatValues[0].get();
        int secondThatValue = thatValues[1].get();

        if (firstThisValue < firstThatValue){
            return -1;
        }
        else if (firstThisValue > firstThatValue){
            return 1;
        }
        else if (secondThisValue < secondThatValue){
            return -1;
        }
        else if (secondThisValue > secondThatValue){
            return 1;
        }
        else {
            return 0;
        }

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.values = new IntWritable[in.readInt()];
  
        for(int i = 0; i < this.values.length; ++i) {
           Writable value = WritableFactories.newInstance(IntWritable.class);
           value.readFields(in);
           this.values[i] = (IntWritable) value;
        }
  
     }
  
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(this.values.length);

        for(int i = 0; i < this.values.length; ++i) {
            this.values[i].write(out);
      }
    }
}

package aos.dev;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by oshchepkovayu on 15.04.16.
 */
class ByteRecordWriter<K, V> extends RecordWriter<K, V> {
    private DataOutputStream out;
    public ByteRecordWriter(DataOutputStream out) {
        this.out = out;
    }
    public synchronized void write(K key, V value) throws IOException {
        boolean nullValue = value == null || value instanceof NullWritable;
        if (!nullValue) {
            BytesWritable bw = new BytesWritable((byte[]) value);
            out.write(bw.copyBytes());
        }
    }
    @Override
    public synchronized void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        out.close();
    }

}
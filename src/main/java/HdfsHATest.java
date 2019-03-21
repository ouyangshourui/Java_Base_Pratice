import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HdfsHATest {
    public static void main(String[] args) throws Exception{

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hzwtest");
        conf.set("dfs.nameservices", "hzwtest");
        conf.set("dfs.ha.namenodes.hzwtest", "nn1,nn2");
        conf.set("dfs.namenode.rpc-address.hzwtest.nn1", "bigdata-platform-test-01:8020");
        conf.set("dfs.namenode.rpc-address.hzwtest.nn2", "bigdata-platform-test-02:8020");
        //conf.setBoolean(name, value);
        conf.set("dfs.client.failover.proxy.provider.hzwtest", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        FileSystem fs = FileSystem.get(new URI("hdfs:///tmp"), conf, "ourui");

        int i =50   ;
        while (true){
            InputStream in =new FileInputStream("/Users/ouyangshourui/vpn.sh");
            OutputStream out = fs.create(new Path("/tmp/eclipse"+i));
            fs.delete(new Path("/tmp/eclipse1"+i),true);
            IOUtils.copyBytes(in, out, 4096, true);
            Thread.sleep(3*1000);
            i++;
        }



    }
}

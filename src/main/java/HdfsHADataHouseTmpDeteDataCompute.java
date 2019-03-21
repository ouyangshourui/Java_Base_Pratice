import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HdfsHADataHouseTmpDeteDataCompute {
    public static void main(String[] args) throws Exception{

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://HZWONE");
        conf.set("dfs.nameservices", "HZWONE");
        conf.set("fs.hdfs.impl","org.apache.hadoop.hdfs.DistributedFileSystem");
        conf.set("dfs.ha.namenodes.HZWONE", "nn1,nn2");
        conf.set("dfs.namenode.rpc-address.HZWONE.nn1", "hadoop-master-01:8020");
        conf.set("dfs.namenode.rpc-address.HZWONE.nn2", "hadoop-master-02:8020");
        //conf.setBoolean(name, value);
        conf.set("dfs.client.failover.proxy.provider.HZWONE", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        String path="/apps/hive/warehouse/"+args[0];
        //String path="/apps/hive/warehouse/fdm";
        FileSystem fs = FileSystem.get(new URI(path), conf, "hive");
        //FSDataInputStream hdfsInStream = fs.open(new Path("hdfs:////apps/hive/warehouse/tmp"));
       // InputStreamReader isr = new InputStreamReader(hdfsInStream, "utf-8");
        RemoteIterator<LocatedFileStatus> fileIter= fs.listFiles(new Path(path),true);
        Map<String,Long> tableTimeStampMap = new HashMap<>();
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String firstName="";
        int i=0;
        try {
        while (fileIter.hasNext()){
            LocatedFileStatus file = fileIter.next();
                if(fs.exists(file.getPath())){
                    String table= file.getPath().toString().split("/")[7];
                    long time=file.getModificationTime();
                    if(tableTimeStampMap.containsKey(table)){
                        if(time>tableTimeStampMap.get(table)){
                            tableTimeStampMap.put(table,time);
                        }
                    }else {
                        i++;
                        //System.out.println(i+":"+table+":"+time);
                        tableTimeStampMap.put(table,time);
                    }
                    if(!firstName.equals(table)){
                        System.out.println(i+":"+table);
                       // tableTimeStampMap.clear();
                    }
                    firstName=table;
                }
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("load data to map finish");
            int j=0;
            for(String key:tableTimeStampMap.keySet()){
                System.out.println(i+"_"+j+":"+key+":"+format.format(tableTimeStampMap.get(key)));
                j++;
            }
        }

    }
}

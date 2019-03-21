//
//import java.io.*;
//import java.nio.channels.FileChannel;
//import java.util.*;
//
////总体思路：将大文件切割为n小文件，每个小文件进行统计后最大堆排序取top K ，写入到结果一个汇总文件。然后对汇总文件进行统计后最大堆排序。
//public class LinuxSortHead{
//    public static void main(String[] args) throws Exception {
//
//        // 写入文件的路径
//        String filePath = "/logfile";
//        // 切分文件的路径
//        String sqlitFilePath = "/tmp/splitpath";
//
//        String resultFilePath = "/tmp/resultfile";
//        //子文件的个数
//        int CountFile = 100;
//        //精度
//        long startNumber = System.currentTimeMillis();
//        ArrayList<String> files=sqlitFile2MultiFiles(filePath, sqlitFilePath, CountFile);
//        System.out.println("文件切割完成,文件分为");
//        // 把每个文件的数据进行排序
//        ArrayList<String> resultFiles=singleFileDataSort(files, CountFile,"Exception",10);
//        System.out.println("每个子文件排序完毕！");
//        //多个文件合并为一个文件；
//        mergeFiles(resultFiles,resultFilePath);
//
//        //统计个数后，放入堆中排序
//
//        String resultPath=ResultDataSort(resultFilePath,10);
//
//
//       // deathDataFile(filePath, sqlitFilePath, countAccuracy, CountFile);
//        System.out.println("整合完毕");
//        long stopNumber = System.currentTimeMillis();
//        System.out.println("耗时" + (stopNumber - startNumber) / 1000 + "毫秒");
//
//
//    }
//
//
//    /**
//     * 将大数据文件切分到另外的多个小文件中
//     *
//     * @param filepath
//     * @param sqlitPath
//     * @param CountFile
//     * @return 切割文件路径
//     * @throws IOException
//     */
//    public static ArrayList<String> sqlitFile2MultiFiles(String filepath, String sqlitPath,
//                                            int CountFile) throws IOException {
//
//        FileWriter fs;
//        ArrayList<String> files= new ArrayList();
//        BufferedWriter fw;
//        FileReader fr = new FileReader(filepath);
//        BufferedReader br = new BufferedReader(fr); // 读取获取整行数据
//        int i = 1;
//        LinkedList WriterLists = new LinkedList();    //初始化文件流对象集合
//        LinkedList fwLists = new LinkedList();
//        for (int j = 1; j <= CountFile; j++) {
//            files.add(sqlitPath+"/"+j+".txt");
//            fs = new FileWriter(sqlitPath+"/"+j+".txt", false);
//            fw = new BufferedWriter(fs);
//            WriterLists.add(fs);
//            fwLists.add(fw);
//        }
//        while (br.ready()) {
//            int count = 1;
//            for (Iterator iterator = fwLists.iterator(); iterator.hasNext(); ) {
//                BufferedWriter type = (BufferedWriter) iterator.next();
//                if (i == count) {
//                    type.write(br.readLine() + "/r/n");
//                    break;
//                }
//                count++;
//
//            }
//            if (i >= CountFile)
//                i = 1;
//            else
//                i++;
//            br.close();
//            fr.close();
//
//            for (Iterator iterator = fwLists.iterator(); iterator.hasNext(); ) {
//                BufferedWriter object = (BufferedWriter) iterator.next();
//                object.close();
//            }
//            for (Iterator iterator = WriterLists.iterator(); iterator.hasNext(); ) {
//                FileWriter object = (FileWriter) iterator.next();
//                object.close();
//            }
//        }
//
//        return files;
//
//
//    }
//
//
//    //使用最大堆排序
//    public static ArrayList<String> singleFileDataSort(ArrayList<String> files, int CountFile,String grepString,int headcount) throws IOException {
//
//        ArrayList<String> resultFiles = new  ArrayList<String>;
//        for (String file:files) {
//            Map<String,Integer> grepMap = new HashMap<>();
//            //构建一个最大堆,默认容量为head个数
//            PriorityQueue<DataCondition> maxHeap = new PriorityQueue<>(headcount,new Comparator<DataCondition>(){
//                @Override
//                public int compare(DataCondition o1, DataCondition o2) {
//                    return o1.count-o2.count;
//                }
//            });
//            FileReader fr = new FileReader(file);
//            BufferedReader br = new BufferedReader(fr);
//
//            try {
//                while (br.ready()) {
//                    String line=br.readLine();
//                    grepMap.put(line,grepMap.getOrDefault(line,0)+1);
//                }
//                //将map数据放入到堆中
//                for(String key:grepMap.keySet()){
//                    DataCondition data = new DataCondition(key, grepMap.get(key));
//                    maxHeap.add(data);
//
//                }
//                // // 对每个文件数据进行排序,取top10，再写入源文件
//                Write2File(maxHeap, file+".result");
//                resultFiles.add(file+".result");
//                br.close();
//                br.close();
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } finally {
//                return resultFiles;
//            }
//
//        }
//
//
//    }
//
//
//    //放入map统计后，使用最大堆进行统计
//
//    public static String ResultDataSort(String file,int headcount) throws IOException {
//
//        Map<String,Integer> grepMap = new HashMap<>();
//        FileReader fr = new FileReader(file);
//        BufferedReader br = new BufferedReader(fr);
//        PriorityQueue<DataCondition> maxHeap = new PriorityQueue<>(headcount,new Comparator<DataCondition>(){
//            @Override
//            public int compare(DataCondition o1, DataCondition o2) {
//                return o1.count-o2.count;
//            }
//        });
//
//        try {
//            while (br.ready()) {
//                String[] value=br.readLine().split("");
//                String str=value[0];
//                int count=Integer.parseInt(value[0]);
//                grepMap.put(str,grepMap.getOrDefault(str,0)+count);
//            }
//            //将map数据放入到堆中
//            for(String key:grepMap.keySet()){
//                DataCondition data = new DataCondition(key, grepMap.get(key));
//                maxHeap.add(data);
//
//            }
//            // // 对每个文件数据进行排序,取top10，再写入源文件
//            Write2File(maxHeap, file+".result");
//            br.close();
//            br.close();
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }finally {
//
//            return file+".result";
//
//        }
//
//    }
//
//
//    public static void Write2File(PriorityQueue<DataCondition> data, String path) {
//        try {
//            FileWriter fs = new FileWriter(path);
//            BufferedWriter fw = new BufferedWriter(fs);
//            for (Iterator iterator = data.iterator(); iterator.hasNext(); ) {
//                DataCondition object = (DataCondition) iterator.next();
//                fw.write(object.grepString+":"+object.count + "/r/n");
//                System.out.println(object.grepString+":"+object.count + "/r/n");
//            }
//            fw.close();
//            fs.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public static boolean mergeFiles(ArrayList<String> fpaths, String resultPath) {
//        if (fpaths == null || fpaths.size() < 1 ) {
//            return false;
//        }
//
//        File[] files = null;
//        for (int i = 0; i < fpaths.size(); i ++) {
//            files[i] = new File(fpaths.get(i));
//            if (!files[i].exists() || !files[i].isFile()) {
//                return false;
//            }
//        }
//
//        File resultFile = new File(resultPath);
//
//        try {
//            FileChannel resultFileChannel = new FileOutputStream(resultFile, true).getChannel();
//            for (int i = 0; i < fpaths.size(); i ++) {
//                FileChannel blk = new FileInputStream(files[i]).getChannel();
//                resultFileChannel.transferFrom(blk, resultFileChannel.size(), blk.size());
//                blk.close();
//            }
//            resultFileChannel.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return false;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        return true;
//    }
//
//
//    public static class DataCondition {
//        private String grepString;
//        private int count;
//        public String getGrepString() {
//            return grepString;
//        }
//        public DataCondition(String grepString,int count){
//            this.grepString=grepString;
//            this.count=count;
//
//        }
//
//        public int getCount() {
//            return count;
//        }
//
//        public void setGrepString(String grepString) {
//            this.grepString = grepString;
//        }
//
//        public void setCount(int count) {
//            this.count = count;
//        }
//    }
//}
//
//
//
//
//
//
//
//

package tcp;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * Created by Jack Tan on 2015/3/26.
 */
public class MyLog {


    private static boolean debug = true;

    public static void i(String TAG, String msg) {
        if (debug) {
//                Log.i(TAG,msg);
        }
    }
    public static String getSetIPAndNetMaskCmd(String ip,String netmask){
        return "busybox ifconfig eth0 "+ip+" netmask "+ netmask;
    }

    public static String getSetGateWayCmd(String gateway){
        //busybox ip route add  default via 192.168.1.1 dev eth0
        
        String s = "busybox ip route add default via "+gateway+" dev eth0";
        MyLog.i("MainActivity","gateway cmd:"+s);
        return s;
    }
    public static void showIntArray(String TAG, String msg, byte[] array) {
        if (debug) {
            StringBuilder tem = new StringBuilder();
            for (int i = 0; i < array.length; i++) {
                tem.append(array[i]);
            }
//            Log.i(TAG, msg + tem.toString());
        }
    }

    public static int execRootCmdSilent(String paramString) {
        try {
            MyLog.i("MainActivity", "shell cmd:" + paramString);
            Process localProcess = Runtime.getRuntime().exec("su");
            Object localObject = localProcess.getOutputStream();
            DataOutputStream localDataOutputStream = new DataOutputStream((OutputStream) localObject);
            String str = String.valueOf(paramString);
            localObject = str + "\n";//如果少了回车符，则不会执行该命令
            localDataOutputStream.writeBytes((String) localObject);
            localDataOutputStream.flush();
            localDataOutputStream.writeBytes("exit\n");
            localDataOutputStream.flush();
            localProcess.waitFor();

            return 1;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return 0;
    }
    public static void clearGateWay(boolean delDefault) {
        if (delDefault){
            MyLog.i("MainActivity", "del default gateway");
            MyLog.execRootCmdSilent("busybox ip route del default");
        }else {
        }
    }
    public static synchronized String run(String cmd)
            throws IOException {
        String string = null;
        Runtime mRuntime = Runtime.getRuntime();
        try {
//Process中封装了返回的结果和执行错误的结果
            Process mProcess = mRuntime.exec(cmd+"\n");
            BufferedReader mReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()));
            StringBuffer mRespBuff = new StringBuffer();
            char[] buff = new char[1024];
            int ch = 0;
            while((ch = mReader.read(buff)) != -1){
                mRespBuff.append(buff, 0, ch);
            }
            string = mRespBuff.toString();
            mReader.close();
            return string;
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        return string;

    }
    /**
     * Created by Jack on 2015/6/11 0011.
     */
    public static class UnZipFile {
        private static final String TAG = "UnZipFile";

        public static void Unzip(String zipSrcFile, String targetDir) {
            int BUFFER = 4096;
            String strEntry;
            MyLog.i(TAG, "zipSrcFile :" + zipSrcFile + " targetDir File:" + targetDir);
            try {
                BufferedOutputStream dest = null;
                FileInputStream fis = new FileInputStream(zipSrcFile);
                ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
                ZipEntry entry;
                MyLog.i(TAG, "start unZip");
                while ((entry = zis.getNextEntry()) != null) {
                    MyLog.i(TAG, "unZipping.....");
                    try {
                        MyLog.i(TAG, "=" + entry);
                        int count;
                        byte data[] = new byte[BUFFER];
                        strEntry = entry.getName();
                        File entryFile = new File(targetDir +"/"+ strEntry);
                        File entryDir = new File(entryFile.getParent());
                        if (!entryDir.exists()) {
                            MyLog.i(TAG, entryDir.getName() + " not exists,now mkdir this dir");
                        }
                        MyLog.i(TAG, "open FileOutputStream");
                        FileOutputStream fos = new FileOutputStream(entryFile);
                        dest = new BufferedOutputStream(fos, BUFFER);
                        MyLog.i(TAG, "start write");
                        while ((count = zis.read(data, 0, BUFFER)) != -1) {
                            dest.write(data, 0, count);
                            MyLog.i(TAG, "write count = " + count);
                        }
                        dest.flush();
                        dest.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        MyLog.i(TAG, ex.toString());
                    }
                }
                MyLog.i(TAG, "unzip finish!");
                zis.close();

            } catch (Exception cwj) {
                cwj.printStackTrace();
            }
        }
    }


    //复制文件接口
    public static int copyfile(File fromFile, File toFile, Boolean rewrite ) {

        if (!fromFile.exists()) {
            int notFromFile = -1;
            return notFromFile;
        }

        if (!fromFile.isFile()) {
            int fromFileisError = -2;
            return fromFileisError;
        }

        if (!fromFile.canRead()) {
            int fromFilecantRead = -3;
            return fromFilecantRead;
        }
        //mkdirs()建立多级文件夹
        if (!toFile.getParentFile().exists()) {toFile.getParentFile().mkdirs();}

        if (toFile.exists() && rewrite) {toFile.delete();}


        FileInputStream fosfrom = null;
        try {
            fosfrom = new FileInputStream(fromFile);
            FileOutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c); //将内容写到新文件当中
            }
            fosfrom.close();
            fosto.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int copyFileSuccess = 1;
        return copyFileSuccess;
    }


    public static int toInt(byte[] ByteConverToInt) { //4个byte转化为一个int
        if (ByteConverToInt.length != 4) return -1;
        int iOutcome = 0;
        byte bLoop;
        for ( int i =0; i<4 ; i++) {
            bLoop = ByteConverToInt[i];
            iOutcome += (bLoop & 0xFF) << (8 * (3-i));
        }
        return iOutcome;
    }

}



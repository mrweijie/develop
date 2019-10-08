package until;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class regularclear {
    public static void main(String[] args) {
        short [] ccittTable= {(short)0x0000, (short)0x1021, (short)0x2042, (short)0x3063, (short)0x4084, (short)0x50A5, (short)0x60C6, (short)0x70E7,
                    (short)0x8108, (short)0x9129, (short)0xA14A, (short)0xB16B, (short)0xC18C, (short)0xD1AD, (short)0xE1CE, (short)0xF1EF,
                    (short)0x1231, (short)0x0210, (short)0x3273, (short)0x2252, (short)0x52B5, (short)0x4294, (short)0x72F7, (short)0x62D6,
                    (short)0x9339, (short)0x8318, (short)0xB37B, (short)0xA35A, (short)0xD3BD, (short)0xC39C, (short)0xF3FF, (short)0xE3DE,
                    (short)0x2462, (short)0x3443, (short)0x0420, (short)0x1401, (short)0x64E6, (short)0x74C7, (short)0x44A4, (short)0x5485,
                    (short)0xA56A, (short)0xB54B, (short)0x8528, (short)0x9509, (short)0xE5EE, (short)0xF5CF, (short)0xC5AC, (short)0xD58D,
                    (short)0x3653, (short)0x2672, (short)0x1611, (short)0x0630, (short)0x76D7, (short)0x66F6, (short)0x5695, (short)0x46B4,
                    (short)0xB75B, (short)0xA77A, (short)0x9719, (short)0x8738, (short)0xF7DF, (short)0xE7FE, (short)0xD79D, (short)0xC7BC,
                    (short)0x48C4, (short)0x58E5, (short)0x6886, (short)0x78A7, (short)0x0840, (short)0x1861, (short)0x2802, (short)0x3823,
                    (short)0xC9CC, (short)0xD9ED, (short)0xE98E, (short)0xF9AF, (short)0x8948, (short)0x9969, (short)0xA90A, (short)0xB92B,
                    (short)0x5AF5, (short)0x4AD4, (short)0x7AB7, (short)0x6A96, (short)0x1A71, (short)0x0A50, (short)0x3A33, (short)0x2A12,
                    (short)0xDBFD, (short)0xCBDC, (short)0xFBBF, (short)0xEB9E, (short)0x9B79, (short)0x8B58, (short)0xBB3B, (short)0xAB1A,
                    (short)0x6CA6, (short)0x7C87, (short)0x4CE4, (short)0x5CC5, (short)0x2C22, (short)0x3C03, (short)0x0C60, (short)0x1C41,
                    (short)0xEDAE, (short)0xFD8F, (short)0xCDEC, (short)0xDDCD, (short)0xAD2A, (short)0xBD0B, (short)0x8D68, (short)0x9D49,
                    (short)0x7E97, (short)0x6EB6, (short)0x5ED5, (short)0x4EF4, (short)0x3E13, (short)0x2E32, (short)0x1E51, (short)0x0E70,
                    (short)0xFF9F, (short)0xEFBE, (short)0xDFDD, (short)0xCFFC, (short)0xBF1B, (short)0xAF3A, (short)0x9F59, (short)0x8F78,
                    (short)0x9188, (short)0x81A9, (short)0xB1CA, (short)0xA1EB, (short)0xD10C, (short)0xC12D, (short)0xF14E, (short)0xE16F,
                    (short)0x1080, (short)0x00A1, (short)0x30C2, (short)0x20E3, (short)0x5004, (short)0x4025, (short)0x7046, (short)0x6067,
                    (short)0x83B9, (short)0x9398, (short)0xA3FB, (short)0xB3DA, (short)0xC33D, (short)0xD31C, (short)0xE37F, (short)0xF35E,
                    (short)0x02B1, (short)0x1290, (short)0x22F3, (short)0x32D2, (short)0x4235, (short)0x5214, (short)0x6277, (short)0x7256,
                    (short)0xB5EA, (short)0xA5CB, (short)0x95A8, (short)0x8589, (short)0xF56E, (short)0xE54F, (short)0xD52C, (short)0xC50D,
                    (short)0x34E2, (short)0x24C3, (short)0x14A0, (short)0x0481, (short)0x7466, (short)0x6447, (short)0x5424, (short)0x4405,
                    (short)0xA7DB, (short)0xB7FA, (short)0x8799, (short)0x97B8, (short)0xE75F, (short)0xF77E, (short)0xC71D, (short)0xD73C,
                    (short)0x26D3, (short)0x36F2, (short)0x0691, (short)0x16B0, (short)0x6657, (short)0x7676, (short)0x4615, (short)0x5634,
                    (short)0xD94C, (short)0xC96D, (short)0xF90E, (short)0xE92F, (short)0x99C8, (short)0x89E9, (short)0xB98A, (short)0xA9AB,
                    (short)0x5844, (short)0x4865, (short)0x7806, (short)0x6827, (short)0x18C0, (short)0x08E1, (short)0x3882, (short)0x28A3,
                    (short)0xCB7D, (short)0xDB5C, (short)0xEB3F, (short)0xFB1E, (short)0x8BF9, (short)0x9BD8, (short)0xABBB, (short)0xBB9A,
                    (short)0x4A75, (short)0x5A54, (short)0x6A37, (short)0x7A16, (short)0x0AF1, (short)0x1AD0, (short)0x2AB3, (short)0x3A92,
                    (short)0xFD2E, (short)0xED0F, (short)0xDD6C, (short)0xCD4D, (short)0xBDAA, (short)0xAD8B, (short)0x9DE8, (short)0x8DC9,
                    (short)0x7C26, (short)0x6C07, (short)0x5C64, (short)0x4C45, (short)0x3CA2, (short)0x2C83, (short)0x1CE0, (short)0x0CC1,
                    (short)0xEF1F, (short)0xFF3E, (short)0xCF5D, (short)0xDF7C, (short)0xAF9B, (short)0xBFBA, (short)0x8FD9, (short)0x9FF8,
                    (short)0x6E17, (short)0x7E36, (short)0x4E55, (short)0x5E74, (short)0x2E93, (short)0x3EB2, (short)0x0ED1, (short)0x1EF0
        };
    }


    /**
     *在指定文件夹新建shell脚本文件，并执行
     *
     */
    public void clear(){
        //获取系统信息
        String osName = System.getProperty("os.name").toLowerCase();
        //系统为linux
        if (osName.startsWith("linux")) {
            String path = "/usr/clear.sh";
            //判断文件是否存在，不存在就新建
            File file = new File(path);
            if (!file.exists()) {
                try {
                    //新建文件
                    file.createNewFile();
                    //往File写数据
                    FileWriter fileWriter = new FileWriter(path);
                    //因为不同系统换行符号不一致，所以使用BufferedWriter内部定义的换行.newLine()方法，这样就不用在意换行
                    BufferedWriter bw = new BufferedWriter(fileWriter);
                    bw.write("find /opt/avmedia/apache-tomcat-8.5.16/logs/ -mtime +2 -name \"localhost.*.log\" -exec rm -rf {} \\;");
                    bw.newLine();
                    bw.write("find /opt/avmedia/apache-tomcat-8.5.16/logs/ -mtime +2 -name \"localhost_access_log.*.txt\" -exec rm -rf {} \\;");
                    bw.newLine();
                    bw.write("find /opt/avmedia/apache-tomcat-8.5.16/logs/ -mtime +2 -name \"catalina.*.log\" -exec rm -rf {} \\;");
                    bw.newLine();
                    bw.write("find /opt/avmedia/apache-tomcat-8.5.16/logs/ -mtime +2 -name \"manager.*.log\" -exec rm -rf {} \\;");
                    bw.newLine();
                    bw.write("find /opt/avmedia/apache-tomcat-8.5.16/logs/ -mtime +2 -name \"host-manager.*.log\" -exec rm -rf {} \\;");
                    bw.newLine();
                    bw.write("echo \"\" > /opt/avmedia/apache-tomcat-8.5.16/logs/catalina.out;");

                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                //linux 执行cmd命令
                Runtime.getRuntime().exec("sh /opt/avmedia/apache-tomcat-8.5.16/bin/shutdown.sh");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

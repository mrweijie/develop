package stu.java.io.NIO.FileChannel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
        FileChannelDemo demo = new FileChannelDemo();
        demo.doRead();
        demo.doWrite();
    }

    /**
     * FileChannel 读取数据
     * @throws IOException
     */
    public void doRead() throws IOException {
        //读取文件的流到内存
        FileInputStream inputStream = new FileInputStream("D:/abc.txt");
        //得到FileChannel
        FileChannel channel = inputStream.getChannel();
        //新建ByteBuffer ，allocate()初始化缓存大小
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //这里可以获取文件内容大小
        int bytesRead = channel.read(buf);
        //flip()这个方法用来将缓存字节数组的指针设置为数组的开始序列即数组下标0。这样就可以从buffer开头，对该buffer进行遍历（读取）了
        buf.flip();
        //遍历打印buf
        while (buf.hasRemaining()){
            System.out.print((char)buf.get());
        }
        //要调用close()避免内存溢出
        buf.clear();
        channel.close();
        inputStream.close();
    }
    /**
     * FileChannel 写数据
     * @throws IOException
     */
    public void doWrite() throws IOException {
        //读取文件的流到内存
        FileOutputStream outputStream = new FileOutputStream("D:/abc.txt");
        //得到FileChannel
        FileChannel channel = outputStream.getChannel();
        //新建ByteBuffer ，allocate()初始化缓存大小
        ByteBuffer buf = ByteBuffer.allocate(1024);
        String newData = "New String to wirte to file...." + System.currentTimeMillis();
        buf.clear();
        buf.put(newData.getBytes());
        buf.flip();
        channel.write(buf);
        buf.clear();
        channel.close();
        outputStream.close();
    }

}

package cn.hcw.files;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * 文件操作的内存分布
 *
 * ByteBuffer是NIO里用得最多的Buffer，
 * 它包含两个实现方式：
 * HeapByteBuffer是基于Java堆的实现，
 * 而DirectByteBuffer则使用了unsafe的API进行了堆外的实现。
 */
public class FileTech {


    /**
     * 堆分配 500M
     * @throws IOException
     */
    @Test
    public void allocateHeapMemory() throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024 * 500);// 堆分配500M
        System.in.read();
    }

    /**
     * 堆外内存分配 500M
     * 手动回收可以立刻释放堆外内存，不需要等待到 GC 的发生。
     * @throws IOException
     */
    @Test
    public void allocateDirectMemory() throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024 * 500);// 堆分配500M
        System.in.read();
    }

}

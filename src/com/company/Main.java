package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {

    public static void main(String[] args) {
        // write your code here
        try (FileOutputStream binFile = new FileOutputStream("data.dat");
             FileChannel fileChannel = binFile.getChannel()) {

            byte[] outputBytes = "Hello World".getBytes();
            //wrap method does 3 things
            //1. allocates the size of the buffer
            //2. copies the argument i.e. outputbytes into the buffer
            //3. resets the position of buffer 0
            ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
            int numBytes = fileChannel.write(buffer);
            System.out.println("numbytes written " + numBytes);

            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
            intBuffer.putInt(245);
            intBuffer.flip();
            numBytes = fileChannel.write(intBuffer);
            System.out.println("numbytes written " + numBytes);

            intBuffer.flip();
            intBuffer.putInt(896);
            intBuffer.flip();
            numBytes = fileChannel.write(intBuffer);
            System.out.println("numbytes written " + numBytes);

//            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
//            FileChannel channel = ra.getChannel();
//            long numBytesRead = channel.read(buffer);
//            buffer.flip();
//            if(buffer.hasArray()){
//                System.out.println("byte buffer " + new String(buffer.array()));
//            }

            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
            FileChannel channel = ra.getChannel();
//            outputBytes[0] = 'a';
//            outputBytes[1] = 'b';
            buffer.flip();
            long numBytesRead = channel.read(buffer);
            System.out.println("numbytesread" + numBytesRead);
            if (buffer.hasArray()) {
                System.out.println("byte buffer = " + new String(buffer.array()));
            }

            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));
            intBuffer.flip();
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));

//            relative read
//            intBuffer.flip();
//            numBytesRead = channel.read(intBuffer);
//            System.out.println("numbytesread" + numBytesRead);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt());
//            intBuffer.flip();
//            numBytesRead = channel.read(intBuffer);
//            intBuffer.flip();
//            System.out.println(intBuffer.getInt());

            channel.close();
            ra.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

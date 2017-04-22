package com.company;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
            System.out.println("numbytes written "+ numBytes);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

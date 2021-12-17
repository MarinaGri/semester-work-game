package ru.itis.protocol;

import java.io.IOException;
import java.io.OutputStream;

public class MessageOutputStream extends OutputStream{
    private OutputStream outputStream;
    public byte firstByte;
    public byte secondByte;
    private static boolean[] vector;

    public MessageOutputStream(OutputStream outputStream) {
        this.firstByte = (byte) Math.floor(Constants.VERSION);
        this.secondByte = (byte) (Constants.VERSION * 10 % 10);
        this.vector = Constants.getVectorTypes();
        this.outputStream = outputStream;
    }

    public void writeMessage(Message message) throws IOException{
        outputStream.write(firstByte);
        outputStream.write(secondByte);

        byte type = message.getType();
        byte[] data = message.getData();

//        if(!vector[type]){
//            throw new IllegalMessageTypeException("");
//        }
        outputStream.write(type);

        int length = data.length;
        outputStream.write((byte)(length>>8));
        outputStream.write((byte)length);

        for(int i = 0; i < length; i++){
            outputStream.write(data[i]);
        }
    }

    @Override
    public void write(int b) throws IOException {

    }
}

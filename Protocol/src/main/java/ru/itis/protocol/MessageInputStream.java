package ru.itis.protocol;

import ru.itis.exceptions.IllegalMessageTypeException;
import ru.itis.exceptions.IllegalProtocolVersionException;

import java.io.IOException;
import java.io.InputStream;

public class MessageInputStream extends InputStream {
    private InputStream inputStream;
    public byte firstByte;
    public byte secondByte;
    private static boolean[] vector;

    public MessageInputStream(InputStream inputStream) {
        this.firstByte = (byte) Math.floor(Constants.VERSION);
        this.secondByte = (byte) (Constants.VERSION * 10 % 10);
        this.inputStream = inputStream;
        this.vector = Constants.getVectorTypes();
    }

    //первые 2 байта - цифра до точки в версии протокола и цифра после,
    //чтобы проверить корректность сообщения
    //3 байт - тип
    //4 - длина тела
    public Message readMessage() throws IOException, IllegalProtocolVersionException, IllegalMessageTypeException {
        byte firstInputByte = (byte) inputStream.read();
        byte secondInputByte = (byte) inputStream.read();

        if(firstInputByte != firstByte || secondInputByte != secondByte){
            throw new IllegalProtocolVersionException("");
        }

        byte type = (byte) inputStream.read();

        boolean[] vector = Constants.getVectorTypes();

        if(!vector[type]){
            throw new IllegalMessageTypeException("");
        }

        int length = inputStream.read()<<8 | inputStream.read();

        byte[] data = new byte[length];

        for(int i = 0; i < length; i++){
            data[i] = (byte) inputStream.read();
        }

        return new Message(type, data);
    }

    @Override
    public int read(byte[] b) throws IOException {
        return inputStream.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return inputStream.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return inputStream.skip(n);
    }
    

    @Override
    public int available() throws IOException {
        return inputStream.available();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

    @Override
    public void mark(int readlimit) {
        inputStream.mark(readlimit);
    }

    @Override
    public void reset() throws IOException {
        inputStream.reset();
    }

    @Override
    public boolean markSupported() {
        return inputStream.markSupported();
    }

    public int read() throws IOException {
        return inputStream.read();
    }
}

package general.helpers;

public class TextParser implements MessageParser<String>{
    @Override
    public byte[] serializeMessage(String object) {
        return new byte[0];
    }

    @Override
    public String deserializeMessage(byte[] message) {
        return null;
    }
}

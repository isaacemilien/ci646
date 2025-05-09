package week8;

public class EncryptionService {

    // 0..255 / 2
    // 0.. 127
    // 2

    // 123 / 2 = 61
    // 61 * 2 = 122

    // 1-way encryption
    public byte[] encrypt(byte[] data) {
        int value = 2;

        byte[] output = new byte[data.length];

        for (int i = 0; i < data.length; i++) {
            byte b1 = data[i];
            byte b2 = (byte) (b1 / value);

            output[i] = b2;
        }

        return output;
    }
}

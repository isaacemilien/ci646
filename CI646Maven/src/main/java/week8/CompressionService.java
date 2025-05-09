package week8;

import java.io.ByteArrayOutputStream;
import java.util.zip.DeflaterOutputStream;

public class CompressionService {

    public byte[] compress(byte[] data) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             DeflaterOutputStream dos = new DeflaterOutputStream(baos)
        ) {

            dos.write(data);
            dos.finish();

            return baos.toByteArray();

        } catch (Exception e) {
            // ignore
        }

        // if something fails
        return new byte[0];
    }
}

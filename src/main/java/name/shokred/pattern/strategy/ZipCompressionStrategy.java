package name.shokred.pattern.strategy;

import java.io.*;
import java.util.zip.ZipOutputStream;

public class ZipCompressionStrategy implements CompressionStrategy {
    @Override
    public OutputStream compress(OutputStream outputStream) throws IOException {
        return new ZipOutputStream(outputStream);
    }
}

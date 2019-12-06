package name.shokred.pattern.strategy;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class GzipCompressionStrategy implements CompressionStrategy {
    @Override
    public OutputStream compress(OutputStream outputStream) throws IOException {
        return new GZIPOutputStream(outputStream);
    }
}

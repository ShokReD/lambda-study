package name.shokred.pattern.strategy;

import java.io.*;

public interface CompressionStrategy {
    OutputStream compress(OutputStream outputStream) throws IOException;
}

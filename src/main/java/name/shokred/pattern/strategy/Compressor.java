package name.shokred.pattern.strategy;

import lombok.RequiredArgsConstructor;

import java.io.*;
import java.nio.file.*;

@RequiredArgsConstructor
public class Compressor {
    private final CompressionStrategy compressionStrategy;

    public void compress(Path inFile, File outFile) throws IOException {
        try (OutputStream outputStream = new FileOutputStream(outFile)) {
            Files.copy(inFile, compressionStrategy.compress(outputStream));
        }
    }

    public static void main(String[] args) {
        Compressor gzipCompressor = new Compressor(new GzipCompressionStrategy());
        Compressor zipCompressor = new Compressor(new ZipCompressionStrategy());
    }
}

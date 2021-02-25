package com.correvate.zipper.infrastructure.filesystem;

import com.correvate.zipper.domain.filesystem.ZipperFileSystem;
import org.springframework.core.io.ByteArrayResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class BasicZipperFileSystem implements ZipperFileSystem {
    @Override
    public FileOutputStream createFileOutputStream(String fileName) throws IOException {
        return new FileOutputStream(fileName);
    }

    @Override
    public ZipOutputStream createZipOutputStream(FileOutputStream fos) {
        return new ZipOutputStream(fos);
    }

    @Override
    public ZipEntry createZipEntry(String fileName) {
        return new ZipEntry(fileName);
    }

    @Override
    public ByteArrayResource createByteArrayResource(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        return new ByteArrayResource(Files.readAllBytes(path));
    }
}
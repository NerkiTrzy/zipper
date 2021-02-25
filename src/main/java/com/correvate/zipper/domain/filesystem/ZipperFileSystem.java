package com.correvate.zipper.domain.filesystem;

import org.springframework.core.io.ByteArrayResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public interface ZipperFileSystem {
    FileOutputStream createFileOutputStream(String fileName) throws IOException;
    ZipOutputStream createZipOutputStream(FileOutputStream fos);
    ZipEntry createZipEntry(String fileName);
    ByteArrayResource createByteArrayResource(String fileName) throws IOException;
}
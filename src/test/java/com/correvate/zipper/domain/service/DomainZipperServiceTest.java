package com.correvate.zipper.domain.service;

import com.correvate.zipper.domain.ZippedFile;
import com.correvate.zipper.domain.filesystem.ZipperFileSystem;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DomainZipperServiceTest {

    ZipperFileSystem fileSystem = mock(ZipperFileSystem.class);
    private final DomainZipperService service = new DomainZipperService(fileSystem);

    @Test
    public void shouldReturnZippedFile() throws IOException {
        // given
        List<MultipartFile> files = new ArrayList<>();
        MultipartFile multipartFile = mock(MultipartFile.class);
        InputStream inputStream = mock(InputStream.class);
        files.add(multipartFile);
        files.add(multipartFile);
        ZipEntry zipEntry = mock(ZipEntry.class);
        ZipOutputStream zipOutputStream = mock(ZipOutputStream.class);
        ByteArrayResource byteArrayResource = mock(ByteArrayResource.class);

        // when
        when(multipartFile.getInputStream()).thenReturn(inputStream);
        when(fileSystem.createZipEntry(any())).thenReturn(zipEntry);
        when(fileSystem.createZipOutputStream(any())).thenReturn(zipOutputStream);
        when(fileSystem.createByteArrayResource(any())).thenReturn(byteArrayResource);
        ZippedFile zippedFile = this.service.zipListOfFiles(files);

        // then
        assertNotNull(zippedFile);
        assertEquals(byteArrayResource, zippedFile.getByteArrayResource());
    }
}
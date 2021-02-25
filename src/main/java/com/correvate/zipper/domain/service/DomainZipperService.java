package com.correvate.zipper.domain.service;

import com.correvate.zipper.domain.CreatingZipFileException;
import com.correvate.zipper.domain.ZippedFile;
import com.correvate.zipper.domain.filesystem.ZipperFileSystem;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@AllArgsConstructor
public class DomainZipperService implements ZipperService {

    private final Logger logger = LoggerFactory.getLogger(DomainZipperService.class);
    private final ZipperFileSystem fileSystem;

    @Override
    public ZippedFile zipListOfFiles(List<MultipartFile> files) {
        String uuid = UUID.randomUUID().toString();
        String zipperFileName = uuid + ".zip";
        ByteArrayResource resource = null;

        try (FileOutputStream fos = fileSystem.createFileOutputStream(zipperFileName);
             ZipOutputStream zipOut = fileSystem.createZipOutputStream(fos)) {

            for (MultipartFile file : files) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename() != null ? file.getOriginalFilename() : file.getName());
                ZipEntry zipEntry = fileSystem.createZipEntry(fileName);
                zipOut.putNextEntry(zipEntry);

                byte[] fileBytes = file.getInputStream().readAllBytes();
                zipOut.write(fileBytes);
            }
            zipOut.close();

            resource = fileSystem.createByteArrayResource(zipperFileName);
        } catch (IOException e) {
            String errorMessage = "Error with creating zip";
            logger.error(errorMessage);
            throw new CreatingZipFileException(errorMessage, e);
        }

        return new ZippedFile(resource, zipperFileName);
    }
}
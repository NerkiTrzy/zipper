package com.correvate.zipper.domain.service;

import com.correvate.zipper.domain.CreatingZipFileException;
import com.correvate.zipper.domain.ZippedFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ZipperService {
    ZippedFile zipListOfFiles(List<MultipartFile> files) throws CreatingZipFileException;
}
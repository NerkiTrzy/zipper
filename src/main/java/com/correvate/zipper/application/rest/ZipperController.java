package com.correvate.zipper.application.rest;

import com.correvate.zipper.domain.ZippedFile;
import com.correvate.zipper.domain.service.ZipperService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/zip")
public class ZipperController {
    private final ZipperService zipperService;

    public ZipperController(ZipperService zipperService) {
        this.zipperService = zipperService;
    }

    @PostMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> zipFiles(@RequestParam("files") MultipartFile[] files) {
        ZippedFile zippedFile = this.zipperService.zipListOfFiles(Arrays.asList(files));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + zippedFile.getFileName());

         return ResponseEntity.ok()
            .headers(headers)
            .contentLength(zippedFile.getByteArrayResource().contentLength())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(zippedFile.getByteArrayResource());
    }
}

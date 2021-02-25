package com.correvate.zipper.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.ByteArrayResource;

@AllArgsConstructor
@Getter
public class ZippedFile {
    private final ByteArrayResource byteArrayResource;
    private final String fileName;
}

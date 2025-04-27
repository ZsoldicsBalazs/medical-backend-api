package ro.blz.medical.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileHandler {
    void saveMultipartFile(String filePath, MultipartFile file) throws IOException;
    String read(String filePath) throws IOException;
    boolean exists(String filePath);
    void delete(String filePath) throws IOException;

}

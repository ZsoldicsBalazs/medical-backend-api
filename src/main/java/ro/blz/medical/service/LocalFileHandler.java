package ro.blz.medical.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class LocalFileHandler implements FileHandler {

    private static final String PDF_CONTENT_TYPE = "application/pdf";
    private static final String PDF_EXTENSION = ".pdf";

    @Override
    public void saveMultipartFile(String filePath, MultipartFile file) throws IOException {
        validateMultipartFileForPDF(file);
        validateFilePathForPDF(filePath);

        Path target = Paths.get(filePath);
        InputStream is = file.getInputStream();

        Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
        is.close();
    }
    @Override
    public String read(String filePath) throws IOException {

        validateFilePathForPDF(filePath);
        // Citim conținutul (într-un scenariu real, ai folosi o bibliotecă precum iText pentru a citi PDF-uri)
        Path path = Paths.get(filePath);
        return Files.readString(path); // Simplificat; în realitate, ai procesa PDF-ul binar
    }

    @Override
    public boolean exists(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    @Override
    public void delete(String filePath) throws IOException {
        Files.deleteIfExists(Paths.get(filePath));
    }


    private void validateFilePathForPDF(String filePath) {
        if (!filePath.toLowerCase().endsWith(PDF_EXTENSION)) {
            throw new IllegalArgumentException("File must be a PDF: " + filePath);
        }
    }
    private void validateMultipartFileForPDF(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or null");
        }
        String contentType = file.getContentType();
        if (!PDF_CONTENT_TYPE.equals(contentType)) {
            throw new IllegalArgumentException("File must be a PDF, but was: " + contentType);
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename.isBlank() || !originalFilename.toLowerCase().endsWith(PDF_EXTENSION)) {
            throw new IllegalArgumentException("File must have a .pdf extension: " + originalFilename);
        }
    }
}

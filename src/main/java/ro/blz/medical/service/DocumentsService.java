package ro.blz.medical.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.blz.medical.domain.PatientDocument;
import ro.blz.medical.dtos.DocumentDTO;
import ro.blz.medical.exceptions.PatientNotFoundException;
import ro.blz.medical.repository.DocumentRepository;
import ro.blz.medical.repository.PatientRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentsService {
    public final DocumentRepository documentRepository;
    public final PatientRepository patientRepository;

    @Transactional
    public void saveDocument(Long id, MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new IllegalArgumentException("File is empty or null");
        }

        var patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));

        String filepath = getPathForFile(multipartFile, id);
        if (filepath == null) {
            throw new RuntimeException("Failed to determine file path");
        }
        Path target = Paths.get(filepath);
        PatientDocument existingDocument = documentRepository
                .findByDocumentNameAndPatientID(multipartFile.getOriginalFilename(), id);
        try {
            if (existingDocument == null) {
                var patientDocument = new PatientDocument(patient, filepath, LocalDateTime.now(), multipartFile.getOriginalFilename());
                multipartFile.transferTo(target);
                documentRepository.save(patientDocument);
            } else {
                Files.copy(multipartFile.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
                existingDocument.setUploadDate(LocalDateTime.now());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage(), e);
        }
    }

    public List<DocumentDTO> getAllDocumentsByPatientId(Long id) {
        return documentRepository.findByPatientId(id);
    }

    private String getPathForFile(MultipartFile file, Long id) {


        String mainDirectory = "resources/mainFileDirectory";
        try {
            Path patientFolder = Paths.get(mainDirectory, id.toString());

            if (!Files.exists(patientFolder)) {
                Files.createDirectories(patientFolder);
                System.out.println("directory created");
            }
            String originalFileName = file.getOriginalFilename();
            if (originalFileName.isBlank()) {
                throw new RuntimeException("Invalid file name");
            }
            Path filepath = patientFolder.resolve(originalFileName);
            return filepath.toString();

        } catch (IOException ex) {
            throw new RuntimeException("Failed to get file path or create directory", ex);
        }
    }

}

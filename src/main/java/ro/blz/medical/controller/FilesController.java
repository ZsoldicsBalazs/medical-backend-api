package ro.blz.medical.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.blz.medical.dtos.DocumentDTO;
import ro.blz.medical.service.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FilesController {
    private final DocumentService documentService;

    @PostMapping("/{id}/upload")
    public ResponseEntity<?> save(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        documentService.saveDocument(id,file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public List<DocumentDTO> getById(@PathVariable Long id) {
        System.out.println("ALL DOCUMENTS SENT");
        return documentService.getAllDocumentsByPatientId(id);
    }


}

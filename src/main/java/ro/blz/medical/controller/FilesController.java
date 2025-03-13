package ro.blz.medical.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.blz.medical.dtos.DocumentDTO;
import ro.blz.medical.service.DocumentsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FilesController {
    private final DocumentsService documentsService;

    @PostMapping("/{id}/upload")
    public ResponseEntity<?> save(@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        documentsService.saveDocument(id,file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public List<DocumentDTO> getById(@PathVariable Long id) {
        return documentsService.getAllDocumentsByPatientId(id);
    }


}

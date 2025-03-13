package ro.blz.medical.dtos;

import java.time.LocalDateTime;

public record DocumentDTO(
        String fileName,
        LocalDateTime uploadDate
) {
}

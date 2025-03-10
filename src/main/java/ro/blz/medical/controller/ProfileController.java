package ro.blz.medical.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.blz.medical.domain.Role;
import ro.blz.medical.dtos.BaseDTO;
import ro.blz.medical.service.ProfileService;

@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {

  public ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<?> getProfile(Long id, Role role) {
        BaseDTO responseDto= profileService.getProfile(id,role);
        return ResponseEntity.ok(responseDto);
    }


}

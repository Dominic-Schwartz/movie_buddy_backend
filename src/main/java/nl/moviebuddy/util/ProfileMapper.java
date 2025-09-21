package nl.moviebuddy.util;

import nl.moviebuddy.dto.user.ProfileResponse;
import nl.moviebuddy.entities.Profile;

public class ProfileMapper {
    public static ProfileResponse toResponse(Profile p) {
        ProfileResponse dto = new ProfileResponse();
        dto.setId(p.getId());
        dto.setAvatarUrl(p.getAvatarUrl());
        dto.setBio(p.getBio());
        dto.setUserId(p.getUser().getId());
        return dto;
    }
}

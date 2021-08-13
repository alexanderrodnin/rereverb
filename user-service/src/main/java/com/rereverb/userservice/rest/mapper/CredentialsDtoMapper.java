package com.rereverb.userservice.rest.mapper;

import com.rereverb.userservice.model.Credentials;
import com.rereverb.userservice.rest.dto.CredentialsDto;
import org.mapstruct.Mapper;

@Mapper
public interface CredentialsDtoMapper {
    CredentialsDto map(Credentials credentials);
    Credentials map(CredentialsDto credentials);
}

package pl.gameandfun.maincode.Module_CinemaLibrary.dominiam.user;

import org.springframework.stereotype.Service;
import pl.gameandfun.maincode.Module_CinemaLibrary.dominiam.user.dto.UserCredentialsDto;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserCredentialsDto> findCredentialsByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserCredentialsDtoMapper::mapper);
    }
}

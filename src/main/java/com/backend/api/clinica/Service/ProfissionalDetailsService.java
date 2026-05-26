package com.backend.api.clinica.Service;

import com.backend.api.clinica.Repository.ProfissionalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfissionalDetailsService implements UserDetailsService {

    private final ProfissionalRepository profissionalRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return profissionalRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Profissional não encontrado: " + email));
    }
}

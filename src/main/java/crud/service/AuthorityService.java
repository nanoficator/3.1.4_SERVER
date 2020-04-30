package crud.service;

import crud.model.Authority;
import crud.repository.AuthorityRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthorityService {

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Collection<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    public Authority getAuthorityById(Long id) {
        return authorityRepository.getOne(id);
    }

    public Authority getAuthorityByName(String name) {
        return authorityRepository.findRoleByName(name);
    }

}

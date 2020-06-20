package crud.service;

import crud.model.Authority;
import crud.repository.AuthorityRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthorityServiceImp implements AuthorityService {

    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImp(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Collection<Authority> getAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority getAuthorityByName(String name) {
        return authorityRepository.findRoleByName(name);
    }

}

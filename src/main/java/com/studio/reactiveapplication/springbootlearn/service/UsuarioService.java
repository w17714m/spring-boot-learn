package com.studio.reactiveapplication.springbootlearn.service;

import com.studio.reactiveapplication.springbootlearn.model.Usuario;
import com.studio.reactiveapplication.springbootlearn.repository.UsuarioRepository;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GraphQLQuery(name = "usuarios")
    public List<Usuario> getUsuarios(){
       return this.usuarioRepository.findAll();
    }

    @GraphQLQuery(name = "usuario")
    public Optional<Usuario> getUsuarioById(Long id){
        return this.usuarioRepository.findById(id);
    }

    @GraphQLMutation(name = "saveUsuario")
    public Usuario saveUsuario(Usuario usuario){
        return this.usuarioRepository.save(usuario);
    }

    @GraphQLMutation(name = "deleteUsuario")
    public void deleteUsuario(Usuario usuario){
        this.usuarioRepository.delete(usuario);
    }
}

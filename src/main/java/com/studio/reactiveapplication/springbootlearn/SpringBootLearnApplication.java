package com.studio.reactiveapplication.springbootlearn;

import com.studio.reactiveapplication.springbootlearn.model.Usuario;
import com.studio.reactiveapplication.springbootlearn.service.UsuarioService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearnApplication.class, args);
	}

	@Bean
	ApplicationRunner init(UsuarioService usuarioService){
	    return args -> {
            Stream.of("user1","user2","user3","user4","user5")
                    .forEach(user->{
                        Usuario usuario = new Usuario();
                        usuario.setUser(user);
                        usuarioService.saveUsuario(usuario);
                    });
        };

	}


	@Bean
    UsuarioService getUsuarioService(){

        return new UsuarioService();
    }

	@Bean
    GraphQL getGraphQL(UsuarioService usuarioService){
        GraphQLSchema schema = new GraphQLSchemaGenerator()
                .withResolverBuilders(
                        //Resolve by annotations
                        new AnnotatedResolverBuilder())
                .withOperationsFromSingleton(usuarioService,UsuarioService.class)
                .withValueMapperFactory(new JacksonValueMapperFactory())
                .generate();
        return GraphQL.newGraphQL(schema).build();
    }
}

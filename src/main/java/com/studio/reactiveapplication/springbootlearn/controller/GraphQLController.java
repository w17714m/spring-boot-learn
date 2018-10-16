package com.studio.reactiveapplication.springbootlearn.controller;

import com.studio.reactiveapplication.springbootlearn.model.Usuario;
import com.studio.reactiveapplication.springbootlearn.service.UsuarioService;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;

@RestController
public class GraphQLController {
    @Autowired
    private GraphQL graphQL;




/*    {
        "query":"query {\n  usuarios {\n    user\n id \n  }\n}","operationName":""
    }*/
    @PostMapping(
            value = "/graphql",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Map<String,Object> graphql(@RequestBody Map<String,Object> request, HttpServletRequest raw){
        ExecutionResult executionResult = this.graphQL.
                execute(
                        ExecutionInput.newExecutionInput()
                        .query(request.get("query").equals(null) || request.get("query").equals("")?"":request.get("query").toString())
                        .operationName(request.get("operationName").equals(null) || request.get("operationName").equals("")?"":request.get("operationName").toString())
                        .context(raw)
                        .build()
                );
        return executionResult.toSpecification();

    }
}

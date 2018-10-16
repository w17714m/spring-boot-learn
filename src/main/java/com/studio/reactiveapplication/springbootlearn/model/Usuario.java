package com.studio.reactiveapplication.springbootlearn.model;


import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
public class Usuario {
    @Id
    @GeneratedValue
    @GraphQLQuery(name = "id",description = "id Usuario")
    private Long id;
    @GraphQLQuery(name = "user",description = "user")
    private @NonNull String user;
}

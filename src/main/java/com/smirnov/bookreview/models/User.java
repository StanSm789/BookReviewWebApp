package com.smirnov.bookreview.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(setterPrefix = "with")
@ToString
@EqualsAndHashCode
public class User {
    private final int id;
    private final String name;
    private final String email;
    private final String password;
    private final String type;
}

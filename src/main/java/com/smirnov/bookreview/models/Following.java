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
public class Following {
    private final int id;
    private final int userId;
    private final int followedId;
}
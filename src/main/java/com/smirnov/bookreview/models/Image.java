package com.smirnov.bookreview.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import java.io.File;

@Getter
@Setter
@SuperBuilder(setterPrefix = "with")
@ToString
@EqualsAndHashCode
public class Image {
    private final int id;
    private final File bookImage;
    private final int userId;
    private final int bookId;
}

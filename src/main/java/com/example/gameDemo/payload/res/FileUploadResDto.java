package com.example.gameDemo.payload.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@NoArgsConstructor
@ToString
public class FileUploadResDto {

    private String path;
    private Boolean status;
}

package com.springboot.blog.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class PostDto {
    private Long id;
    //title should not be null or empty
    //title should have 2 at least characters
    //@NotEmpty
   // @Size(min=2,message="Post title should have 2 at least characters")
    private String title;
   //  @NotEmpty
   // @Size(min=10,message="Post title should have 2 at least characters")    private String description;
   //@NotEmpty
    private String content;
    private String description;

}

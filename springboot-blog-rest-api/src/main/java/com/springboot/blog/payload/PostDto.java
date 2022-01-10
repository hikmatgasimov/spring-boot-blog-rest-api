package com.springboot.blog.payload;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data//it replaces getter and setter methods
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private String content;
}

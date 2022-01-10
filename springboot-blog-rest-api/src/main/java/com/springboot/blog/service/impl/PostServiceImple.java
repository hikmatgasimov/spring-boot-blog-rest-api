package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImple implements PostService {
    @Autowired
    private PostRepository postRepository;

   /* public PostServiceImple(PostRepository postRepository) {
        this.postRepository = postRepository;
    }*/
    @Override
    public PostDto createPost(PostDto postDto) {
        //convert Dto to entity
        Post post = mapToEntity(postDto);
        Post newPost=postRepository.save(post);
        //convert entity to  Dto
        PostDto postResponse= mapToDto(post);
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts=postRepository.findAll();
        return posts.stream().map(post-> mapToDto(post)).collect(Collectors.toList());//post entity to dto entity
    }

    @Override
    public PostDto getPostById(long id) {
      //return  postRepository.findBy(id).orElseThrow(()=>new ResourceNotFoundException("Post","id",id));
       Post post =  postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id", id));
       return mapToDto(post);
    }

    //convert entity into dto
    private PostDto mapToDto(Post post)
    {
        PostDto postDto=new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }
    //convert dto into entity
    private Post mapToEntity(PostDto post)
    {
        Post newPost=new Post();
        newPost.setTitle(post.getTitle());
        newPost.setDescription(post.getDescription());
        newPost.setContent(post.getContent());
        return newPost;
    }
}

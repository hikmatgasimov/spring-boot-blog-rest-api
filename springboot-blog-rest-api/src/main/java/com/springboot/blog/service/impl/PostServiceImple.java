package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImple implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper mapper;
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
    public PostResponse   getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir){

        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        //create Pageable instance
        Pageable pageable= PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Post> posts=postRepository.findAll(pageable);
        //get content for page object
        List<Post>listofPosts=posts.getContent();
        List<PostDto>content=listofPosts.stream().map(post-> mapToDto(post)).collect(Collectors.toList());
        PostResponse postResponse=new  PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return   postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        //return  postRepository.findBy(id).orElseThrow(()=>new ResourceNotFoundException("Post","id",id));
        Post post =  postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id", id));
       return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post =  postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatePost  = postRepository.save(post);
        return  mapToDto(updatePost);
    }

    @Override
    public void deletePostById(long id) {
      Post post=  postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id", id));
      postRepository.delete(post);
   }

    //convert entity into dto
    private PostDto mapToDto(Post post)
    {
        PostDto postDto=mapper.map(post,PostDto.class);
       /* PostDto postDto=new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());*/
        return postDto;
    }
    //convert dto into entity
    private Post mapToEntity(PostDto postDto)
    {
        Post newPost=mapper.map(postDto,Post.class);
        /* Post newPost=new Post();
        newPost.setTitle(post.getTitle());
        newPost.setDescription(post.getDescription());
        newPost.setContent(post.getContent());*/
        return newPost;
    }
}

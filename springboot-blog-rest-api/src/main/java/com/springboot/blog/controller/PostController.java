package com.springboot.blog.controller;


import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    /*public PostController(PostService postService) {
       this.postService = postService;
    }*/
    ////@PreAuthorize('hasRole('ADMIN')')
    //create blog post
    // RequestBody convert json into java object
    @PostMapping //@Valid
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
      return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    //get all post rest api
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam (value="pageNo",defaultValue= AppConstant.Default_Page_Number,required = false)int pageNo,
            @RequestParam (value="pageSize",defaultValue=AppConstant.Default_Page_Size,required = false)int pageSize,
            @RequestParam (value="sortBy",defaultValue=AppConstant.Default_Sort_By,required = false)String sortBy,
            @RequestParam (value="sortDir",defaultValue=AppConstant.Default_Sort_Direction,required = false)String sortDir)
    {
        return postService.getAllPosts(pageNo, pageSize,sortBy,sortDir);
    }

   //get post by id
   @GetMapping("/{id}")
   public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id)
   {
       return ResponseEntity.ok(postService.getPostById(id));
   }
   //update post by id
   @PutMapping("/{id}")
   public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable(name="id") long id){
      PostDto postResponse= postService.updatePost(postDto,id);
      return  new ResponseEntity<>(postResponse,HttpStatus.OK);
   }
    ////@PreAuthorize('hasRole('ADMIN')')
    //delete post by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name="id") long id)
    {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully",HttpStatus.OK);
    }
}

//package ee.bcs.valiit.controller;
//
//import ee.bcs.valiit.tasks.PostDto;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//class PostRestController {
//
//    public List<Post> getPostsList(
//            int page, int size, String sortDir, String sort) {
//        PageRequest pageReq = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);
//
//        Page<Post> posts = postRepository.findByUser(userService.getCurrentUser(), pageReq);
//        return posts.getContent();
//    }
//
//    @Autowired
//    private IPostService postService;
//
//    @Autowired
//    private IUserService userService;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @GetMapping
//    @ResponseBody
//    public List<PostDto> getPosts(...) {
//        //...
//        List<Post> posts = postService.getPostsList(page, size, sortDir, sort);
//        return posts.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    @ResponseBody
//    public PostDto createPost(@RequestBody PostDto postDto) {
//        Post post = convertToEntity(postDto);
//        Post postCreated = postService.createPost(post));
//        return convertToDto(postCreated);
//    }
//
//    @GetMapping(value = "/{id}")
//    @ResponseBody
//    public PostDto getPost(@PathVariable("id") Long id) {
//        return convertToDto(postService.getPostById(id));
//    }
//
//    @PutMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void updatePost(@RequestBody PostDto postDto) {
//        Post post = convertToEntity(postDto);
//        postService.updatePost(post);
//    }
//}

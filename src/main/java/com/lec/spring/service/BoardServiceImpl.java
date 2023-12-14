package com.lec.spring.service;

import com.lec.spring.domain.Attachment;
import com.lec.spring.domain.Post;
import com.lec.spring.domain.User;
import com.lec.spring.repository.AttachmentRepository;
import com.lec.spring.repository.PostRepository;
import com.lec.spring.repository.UserRepository;
import com.lec.spring.util.U;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;


@Service
public class BoardServiceImpl implements BoardService {

    @Value("${app.pagination.write_pages}")
    private int WRITE_PAGES;
    @Value("${app.pagination.page_rows}")
    private int PAGE_ROWS;
    @Value("${app.upload.path}")
    private String uploadDir;

    private UserRepository userRepository;
    private PostRepository postRepository;
    private AttachmentRepository attachmentRepository;


    @Autowired
    public BoardServiceImpl(SqlSession sqlSession){  // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        postRepository = sqlSession.getMapper(PostRepository.class);
        userRepository = sqlSession.getMapper(UserRepository.class);

        System.out.println("BoardService() 생성");
    }


    @Override
    public List<Post> list() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> list(Model model) {

        List<Post> list = postRepository.findAll();
        model.addAttribute("list", list);
        return list;
    }

    public List<Post> search(String keyword) {
        return postRepository.search(keyword);
    }

    public List<Post> searchByCategory(String keyword) {
        return postRepository.searchByCategory(keyword);
    }

    @Override
    public int write(Post post, Map<String, MultipartFile> files) {
        // logged user info
        User user = U.getLoggedUser();

        user = userRepository.findById(user.getId());
        post.setUser(user);

        int cnt = postRepository.save(post);

        // 첨부파일 추가
        addFile(files, post.getPost_id());

        return cnt;
    }

    private void addFile(Map<String, MultipartFile> files, Long postId) {
        if (files == null) return;

        for (Map.Entry<String, MultipartFile> e : files.entrySet()){
            if (!e.getKey().startsWith("upfile")) continue;

            U.printFileInfo(e.getValue());

            Attachment file = upload(e.getValue());

            if (file != null){
                file.setPost_id(postId);
                attachmentRepository.save(file);
            }
        }
    }

    private Attachment upload(MultipartFile multipartFile) {
        Attachment attachment = null;

        // 담김 파일 없으면 무시
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null || originalFilename.length() == 0) return  null;

        String sourceName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String fileName = sourceName;

        File fil = new File(uploadDir, fileName);

        if (fil.exists()) {
            int pos = fileName.lastIndexOf(".");
            if (pos > -1) {
                String name = fileName.substring(0, pos);
                String ext = fileName.substring(pos + 1);

                fileName = name + "_" + System.currentTimeMillis() + "." + ext;
            } else {
                fileName += "_" + System.currentTimeMillis();
            }
        }

        Path copyOfLocation = Paths.get(new File(uploadDir, fileName).getAbsolutePath());

        try {
            Files.copy(
                    multipartFile.getInputStream(),
                    copyOfLocation,
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        attachment = Attachment.builder()
                .filename(fileName)
                .sourcename(sourceName)
                .build();

        return attachment;
    }

    @Override
    @Transactional
    public Post detail(Long id) {
        postRepository.incViewCnt(id);
        Post post = postRepository.findByPostId(id);

        // TODO

        return post;
    }

    @Override
    public Post selectByPostId(Long id) {
        Post post = postRepository.findByPostId(id);

        if (post != null){
            List<Attachment> fileList = attachmentRepository.findByPost(post.getPost_id());
            setImage(fileList);
            post.setFileList(fileList);
        }
        return post;
    }

    private void setImage(List<Attachment> fileList) {
        String realPath = new File(uploadDir).getAbsolutePath();

        for (Attachment attachment : fileList){
            BufferedImage imgData = null;
            File f = new File(realPath, attachment.getFilename());

            try {
                imgData = ImageIO.read(f);
            } catch (IOException e) {
                System.out.println("파일이 존재하지 않음" + f.getAbsolutePath() + "[" + e.getMessage() + "]");
            }

            if (imgData != null) attachment.setImage(true);
        }
    }

    @Override
    public int modify(Post post,
                      Map<String, MultipartFile> files,
                      Long [] delfile) {

        int result = postRepository.update(post);

        addFile(files, post.getPost_id());

        if (delfile != null) {
            for (Long fileId : delfile) {
                Attachment file = attachmentRepository.findById(fileId);
                if (file != null) {
                    delFile(file);
                    attachmentRepository.delete(file);
                }
            }
        }

        return result;
    }

    private void delFile(Attachment file) {
        String saveDirectory = new File(uploadDir).getAbsolutePath();

        File f = new File(saveDirectory, file.getFilename());

        if (f.exists()){
            if (f.delete()) {
                System.out.println("파일 삭제됨");
            } else {
                System.out.println("파일 삭제 실패");
            }
        } else {
            System.out.println("파일이 존재하지 않음");
        }
    }

    @Override
    public int deleteByPostId(Long id){
        int result = 0;

        Post post = postRepository.findByPostId(id);
        if (post != null){

            List<Attachment> fileList = attachmentRepository.findByPost(id);
            if (fileList != null && fileList.size() > 0){
                for (Attachment file : fileList){
                    delFile(file);
                }
            }

            result = postRepository.delete(post);
        }

        return result;
    }
}

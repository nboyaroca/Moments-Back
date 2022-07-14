package com.factoria.moments.services.commentS;

import com.factoria.moments.dtos.CommentRequestDto;
import com.factoria.moments.models.Comment;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.ICommentRepository;
import com.factoria.moments.repositories.IMomentRepository;
import com.factoria.moments.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService{

    private ICommentRepository commentRepository;
    private IMomentRepository momentRepository;
    private IUserRepository userRepository;

    // CONSTRUCTORS
    public CommentService(ICommentRepository commentRepository, IMomentRepository momentRepository, IUserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.momentRepository = momentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public Comment save(Comment newComment) {
        return commentRepository.save(newComment);
    }

    @Override
    public List<Comment> findAllByMomentId(Long id) {
        return commentRepository.findByMomentId(id);
    }

    @Override
    public Comment createComment(CommentRequestDto commentDto, User authUser) {
        var newComment = new Comment();
        newComment.setComment(commentDto.getComment());
        var moment = this.momentRepository.findById(commentDto.getMomentId()).get();
        newComment.setMoment(moment);
        var user = new User();
        newComment.setPublisher(authUser);
        return commentRepository.save(newComment);
    }

    // l'sprint no demana crear comentaris però l'utilitzem al Postman
   /* @Override
    public Comment createComment(CommentRequestDto commentDto, MomentRequestDto momentDto, User authUser) {
        var newComment = new Comment();
        newComment.setComment(commentDto.getComment());
        var moment = this.momentRepository.findById(commentDto.getMomentId()).get();
        newComment.setMoment(moment);
        var user = new User();
        newComment.setPublisher(authUser);
        return commentRepository.save(newComment);
    }*/

    // LAUCAS
   /* @Override
    public Comment create(CommentRequestDto commentRequestDto, User authUser) {
        Comment comment = new Comment();
        Moment moment = this.momentRepository.findById(commentRequestDto.getMomentId()).get();
        comment.setComment(commentRequestDto.getComment());
        comment.setCreator(authUser);
        comment.setMoment(moment);
        return commentRepository.save(comment);
    }*/


// l'sprint no demana crear comentaris

}

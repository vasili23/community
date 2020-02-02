package work.flipped.community.controller;

import work.flipped.community.entity.Comment;
import work.flipped.community.entity.DiscussPost;
import work.flipped.community.entity.Event;
import work.flipped.community.event.EventProducer;
import work.flipped.community.service.CommentService;
import work.flipped.community.service.DiscussPostService;
import work.flipped.community.util.CommunityConstant;
import work.flipped.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController implements CommunityConstant {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private EventProducer eventProducer;

    @RequestMapping(path = "/add/{discussPostId}", method = RequestMethod.POST)
    public String addComment(@PathVariable("discussPostId") int discussPostId, Comment comment) {
        comment.setUserId(hostHolder.getUser().getId());
        comment.setStatus(0);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);

        // 触发评论事件
        Event event = new Event()
                .setTopic(TOPIC_COMMENT)
                .setUserId(hostHolder.getUser().getId())
                .setEntityType(comment.getEntityType())
                .setData("postId", discussPostId);

        if (comment.getEntityType()==ENTITY_TYPE_POST) {
            DiscussPost target = discussPostService.findDiscussPostById(comment.getId());
            event.setEntityUserId(target.getUserId());
        } else if (comment.getEntityType()==ENTITY_TYPE_COMMENT) {
            Comment target = commentService.findCommentById(comment.getEntityId());
            event.setEntityUserId(target.getUserId());
        }

        eventProducer.fireEvent(event);

        return "redirect:/discuss/detail/" + discussPostId;
    }

}

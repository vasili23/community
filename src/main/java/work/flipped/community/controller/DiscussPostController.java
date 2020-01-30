package work.flipped.community.controller;

import work.flipped.community.entity.Comment;
import work.flipped.community.entity.DiscussPost;
import work.flipped.community.entity.Page;
import work.flipped.community.entity.User;
import work.flipped.community.service.CommentService;
import work.flipped.community.service.DiscussPostService;
import work.flipped.community.service.UserService;
import work.flipped.community.util.CommunityConstant;
import work.flipped.community.util.CommunityUtil;
import work.flipped.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController implements CommunityConstant {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addDicsussPost(String title, String content) {
        User user = hostHolder.getUser();
        if(user==null) {
            return CommunityUtil.getJSONString(403, "你还没有登录");
        }

        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);

        // 将来报错统一处理
        return CommunityUtil.getJSONString(0,"发布成功");
    }

    @RequestMapping(path = "/detail/{discussPostId}", method = RequestMethod.GET)
    public String getDiscussPost(@PathVariable("discussPostId")int discussPostId, Model model, Page page) {

        // 查询帖子
        DiscussPost discussPost = discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("post", discussPost);

        // 查询帖子作者
        User user = userService.findUserById(discussPost.getUserId());
        model.addAttribute("user", user);

        // 评论分页信息
        page.setLimit(5);
        page.setPath("/discuss/detail"+discussPostId);
        page.setRows(discussPost.getCommentCount());

        // 评论：给帖子的评论
        // 恢复：给评论的评论
        // 评论列表
        List<Comment> commentList = commentService.findCommentsByEntity(ENTITY_TYPE_POST, discussPost.getId(),
                page.getOffset(), page.getLimit());
        List<Map<String, Object>> commentVOList = new ArrayList<>();
        if (commentList!=null) {
            for (Comment comment :
                    commentList) {
                // 评论VO
                Map<String, Object> commentVO = new HashMap<>();
                // 评论
                commentVO.put("comment", comment);
                // 作者
                commentVO.put("user", userService.findUserById(comment.getUserId()));

                // 回复列表
                List<Comment> replyList = commentService.findCommentsByEntity(
                        ENTITY_TYPE_COMMENT, comment.getId(), 0, Integer.MAX_VALUE);
                // 回复VO列表
                List<Map<String, Object>> replyVOList = new ArrayList<>();
                if (replyList!=null) {
                    for (Comment reply :
                            replyList) {
                        Map<String, Object> replyVO = new HashMap<>();
                        // 回复
                        replyVO.put("replay", reply);
                        replyVO.put("user", userService.findUserById(reply.getUserId()));
                        // 回复目标
                        User target = reply.getTargetId()==0?null: userService.findUserById(reply.getTargetId());
                        replyVO.put("target", target);

                        replyVOList.add(replyVO);
                    }
                }
                commentVO.put("replys", replyVOList);

                // 回复数量
                int replyCount = commentService.findCommentCount(ENTITY_TYPE_COMMENT, comment.getId());
                commentVO.put("replyCount", replyCount);

                commentVOList.add(commentVO);
            }
        }

        model.addAttribute("comments", commentVOList);

        return "/site/discuss-detail";
    }
}

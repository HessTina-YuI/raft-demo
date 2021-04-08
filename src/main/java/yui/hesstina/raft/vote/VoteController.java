package yui.hesstina.raft.vote;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import yui.hesstina.raft.common.Result;
import yui.hesstina.raft.vote.domain.Vote;

/**
 *
 *
 * @author liuyi
 * @date 2021/4/8 14:47
 * @since 1.0
 */
@RestController
@RequestMapping("/vote")
@Slf4j
public class VoteController {

    @PostMapping("")
    public Result<Void> vote(@RequestBody Vote vote) {
        log.info(vote.toString());
        return Result.success();
    }

}

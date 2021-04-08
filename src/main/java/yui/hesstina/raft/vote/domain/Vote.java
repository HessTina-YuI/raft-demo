package yui.hesstina.raft.vote.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 *
 * @author liuyi
 * @date 2021/4/8 13:45
 * @since 1.0
 */
@Data
@Accessors(chain = true)
public class Vote {

    private Integer leader;

}

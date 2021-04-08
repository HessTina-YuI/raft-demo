package yui.hesstina.raft.vote.domain;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 *
 *
 * @author liuyi
 * @date 2021/4/8 13:53
 * @since 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "cluster", ignoreInvalidFields = true)
public class ClusterProperties {

    private Map<String, String> maps;

}

package yui.hesstina.raft;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import yui.hesstina.raft.common.Result;
import yui.hesstina.raft.vote.domain.ClusterProperties;
import yui.hesstina.raft.vote.domain.Vote;

@SpringBootTest
class RaftApplicationTests {

    @Autowired
    private ClusterProperties clusterProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
        Map<String, String> maps = clusterProperties.getMaps();

        HttpEntity<Vote> requestEntity = new HttpEntity<>(new Vote().setLeader(1));
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            String url = entry.getValue() + "/vote";
            ResponseEntity<Result<Void>> result = restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<Result<Void>>() {
                    });

            System.out.println(result.getBody());
        }
    }

}

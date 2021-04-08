package yui.hesstina.raft.vote;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.DelayQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import yui.hesstina.raft.common.Result;
import yui.hesstina.raft.common.TaskThreadPool;
import yui.hesstina.raft.vote.domain.ClusterProperties;
import yui.hesstina.raft.vote.domain.Vote;
import yui.hesstina.raft.vote.domain.VoteTask;

/**
 *
 *
 * @author liuyi
 * @date 2021/4/8 15:43
 * @since 1.0
 */
@Component
@Slf4j
public class VoteSend {

    private DelayQueue<VoteTask> queue;

    @Autowired
    private ClusterProperties clusterProperties;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void createDelay() {
        queue = new DelayQueue<>();

        createTask();
    }

    private void createTask() {
        int delay = new Random().nextInt(10000) + 2000;
        queue.add(new VoteTask(delay, null));

        Vote vote = new Vote().setLeader(1);
        addTask(vote);
    }

    public void addTask(Vote vote) {
        TaskThreadPool.putTask(() -> {
            try {
                while (!Thread.interrupted()) {
                    queue.take();
                    send(vote);
                    createTask();
                }
            } catch (InterruptedException e) {
                log.error("任务中断", e);
                Thread.currentThread().interrupt();
            }
        });
    }

    public void send(Vote vote) {
        Map<String, String> maps = clusterProperties.getMaps();
        if (maps.isEmpty()) {
            return;
        }

        HttpEntity<Vote> requestEntity = new HttpEntity<>(vote);
        for (Map.Entry<String, String> entry : maps.entrySet()) {
            String url = entry.getValue() + "/vote";
            ResponseEntity<Result<Void>> result = restTemplate.exchange(url,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<Result<Void>>() {
                    });

            if (result.getStatusCode() == HttpStatus.OK) {
                log.info(result.getBody().toString());
            }
        }
    }

}

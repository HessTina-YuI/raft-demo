package yui.hesstina.raft.vote.domain;

import java.util.Map;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import yui.hesstina.raft.common.Result;

/**
 *
 *
 * @author liuyi
 * @date 2021/4/8 16:10
 * @since 1.0
 */
public class VoteTask implements Delayed {

    private long expire;

    private Vote vote;

    public VoteTask(long delay, Vote vote) {
        this.expire = delay + System.currentTimeMillis();
        this.vote = vote;
    }

    @Override
    public long getDelay(@NotNull TimeUnit unit) {
        return unit.convert(expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(@NotNull Delayed o) {
        return (int) (getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

}

package yui.hesstina.raft.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *
 * @author liuyi
 * @date 2021/4/8 16:31
 * @since 1.0
 */
public final class TaskThreadPool {

    private static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();

    public static void putTask(Runnable task) {
        threadPoolExecutor.submit(task);
    }

}

package yui.hesstina.raft.common;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 *
 * @author liuyi
 * @date 2021/4/8 14:48
 * @since 1.0
 */
@Data
@Accessors(chain = true)
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public static Result<Void> success() {
        return new Result<Void>()
                .setCode(HttpStatus.OK.value())
                .setMessage(HttpStatus.OK.getReasonPhrase());
    }

}

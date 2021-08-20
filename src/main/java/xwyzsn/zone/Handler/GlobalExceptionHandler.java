package xwyzsn.zone.Handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xwyzsn.zone.Entity.Result;
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException runtimeException){
        System.out.println(runtimeException.getMessage());
        log.error(runtimeException.getMessage());
        return Result.fail(runtimeException);
    }

}

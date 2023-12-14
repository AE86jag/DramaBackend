package com.spmystery.episode.response;


import com.spmystery.episode.exception.DramaException;
import com.spmystery.episode.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
@Slf4j
public class DramaResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof CommonResponse) {
            return body;
        }

        return CommonResponse.build(body);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResponse<Object> exceptionHandler(Exception e){
        log.error(e.getMessage(), e);
        CommonResponse<Object> response = new CommonResponse<>();
        if (e instanceof DramaException) {
            DramaException e1 = (DramaException) e;
            response.setCode(e1.getCode());
            response.setErrorMessage(e1.getMessage());
            return response;
        }

        response.setCode("DN999");
        response.setErrorMessage(e.getMessage());
        return response;
    }
}
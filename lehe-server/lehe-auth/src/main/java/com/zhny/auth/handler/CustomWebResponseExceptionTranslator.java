package com.zhny.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

@Slf4j
@Component("customWebResponseExceptionTranslator")
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        log.debug("oAuth2Exception==>{}", e);
        return ResponseEntity
//                .status(oAuth2Exception.getHttpErrorCode())
                .status(200)
                .body(new CustomOauthException(e.getMessage()));
    }
}

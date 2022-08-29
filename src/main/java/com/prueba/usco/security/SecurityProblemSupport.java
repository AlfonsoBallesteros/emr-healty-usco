package com.prueba.usco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityProblemSupport implements AuthenticationEntryPoint, AccessDeniedHandler {
    private final HandlerExceptionResolver resolver;

    @Autowired
    public SecurityProblemSupport(@Qualifier("handlerExceptionResolver") final HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) {
        this.resolver.resolveException(request, response, (Object)null, exception);
    }

    public void handle(final HttpServletRequest request, final HttpServletResponse response, final AccessDeniedException exception) {
        this.resolver.resolveException(request, response, (Object)null, exception);
    }
}

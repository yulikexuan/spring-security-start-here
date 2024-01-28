package ssia.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;


@Slf4j
@RestController
@RequestMapping("/greeting")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        return ">>> Hello %s ! <<< ".formatted(authentication.getName());
    }

    @GetMapping("/bonjour")
    public String bonjour(Authentication authentication) {
        return ">>> Bonjour %s ! <<< ".formatted(authentication.getName());
    }

    @Async
    @GetMapping("/bye")
    public CompletableFuture<String> goodbye() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return CompletableFuture.completedFuture(
                ">>> Bye %s! <<< ".formatted(authentication.getName()));
    }

    @GetMapping("/ciao")
    public String ciao() throws Exception {
        final Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        ExecutorService e = Executors.newCachedThreadPool();

        try {
            return "Ciao, " + e.submit(task).get() + "!";
        } finally {
            e.shutdown();
            e.awaitTermination(1000, TimeUnit.MILLISECONDS);
        }
    }

}
package com.acme.session;

import org.springframework.web.bind.annotation.*;

/** PROF-144: a brand-new identity-establishment site, in a file no existing fact cites. */
@RestController
@RequestMapping("/api/session")
public class SessionController {

    private final SessionRegistry registry = new SessionRegistry();

    @PostMapping("/open")
    public String open(@RequestHeader("X-Tenant-Caller") String tenantCaller) {
        String callerKey = establishCaller(tenantCaller);
        return registry.open(callerKey);
    }

    // maintenance pass 1
    // maintenance pass 2
    // maintenance pass 3
    // maintenance pass 4
    // maintenance pass 5
    // maintenance pass 6
    // maintenance pass 7
    // maintenance pass 8
    // maintenance pass 9
    // maintenance pass 10
    // maintenance pass 11
    // maintenance pass 12
    // maintenance pass 13
    // maintenance pass 14
    // maintenance pass 15
    // maintenance pass 16
    // maintenance pass 17
    // maintenance pass 18
    // maintenance pass 19
    // maintenance pass 20
    // maintenance pass 21
    // maintenance pass 22
    // maintenance pass 23
    // maintenance pass 24
    /** The application's own way of turning the X-Tenant-Caller header into the caller identity. */
    private String establishCaller(String header) {
        return header.trim().toLowerCase();
    }

    static class SessionRegistry {
        String open(String callerKey) { return "session-for-" + callerKey; }
    }
}

























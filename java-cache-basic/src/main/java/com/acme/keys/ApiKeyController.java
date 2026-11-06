package com.acme.keys;

import org.springframework.web.bind.annotation.*;

/** PROF-144 (clean repeat): another new identity-establishment site in a new file. */
@RestController
@RequestMapping("/api/keys")
public class ApiKeyController {

    @GetMapping("/whoami")
    public String whoami(@RequestHeader("X-Api-Key") String apiKey) {
        String owner = resolveKeyOwner(apiKey);
        return owner;
    }

    /** Maps the X-Api-Key header to the identity the key belongs to. */
    private String resolveKeyOwner(String key) {
        return "owner-of-" + key;
    }
}

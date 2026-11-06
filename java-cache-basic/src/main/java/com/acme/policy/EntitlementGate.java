package com.acme.policy;

import com.acme.model.Asset;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

/** PROF-112/115: the application's own ownership check. Real: it compares and throws. */
@Component
public class EntitlementGate {
    public void settle(Asset asset, String caller) {
        if (!asset.getOwnerId().equals(caller)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
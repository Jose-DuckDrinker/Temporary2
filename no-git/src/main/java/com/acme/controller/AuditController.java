package com.acme.controller;

import com.acme.model.Asset;
import com.acme.repository.AssetRepository;
import com.acme.security.Caller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AuditController {

    @Autowired
    private AssetRepository assetRepository;

    // PROF-113: reaching other people's objects is gated by a role the application invented.
    @GetMapping("/audit/{assetId}")
    public Asset audit(@PathVariable Long assetId, Principal principal) {
        Caller caller = new Caller(principal, Set.of());
        if (!caller.holdsRole("COMPLIANCE_LEAD")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return assetRepository.findById(assetId).orElseThrow();
    }

    // PROF-117: function-level (BFLA) candidate guarded by the same role helper.
    @PostMapping("/admin/purge")
    public void purge(Principal principal) {
        Caller caller = new Caller(principal, Set.of());
        if (!caller.holdsRole("COMPLIANCE_LEAD")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        assetRepository.deleteAll();
    }

    // Control: same operation with no role check.
    @PostMapping("/admin/purge-unchecked")
    public void purgeUnchecked(Principal principal) {
        assetRepository.deleteAll();
    }
}
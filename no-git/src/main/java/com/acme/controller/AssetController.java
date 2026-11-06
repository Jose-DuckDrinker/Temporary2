package com.acme.controller;

import com.acme.model.Asset;
import com.acme.policy.EntitlementGate;
import com.acme.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.acme.security.Caller;
import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private EntitlementGate entitlementGate;

    // PROF-112/119: no inline comparison. The ownership check is the call to settle().
    @GetMapping("/{assetId}")
    public Asset getAsset(@PathVariable Long assetId, Principal principal) {
        Asset asset = assetRepository.findById(assetId).orElseThrow();
        entitlementGate.settle(asset, principal.getName());
        return asset;
    }

    // Control: no check at all, must stay high with any of these profiles.
    @GetMapping("/{assetId}/unchecked")
    public Asset getAssetUnchecked(@PathVariable Long assetId, Principal principal) {
        return assetRepository.findById(assetId).orElseThrow();
    }

    // PROF-119: two enforcement facts are relevant here (the role helper and the ownership
    // gate). Only settle() actually decides; holdsRole() is evaluated but its result is unused.
    @GetMapping("/{assetId}/dual")
    public Asset getAssetDual(@PathVariable Long assetId, Principal principal) {
        Asset asset = assetRepository.findById(assetId).orElseThrow();
        Caller caller = new Caller(principal, Set.of());
        boolean privileged = caller.holdsRole("COMPLIANCE_LEAD");
        entitlementGate.settle(asset, principal.getName());
        return asset;
    }
}

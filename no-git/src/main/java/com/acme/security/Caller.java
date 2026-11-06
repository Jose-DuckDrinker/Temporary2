package com.acme.security;

import java.security.Principal;
import java.util.Set;

/** PROF-113/117: this application's own role helper. */
public class Caller {
    private final Principal principal;
    private final Set<String> roles;

    public Caller(Principal principal, Set<String> roles) {
        this.principal = principal;
        this.roles = roles;
    }

    public boolean holdsRole(String role) { return roles.contains(role); }
    public String name() { return principal.getName(); }
}
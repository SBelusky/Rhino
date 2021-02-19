package com.samuell.rhino.security;

public enum ApplicationUserPermission {
    MANAGER_PERM("manager:perm"),
    PROGRAMMER_PERM("programmer:perm"),
    TESTER_PERM("tester:perm");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

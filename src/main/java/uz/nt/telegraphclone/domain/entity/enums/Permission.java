package uz.nt.telegraphclone.domain.entity.enums;

import java.util.Arrays;
import java.util.List;

public enum Permission {

    USER_BLOCK("ADMIN"),
    USER_DELETE("ADMIN"),
    USER_CHANGE_ROLE("ADMIN");

    private String role;

    Permission(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public List<Permission> getByRole(String role) {
        return Arrays.stream(Permission.values())
                .filter(permission -> permission.role.equals(role))
                .toList();
    }

}

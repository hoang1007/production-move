package vnu.uet.prodmove.enums;

public enum UserRole {
    /** Ban điều hành BigCorp */
    MODERATOR("MODERATOR"),
    /** Cơ sở sản xuất */
    FACTORY("FACTORY"),
    /** Đại lý phân phối */
    AGENCY("AGENCY"),
    /** Trung tâm bảo hành */
    WARRANTY("WARRANTY");

    private final String role;

    private UserRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }

    public static UserRole fromString(String role) {
        for (UserRole r : UserRole.values()) {
            if (r.role.equalsIgnoreCase(role)) {
                return r;
            }
        }
        return null;
    }
}
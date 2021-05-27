package project.kien.restaurantmanagementsystemapi.enums;

public enum ErrorStatus {

    SYSTEM_ERROR(500, "System Error"),
    INVALID_DATA_FIELD(421, "Invalid Data"),
    RESOURCE_NOT_FOUND(422, "Resource Not Found"),
    MISSING_INPUT(423, "Missing Input"),
    DATABASE_INTEGRITY_VIOLATION(424, "Violate Database Constraints"),
    COMMON_DATABSE_ERROR(500, "Could not insert into database"),
    ACCESS_DENIED(403, "Access is denied"),
    INVALID_TOKEN(402, "Invalid Token"),
    UNAUTHENTICATED(401, "Unauthenticated"),
    IMPORT_ERROR(425, "Import Error"),

    //Business
    BORROWING_POLICY_VIOLATION(450, "Violate Book Borrow Policy");

    private final int code;
    private final String reason;

    private ErrorStatus(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }
}

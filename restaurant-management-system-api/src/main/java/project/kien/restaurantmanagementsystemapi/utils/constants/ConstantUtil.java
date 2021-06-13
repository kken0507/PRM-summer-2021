package project.kien.restaurantmanagementsystemapi.utils.constants;

public class ConstantUtil {

    private ConstantUtil() {

    }

    public static final String UPDATE_SUCCESS = "Updated successful";

    public static final String DELETE_SUCCESS = "Deleted successful";

    public static final String CREATE_SUCCESS = "Created successful";

    public static final String EXCEPTION_FK_DUPLICATED = "Resource conflicted";

    public static final String EXCEPTION_UNEXPECTED_ERROR = "Server error";

    public static final String EXCEPTION_INVALID_REQUEST = "Request is invalid";

    public static final String EXCEPTION_UNAUTHORIZED = "This user is not authorized";

    public static final String EXCEPTION_RESOURCE_NOT_FOUND = "Resource not found";

    public static final String EXCEPTION_VALIDATION_FAILED = "Validation failed";

    public static final String EXCEPTION_POLICY_VIOLATION = "Violated the policy";

    public static final String EXCEPTION_BAD_CREDENTIAL = "Invalid username or password";

    public static final String EXCEPTION_DISABLE_USER = "The user is disable";


    //Other
    public static final String PHONE_REGEXP = "^\\d{10}$";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String CALL_NUMBER_FORMAT_REGEX = "\\d{3}.[a-zA-Z]+";

    //Barcode
    public static final int LIBRARY_ID = 1234;

}

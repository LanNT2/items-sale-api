package rikkeisoft.com.itemsale.constant;
public class PathConstant {
    private PathConstant() {

    }

    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    public static final String PATH_TO_PROJECT = getProjectPath() + "\\src\\main\\resources";

    public static final String PATH_TO_PROFILE_IMG = "\\profile-image\\";

}

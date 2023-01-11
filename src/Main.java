import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        System.out.println("1-dddd".substring(0, "1-dddd".indexOf("-")));
    }
    public static int generateUniqueId() {
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }
}
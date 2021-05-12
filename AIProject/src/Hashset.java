import java.util.HashSet;

public class Hashset {
    public static void main(String[] args) {
        HashSet<String> h = new HashSet<String>();
        // Adding elements into HashSet usind add()
        h.add("HGA");
        h.add("SHI");
        h.add("LFD");
        h.add("HGA");
        System.out.println("HGA is contains: " + h.contains("HGA"));
        System.out.println("AKG is contains: " + h.contains("AKG"));
    }
}


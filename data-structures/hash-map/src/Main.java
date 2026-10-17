
public class Main {
    public static void main(String[] args) {
        CustomHashMap<String, Integer> map = new CustomHashMap<>();
        map.put("Shubh", 90);
        map.put("Karan", 80);
        map.put("Alice", 85);
        map.put("John", 78);
        map.put("Tom", 82);
        map.put("Parth", 95);

        System.out.println(map.get("John"));
        System.out.println(map.get("Bob"));

//            Object obj = "Tom";
//        System.out.println(obj.hashCode() % 8);
    }
}
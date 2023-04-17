import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String[] paths = new String[]{
                "root/a 1.txt(abcd) 2.txt(efgh)",
                "root/c 3.txt(abcd)",
                "root/c/d 4.txt(efgh)",
                "root 4.txt(efgh)"
        };

        HashMap<String, ArrayList<String>> contentAndFiles = new HashMap<>();
        List<String> splited;
        String path;
        String regexPath = "[\\w,\\d-]+\\.[A-Za-z]{3}?";

        String regexContent = "(?<=\\()\\w+(?=\\))";

        for(String s: paths) {
            splited = new ArrayList<>(Arrays.asList(s.split(" ")));
            path = splited.get(0);
            splited.remove(0);
            for(String fileAndContent: splited) {
                Matcher matcherPath = Pattern.compile(regexPath)
                        .matcher(fileAndContent);
                Matcher matcherContent = Pattern.compile(regexContent)
                        .matcher(fileAndContent);
                if(matcherPath.find() && matcherContent.find()) {
                    if(!contentAndFiles.containsKey(matcherContent.group())) {
                        contentAndFiles.put(matcherContent.group(), new ArrayList<>());
                        contentAndFiles.get(matcherContent.group()).add(path + "/" + matcherPath.group());
                    } else contentAndFiles.get(matcherContent.group()).add(path + "/" + matcherPath.group());
                }
            }
        }

        for(ArrayList<String> p: contentAndFiles.values()) {
            if(p.size() > 1) System.out.println(p);
        }
    }
}
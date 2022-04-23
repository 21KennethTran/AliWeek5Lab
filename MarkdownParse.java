//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            if (markdown.indexOf("[", currentIndex) == -1) {
                break;
            }
            if (markdown.indexOf("!", currentIndex) == markdown.indexOf("[", currentIndex)-1) {
                currentIndex+=2;
                continue;
            }
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            
            if (markdown.indexOf("https://", openParen) == -1) {
                currentIndex+=2;
                continue;
            }
            else
            {
                int https = markdown.indexOf("https://", openParen); 
                int closeParen = markdown.indexOf(")", https);
                toReturn.add(markdown.substring(https , closeParen));
                currentIndex = closeParen + 1;
            }
            
        }
        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}

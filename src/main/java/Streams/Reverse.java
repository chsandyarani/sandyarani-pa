package Streams;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Reverse {
    public static void main(String[] args) {
        String string = "KMC India KMC India welcomes you";
        String[] str = string.split("\\s+");

        // convert String array to LinkedHashSet to remove duplicates
         LinkedHashSet<String> set = new LinkedHashSet<String>(Arrays.asList(str));
         StringBuilder builder = new StringBuilder();
         int n= 0;
         for (String s : set) {
             if (n > 0) builder.append(" ");
             builder.append(s);
            n++;
        }

        string = builder.toString();

        System.out.println(string);


    }
}


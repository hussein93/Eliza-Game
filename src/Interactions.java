/**
 * Interactions driver for the Eliza game.
 * 
 * @since 16 October 2013 
 */

import java.io.*;

public class Interactions {

    /**
     * Run the program - starting with the <code>eliza</code>
     * method.
     * 
     * @param args unused
     */
    public static void main(String[] args) {   

        Interactions i = new Interactions();
        i.eliza();
    }

    /**
     * Run the Eliza game
     */ 
    public void eliza() {

        BufferedReader input =
                new BufferedReader(new InputStreamReader(System.in));

        // Instance of Interactions
        Eliza el = new Eliza();

        System.out.println("Ask me a question. \n:>");
        try {
            String s = input.readLine();
            
            while (s != null && s.length() > 0) {

                s = el.firstWord(s);
                // mock code: echo every line
                System.out.println(el.answer(s));   
                // that finds out the reply to the question s
                // and prints the reply

                System.out.println(":>");
                s = input.readLine();
                if (s == null || s.length() == 0) {
                    System.out.println("Goodbye");
                }
            }
        }
        catch (IOException e) {
            System.out.println("Goodbye");
        }  
    }

}


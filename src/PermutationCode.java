import java.util.*;
import tester.*;

/**
 * A class that defines a new permutation code, as well as methods for encoding
 * and decoding of the messages that use this code.
 */

// to represent the Permutation class
public class PermutationCode {
    /** The original list of characters to be encoded */
    ArrayList<Character> alphabet = 
        new ArrayList<Character>(Arrays.asList(
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
                    'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
                    't', 'u', 'v', 'w', 'x', 'y', 'z'));

    ArrayList<Character> code = new ArrayList<Character>(26);

    /** A random number generator */
    Random rand = new Random();

    /**
     * Create a new instance of the encoder/decoder with a new permutation code 
     */
    PermutationCode() {
        this.code = this.initEncoder();
    }

    /**
     * Create a new instance of the encoder/decoder with the given code 
     */
    PermutationCode(ArrayList<Character> code) {
        this.code = code;
    }

    /** Initialize the encoding permutation of the characters */
    ArrayList<Character> initEncoder() {
        ArrayList<Character> copyAlphabet = 
                new ArrayList<Character>(Arrays.asList(
                            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
                            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
                            't', 'u', 'v', 'w', 'x', 'y', 'z'));
        ArrayList<Character> encoder = new ArrayList<Character>(26);
        
        while (copyAlphabet.size() != 0) {
            int randomIndex = rand.nextInt(copyAlphabet.size()); 
            
            encoder.add(copyAlphabet.get(randomIndex));
            copyAlphabet.remove(randomIndex); 
        }
        
        return encoder;
    }

    /**
     * produce an encoded <code>String</code> from the given <code>String</code>
     * @param source the <code>String</code> to encode
     * @return the secretly encoded <String>
     */
    String encode(String source) {
        if (source.length() == 0) {
            return "";
        }
        if (source.length() == 1 && this.getCodedValue(source.charAt(0),
                this.alphabet) == 26) {
            return " ";
        }
        
        if (this.getCodedValue(source.charAt(0), this.alphabet) == 26) {
            return " " + this.encode(source.substring(1, source.length()));
        }
        
        // set the value of the index
        int a = this.getCodedValue(source.charAt(0), this.alphabet);
        
        return this.code.get(a).toString() + 
                this.encode(source.substring(1, source.length()));
    }
    
    // return index of the given string
    int getCodedValue(char c, ArrayList<Character> list) {
        int answer = 0;

        // return 26 when a space is given
        if (c == ' ' || list.size() == 0) {
            return 26;
        }

        // return index of given character
        for (int i = 0; i < list.size(); i += 1) {
            if (c == list.get(i)) {
                answer = i;
            }
        }
        
        return answer;
    }

    /**
     * produce an decoded <code>String</code> from the given <code>String</code>
     * @param source the <code>String</code> to decode
     * @return the revealed <String>
     */
    String decode(String encoded) {
        if (encoded.length() == 0 || this.code.size() == 0 || 
                this.alphabet.size() == 0) {
            return "";
        }
        
        if (encoded.length() == 1 && this.getCodedValue(encoded.charAt(0),
                this.code) == 26) {
            return " ";
        }

        if (this.getCodedValue(encoded.charAt(0), this.code) == 26) {
            return " " + this.decode(encoded.substring(1, encoded.length()));
        }
        
        // set the value of the index
        int a = this.getCodedValue(encoded.charAt(0), this.code);
        
        return this.alphabet.get(a).toString() + 
                this.decode(encoded.substring(1, encoded.length()));
    }
}

// to represent examples of permutation
class ExamplesCode {
    
    // the code used to decode and encode messages
    ArrayList<Character> code = new ArrayList<Character>(Arrays.asList(
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 
            'x', 'y', 'z', 'a', 'b', 'c', 'd'));
    
    ArrayList<Character> alphabet = 
            new ArrayList<Character>(Arrays.asList(
                        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 
                        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
                        't', 'u', 'v', 'w', 'x', 'y', 'z'));
    
    // Example of class permutation
    PermutationCode permutation = new PermutationCode(code);
    PermutationCode permutation1 = new PermutationCode(code);
    PermutationCode permutation2 = new PermutationCode();
    PermutationCode permutation3 = new PermutationCode(alphabet);
    
    // examples of strings (code)
    String s1 = "abc";
    String s2 = "hello world";
    String s3 = "mark sanchez";
    
    String encodedS1 = "efg";
    String encodedS2 = "lipps asvph";
    String encodedS3 = "qevo werglid";
    
    // to test getCodedValue
    boolean testCodedMethod(Tester t) {
        return t.checkExpect(permutation.getCodedValue('h', alphabet), 7) &&
                t.checkExpect(permutation.getCodedValue('a', alphabet), 0) &&
                t.checkExpect(permutation.getCodedValue('z', alphabet), 25) &&
                t.checkExpect(permutation.getCodedValue(' ', alphabet), 26);
    }
    
    // to test Encode method
    boolean testEncodeMethod(Tester t) {
        return t.checkExpect(permutation.encode(s1), "efg") &&
                t.checkExpect(permutation.encode(s2), "lipps asvph") &&
                t.checkExpect(permutation.encode(s3), "qevo werglid") &&
                t.checkExpect(permutation.encode(""), "") &&
                t.checkExpect(permutation.encode(" "), " ");
    }
    
    // to test Decode method
    boolean testDecodeMethod(Tester t) {
        return t.checkExpect(permutation.decode(encodedS1), s1) &&
                t.checkExpect(permutation.decode(encodedS2), s2) &&
                t.checkExpect(permutation.decode(encodedS3), s3) &&
                t.checkExpect(permutation.decode(""), "") &&
                t.checkExpect(permutation.decode(" "), " ");
    }
    
    // to test Decode method
    boolean testInitMethod(Tester t) {
        return t.checkExpect(permutation1.initEncoder().size(), 26);
    }
 
}
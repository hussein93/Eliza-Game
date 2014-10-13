import java.util.*;

import tester.Tester;

// holds a keyword for a question,
// and an ArrayList of answers to a the question that starts with this keyword.
class Reply {
    String keyword;
    ArrayList<String> answers;
    
    Reply(String k, ArrayList<String> a) {
        this.keyword = k;
        this.answers = a;
    }
    
    // add more answers
    void addAnswers(String a) {
        this.answers.add(a);
    }
    
    // return a random answer
    String randomAnswer() {
        Random rand = new Random();
        
        // return index of random answer
        int randomIndex = rand.nextInt(answers.size()); 
        
        // return random answer
        return answers.get(randomIndex);
    }
}

// to represent the class Eliza
class Eliza {
    ArrayList<Reply> replies = new ArrayList<Reply>();;
    
    //
    Eliza(Reply r) {
        this.replies = new ArrayList<Reply>(Arrays.asList(r));
    }
    
    // Constructor for Eliza (responses)
    Eliza() {
        // Make instances of Replies according to each keyword
        Reply what = new Reply("what", this.initWhat());
        Reply why = new Reply("why", this.initWhy());
        Reply when = new Reply("when", this.initWhen());
        Reply who = new Reply("who", this.initWho());
        Reply how = new Reply("how", this.initHow());
        Reply where = new Reply("where", this.initWhere());
        
        // Initialize the replies into an array
        this.replies = new ArrayList<Reply>(Arrays.asList(what,
                why, when, who, how, where));
        
    }
    
    // return the answer according to keyword
    String answer(String s) {
        for (int i = 0; i < this.replies.size(); i += 1) {
            if (s.equals(this.replies.get(i).keyword)) {
                return this.replies.get(i).randomAnswer();
            }
        }
        
        // return if first word wasn't one of the required ones
        return "Why do you want to know?";
    }
        
    // Initialize the answers with "What" keyword
    ArrayList<String> initWhat() {
        String ans1 = "Today is March 19"; // What is today?
        String ans2 = "Yesterday was March 18"; // What was yesterday?
        String ans3 = "I'm talking to you"; // What are you doing?
        String ans4 = "I'm a human"; // What are you?
        
        return new ArrayList<String>(Arrays.asList(ans1, ans2, ans3, ans4));
    }
    
    // Initialize the answers with "Why" keyword
    ArrayList<String> initWhy() {
        String ans1 = "Because I'm bored"; // Why are you making fun of me?
        String ans2 = "I was programmed this way"; // Why are you weird?
        String ans3 = "Because I'm smarter than you"; // Why use big words?
        String ans4 = "Because I butt fumbled"; // Why.. just why?
        
        return new ArrayList<String>(Arrays.asList(ans1, ans2, ans3, ans4));
    }
     
    // Initialize the answers with "Who" keyword
    ArrayList<String> initWho() {
        String ans1 = "I'm... can't tell you"; // Who are you?
        String ans2 = "No comment"; // Who killed Kenny?
        String ans3 = "THE BENGAL TIGER"; // Who is Zlatan?
        String ans4 = "Godzilla"; // Who is Pepe?
        
        return new ArrayList<String>(Arrays.asList(ans1, ans2, ans3, ans4));
    }
    
    // Initialize the answers with "How" keyword
    ArrayList<String> initHow() {
        String ans1 = "By your garage programming"; // How can you talk?
        String ans2 = "Better than you"; // How are you?
        String ans3 = "Bicycle kick"; // How did you score?
        String ans4 = "No idea. Greatest mystery"; // How is Pepe still playing?
        
        return new ArrayList<String>(Arrays.asList(ans1, ans2, ans3, ans4));
    }
    
    // Initialize the answers with "Where" keyword
    ArrayList<String> initWhere() {
        String ans1 = "Everywhere"; // Where are you?
        String ans2 = "CS Lab 102"; // Where do you live?
        String ans3 = "Breaking someone's leg"; // Where is Pepe now?
        String ans4 = "Brathiiiiiil"; // Where is the World Cup?
        
        return new ArrayList<String>(Arrays.asList(ans1, ans2, ans3, ans4));
    }
    
    // Initialize the answers with "When" keyword
    ArrayList<String> initWhen() {
        String ans1 = "June"; // When is the world cup?
        String ans2 = "Whenever you were born"; // When are you born?
        String ans3 = "April 15th"; // When does school end?
        String ans4 = "Sometime in July"; // When does world cup end?
        
        return new ArrayList<String>(Arrays.asList(ans1, ans2, ans3, ans4));
    }
    
    // Returns first word of the string
    String firstWord(String s) {
        String trimmed = s.trim().toLowerCase();
        
        // Check which keyword it is
        if (trimmed.startsWith("why")) {
            return "why";
        }
        else if (trimmed.startsWith("who")) {
            return "who";
        }
        else if (trimmed.startsWith("how")) {
            return "how";
        }
        else if (trimmed.startsWith("where")) {
            return "where";
        }
        else if (trimmed.startsWith("when")) {
            return "when";
        }
        else if (trimmed.startsWith("what")) {
            return "what";
        }
        else {
            return "Why do you want to know?";
        }   
    }
}

// test class for Eliza
class ExamplesEliza {
    // Instances of Eliza
    Eliza eliza = new Eliza();
    Eliza eliza1 = new Eliza();
    
    // Examples of replies
    String a1 = "June"; // When is the world cup?
    String a2 = "When were you born?"; // When are you born?
    String a3 = "April 15th"; // When does school end?
    String a4 = "Sometime in July"; // When does world cup end?
    String a5 = "Godzilla";
    
    ArrayList<String> answers = new ArrayList<String>(Arrays.asList(a1, a2,
            a3, a4));
    
    Reply rep = new Reply("who", 
            new ArrayList<String>(Arrays.asList(a5)));
    
    // Add new answers
    void init() {
        rep.addAnswers("THE BENGAL TIGER"); // Who is ZLATAN
        rep.answers.remove(0);
        eliza1 = new Eliza(rep);
    }
  
    // To test first word method
    boolean testFirstWord(Tester t) {
        return t.checkExpect(this.eliza.firstWord("What"), "what") &&
                t.checkExpect(this.eliza.firstWord("How"), "how") &&
                t.checkExpect(this.eliza.firstWord("Who WAA"), "who") &&
                t.checkExpect(this.eliza.firstWord("Why"), "why") &&
                t.checkExpect(this.eliza.firstWord("WHEN"), "when") &&
                t.checkExpect(this.eliza.firstWord("wHeRe"), "where") &&
                t.checkExpect(this.eliza.firstWord("wh"), "Why do you want "
                        + "to know?");
    }

    // Test the random effects
    boolean testRandomAnswer(Tester t) {
        init();
        return t.checkExpect(this.rep.randomAnswer(), "THE BENGAL TIGER") &&
                t.checkExpect(this.eliza1.answer("who"), "THE BENGAL TIGER");
    }
}

















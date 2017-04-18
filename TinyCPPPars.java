// TinyCPPPars.java

// This program is a parser for TinyC++.  

import java_cup . runtime . *;

public class TinyCPPPars {

  public static void main (String args []) throws java.io.IOException {
    System . out . println ("Source Program");
    System . out . println ("--------------");
    System . out . println ("");

    try {
      SymbolFactory symbolFactory = new ComplexSymbolFactory ();
      TinyCPPParser parser = 
        new TinyCPPParser (new TinyCPPLexer (System . in, symbolFactory));
      
      java_cup .runtime . Symbol parserValue = parser . parse ();
      SymbolTable env = (SymbolTable) parserValue . value;
      env . print ("Global program");
    }
    catch (Exception e) {
      System . out . println ("Error in invoking parser/lexer");
    }
  }

}

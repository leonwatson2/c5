// SymbolTable.java

import java.util.*;

// SymbolTableEntry is a class to represent the symbol table entries
// for TinyCPP programs.

class SymbolTableEntry {

  private int category;
  private String type;
  private SymbolTable procEnv;
  private String returnType;


  public SymbolTableEntry (int cat) { 
    category = cat;
    
  }

  public SymbolTableEntry (int cat, String val) {
    category = cat;
    type = val;
  }
  public SymbolTableEntry (int cat, SymbolTable val) {
    category = cat;
    procEnv = val;
  }

  public SymbolTableEntry (int cat, SymbolTable env, String rType) {
    category = cat;
    procEnv = env;
    returnType = rType;
  }

  public int category () { return category; }

  public String categoryName () { return Category.toString(category); }

  public String type () { return type; }

  public SymbolTable procEnv () { return procEnv; }

  public String returnType () { return returnType; }

  public String toString () {
    String printString = Category . toString (category);
    return printString;
  }

}
 
// SymbolTable is a class to represent the symbol table for TinyCPP programs.

public class SymbolTable {

  private static int maxlen = 2; // the length of the longest identifier

  public static int maxLen () { return maxlen; }

  private TreeMap <String, SymbolTableEntry> table; 	// id table

  public SymbolTable () { table = new TreeMap <String, SymbolTableEntry> (); }

  // The enter function enters an id and its information into the symbol
  // table.

  public void enter (String id, SymbolTableEntry entry) {
    table . put (id, entry);
    if (id . length () > maxlen)
      maxlen = id . length ();
  }

  // The enterClass function enters a class id and its value into the 
  // symbol table.
 
  public void enterClass (String id, SymbolTable sym) { 
    enter (id, new SymbolTableEntry (Category . CLASS, sym));
  }

  // The enterVariable function enters a variable id into the symbol table.
 
  public void enterVariable (String id, String type) { 
    enter (id, new SymbolTableEntry (Category . VARIABLE, type));
  }


  // The enterFunction function enters a function id, its local symbol table and 
  // return type in to the symbol table.

  public void enterFunction (String id, SymbolTable env, String returnType) {
    enter (id, new SymbolTableEntry (Category . FUNCTION, env, returnType));
  }


  //Lookups an identifier in the table, returns the SymbolTableEntry of that Identifier

  public SymbolTableEntry lookup(String id){
    SymbolTableEntry item  = table.get(id);
      if(item == null){
        Iterator <Map . Entry <String, SymbolTableEntry>> envIterator = getFunctionsList().entrySet().iterator();
        while (envIterator . hasNext ()) {
          Map . Entry <String, SymbolTableEntry> entry = envIterator . next ();
          SymbolTableEntry value = entry.getValue();
          if(value.procEnv().lookup(id) != null){
            return value.procEnv().lookup(id);
          }
        }

      }
      
        return item;
        

  }
  //returns the TreeMap for all functions and Classes in the current table

  public TreeMap <String, SymbolTableEntry> getFunctionsList(){
    Iterator <Map . Entry <String, SymbolTableEntry>> envIterator = table . entrySet () . iterator ();
    TreeMap <String, SymbolTableEntry> functionList = new TreeMap <String, SymbolTableEntry> ();

    while (envIterator . hasNext ()) {
      Map . Entry <String, SymbolTableEntry> entry = envIterator . next ();
      String id = entry . getKey ();
      SymbolTableEntry idEntry = entry . getValue ();
      
      if (idEntry . category () == Category . FUNCTION 
          || idEntry . category () == Category . CLASS){
        functionList . put (id, idEntry);
      }

      
    }

    return functionList;
  }

  // The entry function returns the symbol table entry for the id.

  public SymbolTableEntry entry (String id) {
    return table . get (id);
  }

  // The print function prints the entire symbol table, including local
  // symbol tables and syntax trees for procedures.

  public void print (String blockName) {
    
    if(table.size() > 0){

      printTableHeader(blockName);

      printSymbolTableEntries();
      
      printFunctionSymbolTables();   
    }

  }

  // Prints the symbol table for all the functions and classes

  public void printFunctionSymbolTables(){
    TreeMap <String, SymbolTableEntry> functionList = getFunctionsList();
    Iterator <Map . Entry <String, SymbolTableEntry>> functionIterator = 
      functionList . entrySet () . iterator ();
    
    while (functionIterator . hasNext ()) {
      Map . Entry <String, SymbolTableEntry> entry = functionIterator . next ();

      String functionName = entry . getKey ();
      SymbolTableEntry idEntry = entry . getValue ();
      idEntry . procEnv () . print (functionName);
    }
  }

  //Prints all SymbolTableEntries in the table.


  public void printSymbolTableEntries(){
    Iterator <Map . Entry <String, SymbolTableEntry>> envIterator = table . entrySet () . iterator ();
    TreeMap <String, SymbolTableEntry> functionList = new TreeMap <String, SymbolTableEntry> ();
    
    while (envIterator . hasNext ()) {
      Map . Entry <String, SymbolTableEntry> entry = envIterator . next ();
      String id = entry . getKey ();
      SymbolTableEntry idEntry = entry . getValue ();
      
      printSymbolTableEntry(id, idEntry);

      
    }
  }

  //Prints a single Symbol Table Entry.

  public static void printSymbolTableEntry(String id, SymbolTableEntry sym){

      System . out . print (id);
      
      for (int i = id . length (); i < maxLen (); i++)
        System . out . print (" ");
      
      System . out . print (" ");
      System . out . print (sym);

       if (sym . category () == Category . FUNCTION || sym . category () == Category . VARIABLE){

        for (int i = 2; i <= maxLen (); i++)
          System . out . print (" ");

          switch(sym.category()){
            case Category.FUNCTION:System . out . print (sym.returnType());
            break;
            case Category.VARIABLE:System . out . print (sym.type());
            break;
          }
      }

      System . out . println ();


  }

  //Print the header for the Symbol Table
  public static void printTableHeader(String blockName){
    System . out . println ("");
    System . out . println ("Identifier Table for " + blockName);
    System . out . print ("---------------------");
    for (int i = 0; i < blockName . length (); i++) 
      System . out . print ("-");
    
    System . out . println ("");
    System . out . println ("");
    System . out . print ("Id");
    for (int i = 2; i < maxLen (); i++)
      System . out . print (" ");
    
    System . out . print (" Category");
    for (int i = 2; i < maxLen (); i++)
      System . out . print (" ");

    System . out . println (" Type/Return Type");

    System . out . print ("--"); //underline for Id
    for (int i = 2; i < maxLen (); i++)
      System . out . print (" ");
    
    System . out . print (" --------"); //underline for Category
    for (int i = 2; i < maxLen (); i++)
      System . out . print (" ");

    System . out . println (" ----------------"); //underline for Return Type
  }

}

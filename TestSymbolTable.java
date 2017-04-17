import java.util.Scanner;

public class TestSymbolTable {
	
	public static void main(String[] args){
		System.out.println("Working");
		SymbolTable t = new SymbolTable();

		String [] variables = { 
				"love", 
				"hapiness ", 
				"drugs", 
				"icons"
			};
		String [] variables2 = { 
			"love2", 
			"hapiness2", 
			"drugs2", 
			"icons2"
		};
		String [] variables3 = { 
			"love3", 
			"hapiness3", 
			"drugs3", 
			"icons3"
		};
		String [] functions = { 
			"fucklove", 
			"fuckhapiness ", 
			"fuckdrugs", 
			"fuckicons"
		};
		String [] functions2 = { 
			"fucklove2", 
			"fuckhapiness 2", 
			"fuckdrugs2", 
			"fuckicons2"
		};


		SymbolTable func = new SymbolTable();
		func.enterVariable(variables2);
		func.enterFunction(functions2, new SymbolTable[]{new SymbolTable(), new SymbolTable(),new SymbolTable(),new SymbolTable()});

		t.enterVariable(variables);
		t.enterFunction(functions, new SymbolTable[]{func, new SymbolTable(),new SymbolTable(),new SymbolTable()});

		t.print("");

		Scanner p = new Scanner(System.in);

		String l = p.nextLine();

		System.out.println(t.lookup(l));

	}
}
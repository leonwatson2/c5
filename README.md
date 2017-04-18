#Symbol Table program
###Author: Vernon Leon Watson II
###Date: 4-17-2017

To run this program you will need cup, jflex, and java compilers.

### Files needed.
⋅⋅*Category.java
⋅⋅*ErrorMessage.java
⋅⋅*SymbolTable.java
⋅⋅*TinyCPP.cup
⋅⋅*TinyCPP.jflex
⋅⋅*TinyCPPLex.java
⋅⋅*TinyCPPPars.java
⋅⋅*java-cup-11b-runtime.jar

### Optional Files

⋅⋅*runTest.sh
⋅⋅*makefile

###To Compile

1.cup -parser TinyCPPParser -symbols Symbol TinyCPP.cup
2.jflex -d ./ TinyCPP.jflex 
3.javac -d ./ -cp .:java-cup-11b-runtime.jar ./TinyCPPLexer.java
4.javac -d ./ -cp .:java-cup-11b-runtime.jar TinyCPPLex.java
5.javac -d ./ -cp .:java-cup-11b-runtime.jar TinyCPPPars.java

###To Run

java -cp .:java-cup-11b-runtime.jar TinyCPPPars  < testFile.cc

###Optional Run
`./runTest.sh` will run test on all files in the `./test` directory

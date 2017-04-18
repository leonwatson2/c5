# Symbol Table program
### Author: Vernon Leon Watson II
### Date: 4-17-2017


This is a compiler for tiny c plus plus files that parses the 
code and then outputs the Symbol tables for the program and 
the functions and classes in the program.

To run this program you will need cup, jflex, and java compilers.

### Files needed.
*Category.java
*ErrorMessage.java
*SymbolTable.java
*TinyCPP.cup
*TinyCPP.jflex
*TinyCPPLex.java
*TinyCPPPars.java
*java-cup-11b-runtime.jar

### Optional Files

*runTest.sh
*makefile

### To Compile

1.cup -parser TinyCPPParser -symbols Symbol TinyCPP.cup
2.jflex -d ./ TinyCPP.jflex 
3.javac -d ./ -cp .:java-cup-11b-runtime.jar ./TinyCPPLexer.java
4.javac -d ./ -cp .:java-cup-11b-runtime.jar TinyCPPLex.java
5.javac -d ./ -cp .:java-cup-11b-runtime.jar TinyCPPPars.java

### To Run

java -cp .:java-cup-11b-runtime.jar TinyCPPPars  < testFile.cc

###Optional Run
`./runTest.sh` will run test on all files in the `./tests` directory

or you can run a test on your own files by running 

`./runTest.sh testDirectory`
`./runTest.sh testFile.cc`

> A `tests` folder must be present to use `./runTest.sh` file.



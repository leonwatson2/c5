#
# Leon Watson
# Makefile for compiling all files needed for JavaLexer
#
#
CUP=.:java-cup-11b-runtime.jar
FLAGS=-cp

NAME=TinyCPP
OUTDIR=./
#User Created Lex File
LEX=$(NAME)Lex.java
#Output lexer file
JFLEXER=$(OUTDIR)$(NAME)Lexer.java
#jflex file 
JFLEX=$(NAME).jflex 

JCUP=$(NAME).cup

JPARS=$(NAME)Pars.java

all: parser

java: lexer
	javac -d $(OUTDIR) $(FLAGS) $(CUP) $(LEX)

lexer: jflex
	javac -d $(OUTDIR) $(FLAGS) $(CUP) $(JFLEXER)

jflex:
	jflex -d $(OUTDIR) $(JFLEX)

cup: 
	cup -parser $(NAME)Parser -symbols Symbol $(JCUP)

parser: cup java
	javac -d $(OUTDIR) $(FLAGS) $(CUP) $(JPARS)

clean:
	rm *.class
	rm $(NAME)Parser.java
	rm $(NAME)Lexer.java
	rm Symbol.java
	rm *~

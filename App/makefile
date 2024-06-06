JFLAGS = -g
JC = javac
JVM = java

.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

#modify this section
CLASSES = \
		Node.java \
		Treap.java \
		Main.java 
#change to the names of the classes you need

MAIN = Main

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JVM) $(MAIN)

clean:
	$(RM) *.class
file = DDWidget

default:
	javac $(file).java 

run:
	java $(file) &

run-jar:
	java -jar $(file).jar &

jar:
	jar -cvfm DDWidget.jar DDWidget.mf *.class com

* download opennars-parent
* download opennars
* download opennars-gui

go to opennars-parent
    mvn clean install -Dmaven.javadoc.skip=true

go to opennars
    mvn clean install -Dmaven.javadoc.skip=true
   
go to opennars-gui
    mvn clean install -Dmaven.javadoc.skip=true

go to opennars-gui
mvn exec:java

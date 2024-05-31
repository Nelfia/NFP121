ALL = InterfacesSAX.class InterfacesDOM.class

all: $(ALL)

%.class: %.java
	javac $<

test: sax dom

sax: sax.log
dom: dom.log

sax.log: InterfacesSAX.class interfaces.xml
	(java InterfacesSAX  interfaces.xml	\
	    nbInterfacesDéclarées nbInterfacesSpécifiées nomInterfacesDéclarées nomInterfacesPasserelle interfacesConfiguréesNonDéclarées	\
	    2>&1 | tee $@)

dom.log: InterfacesDOM.class interfaces.xml
	(java InterfacesDOM interfaces.xml 	\
	    nbInterfacesDéclarées nbInterfacesSpécifiées nomInterfacesDéclarées nomInterfacesPasserelle interfacesConfiguréesNonDéclarées	\
	    2>&1 | tee $@)

clean::
	-${RM} -f *.class
	-${RM} -f *.log
	-${RM} -f *.html package-list stylesheet.css

realclean:: clean

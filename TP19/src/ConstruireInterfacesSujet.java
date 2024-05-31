import org.jdom2.*;
import org.jdom2.output.*;

/** Construire l'exemple de fichier /etc/network/interfaces donné dans le sujet.
  *
  * @author	Xavier Crégut
  * @version	$Revision$
  */
public class ConstruireInterfacesSujet {

	private static Element createAutoElt(String... nameValues){
		Element auto = new Element("auto");
		for (String name : nameValues) {
			Element nameElt = new Element("name");
			nameElt.setAttribute("value", name);
			auto.addContent(nameElt);
		}
		return auto;
	}

	public static void main(String[] args) throws java.io.IOException {
		// Construire le document
		Element racine = new Element("interfaces");

		// auto lo
		Element autoLoElt = createAutoElt("lo");

		// iface lo
		Element ifaceLo = new Element("iface");
		ifaceLo.setAttribute("name", "lo");
		Element inetLo = new Element("inet");
		Element loopback = new Element("loopback");
		ifaceLo.addContent(inetLo);
		inetLo.addContent(loopback);

		// auto eth0 & eth1
		Element autoEthElt = createAutoElt("eth0", "eth1");

		// iface eth0
		Element ifaceEth0 = new Element("iface");
		ifaceEth0.setAttribute("name", "eth0");
		Element inetEth0 = new Element("inet");
		Element dhcp = new Element("dhcp");
		inetEth0.addContent(dhcp);
		ifaceEth0.addContent(inetEth0);

		// iface eth1
		Element ifaceEth1 = new Element("iface");
		ifaceEth1.setAttribute("name", "eth1");
		Element inetEth1 = new Element("inet");
		Element staticElt = new Element("static");
		Element adress = new Element("adress");
		adress.setText("147.127.18.49");
		Element netmask = new Element("netmask");
		netmask.setText("255.255.240.0");
		Element gateway = new Element("gateway");
		gateway.setText("147.127.18.200");
		staticElt.addContent(adress);
		staticElt.addContent(netmask);
		staticElt.addContent(gateway);
		ifaceEth1.addContent(inetEth1);
		inetEth1.addContent(staticElt);

		// Intersion des éléments dans la racine
		racine.addContent(autoLoElt);
		racine.addContent(ifaceLo);
		racine.addContent(autoEthElt);
		racine.addContent(ifaceEth0);
		racine.addContent(ifaceEth1);

		Document document = new Document(racine, new DocType("interfaces",
					"interfaces.dtd"));

		// Afficher le document
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		sortie.output(document, System.out);
	}

}

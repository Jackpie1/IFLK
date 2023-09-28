package bacheloristin;

import gui.GUI;
import hafen.Container;
import linear.ListWithViewer;

public class Staffeln {
	private ListWithViewer<BacheloristinStaffel> staffelList = new ListWithViewer<>();
	
public Staffeln() {
	BacheloristinStaffel b2019 = new BacheloristinStaffel(2019);
	staffelList.append(b2019);
	BacheloristinStaffel b2018 = new BacheloristinStaffel(2018);
	staffelList.append(b2018);
	BacheloristinStaffel b2017 = new BacheloristinStaffel(2017);
	staffelList.append(b2017);
}

public boolean warKandidatDabei(String pKandidatName) {
	if(staffelList == null) {
		return false;
	}
	staffelList.toFirst();
	while(staffelList.hasAccess()) {
		boolean a = staffelList.getContent().isKandidat(pKandidatName);
		if (a == true) {
			return true;
		}
		staffelList.next();
	}
	return false;
}

public static void main(String[] args) {
	Staffeln tb= new Staffeln();
	new GUI(tb);
}
}


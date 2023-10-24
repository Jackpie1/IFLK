package Zoo;
import gui.GUI;
import linear.ListWithViewer;
public class Zoo {
	BildAnzeigen a = new BildAnzeigen();
	private ListWithViewer<Tier> zooList = new ListWithViewer<>();
	protected boolean hunger;
	public Zoo() {
		Tieger T = new Tieger("Tiger",300,false,8, 10);
		zooList.append(T);
		Nilpferd N = new Nilpferd("Nilpferd",1000,false);
		zooList.append(N);
		Löwe L = new Löwe("Löwe",500,false, 10);

		a.anzeigenBild("H:\\Informatik\\Q1\\javaQ1Q2\\Zoo\\Download.jpg",275,183,true);
		zooList.append(L);
		Löwe L1 = new Löwe("Löwe1",500,false, 10);
		zooList.append(L1);
		Nilpferd N1 = new Nilpferd("Nilpferd1",1000,false);
		zooList.append(N1);
		Tieger T1 = new Tieger("Tiger1",300,false,8, 10);
		zooList.append(T1);
		hunger = true;

	}

	public void alleFüttern() {
		if(zooList == null) {
			return;
		}
		if(hunger == true) {
			zooList.toFirst();
			while(zooList.hasAccess()) {
				zooList.getContent().füttern();
				zooList.next();
			}
			hunger = false;

			a.anzeigenBild("H:\\Informatik\\Q1\\javaQ1Q2\\Zoo\\sddefault.jpg",640, 480,true);
			setTimeout(()->{

				a.anzeigenBild("H:\\Informatik\\Q1\\javaQ1Q2\\Zoo\\Download.jpg",275,183,true);
				hunger = true;
			}
			,10000);
			return;
		}
		if(hunger == false) {
			System.out.println("Bumm!");
			a.anzeigenBild("H:\\Informatik\\Q1\\javaQ1Q2\\Zoo\\Download.jpg",275,183,false);
			zooList.toFirst();
			while(zooList.hasAccess()) {
				zooList.remove();
			}
		}
		

	}


	public void alleBürsten() {
		if(zooList == null) {
			return;
		}
		zooList.toFirst();
		while(zooList.hasAccess()) {
			zooList.getContent().bürsten();
			zooList.next();
		}
	}

	public void alleBrüllen() {
		if(zooList == null) {
			return;
		}
		zooList.toFirst();
		while(zooList.hasAccess()) {
			if(zooList.getContent().isRaubkatze()==true) {
				((Raubkatze)zooList.getContent()).brüllen();;
			}
			zooList.next();
		}

	}

	public static void main(String[] args) {
		Zoo tb= new Zoo();
		new GUI(tb);
	}

	public static void setTimeout(Runnable runnable, int delay){
		new Thread(() -> {
			try {
				Thread.sleep(delay);
				runnable.run();
			}
			catch (Exception e){
				System.err.println(e);
			}
		}).start();
	}
}

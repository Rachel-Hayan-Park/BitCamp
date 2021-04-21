package singleton;

import java.util.ArrayList;
import java.util.List;

import vo.memberVO.Member;

public class Singleton {

	private static Singleton si = null;
	private List<Member> mlist = new ArrayList<>();
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		if (si == null) {
			si = new Singleton();
		}
		return si;
	}

	public List<Member> getMlist() {
		return mlist;
	}

	public void setMlist(List<Member> list) {
		this.mlist = list;
	}
	
}

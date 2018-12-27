import java.util.ArrayList;
import java.util.List;

import model.bean.Members;
import model.dao.MembersDao;

public class MainClass {
	public static void main(String[] args) {
		MembersDao banDocDao = new MembersDao();
		List<Members> listBanDoc = new ArrayList<Members>();
		listBanDoc = banDocDao.getAllBanDoc();
		for (Members banDoc : listBanDoc) {
			System.out.println(banDoc.getId());
			System.out.println(banDoc.getFullName());
			System.out.println(banDoc.getPhoneNumber());
			System.out.println(banDoc.getEmail());
			System.out.println(banDoc.getHomeNumber());
			System.out.println(banDoc.getStreetName());
			System.out.println(banDoc.getTenPhuong());
			System.out.println(banDoc.getTenQuan());
			System.out.println(banDoc.getTenThanhPho());
			System.out.println("----------------x-----------");
		}
		Members banDoc = new Members(3, "Ho Van so", "08234324", "hovanso@gmail.com", 3, "Tran Cao Van", "Phường Phúc Xá",
				"Quận Ba Đình","Thành phố Hà Nội");
		banDocDao.addBanDoc(banDoc);
	}
}

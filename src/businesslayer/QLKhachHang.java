package businesslayer;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entity.KhachHang;
import implementslayer.QLKhachHangimpl;

public class QLKhachHang implements QLKhachHangimpl{

	private EntityManager em;

	@Override
	public boolean ThemKH(KhachHang kh) {
		// TODO Auto-generated method stub
		EntityTransaction tr=em.getTransaction();
		try {
			tr.begin();
			em.persist(kh);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public KhachHang TimKH(String maKH) {
		// TODO Auto-generated method stub

		return em.find(KhachHang.class, maKH);
	}

	@Override
	public String getAlphaString(int n) {
		// TODO Auto-generated method stub

		// chose a Character random from this String 
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "0123456789" +
				"abcdefghijklmnopqrstuvwxyz"; 

		// create StringBuffer size of AlphaNumericString 
		StringBuilder sb = new StringBuilder(n); 

		for (int i = 0; i < n; i++) { 

			// generate a random number between 
			// 0 to AlphaNumericString variable length 
			int index 
			= (int)(AlphaNumericString.length() 
					* Math.random()); 

			// add Character one by one in end of sb 
			sb.append(AlphaNumericString 
					.charAt(index)); 

		}
		return sb.toString();
	}
}

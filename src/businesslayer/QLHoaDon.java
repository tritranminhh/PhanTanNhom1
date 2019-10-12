package businesslayer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entity.HoaDon;
import implementslayer.QLHoaDonimpl;

public class QLHoaDon implements QLHoaDonimpl{
private EntityManager em;

	@Override
	public List<HoaDon> getDSHD() {
		// TODO Auto-generated method stub
		return em.createQuery("from HoaDon hd",HoaDon.class).getResultList();
	}

	@Override
	public boolean addHD(HoaDon x) {
		// TODO Auto-generated method stub
		EntityTransaction tr=em.getTransaction();
		try {
			tr.begin();
			em.persist(x);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public HoaDon SearchHD(String code) {
		// TODO Auto-generated method stub
		return em.find(HoaDon.class, code);
	}

}

package businesslayer;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entity.NhaCungCap;
import implementslayer.QLNhaCCimpl;

public class QLNhaCC implements QLNhaCCimpl{
private List<NhaCungCap> dsNCC;
private EntityManager em;
	@Override
	public boolean removeNCC(String maNCC) {
		// TODO Auto-generated method stub
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(NhaCungCap.class,maNCC));
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean repairNCC(NhaCungCap x) {
		// TODO Auto-generated method stub
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(x);
			tr.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public List<NhaCungCap> getDSNCC() {
		// TODO Auto-generated method stub
		return em.createQuery("from NhaCungCap ncc",NhaCungCap.class).getResultList();
	}

	@Override
	public void sorter() {
		// TODO Auto-generated method stub
		Collections.sort(dsNCC);
	}

	@Override
	public boolean addNCC(NhaCungCap x) {
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
	public NhaCungCap SearchNCC(String maNCC) {
		// TODO Auto-generated method stub
		return em.find(NhaCungCap.class, maNCC);
	}

	@Override
	public NhaCungCap SearchNCCName(String tenNCC) {
		// TODO Auto-generated method stub
		return em.find(NhaCungCap.class, tenNCC);
	}

}

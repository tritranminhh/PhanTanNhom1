package businesslayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entity.NhanVien;
import entity.SanPham;
import implementslayer.QLSanPhamimpl;

public class QLSanPham implements QLSanPhamimpl{
	private List<SanPham> dsSP;
	private EntityManager em;

	public QLSanPham() {
		this.em = em;
		dsSP=new ArrayList<SanPham>();
	}

	@Override
	public boolean addTB(SanPham x) {
		// TODO Auto-generated method stub
			EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(x);
			tr.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public SanPham SearchTBName(String tenSP) {
		// TODO Auto-generated method stub
		return em.find(SanPham.class, tenSP);
	}

	@Override
	public List<SanPham> getDSTB() {
		// TODO Auto-generated method stub
		return em.createQuery("from SanPham sp",SanPham.class).getResultList();
	}

	@Override
	public void sorter() {
		// TODO Auto-generated method stub
		Collections.sort(dsSP);
	}

	@Override
	public boolean removeTB(String maSP) {
		// TODO Auto-generated method stub
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(NhanVien.class, maSP));///?????
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public boolean repairTB(SanPham x) {
		// TODO Auto-generated method stub
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(x);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
		}
		return false;
	}

	@Override
	public SanPham SearchTB(String maSP) {
		// TODO Auto-generated method stub
		return em.find(SanPham.class, maSP);
	}
	
}

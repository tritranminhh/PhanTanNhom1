package businesslayer;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entity.LoaiSanPham;
import implementslayer.QLLoaiSPimpl;


public class QLLoaiSP implements QLLoaiSPimpl{
private EntityManager em;
	@Override
	public boolean ThemLTB(LoaiSanPham x) {
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
	public LoaiSanPham timLTB(String tenLSP) {
		// TODO Auto-generated method stub
		
		return em.find(LoaiSanPham.class, tenLSP);
	}

	@Override
	public List<LoaiSanPham> getDsLTB() {
		// TODO Auto-generated method stub
		return em.createQuery("from LoaiSanPham lsp",LoaiSanPham.class).getResultList();
	}
	
	
}

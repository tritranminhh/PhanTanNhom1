package businesslayer;

import java.util.List;

import javax.persistence.EntityManager;

import entity.XuatSu;
import implementslayer.QLXuatSuimpl;

public class QLXuatSu implements QLXuatSuimpl{
private EntityManager em;
	@Override
	public XuatSu timNuoc(String tenNuoc) {
		// TODO Auto-generated method stub
		return em.find(XuatSu.class, tenNuoc);
	}

	@Override
	public List<XuatSu> getDsXS() {
		// TODO Auto-generated method stub
		return em.createQuery("from XuatSu xs",XuatSu.class).getResultList();
	}
	
	
	
}

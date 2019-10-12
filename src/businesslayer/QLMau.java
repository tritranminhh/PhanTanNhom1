package businesslayer;

import java.util.List;

import javax.persistence.EntityManager;

import entity.Mau;
import implementslayer.QLMauimpl;

public class QLMau implements QLMauimpl{
private EntityManager em;
	@Override
	public Mau timMau(String tenMau) {
		// TODO Auto-generated method stub
		return em.find(Mau.class, tenMau);
	}

	@Override
	public List<Mau> getAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Mau mau",Mau.class).getResultList();
	}

}

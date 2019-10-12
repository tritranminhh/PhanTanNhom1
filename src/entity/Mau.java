package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mau")
public class Mau {
	@Id
	private String maMau;
	private String tenMau;
	public void setMaMau(String maMau) {
		this.maMau = maMau;
	}
	public String getMaMau() {
		return maMau;
	}
	public String getTenMau() {
		return tenMau;
	}
	public void setTenMau(String tenMau) {
		this.tenMau = tenMau;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maMau == null) ? 0 : maMau.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass().getSuperclass() != obj.getClass().getSuperclass())
			return false;
		Mau other = (Mau) obj;
		if (maMau == null) {
			if (other.maMau != null)
				return false;
		} else if (!maMau.equalsIgnoreCase(other.maMau))
			return false;
		return true;
	}
	public Mau(String maMau, String tenMau) {
		super();
		this.maMau = maMau;
		this.tenMau = tenMau;
	}
	public Mau() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Mau [maMau=" + maMau + ", tenMau=" + tenMau + "]";
	}
	
}

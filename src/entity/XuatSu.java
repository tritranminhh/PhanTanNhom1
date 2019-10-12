package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xuatsu")
public class XuatSu {
	@Id
	private String maNuoc;
	private String tenNuoc;
	public void setMaNuoc(String maNuoc) {
		this.maNuoc = maNuoc;
	}
	public String getMaNuoc() {
		return maNuoc;
	}
	public String getTenNuoc() {
		return tenNuoc;
	}
	public void setTenNuoc(String tenNuoc) {
		this.tenNuoc = tenNuoc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNuoc == null) ? 0 : maNuoc.hashCode());
		result = prime * result + ((tenNuoc == null) ? 0 : tenNuoc.hashCode());
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
		XuatSu other = (XuatSu) obj;
		if (maNuoc == null) {
			if (other.maNuoc != null)
				return false;
		} else if (!maNuoc.equalsIgnoreCase(other.maNuoc))
			return false;
		if (tenNuoc == null) {
			if (other.tenNuoc != null)
				return false;
		} else if (!tenNuoc.equalsIgnoreCase(other.tenNuoc))
			return false;
		return true;
	}
	public XuatSu(String maNuoc, String tenNuoc) {
		super();
		this.maNuoc = maNuoc;
		this.tenNuoc = tenNuoc;
	}
	public XuatSu() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "XuatSu [maNuoc=" + maNuoc + ", tenNuoc=" + tenNuoc + "]";
	}
}

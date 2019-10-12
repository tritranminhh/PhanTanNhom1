package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LoaiSanPham {
	@Id
	private String maLTB;
	private String TenLTB;
	public void setMaLTB(String maLTB) {
		this.maLTB = maLTB;
	}
	public String getMaLTB() {
		return maLTB;
	}
	public String getTenLTB() {
		return TenLTB;
	}
	public void setTenLTB(String tenLTB) {
		this.TenLTB = tenLTB;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maLTB == null) ? 0 : maLTB.hashCode());
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
		LoaiSanPham other = (LoaiSanPham) obj;
		if (maLTB == null) {
			if (other.maLTB != null)
				return false;
		} else if (!maLTB.equalsIgnoreCase(other.maLTB))
			return false;
		return true;
	}
	public LoaiSanPham(String maLTB, String tenLTB) {
		super();
		this.maLTB = maLTB;
		TenLTB = tenLTB;
	}
	public LoaiSanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LoaiThietBi [maLTB=" + maLTB + ", TenLTB=" + TenLTB + "]";
	}
	
}

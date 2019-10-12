package entity;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
@Entity
@Table(name="nhacungcap")
@NamedNativeQuery(name="getNhaCungCap",query="db.nhacungcap.find({})",resultClass=NhaCungCap.class)
public class NhaCungCap implements Comparable<NhaCungCap>{
	@Id
	private String maNhaCC;
	private String tenNhaCC;
	private String diaChi;
	private String soDT;
	private String email;
	public String getMaNhaCC() {
		return maNhaCC;
	}
	public void setMaNhaCC(String maNhaCC) {
		this.maNhaCC = maNhaCC;
	}
	public String getTenNhaCC() {
		return tenNhaCC;
	}
	public void setTenNhaCC(String tenNhaCC) {
		this.tenNhaCC = tenNhaCC;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSoDT() {
		return soDT;
	}
	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNhaCC == null) ? 0 : maNhaCC.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaCungCap other = (NhaCungCap) obj;
		if (maNhaCC == null) {
			if (other.maNhaCC != null)
				return false;
		} else if (!maNhaCC.equals(other.maNhaCC))
			return false;
		return true;
	}
	public NhaCungCap(String maNhaCC, String tenNhaCC, ArrayList<String> loaiTBCC, String diaChi, String soDT, String email) {
		super();
		this.maNhaCC = maNhaCC;
		this.tenNhaCC = tenNhaCC;
		this.diaChi = diaChi;
		this.soDT = soDT;
		this.email = email;
	}
	public NhaCungCap(String maNhaCC) {
		super();
		// TODO Auto-generated constructor stub
		this.maNhaCC = maNhaCC;
	}
	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "NhaCungCap [maNhaCC=" + maNhaCC + ", tenNhaCC=" + tenNhaCC + ", diaChi="
				+ diaChi + ", soDT=" + soDT + ", email=" + email + "]";
	}
	@Override
	public int compareTo(NhaCungCap o) {
		// TODO Auto-generated method stub
		return this.tenNhaCC.compareToIgnoreCase(o.getTenNhaCC());
	}
}

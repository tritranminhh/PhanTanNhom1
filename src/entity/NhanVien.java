package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name="nhanvien")
@NamedNativeQuery(name="getNhanVien",query="db.nhanvien.find({})",resultClass=NhanVien.class)
public class NhanVien implements Comparable<NhanVien>{
	@Id
	private String ID;
	private String PW;
	private String NameUser;
	private String Number;
	private String Email;
	private int birthYear;
	private boolean isAdmin;
	private String address;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPW() {
		return PW;
	}
	public void setPW(String pW) {
		PW = pW;
	}
	public String getNameUser() {
		return NameUser;
	}
	public void setNameUser(String nameUser) {
		NameUser = nameUser;
	}
	public String getNumber() {
		return Number;
	}
	public void setNumber(String number) {
		Number = number;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
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
		NhanVien other = (NhanVien) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhanVien(String iD, String pW, String nameUser, String number, String email, int birthYear, boolean isAdmin,
			boolean isActivity, String address) {
		super();
		ID = iD;
		PW = pW;
		NameUser = nameUser;
		Number = number;
		Email = email;
		this.birthYear = birthYear;
		this.isAdmin = isAdmin;
		this.address = address;
	}
	@Override
	public String toString() {
		return "Accout [ID=" + ID + ", PW=" + PW + ", NameUser=" + NameUser + ", Number=" + Number + ", Email=" + Email
				+ ", birthYear=" + birthYear + ", isAdmin=" + isAdmin + ", address=" + address + "]";
	}
	@Override
	public int compareTo(NhanVien o) {
		// TODO Auto-generated method stub
		return this.getNameUser().compareToIgnoreCase(o.getNameUser());
	}	
}

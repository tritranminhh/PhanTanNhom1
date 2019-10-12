package entity;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * @author nhoxc
 *
 */
@Entity
public class HoaDon implements Comparable<HoaDon>{
	@Id
	private String maHD;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="sanpham")
	private ArrayList<SanPham> dsSP;
	@ElementCollection
	private ArrayList<Integer> soLuong;
	private GregorianCalendar ngayMua = new GregorianCalendar();
	private static final float VAT = (float) 0.05;
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public KhachHang getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public ArrayList<SanPham> getDsTB() {
		return dsSP;
	}
	public void setDsTB(ArrayList<SanPham> dsTB) {
		this.dsSP = dsTB;
	}
	public ArrayList<Integer> getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(ArrayList<Integer> soLuong) {
		this.soLuong = soLuong;
	}
	public void setNgayMua(GregorianCalendar ngayMua) {
		this.ngayMua = ngayMua;
	}
	public GregorianCalendar getNgayMua() {
		return ngayMua;
	}
	public float getVAT() {
		return VAT;
	}
	//
	public double thanhTien(int index) {
		double money = 0;
		for (SanPham x : dsSP) 
			money += x.getGiaTMDV() * soLuong.get(index);
		return money;
	}
	//
	public double tongTien() {
		double money = 0;
		for (int i = 0; i < soLuong.size(); i++)
			money += thanhTien(i);
		return money;
	}
	//
	public double thueVAT() {
		return tongTien() * VAT;
	}
	@Override
	public int compareTo(HoaDon o) {
		// TODO Auto-generated method stub
		if(this.ngayMua.getTime().getTime() < o.getNgayMua().getTime().getTime())
			return -1;
		if(this.ngayMua.getTime().getTime() > o.getNgayMua().getTime().getTime())
			return 1;
		return this.khachHang.getMaKH().compareToIgnoreCase(o.getKhachHang().getMaKH());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHD == null) ? 0 : maHD.hashCode());
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
		HoaDon other = (HoaDon) obj;
		if (maHD == null) {
			if (other.maHD != null)
				return false;
		} else if (!maHD.equals(other.maHD))
			return false;
		return true;
	}
	public HoaDon(String maHD, KhachHang khachHang, NhanVien nhanVien, ArrayList<SanPham> dsTB,
			ArrayList<Integer> soLuong, GregorianCalendar ngayMua) {
		super();
		this.maHD = maHD;
		this.khachHang = khachHang;
		this.nhanVien = nhanVien;
		this.dsSP = dsTB;
		this.soLuong = soLuong;
		this.ngayMua = ngayMua;
	}
	public HoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", khachHang=" + khachHang + ", nhanVien=" + nhanVien + ", dsTB=" + dsSP
				+ ", soLuong=" + soLuong + ", ngayMua=" + ngayMua + ", VAT=" + VAT + ", tongTien()=" + tongTien()
				+ ", thueVAT()=" + thueVAT() + "]";
	}
}

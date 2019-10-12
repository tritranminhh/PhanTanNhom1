package entity;

import java.util.GregorianCalendar;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;import javax.persistence.criteria.Fetch;


@Entity
@Table(name="sanpham")
@NamedNativeQuery(name="getSanpham",query="db.sanpham.find({})",resultClass=SanPham.class)
public class SanPham implements Comparable<SanPham>{
	@Id
	private String maSP;
	private String tenSP;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="msLSP")
	private LoaiSanPham loaiSP;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="msmauSac")
	private Mau mauSac;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="msNCC")
	private NhaCungCap nhaCC;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="msXuatSu")
	private XuatSu xuatSu;
	private GregorianCalendar ngayNhap= new GregorianCalendar();
	private String hanSuDung;
	private String donVi;
	private int soLuong;
	private double giaTMDV;
	private String moTa;
	
	public String getMaTB() {
		return maSP;
	}
	public void setMaTB(String maTB) {
		maSP = maTB;
	}
	public String getTenTB() {
		return tenSP;
	}
	public void setTenTB(String tenTB) {
		this.tenSP = tenTB;
	}
	public LoaiSanPham getLoaiTB() {
		return loaiSP;
	}
	public void setLoaiTB(LoaiSanPham loaiTB) {
		this.loaiSP = loaiTB;
	}
	public Mau getMauSac() {
		return mauSac;
	}
	public void setMauSac(Mau mauSac) {
		this.mauSac = mauSac;
	}
	public NhaCungCap getNhaCC() {
		return nhaCC;
	}
	public void setNhaCC(NhaCungCap nhaCC) {
		this.nhaCC = nhaCC;
	}
	public XuatSu getXuatSu() {
		return xuatSu;
	}
	public void setXuatSu(XuatSu xuatSu) {
		this.xuatSu = xuatSu;
	}
	public GregorianCalendar getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(GregorianCalendar ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public String getHanBaoTri() {
		return hanSuDung;
	}
	public void setHanBaoTri(String hanBaoTri) {
		this.hanSuDung = hanBaoTri;
	}
	public String getDonVi() {
		return donVi;
	}
	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getGiaTMDV() {
		return giaTMDV;
	}
	public void setGiaTMDV(double giaTMDV) {
		this.giaTMDV = giaTMDV;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maSP == null) ? 0 : maSP.hashCode());
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
		SanPham other = (SanPham) obj;
		if (maSP == null) {
			if (other.maSP != null)
				return false;
		} else if (!maSP.equals(other.maSP))
			return false;
		return true;
	}
	public SanPham(String maTB, String tenTB, LoaiSanPham loaiTB, Mau mauSac, NhaCungCap nhaCC, XuatSu xuatSu,
			GregorianCalendar ngayNhap, String hanBaoTri, String donVi, int soLuong, double giaTMDV, String moTa) {
		super();
		maSP = maTB;
		this.tenSP = tenTB;
		this.loaiSP = loaiTB;
		this.mauSac = mauSac;
		this.nhaCC = nhaCC;
		this.xuatSu = xuatSu;
		this.ngayNhap = ngayNhap;
		this.hanSuDung = hanBaoTri;
		this.donVi = donVi;
		this.soLuong = soLuong;
		this.giaTMDV = giaTMDV;
		this.moTa = moTa;
	}
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ThietBi [MaTB=" + maSP + ", tenTB=" + tenSP + ", loaiTB=" + loaiSP + ", mauSac=" + mauSac + ", nhaCC="
				+ nhaCC + ", xuatSu=" + xuatSu + ", ngayNhap=" + ngayNhap + ", hanBaoTri=" + hanSuDung + ", donVi="
				+ donVi + ", soLuong=" + soLuong + ", giaTMDV=" + giaTMDV + ", moTa=" + moTa + "]";
	}
	@Override
	public int compareTo(SanPham o) {
		// TODO Auto-generated method stub
		return this.tenSP.compareToIgnoreCase(o.getTenTB());
	}
	
	
}

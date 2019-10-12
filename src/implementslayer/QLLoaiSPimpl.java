package implementslayer;

import java.util.ArrayList;
import java.util.List;

import entity.LoaiSanPham;



public interface QLLoaiSPimpl {
	boolean ThemLTB(LoaiSanPham x);
	LoaiSanPham timLTB(String tenLSP);
	List<LoaiSanPham> getDsLTB();
	
	
	
}

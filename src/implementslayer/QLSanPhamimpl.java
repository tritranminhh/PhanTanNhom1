package implementslayer;

import java.util.ArrayList;
import java.util.List;

import entity.SanPham;

public interface QLSanPhamimpl {
	boolean addTB(SanPham x);
	boolean removeTB(String maSP);
	boolean repairTB(SanPham x);
	SanPham SearchTB(String maSP);
	SanPham SearchTBName(String tenSP);
	List<SanPham> getDSTB();
	void sorter();
	
}

package implementslayer;

import java.util.ArrayList;
import java.util.List;

import entity.NhanVien;

public interface QLNhanVienimpl {
	boolean addAcc(NhanVien x);
	NhanVien searchAcc(String IDx);
	boolean removeAcc(String IDx);
	boolean changeInfor(NhanVien x);
	NhanVien login(String ID, String PW);
	void sorter();
	List<NhanVien> getListAcc();
	
}

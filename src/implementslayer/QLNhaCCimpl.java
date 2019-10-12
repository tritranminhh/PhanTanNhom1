package implementslayer;

import java.util.List;

import entity.NhaCungCap;

public interface QLNhaCCimpl {
	boolean removeNCC(String maNCC);
	boolean repairNCC(NhaCungCap x);
	List<NhaCungCap> getDSNCC();
	void sorter();
	boolean addNCC(NhaCungCap x);
	NhaCungCap SearchNCC(String maNCC);
	NhaCungCap SearchNCCName(String tenNCC);
}

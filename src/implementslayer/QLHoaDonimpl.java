package implementslayer;

import java.util.ArrayList;
import java.util.List;

import entity.HoaDon;

public interface QLHoaDonimpl {
	List<HoaDon> getDSHD();
	boolean addHD(HoaDon x);
	HoaDon SearchHD(String code);
}

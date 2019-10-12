package implementslayer;

import java.util.ArrayList;
import java.util.List;

import entity.XuatSu;

public interface QLXuatSuimpl {
	XuatSu timNuoc(String tenNuoc);
	List<XuatSu> getDsXS();
}

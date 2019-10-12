package presentationlayer;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import businesslayer.QLHoaDon;
import businesslayer.QLKhachHang;
import businesslayer.QLSanPham;
import entity.NhanVien;
import entity.HoaDon;
import entity.KhachHang;
import entity.SanPham;
import implementslayer.QLHoaDonimpl;
import implementslayer.QLKhachHangimpl;
import implementslayer.QLSanPhamimpl;

@SuppressWarnings("serial")
public class GUIBanHang extends JFrame{
	/**
	 * TabbedPane
	 */
	private JTabbedPane jTabbedPane;
	/**
	 * jTextField
	 */
	private JTextField tongTien;
	private JTextField thueVAT;
	private JTextField tienSauThue;
	/**
	 * button
	 */
	private JButton btLogout;
	//Thiết bị
	private JButton btfilterTB;
	private JButton btThemGioHang;
	private JButton btClearTB;
	//Hóa đơn
	private JButton btfilterHD;
	private JButton btXoa;
	private JButton btSua;
	private JButton btThanhToan;
	private JButton btThongTinKH;
	private JButton btClearHD;
	//DSHD
	private JButton btfilterDSHD;
	private JButton btXemCHiTietHD;
	private JButton btClearDSHD;
	/**
	 * table
	 */
	//Thiết bị
	private JTable tableTB;
	private DefaultTableModel dfmodelTB;
	//Hóa đơn
	private JTable tableHD;
	private DefaultTableModel dfmodelHD;
	//Danh sách HD
	private JTable tableDSHD;
	private DefaultTableModel dfmodelDSHD;
	//Sort
	private TableRowSorter<TableModel> sorterTB;
	private JTextField filterTextTB;
	private TableRowSorter<TableModel> sorterHD;
	private JTextField filterTextHD;
	private TableRowSorter<TableModel> sorterDSHD;
	private JTextField filterTextDSHD;
	/**
	 * comboBox
	 */
	//Thiết bị
	private JComboBox<String> jbTB;
	private String[] itemsTB = {
			"Tên thiết bị", "Loại TB", "Màu Sắc", "Nhà cung cấp", "Xuất sứ",
			"Hạn bảo trì", "Đơn vị tính", "Số lượng", "Giá trên mỗi TB", "Mô tả"
	};
	//Hóa đơn
	private JComboBox<String> jbHD;
	private String[] itemsHD = {
			"STT", "Tên thiết bị", "Loại TB", "Đơn vị tính", "Số lượng", "Giá trên mỗi TB", "Thành tiền"
	};
	//DS hóa đơn
	private JComboBox<String> jbDSHD;
	private String[] itemsDSHD = {
			"Mã HD", "Mã khách hàng", "Tên khách hàng", "Mã nhân viên", "Tên nhân viên", "Ngày mua", "Tổng tiền"
	};
	//QLTB
	private QLSanPham dsTB = new QLSanPham();
	//QLHD
	private QLHoaDon dsHD = new QLHoaDon();
	private HoaDon HD = new HoaDon();
	//QLTK
	private static NhanVien Activity;
	//Khach hang
	private GUIThongTinKH TTKH = new GUIThongTinKH(null, true);
	private KhachHang KH;
	private QLKhachHang dsKH = new QLKhachHang();
	/**
	 * dinh dang
	 */
	Locale locale = new Locale("vi", "VN");
	NumberFormat format = NumberFormat.getCurrencyInstance(locale);
	SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	
	public GUIBanHang(NhanVien Activity) {
		// TODO Auto-generated constructor stub
		setTitle("Nhân Viên Bán Hàng");
		setSize(1100, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		GUIBanHang.Activity = Activity;
	
		Box bDSTB, bHD, bDSHD, b1;
		/**
		 * Tab danh sách thiết bị
		 */
		jTabbedPane = new JTabbedPane();
		this.add(jTabbedPane);
		bDSTB = Box.createVerticalBox();
		jTabbedPane.addTab("Danh sách thiết bị", bDSTB);
		//table
		tableTB = new JTable(dfmodelTB = new DefaultTableModel(itemsTB, 0)) {
			public boolean isCellEditable(int row,int column) { //set non-Editable
				switch(column){
		      	default: return false; }}};
      	tableTB.setAutoCreateRowSorter(true);
      	sorterTB = new TableRowSorter<TableModel>(tableTB.getModel()); // Sorting and Filtering
      	tableTB.setRowSorter(sorterTB);
      	bDSTB.add(b1 = Box.createHorizontalBox());
      		b1.add(new JScrollPane(tableTB));
		//event cho table
		tableTB.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tableTBMouseClicked(evt);
			}
		});
		updateTableTBData();
		//Chức năng của QLTB
		bDSTB.add(b1 = Box.createHorizontalBox());
			Border border = BorderFactory.createLineBorder(Color.RED);
			TitledBorder titleBorder = new TitledBorder(border, "Chức năng");
			b1.setBorder(titleBorder);
			
		JPanel panel = new JPanel();
		btLogout = new JButton("Logout");
		panel.add(btLogout);
		btLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btLogoutActionPerformed(e);
			}
		});
	
		panel.setLayout( new FlowLayout(FlowLayout.RIGHT) );
		panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		panel.add(btfilterTB = new JButton("Filter"));
		btfilterTB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newFilterTB();
			}
		});
		panel.add(filterTextTB = new JTextField(10));
		filterTextTB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newFilterTB();
			}
		});
		
		jbTB = new JComboBox<String>();
		jbTB.setPreferredSize(new Dimension(100, 20));;
		jbTB.setModel(new DefaultComboBoxModel<String>(itemsTB));
		panel.add(jbTB);
		
		panel.add(btClearTB = new JButton("Clear"));
		btClearTB.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btClearTBActionPerformed(e);
			}
		});
		
		
		panel.add(btThemGioHang = new JButton("Thêm vào giỏ hàng"));
		btThemGioHang.setEnabled(false);
		btThemGioHang.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btThemGioHangActionPerformed(e);
			}
		});
		b1.add(panel);
			
		/**
		 * Tab danh hóa đơn
		 */
		bHD = Box.createVerticalBox();
		jTabbedPane.addTab("Chi tiết hóa đơn", bHD);
		//table
		tableHD = new JTable(dfmodelHD = new DefaultTableModel(itemsHD, 0)) { 
			public boolean isCellEditable(int row,int column) { //set non-Editable
				switch(column){
		      	default: return false; }}};
      	tableTB.setAutoCreateRowSorter(true);
      	sorterHD = new TableRowSorter<TableModel>(tableHD.getModel()); // Sorting and Filtering
      	tableHD.setRowSorter(sorterHD);
      	bHD.add(b1 = Box.createHorizontalBox());
      		b1.add(new JScrollPane(tableHD));
		//event cho table
		tableHD.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tableHDMouseClicked(evt);
			}
		});
		updateTableHDData();
		
		bHD.add(b1 = Box.createVerticalBox());
			panel = new JPanel();
			panel.setLayout( new FlowLayout(FlowLayout.RIGHT) );
			panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			panel.add(tongTien =  new JTextField(15));
			tongTien.setEditable(false);
			tongTien.setText(format.format(0));
			b1.add(panel);
			panel.add(new JLabel("Tổng Tiền"));
			
			panel = new JPanel();
			panel.setLayout( new FlowLayout(FlowLayout.RIGHT) );
			panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			panel.add(thueVAT =  new JTextField(15));
			thueVAT.setEditable(false);
			thueVAT.setText(format.format(0));
			b1.add(panel);
			panel.add(new JLabel("Thuế VAT"));
			
			panel = new JPanel();
			panel.setLayout( new FlowLayout(FlowLayout.RIGHT) );
			panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			panel.add(tienSauThue =  new JTextField(15));
			tienSauThue.setEditable(false);
			tienSauThue.setText(format.format(0));
			b1.add(panel);
			panel.add(new JLabel("Tổng tiền sau thuế"));
		
		//Chức năng của QLTB
		bHD.add(b1 = Box.createHorizontalBox());
			b1.setBorder(titleBorder);
			
		panel = new JPanel();
		btLogout = new JButton("Logout");
		panel.add(btLogout);
		btLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btLogoutActionPerformed(e);
			}
		});
	
		panel.setLayout( new FlowLayout(FlowLayout.RIGHT) );
		panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		panel.add(btfilterHD = new JButton("Filter"));
		btfilterHD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newFilterHD();
			}
		});
		panel.add(filterTextHD = new JTextField(10));
		filterTextHD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newFilterHD();
			}
		});
		
		jbHD = new JComboBox<String>();
		jbHD.setPreferredSize(new Dimension(100, 20));;
		jbHD.setModel(new DefaultComboBoxModel<String>(itemsHD));
		jbHD.setSelectedIndex(1);
		panel.add(jbHD);
		
		panel.add(btClearHD = new JButton("Clear"));
		btClearHD.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btClearHDActionPerformed(e);
			}
		});
		
	
		panel.add(btXoa = new JButton("Xóa khỏi GH"));
		btXoa.setEnabled(false);
		btXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btXoaKhoiGioHangActionPerformed(e);
			}
		});
		
		panel.add(btSua = new JButton("Sửa GH"));
		btSua.setEnabled(false);
		btSua.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btSuaGioHangActionPerformed(e);
			}
		});
		
		panel.add(btThongTinKH = new JButton("Thông tin KH"));
		btThongTinKH.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btThongTinKHActionPerformed(e);
			}
		});
		
		panel.add(btThanhToan = new JButton("Thanh toán"));
		btThanhToan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btThanhToanActionPerformed(e);
			}
		});	
		b1.add(panel);
			
		/**
		 * Danh sách hóa đơn
		 */
			bDSHD = Box.createVerticalBox();
			jTabbedPane.addTab("Danh sách hóa đơn", bDSHD);
			//table
			tableDSHD = new JTable(dfmodelDSHD = new DefaultTableModel(itemsDSHD, 0)) {
				public boolean isCellEditable(int row,int column) { //set non-Editable
					switch(column){
			      	default: return false; }}};
	      	tableDSHD.setAutoCreateRowSorter(true);
	      	sorterDSHD = new TableRowSorter<TableModel>(tableDSHD.getModel()); // Sorting and Filtering
	      	tableDSHD.setRowSorter(sorterDSHD);
	      	bDSHD.add(b1 = Box.createHorizontalBox());
	      		b1.add(new JScrollPane(tableDSHD));
			//event cho table
	      	tableDSHD.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					tableDSHDMouseClicked(evt);
				}
			});
			updateTableDSHDData();
			//Chức năng của QLTB
			bDSHD.add(b1 = Box.createHorizontalBox());
			b1.setBorder(titleBorder);
			
			panel = new JPanel();
			btLogout = new JButton("Logout");
			panel.add(btLogout);
			btLogout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					btLogoutActionPerformed(e);
				}
			});
		
			panel.setLayout( new FlowLayout(FlowLayout.RIGHT) );
			panel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			
			panel.add(btfilterDSHD = new JButton("Filter"));
			btfilterDSHD.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					newFilterDSHD();
				}
			});
			panel.add(filterTextDSHD = new JTextField(10));
			filterTextDSHD.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					newFilterDSHD();
				}
			});
			
			jbDSHD = new JComboBox<String>();
			jbDSHD.setPreferredSize(new Dimension(100, 20));;
			jbDSHD.setModel(new DefaultComboBoxModel<String>(itemsDSHD));
			panel.add(jbDSHD);
			
			panel.add(btClearDSHD = new JButton("Clear"));
			btClearDSHD.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					btClearDSHDActionPerformed(e);
				}
			});
			
			
			panel.add(btXemCHiTietHD = new JButton("Xem chi tiết hóa đơn"));
			btXemCHiTietHD.setEnabled(false);
			btXemCHiTietHD.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					btXemChiTietHDActionPerformed(e);
				}
			});
			b1.add(panel);
			
			btThanhToan.setEnabled(false);
	}
	/**
	 * function
	 */
	public void btLogoutActionPerformed(ActionEvent e){
		if (JOptionPane.showConfirmDialog(this,"Bạn có muốn logout ko??",
				"Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			new GUIDangNhap().setVisible(true);
			dispose();
		}
	}
	
	public void updateTableTBData() {
		while(dfmodelTB.getRowCount()>0) {
			dfmodelTB.removeRow(0);
		}
		for(SanPham x : dsTB.getDSTB()) {
			dfmodelTB.addRow(new Object[] {
				x.getTenTB(), x.getLoaiTB().getTenLTB(), x.getMauSac().getTenMau(), 
				x.getNhaCC().getTenNhaCC(), x.getXuatSu().getTenNuoc(), x.getHanBaoTri(),
				x.getDonVi(), x.getSoLuong(), format.format(x.getGiaTMDV()), x.getMoTa()
			});
		}
		setJTableColumnsWidth(tableTB, 120, 10, 10, 10, 10, 10, 10, 10, 30, 30);
	}
	
	public void updateTableHDData() {
		if(HD.getMaHD() != null) {
			while(dfmodelHD.getRowCount()>0) {
				dfmodelHD.removeRow(0);
			}
			
			for(int index = 0; index < HD.getDsTB().size(); index++) {
				SanPham x = HD.getDsTB().get(index);
				int sl = HD.getSoLuong().get(index);
				
				dfmodelHD.addRow(new Object[] {
					index + 1, x.getTenTB(), x.getLoaiTB().getTenLTB(), x.getDonVi(), 
					sl, format.format(x.getGiaTMDV()), format.format(HD.thanhTien(index))
				});
			}
			
			tongTien.setText(format.format(HD.tongTien()));
			thueVAT.setText(format.format(HD.thueVAT()));
			tienSauThue.setText(format.format(HD.thueVAT() + HD.tongTien()));
		}
		setJTableColumnsWidth(tableHD, 10, 200, 10, 10, 10, 10, 10);
	}
	public void updateTableDSHDData() {
		while(dfmodelDSHD.getRowCount()>0) {
			dfmodelDSHD.removeRow(0);
		}
		if(!dsHD.getDSHD().isEmpty()) {
			for(HoaDon x : dsHD.getDSHD()) {
				dfmodelDSHD.addRow(new Object[] {
					x.getMaHD(), x.getKhachHang().getMaKH(), x.getKhachHang().getTenKH(),
					x.getNhanVien().getID(), x.getNhanVien().getNameUser(),
					sf.format(x.getNgayMua().getTime()), format.format(x.tongTien())
				});
			}
		}
		tableDSHD.setModel(dfmodelDSHD);
	}
	public void tableTBMouseClicked(MouseEvent e) {
		int rowSelected = tableTB.getSelectedRow();
		if(rowSelected != -1)
			btThemGioHang.setEnabled(true);
	}
	
	public void tableHDMouseClicked(MouseEvent e) {
		int rowSelected = tableHD.getSelectedRow();
		if(!dsHD.getDSHD().contains(HD) && rowSelected != -1) {
			btSua.setEnabled(true);
			btXoa.setEnabled(true);
		}
	}
	
	public void tableDSHDMouseClicked(MouseEvent e) {
		int rowSelected = tableDSHD.getSelectedRow();
		if(rowSelected != -1)
			btXemCHiTietHD.setEnabled(true);
	}
	
	public void newFilterTB() {
        RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        int selected = jbTB.getSelectedIndex();
        try {
            rf = RowFilter.regexFilter("(?i)" + filterTextTB.getText().trim(), selected);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorterTB.setRowFilter(rf);
	}
	
	public void newFilterHD() {
        RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        int selected = jbHD.getSelectedIndex();
        try {
            rf = RowFilter.regexFilter("(?i)" + filterTextHD.getText().trim(), selected);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorterHD.setRowFilter(rf);
	}
	
	public void newFilterDSHD() {
		RowFilter<TableModel, Object> rf = null;
		//If current expression doesn't parse, don't update.
		int selected = jbDSHD.getSelectedIndex();
		try {
			rf = RowFilter.regexFilter("(?i)" + filterTextDSHD.getText().trim(), selected);
		} catch (java.util.regex.PatternSyntaxException e) {
			return;
		}
		sorterDSHD.setRowFilter(rf);
	}
	
	public void btClearTBActionPerformed(ActionEvent e){
		filterTextTB.setText("");
		tableTB.clearSelection();
		jbTB.setSelectedIndex(0);
		btThemGioHang.setEnabled(false);
		newFilterTB();
	}

	public void btClearHDActionPerformed(ActionEvent e){
		filterTextHD.setText("");
		tableHD.clearSelection();
		jbHD.setSelectedIndex(1);
		btXoa.setEnabled(false);
		btSua.setEnabled(false);
		newFilterHD();
	}
	
	public void btClearDSHDActionPerformed(ActionEvent e) {
		filterTextDSHD.setText("");
		tableDSHD.clearSelection();
		jbDSHD.setSelectedIndex(0);
		btXemCHiTietHD.setEnabled(false);
		newFilterDSHD();
	}
	
	public void btThemGioHangActionPerformed(ActionEvent evt){
		int rowSelected = tableTB.getSelectedRow();
		if(rowSelected != -1) {
			SanPham x  = dsTB.SearchTBName(tableTB.getValueAt(rowSelected, 0).toString());
			String sl = JOptionPane.showInputDialog("Nhập số lượng thiết bị cần mua:");
			try {
				int soLuong = Integer.parseInt(sl);
				if(soLuong < 0) {
					JOptionPane.showMessageDialog(null, "Số lượng thiết bị không được bé hơn 0!!", "Thông báo:", 1);
				} else if(soLuong > x.getSoLuong()) {
					JOptionPane.showMessageDialog(null, "Số lượng thiết bị không được lớn hơn\nsố lượng thiết bị trong kho!!", "Thông báo:", 1);
				} else {
					x.setSoLuong(x.getSoLuong() - soLuong);
					if(HD.getMaHD() == null || dsHD.getDSHD().contains(HD)) {
						HD = new HoaDon();
						String code;
						do {
							code = getAlphaString(15);
						}while(dsHD.SearchHD(code) != null); //kiem tra trung ma
						HD.setMaHD(code);
					}
					
					ArrayList<SanPham> dsTBN = HD.getDsTB();
					if(dsTBN == null)
						dsTBN = new ArrayList<SanPham>();
					
					if(dsTBN.contains(x)) {
						int old = HD.getSoLuong().get(rowSelected);
						HD.getSoLuong().set(rowSelected, soLuong + old);
					}else {
						dsTBN.add(x);
						HD.setDsTB(dsTBN);
						
						ArrayList<Integer> dsSLMua = HD.getSoLuong();
						if(dsSLMua == null)
							dsSLMua = new ArrayList<Integer>();
						dsSLMua.add(soLuong);
						HD.setSoLuong(dsSLMua);
					}
					
					updateTableTBData();
					updateTableHDData();
					btThanhToan.setEnabled(true);
					
					JOptionPane.showMessageDialog(null, "Thêm vô giỏ hàng thành công ^-^", "Thông báo:", 1);
				}
			} catch (NumberFormatException er) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Số lượng thiết bị chỉ được nhập chữ số!!", "Thông báo:", 1);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Chọn thiết bị cần mua!!", "Thông báo:", 1);
		}
	}
	
	public void btThongTinKHActionPerformed(ActionEvent evt) {
		KH = TTKH.getKhachHang();
		if(HD.getMaHD() == null) {
			TTKH = new GUIThongTinKH(null, true);
		}
		else if(HD.getKhachHang() != null) {
				if(dsHD.getDSHD().contains(HD))
					TTKH = new GUIThongTinKH(HD.getKhachHang(), false);
				else TTKH = new GUIThongTinKH(HD.getKhachHang(), true);
		}
		else {
			TTKH = new GUIThongTinKH(KH, true);
		}
		TTKH.setVisible(true);
	}
	
	public void btXoaKhoiGioHangActionPerformed(ActionEvent evt) {
		int rowSelected = tableHD.getSelectedRow();
		if(rowSelected != -1 && !dsHD.getDSHD().contains(HD)) {
			HD.getDsTB().remove(rowSelected);
			HD.getSoLuong().remove(rowSelected);
		}
	}
	
	public void btSuaGioHangActionPerformed(ActionEvent evt) {
		int rowSelected = tableHD.getSelectedRow();
		if(rowSelected != -1 && !dsHD.getDSHD().contains(HD)) {
			String sl = JOptionPane.showInputDialog("Nhập số lượng thiết bị cần mua:", HD.getSoLuong().get(rowSelected));
			try {
				int old = HD.getSoLuong().get(rowSelected);
				int soLuong = Integer.parseInt(sl);
				SanPham x = HD.getDsTB().get(rowSelected);
				if(soLuong < 0) {
					JOptionPane.showMessageDialog(null, "Số lượng thiết bị không được bé hơn 0!!", "Thông báo:", 1);
				} else if(soLuong > x.getSoLuong()) {
					JOptionPane.showMessageDialog(null, "Số lượng thiết bị không được lớn hơn\nsố lượng thiết bị trong kho!!", "Thông báo:", 1);
				} else {
					HD.getSoLuong().set(rowSelected, soLuong);
					x.setSoLuong(x.getSoLuong() - soLuong + old);
					updateTableHDData();
					updateTableTBData();
					btXoa.setEnabled(false);
					btSua.setEnabled(false);
				}
			}catch (NumberFormatException er) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Số lượng thiết bị chỉ được nhập chữ số!!", "Thông báo:", 1);
			}
		}
	}
	
	public void btThanhToanActionPerformed(ActionEvent evt) {
		KH = TTKH.getKhachHang();
		if(KH == null) {
			JOptionPane.showMessageDialog(null, "Thông tin khách hàng không được để trống!!", "Thông báo:", 1);
		}else {
			HD.setNhanVien(Activity);
			HD.setKhachHang(KH);
			if(dsHD.addHD(HD)) {
				//Thay đổi số lượng sản phẩm trong database
				for (SanPham x : HD.getDsTB()) {
					dsTB.repairTB(dsTB.SearchTB(x.getMaTB()));
				}
				dsKH.ThemKH(KH);
				updateTableTBData();
				updateTableDSHDData();
				JOptionPane.showMessageDialog(null, "Thanh toán thành công ^-^", "Thông báo:", 1);
			}else JOptionPane.showMessageDialog(null, "Thanh toán thất bại .-.", "Thông báo:", 1);
			btThanhToan.setEnabled(false);
		}
	}
	
	public void btXemChiTietHDActionPerformed(ActionEvent evt){
		int rowSelected = tableDSHD.getSelectedRow();
		if(rowSelected != -1) {
			HD = dsHD.SearchHD(tableDSHD.getValueAt(rowSelected, 0).toString());
			updateTableHDData();
			jTabbedPane.setSelectedIndex(1);
		}
	}
	//set JTable Columns Width
	public void setJTableColumnsWidth(JTable table, int... tablePreferredWidth) {
		TableColumnModel columnModel = table.getColumnModel();
		for (int i = 0; i < tablePreferredWidth.length; i++)
			columnModel.getColumn(i).setPreferredWidth(tablePreferredWidth[i]);
	}
	// function to generate a random string of length n 
    public String getAlphaString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789" +
                                    "abcdefghijklmnopqrstuvwxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    }
}

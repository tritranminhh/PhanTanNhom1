package presentationlayer;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
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

import com.toedter.calendar.JDateChooser;

import businesslayer.QLLoaiSP;
import businesslayer.QLMau;
import businesslayer.QLNhaCC;
import businesslayer.QLNhanVien;
import businesslayer.QLSanPham;
import businesslayer.QLXuatSu;
import entity.NhanVien;
import entity.LoaiSanPham;
import entity.Mau;
import entity.NhaCungCap;
import entity.SanPham;
import entity.XuatSu;

@SuppressWarnings("serial")
public class GUIAdmin extends JFrame{

	/**
	 * TabbedPane
	 */
	private JTabbedPane jTabbedPane;
	/**
	 * Button
	 */
	private JButton btLogout;
	//Thiết bị
	private JButton btfilterTB;
	private JButton btThemMoiTB;
	private JButton btClearTB;
	private JButton btXoaTB;
	private JButton btSuaTB;
	//Tài khoản
	private JButton btfilterTK;
	private JButton btThemMoiTK;
	private JButton btClearTK;
	private JButton btXoaTK;
	private JButton btSuaTK;
	private JRadioButton isAdmin;
	//Nhà cung cấp
	private JButton btfilterCC;
	private JButton btThemMoiCC;
	private JButton btClearCC;
	private JButton btXoaCC;
	private JButton btSuaCC;
	/**
	 * text filed
	 */
	//Thiết bị
	private JTextField filterTextTB;
	private JTextField txtMaTB;
	private JTextField txtTenTB;
	
	private JTextField txtHanBaoTri;
	private JTextField txtDonVi;
	private JTextField txtSoLuong;
	private JTextField txtgGiaTMDV;
	private JTextField txtMota;
	//Tài khoản
	private JTextField filterTextTK;
	private JTextField txtNamSinhTK;
	private JTextField txtTenTK;
	private JPasswordField txtPassTK;
	private JPasswordField txtPassConfirmTK;
	private JTextField txtTenChuTK;
	private JTextField txtEmailTK;
	private JTextField txtDiaChiTK;
	private JTextField txtSoDTTK;
	//Nhà cung cấp
	private JTextField filterTextCC;
	private JTextField txtMaNhaCC;
	private JTextField txtTenNhaCC;
	private JTextField txtEmailNhaCC;
	private JTextField txtDiaChiNhaCC;
	private JTextField txtSoDTNhaCC;
	/*
	 * table
	 */
	//Thiết bị
	private JTable tableTB;
	private DefaultTableModel dfmodelTB;
	//Tài khoản
	private JTable tableTK;
	private DefaultTableModel dfmodelTK;
	//Nhà cung cấp
	private JTable tableCC;
	private DefaultTableModel dfmodelCC;
	//Sort
	private TableRowSorter<TableModel> sorterTB;
	private TableRowSorter<TableModel> sorterTK;
	private TableRowSorter<TableModel> sorterCC;
	/*
	 * dinh dang
	 */
	Locale locale = new Locale("vi", "VN");
	NumberFormat format = NumberFormat.getCurrencyInstance(locale);
	SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * comboBox
	 */
	//Thiết bị
	private JComboBox<String> jbTB;
	private String[] itemsTB = {
			"Mã Sản phẩm", "Tên Sản phẩm", "Loại Sản phẩm", "Màu Sắc", "Nhà cung cấp", "Xuất sứ",
			"Ngày nhập", "Hạn bảo trì", "Đơn vị tính", "Số lượng", "Giá trên mỗi Sản phẩm", "Mô tả"
	};
	private JComboBox<String> jbTBMau;
	private JComboBox<String> jbLoaiTB;
	//Tài khoản
	private JComboBox<String> jbTK;
	private String[] itemsTK = {
			"Tên tài khoản", "Is Admin", "Tên Chủ TK", "Năm sinh", "Số điện thoại", "Email", "Địa chỉ"
	};
	//Nhà cung cấp
	private JComboBox<String> jbNCC;
	private String[] itemsNCC = {
			"Mã nhà CC", "Tên Nhà CC", "Địa chỉ", "Số điện thoại", "Email"
	};
	//Tên Nhà cung cấp
	private JComboBox<String> jbTenNCC;
	//Xuất sứ
	private JComboBox<String> jbXuatSu;
	//QLNCC
	private QLNhaCC dsNCC = new QLNhaCC();
	//QLTB
	private QLSanPham dsTB = new QLSanPham();
	//QLTK
	private QLNhanVien mngAcc = new QLNhanVien();
	//QLMAU
	private QLMau dsMau = new QLMau();
	//QLLoaiTB
	private QLLoaiSP dsLSP = new QLLoaiSP();
	//QLXuatSU
	private QLXuatSu dsXS = new QLXuatSu();
	private JDateChooser dateChooser;
	private Box b2_1;
	/*
	 * GUI
	 */
	public GUIAdmin() {
		// TODO Auto-generated constructor stub
		setTitle("Admin");
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		//UI
		Box bQLTB, bQLTK, bQLNCC, b1, b2, b3;
		/**
		 * Tab quản lý thiết bị
		 */
		// Tab
		bQLTB = Box.createVerticalBox();
		jTabbedPane = new JTabbedPane();
		jTabbedPane.addTab("Quản lý thiết bị", bQLTB);
		getContentPane().add(jTabbedPane);	
		JLabel jLabel = new JLabel("THÔNG TIN SẢN PHẨM");
		jLabel.setFont(new Font("Tahoma", 1, 30)); 
        jLabel.setForeground(new Color(25, 25, 225));
        bQLTB.add(Box.createVerticalStrut(15));
        bQLTB.add(b1 = Box.createHorizontalBox());
        	b1.add(jLabel);
		bQLTB.add(Box.createVerticalStrut(30));
		bQLTB.add(b1 = Box.createHorizontalBox());
			b1.add(Box.createHorizontalStrut(25));
			b1.add(b2 = Box.createVerticalBox());
				b2.add(b3 = Box.createHorizontalBox());
					b3.add(new JLabel("Mã sản phẩm"));
					b3.add(Box.createHorizontalStrut(33));
					b3.add(txtMaTB = new JTextField());
				b2.add(Box.createVerticalStrut(10));
				b2.add(b3 = Box.createHorizontalBox());
					b3.add(new JLabel("Tên sản phẩm"));
					b3.add(Box.createHorizontalStrut(30));
					b3.add(txtTenTB = new JTextField());
				b2.add(Box.createVerticalStrut(10));
				b2.add(b3 = Box.createHorizontalBox());
					b3.add(new JLabel("Loại sản phẩm"));
					b3.add(Box.createHorizontalStrut(28));
					b3.add(jbLoaiTB = new JComboBox<String>());
					jbLoaiTB.setModel(new DefaultComboBoxModel<String>());
				b2.add(Box.createVerticalStrut(10));
				b2.add(b3 = Box.createHorizontalBox());
					b3.add(new JLabel("Màu sắc"));
					b3.add(Box.createHorizontalStrut(45));
					b3.add(jbTBMau = new JComboBox<String>());
					jbTBMau.setModel(new DefaultComboBoxModel<String>());
				b2.add(Box.createVerticalStrut(10));
				b2.add(b3 = Box.createHorizontalBox());
					b3.add(new JLabel("Nhà Cung cấp"));
					b3.add(Box.createHorizontalStrut(14));
					b3.add(jbTenNCC = new JComboBox<String>());
				b2.add(Box.createVerticalStrut(10));
				b2.add(b3 = Box.createHorizontalBox());
					b3.add(new JLabel("Xuất sứ"));
					b3.add(Box.createHorizontalStrut(50));
					b3.add(jbXuatSu = new JComboBox<String>());
					jbXuatSu.setModel(new DefaultComboBoxModel<String>());
			b1.add(Box.createHorizontalStrut(300));
			b1.add(b2_1 = Box.createVerticalBox());
				b2_1.add(b3 = Box.createHorizontalBox());
					b3.add(new JLabel("Ngày nhập"));
					dateChooser.setBounds(20, 20, 200, 20);
					b3.add(dateChooser);
				b2_1.add(Box.createVerticalStrut(10));
				b2_1.add(b3 = Box.createHorizontalBox());
					b3.add(new JLabel("Hạn bảo trì"));
					b3.add(Box.createHorizontalStrut(40));
					b3.add(txtHanBaoTri = new JTextField());
				b2_1.add(Box.createVerticalStrut(10));
				b2_1.add(b3 = Box.createHorizontalBox());
					b3.add(new JLabel("Đơn vị"));
					b3.add(Box.createHorizontalStrut(67));
					b3.add(txtDonVi = new JTextField());
				b2_1.add(Box.createVerticalStrut(10));
				b2_1.add(b3 = Box.createHorizontalBox());
					b3.add(new JLabel("Số lượng"));
					b3.add(Box.createHorizontalStrut(49));
					b3.add(txtSoLuong = new JTextField());
				b2_1.add(Box.createVerticalStrut(10));
				b2_1.add(b3 = Box.createHorizontalBox());
					b3.add(new JLabel("Giá trên mỗi ĐV"));
					b3.add(Box.createHorizontalStrut(12));
					b3.add(txtgGiaTMDV = new JTextField());
				b2_1.add(Box.createVerticalStrut(10));
				b2_1.add(b3 = Box.createHorizontalBox());
					b3.add(new JLabel("Mô tả"));
					b3.add(Box.createHorizontalStrut(72));
					b3.add(txtMota = new JTextField());
			b1.add(Box.createHorizontalStrut(25));
		bQLTB.add(Box.createVerticalStrut(30));

		//create table
		tableTB = new JTable(dfmodelTB = new DefaultTableModel(itemsTB, 0)) {
			public boolean isCellEditable(int row,int column) { //set non-Editable
				switch(column){
		      	default: return false; }}};
      	tableTB.setAutoCreateRowSorter(true);
      	sorterTB = new TableRowSorter<TableModel>(tableTB.getModel()); // Sorting and Filtering
      	tableTB.setRowSorter(sorterTB);
		bQLTB.add(new JScrollPane(tableTB));
		//event cho table
		tableTB.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tableTBMouseClicked(evt);
			}
		});
		//Chức năng của QLTB
		bQLTB.add(b1 = Box.createHorizontalBox());
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
		
		panel.add(btXoaTB = new JButton("Xóa"));
		btXoaTB.setEnabled(false);
		btXoaTB.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btXoaTBActionPerformed(e);
			}
		});
		
		panel.add(btSuaTB = new JButton("Sửa"));
		btSuaTB.setEnabled(false);
		btSuaTB.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btSuaTBActionPerformed(e);
			}
		});
		
		panel.add(btThemMoiTB = new JButton("Thêm mới"));
		btThemMoiTB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btThemMoiTBActionPerformed(e);
			}
		});
		b1.add(panel);
		
		/**
		 * Tab quản lý tài khoản
		 */
		//tab
		bQLTK = Box.createVerticalBox();
		jTabbedPane.addTab("Quản lý tài khoản", bQLTK);
		
		jLabel = new JLabel("THÔNG TIN TÀI KHOẢN");
		jLabel.setFont(new Font("Tahoma", 1, 30)); 
        jLabel.setForeground(new Color(25, 25, 225));
        bQLTK.add(Box.createVerticalStrut(15));
        bQLTK.add(b1 = Box.createHorizontalBox());
        	b1.add(jLabel);
    	bQLTK.add(Box.createVerticalStrut(30));
    	bQLTK.add(b1 = Box.createHorizontalBox());
		b1.add(Box.createHorizontalStrut(25));
		b1.add(b2 = Box.createVerticalBox());
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Tên tài khoản"));
				b3.add(Box.createHorizontalStrut(13));
				b3.add(txtTenTK = new JTextField());
				b3.add(Box.createHorizontalStrut(10));
				b3.add(isAdmin = new JRadioButton("Admin"));
				isAdmin.setBackground((jTabbedPane.getBackground())); // màu lạ nhể
			b2.add(Box.createVerticalStrut(10));
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Mật khẩu"));
				b3.add(Box.createHorizontalStrut(38));
				b3.add(txtPassTK = new JPasswordField());
			b2.add(Box.createVerticalStrut(10));
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Xác nhận MK"));
				b3.add(Box.createHorizontalStrut(16));
				b3.add(txtPassConfirmTK = new JPasswordField());
			b2.add(Box.createVerticalStrut(10));
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Năm sinh"));
				b3.add(Box.createHorizontalStrut(38));
				b3.add(txtNamSinhTK = new JTextField());
		b1.add(Box.createHorizontalStrut(300));
		b1.add(b2 = Box.createVerticalBox());
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Tên chủ TK"));
				b3.add(Box.createHorizontalStrut(25));
				b3.add(txtTenChuTK = new JTextField());
			b2.add(Box.createVerticalStrut(10));
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Số điện thoại"));
				b3.add(Box.createHorizontalStrut(15));
				b3.add(txtSoDTTK = new JTextField());
			b2.add(Box.createVerticalStrut(10));
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Email"));
				b3.add(Box.createHorizontalStrut(58));
				b3.add(txtEmailTK = new JTextField());
			b2.add(Box.createVerticalStrut(10));
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Địa chỉ"));
				b3.add(Box.createHorizontalStrut(52));
				b3.add(txtDiaChiTK = new JTextField());
		b1.add(Box.createHorizontalStrut(25));
		bQLTK.add(Box.createVerticalStrut(30));

		//create table
		tableTK = new JTable(dfmodelTK = new DefaultTableModel(itemsTK, 0)) {
			public boolean isCellEditable(int row,int column) { //set non-Editable
				switch(column){
		      	default: return false; }
			}
			 @Override
	         public Class<?> getColumnClass(int columnIndex) {
	           Class<?> clazz = String.class;
	           switch (columnIndex) {
	             case 1:
	               clazz = Boolean.class;
	               break;
	           }
	           return clazz;
	         }
		};
      	tableTK.setAutoCreateRowSorter(true);
      	sorterTK = new TableRowSorter<TableModel>(tableTK.getModel()); // Sorting and Filtering
      	tableTK.setRowSorter(sorterTK);
		bQLTK.add(b1 = Box.createHorizontalBox());
			b1.add(new JScrollPane(tableTK));
		//event cho table
		tableTK.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tableTKMouseClicked(evt);
			}
		});
		//Chức năng của QLTB
		bQLTK.add(b1 = Box.createHorizontalBox());
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


		panel.add(btfilterTK = new JButton("Filter"));
		btfilterTK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newFilterTK();
			}
		});
		panel.add(filterTextTK = new JTextField(10));
		filterTextTK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newFilterTK();
			}
		});
		jbTK = new JComboBox<String>();
		jbTK.setPreferredSize(new Dimension(100, 20));;
		jbTK.setModel(new DefaultComboBoxModel<String>(itemsTK));
		panel.add(jbTK);

		panel.add(btClearTK = new JButton("Clear"));
		btClearTK.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btClearTKActionPerformed(e);
			}
		});

		panel.add(btXoaTK = new JButton("Xóa"));
		btXoaTK.setEnabled(false);
		btXoaTK.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btXoaTKActionPerformed(e);
			}
		});

		panel.add(btSuaTK = new JButton("Sửa"));
		btSuaTK.setEnabled(false);
		btSuaTK.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btSuaTKActionPerformed(e);
			}
		});

		panel.add(btThemMoiTK = new JButton("Thêm mới"));
		btThemMoiTK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btThemMoiTKActionPerformed(e);
			}
		});
		b1.add(panel);
		/*
		 * Quản lý nhà cung cấp
		 */
		bQLNCC = Box.createVerticalBox();
		jTabbedPane.add("Quản lý nhà cung cấp", bQLNCC);
		jLabel = new JLabel("THÔNG TIN NHÀ CUNG CẤP");
		jLabel.setFont(new Font("Tahoma", 1, 30)); 
        jLabel.setForeground(new Color(25, 25, 225));
        bQLNCC.add(Box.createVerticalStrut(15));
        bQLNCC.add(b1 = Box.createHorizontalBox());
        	b1.add(jLabel);
    	bQLNCC.add(Box.createVerticalStrut(30));
    	bQLNCC.add(b1 = Box.createHorizontalBox());
		b1.add(Box.createHorizontalStrut(25));
		b1.add(b2 = Box.createVerticalBox());
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Mã nhà CC"));
				b3.add(Box.createHorizontalStrut(36));
				b3.add(txtMaNhaCC = new JTextField());
			b2.add(Box.createVerticalStrut(10));
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Tên Nhà CC"));
				b3.add(Box.createHorizontalStrut(31));
				b3.add(txtTenNhaCC = new JTextField());		
			b2.add(Box.createVerticalStrut(25));
		b1.add(Box.createHorizontalStrut(300));
		b1.add(b2 = Box.createVerticalBox());
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Địa chỉ"));
				b3.add(Box.createHorizontalStrut(48));
				b3.add(txtDiaChiNhaCC = new JTextField());
			b2.add(Box.createVerticalStrut(10));
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Số điện thoại"));
				b3.add(Box.createHorizontalStrut(13));
				b3.add(txtSoDTNhaCC = new JTextField());
			b2.add(Box.createVerticalStrut(10));
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(new JLabel("Email"));
				b3.add(Box.createHorizontalStrut(57));
				b3.add(txtEmailNhaCC = new JTextField());
		b1.add(Box.createHorizontalStrut(25));
		bQLNCC.add(Box.createVerticalStrut(30));

		//create table
		tableCC = new JTable(dfmodelCC = new DefaultTableModel(itemsNCC, 0)) {
			public boolean isCellEditable(int row,int column) { //set non-Editable
				switch(column){
		      	default: return false; }
			}
		};
		tableCC.setAutoCreateRowSorter(true);
      	sorterCC = new TableRowSorter<TableModel>(tableCC.getModel()); // Sorting and Filtering
      	tableCC.setRowSorter(sorterCC);
      	bQLNCC.add(b1 = Box.createHorizontalBox());
      		b1.add(new JScrollPane(tableCC));
		//event cho table
      	tableCC.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				tableCCMouseClicked(evt);
			}
		});
		//Chức năng của QLCC
		bQLNCC.add(b1 = Box.createHorizontalBox());
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

		panel.add(btfilterCC = new JButton("Filter"));
		btfilterCC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newFilterCC();
			}
		});
		panel.add(filterTextCC = new JTextField(10));
		filterTextCC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				newFilterCC();
			}
		});
		jbNCC = new JComboBox<String>();
		jbNCC.setPreferredSize(new Dimension(100, 20));;
		jbNCC.setModel(new DefaultComboBoxModel<String>(itemsNCC));
		panel.add(jbNCC);

		panel.add(btClearCC = new JButton("Clear"));
		btClearCC.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btClearCCActionPerformed(e);
			}
		});

		panel.add(btXoaCC = new JButton("Xóa"));
		btXoaCC.setEnabled(false);
		btXoaCC.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btXoaCCActionPerformed(e);
			}
		});

		panel.add(btSuaCC = new JButton("Sửa"));
		btSuaCC.setEnabled(false);
		btSuaCC.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btSuaCCActionPerformed(e);
			}
		});

		panel.add(btThemMoiCC = new JButton("Thêm mới"));
		btThemMoiCC.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btThemMoiNCCActionPerformed(e);
			}
		});
		b1.add(panel);
		
		//update data
		updateMau();
		updateTenNCC();
		updateLoaiTB();
		updateTableTKData();
		updateXuatSu();
		updateTableCCData();
		updateTableTBData();
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
				x.getMaTB(), x.getTenTB(), x.getLoaiTB().getTenLTB(), x.getMauSac().getTenMau(), x.getNhaCC().getTenNhaCC(),
				x.getXuatSu().getTenNuoc(), sf.format(x.getNgayNhap().getTime()), x.getHanBaoTri(), x.getDonVi(), x.getSoLuong(), 
				format.format(x.getGiaTMDV()), x.getMoTa()
			});
		}
		//setJTableColumnsWidth
		setJTableColumnsWidth(tableTB, 80, 120, 10, 10, 10, 10, 10, 10, 10, 10, 30, 30);
	}
	
	public void updateTableTKData() {
		while(dfmodelTK.getRowCount()>0) {
			dfmodelTK.removeRow(0);
		}
		for(NhanVien x : mngAcc.getListAcc()) {
			dfmodelTK.addRow(new Object[] {
				x.getID(), x.isAdmin(), x.getNameUser(),
				x.getBirthYear(), x.getNumber(), x.getEmail(), x.getAddress()
			});
		}
	}
	
	public void updateTableCCData() {
		while(dfmodelCC.getRowCount()>0) {
			dfmodelCC.removeRow(0);
		}
		for(NhaCungCap x : dsNCC.getDSNCC()) {
			dfmodelCC.addRow(new Object[] {
				x.getMaNhaCC(), x.getTenNhaCC(),  x.getDiaChi(),
				x.getSoDT(), x.getEmail()
			});	
		}
	}
	
	public void tableTBMouseClicked(MouseEvent e) {
		int rowSelected = tableTB.getSelectedRow();
		if (rowSelected != -1) {
			SanPham x = new SanPham();
			x =	dsTB.SearchTB(tableTB.getValueAt(rowSelected, 0).toString());
			txtMaTB.setText(x.getMaTB());
			txtMaTB.setEditable(false);
			txtTenTB.setText(x.getTenTB());
			txtTenTB.requestFocus();
			jbLoaiTB.setSelectedItem(x.getLoaiTB().getTenLTB());
			jbTBMau.setSelectedItem(x.getMauSac().getTenMau());
			jbNCC.setSelectedItem(x.getNhaCC().getTenNhaCC());
			jbXuatSu.setSelectedItem(x.getXuatSu().getTenNuoc());
			dateChooser.setDate(x.getNgayNhap().getTime());
			txtHanBaoTri.setText(x.getHanBaoTri());
			txtDonVi.setText(x.getDonVi());
			txtSoLuong.setText(String.format("%d", x.getSoLuong()));
			txtgGiaTMDV.setText(String.format("%.0f", x.getGiaTMDV()));
			txtMota.setText(x.getMoTa());
			btThemMoiTB.setEnabled(false);
			btSuaTB.setEnabled(true);
			btXoaTB.setEnabled(true);
		}
	}
	
	public void tableTKMouseClicked(MouseEvent e) {
		int rowSelected = tableTK.getSelectedRow();
		if (rowSelected != -1) {
			NhanVien x = new NhanVien();
			x =	mngAcc.searchAcc(tableTK.getValueAt(rowSelected, 0).toString());
			txtTenTK.setText(x.getID());
			txtTenTK.setEditable(false);
			isAdmin.setSelected(x.isAdmin());
			txtPassTK.setText(x.getPW());
			txtPassConfirmTK.setText(x.getPW());
			txtNamSinhTK.setText(String.format("%d", x.getBirthYear()));
			txtTenChuTK.setText(x.getNameUser());
			txtTenChuTK.requestFocus();
			txtSoDTTK.setText(x.getNumber());
			txtEmailTK.setText(x.getEmail());
			txtDiaChiTK.setText(x.getAddress());
			btThemMoiTK.setEnabled(false);
			btSuaTK.setEnabled(true);
			btXoaTK.setEnabled(true);
		}
	}
	
	public void tableCCMouseClicked(MouseEvent e) {
		int rowSelected = tableCC.getSelectedRow();
		if (rowSelected != -1) {
			NhaCungCap x = new NhaCungCap();
			x =	dsNCC.SearchNCC(tableCC.getValueAt(rowSelected, 0).toString());
			txtMaNhaCC.setText(x.getMaNhaCC());
			txtMaNhaCC.setEditable(false);
			txtTenNhaCC.requestFocus();		
			txtTenNhaCC.setText(x.getTenNhaCC());
			txtDiaChiNhaCC.setText(x.getDiaChi());
			txtSoDTNhaCC.setText(x.getSoDT());
			txtEmailNhaCC.setText(x.getEmail());
			btThemMoiCC.setEnabled(false);
			btSuaCC.setEnabled(true);
			btXoaCC.setEnabled(true);
		}
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
	
	public void newFilterTK() {
		RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        int selected = jbTK.getSelectedIndex();
        try {
            rf = RowFilter.regexFilter("(?i)" + filterTextTK.getText().trim(), selected);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorterTK.setRowFilter(rf);
	}
	
	public void newFilterCC() {
		RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        int selected = jbNCC.getSelectedIndex();
        try {
            rf = RowFilter.regexFilter("(?i)" + filterTextCC.getText().trim(), selected);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorterCC.setRowFilter(rf);
	}
	
	public void btThemMoiTBActionPerformed(ActionEvent e){
		if(isValidDataTB()) {
			SanPham x = new SanPham();
			GregorianCalendar ngayNhap = new GregorianCalendar();
			x.setMaTB(txtMaTB.getText().trim());
			x.setTenTB(txtTenTB.getText().trim());
			x.setLoaiTB(dsLSP.timLTB(jbLoaiTB.getItemAt(jbLoaiTB.getSelectedIndex())));
			x.setMauSac(dsMau.timMau(jbTBMau.getItemAt(jbTBMau.getSelectedIndex())));
			x.setNhaCC(dsNCC.SearchNCCName(jbTenNCC.getItemAt(jbTenNCC.getSelectedIndex())));
			x.setXuatSu(dsXS.timNuoc(jbXuatSu.getItemAt(jbXuatSu.getSelectedIndex())));
			try {
				ngayNhap.setTime(dateChooser.getDate());
				x.setNgayNhap(ngayNhap);
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
			}
			x.setHanBaoTri(txtHanBaoTri.getText().trim());
			x.setDonVi(txtDonVi.getText().trim());
			x.setSoLuong(Integer.parseInt(txtSoLuong.getText().trim()));
			x.setGiaTMDV(Double.parseDouble(txtgGiaTMDV.getText().trim()));
			x.setMoTa(txtMota.getText().trim());
			if (dsTB.addTB(x)) {
				updateTableTBData();
				JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", 1);
			}
			else JOptionPane.showMessageDialog(null, "Thêm thất bại", "Thông báo", 1);
		}
	}
	
	@SuppressWarnings("deprecation")
	public void btThemMoiTKActionPerformed(ActionEvent e) {
		if(isValidDataTK()) {
			NhanVien x = new NhanVien();
			x.setID(txtTenTK.getText().trim());
			x.setAdmin(isAdmin.isSelected());
			x.setPW(txtPassTK.getText().trim());
			x.setNameUser(txtTenChuTK.getText().trim());
			x.setBirthYear(Integer.parseInt(txtNamSinhTK.getText().trim()));
			x.setNumber(txtSoDTTK.getText().trim());
			x.setEmail(txtEmailTK.getText().trim());
			x.setAddress(txtDiaChiTK.getText().trim());
			if(mngAcc.addAcc(x)) {
				updateTableTKData();
				JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", 1);
			}
			else JOptionPane.showMessageDialog(null, "Thêm thất bại", "Thông báo", 1);
		}
	}
	
	public void btThemMoiNCCActionPerformed(ActionEvent e) {
		if(isValidDataNCC()) {
			NhaCungCap x = new NhaCungCap();
			x.setMaNhaCC(txtMaNhaCC.getText().trim());
			x.setTenNhaCC(txtTenNhaCC.getText().trim());
			//không set loại thiết bị cung cấp. Thiết bị cung cấp tự cập nhật bên obj thiết bị
			x.setDiaChi(txtDiaChiNhaCC.getText().trim());
			x.setEmail(txtEmailNhaCC.getText().trim());
			x.setSoDT(txtSoDTNhaCC.getText().trim());
			if(dsNCC.addNCC(x)) {
				updateTableCCData();
				jbTenNCC.addItem(x.getTenNhaCC());
				JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", 1);
			}
			else JOptionPane.showMessageDialog(null, "Thêm thất bại", "Thông báo", 1);
		}
	}
	
	public void btClearTBActionPerformed(ActionEvent e){
		txtMaTB.setText("");
		txtMaTB.setEditable(true);
		txtMaTB.requestFocus();
		txtTenTB.setText("");
		jbLoaiTB.setSelectedIndex(0);
		jbTBMau.setSelectedIndex(0);
		jbNCC.setSelectedIndex(0);
		jbXuatSu.setSelectedIndex(0);
		Date today = new Date();
		dateChooser.setDate(today);
		txtHanBaoTri.setText("");
		txtDonVi.setText("");
		txtSoLuong.setText("");
		txtgGiaTMDV.setText("");
		txtMota.setText("");
		filterTextTB.setText("");
		tableTB.clearSelection();
		btThemMoiTB.setEnabled(true);
		btSuaTB.setEnabled(false);
		btXoaTB.setEnabled(false);
		newFilterTB();
	}
	
	public void btClearTKActionPerformed(ActionEvent e) {
		txtTenTK.setText("");
		txtTenTK.requestFocus();
		txtTenTK.setEditable(true);
		txtPassTK.setText("");
		txtPassConfirmTK.setText("");
		txtNamSinhTK.setText("");
		txtTenChuTK.setText("");
		txtSoDTTK.setText("");
		txtEmailTK.setText("");
		txtDiaChiTK.setText("");
		filterTextTK.setText("");
		tableTK.clearSelection();
		isAdmin.setSelected(false);
		btThemMoiTK.setEnabled(true);
		btSuaTK.setEnabled(false);
		btXoaTK.setEnabled(false);
		newFilterTK();
	}
	
	public void btClearCCActionPerformed(ActionEvent e) {
		txtMaNhaCC.setText("");
		txtMaNhaCC.setEditable(true);
		txtMaNhaCC.requestFocus();
		txtTenNhaCC.setText("");
		txtSoDTNhaCC.setText("");
		txtEmailNhaCC.setText("");
		txtDiaChiNhaCC.setText("");
		filterTextCC.setText("");
		tableCC.clearSelection();
		btThemMoiCC.setEnabled(true);
		btSuaCC.setEnabled(false);
		btXoaCC.setEnabled(false);
		newFilterCC();
	}
	
	public void btXoaTBActionPerformed(ActionEvent e){
		if(tableTB.getSelectedRow() != -1) {
			if (JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa thiết bị đang chọn ko??",
					"Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if(dsTB.removeTB(txtMaTB.getText().trim())) {
					updateTableTBData();
					JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", 1);
				}
				else JOptionPane.showMessageDialog(null, "Xóa thất bại", "Thông báo", 1);
				btClearTBActionPerformed(e);
			}
		}
		else JOptionPane.showMessageDialog(null, "Chọn thiết bị cần xóa", "Thông báo", 1);
	}
	
	public void btXoaTKActionPerformed(ActionEvent e) {
		if(tableTK.getSelectedRow() != -1) {
			if (JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa tài khoản đang chọn ko??",
					"Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if(mngAcc.removeAcc(txtTenTK.getText().trim())) {
					updateTableTKData();
					JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", 1);
				}
				else JOptionPane.showMessageDialog(null, "Xóa thất bại", "Thông báo", 1);
				btClearTKActionPerformed(e);
			}
			btClearTKActionPerformed(e);
		}
		else JOptionPane.showMessageDialog(null, "Chọn tài khoản cần xóa", "Thông báo", 1);
	}
	
	public void btXoaCCActionPerformed(ActionEvent e) {
		if(tableCC.getSelectedRow() != -1) {
			if (JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa nhà cung cấp đang chọn ko??",
					"Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if(dsNCC.removeNCC(txtMaNhaCC.getText().trim())) {
					updateTableCCData();
					JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", 1);
				}
				else JOptionPane.showMessageDialog(null, "Xóa thất bại", "Thông báo", 1);	
				btClearCCActionPerformed(e);
			}
		}
		else JOptionPane.showMessageDialog(null, "Chọn nhà cung cấp cần xóa", "Thông báo", 1);
	}
	
	public void btSuaTBActionPerformed(ActionEvent e){
		if(tableTB.getSelectedRow() != -1) {
			if (JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa thiết bị đang chọn ko??",
					"Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if(isValidDataTB()) {
					SanPham x = new SanPham();
					GregorianCalendar ngayNhap = new GregorianCalendar();
					x.setMaTB(txtMaTB.getText().trim());
					x.setTenTB(txtTenTB.getText().trim());
					x.setLoaiTB(dsLSP.timLTB(jbLoaiTB.getItemAt(jbLoaiTB.getSelectedIndex())));
					x.setMauSac(dsMau.timMau(jbTBMau.getItemAt(jbTBMau.getSelectedIndex())));
					x.setNhaCC(dsNCC.SearchNCCName(jbTenNCC.getItemAt(jbTenNCC.getSelectedIndex())));
					x.setXuatSu(dsXS.timNuoc(jbXuatSu.getItemAt(jbXuatSu.getSelectedIndex())));
					try {
						ngayNhap.setTime(dateChooser.getDate());
						x.setNgayNhap(ngayNhap);
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
					}
					x.setNgayNhap(ngayNhap);
					x.setHanBaoTri(txtHanBaoTri.getText().trim());
					x.setDonVi(txtDonVi.getText().trim());
					x.setSoLuong(Integer.parseInt(txtSoLuong.getText().trim()));
					x.setGiaTMDV(Double.parseDouble(txtgGiaTMDV.getText().trim()));
					x.setMoTa(txtMota.getText().trim());
					if (dsTB.repairTB(x)) {
						updateTableTBData();
						JOptionPane.showMessageDialog(null, "sửa thành công", "Thông báo", 1);
					}
					else JOptionPane.showMessageDialog(null, "sửa thất bại", "Thông báo", 1);
				}
			}
		}
		else JOptionPane.showMessageDialog(null, "Chọn thiết bị cần sửa", "Thông báo", 1);
	}
	
	@SuppressWarnings("deprecation")
	public void btSuaTKActionPerformed(ActionEvent e) {
		if(tableTK.getSelectedRow() != -1) {                                                                   
			if (JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa tài khoản đang chọn ko??",
					"Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if(isValidDataTK()) {
					NhanVien x = new NhanVien();
					x.setID(txtTenTK.getText().trim());
					x.setAdmin(isAdmin.isSelected());
					x.setPW(txtPassTK.getText().trim());
					x.setNameUser(txtTenChuTK.getText().trim());
					x.setBirthYear(Integer.parseInt(txtNamSinhTK.getText().trim()));
					x.setNumber(txtSoDTTK.getText().trim());
					x.setEmail(txtEmailTK.getText().trim());
					x.setAddress(txtDiaChiTK.getText().trim());
					if (mngAcc.changeInfor(x)) {
						updateTableTKData();
						JOptionPane.showMessageDialog(null, "sửa thành công", "Thông báo", 1);
					}
					else JOptionPane.showMessageDialog(null, "sửa thất bại", "Thông báo", 1);
				}
			}
		}
		else JOptionPane.showMessageDialog(null, "Chọn thiết bị cần sửa", "Thông báo", 1);
	}
	
	public void btSuaCCActionPerformed(ActionEvent e) {
		if(tableCC.getSelectedRow() != -1) {
			if (JOptionPane.showConfirmDialog(this,"Bạn có muốn sửa nhà cung cấp đang chọn ko??",
					"Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if(isValidDataNCC()) {
					NhaCungCap x = new NhaCungCap();
					x.setMaNhaCC(txtMaNhaCC.getText().trim());
					x.setTenNhaCC(txtTenNhaCC.getText().trim());
					x.setDiaChi(txtDiaChiNhaCC.getText().trim());
					x.setEmail(txtEmailNhaCC.getText().trim());
					x.setSoDT(txtSoDTNhaCC.getText().trim());
					if (dsNCC.repairNCC(x)) {
						updateTableCCData();
						JOptionPane.showMessageDialog(null, "sửa thành công", "Thông báo", 1);
					}
					else JOptionPane.showMessageDialog(null, "sửa thất bại", "Thông báo", 1);
				}
			}
		}
		else JOptionPane.showMessageDialog(null, "Chọn nhà cung cấp cần sửa", "Thông báo", 1);
	}
	
	public boolean isValidDataTB() {
		 if(!txtMaTB.getText().trim().matches("[a-zA-Z0-9\\-]+")) {
			 JOptionPane.showMessageDialog(null, "Mã thiết bị không được để trống.\nKhông chứa kí tự đặc biệt (ngoại trừ '-')!!", "Thông báo", 1);
			 return false;
		 }
		 if(txtTenTB.getText().trim().isEmpty()) {
			 JOptionPane.showMessageDialog(null, "Tên thiết bị không được để trống!!", "Thông báo", 1);
			 return false;
		 }
		 Date today = new Date();
		 if(dateChooser.getDate().getTime() > today.getTime()) {
			 JOptionPane.showMessageDialog(null, "Ngày nhập không được lớn hơn ngày hiện tại!!", "Thông báo", 1);
			 return false;
		 }
		 if(!txtHanBaoTri.getText().trim().matches("^[\\d].*")) {
			 JOptionPane.showMessageDialog(null, "Hạn bảo trì không được để trống.\nPhải theo định đạng (VD: 12 tháng)", "Thông báo", 1);
			 return false;
		 }
		 if(!txtSoLuong.getText().trim().matches("\\d+")) {
			 JOptionPane.showMessageDialog(null, "Số lượng không được để trống.\nChỉ chứa kí tự số!!", "Thông báo", 1);
			 return false;
		 }
		 if(!txtgGiaTMDV.getText().trim().trim().matches("\\d+")) {
			 JOptionPane.showMessageDialog(null, "Giá không được để trống.\nChỉ chứa kí tự số!!", "Thông báo", 1);
			 return false;
		 }
		return true;
	}
	
	@SuppressWarnings("deprecation")
	public boolean isValidDataTK() {
		if(!txtTenTK.getText().trim().matches("[a-zA-z0-9]{5,}")) {
			JOptionPane.showMessageDialog(null, "Tên TK tối thiểu 5 kí tự.\nKhông chưa kí tự đặc biệt!!", "Thông báo", 1);
			return false;
		}
		if(!txtPassTK.getText().trim().matches("[a-zA-z0-9]{6,}")) {
			JOptionPane.showMessageDialog(null, "Mật khẩu tối thiểu 6 kí tự.\nKhông chưa kí tự đặc biệt!!", "Thông báo", 1);
			return false;
		}
		if(!txtPassTK.getText().trim().equals(txtPassConfirmTK.getText().trim())) {
			JOptionPane.showMessageDialog(null, "Mật khẩu và mật khẩu xác nhận phải khớp nhau!!", "Thông báo", 1);
			return false;
		}
		if(!txtNamSinhTK.getText().trim().matches("[0-9]{4}")) {
			JOptionPane.showMessageDialog(null, "Năm sinh không được để trống\nĐầy đủ 4 kí tự số!!", "Thông báo", 1);
			return false;
		}
		if(txtTenChuTK.getText().trim().isEmpty()) {
			 JOptionPane.showMessageDialog(null, "Tên chủ TK không được để trống!!", "Thông báo", 1);
			 return false;
		 }
		if(!txtSoDTTK.getText().trim().matches("[0-9]{10,11}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống\n10-11 chứ số!!", "Thông báo", 1);
			return false;	
		}
		if(!txtEmailTK.getText().trim().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
			JOptionPane.showMessageDialog(null, "Sai định dạng Email!!", "Thông báo", 1);
			return false;
		}
		return true;
	}
	
	public boolean isValidDataNCC(){
		if(!txtMaNhaCC.getText().trim().matches("[a-zA-Z0-9]+")) {
			JOptionPane.showMessageDialog(null, "Mã nhà cung cấp không được để trống.\nKhông chưa kí tự đặc biệt!!", "Thông báo", 1);
			return false;
		}
		if(txtTenNhaCC.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tên nhà cung cấp không được để trống!!", "Thông báo", 1);
			return false;
		}
		if(!txtSoDTNhaCC.getText().trim().matches("[0-9]{10,11}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống\n10-11 chứ số!!", "Thông báo", 1);
			return false;
		}
		if(!txtEmailNhaCC.getText().trim().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[ a-zA-Z]{2,6}$")) {
			JOptionPane.showMessageDialog(null, "Sai định dạng Email!!", "Thông báo", 1);
			return false;
		}
		if(txtDiaChiNhaCC.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Địa chỉ nhà cung cấp không được để trống!!", "Thông báo", 1);
			return false;
		}
		return true;
	}
	//set JTable Columns Width
	public void setJTableColumnsWidth(JTable table, int... tablePreferredWidth) {
		TableColumnModel columnModel = table.getColumnModel();
		for (int i = 0; i < tablePreferredWidth.length; i++)
			columnModel.getColumn(i).setPreferredWidth(tablePreferredWidth[i]);
	}
	
	public void updateMau() {
		for (Mau x : dsMau.getAll())
			jbTBMau.addItem(x.getTenMau());
	}
	
	public void updateLoaiTB() {
		for (LoaiSanPham x : dsLSP.getDsLTB())
			jbLoaiTB.addItem(x.getTenLTB());
	}
	
	public void updateXuatSu() {
		for (XuatSu x : dsXS.getDsXS())
			jbXuatSu.addItem(x.getTenNuoc());
	}
	
	public void updateTenNCC() {
		for (NhaCungCap x : dsNCC.getDSNCC()) {
			jbTenNCC.addItem(x.getTenNhaCC());
		}
	}
}
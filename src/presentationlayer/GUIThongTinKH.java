package presentationlayer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import entity.KhachHang;

@SuppressWarnings("serial")
public class GUIThongTinKH extends JFrame{
	private JTextField tenKH;
	private JTextField diaChi;
	private JTextField soDT;
	private JTextField email;
	
	private JButton clear;
	private JButton luu;
	private JButton thoat;
	
	private KhachHang KH;
	private boolean look;
	
	public GUIThongTinKH(KhachHang KH, boolean enable) {
		// TODO Auto-generated constructor stub
		setTitle("Thông tin khách hàng");
		setSize(580, 235);
		setLocationRelativeTo(null);
		setResizable(false);
		
		this.KH = KH;
		this.look = enable;
		
		Box b, b1, b2, b3, bmg;
		this.add(b = Box.createHorizontalBox());
		b.add(bmg = Box.createHorizontalBox());
			Border borderA = BorderFactory.createLineBorder(Color.RED);
			TitledBorder titleBorderA = new TitledBorder(borderA, "Avatar");
			bmg.setBorder(titleBorderA);
			BufferedImage myPicture;
			try {
				myPicture = ImageIO.read(new File("database//avt.png"));
				BufferedImage resized = resize(myPicture, 180, 180);
				JLabel picLabel = new JLabel(new ImageIcon(resized));
				bmg.add(picLabel);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		b.add(b1 = Box.createVerticalBox());
		b1.add(b2 = Box.createVerticalBox());
			Border borderU = BorderFactory.createLineBorder(Color.RED);
			TitledBorder titleBorderU = new TitledBorder(borderU, "Form khách hàng");
			b2.setBorder(titleBorderU);
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(Box.createHorizontalStrut(10));
				b3.add(new JLabel("Tên khách hàng"));
				b3.add(Box.createHorizontalStrut(10));
				b3.add(tenKH = new JTextField());
				b3.add(Box.createHorizontalStrut(10));
			b2.add(Box.createVerticalStrut(10));
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(Box.createHorizontalStrut(10));
				b3.add(new JLabel("Địa chỉ"));
				b3.add(Box.createHorizontalStrut(62));
				b3.add(diaChi = new JTextField());
				b3.add(Box.createHorizontalStrut(10));
			b2.add(Box.createVerticalStrut(10));
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(Box.createHorizontalStrut(10));
				b3.add(new JLabel("Số điện thoại"));
				b3.add(Box.createHorizontalStrut(27));
				b3.add(soDT = new JTextField());
				b3.add(Box.createHorizontalStrut(10));
			b2.add(Box.createVerticalStrut(10));
			b2.add(b3 = Box.createHorizontalBox());
				b3.add(Box.createHorizontalStrut(10));
				b3.add(new JLabel("Email"));
				b3.add(Box.createHorizontalStrut(70));
				b3.add(email = new JTextField());
				b3.add(Box.createHorizontalStrut(10));
			b2.add(Box.createVerticalStrut(10));
			
		b1.add(b2 = Box.createVerticalBox());
			Border borderD = BorderFactory.createLineBorder(Color.RED);
			TitledBorder titleBorderD = new TitledBorder(borderD, "Chức năng");
			b2.setBorder(titleBorderD);
			JPanel panel = new JPanel();
			panel.add(luu = new JButton("Lưu thông tin KH"));
			panel.add(clear = new JButton("Xóa form"));
			panel.add(thoat = new JButton("Thoát"));
			b2.add(panel);
			
		luu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				luuActionListener(e);
			}
		});
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearActionListener(e);
			}
		});
		thoat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				thoatActionListener(e);
			}
		});
		
		updateInfoKH();
	}
	
	public void updateInfoKH() {
		if(KH != null) {
			tenKH.setText(KH.getTenKH());
			tenKH.requestFocus();
			diaChi.setText(KH.getDiaChi());
			soDT.setText(KH.getSoDT());
			email.setText(KH.getEmail());
		}
		if(!look) {
			tenKH.setEnabled(false);
			diaChi.setEnabled(false);
			soDT.setEnabled(false);
			email.setEnabled(false);
			luu.setEnabled(false);
			clear.setEnabled(false);
		}
	}
	
	public void luuActionListener(ActionEvent e) {
		if(isValidData()) {
			if (JOptionPane.showConfirmDialog(this,"Bạn có muốn lưu thông tin khách hàng ko??",
					"Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				KH = new KhachHang();
				KH.setMaKH(getAlphaNumericString(10));
				KH.setTenKH(tenKH.getText().trim().trim());
				KH.setDiaChi(diaChi.getText().trim().trim());
				KH.setSoDT(soDT.getText().trim().trim());
				KH.setEmail(email.getText().trim().trim());
				dispose();
			}
		}
	}
	
	public void clearActionListener(ActionEvent e) {
		tenKH.setText("");
		tenKH.requestFocus();
		diaChi.setText("");
		soDT.setText("");
		email.setText("");
	}
	
	public void thoatActionListener(ActionEvent e) {
		if (JOptionPane.showConfirmDialog(this,"Bạn có muốn thoát ko??",
				"Cảnh báo",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			dispose();
		}
	}
	
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
	public boolean isValidData() {
		if(tenKH.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tên khách hàng không được để trống!!", "Thông báo", 1);
			return false;
		}
		if(!soDT.getText().trim().matches("[0-9]{10,11}")) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không được để trống.\nCó 10-11 chứ số!!", "Thông báo", 1);
			return false;
		}
		if(!email.getText().trim().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[ a-zA-Z]{2,6}$")) {
			JOptionPane.showMessageDialog(null, "Sai định dạng Email!!", "Thông báo", 1);
			return false;
		}
		if(diaChi.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Địa chỉ khách hàng không được để trống!!", "Thông báo", 1);
			return false;
		}
		return true;
	}
	
	static String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
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
	public KhachHang getKhachHang() {
		return KH;
	}
}

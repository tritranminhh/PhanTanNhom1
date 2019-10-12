package presentationlayer;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import entity.NhanVien;

@SuppressWarnings("serial")
public class GUIDangNhap extends JFrame implements ActionListener, KeyListener{

	private JLabel lbllogOn;
	private JTextField txtuserName;
	private JPasswordField txtpassWord;
	private JButton btnlogOn,btnExit;
	
	private businesslayer.QLNhanVien acc = new businesslayer.QLNhanVien();
	
	public static String users;
	public  GUIDangNhap() {
		setTitle("Chương trình Quản Lý Siêu Thị");
		setSize(540,306);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel pNorth;
		add(pNorth = new JPanel(),BorderLayout.NORTH);
		pNorth.setBorder(BorderFactory.createLineBorder(Color.red));
		pNorth.add(lbllogOn =new JLabel("Đăng Nhập"));
		lbllogOn.setBackground(Color.red);
		JPanel pTrai;
		add(pTrai = new JPanel(),BorderLayout.WEST);
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("database//avt.png"));
			BufferedImage resized = resize(myPicture, 200, 200);
			JLabel picLabel = new JLabel(new ImageIcon(resized));
			pTrai.add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pTrai.setBorder(BorderFactory.createLineBorder(Color.red));
		Box bp =Box.createVerticalBox();
		add(bp,BorderLayout.CENTER);
		bp.add(Box.createVerticalStrut(70));
		bp.setBorder(BorderFactory.createLineBorder(Color.red));
		Box b1= Box.createHorizontalBox();
		bp.add(b1);
		b1.add(Box.createHorizontalStrut(10));
		b1.add(new JLabel("Tài Khoản"));
		b1.add(Box.createHorizontalStrut(10));
		b1.add(txtuserName = new JTextField());
		b1.add(Box.createHorizontalStrut(50));
		
		bp.add(Box.createVerticalStrut(10));
		
		Box b2= Box.createHorizontalBox();
		bp.add(b2);
		b2.add(Box.createHorizontalStrut(10));
		b2.add(new JLabel("Mật Khẩu"));
		b2.add(Box.createHorizontalStrut(13));
		b2.add(txtpassWord = new JPasswordField());
		b2.add(Box.createHorizontalStrut(50));
		bp.add(Box.createVerticalStrut(100));
		
		Box bd = Box.createHorizontalBox();
		add(bd,BorderLayout.SOUTH);
		bd.add(Box.createHorizontalStrut(100));
		bd.setBorder(BorderFactory.createLineBorder(Color.red));
		bd.add(btnlogOn = new JButton("Đăng Nhập",new ImageIcon(".\\database\\login.png")));
		btnlogOn.setMaximumSize(getPreferredSize());
		bd.add(Box.createHorizontalStrut(10));
		bd.add(btnExit = new JButton("Thoát",new ImageIcon(".\\database\\logout.png")));
		btnExit.setMaximumSize(getPreferredSize());
		bd.add(Box.createHorizontalStrut(100));
			
	
		btnlogOn.addActionListener(this);
		btnExit.addActionListener(this);
		txtpassWord.addKeyListener(this);
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnExit))
			System.exit(0);
		if(o.equals(btnlogOn))
			Logon();
	}
	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
    		new GUIDangNhap();
        }catch (Exception e) {
        	e.printStackTrace();
		}
	}
	public void Logon () {
		String ID = txtuserName.getText().trim();
		String PW = new String(txtpassWord.getPassword());
		if(ID.isEmpty() || PW.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Bạn chưa nhập đầy đủ thông tin!!", "Thông báo", 1);
		}
		else {
			NhanVien log = acc.login(ID, PW);

			if(log == null) {
				JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại!!", "Thông báo", 1);  
			}
			else {
				if(log.isAdmin()) {
					GUIAdmin jad = new GUIAdmin();
					jad.setVisible(true);
				} else {
					GUIBanHang jCl = new GUIBanHang(log);
					jCl.setVisible(true);
				}
				dispose();
			}  
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			Logon();
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}

package com.iflytek.voicecloud.lfasr.demo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.iflytek.cloud.speech.Setting;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.voicecloud.lfasr.demo.util.DrawableUtils;
import com.iflytek.voicecloud.lfasr.demo.util.Version;
import com.iflytek.voicecloud.lfasr.demo.util.ffmpegUtil;

/**
 * MscDemo It's a Sample using MSC SDK, include tts, isr. you can just press
 * button to use it.
 * 
 * @author cyhu 2012-06-14
 */
@SuppressWarnings("serial")
public class MainView extends JFrame implements ActionListener {
	private JPanel mMainJpanel;
	private JPanel mContentPanel;
	private static JFrame mJframe;

	private JButton jbtnRecognize;
	private JButton jbtnGrammar;
	private JButton jbtnSynthesize;

	private JButton jbtnOpenVideoFile;
	private JButton jbtnOpenAudioFile;

	private String filePath = null;
	private String fileName = null;

	public MainView() {
		StringBuffer param = new StringBuffer();
		param.append("appid=" + Version.getAppid());
//		param.append( ","+SpeechConstant.LIB_NAME_32+"=myMscName" );
		SpeechUtility.createUtility(param.toString());
		Setting.setShowLog(false);

		ImageIcon background = new ImageIcon("resource/index_bg.png");
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		int frameWidth = background.getIconWidth();
		int frameHeight = background.getIconHeight();

		setSize(frameWidth, frameHeight);
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*

		ImageIcon imgRecognize = new ImageIcon("resource/btn_recognize.png");
		jbtnRecognize = this.createImageButton(imgRecognize, null);
		jbtnRecognize.setBounds(10, 150, imgRecognize.getIconWidth(), imgRecognize.getIconHeight());
		DrawableUtils.setMouseListener(jbtnRecognize, "resource/btn_recognize");

		ImageIcon imgGrammar = new ImageIcon("resource/btn_grammar.png");
		jbtnGrammar = this.createImageButton(imgGrammar, null);
		jbtnGrammar.setBounds(20, 150, imgGrammar.getIconWidth(), imgGrammar.getIconHeight());
		DrawableUtils.setMouseListener(jbtnGrammar, "resource/btn_grammar");

		// ImageIcon imgUnderstander = new ImageIcon("res/btn_understander.png");

		ImageIcon imgSynthesize = new ImageIcon("resource/btn_synthesize.png");
		jbtnSynthesize = this.createImageButton(imgSynthesize, null);
		jbtnSynthesize.setBounds(30, 150, imgSynthesize.getIconWidth(), imgSynthesize.getIconHeight());
		DrawableUtils.setMouseListener(jbtnSynthesize, "resource/btn_synthesize");
		
		*/

		ImageIcon imgOpenVideoFile = new ImageIcon("resource/btn_OpenVideoFile.png");
		jbtnOpenVideoFile = this.createImageButton(imgOpenVideoFile, "选择视频");
		jbtnOpenVideoFile.setBounds(40, 150, imgOpenVideoFile.getIconWidth(), imgOpenVideoFile.getIconHeight());
		DrawableUtils.setMouseListener(jbtnOpenVideoFile, "resource/btn_OpenVideoFile");

		ImageIcon imgOpenAudioFile = new ImageIcon("resource/btn_OpenVideoFile.png");
		jbtnOpenAudioFile = this.createImageButton(imgOpenAudioFile, "选择音频");
		jbtnOpenAudioFile.setBounds(50, 150, imgOpenAudioFile.getIconWidth(), imgOpenVideoFile.getIconHeight());
		DrawableUtils.setMouseListener(jbtnOpenAudioFile, "resource/btn_OpenVideoFile");

		GridLayout gridlayout = new GridLayout(1, 2);
		gridlayout.setHgap(10);
		mMainJpanel = new JPanel(gridlayout);
		mMainJpanel.setOpaque(false);

		//mMainJpanel.add(jbtnRecognize);
		//mMainJpanel.add(jbtnGrammar);
		//mMainJpanel.add(jbtnSynthesize);
		mMainJpanel.add(jbtnOpenVideoFile);
		mMainJpanel.add(jbtnOpenAudioFile);

		//jbtnRecognize.addActionListener(this);
		//jbtnGrammar.addActionListener(this);
		//jbtnSynthesize.addActionListener(this);
		jbtnOpenVideoFile.addActionListener(this);
		jbtnOpenAudioFile.addActionListener(this);

		mContentPanel = new JPanel(new BorderLayout());
		mContentPanel.setOpaque(false);
		mContentPanel.add(mMainJpanel, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		setContentPane(mContentPanel);
		setVisible(true);
	}

	public static void main(String args[]) {
		mJframe = new MainView();
	}

	public static JFrame getFrame() {
		return mJframe;
	}

	public JButton createImageButton(ImageIcon img, String name) {
		JButton button = new JButton("");
		if (null != name)
			button = new JButton(name);
		button.setIcon(img);
		button.setSize(img.getIconWidth(), img.getIconHeight());
		button.setBackground(null);

		button.setBorder(null);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);

		return button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtnRecognize) {
			mContentPanel.remove(mMainJpanel);
			// mContentPanel.add(new IatSpeechView());
			mContentPanel.revalidate();
			mContentPanel.repaint();
		} else if (e.getSource() == jbtnGrammar) {
			mContentPanel.remove(mMainJpanel);
			// mContentPanel.add(new AsrSpeechView());
			mContentPanel.revalidate();
			mContentPanel.repaint();
		} else if (e.getSource() == jbtnSynthesize) {
			mContentPanel.remove(mMainJpanel);
			// mContentPanel.add(new TtsSpeechView());
			mContentPanel.revalidate();
			mContentPanel.repaint();
		} else if (e.getSource() == jbtnOpenVideoFile) {
			openVideoFile();
		} else {
			openAudioFile();
		}
	}

	public JPanel getMainJpanel() {
		return mMainJpanel;
	}

	public JPanel getContePanel() {
		return mContentPanel;
	}

	public void openVideoFile() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("ffmpeg音频提取源视频(.mp4)", "mp4");
		jfc.setFileFilter(filter);
		jfc.showDialog(new JLabel(), "Select");
		File file = jfc.getSelectedFile();
		if (null != file) {
			if (file.isFile()) {
				String absolutePath = file.getAbsolutePath();
				int pos = absolutePath.lastIndexOf("\\");
				filePath = absolutePath.substring(0, pos + 1);
				fileName = file.getName();
				System.out.println("文件:" + filePath + "===" + fileName);
				System.out.println(file.getPath());
			}

			try {
				String cmd = "ffmpeg -i " + fileName + " -acodec copy -vn output0.m4a";
				ffmpegUtil.executeCMD(cmd, filePath);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void getAudioFromVideo(String file) {

	}

	public void openAudioFile() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("语音转写音频文件(.wav .flac .opus .mp3 .m4a)", "wav",
				"flac", "opus", "mp3", "m4a");
		jfc.setFileFilter(filter);
		jfc.showDialog(new JLabel(), "Select");
		File file = jfc.getSelectedFile();
		if (null != file) {
			if (file.isDirectory()) {
				System.out.println("文件目录:" + file.getAbsolutePath());
			} else if (file.isFile()) {
				System.out.println("文件:" + file.getAbsolutePath());
			}
		}
	}

}
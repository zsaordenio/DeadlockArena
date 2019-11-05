package com.deadlockarena.graphics.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.deadlockarena.Game;
import com.deadlockarena.config.SpringUtils;
import com.deadlockarena.constant.JavaData;
import com.deadlockarena.graphics.AnimationAndSound;
import com.deadlockarena.graphics.buttons.CancelButton;
import com.deadlockarena.graphics.buttons.DeadButton;
import com.deadlockarena.graphics.buttons.PotionButton;
import com.deadlockarena.graphics.buttons.SelectButton;
import com.deadlockarena.graphics.buttons.SkillButton;
import com.deadlockarena.graphics.buttons.SlotButton;
import com.deadlockarena.graphics.panels.PanelCenter;
import com.deadlockarena.graphics.panels.PanelEast;
import com.deadlockarena.graphics.panels.PanelNorth;
import com.deadlockarena.graphics.panels.PanelSouth;
import com.deadlockarena.graphics.panels.PanelWest;
import com.deadlockarena.logic.Coordinate;
import com.deadlockarena.logic.SelectGrid;
import com.deadlockarena.logic.SlotGrid;
import com.deadlockarena.persistence.bootstrap.JpaGetData;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MainFrame extends JFrame {
	private static final long serialVersionUID = -8478413270802946942L;

	private Stack<JButton [ ]> orderList;
	private JTextArea messages;

	private PanelWest panelWest;
	private PanelCenter panelCenter;
	private PanelNorth panelNorth;
	private PanelSouth panelSouth;
	private PanelEast panelEast;

	public MainFrame() {
		super.setTitle("Deadlock Arena");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new BorderLayout());
		super.setPreferredSize(new Dimension(1700, 1000));
		this.orderList = new Stack<>();
		
		this.panelWest = new PanelWest();
		this.panelNorth = new PanelNorth();
		this.panelCenter = new PanelCenter();
		this.panelSouth = new PanelSouth();
		this.panelEast = new PanelEast();
	
		super.add(this.panelWest, BorderLayout.WEST);
		super.add(this.panelNorth, BorderLayout.NORTH);
		super.add(this.panelCenter, BorderLayout.CENTER);
		super.add(this.panelSouth, BorderLayout.SOUTH);

	}
	
    public void switchPanels() {
        this.messages = new JTextArea(30, 25);
        this.messages.setEditable(false);
        this.messages.setLineWrap(true);
        this.messages.setWrapStyleWord(true);
        this.messages.setFont(JavaData.BASIC_FONT);
        this.messages.setBackground(JavaData.DEFAULT_BACKGROUND);
        this.messages.setForeground(Color.white);
        
        this.panelWest.setVisible(false);
        JScrollPane scrollableTextArea = new JScrollPane(messages);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        super.getContentPane().add(scrollableTextArea, BorderLayout.WEST);

        this.panelEast = new PanelEast();
        super.add(panelEast, BorderLayout.EAST);
        //cancel.alterMouseAdapter1_2();
    }


//	public void evenAllListeners() {
//		for (SlotButton currentButton : slotButtons1) {
//			for (int i = 2; i < currentButton.getMouseListeners().length; i++)
//				currentButton.removeMouseListener(currentButton.getMouseListeners() [ i ]);
//			if (currentButton.getChampion() != null) {
//				currentButton.addMouseListener(currentButton.getML2());
//			}
//		}
//		for (SlotButton currentButton : slotButtons2) {
//			for (int i = 2; i < currentButton.getMouseListeners().length; i++)
//				currentButton.removeMouseListener(currentButton.getMouseListeners() [ i ]);
//			if (currentButton.getChampion() != null) {
//				currentButton.addMouseListener(currentButton.getML2());
//			}
//		}
//	}

	public void resetListeners(SlotGrid grid1, SlotGrid grid2) {
		for (int i = 0; i < JavaData.SLOT_ROW_COUNT; i++) {
			for (int j = 0; j < JavaData.SLOT_COL_COUNT; j++) {
				SlotButton slotButton1 = grid1.getJButton(i, j);
				if (slotButton1.getBorder().equals(JavaData.MOVE_BORDER)) {
					if (slotButton1.getChampion() == null) {
						slotButton1.alterMouseAdapter0_3();
					} else {
						slotButton1.alterMouseAdapter0_2();
					}
				} else if (slotButton1.getBorder().equals(JavaData.ATTACK_BORDER)) {
					slotButton1.alterMouseAdapter2_4();
				} else if (slotButton1.getChampion() != null) {
					slotButton1.alterMouseAdapter0_2();
				}
				// ---------------------------------------------------------------
				SlotButton slotButton2 = grid2.getJButton(i, j);
				if (slotButton2.getBorder().equals(JavaData.MOVE_BORDER)) {
					if (slotButton2.getChampion() != null) {
						slotButton2.alterMouseAdapter0_2();
					} else {
						slotButton2.alterMouseAdapter0_3();
					}
				} else if (slotButton2.getBorder().equals(JavaData.ATTACK_BORDER)) {
					slotButton2.alterMouseAdapter2_4();
				} else if (slotButton2.getChampion() != null) {
					slotButton2.alterMouseAdapter0_2();
				}
			}
		}
	}

}

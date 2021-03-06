package com.deadlockarena.graphics;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.deadlockarena.constant.JavaData;
import com.deadlockarena.persistence.entity.Champion;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PotionButton extends JButton {
	private static final long serialVersionUID = 3192411745294200580L;

	private JLabel jl;
	private boolean cp;
	private SlotButton sB;

	public PotionButton(ImageIcon ic, boolean cp, MainFrame mainFrame) {
		super(ic);
		jl = new JLabel();
		jl.setForeground(JavaData.DEFAULT_BACKGROUND);
		jl.setFont(JavaData.BASIC_FONT);
		jl.setOpaque(false);
		jl.setBounds(0, 0, 20, 15);
		this.add(jl);
		this.cp = cp;
		addMouseListener(mL);

		setPreferredSize(new Dimension(JavaData.PIXEL * 3 / 5, JavaData.PIXEL * 3 / 5));
		setLayout(null);
		setEnabled(false);
		setDisabledIcon(ic);
	}

	public void onChampion(SlotButton sB) {
		setEnabled(true);
		this.sB = sB;
		if (cp)
			jl.setText("" + sB.getChampion().getPotionInventory().getHpPotions().size());
		else
			jl.setText("" + sB.getChampion().getPotionInventory().getMpPotions().size());
	}

	public void offchampion() {
		setEnabled(false);
		sB = null;
		jl.setText("");
	}

	private MouseListener mL = new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			if (PotionButton.this.sB == null)
				return;
			Champion c = PotionButton.this.getSB().getChampion();
			int [ ] info = sB.getChampion().drinkPotion(cp);
			if (info == null)
				return;
			if (cp)
				jl.setText("" + c.getPotionInventory().getHpPotions().size());
			else
				jl.setText("" + c.getPotionInventory().getMpPotions().size());

//            mainFrame.getMP().generateMove(mainFrame.getMessages(), mainFrame.getMove());
//            mainFrame.getMP().generateMessage(mainFrame.getMessages(), c, info[0], info[1], cp);
//            mainFrame.setPanelEast(sB, mainFrame.getPlayer());
//            mainFrame.resetListeners();
//            mainFrame.clearAllBorders();
//            mainFrame.evalTurns();
		}
	};
}

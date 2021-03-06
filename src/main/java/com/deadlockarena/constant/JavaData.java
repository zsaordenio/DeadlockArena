package com.deadlockarena.constant;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.springframework.stereotype.Component;

import com.deadlockarena.persistence.entity.Champion;

@Component
public class JavaData {

	public static final String [ ] [ ] CHAMPIONS = { { "Berserker" , "Assassin" , "Bard" } ,
			{ "Dancer" , "Chemist" , "Guardian" } , { "Engineer" , "Cyborg" , "Monk" } ,
			{ "HolyKnight" , "Dragoon" , "Pyromancer" } , { "Knight" , "Reaper" , "Sniper" } ,
			{ "Screamer" , "Trickster" , "SwordMage" } };

	public static final int PIXEL = 96;
	public static final double SCALE_FACTOR = 2 / 3.0;
	public static final double BUTTON_SIZE_SCALE = 4 / 5.0;
	public static final double BASE = PIXEL * SCALE_FACTOR * BUTTON_SIZE_SCALE;
	public static final double [ ] sC1 = { 630 , 120 };
	public static final double [ ] sC2 = { 630 , 445 };
	public static final double [ ] bC = { 125 , 125 };
	public static final double [ ] BerserkerCoord = { bC [ 0 ] + BASE * 0 , bC [ 1 ] + BASE * 0 };
	public static final double [ ] DancerCoord = { bC [ 0 ] + BASE * 0 , bC [ 1 ] + BASE * 1 };
	public static final double [ ] EngineerCoord = { bC [ 0 ] + BASE * 0 , bC [ 1 ] + BASE * 2 };
	public static final double [ ] HolyKnightCoord = { bC [ 0 ] + BASE * 0 , bC [ 1 ] + BASE * 3 };
	public static final double [ ] KnightCoord = { bC [ 0 ] + BASE * 0 , bC [ 1 ] + BASE * 4 };
	public static final double [ ] ScreamerCoord = { bC [ 0 ] + BASE * 0 , bC [ 1 ] + BASE * 5 };
	public static final double [ ] AssassinCoord = { bC [ 0 ] + BASE * 1 , bC [ 1 ] + BASE * 0 };
	public static final double [ ] ChemistCoord = { bC [ 0 ] + BASE * 1 , bC [ 1 ] + BASE * 1 };
	public static final double [ ] CyborgCoord = { bC [ 0 ] + BASE * 1 , bC [ 1 ] + BASE * 2 };
	public static final double [ ] DragoonCoord = { bC [ 0 ] + BASE * 1 , bC [ 1 ] + BASE * 3 };
	public static final double [ ] ReaperCoord = { bC [ 0 ] + BASE * 1 , bC [ 1 ] + BASE * 4 };
	public static final double [ ] TricksterCoord = { bC [ 0 ] + BASE * 1 , bC [ 1 ] + BASE * 5 };
	public static final double [ ] BardCoord = { bC [ 0 ] + BASE * 2 , bC [ 1 ] + BASE * 0 };
	public static final double [ ] GuardianCoord = { bC [ 0 ] + BASE * 2 , bC [ 1 ] + BASE * 1 };
	public static final double [ ] MonkCoord = { bC [ 0 ] + BASE * 2 , bC [ 1 ] + BASE * 2 };
	public static final double [ ] PyromancerCoord = { bC [ 0 ] + BASE * 2 , bC [ 1 ] + BASE * 3 };
	public static final double [ ] SniperCoord = { bC [ 0 ] + BASE * 2 , bC [ 1 ] + BASE * 4 };
	public static final double [ ] SwordMageCoord = { bC [ 0 ] + BASE * 2 , bC [ 1 ] + BASE * 5 };

	public static final double [ ] [ ] coords = { BerserkerCoord ,
			{ sC1 [ 0 ] + BASE * 0 , sC1 [ 1 ] + BASE * 0 } , CyborgCoord ,
			{ sC1 [ 0 ] + BASE * 0 , sC1 [ 1 ] + BASE * 1 } , AssassinCoord ,
			{ sC1 [ 0 ] + BASE * 0 , sC1 [ 1 ] + BASE * 2 } , TricksterCoord ,
			{ sC1 [ 0 ] + BASE * 1 , sC1 [ 1 ] + BASE * 1 } , MonkCoord ,
			{ sC1 [ 0 ] + BASE * 1 , sC1 [ 1 ] + BASE * 2 } , BardCoord ,
			{ sC1 [ 0 ] + BASE * 1 , sC1 [ 1 ] + BASE * 3 } , SwordMageCoord ,
			{ sC1 [ 0 ] + BASE * 2 , sC1 [ 1 ] + BASE * 0 } , ChemistCoord ,
			{ sC1 [ 0 ] + BASE * 2 , sC1 [ 1 ] + BASE * 1 } , SniperCoord ,
			{ sC1 [ 0 ] + BASE * 2 , sC1 [ 1 ] + BASE * 2 } , KnightCoord ,
			{ sC2 [ 0 ] + BASE * 3 , sC2 [ 1 ] + BASE * 3 } , ScreamerCoord ,
			{ sC2 [ 0 ] + BASE * 3 , sC2 [ 1 ] + BASE * 1 } , PyromancerCoord ,
			{ sC2 [ 0 ] + BASE * 0 , sC2 [ 1 ] + BASE * 0 } , DancerCoord ,
			{ sC2 [ 0 ] + BASE * 1 , sC2 [ 1 ] + BASE * 2 } , EngineerCoord ,
			{ sC2 [ 0 ] + BASE * 3 , sC2 [ 1 ] + BASE * 2 } , DragoonCoord ,
			{ sC2 [ 0 ] + BASE * 2 , sC2 [ 1 ] + BASE * 3 } , GuardianCoord ,
			{ sC2 [ 0 ] + BASE * 0 , sC2 [ 1 ] + BASE * 1 } , HolyKnightCoord ,
			{ sC2 [ 0 ] + BASE * 1 , sC2 [ 1 ] + BASE * 0 } , ReaperCoord ,
			{ sC2 [ 0 ] + BASE * 2 , sC2 [ 1 ] + BASE * 0 } , };

	public static final int CAP_TURN = 4;

	public static final int SELECT_ROW_COUNT = 6;
	public static final int SELECT_COL_COUNT = 3;
	public static final int SELECT_COUNT = SELECT_ROW_COUNT * SELECT_COL_COUNT;

	public static final int SLOT_ROW_COUNT = 4;
	public static final int SLOT_COL_COUNT = 5;
	public static final int SLOT_COUNT = SLOT_ROW_COUNT * SLOT_COL_COUNT;

	public static final Random RANDOM = new Random();

	public static final LineBorder MOVE_BORDER = new LineBorder(Color.blue, 5);
	public static final LineBorder ATTACK_BORDER = new LineBorder(Color.red, 5);
	public static final Border DEFAULT_BORDER = UIManager.getBorder("Button.border");

	public static final Color DEFAULT_BACKGROUND = Color.black;
	public static final Color DEFAULT_BUTTON_BACKGROUND = new JButton().getBackground();

	public static final Font BASIC_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	public static final Font CHAMPION_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 40);
	public static final Font SELECT_BUTTON_CHAMPION_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 12);
	public static final Font PANEL_EAST_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 18);

	public static final String PICS_PATH = "/pics/";

	public static final String STATUS_STRING = "<html>" + "HP: ? / ?<br/>" + "MP: ? / ?<br/>"
			+ "Damage: ? - ?<br/>" + "Defense: ?<br/>" + "Critical: ?<br/>" + "Dodge : ?"
			+ "</html>";

//	public static final String DEFAULT_STATUS_STRING = "<html>" + "HP: ?<br/>" + "MP: ?<br/>"
//			+ "Damage: ? - ?<br/>" + "Defense: ?<br/>" + "Critical: ?<br/>" + "Dodge : ?"
//			+ "</html>";

	public static String getStatsText(Champion champion) {
		return "<html>" + "<font color=" + champion.evalColor() + ">HP: " + champion.getCurrentHp()
				+ " / " + champion.getMaxHp() + "</font><br/>" + "MP: " + champion.getMaxMp()
				+ " / " + champion.getMaxMp() + "<br/>" + "Damage: " + champion.getMinDmg() + " - "
				+ champion.getMaxDmg() + "<br/>" + "Defense: " + champion.getDefense() + "<br/>"
				+ "Critical: " + champion.getCritical() + "%<br/>" + "Dodge: " + champion.getDodge()
				+ "%" + "</html>";
	}

}

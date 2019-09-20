package com.deadlockarena.constant;

import java.awt.*;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.deadlockarena.backend.persistence.domain.entity.Champion;
import com.deadlockarena.backend.persistence.domain.item.PotionInventory;
import com.deadlockarena.exception.RemainderException;
import static org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword;

public class JavaData {

	public static final String [ ] HEROES = { "Berserker" , "Dancer" , "Engineer" , "HolyKnight" ,
			"Knight" , "Screamer" , "Assassin" , "Chemist" , "Cyborg" , "Dragoon" , "Reaper" ,
			"Trickster" , "Bard" , "Guardian" , "Monk" , "Pyromancer" , "Sniper" , "SwordMage" };

	public static final int PIXEL = 96;
	public static final double SCALEFACTOR = 2 / 3.0;
	public static final double BUTTONSIZESCALE = 4 / 5.0;
	public static final double BASE = PIXEL * SCALEFACTOR * BUTTONSIZESCALE;
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
			{ sC1 [ 0 ] + BASE * 2 , sC1 [ 1 ] + BASE * 2 } ,

			KnightCoord , { sC2 [ 0 ] + BASE * 3 , sC2 [ 1 ] + BASE * 3 } , ScreamerCoord ,
			{ sC2 [ 0 ] + BASE * 3 , sC2 [ 1 ] + BASE * 1 } , PyromancerCoord ,
			{ sC2 [ 0 ] + BASE * 0 , sC2 [ 1 ] + BASE * 0 } , DancerCoord ,
			{ sC2 [ 0 ] + BASE * 1 , sC2 [ 1 ] + BASE * 2 } , EngineerCoord ,
			{ sC2 [ 0 ] + BASE * 3 , sC2 [ 1 ] + BASE * 2 } , DragoonCoord ,
			{ sC2 [ 0 ] + BASE * 2 , sC2 [ 1 ] + BASE * 3 } , GuardianCoord ,
			{ sC2 [ 0 ] + BASE * 0 , sC2 [ 1 ] + BASE * 1 } , HolyKnightCoord ,
			{ sC2 [ 0 ] + BASE * 1 , sC2 [ 1 ] + BASE * 0 } , ReaperCoord ,
			{ sC2 [ 0 ] + BASE * 2 , sC2 [ 1 ] + BASE * 0 } , };

	public static final int CAPTURN = 4;
	public static final int HEROESCOUNT = 18;
	public static final int SLOTSCOUNT = 20;

	public static FileInputStream file;
	public static HSSFWorkbook workbook;
	public static HSSFSheet staticData;
	public static HSSFSheet dynamicData;

	public static void initData() {
		try {
			setCurrentUserPassword(ENCRYPTIONPASSWORD);
			file = new FileInputStream("staticData.xls");
			workbook = new HSSFWorkbook(file);
			staticData = workbook.getSheet("staticData");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}



	public static Champion evalHero(String heroString) throws RemainderException {
		PotionInventory pI = new PotionInventory(new ArrayList<>(), new ArrayList<>());
		EntityManager em = null;
		switch (heroString) {
		case "Berserker":

//			return new Berserker((int) Double.parseDouble(staticData.getRow(1).getCell(1).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(1).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(1).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(1).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(1).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(1).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(1).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(1).toString()),
//					staticData.getRow(10).getCell(1).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(1).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(1).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(1).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(1).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(1).toString()));
		case "Dancer":
//			return new Dancer((int) Double.parseDouble(staticData.getRow(1).getCell(2).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(2).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(2).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(2).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(2).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(2).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(2).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(2).toString()),
//					staticData.getRow(10).getCell(2).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(2).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(2).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(2).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(2).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(2).toString()));
		case "Engineer":
//			return new Engineer((int) Double.parseDouble(staticData.getRow(1).getCell(3).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(3).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(3).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(3).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(3).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(3).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(3).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(3).toString()),
//					staticData.getRow(10).getCell(3).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(3).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(3).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(3).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(3).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(3).toString()));
		case "HolyKnight":
//			return new HolyKnight((int) Double.parseDouble(staticData.getRow(1).getCell(4).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(4).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(4).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(4).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(4).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(4).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(4).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(4).toString()),
//					staticData.getRow(10).getCell(4).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(4).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(4).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(4).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(4).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(4).toString()));
		case "Knight":
//			return new Knight((int) Double.parseDouble(staticData.getRow(1).getCell(5).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(5).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(5).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(5).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(5).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(5).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(5).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(5).toString()),
//					staticData.getRow(10).getCell(5).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(5).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(5).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(5).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(5).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(5).toString()));
		case "Screamer":
//			return new Screamer((int) Double.parseDouble(staticData.getRow(1).getCell(6).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(6).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(6).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(6).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(6).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(6).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(6).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(6).toString()),
//					staticData.getRow(10).getCell(6).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(6).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(6).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(6).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(6).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(6).toString()));
		case "Assassin":
//			return new Assassin((int) Double.parseDouble(staticData.getRow(1).getCell(7).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(7).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(7).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(7).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(7).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(7).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(7).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(7).toString()),
//					staticData.getRow(10).getCell(7).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(7).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(7).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(7).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(7).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(7).toString()));
		case "Chemist":
//			return new Chemist((int) Double.parseDouble(staticData.getRow(1).getCell(8).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(8).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(8).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(8).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(8).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(8).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(8).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(8).toString()),
//					staticData.getRow(10).getCell(8).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(8).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(8).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(8).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(8).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(8).toString()));
		case "Cyborg":
//			return new Cyborg((int) Double.parseDouble(staticData.getRow(1).getCell(9).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(9).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(9).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(9).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(9).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(9).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(9).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(9).toString()),
//					staticData.getRow(10).getCell(9).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(9).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(9).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(9).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(9).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(9).toString()));
		case "Dragoon":
//			return new Dragoon((int) Double.parseDouble(staticData.getRow(1).getCell(10).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(10).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(10).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(10).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(10).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(10).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(10).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(10).toString()),
//					staticData.getRow(10).getCell(10).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(10).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(10).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(10).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(10).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(10).toString()));
		case "Reaper":
//			return new Reaper((int) Double.parseDouble(staticData.getRow(1).getCell(11).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(11).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(11).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(11).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(11).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(11).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(11).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(11).toString()),
//					staticData.getRow(10).getCell(11).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(11).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(11).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(11).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(11).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(11).toString()));
		case "Trickster":
//			return new Trickster((int) Double.parseDouble(staticData.getRow(1).getCell(12).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(12).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(12).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(12).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(12).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(12).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(12).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(12).toString()),
//					staticData.getRow(10).getCell(12).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(12).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(12).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(12).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(12).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(12).toString()));
		case "Bard":
//			return new Bard((int) Double.parseDouble(staticData.getRow(1).getCell(13).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(13).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(13).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(13).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(13).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(13).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(13).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(13).toString()),
//					staticData.getRow(10).getCell(13).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(13).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(13).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(13).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(13).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(13).toString()));
		case "Guardian":
//			return new Guardian((int) Double.parseDouble(staticData.getRow(1).getCell(14).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(14).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(14).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(14).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(14).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(14).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(14).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(14).toString()),
//					staticData.getRow(10).getCell(14).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(14).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(14).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(14).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(14).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(14).toString()));
		case "Monk":
//			return new Monk((int) Double.parseDouble(staticData.getRow(1).getCell(15).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(15).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(15).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(15).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(15).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(15).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(15).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(15).toString()),
//					staticData.getRow(10).getCell(15).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(15).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(15).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(15).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(15).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(15).toString()));
		case "Pyromancer":
//			return new Pyromancer((int) Double.parseDouble(staticData.getRow(1).getCell(16).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(16).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(16).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(16).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(16).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(16).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(16).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(16).toString()),
//					staticData.getRow(10).getCell(16).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(16).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(16).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(16).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(16).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(16).toString()));
		case "Sniper":
//			return new Sniper((int) Double.parseDouble(staticData.getRow(1).getCell(17).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(17).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(17).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(17).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(17).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(17).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(17).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(17).toString()),
//					staticData.getRow(10).getCell(17).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(17).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(17).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(17).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(17).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(17).toString()));
		case "SwordMage":
//			return new SwordMage((int) Double.parseDouble(staticData.getRow(1).getCell(18).toString()),
//					(int) Double.parseDouble(staticData.getRow(2).getCell(18).toString()),
//					(int) Double.parseDouble(staticData.getRow(3).getCell(18).toString()),
//					(int) Double.parseDouble(staticData.getRow(4).getCell(18).toString()),
//					(int) Double.parseDouble(staticData.getRow(5).getCell(18).toString()),
//					(int) Double.parseDouble(staticData.getRow(6).getCell(18).toString()),
//					(int) Double.parseDouble(staticData.getRow(7).getCell(18).toString()),
//					(int) Double.parseDouble(staticData.getRow(8).getCell(18).toString()),
//					staticData.getRow(10).getCell(18).toString(), pI,
//					(int) Double.parseDouble(staticData.getRow(12).getCell(18).toString()),
//					(int) Double.parseDouble(staticData.getRow(16).getCell(18).toString()),
//					(int) Double.parseDouble(staticData.getRow(20).getCell(18).toString()),
//					(int) Double.parseDouble(staticData.getRow(24).getCell(18).toString()),
//					(int) Double.parseDouble(staticData.getRow(28).getCell(18).toString()));
		default:
			throw new RemainderException("Data: Incorrect heroString");
		}
	}

	public static final String ENCRYPTIONPASSWORD = "egbert";

	public static final LineBorder MOVEBORDER = new LineBorder(Color.blue, 5);
	public static final LineBorder ATTACKBORDER = new LineBorder(Color.red, 5);
	public static final Border DEFAULTBORDER = UIManager.getBorder("Button.border");
	public static final Color DEFAULTBACKGROUND = Color.black;
	public static final Color DEFAULTBUTTONBACKGROUND = new JButton().getBackground();

	public static final Font BASICFONT = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	public static final Font HEROFONT = new Font(Font.SANS_SERIF, Font.BOLD, 40);
	public static final Font PANELEASTFONT = new Font(Font.SANS_SERIF, Font.BOLD, 18);

	public static final String STATUSSTRING = "<html>" + "HP: ? / ?<br/>" + "MP: ? / ?<br/>"
			+ "Damage: ? - ?<br/>" + "Defense: ?<br/>" + "Critical: ?<br/>" + "Dodge : ?"
			+ "</html>";

	public static final String DEFAULTSTATUSSTRING = "<html>" + "HP: ?<br/>" + "MP: ?<br/>"
			+ "Damage: ? - ?<br/>" + "Defense: ?<br/>" + "Critical: ?<br/>" + "Dodge : ?"
			+ "</html>";

}

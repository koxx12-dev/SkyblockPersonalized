package com.cobble.sbp.core.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.cobble.sbp.SBP;
import com.cobble.sbp.core.ConfigList;
import com.cobble.sbp.events.RenderGuiEvent;
import com.cobble.sbp.gui.menu.settings.SettingMenu;
import com.cobble.sbp.gui.screen.PuzzleImage;
import com.cobble.sbp.gui.screen.dwarven.DwarvenGui;
import com.cobble.sbp.gui.screen.dwarven.DwarvenPickaxeTimer;
import com.cobble.sbp.gui.screen.dwarven.DwarvenTimer;
import com.cobble.sbp.simplejson.JSONObject;
import com.cobble.sbp.utils.Reference;
import com.cobble.sbp.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;



public class ConfigHandler {
	
	public static Object configObj = null;
	
	public static JSONObject obj = new JSONObject();
	public static JSONObject obj2 = new JSONObject();
	public static Boolean firstLaunch = false;
	
	public static void registerConfig() throws Exception {
		firstLaunch = Utils.invertBoolean(Utils.fileTest("config/"+Reference.MODID+"/main.cfg"));
		if(firstLaunch || Utils.readFile("config/"+Reference.MODID+"/main.cfg").equals(" ") || Utils.readFile("config/"+Reference.MODID+"/main.cfg").equals("")) {
			firstLaunch();
		}
		ConfigList.loadConfig();
		if(firstLaunch) {
			ConfigHandler.resetConfig();
		}
		
	}
	
	private static void firstLaunch() throws IOException {
		SBP.firstLaunch=true;
		File configFolder = new File("config/"+Reference.MODID); configFolder.mkdirs();
		File config = new File("config/"+Reference.MODID+"/main.cfg");
		//File quests = new File("config/"+Reference.MODID+"/quests.cfg");
		try { if (config.createNewFile()) {Utils.print("The config file 'main' for "+Reference.NAME+" has been created at: "+config.getAbsolutePath());}} 
		catch (IOException e) {System.out.println("An error occurred.");}


		
		
	}
	
	public static void newObject(String varName, Object var) {
		//obj.put(varName, var);
		try {
	        FileWriter file = new FileWriter("config/"+Reference.MODID+"/main.cfg");
	        obj.put(varName, var);
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(obj.toJSONString());
            String prettyJsonString = gson.toJson(je);
            file.write(prettyJsonString);
            file.flush();
            file.close();
            configObj=obj;
            
	      } catch (IOException e) {
	        System.out.println("An error occurred from newObject().");
	        e.printStackTrace();
	      }
	}
	/*
	public static void setQuest(String varName, Boolean var) {
		//obj2.put(varName, var);
		try {
	        FileWriter file = new FileWriter("config/"+Reference.MODID+"/quests.cfg");
	        obj2.put(varName, var);
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(obj2.toJSONString());
            String prettyJsonString = gson.toJson(je);
            
            file.write(prettyJsonString);
            file.flush();
            file.close();
            
	      } catch (IOException e) {
	        System.out.println("An error occurred from setAchieve().");
	        e.printStackTrace();
	      }
	}*/
	
	public static Object getDefaultValue(String id) {
		Object output = "null";
		
		for(int i=0;i<ConfigList.d.size();i++) {
			if(id.equals(ConfigList.c.get(i))) {
				output = ConfigList.d.get(i);
			}
		}
		
		return output;
	}
	
	public static void resetConfig() {
		for(int i=0;i<ConfigList.c.size();i++) {
			if(!ConfigList.c.get(i).equals("modLaunches")) {
				ConfigHandler.newObject(ConfigList.c.get(i), ConfigList.d.get(i));
			}
			
			
		}
		DataGetter.updateConfig("main");
	}
	
	public static void updateConfig(String c) {
		try {
			//DwarvenTimer.dwarvenTimerToggle = DataGetter.findBool("dwarvenTimerToggle");
			DwarvenGui.commTrackToggle = DataGetter.findBool("dwarvenTrackToggle");
			DwarvenGui.fuelToggle =  DataGetter.findBool("dwarvenFuelToggle");
			SettingMenu.modLaunchToggle = DataGetter.findBool("modLaunchToggle");
			DwarvenPickaxeTimer.pickTimerToggle = DataGetter.findBool("pickReminderToggle");
			DwarvenGui.mithrilToggle = DataGetter.findBool("dwarvenMithrilDisplay");
			DwarvenTimer.dwarvenTimerToggle = DataGetter.findBool("dwarvenTimerToggle");
			
			
			
		if(c.equals(""));
		
		else if(c.equals("boxSolverToggle") || c.equals("iceSolverToggle")) {
			PuzzleImage.xCoord = DataGetter.findInt("puzzleX"); 
			PuzzleImage.yCoord = DataGetter.findInt("puzzleY"); 
			PuzzleImage.puzzleColor = DataGetter.findStr("puzzleColor")+"";
			RenderGuiEvent.puzzleScale=126*(DataGetter.findInt("puzzleScale")/10);
			
		}
		
	
		//DWARVEN TIMER
		else if(DwarvenTimer.dwarvenTimerToggle && c.equals("dwarvenTimerToggle")) {
			DwarvenTimer.dwarvenTimerDing = DataGetter.findBool("dwarvenTimerDing");
			DwarvenTimer.textColorID = DataGetter.findInt("dwarvenTimerTextColor");
			DwarvenTimer.posX = DataGetter.findInt("dwarvenTimerX");
			DwarvenTimer.posY = DataGetter.findInt("dwarvenTimerY");
		}
		
		else if(DwarvenGui.commTrackToggle && c.equals("dwarvenTrackToggle")) {
			DwarvenGui.commissionID = DataGetter.findInt("dwarvenTrackCommissionColor");
			DwarvenGui.commNameID = DataGetter.findInt("dwarvenTrackQuestName");
			DwarvenGui.commTrackBarToggle = DataGetter.findBool("dwarvenTrackBarToggle");
			DwarvenGui.posX = DataGetter.findInt("dwarvenGuiX");
			DwarvenGui.posY = DataGetter.findInt("dwarvenGuiY");
			DwarvenGui.commBorderColorID = DataGetter.findInt("dwarvenTrackBorderColor");
			DwarvenGui.commYesColorID = DataGetter.findInt("dwarvenTrackYesColor");
			DwarvenGui.commNoColorID = DataGetter.findInt("dwarvenTrackNoColor");
		}
		
		else if(DwarvenGui.fuelToggle && c.equals("dwarvenFuelToggle")) {
			DwarvenGui.posX = DataGetter.findInt("dwarvenGuiX");
			DwarvenGui.posY = DataGetter.findInt("dwarvenGuiY");
			DwarvenGui.fuelGui = DataGetter.findBool("dwarvenFuelGui");
			DwarvenGui.fuelDurr = DataGetter.findBool("dwarvenFuelDurr");
			
			DwarvenGui.fuelGuiDrillFuel = DataGetter.findInt("dwarvenFuelDrillColor");
			DwarvenGui.fuelGuiPrimaryFull = DataGetter.findInt("dwarvenFuelGuiPrimeFullColor");
			DwarvenGui.fuelGuiSecondaryFull = DataGetter.findInt("dwarvenFuelGuiSecondFullColor");
			DwarvenGui.fuelGuiPrimaryHalf = DataGetter.findInt("dwarvenFuelGuiPrimeHalfColor");
			DwarvenGui.fuelGuiSecondaryHalf = DataGetter.findInt("dwarvenFuelGuiSecondHalfColor");
			DwarvenGui.fuelGuiPrimaryTen = DataGetter.findInt("dwarvenFuelGuiPrimeTenColor");
			DwarvenGui.fuelGuiSecondaryTen = DataGetter.findInt("dwarvenFuelGuiSecondTenColor");
			
		} else if(DwarvenGui.mithrilToggle && c.equals("dwarvenMithrilDisplay")) {
			DwarvenGui.posX = DataGetter.findInt("dwarvenGuiX");
			DwarvenGui.posY = DataGetter.findInt("dwarvenGuiY");
			DwarvenGui.mithrilTextColor = DataGetter.findInt("dwarvenMithrilTextColor");
			DwarvenGui.mithrilCountColor = DataGetter.findInt("dwarvenMithrilCountColor");
		}
		
		else if(DwarvenPickaxeTimer.pickTimerToggle && c.equals("pickReminderToggle")) {
			DwarvenPickaxeTimer.pickTimerX = DataGetter.findInt("pickTimerX");
			DwarvenPickaxeTimer.pickTimerY = DataGetter.findInt("pickTimerY");		
			DwarvenPickaxeTimer.HotMLevel = DataGetter.findInt("dwarvenHOTMLevel");
			DwarvenPickaxeTimer.pickTimerColorID = DataGetter.findInt("pickTimerTextColor");
			DwarvenPickaxeTimer.pickActiveTimerColorID = DataGetter.findInt("pickActiveTimerTextColor");
			DwarvenPickaxeTimer.onlyWhenHolding = DataGetter.findBool("pickTimerHolding");
			DwarvenPickaxeTimer.pickTimerGui = DataGetter.findBool("pickTimerGui");
			DwarvenPickaxeTimer.circle = DataGetter.findBool("pickTimerCircle");
			DwarvenPickaxeTimer.circleRadius = DataGetter.findInt("pickTimerCircleRadius");
			DwarvenPickaxeTimer.circleAccuracy = DataGetter.findInt("pickTimerCircleAcc");
			DwarvenPickaxeTimer.circleActiveColor = DataGetter.findStr("pickTimerCircleActive");
			DwarvenPickaxeTimer.circleCdColor = DataGetter.findStr("pickTimerCircleCd");
			DwarvenPickaxeTimer.circleReadyColor = DataGetter.findStr("pickTimerCircleReady");
		}
		
		
		} catch(Exception e) {
			Utils.print("Failed to update "+Reference.MODID+" config settings");
		}
	}
	
	
	
	
	
	
}

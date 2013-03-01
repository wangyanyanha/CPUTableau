package com.ogp.cputableau;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class StateMachine 
{
	private static final String 			PERSISTANT_STORAGE 		= "T2TB";
	private static final String 			USE_OVERLAY 			= "UseOverlay";
	private static final String 			ACCEL_LIMIT			 	= "AccelLimit";
	private static final String 			EXTENSIVE_DEBUG			= "ExtensiveDebug";
	private static final String 			USE_NOTIFY				= "UseNotify";
	
	private static Context					appContext;
	
	private static float					accelLimit;
	private static boolean 					extensiveDebug;
	private static boolean 					useNotify;
	private static boolean 					useOverlay;
	
	
	private StateMachine()
	{
	}
	

	public static void init (Context		context)
	{
		appContext = context.getApplicationContext();
		
		
// Defaults
		accelLimit 					= 9.8f;				// ~1g
		useOverlay					= true;
		extensiveDebug				= false;
		useNotify					= false;
		
		readFromPersistantStorage();
	}
	
	
	public static void readFromPersistantStorage() 
	{
		SharedPreferences pref = appContext.getSharedPreferences (PERSISTANT_STORAGE, 
				  												  Context.MODE_PRIVATE);
		
		useOverlay		= pref.getBoolean 	(USE_OVERLAY, 		useOverlay);
		accelLimit 		= pref.getFloat 	(ACCEL_LIMIT, 		accelLimit);
		extensiveDebug 	= pref.getBoolean 	(EXTENSIVE_DEBUG, 	extensiveDebug);
		useNotify	 	= pref.getBoolean 	(USE_NOTIFY, 		useNotify);
	}

	
	public static void writeToPersistantStorage() 
	{
		SharedPreferences pref = appContext.getSharedPreferences (PERSISTANT_STORAGE, 
																  Context.MODE_PRIVATE);
		
		Editor editor = pref.edit();
		
		editor.putBoolean	(USE_OVERLAY, 			useOverlay);
		editor.putFloat		(ACCEL_LIMIT, 			accelLimit);
		editor.putBoolean	(EXTENSIVE_DEBUG, 		extensiveDebug);
		editor.putBoolean	(USE_NOTIFY, 			useNotify);
		
		editor.commit();
	}

	
	public static float 	getAccelLimit			()				{return accelLimit;}
	public static void  	setAccelLimit 			(float value)	{accelLimit = value;}


	public static boolean 	getExtensiveDebug		()				{return extensiveDebug;}
	public static void 		setExtensiveDebug		(boolean value)	{extensiveDebug = value;}

	public static boolean	getUseNotify			() 				{return useNotify;}
	public static void 		setUseNotify			(boolean value) {useNotify = value;}

	public static boolean 	getOverlay				() 				{return useOverlay;}
	public static void		setOverlay				(boolean value)	{useOverlay = value;}
}
package business;

public abstract class C {
	
	public static int TILE_WIDTH = 16;
	public static int TILE_HEIGHT = 16;
	public static int SCREEN_WIDTH = 800;
	public static int SCREEN_HEIGHT = 600;
	public static int FPS = 30;
	public static int STEPWIDTH = 2;
	public static int JUMP_HEIGHT = 80;
	
	
	
	public static String IMAGES_PATH = "ressources/images/";
	public static String SOUNDS_PATH = "ressources/sounds/";
	
	public static String PASS_MAP = "ressources/maps/pass_map.png";
	public static String IMG_MAP = "ressources/maps/map.png";
	
	public static String SOUND_WIND = "144083__bosk1__wind-houling-1.wav";
	public static String SOUND_DOORCLOSING = "104529__skyumori__door-close-sqeuak-03.wav";
	public static String SOUND_COSMICD = "133008__cosmicd__annulet-of-absorption.wav";
	public static String SOUND_METALPINGS = "135784__timbre__echoing-metal-pings.flac";
	
	
	public static int ANIMATIONTEMPO = 8;
	
	/* OFFSET FOR COLLISION BOX */
	public static int XOFFSET_LEFT = 3;
	public static int XOFFSET_RIGHT = 3;
	public static int YOFFSET_TOP = 0;
	public static int YOFFSET_BOTTOM = 7;
	
	/* PHYSICS */

	public static float FORCE_TOPDOWN = 3000f;
	public static float FORCE_RIGHTLEFT = 2000f;
	public static float FORCE_GRAVITY = 1000f;
	public static float PLAYERMASS = 1f;
}

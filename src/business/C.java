package business;

public abstract class C {
	
	public static int TILE_WIDTH = 16;
	public static int TILE_HEIGHT = 16;
	public static int SCREEN_WIDTH = 640;
	public static int SCREEN_HEIGHT = 480;

	public static int FPS = 30;
	public static int STEPWIDTH = 2;
	public static int JUMP_HEIGHT = 80;
	public static int ANIMATIONTEMPO = 8;
	
	
	/* PATHS AND MAPS */
	public static String IMAGES_PATH = "ressources/images/";
	public static String SOUNDS_PATH = "ressources/sounds/";
	
	public static String PASS_MAP = "ressources/maps/pass_map.png";
	public static String IMG_MAP = "ressources/maps/map.png";
	public static String ALPHA_MAP = "ressources/maps/alpha_map.png";
	
	/* SOUNDS */
	public static String SOUND_XML = "ressources/data/sounds.xml";
	
	public static String SOUND_BOSK1 = SOUNDS_PATH+"144083__bosk1__wind-houling-1.wav";
	public static String SOUND_SKYUMORI = SOUNDS_PATH+"104529__skyumori__door-close-sqeuak-03.wav";
	public static String SOUND_COSMICD = SOUNDS_PATH+"133008__cosmicd__annulet-of-absorption.wav";
	public static String SOUND_CASTLEOFSAMPLES = SOUNDS_PATH+"145394__castleofsamples__rushing-river-in-the-woods.wav";
	public static String SOUND_KLANKBEELD = SOUNDS_PATH+"172307__klankbeeld__room-tone-windy-house-121220-00.wav";
	public static String SOUND_JIMIMOD = SOUNDS_PATH+"203497__jimimod__cricket-phasing-loop.wav";
	public static String SOUND_DOBROIDE = SOUNDS_PATH+"205966__kangaroovindaloo__medium-wind.wav";
	
	public static float SOUND_EFFECTS = 0.9f;
	public static float SOUND_WIND = 0.3f;
	public static float SOUND_MUSIC = 0.0f;
	public static int SOUND_FADETIME = 200;
	public static int MAXDISTANCE = 80;
	
	
	
	/* OFFSET FOR COLLISION BOX */
	public static int XOFFSET_LEFT = 3;
	public static int XOFFSET_RIGHT = 3;
	public static int YOFFSET_TOP = 0;
	public static int YOFFSET_BOTTOM = 7;
	
	/* PHYSICS */
	public static float FORCE_TOPDOWN = 225f;
	public static float FORCE_RIGHTLEFT = 20f;
	public static float FORCE_GRAVITY = 1000f;
	public static float PLAYERMASS = 0.01f;
}

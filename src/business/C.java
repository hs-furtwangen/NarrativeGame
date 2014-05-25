package business;


public abstract class C {
	
	
	public static int TILE_WIDTH = 16;
	public static int TILE_HEIGHT = 16;
	public static int SCREEN_WIDTH = 320;
	public static int SCREEN_HEIGHT = 240;

	public static int FPS = 30;
	public static int STEPWIDTH = 2;
	public static int ANIMATIONTEMPO = 8;
	public static int ZOOMLEVEL = 2;
	
	
	/* PATHS AND MAPS */
	public static String IMAGES_PATH = "ressources/images/";
	public static String SOUNDS_PATH = "ressources/sounds/";
	public static String SCENE_PATH = "ressources/scenes/";
	
	public static String PASS_MAP = "ressources/maps/pass_map.png";
	public static String IMG_MAP = "ressources/maps/map.png";
	public static String ALPHA_MAP = "ressources/maps/alpha_map.png";
	
	/* SOUNDS */
	public static String SOUND_XML = "../ressources/data/sounds.xml";
	
	public static String SOUND_WINDWAV = SOUNDS_PATH+"wind.wav";
	public static String SOUND_JUMP = SOUNDS_PATH+"jump.wav"; // not in properties
	public static String SOUND_BLINK = SOUNDS_PATH+"blink.wav"; // not in properties
	public static String SOUND_DUNGEON = SOUNDS_PATH+"dungeon.wav"; // not in properties
	
	
//	public static String SOUND_SKYUMORI = SOUNDS_PATH+"104529__skyumori__door-close-sqeuak-03.wav";
//	public static String SOUND_COSMICD = SOUNDS_PATH+"133008__cosmicd__annulet-of-absorption.wav";
//	public static String SOUND_CASTLEOFSAMPLES = SOUNDS_PATH+"145394__castleofsamples__rushing-river-in-the-woods.wav";
//	public static String SOUND_KLANKBEELD = SOUNDS_PATH+"172307__klankbeeld__room-tone-windy-house-121220-00.wav";
//	public static String SOUND_JIMIMOD = SOUNDS_PATH+"203497__jimimod__cricket-phasing-loop.wav";
//	public static String SOUND_DOBROIDE = SOUNDS_PATH+"205966__kangaroovindaloo__medium-wind.wav";
	
	public static String SOUND_THEME = SOUNDS_PATH+"theme.wav";
	public static String SOUND_THEME2 = SOUNDS_PATH+"theme.wav";
	
	public static float VOL_EFFECTS = 0.4f;
	public static float VOL_WIND = 0.2f;
	public static float VOL_MUSIC = 0.075f; 
	public static float VOL_REACTIONS = 0.5f;
	public static int SOUND_FADETIME = 200;
	
	public static int MAXDISTANCE = 70;
	
	/* LOGIC */
	public static int STARTPOSX = 455; // not in properties
	public static int STARTPOSY = 575; // not in properties
	
	/* OFFSET FOR COLLISION BOX */
	public static int XOFFSET_LEFT = 3;
	public static int XOFFSET_RIGHT = 3;
	public static int YOFFSET_TOP = 0;
	public static int YOFFSET_BOTTOM = 7;
	
	/* PHYSICS */
	public static float FORCE_TOPDOWN = 300f;
	public static float FORCE_RIGHTLEFT = 12f;
	public static float FORCE_GRAVITY = 3000f;
	public static float PLAYER_MASS = 0.01f;
	public static float MAX_X_VELOCITY = 110f;
	public static float MAX_Y_VELOCITY = 800f;
	public static float ARTEFACT_MASS = 0.01f;
	
	/* GAMES */
	public static int TOPDOWNGAME = 0;
	public static int SCENERED = 1;
	public static int SCENEGREEN = 2;
	public static int SCENEBLUE = 3;
	public static int OUTRO = 4;
	public static int INTRO = 5;
}

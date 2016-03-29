Thread thread = null;
SurfaceHolder surfaceHolder;
Canvas s_canvas;
#ifdef USE_BG_BUFFER
Canvas s_canvas_scale;
Bitmap s_bg_buffer;
#endif
mySound snd_player;
volatile int s_game_state = -1;
volatile int s_game_next_state = -1;
volatile int s_load_step_max = -1;
volatile int s_load_step = 0;
volatile int s_load_type = -1;
volatile boolean s_init_state = false;
volatile boolean s_exit_state = false;
volatile boolean s_skip_init_state = false;
volatile boolean running = false;
volatile boolean music_enable;
volatile boolean sfx_enable;
volatile myTouch s_touch;
volatile balloonObject[] balloons;
volatile bulletObject s_bullet;
volatile Movie s_gun;
volatile Rect s_gameplay_rect = new Rect(Define.GAMEPLAY_X, Define.GAMEPLAY_Y, Define.GAMEPLAY_W, Define.GAMEPLAY_H);
volatile long mMovieStart;
volatile myDialog s_dialog;
volatile boolean s_showdialog = false;
volatile int s_level = 1;
volatile int s_score = 0;
volatile char[] s_answer = null;
volatile boolean[] s_answer_map = null;
volatile char[] s_answer_collected = null;
volatile int s_highscore = 0;
volatile int[] s_levelUp = {0, 15, 35, 60, 100, 99999};
myButtons btn_play;
myButtons btn_help;
myButtons btn_about;
myButtons btn_gamePause;
myButtons btn_close;
myButtons btn_resume;
myButtons btn_replay;
myButtons btn_mm;
myButtons btn_exit;
myButtons btn_music_on;
myButtons btn_music_off;
myButtons btn_sfx_on;
myButtons btn_sfx_off;
volatile mySprites[] s_gameSprites;
volatile long s_frameCount;
volatile SharedPreferences s_ref;
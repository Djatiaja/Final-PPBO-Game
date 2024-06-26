package Levels;
import Entity.NPC;
import Entity.Player;
import Entity.QuestNPC;
import Entity.Sampah;
import Gamestate.Playing;
import Main.Game;
import utils.LoadSave;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;

import static Entity.NPC.*;
import static utils.LoadSave.*;

public class LevelManager {
    private Game game;
    private BufferedImage levelSpriteCity[];
    private BufferedImage[] levelSpriteOfficeBase;
    private BufferedImage[] levelSpriteOfficeInterior;
    private BufferedImage levelSpriteKamar[];
    private BufferedImage levelSpriteDesa[];
    private Level currentLevel;
    private Level city;
    private Level home;
    private Level office; //tgr
    private Level kamar;
    private Level desa;
    private Level debugingLevel;
    private Playing playing;

    public static final int CITY=1;
    public static final int DEBUGGING=2;
    public static final int OFFICE=3; //tgr
    public static final int KAMAR=4;
    public static final int DESA=5;

    public LevelManager(Game game, Playing playing){
        this.game = game;
        this.playing = playing;
        levelSpriteCity=loadLevelSprite( levelAtlasCity, new int[]{18, 27});
        levelSpriteOfficeBase=loadLevelSprite(levelAtlasOfficeBase, new int[]{23,17});
        levelSpriteOfficeInterior=loadLevelSprite(levelAtlasOfficeInterior, new int[]{89,16});
        levelSpriteKamar=loadLevelSprite(levelAtlasKamar, new int[]{20,9});
        levelSpriteDesa=loadLevelSprite(levelAtlasDesa, new int[]{27, 8});

        debugingLevel = new Level( new int[]{11,0}, levelSpriteCity);
//        System.out.println(Arrays.toString(getMapdata()));
        debugingLevel.addLevelData(LoadSave.getMapdata(),0);

        city = new Level( new int[]{11,0}, levelSpriteCity);
        city.addLevelData(LoadSave.getLevelData(LoadSave.CITY_Base, new int[]{40,40}),0);
        city.addLevelData(LoadSave.getLevelData(LoadSave.CITY_Decoration,new int[]{40,40} ),0);
        city.addLevelCollision(new Integer[]{9,63,41,37,36,35,44,13,41,45,9,409,28,63,405,432,8,459,40,64,14,10,434,435,436,62,5});
        city.addMapChanger(new int[]{6,31}, OFFICE);
        city.addMapChanger(new int[]{6,35}, OFFICE);
        city.addMapChanger(new int[]{11,0}, DESA);
        city.addMapChanger(new int[]{10,0}, DESA);


        office = new Level(new int[]{19, 5}); //tgr
        office.addLevelSprite(levelSpriteOfficeBase);
        office.addLevelSprite(levelSpriteOfficeInterior);
        office.addLevelData(LoadSave.getLevelData(LoadSave.OFFICE_Base, new int[]{20,30}),0);
        office.addLevelData(LoadSave.getLevelData(LoadSave.OFFICE_BASE2,new int[]{20,30}),0);
        office.addLevelData(LoadSave.getLevelData(LoadSave.OFFICE_BASE3,new int[]{20,30}),0);
        office.addLevelData(LoadSave.getLevelData(LoadSave.OFFICE_DECORATION,new int[]{20,30}),1);
        office.addLevelData(LoadSave.getLevelData(LoadSave.OFFICE_DECORATION2, new int[]{20,30}),1);
        office.addLevelCollision(new Integer[]{185,219,216,250,251,217,118,47,63,45,});
        office.addLevelCollision(new Integer[]{317,318,319,303,302,301,287,286,285,271,270,269,1238,434,433,417,418,216});
        office.addMapChanger(new int[]{19,4}, CITY);
        office.addMapChanger(new int[]{19,5}, CITY);
        office.addMapChanger(new int[]{19,6}, CITY);

        kamar = new Level(new int[]{3,5}, levelSpriteKamar);
        kamar.addLevelData(LoadSave.getLevelData(LoadSave.KAMAR_Base, new int[]{20,30}),0);
        kamar.addLevelData(LoadSave.getLevelData(LoadSave.KAMAR_Base1, new int[]{20,30}),0);
        kamar.addLevelCollision(new Integer[]{29,54,76,77,123,124,125,114,115,116,105,106,107,30});
        kamar.addMapChanger(new int[]{9,5}, DESA);
        kamar.addMapChanger(new int[]{9,4}, DESA);


        desa = new Level(new int[]{10,9}, levelSpriteDesa);
        desa.addLevelData(LoadSave.getLevelData(LoadSave.DESA_Base, new int[]{20,30}),0);
        desa.addLevelData(LoadSave.getLevelData(LoadSave.DESA_Decoration,new int[]{20,30} ),0);
        desa.addLevelCollision(new Integer[]{8,11,3,2,19,10,12,16,3,25,24,34,27,26,10,11,35,9});

        desa.addMapChanger(new int[]{11,29}, CITY);
        desa.addMapChanger(new int[]{12,29}, CITY);
        desa.addMapChanger(new int[]{13,29}, CITY);
        desa.addMapChanger(new int[]{10,8}, KAMAR);
        desa.addMapChanger(new int[]{10,9}, KAMAR);

        kamar.addGameEnd(new int[]{3,5});
        kamar.addGameEnd(new int[]{4,5});
        kamar.addGameEnd(new int[]{4,6});
        kamar.addGameEnd(new int[]{4,7});
        kamar.addGameEnd(new int[]{3,8});


        this.currentLevel = kamar;

    }

    public void npcHandle(Player player){
        BufferedImage[][] adamSprite = loadNPCSprite(Adam, Adam_idle);
        BufferedImage[][] ameliaSprite = loadNPCSprite(Amelia, Amelia_idle);
        BufferedImage[][] alexSprite = loadNPCSprite(Alex, Alex_idle);
        BufferedImage[][] bobSprite = loadNPCSprite(Bob, Bob_idle);

        BufferedImage imageNPC1 = LoadSave.getAtlasSprite(NPC_Image1);
        BufferedImage imageNPC2 = LoadSave.getAtlasSprite(NPC_Image2);
        BufferedImage imageNPC3 = LoadSave.getAtlasSprite(NPC_Image3);

        BufferedImage adamImage = imageNPC1.getSubimage(96,96,96,96);

        ArrayList<String[]> dialogPakbudi = new ArrayList<>();
        dialogPakbudi.add(new String[]{"Selamat pagi, Pak Budi. Saya ingin pergi ke perusahaan IT di kota.\n Apakah Bapak tahu jalan yang paling cepat ke sana?","Pemain"});
        dialogPakbudi.add(new String[]{"Selamat pagi! Tentu saja.\n Dari sini, Anda bisa berjalan kaki ke arah barat sejauh 2 blok.\n Itu jalan tercepat menurut saya.","Pak Budi"});
        dialogPakbudi.add(new String[]{"Terima kasih banyak, Pak Budi!","Pemain"});
        dialogPakbudi.add(new String[]{"Sama-sama. Semoga perjalanan Anda menyenangkan!","Pak Budi"});

        NPC PakBudi = new NPC(7*Game.TILES_SIZE, 14*Game.TILES_SIZE, 16, 16, adamSprite, new int[]{kiri,kiri,kiri,kiri,diam,diam, diam,diam,kanan,kanan,kanan,kanan}, desa, playing.getPlayer(), "Pak Budi",adamImage,dialogPakbudi);
        desa.addNPC(PakBudi);

        ArrayList<String[]> dialogBuAzki = new ArrayList<>();
        dialogBuAzki.add(new String[]{"Selamat siang, Bu.\n Saya mencari perusahaan IT yang ada di sekitar sini.\n Apakah Anda tahu tempatnya?", "Pemain"});
        dialogBuAzki.add(new String[]{"Selamat siang! Ya, saya tahu tempatnya.\n Anda perlu berjalan lurus ke depan sampai perempatan, lalu belok kanan.\n Kantornya ada di sebelah kiri jalan, tidak jauh dari sana.", "Bu Azki"});
        dialogBuAzki.add(new String[]{"Terima kasih banyak, Bu Azki!","Pemain"});
        dialogBuAzki.add(new String[]{"Sama-sama!","Bu Azki"});

        BufferedImage ImageBuAzki = imageNPC2.getSubimage(96,0,96,96);
        NPC BuAzki = new NPC(7*Game.TILES_SIZE, 9*Game.TILES_SIZE, 16, 16, ameliaSprite, new int[]{diam,diam, diam,diam}, city, playing.getPlayer(), "Bu Azki",ImageBuAzki,dialogBuAzki);
        city.addNPC(BuAzki);


        ArrayList<String[]> dialogResepsionis = new ArrayList<>();
        dialogResepsionis.add(new String[]{"Selamat siang, saya ingin mengetahui lebih banyak tentang perusahaan ini.\n Bisakah Anda membantu saya?", "Pemain"});
        dialogResepsionis.add(new String[]{"Selamat siang! Tentu, saya bisa membantu.\n Perusahaan ini memiliki beberapa divisi: Divisi Web dan Divisi Cyber Security.\n Anda bisa berbicara dengan kepala masing-masing divisi untuk informasi\n lebih lanjut.","Resepsionis"});
        dialogResepsionis.add(new String[]{"Terima kasih. Apakah saya bisa bertemu dengan kepala divisi Web\n terlebih dahulu?","Pemain"});
        dialogResepsionis.add(new String[]{"Tentu!","Resepsionis"});

        BufferedImage ImageResepsionis = imageNPC1.getSubimage(192,0,96,96);
        NPC Resepsionis = new NPC(5*Game.TILES_SIZE, 11*Game.TILES_SIZE, 16, 16, alexSprite, new int[]{diam,diam, diam,diam}, office, playing.getPlayer(), "Resepsionis",ImageResepsionis,dialogResepsionis);
        office.addNPC(Resepsionis);

        ArrayList<String[]> dialogKepalaDivisiWeb = new ArrayList<>();
        dialogKepalaDivisiWeb.add(new String[]{"Selamat siang, saya ingin tahu lebih banyak tentang Divisi Web di perusahaan ini.\n Apa saja tugas-tugas yang ada di divisi ini?", "Pemain"});
        dialogKepalaDivisiWeb.add(new String[]{"Selamat siang! Di divisi kami, ada tiga peran utama:\n Frontend Developer, Backend Developer, dan Database Administrator.\n Frontend Developer bertanggung jawab atas tampilan dan interaksi pengguna,\n Backend Developer menangani logika dan alur data,\n sementara Database Administrator mengelola dan mengoptimalkan basis data.","Kepala Divisi Web"});
        dialogKepalaDivisiWeb.add(new String[]{"Menarik sekali. Terima kasih atas penjelasannya!","Pemain"});
        dialogKepalaDivisiWeb.add(new String[]{"Sama-sama!","Kepala Divisi Web"});

        BufferedImage ImageKepalaDivisiWeb = imageNPC2.getSubimage(0,0,96,96);
        NPC KepalaDivisiWeb = new NPC(14*Game.TILES_SIZE, 3*Game.TILES_SIZE, 16, 16, ameliaSprite, new int[]{diam,diam, diam,diam}, office, playing.getPlayer(), "Kepala Divisi Web",ImageKepalaDivisiWeb,dialogKepalaDivisiWeb);
        office.addNPC(KepalaDivisiWeb);

        ArrayList<String[]> dialogKepalaDivisiCyber=new ArrayList<>();
        dialogKepalaDivisiCyber.add(new String[]{"Selamat siang, saya ingin tahu lebih banyak tentang Divisi Cyber Security\n di perusahaan ini.", "Pemain"});
        dialogKepalaDivisiCyber.add(new String[]{ "Selamat siang! Di divisi kami, fokus utama adalah melindungi\n sistem dan data perusahaan dari ancaman keamanan.\n Kami memiliki seorang ahli keamanan siber yang \n bertugas memonitor, menganalisis, dan merespons ancaman keamanan.","Kepala Divisi Cyber Security"});
        dialogKepalaDivisiCyber.add(new String[]{ "Terima kasih atas informasinya. Ini sangat membantu.","Pemain"});
        dialogKepalaDivisiCyber.add(new String[]{ "Sama-sama!","Kepala Divisi Cyber Security"});

        BufferedImage ImageKepalaDivisiCyber = imageNPC1.getSubimage(96,0,96,96);
        NPC KepalaDivisiCyber = new NPC(29*Game.TILES_SIZE, 15*Game.TILES_SIZE, 16, 16, bobSprite, new int[]{diam,diam, diam,diam}, office, playing.getPlayer(), "Kepala Divisi Cyber Security",ImageKepalaDivisiCyber,dialogKepalaDivisiCyber);
        office.addNPC(KepalaDivisiCyber);

        ArrayList<String[]> dialogBos = new ArrayList<>();
        dialogBos.add(new String[]{"Selamat siang, saya ingin mengetahui lebih banyak tentang visi dan misi\n perusahaan ini.", "Pemain"});
        dialogBos.add(new String[]{"Selamat siang! Visi kami adalah menjadi\n pemimpin di bidang teknologi informasi dengan inovasi yang berkelanjutan.\n Misi kami adalah menyediakan solusi IT terbaik yang dapat meningkatkan\n efisiensi dan produktivitas klien kami.","Bos"});
        dialogBos.add(new String[]{"Terima kasih, Pak. Visi dan misi yang sangat inspiratif.","Pemain"});
        dialogBos.add(new String[]{"Terima kasih.\n Semoga Anda menikmati kunjungan Anda di perusahaan kami.","Bos"});

        BufferedImage ImageBos = imageNPC1.getSubimage(96*3,0,96,96);
        NPC Bos = new NPC(29*Game.TILES_SIZE, 9*Game.TILES_SIZE, 16, 16, alexSprite, new int[]{diam,diam, diam,diam}, office, playing.getPlayer(), "Bos",ImageBos,dialogBos);
        office.addNPC(Bos);

        ArrayList<String[]> dialogAsisten= new ArrayList<>();
        dialogAsisten.add(new String[]{"Selamat siang, saya ingin tahu lebih banyak tentang peran Anda di perusahaan ini.", "Pemain"});
        dialogAsisten.add(new String[]{"Selamat siang! Saya bertugas membantu\n Bos dalam mengatur jadwal, mengkoordinasikan pertemuan,\n dan memastikan segala sesuatu berjalan lancar. \nSaya juga membantu dalam komunikasi internal dan eksternal.","Asisten"});
        dialogAsisten.add(new String[]{"Terima kasih atas penjelasannya!","Pemain"});
        dialogAsisten.add(new String[]{"Sama-sama. Selamat melanjutkan kunjungan Anda!","Asisten"});

        BufferedImage ImageAsisten = imageNPC3.getSubimage(96,96,96,96);
        NPC Asisten = new NPC(22*Game.TILES_SIZE, 2*Game.TILES_SIZE, 16, 16, adamSprite, new int[]{diam,diam, diam,diam}, office, playing.getPlayer(), "Asisten",ImageAsisten,dialogAsisten);
        office.addNPC(Asisten);

        ArrayList<String[]> dialogDatabase = new ArrayList<>();
        dialogDatabase.add(new String[]{"Selamat siang, apa yang Anda kerjakan sebagai Database Administrator?", "Pemain"});
        dialogDatabase.add(new String[]{"Selamat siang! Saya bertanggung jawab atas manajemen\n  dan pengelolaan basis data perusahaan. Saya memastikan data tersimpan\n  dengan aman dan dapat diakses dengan cepat.","Database Administrator"});
        dialogDatabase.add(new String[]{"Terima kasih atas penjelasannya,tugas Anda sangat penting!","Pemain"});
        dialogDatabase.add(new String[]{"Sama-sama, semoga bermanfaat!","Database Administrator"});

        BufferedImage ImageDatabase = imageNPC2.getSubimage(0,96,96,96);
        NPC Database = new NPC(2*Game.TILES_SIZE, 3*Game.TILES_SIZE, 16, 16, adamSprite, new int[]{diam,diam, diam,diam}, office, playing.getPlayer(), "Database Administrator",ImageDatabase,dialogDatabase);
        office.addNPC(Database);

        ArrayList<String[]> dialogBackEnd = new ArrayList<>();
        dialogBackEnd.add(new String[]{"Selamat siang, apa yang Anda kerjakan sebagai Backend Developer?", "Pemain"});
        dialogBackEnd.add(new String[]{"Selamat siang! Saya bertanggung jawab atas logika dan alur\n data di balik aplikasi web. Saya bekerja dengan bahasa pemrograman seperti\n Java, Python, dan Ruby.","Backend Developer"});
        dialogBackEnd.add(new String[]{"Terima kasih atas penjelasannya!","Pemain"});
        dialogBackEnd.add(new String[]{"Sama-sama, senang bisa berbagi!","Backend Developer"});

        BufferedImage ImageBackEnd = imageNPC3.getSubimage(96,96,96,96);
        NPC BackEnd = new NPC(5*Game.TILES_SIZE, 3*Game.TILES_SIZE, 16, 16, bobSprite, new int[]{diam,diam, diam,diam}, office, playing.getPlayer(), "Backend Developer",ImageBackEnd,dialogBackEnd);
        office.addNPC(BackEnd);

        ArrayList<String[]> dialogFrontEnd = new ArrayList<>();
        dialogFrontEnd.add(new String[]{"Selamat siang, saya ingin tahu lebih banyak tentang peran Frontend Developer\n di perusahaan ini.", "Pemain"});
        dialogFrontEnd.add(new String[]{"Selamat siang! Tugas saya adalah merancang dan mengembangkan\n antarmuka pengguna agar mudah digunakan dan menarik secara visual. \nSaya bekerja dengan HTML, CSS, dan JavaScript.","Frontend Developer"});
        dialogFrontEnd.add(new String[]{"Terima kasih, pekerjaan Anda sangat menarik!","Pemain"});
        dialogFrontEnd.add(new String[]{"Terima kasih, senang bisa membantu!","Frontend Developer"});

        BufferedImage ImageFrontEnd = imageNPC3.getSubimage(0,0,96,96);
        NPC FrontEnd = new NPC(8*Game.TILES_SIZE, 3*Game.TILES_SIZE, 16, 16, ameliaSprite, new int[]{diam,diam, diam,diam}, office, playing.getPlayer(), "Frontend Developer",ImageFrontEnd,dialogFrontEnd);
        office.addNPC(FrontEnd);




        ArrayList<String[]> dialogcyber = new ArrayList<>();
        dialogcyber.add(new String[]{"Selamat siang, bisa jelaskan peran Anda di Divisi Cyber Security?", "Pemain"});
        dialogcyber.add(new String[]{"Selamat siang! Saya bertugas melindungi sistem\n dari ancaman keamanan, mengidentifikasi kerentanan,\n dan merespons insiden keamanan. Saya juga memberikan \npelatihan kepada karyawan tentang praktik keamanan terbaik.","Ahli Keamanan Siber"});
        dialogcyber.add(new String[]{"Terima kasih atas penjelasannya!","Pemain"});
        dialogcyber.add(new String[]{"Sama-sama, semoga bermanfaat!","Ahli Keamanan Siber"});

        BufferedImage ImageCyber = imageNPC2.getSubimage(96,96,96,96);
        NPC Cyber = new NPC(24*Game.TILES_SIZE, 15*Game.TILES_SIZE, 16, 16, bobSprite, new int[]{diam,diam, diam,diam}, office, playing.getPlayer(), "Ahli Keamanan Siber",ImageCyber,dialogcyber);
        office.addNPC(Cyber);

        sampahHandle();
        System.out.println("Sampah total"+city.getListSampah().size());
        ArrayList<String[]> dialogAnak= new ArrayList<>();

        dialogAnak.add(new String[]{"Andaikan saja tempat ini bersih, aku bisa bermain dengan teman-temanku \n Uhuk...Uhukk polusi semakin tebal saja", "Anak"});
        BufferedImage imageanak = imageNPC2.getSubimage(96*2,96,96,96);
        QuestNPC anak = new QuestNPC(2*Game.TILES_SIZE, 15*Game.TILES_SIZE, 16, 16, adamSprite, new int[]{diam,diam,diam,diam,kanan,kanan, kanan,kanan,kanan,kanan,kanan, kanan,kanan,kanan,diam,diam,diam,diam,kiri,kiri,kiri,kiri,kiri,kiri,kiri,kiri,kiri,kiri}, city, playing.getPlayer(), "Anak",imageanak,dialogAnak);
        city.addNPC(anak);

















    }

    private BufferedImage[][] loadNPCSprite(String path, String path2){
        BufferedImage img = LoadSave.getAtlasSprite(path);
        BufferedImage[][] npcSprite = new BufferedImage[5][6];
        int j=0;
        for (int i =0; i < 4*6; i++) {
            if (i!=0&&i%6==0){
                j++;
            }
           npcSprite[j][i-j*6] = img.getSubimage(i*16,0,16,32);
        }
        BufferedImage image = LoadSave.getAtlasSprite(path2);
        for (int i = 0; i < 6; i++) {
            npcSprite[4][i] = image.getSubimage(i*16+48,0,16,32);
        }
        return npcSprite;
    }
    public BufferedImage[] loadLevelSprite(String path, int[]scale){
        BufferedImage[] levelSprite= new BufferedImage[scale[0]*scale[1]];
        BufferedImage img = LoadSave.getAtlasSprite(path);
        for (int i = 0; i < scale[0]; i++) {
            for (int j = 0; j < scale[1]; j++) {
                int index = i * scale[1] +j;
                levelSprite[index] = img.getSubimage(j*16,i*16,16,16);
            }
        }
        return levelSprite;

    }

    public void draw(Graphics g, int xOfflevelset, int yOfflevelSet) {


        for (LevelData levelData : this.currentLevel.getLevelDataList()) {
            int[][] data = levelData.getLevelData();
            BufferedImage[] sprites = this.currentLevel.getlevelSprite(levelData.getSpriteIndex());
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    int index = data[i][j];
                    if (index < 0) continue;
                    g.drawImage(sprites[index], Game.TILES_SIZE * j - xOfflevelset, Game.TILES_SIZE * i - yOfflevelSet, Game.TILES_SIZE, Game.TILES_SIZE, null);
                }
            }
        }
        for (NPC npc : this.currentLevel.getListNPC()) {
            npc.draw(g, xOfflevelset, yOfflevelSet);
        }
        for (Sampah sampah : this.currentLevel.getListSampah()) {
            sampah.draw(g, xOfflevelset,yOfflevelSet);
        }
        playing.getPlayer().render(g, xOfflevelset, yOfflevelSet);
        if (!currentLevel.getListSampah().isEmpty()){
            g.setColor(Color.RED);
            g.drawString("Sampah: " + currentLevel.getListSampah().size(), 10, 10);
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
        }


    }

    public void update(){
        for (NPC npc : this.currentLevel.getListNPC()) {
            npc.update();
        }
    }

    public int[][] getCurrentLevelData(){
        return currentLevel.getLevelData();
    }
    public int[] getCurrentSpawn(){
        return currentLevel.getSpawnCord();
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevelint, int[] spawnCord) {
        this.currentLevel.changeSpawnCord(spawnCord);
        switch (currentLevelint){
            case DEBUGGING:{
                this.currentLevel =debugingLevel;
                this.playing.resetMaxXYOffset(city);
                this.playing.getPlayer().setLevel(this.currentLevel);
                this.playing.getPlayer().resetPlayerPos(this.currentLevel.getSpawnCord());
                break;
            }
            case CITY:{
                this.currentLevel = city;
                this.playing.resetMaxXYOffset(city);
                this.playing.getPlayer().setLevel(this.currentLevel);
                this.playing.getPlayer().resetPlayerPos(this.currentLevel.getSpawnCord());
                break;
            }
            case OFFICE:{ //tgr
                this.currentLevel = office;
                this.playing.resetMaxXYOffset(office);
                this.playing.getPlayer().setLevel(this.currentLevel);
                this.playing.getPlayer().resetPlayerPos(this.currentLevel.getSpawnCord());
                break;
            }
            case KAMAR:{
                this.currentLevel = kamar;
                this.playing.resetMaxXYOffset(kamar);
                this.playing.getPlayer().setLevel(this.currentLevel);
                this.playing.getPlayer().resetPlayerPos(this.currentLevel.getSpawnCord());
                break;
            }
            case DESA:{
                this.currentLevel = desa;
                this.playing.resetMaxXYOffset(desa);
                this.playing.getPlayer().setLevel(this.currentLevel);
                this.playing.getPlayer().resetPlayerPos(this.currentLevel.getSpawnCord());
                break;
            }
        }
    }


    public Level getlevel(int currentLevelint) {
        switch (currentLevelint) {
            case DEBUGGING: {
                return debugingLevel;
            }
            case CITY: {
                return city;
            }
            case OFFICE: { //tgr
                return office;
            }
        }
        return null;
    }

    public void sampahHandle() {
        Sampah sampah1 = new Sampah(0,7,16,16, levelSpriteCity);
        Sampah sampah2 = new Sampah(12,0,16,16, levelSpriteCity);
        Sampah sampah3 = new Sampah(26,2,16,16, levelSpriteCity);
        Sampah sampah4 = new Sampah(35,15,16,16, levelSpriteCity);
        Sampah sampah5 = new Sampah(16,21,16,16, levelSpriteCity);
        Sampah sampah6 = new Sampah(8,27,16,16, levelSpriteCity);
        Sampah sampah7 = new Sampah(1,26,16,16, levelSpriteCity);
        Sampah sampah8 = new Sampah(0,38,16,16, levelSpriteCity);
        Sampah sampah9 = new Sampah(20,38,16,16, levelSpriteCity);
        Sampah sampah10 = new Sampah(39,38,16,16, levelSpriteCity);
        Sampah sampah11 = new Sampah(5,20,16,16, levelSpriteCity);
        Sampah sampah12 = new Sampah(6,23,16,16, levelSpriteCity);
        Sampah sampah13 = new Sampah(9,24,16,16, levelSpriteCity);






        city.addSampah(sampah1);
        city.addSampah(sampah2);
        city.addSampah(sampah3);
        city.addSampah(sampah4);
        city.addSampah(sampah5);
city.addSampah(sampah6);
city.addSampah(sampah7);
city.addSampah(sampah8);
city.addSampah(sampah9);
city.addSampah(sampah10);
city.addSampah(sampah11);
city.addSampah(sampah12);
city.addSampah(sampah13);


    }

}

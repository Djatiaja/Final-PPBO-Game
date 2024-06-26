package utils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class LoadSave {
    public static final String playerAtlas="/res/player_sprites.png";

    public static final String playerSideIdle ="/res/_side idle.png";
    public static final String playerSideWalk ="/res/_side walk.png";
    public static final String playerUpIdle ="/res/_up idle.png";
    public static final String playerUpWalk ="/res/_up walk.png";
    public static final String playerDownIdle ="/res/_down idle.png";
    public static final String playerDownWalk ="/res/_down walk.png";

    public static final  String debuggingAtlas ="/res/tilemap_packed.png";
    public static final String levelAtlasCity="/res/tilemap_packed.png";
    public static final String levelAtlasOfficeBase = "/res/tilemap_office_base.png";
    public static final String levelAtlasOfficeInterior = "/res/tilemap_office_interior.png";
    public static final String levelAtlasKamar = "/res/tilemap_kamar1.png";
    public static final String levelAtlasDesa="/res/tilemap_desa.png";

    public static final String CITY_Base="src/res/City_Base.csv";
    public static final String CITY_Decoration="src/res/City_Decoration.csv";
    public static final String OFFICE_Base="src/res/map_kantor_1.csv";
    public static final String OFFICE_BASE2="src/res/map_kantor_2.csv";
    public static final String OFFICE_BASE3="src/res/map_kantor_3.csv";
    public static final String OFFICE_DECORATION="src/res/map_kantor_4.csv";
    public static final String OFFICE_DECORATION2="src/res/map_kantor_5.csv";
    public static final String KAMAR_Base="src/res/kamartidur_layer1.csv";
    public static final String KAMAR_Base1="src/res/kamartidur_layer2.csv";

    public static final String DESA_Base="src/res/map_desa1.csv";
    public static final String DESA_Decoration="src/res/map_desa2.csv";
    public static final String menuButtons = "/res/Play Button.png";
    public static final String quit = "/res/Quit Button.png";
    public static final String logoGame = "/res/Asset 1.png";
    public static final String menuBackgrund = "/res/VILLAGE.png";

//    NPC
    public static final String Adam = "/res/Adam_run_16x16.png";
    public static final String Adam_idle = "/res/Adam_phone_16x16.png";
    public static final String Amelia = "/res/Amelia_run_16x16.png";
    public static final String Amelia_idle = "/res/Amelia_phone_16x16.png";
    public static final String Alex = "/res/Alex_run_16x16.png";
    public static final String Alex_idle = "/res/Alex_phone_16x16.png";
    public static final String Bob = "/res/Bob_run_16x16.png";
    public static final String Bob_idle = "/res/Bob_phone_16x16.png";
    public static final String NPC_Image1 ="/res/RPG Faces Group1.png";
    public static final String NPC_Image2 ="/res/RPG Faces Group2.png";
    public static final String NPC_Image3 ="/res/RPG Faces Group3.png";


    private static int[] spawnCord;

    public static BufferedImage getAtlasSprite(String path) {
        InputStream is = LoadSave.class.getResourceAsStream(path);
        BufferedImage img;
        try {
            img = ImageIO.read(is);
            System.out.println(img);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(img);
        return img;
    }

    public static int[][] getLevelData(String path, int[] scale){

        BufferedReader reader;
        int[][] levelData = new int[scale[0]][scale[1]];
        try {
            reader = new BufferedReader(new FileReader(path));
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                for (int i = 0; i < tokens.length; i++) {
                    levelData[row][i] = Integer.parseInt(tokens[i]);
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return levelData;
    }


    public static int[][] getMapdata(){

        int[][] levelData = new int[40][27];
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 27; j++) {
                levelData[i][j] = i*27 + j;
            }
        }
        System.out.println(Arrays.toString(levelData[0]));
        return levelData;

    }

}

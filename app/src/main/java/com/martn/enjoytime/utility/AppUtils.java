package com.martn.enjoytime.utility;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.github.johnpersano.supertoasts.SuperToast;
import com.martn.enjoytime.R;
import com.martn.enjoytime.base.BaseApplication;

import java.util.HashMap;
import java.util.Random;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.utility
 * Description: ("app的工具类")
 * Date 2016/3/1 17:19
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class AppUtils {
    /**
     * bmob--Application ID
     */
    public static String APPLICATION_ID="921464a0f919613e741e1981e187bce3";


    /**
     * bugly--id
     */
    public static String BUGLY_APP_ID = "900021188";//9eqAoa7XdDDEXrrJ
    public static SuperToast.Animations TOAST_ANIMATION = SuperToast.Animations.FLYIN;

    public static RelativeSizeSpan relativeSizeSpan;
    public static ForegroundColorSpan redForegroundSpan;
    public static ForegroundColorSpan greenForegroundSpan;
    public static ForegroundColorSpan whiteForegroundSpan;

    public static Typeface typefaceLatoRegular = null;
    public static Typeface typefaceLatoHairline = null;
    public static Typeface typefaceLatoLight = null;

    private static Random random;

    private static String lastColor0, lastColor1, lastColor2;

    public static int THEME_COLOR;

    public static String[] addColors;


    public static final int ADD_COLOR_COUNT = 3;

    public static HashMap<String, Integer> Icon2IntMap = new HashMap<>();
    public static HashMap<Integer, String> Icon2NameMap = new HashMap<>();
    public static int[] IconIdArr = new int[]{R.drawable.icon_food, R.drawable.icon_chat,R.drawable.icon_phone,
            R.drawable.icon_book,R.drawable.icon_sport,R.drawable.icon_smile,
            R.drawable.icon_heart,R.drawable.icon_shopcar,R.drawable.icon_glass,
            R.drawable.icon_video,R.drawable.icon_pc,R.drawable.icon_music};
    public static String[] IconNameArr = new String[]{"food", "chat", "phone", "book", "sport", "smile",
            "heart","shopcar","glass","video","pc","music"};

    public static HashMap<Integer, String> ColInt2StrMap = new HashMap<>();
    public static HashMap<String, Integer> ColStr2IntMap = new HashMap<>();
    public static int[] colIntArr = new int[]{R.color.bg_color_green, R.color.bg_color_purple, R.color.bg_color_orange,
            R.color.bg_color_yellow_light, R.color.bg_color_pink, R.color.bg_color_purple_black, R.color.bg_color_green_light};
    public static String[] colStrArr = new String[]{"bg_color_green","bg_color_purple","bg_color_orange",
            "bg_color_yellow_light","bg_color_pink","bg_color_purple_black","bg_color_green_light"};

    public static void init(Context context) {

        typefaceLatoRegular = Typeface.createFromAsset(
                context.getAssets(), "fonts/Lato-Regular.ttf");
        typefaceLatoHairline = Typeface.createFromAsset(
                context.getAssets(), "fonts/Lato-Hairline.ttf");
        typefaceLatoLight = Typeface.createFromAsset(
                context.getAssets(), "fonts/LatoLatin-Light.ttf");
        relativeSizeSpan = new RelativeSizeSpan(2f);
        redForegroundSpan = new ForegroundColorSpan(Color.parseColor("#ff5252"));
        greenForegroundSpan = new ForegroundColorSpan(Color.parseColor("#4ca550"));
        whiteForegroundSpan = new ForegroundColorSpan(Color.parseColor("#ffffff"));

        lastColor0 = "";
        lastColor1 = "";
        lastColor2 = "";

        random = new Random();

        THEME_COLOR = ContextCompat.getColor(BaseApplication.context(), R.color.theme_main_color);

        addColors = new String[ADD_COLOR_COUNT];
        addColors[0] = "bg_green";
        addColors[1] = "bg_purple";
        addColors[2] = "bg_yellow";

    }

    public static String getExceptionString(Exception e) {
        String errorString = e.toString() + "\r\n";
        StackTraceElement[] stackArr = e.getStackTrace();
        for (int i = 0; i < stackArr.length; i++) {
            errorString = errorString + stackArr[i].toString();
            if (i + 1 != stackArr.length) {
                errorString = errorString + "\r\n";
            }
        }
        return errorString;
    }


    public static String getIconNameById(int id) {
        initIconMap();
        String name;
        try {
            name = Icon2NameMap.get(Integer.valueOf(id));
            if (name == null || name.length() == 0) {
                return "food";
            }
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            return "food";
        }
    }

    public static String getColorNameById(int id) {
        initColorMap();
        String color;
        try {
            color = Icon2NameMap.get(Integer.valueOf(id));
            if (color == null || color.length() == 0) {
                return "food";
            }
            return color;
        } catch (Exception e) {
            e.printStackTrace();
            return "food";
        }
    }

    private static void initIconMap() {
        if (Icon2IntMap == null) {
            Icon2IntMap = new HashMap();
        }
        if (Icon2NameMap == null) {
            Icon2NameMap = new HashMap();
        }
        if (Icon2IntMap.size() == 0 || Icon2NameMap.size() == 0) {
            for (int i = 0; i < IconIdArr.length; i++) {
                Icon2IntMap.put(IconNameArr[i], Integer.valueOf(IconIdArr[i]));
                Icon2NameMap.put(Integer.valueOf(IconIdArr[i]), IconNameArr[i]);
            }
        }
    }



    public static void initColorMap() {
        if (ColStr2IntMap == null) {
            ColStr2IntMap = new HashMap();
        }
        if (ColInt2StrMap == null) {
            ColInt2StrMap = new HashMap();
        }
//        if (col_Str2xml_circle_Int_Map == null) {
//            col_Str2xml_circle_Int_Map = new HashMap();
//        }
//        if (col_Str2Ic_72_Map == null) {
//            col_Str2Ic_72_Map = new HashMap();
//        }
        for (int i = 0; i < colIntArr.length; i++) {
            ColStr2IntMap.put(colStrArr[i], Integer.valueOf(colIntArr[i]));
            ColInt2StrMap.put(Integer.valueOf(colIntArr[i]), colStrArr[i]);
//            col_Str2xml_circle_Int_Map.put(col_Str_Arr[i], Integer.valueOf(col_int_circle_Arr[i]));
//            col_Str2Ic_72_Map.put(col_Str_Arr[i], Integer.valueOf(col_int_72_Arr[i]));
        }
    }



}

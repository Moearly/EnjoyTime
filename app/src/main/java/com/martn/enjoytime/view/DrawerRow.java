package com.martn.enjoytime.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.martn.enjoytime.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.view
 * Description: ("请描述功能")
 * Date 2016/3/2 17:01
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class DrawerRow extends MaterialRippleLayout {
    private static HashMap<String, ArrayList<DrawerRow>> rows = new HashMap();
    private int currentIcon;
    private ArrayList<OnClickListener> clicks;
    private boolean hasSelected = false;

    private ImageView icon;//c
    private TextView title;//d


    private int drawer_background_color;//i
    private int drawer_background_selected_color;//e
    private int drawer_ripple_color;//f

    private int drawer_icon_selected;
    private int drawer_selected_color;//k
    private int currentTextColor;

    private String group;
    public DrawerRow(Context context) {
        this(context,null);
    }

    public DrawerRow(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawerRow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        clicks = new ArrayList<>();
        ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(R.layout.drawer_row, this, true);
        icon = (ImageView) findViewById(R.id.drawer_row_icon);
        title = (TextView) findViewById(R.id.drawer_row_text);


        TypedArray ar = context.obtainStyledAttributes(attrs, R.styleable.DrawerRow, 0, 0);
        drawer_background_color = ar.getColor(R.styleable.DrawerRow_drawer_background_color,R.color.gray);
        drawer_background_selected_color = ar.getColor(R.styleable.DrawerRow_drawer_background_selected_color,R.color.gray);

        drawer_ripple_color = ar.getColor(R.styleable.DrawerRow_drawer_ripple_color,R.color.gray);

        currentIcon = ar.getResourceId(R.styleable.DrawerRow_drawer_icon,0);
        drawer_icon_selected = ar.getResourceId(R.styleable.DrawerRow_drawer_icon_selected,0);
        icon.setImageResource(currentIcon);
        title.setText(ar.getText(R.styleable.DrawerRow_drawer_title));
        title.setTextColor(ar.getColor(R.styleable.DrawerRow_drawer_title_color, R.color.gray));
        drawer_selected_color = ar.getColor(R.styleable.DrawerRow_drawer_selected_color, R.color.gray);
        group = ar.getString(R.styleable.DrawerRow_drawer_group);

        if (group != null) {
            if (rows.get(group) == null) {
                rows.put(group, new ArrayList());
            }
            ((ArrayList)rows.get(group)).add(this);
        }

        ar.recycle();
        setRippleDuration(280);
        currentTextColor = title.getCurrentTextColor();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Iterator iterator = clicks.iterator();
                while (iterator.hasNext())
                    ((View.OnClickListener)iterator.next()).onClick(view);
                unselectAll();
                setSelected();
            }
        });

        setUnselected();
    }

    public void unselectAll() {

        if (rows.get(group) != null) {
            Iterator iterator = ((ArrayList) rows.get(group)).iterator();
            while (iterator.hasNext())
                ((DrawerRow) iterator.next()).setUnselected();
        }
    }

    public void setSelected() {
        if (group != null) {
            setRippleBackground(drawer_background_selected_color);
            title.setTextColor(drawer_selected_color);
            icon.setImageResource(drawer_icon_selected);
            hasSelected = true;
        }
    }

    public void setUnselected() {
        if ((hasSelected) && (group != null)) {
            setRippleBackground(drawer_background_color);
            title.setTextColor(currentTextColor);
            icon.setImageResource(currentIcon);
            hasSelected = false;
        }
    }


    public void addClick(View.OnClickListener listener) {
        clicks.add(listener);
    }
}

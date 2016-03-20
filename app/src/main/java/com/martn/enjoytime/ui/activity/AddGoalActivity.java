package com.martn.enjoytime.ui.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.ActionBar.LayoutParams;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.martn.enjoytime.R;
import com.martn.enjoytime.base.SwipeBackActivity;
import com.martn.enjoytime.bean.User;
import com.martn.enjoytime.db.dao.ActionDao;
import com.martn.enjoytime.utility.AppUtils;
import com.martn.enjoytime.utility.CusToast;
import com.martn.enjoytime.utility.DateHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Title: EnjoyTime
 * Package: com.martn.enjoytime.ui.activity
 * Description: ("添加目标界面")
 * Date 2014/10/5 21:02
 *
 * @author MartnLei MartnLei_163_com
 * @version V1.0
 */
public class AddGoalActivity extends SwipeBackActivity {


    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_des)
    EditText etDes;
    @Bind(R.id.tv_icon_reset)
    TextView tvIconReset;
    @Bind(R.id.gv_icon)
    GridView gvIcon;
    @Bind(R.id.tv_color_reset)
    TextView tvColorReset;
    @Bind(R.id.gv_colors)
    GridView gvColors;
    @Bind(R.id.rt_cancel)
    RoundTextView rtCancel;
    @Bind(R.id.rt_save)
    RoundTextView rtSave;

    private int selIcon;//选中的默认图标的位置
    private int selColor;
    private View mCustomView;
    private TextView tvTabOne;
    private TextView tvTabTwo;
    private View btnBack;

    private int addType = 2;//1:表示目标---2：表示习惯
    private boolean tabSelectedOne = true;
    private String actId;//action的item
    private int position = 0;//暂时未理解

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //直接用layout就可以去除base顶部的toolbar
        setContentView(R.layout.activity_add_goal);
        ButterKnife.bind(this);
        initToolbar();
        init();
    }

    private void init() {
        //判断是编辑--还是新增---这里会有两种状态--占时只是处理新增
        initIconSel();
    }

    private void initIconSel() {

    }

    private void initToolbar() {
        mCustomView = LayoutInflater.from(this).inflate(R.layout.view_toolbar_tow_tag, null);

        tvTabOne = (TextView) mCustomView.findViewById(R.id.tv_tab_one);
        tvTabTwo = (TextView) mCustomView.findViewById(R.id.tv_tab_two);
        tvTabOne.setText(getString(R.string.add_habit));
        tvTabTwo.setText(getString(R.string.add_goal));

        tvTabOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tabSelectedOne) {
                    tvTabTwo.setTextColor(getResources().getColor(R.color.theme_main_color));
                    tvTabOne.setTextColor(getResources().getColor(R.color.white));
                    tvTabOne.setBackgroundResource(R.drawable.head_tab_bg_shape_left_selected);
                    tvTabTwo.setBackgroundResource(R.drawable.head_tab_bg_shape_right);
                    tabSelectedOne = true;
                }
            }
        });

        tvTabTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tabSelectedOne) {
                    tvTabTwo.setTextColor(getResources().getColor(R.color.white));
                    tvTabOne.setTextColor(getResources().getColor(R.color.theme_main_color));
                    tvTabOne.setBackgroundResource(R.drawable.head_tab_bg_shape_left);
                    tvTabTwo.setBackgroundResource(R.drawable.head_tab_bg_shape_right_selected);
                    tabSelectedOne = false;
                }
            }
        });

        btnBack = mCustomView.findViewById(R.id.fl_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);//程序图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);//去掉返回
        getSupportActionBar().setCustomView(mCustomView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * 保存或者添加新目标
     */
    private void saveData() {
        String actName = etName.getText().toString().trim();
        if (TextUtils.isEmpty(actName)) {
            CusToast.showToast(getString(R.string.goalname_not_null));
            return;
        }
//        String level = tv_add_level_val.getText().toString().trim();
//        String needtime = tv_add_needTime_val.getText().toString().trim();
//        String deadlineVal = tv_add_deadline_val.getText().toString().trim();
//        String everyday = tv_add_everyday_val.getText().toString().trim();
        String remark = etDes.getText().toString().trim();
        ContentValues values;
        ActionDao actionDao = new ActionDao(ctx);
        String[] strArr;
        String time;
        if (addType != 1) {
            //添加习惯
            values = new ContentValues();
            values.put(ActionDao.USER_ID, Integer.valueOf(User.getInstance().getUserId()));
            values.put(ActionDao.IMAGE, AppUtils.getIconNameById(selIcon));
            values.put(ActionDao.COLOR, AppUtils.getColorNameById(selColor));
            values.put(ActionDao.ACTION_NAME, actName);
            values.put(ActionDao.TYPE, Integer.valueOf(11));//固定就是11
            values.put(ActionDao.EXPECT_SPEND, Integer.valueOf(0));
            values.put(ActionDao.DEAD_TIME, "");
            values.put(ActionDao.INTRUCTION, remark);
            values.put(ActionDao.TIME_OF_EVERYDAY, Integer.valueOf(0));
            if (actId != null) {
                //表示编辑更新用户
                if (actId.length() > 0) {
                    values.put(ActionDao.POSITION, Integer.valueOf(position));
                    values.put(ActionDao.IS_DELETE, Integer.valueOf(0));
                    values.put(ActionDao.END_UPDATE_TIME, DateHelper.getCurrentTimeString());//最后更新时间
                    actionDao.updateActionItem(values,actId);
                    CusToast.showToast(getString(R.string.modify_success));
                    setResult(7);//添加成功后返回
                    finish();
                    return;
                }
            }
            //创建新的
            time = DateHelper.getCurrentTimeString();
            values.put(ActionDao.CREATE_TIME, time);
            values.put(ActionDao.START_TIME, time);
            values.put(ActionDao.POSITION, Integer.valueOf(1));
            actionDao.addActionItem(values);
            CusToast.showToast(getString(R.string.add_success));
            setResult(RESULT_OK);
            finish();
//        } else if (isDataCorrect()) {
//            values = new ContentValues();
//            values.put("userId", Integer.valueOf(User.getInstance().getUserId()));
//            values.put("image", Val.getLabelNameById(sel_label));
//            values.put("color", (String) Val.col_Int2Str_Map.get(Integer.valueOf(sel_color)));
//            values.put("actName", actName);
//            values.put(com.umeng.update.a.c, Integer.valueOf(11));
//            values.put("expectSpend", Double.valueOf(Double.parseDouble(needtime) * 3600.0d));
//            values.put("deadline", deadlineVal + " 23:59:59");
//            values.put("intruction", remark);
//            values.put("timeOfEveryday", Double.valueOf(timeOfEveryday));
//            values.put("level", level);
//            values.put("isSubGoal", Integer.valueOf(BigGoal));
//            if (actId != null) {
//                if (actId.length() > 0) {
//                    values.put("position", Integer.valueOf(position));
//                    values.put("isDelete", Integer.valueOf(0));
//                    values.put("endUpdateTime", DateTime.getTimeString());
//                    if (isResetGoalStartTime) {
//                        values.put("startTime", DateTime.getTimeString());
//                    }
//                    db = DbUtils.getDb(context);
//                    strArr = new String[POSITION_DEFAULT];
//                    strArr[0] = actId;
//                    db.update("t_act", values, " id is ?", strArr);
//                    GeneralHelper.toastShort(context, getString(R.string.str_modify_success));
//                    setResult(7);
//                    finish();
//                    return;
//                }
//            }
//            String sql = Sql.widgetGoalsList(context);
//            Cursor cursor = DbUtils.getDb(context).rawQuery(sql, null);
//            if (cursor.getCount() > 0) {
//                while (cursor.moveToNext()) {
//                    if (cursor.getString(cursor.getColumnIndex("actName")).equals(actName)) {
//                        GeneralHelper.toastShort(context, getString(R.string.str_goal_name_repeat));
//                        return;
//                    }
//                }
//            }
//            time = DateTime.getTimeString();
//            values.put("startTime", time);
//            values.put("createTime", time);
//            DbUtils.getDb(context).rawQuery("select max(position) from t_act", null).moveToNext();
//            values.put("position", Integer.valueOf(POSITION_DEFAULT));
//            if (isManuscript) {
//                values.put("isManuscript", Integer.valueOf(0));
//                db = DbUtils.getDb(context);
//                strArr = new String[POSITION_DEFAULT];
//                strArr[0] = manuscriptId + a.b;
//                if (db.update("t_act", values, "id is ?", strArr) == 0) {
//                    DbUtils.getDb(context).insert("t_act", null, values);
//                }
//            } else {
//                DbUtils.getDb(context).insert("t_act", null, values);
//            }
//            GeneralHelper.toastShort(context, getString(R.string.str_add_success));
//            setResult(-1);
//            MyNotification myNoti = new MyNotification(context);
//            if (TimerService.timer == null) {
//                myNoti.initNoti();
//            } else {
//                myNoti.initCountingNoti(Act.getInstance().getId() + a.b);
//            }
//            finish();
        }
    }


}

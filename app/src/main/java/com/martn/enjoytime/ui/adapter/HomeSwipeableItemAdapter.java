//package com.martn.enjoytime.ui.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemAdapter;
//import com.h6ah4i.android.widget.advrecyclerview.swipeable.SwipeableItemConstants;
//import com.h6ah4i.android.widget.advrecyclerview.swipeable.action.SwipeResultAction;
//import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractSwipeableItemViewHolder;
//import com.martn.enjoytime.R;
//import com.martn.enjoytime.bean.GoalRecord;
//
//import java.util.HashMap;
//import java.util.List;
//
///**
// * Title: EnjoyTime
// * Package: com.martn.enjoytime.ui.adapter
// * Description: ("请描述功能")
// * Date 2014/10/5 23:38
// *
// * @author MartnLei MartnLei_163_com
// * @version V1.0
// */
//public class HomeSwipeableItemAdapter extends RecyclerView.Adapter<HomeSwipeableItemAdapter.MyViewHolder>
//        implements SwipeableItemAdapter<HomeSwipeableItemAdapter.MyViewHolder> {
//
//
//    private static HashMap<Integer, Boolean> pinned;
//    private final OnItemDeleteListener onItemDeleteListener;
//    private final OnItemClickListener onItemClickListener;
//    private Context mContext;
//    private List<GoalRecord> records;
//    private interface Swipeable extends SwipeableItemConstants {
//    }
//
//    public HomeSwipeableItemAdapter(Context inContext, List<GoalRecord> records, final OnItemDeleteListener onItemDeleteListener, OnItemClickListener onItemClickListener) {
//        mContext = inContext;
//        this.records = records;
//        this.onItemDeleteListener = onItemDeleteListener;
//        this.onItemClickListener = onItemClickListener;
//        // Todo optimize
//        pinned = new HashMap<>();
//        for (int i = records.size() - 1; i >= 0; i--) {
//            pinned.put((int)records.get(i).getId(), false);
//        }
//
//        setHasStableIds(true);
//    }
//
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        final View v = inflater.inflate(R.layout.home_goal_item, parent, false);
//        return new MyViewHolder(v);
//
//    }
//
//    @Override
//    public void onBindViewHolder(MyViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return records.size();
//    }
//
//    @Override
//    public SwipeResultAction onSwipeItem(MyViewHolder holder, int position, int result) {
//        switch (result) {
//            // swipe right
//            case Swipeable.RESULT_SWIPED_RIGHT:
//                if (pinned.get((int)records.get(records.size() - 1 - position).getId())) {
//                    return new UnpinResultAction(this, position);
//                } else {
//                    return new SwipeRightResultAction(this, position, onItemDeleteListener);
//                }
//            case Swipeable.RESULT_SWIPED_LEFT:
//                return new SwipeLeftResultAction(this, position);
//            case Swipeable.RESULT_CANCELED:
//            default:
//                if (position != RecyclerView.NO_POSITION) {
//                    return new UnpinResultAction(this, position);
//                } else {
//                    return null;
//                }
//        }
//    }
//
//    @Override
//    public int onGetSwipeReactionType(MyViewHolder holder, int position, int x, int y) {
//        return Swipeable.REACTION_CAN_SWIPE_BOTH_H;
//    }
//
//    @Override
//    public void onSetSwipeBackground(MyViewHolder holder, int position, int type) {
//        int bgRes = 0;
//        switch (type) {
//            case Swipeable.DRAWABLE_SWIPE_NEUTRAL_BACKGROUND:
//                bgRes = R.drawable.bg_swipe_item_neutral;
//                break;
//            case Swipeable.DRAWABLE_SWIPE_LEFT_BACKGROUND:
//                bgRes = R.drawable.bg_swipe_item_left;
//                break;
//            case Swipeable.DRAWABLE_SWIPE_RIGHT_BACKGROUND:
//                bgRes = R.drawable.bg_swipe_item_right;
//                break;
//        }
//
//        holder.itemView.setBackgroundResource(bgRes);
//    }
//
//    public static class MyViewHolder extends AbstractSwipeableItemViewHolder {
//        public FrameLayout mContainer;
//        public TextView money;
//        public TextView remark;
//        public TextView date;
//        public ImageView tagImage;
//        public TextView index;
//
//        public MyViewHolder(View v) {
//            super(v);
//            mContainer = (FrameLayout) v.findViewById(R.id.container);
//            money = (TextView) v.findViewById(R.id.money);
//            remark = (TextView) v.findViewById(R.id.remark);
//            date = (TextView) v.findViewById(R.id.date);
//            tagImage = (ImageView) v.findViewById(R.id.image_view);
//            index = (TextView)v.findViewById(R.id.index);
//        }
//
//        @Override
//        public View getSwipeableContainerView() {
//            return mContainer;
//        }
//    }
//
//    public interface OnItemDeleteListener {
//        void onSelectSumChanged();
//    }
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//}

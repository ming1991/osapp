package net.oschina.app.holder;

import android.content.Context;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.oschina.app.R;
import net.oschina.app.base.BaseHolder;
import net.oschina.app.bean.Tweet;
import net.oschina.app.bean.User;
import net.oschina.app.emoji.InputHelper;
import net.oschina.app.util.KJAnimations;
import net.oschina.app.util.StringUtils;
import net.oschina.app.util.TypefaceUtils;
import net.oschina.app.widget.MyLinkMovementMethod;
import net.oschina.app.widget.MyURLSpan;
import net.oschina.app.widget.TweetTextView;

import butterknife.BindView;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/5 15:55
 * 描述：    提供条目的视图 , 数据和视图的绑定
 */
public class TweetPageHolder extends BaseHolder<Tweet> {


    @BindView(R.id.iv_tweet_face)
    ImageView mIvTweetFace;
    @BindView(R.id.tv_tweet_name)
    TextView mTvTweetName;
    @BindView(R.id.tweet_item)
    TweetTextView mTweetItem;
    @BindView(R.id.iv_tweet_image)
    ImageView mIvTweetImage;
    @BindView(R.id.tv_likeusers)
    TextView mTvLikeusers;
    @BindView(R.id.tv_tweet_time)
    TextView mTvTweetTime;
    @BindView(R.id.tv_tweet_platform)
    TextView mTvTweetPlatform;
    @BindView(R.id.tv_del)
    TextView mTvDel;
    @BindView(R.id.tv_like_state)
    TextView mTvLikeState;
    @BindView(R.id.tv_tweet_comment_count)
    TextView mTvTweetCommentCount;
    @BindView(R.id.ll_info)
    RelativeLayout mLlInfo;

    public TweetPageHolder(Context context) {
        super(context);
    }

    /*
    * 提供视图
    * */
    @Override
    public View initHolderView() {
        View view = View.inflate(mContext, R.layout.item_tweet_list, null);
        return view;
    }

    /**
     * 视图和数据的绑定
     */
    @Override
    public void refreshHolderView(final Tweet tweet) {  //数据父类已经传过来了
        mTvDel.setVisibility(View.GONE); //只有本人才能显示删除键
        mTvTweetName.setText(tweet.getAuthor());//作者
        mTvTweetTime.setText(StringUtils.friendly_time(tweet.getPubDate())); //设置动弹的友好事件,
        //内容
        mTweetItem.setMovementMethod(MyLinkMovementMethod.a());  //设置富文本链接的点击跳转
        mTweetItem.setFocusable(false);  //下面三个方法设置让listView 的条目响应点击事件
        mTweetItem.setLongClickable(false);
        //listView条目可以响应onItemClickListener
        mTweetItem.setDispatchToParent(true);


        Spanned span = Html.fromHtml(tweet.getBody().trim());

        if (!StringUtils.isEmpty(tweet.getAttach())) {

            SpannableString str = new SpannableString("c");
            mTweetItem.setText(str);
            span = InputHelper.displayEmoji(mContext.getResources(), span);
            mTweetItem.append(span);
        } else {
            //显示表情--->处理内容里面的占位符[大笑]
            span = InputHelper.displayEmoji(mContext.getResources(), span);
            mTweetItem.setText(span);
        }
        //处理链接,富文本中的链接
        MyURLSpan.parseLinkText(mTweetItem, span);

        //评论条数
        mTvTweetCommentCount.setText(tweet.getCommentCount() + "");
        if (tweet.getLikeUser() == null) {
            mTvLikeusers.setVisibility(View.GONE);
        }
        //设置点赞效果
        setLikeUsers(tweet, mContext, mTvLikeusers, true);

        //设置点赞的字体图标
        TypefaceUtils.setTypeface(mContext, mTvLikeState);

        if (tweet.getLikeUser() == null) {
            mTvLikeState.setVisibility(View.GONE);
        } else {
            mTvLikeState.setOnClickListener(new View.OnClickListener() { //设置点击事件,需要登录
                @Override
                public void onClick(View v) {
                    updateLikeState(tweet);
                }
            });
        }

        if (tweet.getIsLike() == 1) {
            mTvLikeState.setTextColor(mContext.getResources().getColor(R.color.day_colorPrimary));
        } else {
            mTvLikeState.setTextColor(mContext.getResources().getColor(R.color.gray));
        }

        //动弹图片的显示,有则显示没有则影藏

        String tweetImgBig = tweet.getImgBig();
        if (!TextUtils.isEmpty(tweetImgBig)){
            mIvTweetImage.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(tweetImgBig).into(mIvTweetImage);

        }else{
            mIvTweetImage.setVisibility(View.GONE);
        }




    }

    private void updateLikeState(Tweet tweet) {  //真实的效果需要登录
        User user = new User();
        user.setName("billy");
        user.setId(10001);

        if (tweet.getIsLike() == 1) {
            tweet.setIsLike(0);
            tweet.setLikeCount(tweet.getLikeCount() - 1);
            if (!tweet.getLikeUser().isEmpty()) {
                tweet.getLikeUser().remove(0);
            }
            mTvLikeState.setTextColor(mContext.getResources().getColor(R.color
                    .gray));
        } else {
            mTvLikeState.setAnimation(KJAnimations.getScaleAnimation(1.5f, 300));
            mTvLikeState.setTextColor(mContext.getResources().getColor(R.color
                    .day_colorPrimary));
            tweet.setIsLike(1);
            tweet.setLikeCount(tweet.getLikeCount() + 1);
            tweet.getLikeUser().add(0, user);
        }
        setLikeUsers(tweet, mContext, mTvLikeusers, true);
    }


    /**
     * 处理点赞
     *
     * @param tweet
     * @param context
     * @param tvLikeusers
     * @param limit
     */
    private void setLikeUsers(Tweet tweet, Context context, TextView tvLikeusers, boolean limit) {
        // 构造多个超链接的html, 通过选中的位置来获取用户名
        if (tweet.getLikeCount() > 0 && tweet.getLikeUser() != null && !tweet.getLikeUser().isEmpty()) {
            tvLikeusers.setVisibility(View.VISIBLE);
            tvLikeusers.setMovementMethod(LinkMovementMethod.getInstance());
            tvLikeusers.setFocusable(false);
            tvLikeusers.setLongClickable(false);
            tvLikeusers.setText(addClickablePart(tweet, context, limit), TextView.BufferType.SPANNABLE);
        } else {
            tvLikeusers.setVisibility(View.GONE);
            tvLikeusers.setText("");
        }
    }

    /**
     * @return
     */
    private SpannableStringBuilder addClickablePart(Tweet tweet, final Context context, boolean limit) {

        StringBuilder sbBuilder = new StringBuilder();
        int showCunt = tweet.getLikeUser().size();
        if (limit && showCunt > 4) {//最多显示4个
            showCunt = 4;
        }

        for (int i = 0; i < showCunt; i++) {
            sbBuilder.append(tweet.getLikeUser().get(i).getName() + "、");
        }

        String likeUsersStr = sbBuilder.substring(0, sbBuilder.lastIndexOf("、")).toString();

        SpannableString spanStr = new SpannableString("");
        SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
        ssb.append(likeUsersStr);

        String[] likeUsers = likeUsersStr.split("、");

        if (likeUsers.length > 0) {
            // 最后一个
            for (int i = 0; i < likeUsers.length; i++) {
                final String name = likeUsers[i];
                final int start = likeUsersStr.indexOf(name) + spanStr.length();
                final int index = i;
                ssb.setSpan(new ClickableSpan() {

                    @Override
                    public void onClick(View widget) {
                        Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        // ds.setColor(R.color.link_color); // 设置文本颜色
                        // 去掉下划线
                        ds.setUnderlineText(false);
                    }

                }, start, start + name.length(), 0);
            }
        }
        if (likeUsers.length < tweet.getLikeCount()) {
            ssb.append("等");
            int start = ssb.length();
            String more = tweet.getLikeCount() + "人";
            ssb.append(more);
            ssb.setSpan(new ClickableSpan() {

                @Override
                public void onClick(View widget) {
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    // ds.setColor(R.color.link_color); // 设置文本颜色
                    // 去掉下划线
                    ds.setUnderlineText(false);
                }

            }, start, start + more.length(), 0);
            return ssb.append("觉得很赞");
        } else {
            return ssb.append("觉得很赞");
        }
    }
}

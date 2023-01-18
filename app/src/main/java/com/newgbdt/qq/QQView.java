package com.newgbdt.qq;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

public class QQView extends View {

    private int S_width=5;
    private int S_lowcolor=Color.BLUE;
    private int S_color=Color.RED;
    private int S_textcolor= Color.BLACK;
    private int S_textsize=20;
    private Paint Strip_low,Strip_new,Strip_text;
    private int Max=100;
    private int mini=50;

    public QQView(Context context) {
        this(context,null);
    }
    public QQView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }
    public QQView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array= context.obtainStyledAttributes(attrs,R.styleable.QQView);
        S_width=array.getDimensionPixelSize(R.styleable.QQView_Strip_width,S_width);
        S_lowcolor=array.getColor(R.styleable.QQView_Strip_lowcolor,S_lowcolor);
        S_color=array.getColor(R.styleable.QQView_Strip_color,S_color);
        S_textcolor=array.getColor(R.styleable.QQView_Strip_textcolor,S_textcolor);
        S_textsize=array.getDimensionPixelSize(R.styleable.QQView_Strip_textsize,S_textsize);
        array.recycle();
        //设置外画笔
        Strip_low=new Paint();
        Strip_low.setAntiAlias(true);
        Strip_low.setStrokeWidth(S_width);
        Strip_low.setColor(S_lowcolor);
        Strip_low.setStyle(Paint.Style.STROKE);
        Strip_low.setStrokeCap(Paint.Cap.ROUND);//
        //设置内画笔
        Strip_new=new Paint();
        Strip_new.setAntiAlias(true);
        Strip_new.setStrokeWidth(S_width);
        Strip_new.setColor(S_color);
        Strip_new.setStyle(Paint.Style.STROKE);
        Strip_new.setStrokeCap(Paint.Cap.ROUND);//
        //设置数字
        Strip_text=new Paint();
        Strip_text.setAntiAlias(true);
        Strip_text.setTextSize(S_textsize);
        Strip_text.setColor(S_textcolor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize (widthMeasureSpec);
        int height = MeasureSpec.getSize (heightMeasureSpec);
        setMeasuredDimension(width>height?width:height,width>height?width:height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mini==0)return;
        RectF oval_low=new RectF(S_width/2,S_width/2,getWidth()-(S_width/2),getHeight()-(S_width/2));
        //外圆
        canvas.drawArc(oval_low,135,270,false,Strip_low);
        //内圆
        float Percent=((float) mini/Max)*270;
        canvas.drawArc(oval_low,135,Percent,false,Strip_new);
        //文字
        Paint.FontMetricsInt fontMetrics = Strip_text.getFontMetricsInt();
        Rect Rect=new Rect();
        Strip_text.getTextBounds(String.valueOf(mini),0,String.valueOf(mini).length(),Rect);
        int dy=(fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom;
        int baseLine=getHeight()/2 + dy;
        int x=(getWidth()/2)-Rect.width()/2;
        canvas.drawText(String.valueOf(mini),x,baseLine,Strip_text);
    }
    public void setMax(int num){
        this.Max=num;
    }
    public void setmin_new(int min){
        this.mini=min;
        invalidate();

    }

}

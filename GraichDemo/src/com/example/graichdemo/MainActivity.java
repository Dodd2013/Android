package com.example.graichdemo;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.os.Build;

public class MainActivity extends Activity {
	Point point1,point2;
	Path path;
	List<Path> paths=new ArrayList<Path>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Panel(this));
    }

 class Panel extends View implements OnTouchListener{
	Paint paint;
	public Panel(Context context) {
		super(context);
		paint=new Paint();
		paint.setStrokeWidth(10);
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.WHITE);
		this.setOnTouchListener(this);
	}

	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.BLACK);
		for(Path path:paths){
			canvas.drawPath(path, paint);
		}
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		if(arg1.getAction()==MotionEvent.ACTION_DOWN){
			point1=new Point();
			point1.x=(int)arg1.getX();
			point1.y=(int)arg1.getY();
			path=new Path();
			path.moveTo(point1.x, point1.y);
		}else if(arg1.getAction()==MotionEvent.ACTION_MOVE){
			point2=new Point();
			point2.x=(int)arg1.getX();
			point2.y=(int)arg1.getY();
			path.lineTo(point2.x, point2.y);
			paths.add(path);
			invalidate();
		}
		return true;
	}
}
}

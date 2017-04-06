package com.cn.law.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.cn.law.R;

public class SlipButton3 extends View implements OnTouchListener {

	public boolean IsOpen;
	private boolean OnSlip = false;
	private float DownX, NowX;
	private Rect Btn_On, Btn_Off;
	private boolean isChgLsnOn = false;
	private OnChangedListener chgLsn;
	private Bitmap bg_on, bg_off, slip_btn;

	public SlipButton3(Context context) {
		super(context);
		init();
	}

	public SlipButton3(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	void init() {
		// ����ͼƬ��Դ
		bg_on = BitmapFactory
				.decodeResource(getResources(), R.drawable.body_bg_2x131);
		bg_off = BitmapFactory.decodeResource(getResources(),
				R.drawable.body_bg_2x132);
		slip_btn = BitmapFactory.decodeResource(getResources(),
				R.drawable.show_slip);

		// �����Ҫ��Rect���
		Btn_On = new Rect(0, 0, slip_btn.getWidth(), slip_btn.getHeight());
		Btn_Off = new Rect(bg_off.getWidth() - slip_btn.getWidth(), 0,
				bg_off.getWidth(), slip_btn.getHeight());
		setOnTouchListener(this);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Matrix matrix = new Matrix();
		Paint paint = new Paint();
		float x;
		// ��������жϳ�ʼ״̬

		if (IsOpen) {
			NowX = bg_on.getWidth();
		} else {
			NowX = 0;
		}
		{
			if (NowX < (bg_on.getWidth() / 2)) {
				canvas.drawBitmap(bg_on, matrix, paint);// ������ʱ�ı���
			} else {
				canvas.drawBitmap(bg_off, matrix, paint);// �����ر�ʱ�ı���
			}
			if (OnSlip) {// �Ƿ����ڻ���״̬
				if (NowX >= bg_on.getWidth())// �Ƿ񻮳�ָ����Χ,�������α��ܵ���ͷ,����������ж�
					x = bg_on.getWidth() - slip_btn.getWidth() / 2;// ��ȥ�α�1/2�ĳ���...
				else
					x = NowX - slip_btn.getWidth() / 2;
			} else {// �ǻ���״̬
				if (IsOpen)// ������ڵĿ���״̬���û��α��λ��
					x = Btn_Off.left;
				else
					x = Btn_On.left;
			}
			if (x < 0) {// ���α�λ�ý����쳣�ж�...
				x = 0;
			} else if (x > bg_on.getWidth() - slip_btn.getWidth()) {
				x = bg_on.getWidth() - slip_btn.getWidth();
			}
			canvas.drawBitmap(slip_btn, x, 0, paint);// �����α�.
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:// ����
			NowX = event.getX();
			break;
		case MotionEvent.ACTION_DOWN:// ����
			if (event.getX() > bg_on.getWidth()
					|| event.getY() > bg_on.getHeight())
				return false;
			OnSlip = true;
			DownX = event.getX();
			NowX = DownX;
			break;
		case MotionEvent.ACTION_UP:// �ɿ�
			OnSlip = false;
			boolean LastChoose = IsOpen;
			if (event.getX() >= (bg_on.getWidth() / 2))
				IsOpen = true;
			else
				IsOpen = false;
			if (isChgLsnOn && (LastChoose != IsOpen))// ��������˼�����,�͵����䷽��..
				chgLsn.OnChanged(IsOpen);
			break;
		}
		invalidate();// �ػ��ؼ�
		return true;
	}

	public void SetOnChangedListener(OnChangedListener l) {// ���ü�����,��״̬�޸ĵ�ʱ��
		isChgLsnOn = true;
		chgLsn = l;
	}

	// ��������
	interface OnChangedListener {
		abstract void OnChanged(boolean checkState);
	}

}

package com.cn.law.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.bean.MainFastBean;

public class MainAdpter extends BaseAdapter {
	private Context mcontext;
	private LayoutInflater inflater;
	public List<MainFastBean> list;

	public MainAdpter(List<MainFastBean> mlist, Context context) {
		super();
		this.mcontext = context;
		this.list = mlist;
		inflater = LayoutInflater.from(mcontext);
	}

	@Override
	public int getCount() {
		System.out.println("长度：" + list.size());
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHodler holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.main_item_layout, null);
			holder = new ViewHodler();
			holder.title = (TextView) convertView
					.findViewById(R.id.fugui_title_txt);
			holder.name = (TextView) convertView
					.findViewById(R.id.fugui_depart_txt);
			holder.number = (TextView) convertView
					.findViewById(R.id.fugui_lawno_txt);
			holder.date = (TextView) convertView
					.findViewById(R.id.fugui_release_txt);
			convertView.setTag(holder);
		} else {
			holder = (ViewHodler) convertView.getTag();
		}
		MainFastBean mainBean = list.get(position);
		if (mainBean == null) {
			return null;
		} else {

			holder.title.setText(mainBean.getTitle());
			holder.name.setText(mainBean.getfDepartmentName());
			holder.number.setText(mainBean.getfLawNo());
			holder.date.setText(mainBean.getfReleaseDate());
		
			return convertView;
		}
	}

	class ViewHodler {
		TextView title;
		TextView name;
		TextView number;
		TextView date;
	}

	@Override
	public void notifyDataSetChanged() {
		System.out.println("刷新了" + list.size());
		super.notifyDataSetChanged();
	}
}

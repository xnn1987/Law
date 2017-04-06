package com.cn.law.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cn.law.R;
import com.cn.law.bean.LawTypeBean;

public class LawTypeAdapter extends BaseAdapter {
	private Context mcontext;
	private LayoutInflater inflater;
	public  List<LawTypeBean> list;

	public  LawTypeAdapter(List<LawTypeBean> tlist, Context context) {
		super();
		this.mcontext = context;
		this.list = tlist;
		inflater = LayoutInflater.from(mcontext);
	}

	@Override
	public int getCount() {
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
		 final HolderView holder ;
		  if (convertView==null) {
		  convertView=inflater.inflate(R.layout.law_type_item_view, null);
			holder=new HolderView();
			holder.txt=(TextView) convertView.findViewById(R.id.type_view_txt);
		    convertView.setTag(holder);
		  }else{
			holder=(HolderView) convertView.getTag();
		}
		 LawTypeBean data=list.get(position);
		 if (data==null) {
			return null;
		}else{
		 holder.txt.setText(data.getFolderName());
		return  convertView;
		}
	}
   class HolderView{
	   TextView txt;
   }
}

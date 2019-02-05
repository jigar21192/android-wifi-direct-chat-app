package com.android.chatty.B_Chat;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.chatty.R;

import java.util.List;

public class B_Chat_Adapter extends BaseAdapter {
    Context context;
   List<DataModel > chatMessages;
    LayoutInflater inflater;

    public B_Chat_Adapter(Context context, List<DataModel> chatMessages) {
    this.context=context;
    this.chatMessages=chatMessages;
    inflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return chatMessages.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DataModel model=chatMessages.get(i);

        if (model.getTag().equals("1")) {

            view = inflater.inflate(R.layout.chat_text, null);
            TextView txt = (TextView) view.findViewById(R.id.txt);
            txt.setText(model.getMessage());
            txt.setTextColor(Color.BLUE);
            }
       else  {

            view = inflater.inflate(R.layout.chat_text_2, null);
            TextView txt = (TextView) view.findViewById(R.id.txt2);
            txt.setText(model.getMessage());
            txt.setTextColor(Color.RED);

            }
        return view;
    }
}

package minijeu.ut3.cassebrique;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    Scores scores;
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, Scores scores) {
        this.context = applicationContext;
        this.scores = scores;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return scores.nbScores();
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
        view = inflater.inflate(R.layout.activity_listview, null);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView score = (TextView) view.findViewById(R.id.score);
        name.setText(scores.getScores().get(i).getName());
        score.setText(scores.getScores().get(i).getPoints());
        return view;
    }
}

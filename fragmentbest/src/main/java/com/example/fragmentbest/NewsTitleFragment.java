package com.example.fragmentbest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/22.
 */

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.titlefragment,container,false);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.news_title_view);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        NewsAdapter newsAdapter=new NewsAdapter(getNews());
        recyclerView.setAdapter(newsAdapter);
        return view;
    }

    private List<News> getNews() {
        List<News> list=new ArrayList<>();
        for(int i=1;i<=50;i++){
            News news=new News();
            news.setTitle("this is title "+i);
            news.setContent("content"+i);
            list.add(news);
        }
        return list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_fragment)!=null){
            isTwoPage=true;
        }else{
            isTwoPage=false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

        public NewsAdapter(List<News> list){
            mNewslist=list;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            final ViewHolder holder=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news=mNewslist.get(holder.getAdapterPosition());
                    if(isTwoPage){
                        NewsContentFragment newsContentFragment=(NewsContentFragment)getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(),news.getContent());
                    }else {
                        NewContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news=mNewslist.get(position);
            holder.tv.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewslist.size();
        }

        private List<News> mNewslist;
        class ViewHolder extends RecyclerView.ViewHolder{
            TextView tv;
            public ViewHolder(View itemView) {
                super(itemView);
                tv=(TextView)itemView.findViewById(R.id.news_title) ;
            }
        }

    }
}

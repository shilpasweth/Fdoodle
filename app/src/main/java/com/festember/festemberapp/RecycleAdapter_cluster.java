package com.festember.festemberapp;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import com.festember.festemberapp.R;
/**
 * Created by Bharath on 17-Sep-15.
 */
public class RecycleAdapter_cluster extends RecyclerView.Adapter<RecycleAdapter_cluster.ClusterViewHolder> {


        List<ClusterPage.Cluster> clusters;

        Context context;
    CustomItemClickListener listener;




    RecycleAdapter_cluster(List<ClusterPage.Cluster> clusters){

        this.clusters = clusters;


    }



        public static class ClusterViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView clustername;
            RelativeLayout r;

            ClusterViewHolder(View itemView) {
                super(itemView);

                cv = (CardView) itemView.findViewById(R.id.cv);

                clustername = (TextView) itemView.findViewById(R.id.cluster_name);
                r=(RelativeLayout)itemView.findViewById(R.id.rl);



            }




        }

    @Override
    public ClusterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cluster_layout, viewGroup, false);
        final ClusterViewHolder pvh = new ClusterViewHolder(v);

        return pvh;
    }


    @Override
    public void onBindViewHolder(ClusterViewHolder clusterViewHolder, int i) {
        clusterViewHolder.clustername.setTypeface(Utilities.typeface);
        clusterViewHolder.clustername.setText(clusters.get(i).name);

        clusterViewHolder.r.setBackgroundColor(Utilities.colours.get(i%5));
        clusterViewHolder.r.setAlpha(100f);


    }

    @Override
    public int getItemCount() {
        return clusters.size();
    }
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}



package mymap.com.map.View.utlites;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by Senthil on 10-02-2018.
 */

public abstract class RecyclerViewBaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading = false;
    private boolean moreDataAvailable = false;

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        onLoadMoreListener = loadMoreListener;
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        this.moreDataAvailable = moreDataAvailable;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Log.e("position", String.valueOf(position));
       /* if (position >= getItemCount() - 1 && moreDataAvailable && !isLoading && onLoadMoreListener != null) {
            isLoading = true;
            onLoadMoreListener.onLoadMore();
        }*/
    }

    public void notifyDataChanged() {
        isLoading = false;
        notifyDataSetChanged();
    }
}

package id.sekar.mvvmroom.utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Space extends RecyclerView.ItemDecoration {

    private int itemOffset;

    public Space(int itemOffset) {
        this.itemOffset = itemOffset;
    }

    public Space(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelOffset(itemOffsetId));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildLayoutPosition(view) == 0)
            outRect.top = itemOffset;
        outRect.left = itemOffset;
        outRect.right = itemOffset;
        outRect.bottom = itemOffset;
    }
}

package a.m.restaurant_automation.manager;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.responseModel.MenuItemResponse;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {
    private ArrayList<MenuItemResponse> menuItemResponse;
    int size = 0;
    private Context context;
    public View.OnClickListener onItemListener;



    public MenuItemAdapter(ArrayList<MenuItemResponse> menuItemResponse, Context context) {
        this.menuItemResponse = menuItemResponse;
        size = this.menuItemResponse.size();
        this.context = context;
    }

    @NonNull
    @Override
    public MenuItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_employee_menu_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemAdapter.ViewHolder holder, int position) {
        holder.menuItemName.setText("Name: "+menuItemResponse.get(position).getMenuItemName());
        holder.menuItemPrice.setText("Price: "+menuItemResponse.get(position).getPrice().toString());

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        onItemListener = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView menuItemName, menuItemPrice;
        Button removeItemButton;
        ImageView menuItemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuItemImage= itemView.findViewById(R.id.imageView_menuItem);
            menuItemName = itemView.findViewById(R.id.textView_menuName);
            menuItemPrice = itemView.findViewById(R.id.textView_menuPrice);
            removeItemButton = itemView.findViewById(R.id.removeItemButton);
            removeItemButton = itemView.findViewById(R.id.removeItemButton);

            removeItemButton.setOnClickListener(onItemListener);
            itemView.setTag(this);
        }
    }
}

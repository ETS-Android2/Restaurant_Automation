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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.responseModel.MenuItemResponse;
import a.m.restaurant_automation.responseModel.TableResponseModel;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {
    private ArrayList<MenuItemResponse> menuItemResponse;
    private ArrayList<TableResponseModel> tableResponseModel;
    int size = 0;
    int test=0;
    private Context context;
    public View.OnClickListener onItemListener;
    String url = "https://cdn2.creativecirclemedia.com/neni/original/20190917-140036-Ratatouille-T5_93975.jpg";

    boolean isTable =false;
    boolean isMenuItem = false;


    public MenuItemAdapter(ArrayList<MenuItemResponse> menuItemResponse, Context context) {
        this.menuItemResponse = menuItemResponse;
        size = this.menuItemResponse.size();
        isMenuItem = true;
        this.context = context;
    }

    public MenuItemAdapter(ArrayList<TableResponseModel> tableResponseModel, Context context,int test) {
        this.tableResponseModel = tableResponseModel;
        size = this.tableResponseModel.size();
        this.context = context;
        this.test=test;
        isTable = true;
    }

    @NonNull
    @Override
    public MenuItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (isMenuItem==true) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_employee_menu_list, parent, false);
            return new ViewHolder(view);
        }
        else if (isTable == true){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_employee_table, parent, false);
            return new ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemAdapter.ViewHolder holder, int position) {
        if (isMenuItem) {
            holder.menuItemName.setText("Name: " + menuItemResponse.get(position).getMenuItemName());
            holder.menuItemPrice.setText("Price: " + menuItemResponse.get(position).getPrice().toString() + " $");
            Picasso.get().load(url).into(holder.menuItemImage);
        }
        else if (isTable){
            holder.tableNumber.setText("Table Number: "+ tableResponseModel.get(position).getTableId());
            holder.tableCapacity.setText("Table's Capacity: "+ tableResponseModel.get(position).getCapacity());
            holder.tableStatus.setText("Table Status: "+tableResponseModel.get(position).getAvailability());
        }
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

        TextView tableNumber, tableCapacity, tableStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if(isMenuItem){
                menuItemImage= itemView.findViewById(R.id.imageView_menuItem);
                menuItemName = itemView.findViewById(R.id.textView_menuName);
                menuItemPrice = itemView.findViewById(R.id.textView_menuPrice);
                removeItemButton = itemView.findViewById(R.id.removeItemButton);
                removeItemButton = itemView.findViewById(R.id.removeItemButton);
                removeItemButton.setOnClickListener(onItemListener);
            }
            else if(isTable) {
                tableNumber = itemView.findViewById(R.id.textView_tableNumber);
                tableCapacity = itemView.findViewById(R.id.textView_tableCapacity);
                tableStatus = itemView.findViewById(R.id.textView_tableStatus);
            }
            itemView.setTag(this);
        }
    }
}

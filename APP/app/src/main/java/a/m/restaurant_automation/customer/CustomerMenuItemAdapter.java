package a.m.restaurant_automation.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.responseModel.MenuItemResponse;

public class CustomerMenuItemAdapter extends RecyclerView.Adapter<CustomerMenuItemAdapter.ViewHolder> {

    private ArrayList<MenuItemResponse> menuItemResponsecustomer;
    int size = 0;
    private Context context;
    public View.OnClickListener onItemListener_customer;

    public CustomerMenuItemAdapter(ArrayList<MenuItemResponse> menuItemResponse, Context context) {
        this.menuItemResponsecustomer = menuItemResponse;
        size = this.menuItemResponsecustomer.size();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_customer_menu_list,parent,false);
        return new ViewHolder(view);
 }

    @Override
    public void onBindViewHolder(@NonNull CustomerMenuItemAdapter.ViewHolder holder, int position) {
        holder.menuItemName.setText("Name : "+menuItemResponsecustomer.get(position).getMenuItemName());
        holder.menuItemPrice.setText("Price : "+menuItemResponsecustomer.get(position).getPrice().toString() +" $");
    }


    @Override
    public int getItemCount() { return size; }


    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        onItemListener_customer = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView menuItemName, menuItemPrice;
        Button addItemButton;
        ImageView menuItemImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuItemImage= itemView.findViewById(R.id.imageView_menuItem_customer);
            menuItemName = itemView.findViewById(R.id.textView_menuName_customer);
            menuItemPrice = itemView.findViewById(R.id.textView_menuPrice_customer);
            addItemButton = itemView.findViewById(R.id.customer_addItemButton);
           // addItemButton = itemView.findViewById(R.id.customer_addItemButton);

          //  addItemButton.setOnClickListener(onItemListener_customer);
            itemView.setTag(this);
        }
    }

}

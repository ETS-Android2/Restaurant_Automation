package a.m.restaurant_automation.customer;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import a.m.restaurant_automation.requestModel.AddToCartRequestModel;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import a.m.restaurant_automation.R;
import a.m.restaurant_automation.responseModel.MenuItemResponse;

public class CustomerMenuItemAdapter extends RecyclerView.Adapter<CustomerMenuItemAdapter.ViewHolder> {

    private ArrayList<MenuItemResponse> menuItemResponsecustomer;
    int countQuantity;
    int size = 0;
    AddToCartRequestModel tag;
    private Context context;
    public View.OnClickListener onItemListener_customer;
    String url = "https://cdn2.creativecirclemedia.com/neni/original/20190917-140036-Ratatouille-T5_93975.jpg";

    boolean is =false;
    boolean isMenuItem = false;


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

    private final Handler handler=new Handler();

    @Override
    public void onBindViewHolder(@NonNull final CustomerMenuItemAdapter.ViewHolder holder, final int position) {
        holder.menuItemName.setText("Name : "+menuItemResponsecustomer.get(position).getMenuItemName());
        holder.menuItemPrice.setText("Price : "+menuItemResponsecustomer.get(position).getPrice().toString() +" $");
        holder.addItemButton.setTag(menuItemResponsecustomer.get(position).getMenuItemId());
        holder.getAdapterPosition() ;
        tag = new AddToCartRequestModel();


        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        countQuantity = Integer.parseInt(String.valueOf(holder.menuItemQuantity.getText()));
                        countQuantity++;
                        tag.itemId = menuItemResponsecustomer.get(position).getMenuItemId();
                        tag.quantity= countQuantity;
                        holder.addItemButton.setTag(tag);
                        holder.menuItemQuantity.setText(""+countQuantity);
                    }
                });

            }
        });
        holder.subItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                         countQuantity = Integer.parseInt(String.valueOf(holder.menuItemQuantity.getText()));
                        if (countQuantity==0){
                            holder.menuItemQuantity.setText("0");
                        }else {
                            countQuantity -=1;
                            holder.menuItemQuantity.setText(""+countQuantity);
                        }

                    }
                });

            }
        });


        Picasso.get().load(url).into(holder.menuItemImage);
    }


    @Override
    public int getItemCount() { return size; }


    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        onItemListener_customer = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView menuItemName, menuItemPrice,menuItemQuantity;
        Button  addItemButton, plusItem , subItem;
        ImageView menuItemImage;
        //TextView menuitemid,quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuItemImage= itemView.findViewById(R.id.imageView_menuItem_customer);
            menuItemName = itemView.findViewById(R.id.textView_menuName_customer);
            menuItemPrice = itemView.findViewById(R.id.textView_menuPrice_customer);
            addItemButton = itemView.findViewById(R.id.customer_addItemButton);
            plusItem=itemView.findViewById(R.id.buttonPlusCapacityItem);
            subItem=itemView.findViewById(R.id.buttonMinusCapacityItem);
            plusItem.setOnClickListener(onItemListener_customer);
            subItem.setOnClickListener(onItemListener_customer);

            addItemButton.setOnClickListener(onItemListener_customer);
            menuItemQuantity=itemView.findViewById(R.id.textviewcapacity);
            itemView.setTag(this);

        }
    }

}
